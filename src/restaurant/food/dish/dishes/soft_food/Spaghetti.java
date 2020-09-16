package restaurant.food.dish.dishes.soft_food;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Egg;
import restaurant.food.ingredient.Flour;
import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Water;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Spaghetti extends SoftFood {
    public Spaghetti(String newName, List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newCombinationsSale, newPrice, newIngredients, newTimeToCook);
    }

    public Spaghetti(List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice) {
        this(
                "Спагетти",
                newCombinationsSale,
                newPrice,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Flour.class, 1),
                                new Pair<>(Egg.class, 1),
                                new Pair<>(Water.class, 1)
                        )),
                GlobalVar.COOK_TIME_DEFAULT
        );
    }
}
