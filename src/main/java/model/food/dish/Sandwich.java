package model.food.dish;

import model.food.ingredient.*;
import model.food.ingredient.Water;
import util.GlobalVar;
import util.RecipePart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sandwich extends Snack {
    public Sandwich(String newName, int newPrice, List<RecipePart> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Sandwich() {
        this(
                "Бутерброд",
                99,
                new ArrayList<>
                        (Arrays.asList( // да, в этом элитном ресторане отдельно готовят хлеб для бутерброда
                                new RecipePart(Meat.class.getName(), 1),
                                new RecipePart(Egg.class.getName(), 2),
                                new RecipePart(Water.class.getName(), 1),
                                new RecipePart(Vegetables.class.getName(), 1),
                                new RecipePart(Flour.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_LOW
        );
    }
}
