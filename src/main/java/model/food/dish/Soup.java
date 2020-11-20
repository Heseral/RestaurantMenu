package model.food.dish;

import model.food.dish.Dish;
import model.food.ingredient.Ingredient;
import util.Pair;

import java.util.List;

public abstract class Soup extends Dish {
    public Soup(String newName, int newPrice, List<Pair<String, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }
}
