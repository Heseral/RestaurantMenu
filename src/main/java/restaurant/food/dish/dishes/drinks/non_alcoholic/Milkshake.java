package restaurant.food.dish.dishes.drinks.non_alcoholic;

import restaurant.food.dish.dishes.drinks.Drink;
import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Milk;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Milkshake extends Drink {
    public Milkshake(String newName, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Milkshake() {
        this(
                "Молочный коктейль",
                149,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Milk.class, 1)
                        )),
                GlobalVar.COOK_TIME_LOW
        );
    }
}
