import java.util.ArrayList;
import java.util.Date;

// Template of stock transaction record.
public class StockTrade implements HasDate, HasName, HasID{
    private Date time;
    private int accountNo;
    private String stockName;
    private int price;
    private int number;
    private String activity;

    // Different constructors for different scenarios.
    public StockTrade () {}
    public StockTrade (Date time, Customer user, Stock stock, int number, String activity) {
        this(time, user, stock.getName(), stock.getPrice(), number, activity);
    }
    public StockTrade (Date time, Customer user, String stockName, int price, int number, String activity) {
        this(time, user.getSecuritiesAccount(), stockName, price, number, activity);
    }
    public StockTrade (Date time, SecuritiesAccount account, Stock stock, int number, String activity) {
        this(time, account, stock.getName(), stock.getPrice(), number, activity);
    }
    public StockTrade (Date time, SecuritiesAccount account, String stockName, int price, int number, String activity) {
        this(time, account.getAccountNum(), stockName, price, number, activity);
    }
    public StockTrade (Date time, int accountNo, Stock stock, int number, String activity) {
        this(time, accountNo, stock.getName(), stock.getPrice(), number, activity);
    }
    public StockTrade (Date time, int accountNo, String stockName, int price, int number, String activity) {
        this.time = time;
        this.accountNo = accountNo;
        this.stockName = stockName;
        this.price = price;
        this.number = number;
        if (activity.equalsIgnoreCase("buy") || activity.equalsIgnoreCase("sell"))
            this.activity = activity;
        else
            this.activity = "error";
    }


    // Getter methods.
    public Date getTime() {
        return time;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }

    public String getStockName() {
        return stockName;
    }

    public String getActivity() {
        return activity;
    }

    @Override
    public Date getDate() {
        return getTime();
    }

    @Override
    public ArrayList<Integer> getIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(getAccountNo());
        return ids;
    }

    @Override
    public String getName() {
        return getStockName();
    }
}
