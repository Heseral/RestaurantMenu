package model.food.dish;

import model.food.ingredient.Alcohol;
import model.food.ingredient.Ingredient;
import model.food.ingredient.Water;
import util.GlobalVar;
import util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vodka extends Alcoholic {
    public Vodka(String newName, int newPrice, List<Pair<String, Integer>> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }

    public Vodka() {
        this(
                "Водка",
                349,
                new ArrayList<>
                        (Arrays.asList( // ну тут уже хотя бы больше похоже на правду, чем местное пиво или шампанское
                                new Pair<>(Alcohol.class.getName(), 4),
                                new Pair<>(Water.class.getName(), 1)
                        )),
                GlobalVar.COOK_TIME_MINIMUM
        );
    }
}
