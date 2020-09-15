package RestaurantService;

public abstract class FoodType {
    public static final int MILKY = 1;
    public static final int SPICY =  1 << 1;
    public static final int HOT = 1 << 2;
    public static final int COLD = 1 << 3;
    public static final int ALCOHOLIC = 1 << 4;
    public static final int LIQUID = 1 << 5;
}
