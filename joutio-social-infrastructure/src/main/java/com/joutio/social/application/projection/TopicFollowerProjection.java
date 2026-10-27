package com.joutio.social.application.projection;

import com.joutio.social.domain.event.TopicFollowerEvent;
import com.joutio.social.domain.model.FollowerIds;
import com.joutio.social.domain.model.TopicFollower;
import com.joutio.social.domain.port.out.TopicFollowerRepositoryPort;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Projection du suivi des sujets. La clé est la <b>clé naturelle</b> ({@code userId:topicId}),
 * ce qui rend l'upsert/delete idempotent et tolérant aux événements en double (rejeu).
 */
@Component
@ProcessingGroup("topic-follower-projection")
public class TopicFollowerProjection {

    private final TopicFollowerRepositoryPort topicFollowerRepositoryPort;

    public TopicFollowerProjection(TopicFollowerRepositoryPort topicFollowerRepositoryPort) {
        this.topicFollowerRepositoryPort = topicFollowerRepositoryPort;
    }

    @EventHandler
    @Transactional
    public void on(TopicFollowerEvent.TopicFollowedEvent event) {
        topicFollowerRepositoryPort.save(
                new TopicFollower(
                        FollowerIds.topic(event.topicId(), event.userId()),
                        event.topicId(),
                        event.userId(),
                        event.followedAt()
                )
        );
    }

    @EventHandler
    @Transactional
    public void on(TopicFollowerEvent.TopicUnfollowedEvent event) {
        topicFollowerRepositoryPort.deleteById(
                FollowerIds.topic(event.topicId(), event.userId())
        );
    }
}
