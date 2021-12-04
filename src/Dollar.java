public class Dollar extends Currency{
    public Dollar(){
        this(0);
    }

    public Dollar(int amount){
        super("USD", amount);
    }
}
