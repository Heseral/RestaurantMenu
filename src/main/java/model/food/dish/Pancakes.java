package model.food.dish;

import model.food.ingredient.Egg;
import model.food.ingredient.Flour;
import model.food.ingredient.Milk;
import util.GlobalVar;
import util.RecipePart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pancakes extends SweetFood {
    public Pancakes(String newName, int newPrice, List<RecipePart> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Pancakes() {
        this(
                "Блины",
                199,
                new ArrayList<>
                        (Arrays.asList(
                                new RecipePart(Milk.class.getName(), 1),
                                new RecipePart(Egg.class.getName(), 2),
                                new RecipePart(Flour.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_DEFAULT
        );
    }
}
