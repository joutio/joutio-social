package com.joutio.social.application.service;

import com.joutio.social.domain.model.UserFollowCounts;
import com.joutio.social.domain.model.UserFollowCountsUpdate;
import com.joutio.social.domain.port.out.LiveVersionPort;
import com.joutio.social.domain.port.out.UserFollowerRepositoryPort;
import com.joutio.social.domain.query.UserFollowerQuery.WatchUserFollowCountsQuery;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FollowCountsLiveViewTest {

    private final UserFollowerRepositoryPort follows = mock(UserFollowerRepositoryPort.class);
    private final LiveVersionPort versions = mock(LiveVersionPort.class);
    private final QueryUpdateEmitter emitter = mock(QueryUpdateEmitter.class);
    private final FollowCountsLiveView view = new FollowCountsLiveView(follows, versions, emitter);

    @Test
    void eachPlayerOfAFollowIsEmittedOnceWithItsNextVersion() {
        when(versions.increment("a")).thenReturn(5L);
        when(versions.increment("b")).thenReturn(1L);
        when(follows.countFollowing("a")).thenReturn(1L);
        when(follows.countFollowers("b")).thenReturn(1L);

        view.changed(List.of("a", "b", "a"));

        verify(emitter).emit(eq(WatchUserFollowCountsQuery.class), any(),
                eq(new UserFollowCountsUpdate("a", 5, new UserFollowCounts(1, 0))));
        verify(emitter).emit(eq(WatchUserFollowCountsQuery.class), any(),
                eq(new UserFollowCountsUpdate("b", 1, new UserFollowCounts(0, 1))));
        verify(versions, times(1)).increment("a");
    }
}
