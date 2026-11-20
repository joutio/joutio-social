package com.joutio.social.infrastructure.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "user_block", indexes = {
        @Index(name = "idx_user_block_blocker", columnList = "blocker_id"),
        @Index(name = "idx_user_block_blocked", columnList = "blocked_id")
})
public class UserBlockEntity {

    @Id
    @Column(name = "block_id", nullable = false)
    private String blockId;

    @Column(name = "blocker_id", nullable = false)
    private String blockerId;

    @Column(name = "blocked_id", nullable = false)
    private String blockedId;

    @Column(name = "blocked_at", nullable = false)
    private Instant blockedAt;
}
