package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Recipe")
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "category")
	private String category;
	@Column(name = "picture")
	private String picture;
	@Column(name = "score")
	private int score;
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "recipe_ingredient", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private List<Ingredient> ingredient = new ArrayList<Ingredient>();

	public Recipe() {
		super();
	}

	public Recipe(int id, String name, String category, String picture, int score, List<Ingredient> ingredient) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.picture = picture;
		this.score = score;
		this.ingredient = ingredient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Ingredient> getIngredient() {
		return ingredient;
	}

	public void setIngredient(List<Ingredient> ingredient) {
		this.ingredient = ingredient;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", category=" + category + ", picture=" + picture + ", score="
				+ score + ", ingredient=" + ingredient + "]";
	}

}
