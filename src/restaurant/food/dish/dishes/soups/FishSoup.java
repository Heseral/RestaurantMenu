package restaurant.food.dish.dishes.soups;

import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Meat;
import restaurant.food.ingredient.Vegetables;
import restaurant.food.ingredient.Water;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FishSoup extends Soup {
    public FishSoup(String newName, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public FishSoup() {
        this(
                "Уха",
                199,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Water.class, 2),
                                new Pair<>(Vegetables.class, 1),
                                new Pair<>(Meat.class, 1)
                        )),
                GlobalVar.COOK_TIME_LONG
        );
    }
}
