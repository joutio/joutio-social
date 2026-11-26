package com.joutio.social.application.handler.event;

import com.joutio.social.application.service.FollowCountsLiveView;
import java.util.List;
import com.joutio.axon.autoconfigure.erasure.AxonEventStoreErasureService;
import com.joutio.identity.domain.event.UserEvent.UserAccountDeletedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Supprime les relations de suivi et les blocages d'un compte supprimé. */
@Component
@ProcessingGroup("account-deletion-social")
public class AccountDeletionHandler {

    private final JdbcTemplate jdbcTemplate;
    private final AxonEventStoreErasureService eventStoreErasureService;
    private final FollowCountsLiveView followCounts;

    public AccountDeletionHandler(JdbcTemplate jdbcTemplate,
                                  AxonEventStoreErasureService eventStoreErasureService,
                                  FollowCountsLiveView followCounts) {
        this.jdbcTemplate = jdbcTemplate;
        this.eventStoreErasureService = eventStoreErasureService;
        this.followCounts = followCounts;
    }

    @EventHandler
    @Transactional
    public void on(UserAccountDeletedEvent event) {
        String userId = event.userId();
        List<String> followIds = jdbcTemplate.queryForList("""
                SELECT follow_id FROM topic_follower WHERE user_id = ?
                UNION
                SELECT follow_id FROM user_follower WHERE follower_id = ? OR followed_id = ?
                UNION
                SELECT block_id FROM user_block WHERE blocker_id = ? OR blocked_id = ?
                """, String.class, userId, userId, userId, userId, userId);
        List<String> counterparts = jdbcTemplate.queryForList("""
                SELECT followed_id FROM user_follower WHERE follower_id = ?
                UNION
                SELECT follower_id FROM user_follower WHERE followed_id = ?
                """, String.class, userId, userId);
        jdbcTemplate.update("DELETE FROM topic_follower WHERE user_id = ?", userId);
        jdbcTemplate.update(
                "DELETE FROM user_follower WHERE follower_id = ? OR followed_id = ?",
                userId, userId);
        jdbcTemplate.update(
                "DELETE FROM user_block WHERE blocker_id = ? OR blocked_id = ?",
                userId, userId);
        jdbcTemplate.update("DELETE FROM live_version WHERE user_id = ?", userId);
        eventStoreErasureService.eraseAggregates(followIds);
        followCounts.changed(counterparts);
        followCounts.deleted(userId);
    }
}
