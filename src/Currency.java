public enum Currency {
    USD{
        @Override
        public String toString() {
            return "\u0024";
        }
    },
    CNY{
        @Override
        public String toString() {
            return "\u00A5";
        }
    },
    INR{
        @Override
        public String toString() {
            return "\u20B9";
        }
    }
}
