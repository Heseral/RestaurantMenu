package restaurant.food.dish.dishes.sweet_food;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.*;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cake extends SweetFood {
    public Cake(String newName, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Cake() {
        this(
                "Фруктовый торт",
                399,
                new ArrayList<>
                        (Arrays.asList(
                                new Pair<>(Sugar.class, 2),
                                new Pair<>(Fruits.class, 1),
                                new Pair<>(Egg.class, 3),
                                new Pair<>(Flour.class, 2),
                                new Pair<>(Milk.class, 2)
                        )),
                GlobalVar.COOK_TIME_MAXIMUM
        );
    }
}
