package model.food.dish;

import model.RecipePart;
import model.food.ingredient.Egg;
import model.food.ingredient.Flour;
import model.food.ingredient.Water;
import util.GlobalVar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bread extends Snack {
    public Bread(String newName, int newPrice, List<RecipePart> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Bread() {
        this(
                "Хлеб",
                49,
                new ArrayList<>
                        (Arrays.asList(
                                new RecipePart(Egg.class.getName(), 2),
                                new RecipePart(Flour.class.getName(), 1),
                                new RecipePart(Water.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_MINIMUM
        );
    }
}
