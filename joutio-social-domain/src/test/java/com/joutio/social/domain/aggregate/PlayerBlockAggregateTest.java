package com.joutio.social.domain.aggregate;

import com.joutio.axon.test.JoutioAxonMatchers;
import com.joutio.social.domain.command.PlayerBlockCommand;
import com.joutio.social.domain.event.PlayerBlockEvent;
import com.joutio.social.domain.exception.SocialExceptions;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class PlayerBlockAggregateTest {

    private static final String BLOCK_ID = "blocker-1:blocked-1";

    private final AggregateTestFixture<PlayerBlockAggregate> fixture =
            new AggregateTestFixture<>(PlayerBlockAggregate.class);

    @Test
    void block_appliesPlayerBlockedEvent() {
        fixture.givenNoPriorActivity()
                .when(new PlayerBlockCommand.BlockPlayerCommand(BLOCK_ID, "blocker-1", "blocked-1"))
                .expectEventsMatching(JoutioAxonMatchers.singlePayloadMatching(
                        PlayerBlockEvent.PlayerBlockedEvent.class,
                        e -> ((PlayerBlockEvent.PlayerBlockedEvent) e).blockedId().equals("blocked-1")));
    }

    @Test
    void block_self_isRejected() {
        fixture.givenNoPriorActivity()
                .when(new PlayerBlockCommand.BlockPlayerCommand("blocker-1:blocker-1", "blocker-1", "blocker-1"))
                .expectException(SocialExceptions.CannotBlockSelfProblem.class);
    }

    @Test
    void block_again_isIdempotent() {
        fixture.given(blocked())
                .when(new PlayerBlockCommand.BlockPlayerCommand(BLOCK_ID, "blocker-1", "blocked-1"))
                .expectNoEvents();
    }

    @Test
    void unblock_byBlocker_appliesPlayerUnblockedEvent() {
        fixture.given(blocked())
                .when(new PlayerBlockCommand.UnblockPlayerCommand(BLOCK_ID, "blocker-1"))
                .expectEventsMatching(JoutioAxonMatchers.singlePayloadMatching(
                        PlayerBlockEvent.PlayerUnblockedEvent.class,
                        e -> ((PlayerBlockEvent.PlayerUnblockedEvent) e).blockId().equals(BLOCK_ID)));
    }

    @Test
    void unblock_byBlockedPlayer_isRejected() {
        fixture.given(blocked())
                .when(new PlayerBlockCommand.UnblockPlayerCommand(BLOCK_ID, "blocked-1"))
                .expectException(SocialExceptions.NotBlockOwnerProblem.class);
    }

    @Test
    void block_afterUnblock_blocksAgain() {
        fixture.given(blocked(), new PlayerBlockEvent.PlayerUnblockedEvent(BLOCK_ID, "blocker-1", "blocked-1", Instant.now()))
                .when(new PlayerBlockCommand.BlockPlayerCommand(BLOCK_ID, "blocker-1", "blocked-1"))
                .expectEventsMatching(JoutioAxonMatchers.singlePayloadMatching(
                        PlayerBlockEvent.PlayerBlockedEvent.class, e -> true));
    }

    private static PlayerBlockEvent.PlayerBlockedEvent blocked() {
        return new PlayerBlockEvent.PlayerBlockedEvent(BLOCK_ID, "blocker-1", "blocked-1", Instant.now());
    }
}
