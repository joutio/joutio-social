package com.joutio.social.domain.port.in;

import com.joutio.social.domain.exception.SocialExceptions;
import com.joutio.social.domain.model.TopicFollower;
import com.joutio.social.domain.query.TopicFollowerQuery;

import java.util.concurrent.CompletableFuture;

/**
 * Port entrant — Cas d'utilisation : récupérer un suivi de sujet par son id
 * ({@code followId} déterministe {@code userId + ":" + topicId}).
 */
public interface GetTopicFollowerUseCase {

    CompletableFuture<TopicFollower> getById(TopicFollowerQuery.GetTopicFollowerByIdQuery query)
            throws SocialExceptions.TopicFollowerNotFoundProblem;

    default CompletableFuture<TopicFollower> getById(String followId)
            throws SocialExceptions.TopicFollowerNotFoundProblem {
        return getById(new TopicFollowerQuery.GetTopicFollowerByIdQuery(followId));
    }
}
