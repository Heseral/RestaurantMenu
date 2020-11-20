package model.food.dish;

import model.food.ingredient.Egg;
import model.food.ingredient.Flour;
import model.food.ingredient.Water;
import util.GlobalVar;
import util.RecipePart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Spaghetti extends SoftFood {
    public Spaghetti(String newName, int newPrice, List<RecipePart> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Spaghetti() {
        this(
                "Спагетти",
                199,
                new ArrayList<>
                        (Arrays.asList(
                                new RecipePart(Flour.class.getName(), 1),
                                new RecipePart(Egg.class.getName(), 1),
                                new RecipePart(Water.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_DEFAULT
        );
    }
}
