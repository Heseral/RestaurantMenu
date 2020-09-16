package restaurant.food.dish.dishes.drinks.alcoholic;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Alcohol;
import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Water;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Champagne extends Alcoholic {
    { setName("Шампанское");}

    public Champagne(String newName, List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newCombinationsSale, newPrice, newIngredients, newTimeToCook);
    }

    public Champagne(List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice) {
        this(
                "Шампанское",
                newCombinationsSale,
                newPrice,
                new ArrayList<>
                        (Arrays.asList( // так что тут у нас 10%-ный раствор спирта вместо шампанского ахахах наконецта
                                new Pair<>(Alcohol.class, 1),
                                new Pair<>(Water.class, 1)
                        )),
                GlobalVar.COOK_TIME_MINIMUM
        );
    }
}
