package com.joutio.social.application.handler.event;

import com.joutio.identity.domain.event.UserEvent;
import com.joutio.social.domain.command.UserFollowerCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ReferralFollowHandlerTest {

    @Test
    void referredPlayer_followsTheSponsor() {
        CommandGateway gateway = mock(CommandGateway.class);
        new ReferralFollowHandler(gateway).on(new UserEvent.UserReferredEvent("newcomer", "sponsor", Instant.now()));
        verify(gateway).sendAndWait(new UserFollowerCommand.FollowUserCommand("newcomer:sponsor", "newcomer", "sponsor"));
    }
}
