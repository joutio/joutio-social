package com.joutio.social.domain.port.in;

import com.joutio.social.domain.exception.SocialExceptions;
import com.joutio.social.domain.model.UserFollower;
import com.joutio.social.domain.query.UserFollowerQuery;

import java.util.concurrent.CompletableFuture;

/**
 * Port entrant — Cas d'utilisation : récupérer un abonnement à un joueur par son id
 * ({@code followId} déterministe {@code followerId + ":" + followedId}).
 */
public interface GetUserFollowerUseCase {

    CompletableFuture<UserFollower> getById(UserFollowerQuery.GetUserFollowerByIdQuery query)
            throws SocialExceptions.UserFollowerNotFoundProblem;

    default CompletableFuture<UserFollower> getById(String followId)
            throws SocialExceptions.UserFollowerNotFoundProblem {
        return getById(new UserFollowerQuery.GetUserFollowerByIdQuery(followId));
    }
}
