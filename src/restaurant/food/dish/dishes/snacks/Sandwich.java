package restaurant.food.dish.dishes.snacks;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.*;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sandwich extends Snack {
    public Sandwich(String newName, List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newCombinationsSale, newPrice, newIngredients, newTimeToCook);
    }

    public Sandwich(List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice) {
        this(
                "Бутерброд",
                newCombinationsSale,
                newPrice,
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
