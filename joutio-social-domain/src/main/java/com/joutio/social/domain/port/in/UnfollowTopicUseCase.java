package com.joutio.social.domain.port.in;

import com.joutio.social.domain.command.TopicFollowerCommand;

import java.util.concurrent.CompletableFuture;

public interface UnfollowTopicUseCase {
    CompletableFuture<String> unfollow(TopicFollowerCommand.UnfollowTopicCommand command);

    default CompletableFuture<String> unfollow(String followId, String actorId) {
        return unfollow(new TopicFollowerCommand.UnfollowTopicCommand(followId, actorId));
    }
}

