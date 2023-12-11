package dataModelHibernate;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.script.ScriptException;

import org.hibernate.LazyInitializationException;
import org.hibernate.cfg.Environment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.google.common.io.Resources;

import dataModelHibernate.dao.SingerDao;
import dataModelHibernate.entities.Album;
import dataModelHibernate.entities.Instrument;
import dataModelHibernate.entities.Singer;
import jakarta.annotation.PostConstruct;

@Testcontainers
@SpringJUnitConfig(classes = {Application.class})
@Sql(statements = "CREATE FUNCTION getFirstNameById(in_id bigint) RETURNS VARCHAR(250) AS $$ "
		+ "SELECT first_name FROM SINGER WHERE id = in_id "
		+ "$$ LANGUAGE SQL;")
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
		List<Singer> singersLazy = singerDao.findAll();
		assertNotNull(singersLazy);
		assertThrows(LazyInitializationException.class, () -> singersLazy.get(0).getAlbums().size());
		
		Singer singer = singerDao.findById(10L);
		assertNotNull(singer);
		
		List<Singer> singersEager = singerDao.findAllWithAlbum();
		assertDoesNotThrow(() -> singersEager.get(0).getAlbums().size());
		
		Singer newSinger = new Singer();
		newSinger.setFirstName("Till");
		newSinger.setLastName("Lindemann");
		newSinger.setBirthDate(LocalDate.of(1963, 1, 4));
		newSinger.setVersion(0);
		
		Album newAlbum = new Album();
		newAlbum.setTitle("Skills in Pills");
		newAlbum.setReleaseDate(LocalDate.of(2015, 6, 23));
		
		newSinger.addAlbum(newAlbum);
		
		newSinger = singerDao.save(newSinger);
		assertNotNull(newSinger.getId());
		
		Album album = newSinger.getAlbums().stream().filter(
				(x) -> x.getTitle().equals("Skills in Pills")
				).findFirst().orElse(null);
		assertNotNull(album);
		
		newSinger.removeAlbum(album);

		newSinger = singerDao.save(newSinger);
		assertEquals(0, newSinger.getAlbums().size());
		
		Long id = newSinger.getId();
		newSinger = singerDao.findById(id);
		int ver = newSinger.getVersion(); //транзакции это пздц
		logger.info(ver + "");
		singerDao.delete(newSinger);
		newSinger = singerDao.findById(id);
		assertNull(newSinger);
		
		singer = singerDao.findAllDetails("Michael", "Jackson");
		assertNotNull(singer);
		
		assertEquals("Michael", singerDao.findFirstNameById((long)10));
	}
	
	@Configuration
	public static class TestContainersConfig
	{
		@Autowired
		Properties hibernateProperties;
		
		@PostConstruct
		public void initialize() throws ScriptException, IOException
		{
			final String script = Resources.toString(
					Resources.getResource("init.sql"), 
					StandardCharsets.UTF_8);
			postgreSql.start();
			ScriptUtils.executeDatabaseScript(
					new JdbcDatabaseDelegate(postgreSql, ""), "init.sql", script);
			hibernateProperties.put(Environment.FORMAT_SQL, true);
			hibernateProperties.put(Environment.USE_SQL_COMMENTS, true);
			hibernateProperties.put(Environment.SHOW_SQL, true);
		}
	}
}
