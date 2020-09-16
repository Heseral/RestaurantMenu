package restaurant.food.dish.dishes.drinks.alcoholic;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Alcohol;
import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Water;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vodka extends Alcoholic {
    public Vodka(String newName, List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice, List<Pair<Class<? extends Ingredient>, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newCombinationsSale, newPrice, newIngredients, newTimeToCook);
    }

    public Vodka(List<Pair<List<Dish>, Integer>> newCombinationsSale, int newPrice) {
        this(
                "Водка",
                newCombinationsSale,
                newPrice,
                new ArrayList<>
                        (Arrays.asList( // ну тут уже хотя бы больше похоже на правду, чем местное пиво или шампанское
                                new Pair<>(Alcohol.class, 4),
                                new Pair<>(Water.class, 1)
                        )),
                GlobalVar.COOK_TIME_MINIMUM
        );
    }
}
