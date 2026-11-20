package com.joutio.social.infrastructure.out.persistence.repository;

import com.joutio.social.infrastructure.out.persistence.entity.UserBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBlockJpaRepository extends JpaRepository<UserBlockEntity, String> {

    List<UserBlockEntity> findByBlockerIdOrderByBlockedAtDesc(String blockerId);

    @Query("""
            select case when b.blockerId = :userId then b.blockedId else b.blockerId end
            from UserBlockEntity b
            where b.blockerId = :userId or b.blockedId = :userId
            """)
    List<String> findRelations(@Param("userId") String userId);
}
