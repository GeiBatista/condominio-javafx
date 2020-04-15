package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.CondominioDao;
import model.entities.Condominio;

public class CondominioDaoJDBC implements CondominioDao {

	private Connection conn;

	public CondominioDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Condominio obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
						"INSERT INTO tbl_condominio"
						+ "("
						+ "cond_razao_social, "
						+ "cond_cnpj, "
						+ "cond_inscricao_estadual, "
						+ "cond_inscricao_municipal, "
						+ "cond_email, "
						+ "cond_telefone, "
						+ "cond_celular, "
						+ "cond_endereco, "
						+ "cond_numero_endereco, "
						+ "cond_complemento_endereco, "
						+ "cond_bairro, "
						+ "cond_cidade, "
						+ "cond_uf, "
						+ "cond_cep) "
						+ "VALUES "
						+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
						Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getRazaoSocial());
			st.setString(2, obj.getCnpj());
			st.setString(3, obj.getInscricaoEstadual());
			st.setString(4, obj.getInscricaoMunicipal());
			st.setString(5, obj.getEmail());
			st.setString(6, obj.getTelefone());
			st.setString(7, obj.getCelular());
			st.setString(8, obj.getEndereco());
			st.setString(9, obj.getNumeroEndereco());
			st.setString(10, obj.getComplemetoEndereco());
			st.setString(11, obj.getBairro());
			st.setString(12, obj.getCidade());
			st.setString(13, obj.getUf());
			st.setString(14, obj.getCep());

			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdCondominio(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Condominio obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
						"UPDATE tbl_condominio"
						+ "SET "
						+ "cond_razao_social = ?, "
						+ "cond_cnpj = ?, "
						+ "cond_inscricao_estadual = ?, "
						+ "cond_inscricao_municipal = ?, "
						+ "cond_email = ?, "
						+ "cond_telefone = ?, "
						+ "cond_celular = ?, "
						+ "cond_endereco = ?, "
						+ "cond_numero_endereco = ?, "
						+ "cond_complemento_endereco = ?, "
						+ "cond_bairro = ?, "
						+ "cond_cidade = ?, "
						+ "cond_uf = ?, "
						+ "cond_cep = ?) "
						+ "WHERE pk_id_condominio = ?"
						);
			
			st.setString(1, obj.getRazaoSocial());
			st.setString(2, obj.getCnpj());
			st.setString(3, obj.getInscricaoEstadual());
			st.setString(4, obj.getInscricaoMunicipal());
			st.setString(5, obj.getEmail());
			st.setString(6, obj.getTelefone());
			st.setString(7, obj.getCelular());
			st.setString(8, obj.getEndereco());
			st.setString(9, obj.getNumeroEndereco());
			st.setString(10, obj.getComplemetoEndereco());
			st.setString(11, obj.getBairro());
			st.setString(12, obj.getCidade());
			st.setString(13, obj.getUf());
			st.setString(14, obj.getCep());

			st.executeUpdate();			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"DELETE FROM tbl_condominio WHERE pk_id_condominio = ?");

			st.setInt(1, id);

			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Condominio findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tbl_condominio WHERE pk_id_condominio = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Condominio obj = new Condominio();
				obj.setIdCondominio(rs.getInt("pk_id_condominio"));
				obj.setRazaoSocial(rs.getString("cond_razao_social"));
				obj.setCnpj(rs.getString("cond_cnpj"));
				obj.setInscricaoEstadual(rs.getString("cond_inscricao_estadual"));
				obj.setInscricaoMunicipal(rs.getString("cond_inscricao_municipal"));
				obj.setEmail(rs.getString("cond_email"));
				obj.setTelefone(rs.getString("cond_telefone"));
				obj.setCelular(rs.getString("cond_celular"));
				obj.setEndereco(rs.getString("cond_endereco"));
				obj.setNumeroEndereco(rs.getString("cond_numero_endereco"));
				obj.setComplemetoEndereco(rs.getString("cond_complemento_endereco"));
				obj.setBairro(rs.getString("cond_bairro"));
				obj.setCidade(rs.getString("cond_cidade"));
				obj.setUf(rs.getString("cond_uf"));
				obj.setCep(rs.getString("cond_cep"));
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Condominio> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM tbl_condominio ORDER BY Name");
			rs = st.executeQuery();

			List<Condominio> list = new ArrayList<>();

			while (rs.next()) {
				Condominio obj = new Condominio();
				obj.setIdCondominio(rs.getInt("pk_id_condominio"));
				obj.setRazaoSocial(rs.getString("cond_razao_social"));
				obj.setCnpj(rs.getString("cond_cnpj"));
				obj.setInscricaoEstadual(rs.getString("cond_inscricao_estadual"));
				obj.setInscricaoMunicipal(rs.getString("cond_inscricao_municipal"));
				obj.setEmail(rs.getString("cond_email"));
				obj.setTelefone(rs.getString("cond_telefone"));
				obj.setCelular(rs.getString("cond_celular"));
				obj.setEndereco(rs.getString("cond_endereco"));
				obj.setNumeroEndereco(rs.getString("cond_numero_endereco"));
				obj.setComplemetoEndereco(rs.getString("cond_complemento_endereco"));
				obj.setBairro(rs.getString("cond_bairro"));
				obj.setCidade(rs.getString("cond_cidade"));
				obj.setUf(rs.getString("cond_uf"));
				obj.setCep(rs.getString("cond_cep"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
