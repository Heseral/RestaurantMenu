package restaurant.food;

/**
 * Битовые флаги для еды, так как она может быть одновременно, например, холодной, жидкой и алкогольной.
 *
 * Отсутсвие некоторых флагов будет говорить о том, что еда с противоположным отсутствующему флагу качеством.
 * Справа от флага кратко описан противоположный флаг, который подразумевается по умолчанию, если флаг слева отсутсвует.
 *
 * Не создавайте больше 32 флагов, а лучше вообще 31! int вмещает 32 бита, причем старший - знаковый.
 */
public abstract class FoodType {
    public static final int MILKY       = 1;
    public static final int SPICY       = 1 << 1;
    public static final int HOT         = 1 << 2; // COLD или ~комнатная температура(включая немного теплую)
    public static final int COLD        = 1 << 3; // HOT или ~комнатная температура(включая немного теплую)
    public static final int ALCOHOLIC   = 1 << 4;
    public static final int LIQUID      = 1 << 5; // SOLID или еда мягкая
    public static final int SOLID       = 1 << 6; // LIQUID или еда мягкая
}
