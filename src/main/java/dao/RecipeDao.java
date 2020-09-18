package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Ingredient;
import entities.Recipe;

public class RecipeDao extends Dao<Recipe> {

	public RecipeDao(ConnectionManager cnx) {
		super(cnx);
		// TODO Auto-generated constructor stub
	}

	public List<Recipe> findAll() throws SQLException {
		PreparedStatement ps = ConnectionManager.getConnection()
				.prepareStatement("SELECT r.id as id, r.name as name, "
						+ "r.category as category, r.picture as picture,r.score as score, i.name as iName, i.id as iid "
						+ "FROM recipe r,ingredient i WHERE r.id_ingredient=i.id");
		ResultSet rs = ps.executeQuery();
		List<Recipe> list = new ArrayList<Recipe>();
		while (rs.next()) {
			int ingrId = rs.getInt("iid");
			String iName = rs.getString("iName");
			Ingredient ing = new Ingredient(ingrId, iName);
			List<Ingredient> ingList = new ArrayList<Ingredient>();
			ingList.add(ing);

			int id = rs.getInt("id");
			String name = rs.getString("name");
			String category = rs.getString("category");
			String picture = rs.getString("picture");
			int score = rs.getInt("score");
			Recipe recipe = new Recipe(id, name, category, picture, score, ingList);
			list.add(recipe);
		}
		rs.close();
		ps.close();

		return list;

	}

	public Recipe findById(int id) throws SQLException {

		PreparedStatement ps = ConnectionManager.getConnection()
				.prepareStatement("SELECT r.id as id, r.name as name, "
						+ "r.category as category, r.picture as picture,r.score as score, i.name as iName, i.id as iid "
						+ "FROM recipe r,ingredient i WHERE r.id_ingredient=i.id AND r.id=?");

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		List<Recipe> list = new ArrayList<Recipe>();
		while (rs.next()) {
			int ingrId = rs.getInt("iid");
			String iName = rs.getString("iName");
			Ingredient ing = new Ingredient(ingrId, iName);
			List<Ingredient> ingList = new ArrayList<Ingredient>();
			ingList.add(ing);

			int idR = rs.getInt("id");
			String name = rs.getString("name");
			String category = rs.getString("category");
			String picture = rs.getString("picture");
			int score = rs.getInt("score");
			Recipe recipe = new Recipe(idR, name, category, picture, score, ingList);
			list.add(recipe);

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
		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement("DELETE FROM recipe WHERE id = ?");
		ps.setInt(1, id);
		ps.execute();
		ps.close();

	}

	public int create(Recipe recipe) throws SQLException {

		PreparedStatement ps = ConnectionManager.getConnection().prepareStatement(
				"INSERT INTO recipe (NAME,CATEGORY,PICTURE,SCORE,ID_INGREDIENT) VALUES (?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, recipe.getName());
		ps.setString(2, recipe.getCategory());
		ps.setString(3, recipe.getPicture());
		ps.setInt(4, recipe.getScore());
		ps.setInt(5, ((Ingredient) recipe.getIngredient()).getId());
		ps.execute();
		ResultSet rs = ps.getGeneratedKeys();
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("GENERATED_KEY");
		}
		ps.close();

		return id;

	}

	public void updateById(Recipe recipe) throws SQLException {
		PreparedStatement ps = ConnectionManager.getConnection()
				.prepareStatement("UPDATE RECIPE SET NAME=?,CATEGORY=?,PICTURE=?, SCORE=?,ID_INGREDIENT=? WHERE ID=?");
	}

}
