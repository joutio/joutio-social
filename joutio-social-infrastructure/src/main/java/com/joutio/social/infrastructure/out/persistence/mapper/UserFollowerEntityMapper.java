package com.joutio.social.infrastructure.out.persistence.mapper;

import com.joutio.social.domain.model.UserFollower;
import com.joutio.social.infrastructure.out.persistence.entity.UserFollowerEntity;

public final class UserFollowerEntityMapper {
    private UserFollowerEntityMapper() {
    }

    public static UserFollower toDomain(UserFollowerEntity entity) {
        return new UserFollower(
                entity.getFollowId(),
                entity.getFollowerId(),
                entity.getFollowedId(),
                entity.getFollowedAt()
        );
    }

    public static UserFollowerEntity toEntity(UserFollower userFollower) {
        UserFollowerEntity entity = new UserFollowerEntity();
        entity.setFollowId(userFollower.followId());
        entity.setFollowerId(userFollower.followerId());
        entity.setFollowedId(userFollower.followedId());
        entity.setFollowedAt(userFollower.followedAt());
        return entity;
    }
}
