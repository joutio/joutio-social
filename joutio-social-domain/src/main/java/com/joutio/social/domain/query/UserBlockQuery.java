package com.joutio.social.domain.query;

/** Lectures des blocages. */
public interface UserBlockQuery {

    /** Joueurs bloqués par {@code userId}, du plus récent au plus ancien ({@code List<UserBlock>}). */
    record GetBlockedUsersQuery(String userId) implements UserBlockQuery {
    }

    /**
     * Joueurs en relation de blocage avec {@code userId}, dans un sens ou dans l'autre ({@code List<String>}) : le
     * blocage est symétrique pour les défis, les abonnements et l'appariement.
     */
    record GetBlockRelationsQuery(String userId) implements UserBlockQuery {
    }
}
