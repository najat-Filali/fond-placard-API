package rest;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.IRecipe;
import entities.Recipe;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	private IRecipe recipeBusiness;

	public List<Recipe> getRecipes() throws SQLException {
		List<Recipe> recipe = recipeBusiness.showAllRecipes();
		return recipe;

	}

	public void setRecipe(Recipe recipe) {

	}

}
