package com.joutio.social.domain.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

/** Blocage d'un joueur, identifié par {@code blockerId:blockedId} ({@code UserBlockIds}). */
public interface UserBlockCommand {

    String blockId();

    record BlockUserCommand(
            @TargetAggregateIdentifier String blockId,
            String blockerId,
            String blockedId
    ) implements UserBlockCommand {
    }

    /** Déblocage idempotent ; réservé à l'auteur du blocage. */
    record UnblockUserCommand(
            @TargetAggregateIdentifier String blockId,
            String actorId
    ) implements UserBlockCommand {
    }
}
