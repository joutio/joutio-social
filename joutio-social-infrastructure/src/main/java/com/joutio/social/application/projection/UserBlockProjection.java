package com.joutio.social.application.projection;

import com.joutio.social.domain.event.UserBlockEvent;
import com.joutio.social.domain.model.UserBlock;
import com.joutio.social.domain.port.out.UserBlockRepositoryPort;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/** Projection des blocages : une ligne par blocage actif, supprimée au déblocage. */
@Component
@ProcessingGroup("user-block-projection")
public class UserBlockProjection {

    private final UserBlockRepositoryPort repository;

    public UserBlockProjection(UserBlockRepositoryPort repository) {
        this.repository = repository;
    }

    @EventHandler
    @Transactional
    public void on(UserBlockEvent.UserBlockedEvent event) {
        repository.save(new UserBlock(event.blockId(), event.blockerId(), event.blockedId(), event.blockedAt()));
    }

    @EventHandler
    @Transactional
    public void on(UserBlockEvent.UserUnblockedEvent event) {
        repository.deleteById(event.blockId());
    }
}
