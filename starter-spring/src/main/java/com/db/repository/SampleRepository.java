package com.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.db.entities.Test;

/**
 * @author bruno.taboada
 *
 */
@Transactional
public interface SampleRepository extends JpaRepository<Test, Long> {
    // public Entity findByName();
}