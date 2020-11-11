package restaurant.food.dish.dishes.sweet_food;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Ingredient;
import util.Pair;

import java.util.List;

public abstract class SweetFood extends Dish {
    public SweetFood(String newName, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }
}
