package restaurants.restaurant;

import java.util.List;

interface OrderInfo <T extends Enum<T>> {
    int getId();
    List<T> getDishes();
}
