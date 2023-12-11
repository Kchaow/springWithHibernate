package dataModelHibernate.dao;

import java.util.List;
import java.util.Set;

import dataModelHibernate.entities.Singer;

public interface SingerDao 
{
	List<Singer> findAll();
	List<Singer> findAllWithAlbum();
	Singer findAllDetails(String firstName, String lastName);
	Singer findById(Long id);
	Singer save(Singer singer);
	void delete(Singer singer);
	String findFirstNameById(Long id);
}
