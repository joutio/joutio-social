package com.joutio.social.domain.port.in;

import com.joutio.microservice.core.infrastructure.in.api.request.SearchRequest;
import com.joutio.microservice.core.infrastructure.in.api.response.SearchResponse;
import com.joutio.social.domain.model.UserFollower;
import com.joutio.social.domain.query.UserFollowerQuery;

import java.util.concurrent.CompletableFuture;

public interface SearchUserFollowerUseCase {

    CompletableFuture<SearchResponse<UserFollower>> search(UserFollowerQuery.SearchUserFollowerQuery query);

    default CompletableFuture<SearchResponse<UserFollower>> search(SearchRequest request) {
        return search(new UserFollowerQuery.SearchUserFollowerQuery(request));
    }
}
