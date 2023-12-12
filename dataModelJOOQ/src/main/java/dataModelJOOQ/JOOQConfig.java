package dataModelJOOQ;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.jooq.DSLContext;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JOOQConfig 
{
	@Autowired
	DataSource dataSource;
	
	@Bean
	DSLContext dslContext() throws SQLException
	{
			Settings settings  = new Settings()
					.withRenderNameCase(RenderNameCase.LOWER)
					.withRenderQuotedNames(RenderQuotedNames.NEVER)
					.withRenderSchema(false)
					.withRenderGroupConcatMaxLenSessionVariable(false);
			return DSL.using(dataSource.getConnection(), settings);
	}
}
