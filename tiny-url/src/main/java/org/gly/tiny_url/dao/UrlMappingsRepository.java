package org.gly.tiny_url.dao;

import org.gly.tiny_url.entity.UrlMappings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlMappingsRepository extends JpaRepository<UrlMappings, Integer> {
    UrlMappings findByTinyUrl(String hash);
}
