package restaurants.restaurant;

import java.util.List;

public interface RestaurantClientApi {
    List getDishesList();
    OrderInfo makeOrder(List dishes);
    void retrieveOrder(OrderInfo orderInfo);
}
