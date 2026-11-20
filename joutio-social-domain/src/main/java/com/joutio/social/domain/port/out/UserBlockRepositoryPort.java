package com.joutio.social.domain.port.out;

import com.joutio.social.domain.model.UserBlock;

import java.util.List;

public interface UserBlockRepositoryPort {

    void save(UserBlock block);

    void deleteById(String blockId);

    /** Joueurs bloqués par {@code blockerId}, plus récents d'abord. */
    List<UserBlock> findBlockedBy(String blockerId);

    /** Joueurs en relation de blocage avec {@code userId}, dans un sens ou dans l'autre. */
    List<String> findRelations(String userId);
}
