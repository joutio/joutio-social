package com.joutio.social.infrastructure.out.persistence.repository;

import com.joutio.social.infrastructure.out.persistence.entity.PlayerBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerBlockJpaRepository extends JpaRepository<PlayerBlockEntity, String> {

    List<PlayerBlockEntity> findByBlockerIdOrderByBlockedAtDesc(String blockerId);

    @Query("""
            select case when b.blockerId = :userId then b.blockedId else b.blockerId end
            from PlayerBlockEntity b
            where b.blockerId = :userId or b.blockedId = :userId
            """)
    List<String> findRelations(@Param("userId") String userId);
}
