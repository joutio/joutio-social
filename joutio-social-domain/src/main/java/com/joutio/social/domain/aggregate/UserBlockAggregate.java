package com.joutio.social.domain.aggregate;

import com.joutio.social.domain.command.UserBlockCommand;
import com.joutio.social.domain.event.UserBlockEvent;
import com.joutio.social.domain.exception.SocialExceptions;
import lombok.Getter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateCreationPolicy;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

/**
 * Blocage d'un joueur par un autre, identifié par {@code blockerId:blockedId}. Comme le suivi, l'agrégat est non
 * destructif ({@code blocked} repasse à faux au déblocage) : bloquer à nouveau est idempotent et sans course.
 */
@Getter
@Aggregate
public class UserBlockAggregate {

    @AggregateIdentifier
    private String blockId;
    private String blockerId;
    private String blockedId;
    private boolean blocked;

    protected UserBlockAggregate() {
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(UserBlockCommand.BlockUserCommand command) {
        if (blocked) {
            return;
        }
        if (command.blockerId().equals(command.blockedId())) {
            throw new SocialExceptions.CannotBlockSelfProblem(command.blockerId());
        }
        AggregateLifecycle.apply(new UserBlockEvent.UserBlockedEvent(
                command.blockId(), command.blockerId(), command.blockedId(), Instant.now()));
    }

    @CommandHandler
    public void handle(UserBlockCommand.UnblockUserCommand command) {
        if (!blocked) {
            return;
        }
        if (!blockerId.equals(command.actorId())) {
            throw new SocialExceptions.NotBlockOwnerProblem(blockId, command.actorId());
        }
        AggregateLifecycle.apply(new UserBlockEvent.UserUnblockedEvent(
                blockId, blockerId, blockedId, Instant.now()));
    }

    @EventSourcingHandler
    public void on(UserBlockEvent.UserBlockedEvent event) {
        this.blockId = event.blockId();
        this.blockerId = event.blockerId();
        this.blockedId = event.blockedId();
        this.blocked = true;
    }

    @EventSourcingHandler
    public void on(UserBlockEvent.UserUnblockedEvent event) {
        this.blocked = false;
    }
}
