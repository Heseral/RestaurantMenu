package restaurant.food.dish.dishes.sweet_food;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.*;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cake extends SweetFood {
    public Cake(String newName, List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newCombinationsSale, newPrice, newIngredients, newTimeToCook);
    }

    public Cake(List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice) {
        this(
                "",
                newCombinationsSale,
                newPrice,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Sugar.class, 2),
                                new Pair<>(Fruits.class, 1),
                                new Pair<>(Egg.class, 3),
                                new Pair<>(Flour.class, 2),
                                new Pair<>(Milk.class, 2)
                        )),
                GlobalVar.COOK_TIME_MAXIMUM
        );
    }
}
