package dataModelHibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dataModelHibernate.entities.Singer;


@Transactional
@Repository
public class SingerDaoImpl implements SingerDao
{
	private static final Logger logger = LoggerFactory.getLogger(SingerDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
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
