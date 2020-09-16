package restaurant.food.dish.dishes.drinks.alcoholic;

import restaurant.food.dish.dishes.sweet_food.Cake;
import restaurant.food.ingredient.Alcohol;
import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Water;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Champagne extends Alcoholic {

    public Champagne(String newName, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Champagne() {
        this(
                "Шампанское",
                299,
                new ArrayList<>
                        (Arrays.asList( // так что тут у нас 10%-ный раствор спирта вместо шампанского ахахах наконецта
                                new Pair<>(Alcohol.class, 1),
                                new Pair<>(Water.class, 1)
                        )),
                GlobalVar.COOK_TIME_MINIMUM
        );
    }
}
