package model.food.dish;

import model.food.ingredient.Egg;
import model.food.ingredient.Flour;
import model.food.ingredient.Ingredient;
import model.food.ingredient.Milk;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pancakes extends SweetFood {
    public Pancakes(String newName, int newPrice, List<Pair<String, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Pancakes() {
        this(
                "Блины",
                199,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Milk.class.getName(), 1),
                                new Pair<>(Egg.class.getName(), 2),
                                new Pair<>(Flour.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_DEFAULT
        );
    }
}
