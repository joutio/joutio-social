package com.joutio.social.infrastructure.out.persistence.adapter;

import com.joutio.social.domain.model.UserBlock;
import com.joutio.social.domain.port.out.UserBlockRepositoryPort;
import com.joutio.social.infrastructure.out.persistence.entity.UserBlockEntity;
import com.joutio.social.infrastructure.out.persistence.repository.UserBlockJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserBlockRepositoryAdapter implements UserBlockRepositoryPort {

    private final UserBlockJpaRepository repository;

    public UserBlockRepositoryAdapter(UserBlockJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(UserBlock block) {
        UserBlockEntity entity = new UserBlockEntity();
        entity.setBlockId(block.blockId());
        entity.setBlockerId(block.blockerId());
        entity.setBlockedId(block.blockedId());
        entity.setBlockedAt(block.blockedAt());
        repository.save(entity);
    }

    @Override
    public void deleteById(String blockId) {
        repository.deleteById(blockId);
    }

    @Override
    public List<UserBlock> findBlockedBy(String blockerId) {
        return repository.findByBlockerIdOrderByBlockedAtDesc(blockerId).stream()
                .map(entity -> new UserBlock(
                        entity.getBlockId(), entity.getBlockerId(), entity.getBlockedId(), entity.getBlockedAt()))
                .toList();
    }

    @Override
    public List<String> findRelations(String userId) {
        return repository.findRelations(userId).stream().distinct().toList();
    }
}
