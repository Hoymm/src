package pl.com.muca.restaurants.restaurant;

import java.util.List;

class OrderMaker  {
  private static int idCounter = 0;

  static Order createNewOrder(List <String> dishes){
    return new Order(dishes, ++idCounter);}
}
