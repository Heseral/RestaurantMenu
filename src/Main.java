import restaurant.Restaurant;
import restaurant.RestaurantService;
import util.GlobalVar;
import visitor.Visitor;
import visitor.VisitorService;

import java.util.Date;
import java.util.TimerTask;

/*
    9. Меню ресторана, скидки. Дано меню ресторана, которое содержит набор
    различных блюд - напитки, салаты, закуски и т.д. Каждое блюдо имеет
    стоимость, время приготовления, категорию, ограничения. Для некоторых
    сочетаний блюд могут быть даны скидки. Посетители ресторана могут
    заказывать блюда исходя из своих пожеланий, наличия свободного времени
    и денежных средств.
 */
public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        RestaurantService restaurantService = new RestaurantService();

        restaurantService.setDefaultRestaurantMenu(restaurant);
        restaurantService.setDefaultRestaurantCombinationsSales(restaurant);
        restaurantService.setDefaultRestaurantIngredientsRandomly(restaurant);

        VisitorService visitorService = new VisitorService();

        GlobalVar.TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(">>>>> " + GlobalVar.time++);
            }
        }, 0, 1000);
        GlobalVar.TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                Visitor visitor = new Visitor();
                visitorService.createAbsolutelyRandomOrder(visitor, restaurantService, restaurant);
                System.out.println("      NEW ORDER: " + visitor + " сделал новый заказ.");
            }
        }, 0, 15 * 1000);
        GlobalVar.TIMER.schedule(new TimerTask() {
            @Override
            public void run() {
                restaurantService.resupplyIngredientsRandomly(restaurant);
                System.out.println("      RESUPPLY: новые ингредиенты были поставлены в ресторан.");
            }
        }, 120 * 1000, 120 * 1000);
    }
}
