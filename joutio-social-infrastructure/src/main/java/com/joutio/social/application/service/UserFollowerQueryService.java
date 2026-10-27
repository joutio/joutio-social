package com.joutio.social.application.service;

import com.joutio.microservice.core.infrastructure.axon.QueryResponseTypes;
import com.joutio.microservice.core.infrastructure.in.api.response.SearchResponse;
import com.joutio.social.domain.model.UserFollower;
import com.joutio.social.domain.port.in.GetUserFollowerUseCase;
import com.joutio.social.domain.port.in.SearchUserFollowerUseCase;
import com.joutio.social.domain.query.UserFollowerQuery;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class UserFollowerQueryService implements SearchUserFollowerUseCase, GetUserFollowerUseCase {

    private final QueryGateway queryGateway;

    public UserFollowerQueryService(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @Override
    public CompletableFuture<SearchResponse<UserFollower>> search(UserFollowerQuery.SearchUserFollowerQuery query) {
        return queryGateway.query(query, QueryResponseTypes.searchResponseOf(UserFollower.class));
    }

    @Override
    public CompletableFuture<UserFollower> getById(UserFollowerQuery.GetUserFollowerByIdQuery query) {
        return queryGateway.query(query, QueryResponseTypes.instanceOf(UserFollower.class));
    }
}
