package visitor;

import restaurant.CombinationSale;
import restaurant.Restaurant;
import restaurant.RestaurantService;
import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Ingredient;
import util.Misc;
import util.Pair;

import java.util.ArrayList;
import java.util.List;

public class VisitorService {

    /**
     * Заставляет посетителя сделать абсолютно случайный заказ - случайные категории и блюда.
     * @param visitor
     * @param 
     */
    public void createAbsolutelyRandomOrder(Visitor visitor) {

    }

    /**
     * Заставляет посетителя сделать частично случайный заказ - случайные блюда определенной категории.
     * @param visitor
     * @param desirableCategories
     */
    public void createPartiallyRandomOrder(Visitor visitor, List<Class<? extends Dish>> desirableCategories) {

    }

    /**
     * Заставляет посетителя сделать конкретный заказ - блюда определены.
     * @param visitor
     * @param desirableDishes
     */
    public void createSpecifiedOrder(Visitor visitor, List<Class<? extends Dish>> desirableDishes) {
        
    }


    /**
     * Попытка посетителя заказать блюдо.
     * @param visitor
     * @param dish
     * @param restaurantService
     * @param restaurant
     * @return true, если блюдо успешно заказано, а иначе false
     */
    public boolean tryToOrderDish(Visitor visitor, Dish dish, RestaurantService restaurantService, Restaurant restaurant) {
        // может ли посетитель себе позволить блюдо?
        if (!canVisitorOrderDish(visitor, dish, restaurant)) {
            return false;
        }
        // может ли ресторан себе позволить блюдо?
        Dish orderedDish = restaurantService.tryToCookDish(restaurant, dish);
        if (orderedDish == null) {
            return false;
        }

        // и ресторан, и посетитель, готовы к обмену. Заказ успешен
        return true;
    }

    /**
     * Проверяет возможность заказа посетителем определенного блюда. В проверку включена цена, свободное время,
     * ограничения посетителя. Если денег не хватает, запускается алгоритм поиска наборов блюд со скидкой в заказе.
     * @param visitor
     * @param dish
     * @param restaurant
     * @return true, если посетитель может заказать это блюдо, а иначе false
     */
    private boolean canVisitorOrderDish(Visitor visitor, Dish dish, Restaurant restaurant) {
        if (visitor.getOrder().getTotalPrice() > visitor.getCash()) {
            if (!handleCombinationSalesInOrder(visitor, restaurant)) {
                return false;
            }
        }
        if (visitor.getFreeTime() < dish.getTimeToCook()) {
            return false;
        }
        for (Pair<Class<? extends Ingredient>, Integer> recipePart : dish.getRecipe()) {
            if (visitor.getRestrictions().contains(recipePart.getFirst()) && recipePart.getSecond() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Добавляет блюдо в заказ посетителя, считает новую цену заказа
     * @param visitor
     * @param dish
     */
    private void addDishToOrder(Visitor visitor, Dish dish) {
        Order order = visitor.getOrder();

        order.getOrderedDishes().add(dish);
        order.setTotalPrice(order.getTotalPrice() + dish.getBasicPrice());
    }

    /**
     * Метод проверяет, есть ли в заказе клиента блюда, образующие комбинацию. Если таковая имеется,
     * изменяется цена за комбинацию.
     * @param visitor
     * @param restaurant
     * @return false, если не найдено скидок; true, если найдена хоть одна
     */
    private boolean handleCombinationSalesInOrder(Visitor visitor, Restaurant restaurant) {
        Order order = visitor.getOrder();
        List<CombinationSale> combinationSales = restaurant.getCombinationsSales();

        // блюда, которые предстоит еще проверить на наличие комбинаций с ними
        List<Dish> unhandledDishes = new ArrayList<>(List.copyOf(order.getOrderedDishes()));
        // блюда, зарегестрированные как блюдо, входящее в комбинацию со скидкой.
        // Сбрасывается на каждую итерацию с CombinationSale
        List<Dish> possibleDishesWithSale = new ArrayList<>();
        // начинаем обход с начала списка
        while (unhandledDishes.size() > 0) {
            // может на товар уже наложена скидка?
            if (unhandledDishes.get(0).getCurrentPrice() < unhandledDishes.get(0).getBasicPrice()) {
                unhandledDishes.remove(0);
                continue;
            }
            // может быть с такими блюдами нет никаких скидок?
            if (!restaurant.getCombinationSaleTriggers().contains(unhandledDishes.get(0).getClass())) {
                unhandledDishes.remove(0);
                continue;
            }
            // значит где то скидка есть. Проверим, в какой из комбинаций
            for (CombinationSale combinationSale : combinationSales) {
                // очевидно, если набор для скидки больше, чем количество заказанных посетителем блюд, то
                // там точно не найдутся необходимые для скидки блюда
                if (order.getOrderedDishes().size() < combinationSale.getFirst().size()) {
                    continue;
                }
                // проходимся по блюдам в наборе, смотрим нашлось ли такое блюдо в заказе. Если да, добавляем
                // в список возможно собранного набора блюд со скидкой
                middleFor:
                for (Class<? extends Dish> combinationSaleDish : combinationSale.getFirst()) {
                    for (Dish unhandledDish : unhandledDishes) {
                        if (unhandledDish.getClass() == combinationSaleDish) {
                            possibleDishesWithSale.add(unhandledDish);
                            continue middleFor;
                        }
                    }
                }
                // может посетитель заказал недостаточно блюд для набора со скидкой?
                if (possibleDishesWithSale.size() != combinationSale.getFirst().size()) {
                    // проверим другой набор блюд, может все таки там будет скидка
                    possibleDishesWithSale.clear();
                    continue;
                }
                // на этом шаге мы подтвердили, что клиент заказал набор со скидкой. Пройдемся по списку блюд из заказа,
                // входящих в набор со скидкой
                for (Dish dishWithSale : possibleDishesWithSale) {
                    // скидка на какое то блюдо в результате собранного набора
                    int sale = (Misc.round(dishWithSale.getBasicPrice() * 0.01 * (100 - combinationSale.getSecond())));
                    // у блюда новая цена с учетом скидки. Возможно, пригодится в чеке или в чем то подобном
                    dishWithSale.setCurrentPrice(sale);
                    // блюдо получило скидку, поэтому вычтем эту скидку из финальной стоимости заказа
                    order.setTotalPrice(order.getTotalPrice() - sale);
                }
                // мы нашли набор со скидкой в заказе клиента и обработали полученную скидку.
                // Сообщаем, что такой набор нашелся
                return true;
            }
            // чтож, для этого блюда мы ни нашли ни одного собранного набора со скидкой. Поищем в других блюдах
            unhandledDishes.remove(0);
        }
        return false;
    }
}
