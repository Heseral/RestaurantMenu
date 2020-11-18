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
