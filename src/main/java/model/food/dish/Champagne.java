package model.food.dish;

import model.food.ingredient.Alcohol;
import model.food.ingredient.Ingredient;
import model.food.ingredient.Water;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Champagne extends Alcoholic {

    public Champagne(String newName, int newPrice, List<Pair<String, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Champagne() {
        this(
                "Шампанское",
                299,
                new ArrayList<>
                        (Arrays.asList( // так что тут у нас 10%-ный раствор спирта вместо шампанского ахахах наконецта
                                new Pair<>(Alcohol.class.getName(), 1),
                                new Pair<>(Water.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_MINIMUM
        );
    }
}
