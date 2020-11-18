package model.food.dish;

import model.food.ingredient.*;
import model.food.ingredient.Water;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sandwich extends Snack {
    public Sandwich(String newName, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Sandwich() {
        this(
                "Бутерброд",
                99,
                new ArrayList<>
                        (Arrays.asList( // да, в этом элитном ресторане отдельно готовят хлеб для бутерброда
                                new Pair<>(Meat.class, 1),
                                new Pair<>(Egg.class, 2),
                                new Pair<>(Water.class, 1),
                                new Pair<>(Vegetables.class, 1),
                                new Pair<>(Flour.class, 1)
                        )),
                GlobalVar.COOK_TIME_LOW
        );
    }
}
