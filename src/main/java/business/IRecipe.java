package business;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import entities.Ingredient;
import entities.Recipe;

public interface IRecipe {

	public Recipe addRecipe(String name, String category, String picture, Set<Ingredient> ingredient)
			throws SQLException;

	public Recipe findRecipe(int id) throws SQLException;

	public Recipe findRecipeByIngredient(List<Ingredient> ingredients);

	public List<Recipe> showAllRecipes() throws SQLException;

	public void updateRecipe(Recipe recipe) throws SQLException;

	// public void validateUpdate(Recipe recipe);

}
