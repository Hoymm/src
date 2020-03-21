package restaurants.restaurant;

public enum OrderState {
    PLACED("placed"),
    IS_BEING_PREPARED("being prepared"),
    READY_TO_PICKUP("ready to pickup"),
    PICKED_UP("picked up");

    private final String stringName;
    OrderState(String stringName) {
        this.stringName = stringName;
    }

    @Override
    public String toString() {
        return stringName;
    }
}
