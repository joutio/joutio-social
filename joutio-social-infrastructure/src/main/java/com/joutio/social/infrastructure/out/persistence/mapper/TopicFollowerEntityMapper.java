package com.joutio.social.infrastructure.out.persistence.mapper;

import com.joutio.social.domain.model.TopicFollower;
import com.joutio.social.infrastructure.out.persistence.entity.TopicFollowerEntity;

public final class TopicFollowerEntityMapper {
    private TopicFollowerEntityMapper() {
    }

    public static TopicFollower toDomain(TopicFollowerEntity entity) {
        return new TopicFollower(
                entity.getFollowId(),
                entity.getTopicId(),
                entity.getUserId(),
                entity.getFollowedAt()
        );
    }

    public static TopicFollowerEntity toEntity(TopicFollower topicFollower) {
        TopicFollowerEntity entity = new TopicFollowerEntity();
        entity.setFollowId(topicFollower.followId());
        entity.setTopicId(topicFollower.topicId());
        entity.setUserId(topicFollower.userId());
        entity.setFollowedAt(topicFollower.followedAt());
        return entity;
    }
}

