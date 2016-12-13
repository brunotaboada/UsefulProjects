package com.db.services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.db.repository.SampleRepository;

@Service
@Repository
public class SampleService {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public SampleService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Autowired
	private SampleRepository repo;
	
	@PostConstruct
	@Transactional
	public void populate() {

	}

	@Transactional
	public List<Entity> getAll() {
		return repo.findAll();
	}

	/*@Transactional
	public Entity findById(BigDecimal queryId) {
		return repo.findById(queryId);
	}*/



	@Transactional
	public void delete(long id) {
		repo.delete(id);
	}

}
