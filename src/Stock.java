public class Stock {
    private String name;
    private Dollar price;

    public Stock(){}

    public Stock(String name){
        this(name,0);
    }

    public Stock(String name, int price){
        this.name = name;
        this.price = new Dollar(price);
    }

    public int increase(int amount){
        return this.price.add(amount);
    }

    public int decrease(int amount){
        return this.price.subtract(amount);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Stock)obj).getName());
    }
}
