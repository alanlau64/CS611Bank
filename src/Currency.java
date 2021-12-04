public class Currency {
    private String name;
    private int amount;

    public Currency(){}

    public Currency(String name){
        this(name,0);
    }

    public Currency(String name, int amount){
        this.name = name;
        this.amount = amount;
    }

    public int add(int amount){
        this.amount+=amount;
        return this.amount;
    }

    public int subtract(int amount){
        if(this.amount>=amount){
            this.amount-=amount;
            return this.amount;
        }
        else return -1;
    }
}
