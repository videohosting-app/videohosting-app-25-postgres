package com.videohost;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ViewInfoRepository extends MongoRepository<ViewInfo, String> {
}