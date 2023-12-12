package dataModelJOOQ;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.Configuration;
import org.jooq.meta.jaxb.Database;
import org.jooq.meta.jaxb.Generate;
import org.jooq.meta.jaxb.Generator;
import org.jooq.meta.jaxb.Jdbc;
import org.jooq.meta.jaxb.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateJOOQSources 
{
	//private static final Logger logger = LoggerFactory.getLogger(GenerateJOOQSources.class);
	
	public static void main(String... args) throws Exception 
	{
		Jdbc jdbc = new Jdbc()
				.withDriver("org.postgresql.Driver")
				.withUrl("jdbc:postgresql://localhost:5432/db")
				.withUser("user")
				.withPassword("1298");
		Database dataBase = new Database()
				.withName("org.jooq.meta.postgres.PostgresDatabase")
				.withInputSchema("public")
				.withIncludes(".*");
		Generate generate = new Generate()
				.withPojos(true)
				.withPojosToString(true)
				.withDaos(true);
		Target target = new Target()
				.withPackageName("dataModelJOOQ.generated")
				.withDirectory("./src/main/java/");
		Generator generator = new Generator()
				.withDatabase(dataBase)
				.withGenerate(generate)
				.withTarget(target);
		Configuration conf = new Configuration()
				.withJdbc(jdbc)
				.withGenerator(generator);
		GenerationTool.generate(conf);
	}

}
