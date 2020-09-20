package restaurant;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Water;
import util.GlobalVar;
import util.Misc;
import util.Pair;
import util.Random;
import visitor.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService {

    ///////////////////////////////
    //    МЕНЮ, СКЛАД И СКИДКИ   //
    ///////////////////////////////

    public void resupplyIngredientsRandomly(Restaurant restaurant) {
        for (Class<? extends Ingredient> ingredient : GlobalVar.INGREDIENTS) {
            restaurant.getIngredients().put(ingredient, Math.min(restaurant.getIngredients().get(ingredient) + Random.random(5, 20), 100));
        }
    }

    public void setDefaultRestaurantMenu(Restaurant restaurant) {
        restaurant.setMenu(GlobalVar.ALL_AVAILABLE_DISHES);
    }

    public void setDefaultRestaurantCombinationsSales(Restaurant restaurant) {
        int amountOfCombinationSales = Random.random(5, 15);
        int amountOfDishesForCombinationSale;
        List<Class<? extends Dish>> combinationSaleDishes = new ArrayList<>();
        Class<? extends Dish> dishInCombinationSale;
        for (int i = 0; i < amountOfCombinationSales; i++) {
            amountOfDishesForCombinationSale = Random.random(2, 5);
            for (int j = 0; j < amountOfDishesForCombinationSale; j++) {
                dishInCombinationSale = Random.pick(GlobalVar.ALL_AVAILABLE_DISHES);
                combinationSaleDishes.add(dishInCombinationSale);
                restaurant.getCombinationSaleTriggers().add(dishInCombinationSale);
            }
            restaurant.getCombinationsSales().add(new CombinationSale(Random.random(5, 30), combinationSaleDishes));
        }
    }

    public void setDefaultRestaurantIngredientsRandomly(Restaurant restaurant) {
        Map<Class<? extends Ingredient>, Integer> ingredients = new HashMap<>();
        for (Class<? extends Ingredient> ingredient : GlobalVar.INGREDIENTS) {
            ingredients.put(ingredient, Random.random(40, 60));
        }
        ingredients.put(Water.class, Integer.MAX_VALUE);

        restaurant.setIngredients(ingredients);
    }

    public void addNewCombinationSaleToRestaurant(Restaurant restaurant, CombinationSale saleToAdd) {
        restaurant.getCombinationsSales().add(saleToAdd);
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

    public void clearCombinationSaleListOfRestaurant(Restaurant restaurant) {
        restaurant.getCombinationsSales().clear();
    }

    ///////////////////////////////
    //       ПРИГОТОВЛЕНИЕ       //
    ///////////////////////////////

    // todo: переименовать "может ли выполнить заказ", не забирать тут ингредиенты, а поручить это обработчику событий
    public Dish tryToCookDish(Restaurant restaurant, Dish dish) {
        Map<Class<? extends Ingredient>, Integer> availableIngredients = restaurant.getIngredients();
        List<Pair<Class<? extends Ingredient>, Integer>> recipe = dish.getRecipe();

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

        // TODO: !!!НЕ ЗАБИРАТЬ ИНГРЕДИЕНТЫ ЗДЕСЬ!!!, ПОРУЧИТЬ ЭТО ОБРАБОТЧИКУ СОБЫТИЙ
        // todo: обработчик событий. В него добавлять заказы к приготовлению с информацией для кого заказ
        // todo: использовать TimerTask & Timer

        return dish;
    }

    public void handleOrder(Restaurant restaurant, Order order) {
        for (Dish orderedDish : order.getOrderedDishes()) {
            if (tryToCookDish(restaurant, orderedDish) != null) {
                // todo: поручитьПопыткуГотовкиОбработчикуСобытий()
            }
            /*
                feature: иначе спросить клиента готов ли он подождать.
                    Если нет, возвращаем деньги за блюдо.
                    Если да, поручить обработчику событий попытку приготовления блюда
             */
        }
    }

}
