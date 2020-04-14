package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Condominio;

public class CondominioService {
	
	public List<Condominio> findAll(){		
		List<Condominio> list = new ArrayList<>();
		list.add(new Condominio(1, "Bloco 193"));
		list.add(new Condominio(2, "Colinas de Pituaçú"));
		list.add(new Condominio(3, "Colina do Mar"));
		return list;
	}

}
