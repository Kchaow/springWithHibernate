package dataModelHibernate;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dataModelHibernate.dao.SingerDao;
import dataModelHibernate.entities.Album;
import dataModelHibernate.entities.Singer;

@SpringJUnitConfig(classes = {HibernateTestConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class H2HibernateTest 
{
	private static final Logger logger = LoggerFactory.getLogger(H2HibernateTest.class);
	
	@Autowired
	SingerDao singerDao;
	
	@Test
	@Order(1)
	@DisplayName("should insert a singer with albums")
	public void testInsert()
	{
		Singer singer = new Singer();
		singer.setFirstName("BB");
		singer.setLastName("King");
		singer.setBirthDate(LocalDate.of(1940, 8, 16));
		
		Album album = new Album();
		album.setTitle("My Kind of Blues");
		album.setReleaseDate(LocalDate.of(1961, 7, 18));
		singer.addAlbum(album);
		
		album = new Album();
		album.setTitle("A Heart Full of Blues");
		album.setReleaseDate(LocalDate.of(1962, 3, 20));
		singer.addAlbum(album);
		
		Singer created = singerDao.save(singer);
		assertNotNull(created.getId());
	}
	
	@Test
	@Order(2)
	@DisplayName("should return all singers")
	public void testFindAll()
	{
		List<Singer> singers = singerDao.findAll();
		assertEquals(1, singers.size());
		singers.forEach(singer -> logger.info(singer.toString()));
	}
	
	@Test
	@Order(3)
	@DisplayName("should update a singer")
	public void testUpdate()
	{
		Singer singer = singerDao.findAll().get(0);
		
		assertNotNull(singer);
		singer.setFirstName("Riley B. ");
		int version = singer.getVersion();
		Singer bb = singerDao.save(singer);
		
//		assertEquals(version + 1, bb.getVersion());
	}
	
	@Test
	@Order(4)
	@DisplayName("should delete a singer")
	public void testDelete()
	{
		Singer singer = singerDao.findAll().get(0);
		assertNotNull(singer);
		
		singerDao.delete(singer);
		
		assertEquals(0, singerDao.findAll().size());
	}
	
}
