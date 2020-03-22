package restaurants.restaurant;

import java.util.List;

interface OrderInfo <Dishes extends Enum<Dishes>> {
    int getId();
    List<Dishes> getDishes();
}
