package pl.com.muca.restaurants.restaurant.order;

import java.util.List;

public class OrderMaker  {
  private static int idCounter = 0;

  public static Order createNewOrder(List<String> dishes){
    return new Order(dishes, ++idCounter);}
}
