package model.food.dish;

import model.food.ingredient.Ingredient;
import model.food.ingredient.Milk;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Milkshake extends Drink {
    public Milkshake(String newName, int newPrice, List<Pair<String, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Milkshake() {
        this(
                "Молочный коктейль",
                149,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Milk.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_LOW
        );
    }
}
