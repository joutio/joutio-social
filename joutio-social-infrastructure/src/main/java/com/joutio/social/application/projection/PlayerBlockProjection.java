package com.joutio.social.application.projection;

import com.joutio.social.domain.event.PlayerBlockEvent;
import com.joutio.social.domain.model.PlayerBlock;
import com.joutio.social.domain.port.out.PlayerBlockRepositoryPort;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Projection des blocages : une ligne par blocage actif, supprimée au déblocage. */
@Component
@ProcessingGroup("player-block-projection")
public class PlayerBlockProjection {

    private final PlayerBlockRepositoryPort repository;

    public PlayerBlockProjection(PlayerBlockRepositoryPort repository) {
        this.repository = repository;
    }

    @EventHandler
    @Transactional
    public void on(PlayerBlockEvent.PlayerBlockedEvent event) {
        repository.save(new PlayerBlock(event.blockId(), event.blockerId(), event.blockedId(), event.blockedAt()));
    }

    @EventHandler
    @Transactional
    public void on(PlayerBlockEvent.PlayerUnblockedEvent event) {
        repository.deleteById(event.blockId());
    }
}
