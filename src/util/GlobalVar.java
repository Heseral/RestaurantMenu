package util;

import restaurant.food.dish.Dish;
import restaurant.food.dish.DishService;
import restaurant.Restaurant;
import restaurant.RestaurantService;
import visitor.VisitorService;

import java.util.ArrayList;
import java.util.List;

/*
    Раз статичных методов у сервисов мне не создать, придется сохранять сервисы в глобальные переменные.
    Все еще не понимаю чего от меня хотят, чем вообще плоха классическая модель ООП?
*/
public class GlobalVar {
    public static Restaurant restaurant = new Restaurant();

    public static List<Dish> basicDish = new ArrayList<>();

    public static DishService dishService = new DishService();
    public static RestaurantService restaurantService = new RestaurantService();
    public static VisitorService visitorService = new VisitorService();
}
