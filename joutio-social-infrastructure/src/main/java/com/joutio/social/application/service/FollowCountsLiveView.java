package com.joutio.social.application.service;

import com.joutio.social.domain.model.UserFollowCounts;
import com.joutio.social.domain.model.UserFollowCountsUpdate;
import com.joutio.social.domain.port.out.LiveVersionPort;
import com.joutio.social.domain.port.out.UserFollowerRepositoryPort;
import com.joutio.social.domain.query.UserFollowerQuery.WatchUserFollowCountsQuery;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Compteurs d'abonnements surveillés par les caches du BFF ({@link WatchUserFollowCountsQuery}).
 *
 * <p>{@link #changed(Collection)} s'appelle dans le handler qui vient d'écrire : la version de chaque joueur est
 * incrémentée dans la même transaction (la ligne reste verrouillée, donc deux écritures pour le même joueur se suivent),
 * puis ses compteurs sont relus et émis. L'émission part au commit de l'unité de travail.</p>
 */
@Service
public class FollowCountsLiveView {

    private final UserFollowerRepositoryPort follows;
    private final LiveVersionPort versions;
    private final QueryUpdateEmitter emitter;

    public FollowCountsLiveView(UserFollowerRepositoryPort follows, LiveVersionPort versions,
                                QueryUpdateEmitter emitter) {
        this.follows = follows;
        this.versions = versions;
        this.emitter = emitter;
    }

    public UserFollowCounts read(String userId) {
        return new UserFollowCounts(follows.countFollowing(userId), follows.countFollowers(userId));
    }

    public void changed(Collection<String> userIds) {
        userIds.stream().distinct().forEach(userId -> {
            long version = versions.increment(userId);
            emitter.emit(WatchUserFollowCountsQuery.class, _ -> true,
                    new UserFollowCountsUpdate(userId, version, read(userId)));
        });
    }

    /** Compte supprimé : ses compteurs sont émis vides (le handler de suppression efface aussi sa version). */
    public void deleted(String userId) {
        emitter.emit(WatchUserFollowCountsQuery.class, _ -> true,
                new UserFollowCountsUpdate(userId, Long.MAX_VALUE, null));
    }
}
