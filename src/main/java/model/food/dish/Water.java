package model.food.dish;

import model.food.ingredient.Ingredient;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Water extends Drink {
    public Water(String newName, int newPrice, List<Pair<String, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Water() {
        this(
                "Вода",
                49,
                new ArrayList<>
                        (Arrays.asList( // вау вода готовится из воды
                                new Pair<>(model.food.ingredient.Water.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_MINIMUM
        );
    }
}
