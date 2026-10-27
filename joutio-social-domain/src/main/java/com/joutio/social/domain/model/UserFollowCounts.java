package com.joutio.social.domain.model;

/**
 * Compteurs d'abonnements d'un joueur (source de vérité : {@code joutio-social}).
 */
public record UserFollowCounts(long following, long followers) {
}
