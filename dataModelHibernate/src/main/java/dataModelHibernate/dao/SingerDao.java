package dataModelHibernate.dao;

import java.util.List;

import dataModelHibernate.entities.Singer;

public interface SingerDao 
{
	List<Singer> findAll();
	List<Singer> findAllWithAlbum();
	Singer findById(Long id);
	Singer save(Singer singer);
	void delete(Singer singer);
}
