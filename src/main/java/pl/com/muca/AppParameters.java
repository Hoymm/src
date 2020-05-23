package pl.com.muca;

import pl.com.muca.restaurants.RestaurantType;

public class AppParameters {
  private final int processingStationsNumber;
  private final int orderSimulatorsNumber;
  private final int ordersPerSimulator;
  private RestaurantType restaurantType;

  public AppParameters(Builder builder) {
    this.orderSimulatorsNumber = builder.orderSimulatorsNumber;
    this.ordersPerSimulator = builder.ordersPerSimulator;
    this.processingStationsNumber = builder.processingStationsNumber;
    this.restaurantType = builder.restaurantType;
  }

  public int getProcessingStationsNumber() {
    return processingStationsNumber;
  }

  public int getOrderSimulatorsNumber() {
    return orderSimulatorsNumber;
  }

  public int getOrdersPerSimulator() {
    return ordersPerSimulator;
  }

  public RestaurantType getRestaurantType() {
    return restaurantType;
  }

  static Builder builder(){
    return new Builder();
  }

  public static final class Builder {
    private int processingStationsNumber;
    private int orderSimulatorsNumber;
    private int ordersPerSimulator;
    private RestaurantType restaurantType;

    private Builder() {}

    public static Builder anAppParameters() {
      return new Builder();
    }

    public Builder setProcessingStationsNumber(
        int processingStationsNumber) {
      this.processingStationsNumber = processingStationsNumber;
      return this;
    }

    public Builder setOrderSimulatorsNumber(
        int orderSimulatorsNumber) {
      this.orderSimulatorsNumber = orderSimulatorsNumber;
      return this;
    }

    public Builder setOrdersPerSimulator(int ordersPerSimulator) {
      this.ordersPerSimulator = ordersPerSimulator;
      return this;
    }

    public Builder setRestaurantType(RestaurantType restaurantType) {
      this.restaurantType = restaurantType;
      return this;
    }

    public AppParameters build() {
      return new AppParameters(this);
    }
  }
}
