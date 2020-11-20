package model.food.dish;

import model.food.ingredient.*;
import util.GlobalVar;
import model.RecipePart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cake extends SweetFood {
    public Cake(String newName, int newPrice, List<RecipePart> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Cake() {
        this(
                "Фруктовый торт",
                399,
                new ArrayList<>
                        (Arrays.asList(
                                new RecipePart(Sugar.class.getName(), 2),
                                new RecipePart(Fruits.class.getName(), 1),
                                new RecipePart(Egg.class.getName(), 3),
                                new RecipePart(Flour.class.getName(), 2),
                                new RecipePart(Milk.class.getName(), 2)
                        )),
                GlobalVar.COOK_TIME_MAXIMUM
        );
    }
}
