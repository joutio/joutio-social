package com.joutio.social.domain.event;

import java.time.Instant;

/**
 * Blocage d'un joueur. L'effet est symétrique (ni l'un ni l'autre ne peut défier, suivre ou être apparié à l'autre) ;
 * {@code UserBlockedEvent} retire les abonnements entre les deux (social) et annule leurs défis en attente
 * (matchmaking).
 */
public interface UserBlockEvent {

    String blockId();

    String blockerId();

    String blockedId();

    record UserBlockedEvent(
            String blockId,
            String blockerId,
            String blockedId,
            Instant blockedAt
    ) implements UserBlockEvent {
    }

    record UserUnblockedEvent(
            String blockId,
            String blockerId,
            String blockedId,
            Instant unblockedAt
    ) implements UserBlockEvent {
    }
}
