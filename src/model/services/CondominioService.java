package model.services;

import java.util.List;

import model.dao.CondominioDao;
import model.dao.DaoFactory;
import model.entities.Condominio;

public class CondominioService {
	
	private CondominioDao dao = DaoFactory.createCondominioDao();
	
	public List<Condominio> findAll(){		
		return dao.findAll();
	}
	
	public void saveOrUpdate(Condominio obj) {
		if(obj.getIdCondominio() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
}
