public class Stock {
    private String name;
    private static Currency currency = Currency.USD;
    private int price;

    public Stock(){}

    public Stock(String name){
        this(name,0);
    }

    public Stock(String name, int price){
        this.name = name;
        this.price = price;
    }

    public int increase(int amount){
        this.price += amount;
        return this.price;
    }

    public int decrease(int amount){
        this.price -= amount;
        return this.price;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Stock)obj).getName());
    }
}
