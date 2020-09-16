package restaurant.food.dish.dishes.drinks.non_alcoholic;

import restaurant.food.dish.Dish;
import restaurant.food.dish.dishes.drinks.Drink;
import restaurant.food.ingredient.Ingredient;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Water extends Drink {
    public Water(String newName, List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newCombinationsSale, newPrice, newIngredients, newTimeToCook);
    }

    public Water(List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice) {
        this(
                "Вода",
                newCombinationsSale,
                newPrice,
                new ArrayList<>
                        (Arrays.asList( // вау вода готовится из воды
                                new Pair<>(restaurant.food.ingredient.Water.class, 1)
                        )),
                GlobalVar.COOK_TIME_MINIMUM
        );
    }
}
