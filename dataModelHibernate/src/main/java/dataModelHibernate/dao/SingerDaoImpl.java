package dataModelHibernate.dao;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dataModelHibernate.entities.Album;
import dataModelHibernate.entities.Instrument;
import dataModelHibernate.entities.Singer;


@Transactional
@Repository
public class SingerDaoImpl implements SingerDao
{
	private static final Logger logger = LoggerFactory.getLogger(SingerDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String ALL_SELECT = 
			  "SELECT DISTINCT s.first_name, s.last_name, a.title, a.RELEASE_DATE,\r\n"
			+ "i.ID\r\n"
			+ "from SINGER s\r\n"
			+ "inner join ALBUM a on s.id = a.singer_id\r\n"
			+ "inner join SINGER_INSTRUMENT si on s.ID = si.SINGER_ID\r\n"
			+ "inner join INSTRUMENT i on si.INSTRUMENT_ID = i.ID\r\n"
			+ "where s.FIRST_NAME = :firstName and s.LAST_NAME= :lastName";
			
	@Transactional(readOnly = true)
	@Override
	public String findFirstNameById(Long id)
	{
		return sessionFactory.getCurrentSession()
				.createQuery("select getFirstNameById(?1)", String.class)
				.setParameter(1, id)
				.getSingleResult()
				.toString();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Singer findAllDetails(String firstName, String lastName)
	{
		List<Object[]> results = sessionFactory.getCurrentSession()
				.createNativeQuery(ALL_SELECT, Object[].class)
				.setParameter("firstName", firstName)
				.setParameter("lastName", lastName)
				.list();
		Singer singer = new Singer();
		for (Object[] item : results)
		{
			if (singer.getFirstName() == null && singer.getLastName() == null)
			{
				singer.setFirstName((String)item[0]);
				singer.setFirstName((String)item[1]);
			}
			Album album = new Album();
			album.setTitle((String)item[2]);
//			album.setReleaseDate(((Date) item[3]).toLocalDate());
			singer.addAlbum(album);
			
			Instrument instrument = new Instrument();
			instrument.setInstrumentId(Long.parseLong(item[4] + ""));
			singer.getInstruments().add(instrument);
		}
		return singer;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Singer> findAll() {
		Query<Singer> query = sessionFactory.getCurrentSession()
				.createQuery("from Singer s", Singer.class);
		List<Singer> list = new ArrayList<>();
		for (Singer singer : query.list())
		{
			list.add(singer);
		}
		return list;
	}
	@Override
	@Transactional(readOnly = true)
	public List<Singer> findAllWithAlbum() {
		return sessionFactory.getCurrentSession()
				.createNamedQuery("Singer.findAllWithAlbum", Singer.class)
				.list();
	}
	@Override
	@Transactional(readOnly = true)
	public Singer findById(Long id) {
		return sessionFactory.getCurrentSession()
				.createNamedQuery("Singer.findById", Singer.class)
				.setParameter("id", id)
				.uniqueResult();
	}
	@Override
	@Transactional
	public Singer save(Singer singer) 
	{
		sessionFactory.getCurrentSession()
		.merge(singer);
		return singer;
	}
	@Override
	public void delete(Singer singer) {
		sessionFactory.getCurrentSession().remove(singer);
	}
}
