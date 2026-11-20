package com.joutio.social.application.handler.event;

import com.joutio.social.domain.command.UserFollowerCommand;
import com.joutio.social.domain.event.PlayerBlockEvent;
import com.joutio.social.domain.model.FollowerIds;
import com.joutio.social.domain.port.out.UserFollowerRepositoryPort;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/** Un blocage retire les abonnements entre les deux joueurs, dans les deux sens. */
@Component
@ProcessingGroup("player-block-follows")
public class PlayerBlockFollowHandler {

    private final UserFollowerRepositoryPort follows;
    private final CommandGateway commandGateway;

    public PlayerBlockFollowHandler(UserFollowerRepositoryPort follows, CommandGateway commandGateway) {
        this.follows = follows;
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(PlayerBlockEvent.PlayerBlockedEvent event) {
        unfollow(event.blockerId(), event.blockedId());
        unfollow(event.blockedId(), event.blockerId());
    }

    private void unfollow(String followerId, String followedId) {
        if (follows.exists(followerId, followedId)) {
            commandGateway.send(new UserFollowerCommand.UnfollowUserCommand(
                    FollowerIds.user(followerId, followedId), followerId));
        }
    }
}
