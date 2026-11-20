package com.joutio.social.domain.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

/** Blocage d'un joueur, identifié par {@code blockerId:blockedId} ({@code PlayerBlockIds}). */
public interface PlayerBlockCommand {

    String blockId();

    record BlockPlayerCommand(
            @TargetAggregateIdentifier String blockId,
            String blockerId,
            String blockedId
    ) implements PlayerBlockCommand {
    }

    /** Déblocage idempotent ; réservé à l'auteur du blocage. */
    record UnblockPlayerCommand(
            @TargetAggregateIdentifier String blockId,
            String actorId
    ) implements PlayerBlockCommand {
    }
}
