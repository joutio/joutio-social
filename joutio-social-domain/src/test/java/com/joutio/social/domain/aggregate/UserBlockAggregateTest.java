package com.joutio.social.domain.aggregate;

import com.joutio.axon.test.JoutioAxonMatchers;
import com.joutio.social.domain.command.UserBlockCommand;
import com.joutio.social.domain.event.UserBlockEvent;
import com.joutio.social.domain.exception.SocialExceptions;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class UserBlockAggregateTest {

    private static final String BLOCK_ID = "blocker-1:blocked-1";

    private final AggregateTestFixture<UserBlockAggregate> fixture =
            new AggregateTestFixture<>(UserBlockAggregate.class);

    @Test
    void block_appliesUserBlockedEvent() {
        fixture.givenNoPriorActivity()
                .when(new UserBlockCommand.BlockUserCommand(BLOCK_ID, "blocker-1", "blocked-1"))
                .expectEventsMatching(JoutioAxonMatchers.singlePayloadMatching(
                        UserBlockEvent.UserBlockedEvent.class,
                        e -> ((UserBlockEvent.UserBlockedEvent) e).blockedId().equals("blocked-1")));
    }

    @Test
    void block_self_isRejected() {
        fixture.givenNoPriorActivity()
                .when(new UserBlockCommand.BlockUserCommand("blocker-1:blocker-1", "blocker-1", "blocker-1"))
                .expectException(SocialExceptions.CannotBlockSelfProblem.class);
    }

    @Test
    void block_again_isIdempotent() {
        fixture.given(blocked())
                .when(new UserBlockCommand.BlockUserCommand(BLOCK_ID, "blocker-1", "blocked-1"))
                .expectNoEvents();
    }

    @Test
    void unblock_byBlocker_appliesUserUnblockedEvent() {
        fixture.given(blocked())
                .when(new UserBlockCommand.UnblockUserCommand(BLOCK_ID, "blocker-1"))
                .expectEventsMatching(JoutioAxonMatchers.singlePayloadMatching(
                        UserBlockEvent.UserUnblockedEvent.class,
                        e -> ((UserBlockEvent.UserUnblockedEvent) e).blockId().equals(BLOCK_ID)));
    }

    @Test
    void unblock_byBlockedPlayer_isRejected() {
        fixture.given(blocked())
                .when(new UserBlockCommand.UnblockUserCommand(BLOCK_ID, "blocked-1"))
                .expectException(SocialExceptions.NotBlockOwnerProblem.class);
    }

    @Test
    void block_afterUnblock_blocksAgain() {
        fixture.given(blocked(), new UserBlockEvent.UserUnblockedEvent(BLOCK_ID, "blocker-1", "blocked-1", Instant.now()))
                .when(new UserBlockCommand.BlockUserCommand(BLOCK_ID, "blocker-1", "blocked-1"))
                .expectEventsMatching(JoutioAxonMatchers.singlePayloadMatching(
                        UserBlockEvent.UserBlockedEvent.class, e -> true));
    }

    private static UserBlockEvent.UserBlockedEvent blocked() {
        return new UserBlockEvent.UserBlockedEvent(BLOCK_ID, "blocker-1", "blocked-1", Instant.now());
    }
}
