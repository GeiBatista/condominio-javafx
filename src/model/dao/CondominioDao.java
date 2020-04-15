package model.dao;

import java.util.List;

import model.entities.Condominio;

public interface CondominioDao {

	void insert(Condominio obj);
	void update(Condominio obj);
	void deleteById(Integer id);
	Condominio findById(Integer id);
	List<Condominio> findAll();
}
