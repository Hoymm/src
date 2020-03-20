public enum Dish {
    HAMBURGER{
        @Override
        public String toString() {
            return "hamburger";
        }
    },
    PIZZA{
        @Override
        public String toString() {
            return "pizza";
        }
    },
    CHIPS{
        @Override
        public String toString() {
            return "chips";
        }
    },
    SALAD{
        @Override
        public String toString() {
            return "salad";
        }
    },
    BAKED_CHEESE{
        @Override
        public String toString() {
            return "baked cheese";
        }
    };
}
