package com.joutio.social.domain.port.in;

import com.joutio.microservice.core.infrastructure.in.api.request.SearchRequest;
import com.joutio.microservice.core.infrastructure.in.api.response.SearchResponse;
import com.joutio.social.domain.model.TopicFollower;
import com.joutio.social.domain.query.TopicFollowerQuery;

import java.util.concurrent.CompletableFuture;

public interface SearchTopicFollowerUseCase {

    CompletableFuture<SearchResponse<TopicFollower>> search(TopicFollowerQuery.SearchTopicFollowerQuery query);

    default CompletableFuture<SearchResponse<TopicFollower>> search(SearchRequest request) {
        return search(new TopicFollowerQuery.SearchTopicFollowerQuery(request));
    }
}
