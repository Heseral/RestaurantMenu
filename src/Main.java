import restaurant.Restaurant;
import restaurant.RestaurantService;

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

        restaurantService.fillDefaultRestaurantMenu(restaurant);
        restaurantService.fillDefaultRestaurantCombinationsSales(restaurant);
    }
}
