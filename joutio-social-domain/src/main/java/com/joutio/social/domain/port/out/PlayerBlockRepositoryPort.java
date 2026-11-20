package com.joutio.social.domain.port.out;

import com.joutio.social.domain.model.PlayerBlock;

import java.util.List;

public interface PlayerBlockRepositoryPort {

    void save(PlayerBlock block);

    void deleteById(String blockId);

    /** Joueurs bloqués par {@code blockerId}, plus récents d'abord. */
    List<PlayerBlock> findBlockedBy(String blockerId);

    /** Joueurs en relation de blocage avec {@code userId}, dans un sens ou dans l'autre. */
    List<String> findRelations(String userId);
}
