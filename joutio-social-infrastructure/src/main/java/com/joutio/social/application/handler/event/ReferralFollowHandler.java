package com.joutio.social.application.handler.event;

import com.joutio.identity.domain.event.UserEvent;
import com.joutio.social.domain.command.UserFollowerCommand;
import com.joutio.social.domain.model.FollowerIds;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Parrainage ({@code design/specs/screens/partage.md} § 6) : le filleul suit son parrain dès son arrivée.
 *
 * <p>Le suivi est idempotent (identifiant {@code filleul:parrain}, agrégat en {@code CREATE_IF_MISSING}) : un
 * rejeu de l'événement ne crée pas de doublon, et un suivi déjà existant reste tel quel.</p>
 */
@Component
@ProcessingGroup("referral-follow")
public class ReferralFollowHandler {

    private static final Logger logger = LoggerFactory.getLogger(ReferralFollowHandler.class);

    private final CommandGateway commandGateway;

    public ReferralFollowHandler(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(UserEvent.UserReferredEvent event) {
        commandGateway.sendAndWait(new UserFollowerCommand.FollowUserCommand(
                FollowerIds.user(event.userId(), event.referrerId()),
                event.userId(),
                event.referrerId()));
        logger.info("Referral follow created: followerId={}, followedId={}", event.userId(), event.referrerId());
    }
}
