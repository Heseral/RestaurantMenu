package model.food.dish;

import model.food.ingredient.Ingredient;
import model.food.ingredient.Meat;
import model.food.ingredient.Vegetables;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    - Котлетки.
    - С макарошками?
    - Нет, с пюрешкой, с пюрешкой! Ну, на обеде встретимся.
 */
public class CutletsWithMashedPotato extends SoftFood {
    public CutletsWithMashedPotato(String newName, int newPrice, List<Pair<String, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public CutletsWithMashedPotato() {
        this(
                "Котлеты с пюре",
                199,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Vegetables.class.getName(), 1),
                                new Pair<>(Meat.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_DEFAULT
        );
    }
}
