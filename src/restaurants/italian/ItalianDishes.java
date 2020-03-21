package restaurants.italian;

enum ItalianDishes {
  PANZENELLA("Panzenella"),
  BRUSCHETTA("Bruschetta"),
  FOCACCIA_BREAD("Focaccia Bread"),
  PASTA_CARBONARA("Pasta Carbonara"),
  MARGHERITA_PIZZA("Margherita Pizza"),
  MUSHROOM_RISOTTO("Mushroom Risotto");

  private final String stringName;

  ItalianDishes(String stringName) {
    this.stringName = stringName;
  }

  @Override
  public String toString() {
    return this.stringName;
  }
}
