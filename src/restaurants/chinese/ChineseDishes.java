package restaurants.chinese;

public enum ChineseDishes {
  SWEET_AND_SOUR("Sweet and sour"),
  SPRING_ROLL("Spring roll"),
  FRIED_SHRIMP("Fried shrimp"),
  CHOW_MEIN("Chow mein"),
  DUMPLING("Dumpling"),
  FRIED_RICE("Fried rice"),
  KUNG_PAO_CHICKEN("Kung Pao chicken");

  private final String stringName;

  ChineseDishes(String stringName) {
    this.stringName = stringName;
  }

  @Override
  public String toString() {
    return this.stringName;
  }
}
