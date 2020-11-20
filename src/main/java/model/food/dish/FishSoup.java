package model.food.dish;

import model.RecipePart;
import model.food.ingredient.Meat;
import model.food.ingredient.Vegetables;
import model.food.ingredient.Water;
import util.GlobalVar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FishSoup extends Soup {
    public FishSoup(String newName, int newPrice, List<RecipePart> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public FishSoup() {
        this(
                "Уха",
                199,
                new ArrayList<>
                        (Arrays.asList(
                                new RecipePart(Water.class.getName(), 2),
                                new RecipePart(Vegetables.class.getName(), 1),
                                new RecipePart(Meat.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_LONG
        );
    }
}
