package dataModelHibernate;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"dataModelHibernate.entities", "dataModelHibernate.dao"})
@EnableTransactionManagement
public class HibernateTestConfig 
{
	private static final Logger logger = LoggerFactory.getLogger(HibernateTestConfig.class);
	
	@Bean
	public DataSource dataSource()
	{
		try
		{
			EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
			return dbBuilder
					.setType(EmbeddedDatabaseType.H2)
					.setName("testdb")
					.build();
		}
		catch (Exception e)
		{
			logger.error("Embedded DataSource bean cannot be created!", e);
			return null;
		}
	}
	
	@Bean
	public Properties hibernateProperties()
	{
		Properties hibernateProp = new Properties();
		hibernateProp.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
		hibernateProp.put(Environment.HBM2DDL_AUTO, "create-drop");
		hibernateProp.put(Environment.FORMAT_SQL, true);
		hibernateProp.put(Environment.USE_SQL_COMMENTS, true);
		hibernateProp.put(Environment.SHOW_SQL, true);
		hibernateProp.put(Environment.MAX_FETCH_DEPTH, 3);
		hibernateProp.put(Environment.STATEMENT_BATCH_SIZE, 10);
		hibernateProp.put(Environment.STATEMENT_FETCH_SIZE, 50);
		return hibernateProp;
	}
	
	@Bean
	public SessionFactory sessionFactory()
	{
		return new LocalSessionFactoryBuilder(dataSource())
				.scanPackages("dataModelHibernate.entities")
				.addProperties(hibernateProperties())
				.buildSessionFactory();			
	}
	
	@Bean
	public PlatformTransactionManager transactionManager()
	{
		return new HibernateTransactionManager(sessionFactory());
	}
}
