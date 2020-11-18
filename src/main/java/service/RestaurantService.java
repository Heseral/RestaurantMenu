package service;

import model.CombinationSale;
import model.Restaurant;
import model.food.dish.Dish;
import model.food.ingredient.Ingredient;
import model.food.ingredient.Water;
import util.GlobalVar;
import util.Pair;
import util.Random;
import util.timer_tasks.TaskController;
import util.timer_tasks.TaskCooking;
import util.timer_tasks.TaskWaiting;
import model.Order;
import model.Visitor;

import java.util.*;

public class RestaurantService {

    ///////////////////////////////
    //    МЕНЮ, СКЛАД И СКИДКИ   //
    ///////////////////////////////

    /**
     * Добавляет к каждой категории ингредиента от 5 до 15 улсовных единиц ингредиента
     *
     * @param restaurant ресторан, которому будут доставлены ингредиенты
     */
    public void resupplyIngredientsRandomly(Restaurant restaurant) {
        for (Class<? extends Ingredient> ingredient : GlobalVar.INGREDIENTS) {
            restaurant.getIngredients().put(ingredient, Math.min(restaurant.getIngredients().get(ingredient) + Random.random(5, 15), 100));
        }
    }

    /**
     * Устанавливает стандартное меню для ресторана
     *
     * @param restaurant ресторан, для которого будет установлено стандартное меню
     */
    public void setDefaultRestaurantMenu(Restaurant restaurant) {
        restaurant.setMenu(GlobalVar.ALL_AVAILABLE_DISHES);
    }

    /**
     * Создает случайный набор скидок для ресторана
     *
     * @param restaurant ресторана, для которого будут формироваться наборы скидок
     */
    public void setDefaultRestaurantCombinationsSales(Restaurant restaurant) {
        int amountOfCombinationSales = Random.random(5, 15);
        int amountOfDishesForCombinationSale;
        List<Class<? extends Dish>> combinationSaleDishes;
        Class<? extends Dish> dishInCombinationSale;
        for (int i = 0; i < amountOfCombinationSales; i++) {
            combinationSaleDishes = new ArrayList<>();
            amountOfDishesForCombinationSale = Random.random(2, 5);
            for (int j = 0; j < amountOfDishesForCombinationSale; j++) {
                dishInCombinationSale = Random.pick(GlobalVar.ALL_AVAILABLE_DISHES);
                combinationSaleDishes.add(dishInCombinationSale);
                restaurant.getCombinationSaleTriggers().add(dishInCombinationSale);
            }
            restaurant.getCombinationsSales().add(new CombinationSale(Random.random(5, 30), combinationSaleDishes));
        }
    }

    /**
     * Заполняет склад ресторана случайным количеством ингредиентов: от 25 до 50 каждого типа
     *
     * @param restaurant ресторан, который будет заполнен ингрдиентами
     */
    public void setDefaultRestaurantIngredientsRandomly(Restaurant restaurant) {
        Map<Class<? extends Ingredient>, Integer> ingredients = new HashMap<>();
        for (Class<? extends Ingredient> ingredient : GlobalVar.INGREDIENTS) {
            ingredients.put(ingredient, Random.random(25, 50));
        }
        ingredients.put(Water.class, Integer.MAX_VALUE);

        restaurant.setIngredients(ingredients);
    }

    ///////////////////////////////
    //     ОБРАБОТКА ЗАКАЗОВ     //
    ///////////////////////////////

    /**
     * Спрашивает у ресторана, готов ли он прямо сейчас начать готовить какое то блюдо
     *
     * @param restaurant ресторан, который мы будем опрашивать
     * @param dish       блюдо, которое мы хотели бы приготовить
     * @return true, если ресторан готов начать готовить dish прямо сейчас
     */
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

    /**
     * Обрабатывает заказ клиента: каждое блюдо в заказе обрабатывается в handleOrderedDish(). Также взымает
     * плату с visitor за заказанные блюда.
     *
     * @param restaurant     ресторан, в котором был сделан заказ
     * @param order          заказ
     * @param visitorService сервис посетителя
     */
    public void handleOrder(Restaurant restaurant, Order order, VisitorService visitorService, TaskController controlledBy) {
        while (visitorService.handleCombinationSalesInOrder(order.getOrderedBy(), restaurant)) {
        }
        order.getOrderedBy().setCash(order.getOrderedBy().getCash() - order.getTotalPrice());
        for (Dish orderedDish : order.getOrderedDishes()) {
            handleOrderedDish(restaurant, orderedDish, order.getOrderedBy(), visitorService, controlledBy);
        }
    }

    /**
     * Обработка заказанного клиентом visitor блюда dish. Сюда входит начало приготовления блюда, его задержка
     * приготовления в случае невозможности сделать прямо сейчас, а елси клиент не хочет ждать - вернуть стоиомсть
     * блюда клиенту за неустойку.
     *
     * @param restaurant     ресторан, в котором происходит приготовление блюда
     * @param dish           блюдо
     * @param visitor        посетитель, заказавший блюдо
     * @param visitorService севрис посетителя
     */
    public void handleOrderedDish(
            Restaurant restaurant,
            Dish dish,
            Visitor visitor,
            VisitorService visitorService,
            TaskController controlledBy
    ) {
        if (isRestaurantAvailableToCookDishRightNow(restaurant, dish)) {
            startCookingDish(restaurant, dish, visitor, controlledBy);
            return;
        }
        if (visitorService.isReadyToWaitAdditionalTime(visitor, dish.getTimeToCook(), 1)) {
            new TaskWaiting(
                    restaurant,
                    visitor,
                    this,
                    visitorService,
                    dish,
                    dish.getTimeToCook() + controlledBy.getCurrentTime(),
                    controlledBy
            );
            return;
        }

        handleForfeit(dish, visitor);
    }

    /**
     * Подает блюдо посетителю. На самом деле нет, я вас обманул. Оно просто выводит в консоль, что блюдо подано.
     *
     * @param dish    подаваемое блюдо
     * @param visitor посетитель, принимающий блюдо
     */
    public void serveDish(Dish dish, Visitor visitor) {
        // да, вместо имен будет выведена лапша из буковок и циферок. Так и задумывалось, ведь это лишь симуляция работы
        // ресторана. В настоящем ресторане мы бы не делали глупый sout вывод в консольку.
        System.out.println("      COMPLETED: Подано блюдо: " + dish + ". Заказчик: " + visitor + ".");
    }

    /**
     * Возвращает деньги за блюдо посетителю
     *
     * @param dish    блюдо, которое не удалось приготовить
     * @param visitor посетитель, которому будет переданы деньги за не приготовленное блюдо
     */
    private void handleForfeit(Dish dish, Visitor visitor) {
        System.out.println("      FORFEIT: Блюдо не подано: " + dish + " по причине нехватки ингредиентов. " + visitor + " больше не может ждать. Возмещено");
        visitor.setCash(visitor.getCash() + dish.getCurrentPrice());
    }

    /**
     * Поручает ресторану готовку блюда, включая трату ингредиентов
     *
     * @param restaurant ресторан, в котором готовится блюдо
     * @param dish       блюдо
     * @param visitor    посетитель, заказавший блюдо
     */
    private void startCookingDish(Restaurant restaurant, Dish dish, Visitor visitor, TaskController controlledBy) {
        for (Pair<Class<? extends Ingredient>, Integer> recipePart : dish.getRecipe()) {
            restaurant.getIngredients().put(recipePart.getFirst(), restaurant.getIngredients().get(recipePart.getFirst()) - recipePart.getSecond());
        }
        new TaskCooking(
                dish,
                visitor,
                this,
                restaurant,
                dish.getTimeToCook() + controlledBy.getCurrentTime(),
                controlledBy
        );
    }

    /**
     * Вызывается когда блюдо приготовлено. Подает блюдо клиенту
     *
     * @param dish    приготовленное блюдо
     * @param visitor посетитель, заказавший блюдо
     */
    public void onDishCooked(Dish dish, Visitor visitor) {
        serveDish(dish, visitor);
    }
}
