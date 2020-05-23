package pl.com.muca.restaurants.restaurant;

import java.util.function.Supplier;

public class BufferInfo {

  private final Supplier<Integer> getHowManyOrdersInBuffer;
  private final int totalCapacity;

  public BufferInfo(Supplier<Integer> getHowManyOrdersInBuffer,
      int totalCapacity) {
    this.getHowManyOrdersInBuffer = getHowManyOrdersInBuffer;
    this.totalCapacity = totalCapacity;
  }

  public int size() {
    return getHowManyOrdersInBuffer.get();
  }

  public int getTotalCapacity() {
    return totalCapacity;
  }

  public boolean isFull(){
    return getHowManyOrdersInBuffer.get() == totalCapacity;
  }

  public boolean isEmpty() {
    return getHowManyOrdersInBuffer.get() == 0;
  }
}
