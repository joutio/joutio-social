package com.joutio.social.application.handler.event;

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

    public AccountDeletionHandler(JdbcTemplate jdbcTemplate,
                                  AxonEventStoreErasureService eventStoreErasureService) {
        this.jdbcTemplate = jdbcTemplate;
        this.eventStoreErasureService = eventStoreErasureService;
    }

    @EventHandler
    @Transactional
    public void on(UserAccountDeletedEvent event) {
        String userId = event.userId();
        java.util.List<String> followIds = jdbcTemplate.queryForList("""
                SELECT follow_id FROM topic_follower WHERE user_id = ?
                UNION
                SELECT follow_id FROM user_follower WHERE follower_id = ? OR followed_id = ?
                UNION
                SELECT block_id FROM player_block WHERE blocker_id = ? OR blocked_id = ?
                """, String.class, userId, userId, userId, userId, userId);
        jdbcTemplate.update("DELETE FROM topic_follower WHERE user_id = ?", userId);
        jdbcTemplate.update(
                "DELETE FROM user_follower WHERE follower_id = ? OR followed_id = ?",
                userId, userId);
        jdbcTemplate.update(
                "DELETE FROM player_block WHERE blocker_id = ? OR blocked_id = ?",
                userId, userId);
        eventStoreErasureService.eraseAggregates(followIds);
    }
}
