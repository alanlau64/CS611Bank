public enum Currency {
    USD{
        public String display() {
            return "\u0024";
        }
    },
    CNY{
        public String display() {
            return "\u00A5";
        }
    },
    INR{
        public String display() {
            return "\u20B9";
        }
    }
}
