package restaurant;

import restaurant.food.dish.Dish;
import restaurant.food.dish.dishes.drinks.alcoholic.Beer;
import restaurant.food.dish.dishes.drinks.alcoholic.Champagne;
import restaurant.food.dish.dishes.drinks.alcoholic.Vodka;
import restaurant.food.dish.dishes.drinks.non_alcoholic.Milkshake;
import restaurant.food.dish.dishes.drinks.non_alcoholic.Soda;
import restaurant.food.dish.dishes.drinks.non_alcoholic.Water;
import restaurant.food.dish.dishes.snacks.Bread;
import restaurant.food.dish.dishes.snacks.Sandwich;
import restaurant.food.dish.dishes.soft_food.CutletsWithMashedPotato;
import restaurant.food.dish.dishes.soft_food.Spaghetti;
import restaurant.food.dish.dishes.soups.Borscht;
import restaurant.food.dish.dishes.soups.FishSoup;
import restaurant.food.dish.dishes.sweet_food.Cake;
import restaurant.food.dish.dishes.sweet_food.Pancakes;
import restaurant.food.ingredient.*;
import util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService {

    ///////////////////////////////
    //    МЕНЮ, СКЛАД И СКИДКИ   //
    ///////////////////////////////

    public void fillDefaultRestaurantMenu(Restaurant restaurantToFill) {
        restaurantToFill.setMenu(Arrays.asList(
                // алкогольные напитки
                Beer.class,
                Champagne.class,
                Vodka.class,
                // неалкогольные напитки
                Milkshake.class,
                Soda.class,
                Water.class,
                // закуски
                Bread.class,
                Sandwich.class,
                // мягкая еда(второе)
                CutletsWithMashedPotato.class,
                Spaghetti.class,
                // супы(первое)
                Borscht.class,
                FishSoup.class,
                // десерты
                Cake.class,
                Pancakes.class
        ));
    }

    public void fillDefaultRestaurantCombinationsSales(Restaurant restaurantToFill) {
        addNewCombinationSaleToRestaurant(restaurantToFill, new CombinationSale(10, Cake.class, Soda.class));
        // суровый русский обед
        addNewCombinationSaleToRestaurant(restaurantToFill, new CombinationSale(25, Vodka.class, Borscht.class, CutletsWithMashedPotato.class));
        addNewCombinationSaleToRestaurant(restaurantToFill, new CombinationSale(10, Pancakes.class, Milkshake.class));
        addNewCombinationSaleToRestaurant(restaurantToFill, new CombinationSale(15, Beer.class, Sandwich.class));

    }

    public void fillDefaultRestaurantIngredients(Restaurant restaurant) {
        Map<Class<? extends Ingredient>, Integer> ingredients = new HashMap<>();
        ingredients.put(Alcohol.class, 40);
        ingredients.put(Egg.class, 40);
        ingredients.put(Flour.class, 20);
        ingredients.put(Fruits.class, 25);
        ingredients.put(Meat.class, 30);
        ingredients.put(Milk.class, 20);
        ingredients.put(Sugar.class, 25);
        ingredients.put(Vegetables.class, 50);
        ingredients.put(restaurant.food.ingredient.Water.class, Integer.MAX_VALUE);

        restaurant.setIngredients(ingredients);
    }

    public void addNewCombinationSaleToRestaurant(Restaurant restaurantToAdd, CombinationSale saleToAdd) {
        restaurantToAdd.getCombinationsSales().add(saleToAdd);
    }

    public void removeCombinationSaleFromRestaurant(Restaurant restaurantToRemove, CombinationSale saleToRemove) {
        int i = 0;
        for (CombinationSale possibleSaleToRemove : restaurantToRemove.getCombinationsSales()) {
            if (saleToRemove.equals(possibleSaleToRemove)) {
                restaurantToRemove.getCombinationsSales().remove(i);
            }
            i++;
        }
    }

    public void clearCombinationSaleListOfRestaurant(Restaurant restaurantToClear) {
        restaurantToClear.getCombinationsSales().clear();
    }

    ///////////////////////////////
    //       ПРИГОТОВЛЕНИЕ       //
    ///////////////////////////////

    public Dish tryToCookDish(Restaurant restaurant, Class<? extends Dish> dish)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Map<Class<? extends Ingredient>, Integer> availableIngredients = restaurant.getIngredients();
        // так что тут у нас рефлексия ахахах ноканецта. Счетчик простреленных ног: 0
        Dish dishToCook = dish.getConstructor().newInstance();
        List<Pair<Class<? extends Ingredient>, Integer>> recipe = dishToCook.getRecipe();

        // а есть из чего готовить?
        for (Pair<Class<? extends Ingredient>, Integer> recipePart : recipe) {
            if (availableIngredients.get(recipePart.getFirst()) < recipePart.getSecond()) {
                return null;
            }
        }
        // да, есть, поэтому готовим и тратим ингредиенты
        for (Pair<Class<? extends Ingredient>, Integer> recipePart : recipe) {
            availableIngredients.put(recipePart.getFirst(), availableIngredients.get(recipePart.getFirst()) - recipePart.getSecond());
        }

        return dishToCook;
    }

}
