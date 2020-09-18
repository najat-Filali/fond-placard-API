package rest;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.IRecipe;
import entities.Ingredient;
import entities.Recipe;

@RestController
@RequestMapping("/")
public class RecipeController {

	@Autowired
	private IRecipe recipeBusiness;

	@GetMapping("/recipes")
	public List<Recipe> getRecipes() throws SQLException {
		List<Recipe> recipe = recipeBusiness.showAllRecipes();
		return recipe;
	}

	@PostMapping("/create")
	public ResponseEntity<Ingredient> createRecipe(@PathVariable("name") String name,
			@PathVariable("category") String category, @PathVariable("picture") String picture,
			@PathVariable("ingredients") List<Ingredient> ingredient) throws SQLException {
		recipeBusiness.addRecipe(name, category, picture, ingredient);
		return new ResponseEntity<Ingredient>(new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Recipe> getRecipe(@PathVariable("id") int id) throws SQLException {
		Recipe recipe = recipeBusiness.findRecipe(id);
		return new ResponseEntity<Recipe>(recipe, new HttpHeaders(), HttpStatus.OK);
	}

}
