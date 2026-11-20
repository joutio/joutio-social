package com.joutio.social.domain.query;

/** Lectures des blocages. */
public interface PlayerBlockQuery {

    /** Joueurs bloqués par {@code userId}, du plus récent au plus ancien ({@code List<PlayerBlock>}). */
    record GetBlockedPlayersQuery(String userId) implements PlayerBlockQuery {
    }

    /**
     * Joueurs en relation de blocage avec {@code userId}, dans un sens ou dans l'autre ({@code List<String>}) : le
     * blocage est symétrique pour les défis, les abonnements et l'appariement.
     */
    record GetBlockRelationsQuery(String userId) implements PlayerBlockQuery {
    }
}
