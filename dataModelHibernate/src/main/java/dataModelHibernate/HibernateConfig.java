package dataModelHibernate;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig 
{
	private static Logger logger = LoggerFactory.getLogger(HibernateConfig.class);
	@Autowired
	DataSource dataSource;
	
	@Bean
	public Properties hibernateProperties()
	{
		Properties hibernateProp = new Properties();
		hibernateProp.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
		hibernateProp.put(Environment.HBM2DDL_AUTO, "none");
		hibernateProp.put(Environment.FORMAT_SQL, false);
		hibernateProp.put(Environment.USE_SQL_COMMENTS, false);
		hibernateProp.put(Environment.SHOW_SQL, false);
		hibernateProp.put(Environment.MAX_FETCH_DEPTH, 3);
		hibernateProp.put(Environment.STATEMENT_BATCH_SIZE, 10);
		hibernateProp.put(Environment.STATEMENT_FETCH_SIZE, 50);
		//hibernateProp.put(Environment.JTA_PLATFORM, "org.springframework.orm.hibernate5.ConfigurableJtaPlatform");
		return hibernateProp;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan("dataModelHibernate.entities");
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager()
	{
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
}
