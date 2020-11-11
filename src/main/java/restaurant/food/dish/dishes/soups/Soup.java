package restaurant.food.dish.dishes.soups;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Ingredient;
import util.Pair;

import java.util.List;

public abstract class Soup extends Dish {
    public Soup(String newName, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }
}
