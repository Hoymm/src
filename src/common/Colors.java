package common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Colors {
  GREEN("\033[0;32m"),
  YELLOW("\033[0;33m"),
  BLUE("\033[0;34m"),
  PURPLE("\033[0;35m"),
  CYAN("\033[0;36m"),
  WHITE("\033[0;37m"),
  RESET("\033[0m");

  private final String stringColor;
  private static int colorIdCounter = 0;
  private static final List<Colors> availableColors =
      Arrays.stream(Colors.values()).filter(c -> !c.equals(RESET)).collect(Collectors.toList());

  Colors(String stringColor) {
    this.stringColor = stringColor;
  }

  @Override
  public String toString() {
    return this.stringColor;
  }

  public static Colors getNextColor() {
    return availableColors.get(colorIdCounter++ % availableColors.size());
  }
}
