package com.joutio.social.domain.model;

/**
 * Update de {@code WatchUserFollowCountsQuery} : les compteurs d'un joueur après un abonnement ou un désabonnement,
 * {@code null} si son compte est supprimé. {@code version} est monotone par joueur.
 */
public record UserFollowCountsUpdate(String userId, long version, UserFollowCounts counts) {
}
