package model.food.dish;

import model.food.ingredient.*;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cake extends SweetFood {
    public Cake(String newName, int newPrice, List<Pair<String, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Cake() {
        this(
                "Фруктовый торт",
                399,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Sugar.class.getName(), 2),
                                new Pair<>(Fruits.class.getName(), 1),
                                new Pair<>(Egg.class.getName(), 3),
                                new Pair<>(Flour.class.getName(), 2),
                                new Pair<>(Milk.class.getName(), 2)
                        )),
                GlobalVar.COOK_TIME_MAXIMUM
        );
    }
}
