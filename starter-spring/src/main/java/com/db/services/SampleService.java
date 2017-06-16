package com.db.services;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.db.entities.Test;
import com.db.repository.SampleRepository;

/**
 * @author bruno.taboada
 *
 */
@Service
@Repository
public class SampleService {

    @SuppressWarnings("unused")
    private JdbcTemplate jdbcTemplate;

    /**
     * @param dataSource
     */
    @Autowired
    public SampleService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    private SampleRepository repo;

    /**
     * 
     */
    @PostConstruct
    @Transactional
    public void populate() {

    }

    @Transactional
    public List<Test> getAll() {
        return repo.findAll();
    }

    /*
     * @Transactional public Entity findById(BigDecimal queryId) { return repo.findById(queryId); }
     */

    @Transactional
    public void add(String id, String name, String age) {
        Test test = new Test(BigDecimal.valueOf(Long.valueOf(id)), name, age);
        repo.save(test);
    }

    @Transactional
    public void delete(long id) {
        repo.delete(id);
    }

}
