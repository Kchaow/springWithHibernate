package dataModelHibernate;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.script.ScriptException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.google.common.io.Resources;

import dataModelHibernate.dao.SingerDao;
import dataModelHibernate.entities.Singer;
import jakarta.annotation.PostConstruct;

@Testcontainers
@SpringJUnitConfig(classes = {Application.class})
public class HibernateDemoTest 
{
	private static final Logger logger = LoggerFactory.getLogger(HibernateDemoTest.class);
	
	@Container
	static PostgreSQLContainer<?> postgreSql = new PostgreSQLContainer<>("postgres:16.1");
	
	@DynamicPropertySource
	static void setUp(DynamicPropertyRegistry registry) 
	{
		registry.add("jdbc.driverClassName", postgreSql::getDriverClassName);
		registry.add("jdbc.url", postgreSql::getJdbcUrl);
		registry.add("jdbc.username", postgreSql::getUsername);
		registry.add("jdbc.password", postgreSql::getPassword);
	 }
	
	@Autowired
	SingerDao singerDao;
	
	@Test
	public void findAllSingerTest()
	{
		List<Singer> singers = singerDao.findAll();
		assertNotNull(singers);
		singerDao.findAll().forEach(s -> logger.info(s.toString()));
	}
	
	@Configuration
	public static class TestContainersConfig
	{
		@PostConstruct
		public void initialize() throws ScriptException, IOException
		{
			final String script = Resources.toString(
					Resources.getResource("init.sql"), 
					StandardCharsets.UTF_8);
			postgreSql.start();
			ScriptUtils.executeDatabaseScript(
					new JdbcDatabaseDelegate(postgreSql, ""), "init.sql", script);
		}
	}
}
