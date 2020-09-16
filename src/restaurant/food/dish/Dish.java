package restaurant.food.dish;

import restaurant.food.Food;
import restaurant.food.ingredient.Ingredient;
import util.Pair;

import java.util.List;

public abstract class Dish extends Food {
    // комбинации еды, с которыми будет скидка. Это список комбинаций, состоящий из пары "Набор, который нужен
    // для скидки" и сама скидка
    private List<Pair<List<Dish>, Integer>> combinationsSale;
    // время, необходимое для приготовления блюда
    private int timeToCook;
    // ингредиенты, необходимые для приготовления блюда в виде: Список пар вида: Ингредиент-количество
    private List<Pair<Ingredient, Integer>> ingredients;

    // не передавайте нуллы никуда. Лучше передайте свежесозданный, но пустой объект
    public Dish(
            String newName,
            List<Pair<List<Dish>, Integer>> newCombinationsSale,
            int newPrice,
            List<Pair<Ingredient, Integer>> newIngredients,
            int newTimeToCook
    ) {
        setName(newName);
        setCombinationsSale(newCombinationsSale);
        setBasicPrice(newPrice);
        setIngredients(newIngredients);
        setTimeToCook(newTimeToCook);
    }

    public List<Pair<List<Dish>, Integer>> getCombinationsSale() {
        return combinationsSale;
    }

    public void setCombinationsSale(List<Pair<List<Dish>, Integer>> combinationsSale) {
        this.combinationsSale = combinationsSale;
    }

    public List<Pair<Ingredient, Integer>> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Pair<Ingredient, Integer>> ingredients) {
        this.ingredients = ingredients;
    }

    public int getTimeToCook() {
        return timeToCook;
    }

    public void setTimeToCook(int timeToCook) {
        this.timeToCook = timeToCook;
    }
}
