package business;

import java.sql.SQLException;
import java.util.List;

import dao.IngredientDao;
import entities.Ingredient;

public class IngredientImpl implements IIngredient {

	private IngredientDao ingredientDao;

	public void add(String name) throws SQLException {
		Ingredient ingredient = new Ingredient();
		ingredient.setName(name);
		this.ingredientDao.create(ingredient);

	}

	public void addToRecipe(Ingredient ingredient) {
		// TODO
	}

	public Ingredient findById(int id) throws SQLException {
		Ingredient ingredient = ingredientDao.findById(id);

		return ingredient;

	}

	public List<Ingredient> findAll() throws SQLException {
		List<Ingredient> ingredients = ingredientDao.findAll();
		return ingredients;
	}

	public void deleteIngredient(int id) throws SQLException {
		ingredientDao.deleteById(id);

	}

}
