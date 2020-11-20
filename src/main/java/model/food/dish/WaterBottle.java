package model.food.dish;

import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaterBottle extends Drink {
    public WaterBottle(String newName, int newPrice, List<Pair<String, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public WaterBottle() {
        this(
                "Бутылка воды",
                49,
                new ArrayList<>
                        (Arrays.asList( // вау вода готовится из воды
                                new Pair<>(model.food.ingredient.Water.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_MINIMUM
        );
    }
}
