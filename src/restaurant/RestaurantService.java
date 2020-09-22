package restaurant;

import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Ingredient;
import restaurant.food.ingredient.Water;
import util.GlobalVar;
import util.Pair;
import util.Random;
import visitor.Order;
import visitor.Visitor;
import visitor.VisitorService;

import java.util.*;

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
    //     ОБРАБОТКА ЗАКАЗОВ     //
    ///////////////////////////////

    public boolean isRestaurantAvailableToCookDishRightNow(Restaurant restaurant, Dish dish) {
        Map<Class<? extends Ingredient>, Integer> availableIngredients = restaurant.getIngredients();
        List<Pair<Class<? extends Ingredient>, Integer>> recipe = dish.getRecipe();

        // а есть из чего готовить?
        for (Pair<Class<? extends Ingredient>, Integer> recipePart : recipe) {
            if (availableIngredients.get(recipePart.getFirst()) < recipePart.getSecond()) {
                return false;
            }
        }

        return true;
    }

    public void handleOrder(Restaurant restaurant, Order order, VisitorService visitorService) {
        for (Dish orderedDish : order.getOrderedDishes()) {
            handleOrderedDish(restaurant, orderedDish, order.getOrderedBy(), visitorService);
        }
    }

    public void handleOrderedDish(Restaurant restaurant, Dish dish, Visitor visitor, VisitorService visitorService) {
        if (isRestaurantAvailableToCookDishRightNow(restaurant, dish)) {
            startCookingDish(restaurant, dish, visitor);
            return;
        }
        if (visitorService.isReadyToWaitAdditionalTime(visitor, dish.getTimeToCook(), 1)) {
            GlobalVar.TIMER.schedule(new TimerTask() {
                @Override
                public void run() {
                    handleOrderedDish(restaurant, dish, visitor, visitorService);
                }
            },1000);
            return;
        }

        handleForfeit(dish, visitor);
    }

    public void serveDish(Dish dish, Visitor visitor) {
        // да, вместо имен будет выведена лапша из буковок и циферок. Так и задумывалось, ведь это лишь симуляция работы
        // ресторана. В настоящем ресторане мы бы не делали глупый sout вывод в консольку.
        System.out.println("      COMPLETED: Подано блюдо: " + dish + ". Заказчик: " + visitor + ".");
    }

    private void handleForfeit(Dish dish, Visitor visitor) {
        System.out.println("      FORFEIT: Блюдо не подано: " + dish + " по причине нехватки ингредиентов. " + visitor + " больше не может ждать. Возмещено");
        visitor.setCash(visitor.getCash() + dish.getCurrentPrice());
    }

    private void startCookingDish(Restaurant restaurant, Dish dish, Visitor visitor) {
        for (Pair<Class<? extends Ingredient>, Integer> recipePart : dish.getRecipe()) {
            restaurant.getIngredients().put(recipePart.getFirst(), restaurant.getIngredients().get(recipePart.getFirst()) - recipePart.getSecond());
        }
        GlobalVar.TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                onDishCooked(dish, visitor);

                cancel();
            }
        }, dish.getTimeToCook() * 1000);
    }

    private void onDishCooked(Dish dish, Visitor visitor) {
        serveDish(dish, visitor);
    }
}
