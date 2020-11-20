package model.food.dish;

import model.food.ingredient.Ingredient;
import model.food.ingredient.Meat;
import model.food.ingredient.Vegetables;
import model.food.ingredient.Water;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Borscht extends Soup {
    public Borscht(String newName, int newPrice, List<Pair<String, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Borscht() {
        this(
                "Борщ",
                199,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Water.class.getName(), 2),
                                new Pair<>(Vegetables.class.getName(), 2),
                                new Pair<>(Meat.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_LONG
        );
    }
}
