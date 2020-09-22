package visitor;

import restaurant.CombinationSale;
import restaurant.Restaurant;
import restaurant.RestaurantService;
import restaurant.food.dish.Dish;
import restaurant.food.ingredient.Ingredient;
import util.GlobalVar;
import util.Misc;
import util.Pair;
import util.Random;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class VisitorService {

    /**
     * Заставляет посетителя сделать абсолютно случайный заказ - случайные категории и блюда.
     * Есть малая вероятность, что клиент ничего не закажет
     *
     * @param visitor посетитель, делающий заказ
     */
    public boolean createAbsolutelyRandomOrder(Visitor visitor, RestaurantService restaurantService, Restaurant restaurant) {
        List<Class<? extends Dish>> desirableCategories = new ArrayList<>();
        for (Class<? extends Dish> category : GlobalVar.DISH_CATEGORIES) {
            // вероятность заказа этой категории 50%
            if (Random.prob(50)) {
                int amount = Random.random(1, 3);
                for (int i = 0; i < amount; i++) {
                    desirableCategories.add(category);
                }
            }
        }

        return createPartiallyRandomOrder(visitor, desirableCategories, restaurantService, restaurant);
    }

    /**
     * Заставляет посетителя сделать частично случайный заказ - случайные блюда определенной категории.
     *
     * @param visitor             посетитель, делающий заказ
     * @param desirableCategories предпочитаемые категории. Если одна категория встречается более одного раза, то
     *                            считается, что посетитель желает 2 блюда с такой категорией
     */
    public boolean createPartiallyRandomOrder(
            Visitor visitor,
            List<Class<? extends Dish>> desirableCategories,
            RestaurantService restaurantService,
            Restaurant restaurant
    ) {
        List<Class<? extends Dish>> desirableDishes = new ArrayList<>();
        for (Class<? extends Dish> desirableCategory : desirableCategories) {
            desirableDishes.add(Random.pick(GlobalVar.DISHES_BY_CATEGORY.get(desirableCategory)));
        }

        try {
            return createSpecifiedOrder(visitor, desirableDishes, restaurantService, restaurant);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Заставляет посетителя сделать конкретный заказ - блюда определены.
     *
     * @param visitor           посетитель, делающий заказ
     * @param desirableDishes   предпочитаемые блюда
     * @param restaurantService сервис ресторана, обслуживающий клиента
     * @param restaurant        ресторан, в котором происходит заказ
     */
    public boolean createSpecifiedOrder(Visitor visitor, List<Class<? extends Dish>> desirableDishes, RestaurantService restaurantService, Restaurant restaurant)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // нельзя создать пустой заказ
        if (desirableDishes.size() < 1) {
            return false;
        }

        for (Class<? extends Dish> desirableDish : desirableDishes) {
            tryToAddDishToOrder(visitor, desirableDish.getConstructor().newInstance(), restaurantService, restaurant);
        }

        makeOrder(visitor, restaurantService, restaurant);
        return true;
    }

    /**
     * Создает заказ исходя из полей с предпочтениями посетителя
     * @param visitor посетитель, делающий заказ
     * @param restaurantService сервис ресторана, в котором происходит заказ
     * @param restaurant ресторан, в коором происходит заказ
     */
    public boolean createOrder(Visitor visitor, RestaurantService restaurantService, Restaurant restaurant) {
        try {
            return createSpecifiedOrder(visitor, visitor.getWishes(), restaurantService, restaurant);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Сообщает ресторану, что заказ сделан и передаем ему этот заказ
     *
     * @param visitor           посетитель, делающий заказ
     * @param restaurantService сервис ресторана, обслуживающего посетителя
     * @param restaurant        ресторан, в котором был делается заказ
     */
    public void makeOrder(Visitor visitor, RestaurantService restaurantService, Restaurant restaurant) {
        restaurantService.handleOrder(restaurant, visitor.getOrder(), this);
    }


    /**
     * Попытка посетителя заказать блюдо.
     *
     * @param visitor           посетитель, заказывающий блюдо
     * @param dish              блюдо, заказываемое посетителем
     * @param restaurantService сервис ресторана, обслуживающий клиента
     * @param restaurant        ресторан, в котором происходит заказ
     * @return true, если блюдо успешно заказано, а иначе false
     */
    public boolean tryToAddDishToOrder(Visitor visitor, Dish dish, RestaurantService restaurantService, Restaurant restaurant) {
        // может ли посетитель себе позволить блюдо?
        if (!canVisitorOrderDish(visitor, dish, restaurant)) {
            return false;
        }
        // может ли ресторан себе позволить блюдо?
        if (!restaurantService.isRestaurantAvailableToCookDishRightNow(restaurant, dish)) {
            return false;
        }

        addDishToOrder(visitor, dish);
        return true;
    }

    /**
     * Проверяет возможность заказа посетителем определенного блюда. В проверку включена цена, свободное время,
     * ограничения посетителя. Если денег не хватает, запускается алгоритм поиска наборов блюд со скидкой в заказе.
     *
     * @param visitor    посетитель, пытающийся заказать блюдо
     * @param dish       блюдо, заказываемое посетителем
     * @param restaurant ресторан, в котором происходит попытка заказа
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
     *
     * @param visitor посетитель, который составляет себе заказ
     * @param dish    блюдо, которое будет добавлено в заказ
     */
    private void addDishToOrder(Visitor visitor, Dish dish) {
        Order order = visitor.getOrder();

        order.getOrderedDishes().add(dish);
        order.setTotalPrice(order.getTotalPrice() + dish.getBasicPrice());
    }

    /**
     * Спрашивает у клиента, готов ли тот подождать дополнительное время для чего то. например, в случае, если блюдо
     * не удается приготовить в срок, клиент может подождать еще немнеого времени
     * @param visitor посетитель, у которого "задают вопрос"
     * @param timeFrom время, которое клиент уже готов был потратить
     * @param additionalTime дополнительное время поверх timeFrom
     * @return true, если клиент соглашается подождать еще additionalTime, false иначе
     */
    public boolean isReadyToWaitAdditionalTime(Visitor visitor, int timeFrom, int additionalTime) {
        return timeFrom + additionalTime <= visitor.getFreeTime();
    }

    /**
     * Метод проверяет, есть ли в заказе клиента блюда, образующие комбинацию. Если таковая имеется,
     * изменяется цена за комбинацию.
     *
     * @param visitor    посетитель, для которого будут искаться скидки
     * @param restaurant ресторан, в котором происходит заказ
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
