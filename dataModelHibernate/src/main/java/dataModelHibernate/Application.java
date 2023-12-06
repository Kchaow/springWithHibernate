package dataModelHibernate;

import org.springframework.context.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@ComponentScan("dataModelHibernate")
public class Application 
{
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	public static void main(String...args)
	{
		logger.info("Context loading");
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
	}
}
