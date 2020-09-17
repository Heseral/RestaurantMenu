package util;

import restaurant.Restaurant;
import restaurant.RestaurantService;
import restaurant.food.dish.DishService;
import visitor.VisitorService;

public class GlobalVar {
    public final static Restaurant RESTAURANT = new Restaurant();

    // TODO: DELIT DIS!!!!!!!!!! внести все это как локальные переменный в Main
    public final static DishService DISH_SERVICE = new DishService();
    public final static RestaurantService RESTAURANT_SERVICE = new RestaurantService();
    public final static VisitorService VISITOR_SERVICE = new VisitorService();
    // конец туду

    // по крайней мере нужно время на то чтобы официанту донести заказ
    public final static int COOK_TIME_MINIMUM = 1;
    // откуда я взял эти цифры? Очевидно, среднепотолочные значения
    public final static int COOK_TIME_LOW = 5;
    public final static int COOK_TIME_DEFAULT = 15;
    public final static int COOK_TIME_LONG = 30;
    // не будем мучать клиентов ожиданиями выше этого значения. И так ждать абстрактный час, ужас!
    public final static int COOK_TIME_MAXIMUM = 60;
}
