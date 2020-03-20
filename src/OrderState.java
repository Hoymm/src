public enum OrderState {
    PLACED{
        @Override
        public String toString() {
            return "placed";
        }
    },
    IS_BEING_PREPARED{
        @Override
        public String toString() {
            return "being prepared";
        }
    },
    READY_TO_PICKUP{
        @Override
        public String toString() {
            return "ready to pickup";
        }
    },
    PICKED_UP {
        @Override
        public String toString() {
            return "picked up";
        }
    }


}
