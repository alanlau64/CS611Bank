public class Yuan extends Currency{
    public Yuan(){
        this(0);
    }

    public Yuan(int amount){
        super("CNY", amount);
    }
}
