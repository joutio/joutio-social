package com.joutio.social.domain.aggregate;

import com.joutio.social.domain.command.PlayerBlockCommand;
import com.joutio.social.domain.event.PlayerBlockEvent;
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
public class PlayerBlockAggregate {

    @AggregateIdentifier
    private String blockId;
    private String blockerId;
    private String blockedId;
    private boolean blocked;

    protected PlayerBlockAggregate() {
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    public void handle(PlayerBlockCommand.BlockPlayerCommand command) {
        if (blocked) {
            return;
        }
        if (command.blockerId().equals(command.blockedId())) {
            throw new SocialExceptions.CannotBlockSelfProblem(command.blockerId());
        }
        AggregateLifecycle.apply(new PlayerBlockEvent.PlayerBlockedEvent(
                command.blockId(), command.blockerId(), command.blockedId(), Instant.now()));
    }

    @CommandHandler
    public void handle(PlayerBlockCommand.UnblockPlayerCommand command) {
        if (!blocked) {
            return;
        }
        if (!blockerId.equals(command.actorId())) {
            throw new SocialExceptions.NotBlockOwnerProblem(blockId, command.actorId());
        }
        AggregateLifecycle.apply(new PlayerBlockEvent.PlayerUnblockedEvent(
                blockId, blockerId, blockedId, Instant.now()));
    }

    @EventSourcingHandler
    public void on(PlayerBlockEvent.PlayerBlockedEvent event) {
        this.blockId = event.blockId();
        this.blockerId = event.blockerId();
        this.blockedId = event.blockedId();
        this.blocked = true;
    }

    @EventSourcingHandler
    public void on(PlayerBlockEvent.PlayerUnblockedEvent event) {
        this.blocked = false;
    }
}
