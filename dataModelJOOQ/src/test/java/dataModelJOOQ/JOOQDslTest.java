package dataModelJOOQ;

import dataModelJOOQ.generated.tables.records.SingerRecord;
import static dataModelJOOQ.generated.tables.Singer.SINGER;
import org.jooq.DSLContext;
import org.jooq.Log;
import org.jooq.Result;
import org.jooq.Log.Level;
import org.jooq.Record;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql({ "classpath:testcontainers/create-schema.sql"})
@SpringJUnitConfig(classes = {BasicDataSourceCfg.class, JOOQConfig.class})
public class JOOQDslTest 
{
	@Container
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.1");
	
	@DynamicPropertySource
	static void setUp(DynamicPropertyRegistry registry) 
	{
		registry.add("jdbc.driverClassName", postgres::getDriverClassName);
		registry.add("jdbc.url", postgres::getJdbcUrl);
		registry.add("jdbc.username", postgres::getUsername);
		registry.add("jdbc.password", postgres::getPassword);
	 }
	
	@Autowired
	DSLContext dslContext;
	
	@Test
	@DisplayName("should return all singers")
	void findAll()
	{
		Result<SingerRecord> singers = dslContext
				.selectFrom(SINGER)
				.fetch();
		assertEquals(1, singers.size());
	}
	
	@Test
	@DisplayName("should return singer by id")
	void testFindById()
	{
		SingerRecord singerRecord = dslContext
				.selectFrom(SINGER)
				.where(SINGER.ID.eq(10))
				.fetchOne();
		assertNotNull(singerRecord);
		assertEquals("Michael", singerRecord.getFirstName());
	}
}
