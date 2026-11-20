package com.joutio.social.application.handler.query;

import com.joutio.social.domain.model.PlayerBlock;
import com.joutio.social.domain.port.out.PlayerBlockRepositoryPort;
import com.joutio.social.domain.query.PlayerBlockQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerBlockQueryHandler {

    private final PlayerBlockRepositoryPort repository;

    public PlayerBlockQueryHandler(PlayerBlockRepositoryPort repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<PlayerBlock> handle(PlayerBlockQuery.GetBlockedPlayersQuery query) {
        return repository.findBlockedBy(query.userId());
    }

    @QueryHandler
    public List<String> handle(PlayerBlockQuery.GetBlockRelationsQuery query) {
        return repository.findRelations(query.userId());
    }
}
