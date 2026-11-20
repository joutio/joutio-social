package com.joutio.social.application.handler.query;

import com.joutio.social.domain.model.UserBlock;
import com.joutio.social.domain.port.out.UserBlockRepositoryPort;
import com.joutio.social.domain.query.UserBlockQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserBlockQueryHandler {

    private final UserBlockRepositoryPort repository;

    public UserBlockQueryHandler(UserBlockRepositoryPort repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<UserBlock> handle(UserBlockQuery.GetBlockedUsersQuery query) {
        return repository.findBlockedBy(query.userId());
    }

    @QueryHandler
    public List<String> handle(UserBlockQuery.GetBlockRelationsQuery query) {
        return repository.findRelations(query.userId());
    }
}
