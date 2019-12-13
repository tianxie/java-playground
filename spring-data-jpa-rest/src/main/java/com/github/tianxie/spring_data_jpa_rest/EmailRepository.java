package com.github.tianxie.spring_data_jpa_rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "emails", path = "emails")
public interface EmailRepository extends PagingAndSortingRepository<Email, Long> {
}
