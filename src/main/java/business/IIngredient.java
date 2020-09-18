package business;

import java.sql.SQLException;
import java.util.List;

import entities.Ingredient;

public interface IIngredient {

	public void addToRecipe(Ingredient ingredient);

	public Ingredient findById(int id) throws SQLException;

	public List<Ingredient> findAll() throws SQLException;

	public void deleteIngredient(int id) throws SQLException;

	public void add(String name) throws SQLException;

}
