package model.food.dish;

import model.food.ingredient.Alcohol;
import model.food.ingredient.Ingredient;
import model.food.ingredient.Water;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Beer extends Alcoholic {

    public Beer(String newName, int newPrice, List<Pair<String, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Beer() {
        this(
                "Пиво",
                199,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Alcohol.class.getName(), 1), // на самом деле получится 5%-ный раствор спирта и воды,
                                new Pair<>(Water.class.getName(), 2)    // но тсс, это фича!
                        )),
                GlobalVar.COOK_TIME_MINIMUM
        );
    }
}
