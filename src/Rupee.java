public class Rupee extends Currency{
    public Rupee(){
        this(0);
    }

    public Rupee(int amount){
        super("INR", amount);
    }
}
