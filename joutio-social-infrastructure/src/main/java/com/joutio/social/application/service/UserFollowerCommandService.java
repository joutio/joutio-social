package com.joutio.social.application.service;

import com.joutio.social.domain.command.UserFollowerCommand;
import com.joutio.social.domain.port.in.FollowUserUseCase;
import com.joutio.social.domain.port.in.UnfollowUserUseCase;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserFollowerCommandService implements FollowUserUseCase, UnfollowUserUseCase {

    private final CommandGateway commandGateway;

    public UserFollowerCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> follow(UserFollowerCommand.FollowUserCommand command) {
        return commandGateway.send(command);
    }

    @Override
    public CompletableFuture<String> unfollow(UserFollowerCommand.UnfollowUserCommand command) {
        return commandGateway.send(command);
    }
}
