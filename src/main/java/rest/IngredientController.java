package rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.IIngredient;
import entities.Ingredient;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

	@Autowired
	private IIngredient ingBuisness;

	@GET
	public List<Ingredient> getAll() throws SQLException {
		List<Ingredient> list = ingBuisness.findAll();
		return list;
	}

	@POST
	@Path("/create")
	public void createIngredient(@PathVariable String name) throws SQLException {
		ingBuisness.add(name);
	}

	@GET
	@Path("/{id}")
	public Ingredient getIngredient(@PathVariable int id) throws SQLException {
		Ingredient ingredient = ingBuisness.findById(id);
		return ingredient;
	}

	@POST
	@Path("/{id}")
	public void deleteIngredient(@PathVariable int id) throws SQLException {
		ingBuisness.deleteIngredient(id);

	}
}
