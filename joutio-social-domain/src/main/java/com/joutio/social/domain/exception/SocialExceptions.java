package com.joutio.social.domain.exception;

import com.joutio.microservice.core.domain.exception.ProblemCategory;

import java.util.Map;

public final class SocialExceptions {

    private SocialExceptions() {
    }

    public static class CannotFollowSelfProblem extends SocialProblem {
        public CannotFollowSelfProblem(String userId) {
            super("urn:joutio:social:user:followSelf",
                    "Cannot follow yourself",
                    "User " + userId + " cannot follow themselves",
                    Map.of("userId", userId));
        }
    }

    public static class TopicFollowerNotFoundProblem extends SocialProblem {
        public TopicFollowerNotFoundProblem(String followId) {
            super("urn:joutio:social:topicFollow:notFound",
                    ProblemCategory.BUSINESS_RESOURCE_MISSING,
                    "Topic follow not found",
                    "The topic follow " + followId + " was not found",
                    Map.of("followId", followId));
        }
    }

    public static class UserFollowerNotFoundProblem extends SocialProblem {
        public UserFollowerNotFoundProblem(String followId) {
            super("urn:joutio:social:userFollow:notFound",
                    ProblemCategory.BUSINESS_RESOURCE_MISSING,
                    "User follow not found",
                    "The user follow " + followId + " was not found",
                    Map.of("followId", followId));
        }
    }

    /**
     * Le désabonnement n'est autorisé que pour le joueur concerné par le suivi.
     */
    public static class NotFollowOwnerProblem extends SocialProblem {
        public NotFollowOwnerProblem(String followId, String actorId) {
            super("urn:joutio:social:follow:notOwner",
                    ProblemCategory.PERMISSION,
                    "Not the owner of this follow",
                    "User " + actorId + " cannot remove the follow " + followId,
                    Map.of("followId", followId, "actorId", actorId));
        }
    }
}
