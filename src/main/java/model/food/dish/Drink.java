package model.food.dish;

import model.food.dish.Dish;
import model.food.ingredient.Ingredient;
import util.Pair;

import java.util.List;

public abstract class Drink extends Dish {

    public Drink(String newName, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }
}
