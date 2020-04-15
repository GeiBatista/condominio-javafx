package model.services;

import java.util.ArrayList;
import java.util.List;

import model.dao.CondominioDao;
import model.dao.DaoFactory;
import model.entities.Condominio;

public class CondominioService {
	
	private CondominioDao dao = DaoFactory.createCondominioDao();
	
	public List<Condominio> findAll(){		
		return dao.findAll();
	}
}
