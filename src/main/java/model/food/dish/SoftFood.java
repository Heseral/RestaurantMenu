package model.food.dish;

import model.food.dish.Dish;
import model.food.ingredient.Ingredient;
import util.Pair;

import java.util.List;

public abstract class SoftFood extends Dish {
    public SoftFood(String newName, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }
}
