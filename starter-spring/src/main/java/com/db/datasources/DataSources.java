package com.db.datasources;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSources {

	/*@Bean(destroyMethod = "close")
	public DataSource getDataSource() {
		return new JndiDataSourceLookup().getDataSource("java:comp/env/entitlementsDatasource");
	}*/
	
	/*
		@Bean
		public DataSource dataSource() {
			// no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
			EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
			EmbeddedDatabase db = builder
				.setType(EmbeddedDatabaseType.HSQL) //.H2 or .DERBY
				//.addScript("db/sql/create-db.sql")
				//.addScript("db/sql/insert-data.sql")
				.build();
			return db;
		}
	*/
	
	@Bean
	public DataSource dataSource() {
	    return new HikariDataSource(hikariConfig());
	}

	private HikariConfig hikariConfig() {
	    HikariConfig config = new HikariConfig();
	    config.setDriverClassName("org.hsqldb.jdbcDriver");
	    config.setJdbcUrl("jdbc:hsqldb:file:"+System.getProperty("user.home")+"/db/data");
	    config.setUsername("sa");
	    config.setPassword("");
	    return config;
	}

	/*@Bean(destroyMethod = "close")
	DataSource dataSource(Environment env) {
		OracleDataSource dataSource = null;
		try {
			dataSource = new OracleDataSource();
			dataSource.setUser(env.getRequiredProperty("db.username"));
			dataSource.setPassword(env.getRequiredProperty("db.password"));
			dataSource.setURL(env.getRequiredProperty("db.url"));
			// dataSource.setFastConnectionFailoverEnabled(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataSource;
	}*/

}