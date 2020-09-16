package restaurant.food.dish.dishes.drinks.non_alcoholic;

import restaurant.food.dish.Dish;
import restaurant.food.dish.dishes.drinks.Drink;
import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Milk;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Milkshake extends Drink {
    public Milkshake(String newName, List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newCombinationsSale, newPrice, newIngredients, newTimeToCook);
    }

    public Milkshake(List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice) {
        this(
                "Молочный коктейль",
                newCombinationsSale,
                newPrice,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Milk.class, 1)
                        )),
                GlobalVar.COOK_TIME_LOW
        );
    }
}
