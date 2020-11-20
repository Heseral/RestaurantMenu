package model.food.dish;

import model.food.dish.Dish;
import model.food.ingredient.Ingredient;
import util.Pair;

import java.util.List;

public abstract class Snack extends Dish {
    public Snack(String newName, int newPrice, List<Pair<String, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }
}
