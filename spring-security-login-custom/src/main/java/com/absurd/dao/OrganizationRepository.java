package com.absurd.dao;

import com.absurd.model.Organization;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findByName(String name);

}
