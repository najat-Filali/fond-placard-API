package rest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import business.IIngredient;
import entities.Ingredient;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IIngredient ingBusiness;

	@GetMapping
	public List<Ingredient> getAll() throws SQLException {
		return ingBusiness.findAll();
	}

	@PostMapping("/create")
	public ResponseEntity<Ingredient> createIngredient(@PathVariable("name") String name) throws SQLException {
		ingBusiness.add(name);
		return new ResponseEntity<Ingredient>(new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Ingredient> getIngredient(@PathVariable("id") int id) throws SQLException {
		Ingredient ingredient = ingBusiness.findById(id);
		return new ResponseEntity<Ingredient>(ingredient, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteIngredient(@PathVariable("id") int id) throws SQLException {
		ingBusiness.deleteIngredient(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
