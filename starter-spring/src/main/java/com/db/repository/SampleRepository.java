package com.db.repository;

import javax.persistence.Entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SampleRepository extends JpaRepository<Entity, Long> {
	//public Entity findByName();
}