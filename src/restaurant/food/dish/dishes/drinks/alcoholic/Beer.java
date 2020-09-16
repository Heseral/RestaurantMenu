package restaurant.food.dish.dishes.drinks.alcoholic;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Alcohol;
import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Water;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Beer extends Alcoholic {

    public Beer(String newName, List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice, List<Pair<Ingredient, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newCombinationsSale, newPrice, newIngredients, newTimeToCook);
    }

    public Beer(List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice) {
        this(
                "Пиво",
                newCombinationsSale,
                newPrice,
                new ArrayList<Pair<Ingredient, Integer>>
                        (
                                new Pair<>(Alcohol.class, 1),
                                new Pair<>(Water.class, 1)
                        ), 1);
    }
}
