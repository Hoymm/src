package restaurants.restaurant;

import java.util.List;

public interface RestaurantClientApi<T extends Enum<T>> {
    List<T> getDishesList();
    OrderInfo makeOrder(List<T> dishes);
    void retrieveOrder(OrderInfo orderInfo);
}
