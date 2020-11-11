package restaurant.food.dish.dishes.drinks.alcoholic;

import restaurant.food.dish.dishes.drinks.Drink;
import restaurant.food.ingredient.Ingredient;
import util.Pair;

import java.util.List;

public abstract class Alcoholic extends Drink {
    public Alcoholic(String newName, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }
}
