package com.joutio.social.infrastructure.out.persistence.adapter;

import com.joutio.social.domain.model.PlayerBlock;
import com.joutio.social.domain.port.out.PlayerBlockRepositoryPort;
import com.joutio.social.infrastructure.out.persistence.entity.PlayerBlockEntity;
import com.joutio.social.infrastructure.out.persistence.repository.PlayerBlockJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerBlockRepositoryAdapter implements PlayerBlockRepositoryPort {

    private final PlayerBlockJpaRepository repository;

    public PlayerBlockRepositoryAdapter(PlayerBlockJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(PlayerBlock block) {
        PlayerBlockEntity entity = new PlayerBlockEntity();
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
    public List<PlayerBlock> findBlockedBy(String blockerId) {
        return repository.findByBlockerIdOrderByBlockedAtDesc(blockerId).stream()
                .map(entity -> new PlayerBlock(
                        entity.getBlockId(), entity.getBlockerId(), entity.getBlockedId(), entity.getBlockedAt()))
                .toList();
    }

    @Override
    public List<String> findRelations(String userId) {
        return repository.findRelations(userId).stream().distinct().toList();
    }
}
