package business;

import java.sql.SQLException;
import java.util.List;

import dao.RecipeDao;
import entities.Ingredient;
import entities.Recipe;

public class RecipeImpl implements IRecipe {

	private RecipeDao recipeDao;

	public Recipe addRecipe(String name, String category, String picture, List<Ingredient> ingredients)
			throws SQLException {

		Recipe recipe = new Recipe();
		recipe.setName(name);
		recipe.setPicture(picture);
		recipe.setCategory(category);
		// TODO set List<Ingr>
		this.recipeDao.create(recipe);
		return recipe;

	}

	public Recipe findRecipe(int id) throws SQLException {
		Recipe recipe = this.recipeDao.findById(id);
		return recipe;
	}

	public Recipe findRecipeByIngredient(List<Ingredient> ingredients) {
		Recipe recipe = null;
		return recipe;
	}

	public List<Recipe> showAllRecipes() throws SQLException {
		List<Recipe> list = this.recipeDao.findAll();
		return list;
	}

	public void updateRecipe(Recipe recipe) throws SQLException {
		this.recipeDao.updateById(recipe);

	}

}
