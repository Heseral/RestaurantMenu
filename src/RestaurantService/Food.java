package RestaurantService;

import Util.Pair;

import java.util.List;

public class Food {
    // название еды
    String name;
    // комбинации еды, с которыми будет скидка. Это список комбинаций, состоящий из пары "Набор, который нужен
    // для скидки" и сама скидка
    List<Pair<List<Food>, Integer>> combinationsSale;
    // базовая цена блюда без скидки
    int basicPrice;
    // битово-флаговый тип еды: может быть острым и горячим, алкогольным и молочным и т. д.
    int type;
    // категория еды: суп, закуска и т. д.
    FoodCategory foodCategory;
    // время, необходимое для приготовления блюда
    int timeToCook;
}
