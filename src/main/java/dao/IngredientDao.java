package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Ingredient;

public class IngredientDao extends Dao<Ingredient> {

	public IngredientDao(ConnectionManager cnx) {
		super(cnx);
	}

	public List<Ingredient> findAll() throws SQLException {
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement("SELECT * FROM ingredient");
		ResultSet rs = ps.executeQuery();
		List<Ingredient> list = new ArrayList<Ingredient>();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Ingredient ing = new Ingredient(id, name);
			list.add(ing);
		}
		rs.close();
		ps.close();

		return list;
	}

	public Ingredient findById(int id) throws SQLException {
		PreparedStatement ps = ConnectionManager.getConnection()
				.prepareStatement("SELECT * FROM ingredient WHERE id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		List<Ingredient> list = new ArrayList<Ingredient>();
		while (rs.next()) {
			int theId = rs.getInt("id");
			String name = rs.getString("name");
			Ingredient ing = new Ingredient(theId, name);
			list.add(ing);
		}
		rs.close();
		ps.close();
		if (list.size() > 1) {
			throw new SQLException("More than one response");
		} else if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public void deleteById(int id) throws SQLException {
		PreparedStatement ps = ConnectionManager.getConnection()
				.prepareStatement("DELETE FROM ingredient WHERE id = ?");
		ps.setInt(1, id);
		ps.execute();
		ps.close();
	}

	public int create(Ingredient ingredient) throws SQLException {

		PreparedStatement ps = ConnectionManager.getConnection()
				.prepareStatement("INSERT INTO ingredient (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, ingredient.getName());

		ps.execute();
		ResultSet rs = ps.getGeneratedKeys();
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("GENERATED_KEY");
		}
		ps.close();

		return id;
	}

	public void updateById(Ingredient ingredient) throws SQLException {

		PreparedStatement ps = ConnectionManager.getConnection()
				.prepareStatement("UPDATE ingredient SET NAME=? WHERE ID=?");
		ps.setString(1, ingredient.getName());
		ps.setInt(3, ingredient.getId());
		ps.execute();
		ps.close();
	}

}
