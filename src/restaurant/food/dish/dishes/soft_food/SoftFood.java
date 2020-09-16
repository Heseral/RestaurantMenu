package restaurant.food.dish.dishes.soft_food;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Ingredient;
import util.Pair;

import java.util.List;

public abstract class SoftFood extends Dish {
    public SoftFood(String newName, List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newCombinationsSale, newPrice, newIngredients, newTimeToCook);
    }
}
