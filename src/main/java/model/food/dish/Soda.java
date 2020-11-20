package model.food.dish;

import model.food.ingredient.Water;
import util.GlobalVar;
import model.RecipePart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Soda extends Drink {
    public Soda(String newName, int newPrice, List<RecipePart> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Soda() {
        this(
                "Газировка",
                99,
                new ArrayList<>
                        (Arrays.asList(
                                new RecipePart(Water.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_MINIMUM
        );
    }
}
