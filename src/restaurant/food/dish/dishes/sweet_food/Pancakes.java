package restaurant.food.dish.dishes.sweet_food;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Egg;
import restaurant.food.ingredient.Flour;
import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Milk;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pancakes extends SweetFood {
    public Pancakes(String newName, List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newCombinationsSale, newPrice, newIngredients, newTimeToCook);
    }

    public Pancakes(List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice) {
        this(
                "Блины",
                newCombinationsSale,
                newPrice,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Milk.class, 1),
                                new Pair<>(Egg.class, 2),
                                new Pair<>(Flour.class, 1)
                        )),
                GlobalVar.COOK_TIME_DEFAULT
        );
    }
}
