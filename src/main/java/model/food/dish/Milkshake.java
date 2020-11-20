package model.food.dish;

import model.food.ingredient.Milk;
import util.GlobalVar;
import util.RecipePart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Milkshake extends Drink {
    public Milkshake(String newName, int newPrice, List<RecipePart> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Milkshake() {
        this(
                "Молочный коктейль",
                149,
                new ArrayList<>
                        (Arrays.asList(
                                new RecipePart(Milk.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_LOW
        );
    }
}
