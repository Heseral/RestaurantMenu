package model.food.dish;

import model.food.ingredient.Ingredient;
import model.food.ingredient.Water;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Soda extends Drink {
    public Soda(String newName, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Soda() {
        this(
                "Газировка",
                99,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Water.class, 1)
                        )),
                GlobalVar.COOK_TIME_MINIMUM
        );
    }
}
