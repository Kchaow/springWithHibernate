package dataModelHibernate.dao;

import java.util.List;

import org.hibernate.SessionFactory;
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
	public List<Singer> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Singer s").list();
	}
	@Override
	public List<Singer> findAllWithAlbum() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Singer findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Singer save(Singer singer) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(Singer singer) {
		// TODO Auto-generated method stub
		
	}
}
