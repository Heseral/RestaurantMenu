package model.food.dish;

import model.food.ingredient.Meat;
import model.food.ingredient.Vegetables;
import util.GlobalVar;
import util.RecipePart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    - Котлетки.
    - С макарошками?
    - Нет, с пюрешкой, с пюрешкой! Ну, на обеде встретимся.
 */
public class CutletsWithMashedPotato extends SoftFood {
    public CutletsWithMashedPotato(String newName, int newPrice, List<RecipePart> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public CutletsWithMashedPotato() {
        this(
                "Котлеты с пюре",
                199,
                new ArrayList<>
                        (Arrays.asList(
                                new RecipePart(Vegetables.class.getName(), 1),
                                new RecipePart(Meat.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_DEFAULT
        );
    }
}
