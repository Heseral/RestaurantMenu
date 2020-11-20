package model.food.dish;

import util.RecipePart;

import java.util.List;

public abstract class Alcoholic extends Drink {
    public Alcoholic(String newName, int newPrice, List<RecipePart> newIngredients, int newTimeToCook) {
        super(newName, newPrice, newIngredients, newTimeToCook);
    }
}
