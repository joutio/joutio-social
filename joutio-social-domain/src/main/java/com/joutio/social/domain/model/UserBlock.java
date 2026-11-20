package com.joutio.social.domain.model;

import java.time.Instant;

/** Joueur bloqué par {@code blockerId} (read model). */
public record UserBlock(
        String blockId,
        String blockerId,
        String blockedId,
        Instant blockedAt
) {

    /** Identifiant déterministe du blocage : {@code blockerId + ":" + blockedId}. */
    public static String id(String blockerId, String blockedId) {
        return blockerId + ":" + blockedId;
    }
}
