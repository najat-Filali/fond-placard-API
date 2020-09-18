package dao;

import java.sql.SQLException;
import java.util.List;

public abstract class Dao<T> {

	private ConnectionManager cnx = null;

	public Dao(ConnectionManager cnx) {
		super();
		this.cnx = cnx;
	}

	public abstract List<T> findAll() throws SQLException;

	public abstract T findById(int id) throws SQLException;

	public abstract void deleteById(int id) throws SQLException;

	public abstract int create(T obj) throws SQLException;

	public abstract void updateById(T obj) throws SQLException;

}
