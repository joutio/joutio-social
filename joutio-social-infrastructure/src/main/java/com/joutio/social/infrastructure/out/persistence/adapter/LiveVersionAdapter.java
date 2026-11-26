package com.joutio.social.infrastructure.out.persistence.adapter;

import com.joutio.social.domain.port.out.LiveVersionPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Version des compteurs d'un joueur ({@code live_version}) : un upsert atomique, qui verrouille la ligne jusqu'à la fin
 * de la transaction. Deux écritures concurrentes pour le même joueur obtiennent donc deux versions successives, et la
 * seconde compte l'état laissé par la première.
 */
@Component
public class LiveVersionAdapter implements LiveVersionPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public long increment(String userId) {
        Number version = (Number) entityManager.createNativeQuery("""
                        INSERT INTO live_version (user_id, version) VALUES (:userId, 1)
                        ON CONFLICT (user_id) DO UPDATE SET version = live_version.version + 1
                        RETURNING version""")
                .setParameter("userId", userId)
                .getSingleResult();
        return version.longValue();
    }
}
