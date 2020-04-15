package model.dao;

import db.DB;
import model.dao.impl.CondominioDaoJDBC;

public class DaoFactory {

	public static CondominioDao createCondominioDao() {
		return new CondominioDaoJDBC(DB.getConnection());
	}
}
