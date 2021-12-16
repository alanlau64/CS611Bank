import java.util.ArrayList;
import java.util.Date;

// Template of transaction record of money.
public class Transaction implements HasDate, HasID{
    private Integer fromAccount;
    private Integer toAccount;
    private Date time;
    private Currency currency;
    private Double amount;
    private String type;

    public Transaction () {}
    public Transaction (Account from, Account to, Date time, Currency c, Double amount, String type) {
        this(from.getAccountNum(), to.getAccountNum(), time, c, amount, type);
    }
    public Transaction (Integer from, Integer to, Date time, Currency c, Double amount, String type) {
        this.fromAccount = from;
        if (type.equalsIgnoreCase("transfer"))
            this.toAccount = to;
        else
            this.toAccount = null;
        this.time = time;
        this.currency = c;
        this.amount = amount;
        this.type = type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Integer getFromAccount() {
        return fromAccount;
    }

    public Date getTime() {
        return time;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getToAccount() {
        return toAccount;
    }

    public String getType() {
        return type;
    }

    @Override
    public Date getDate() {
        return getTime();
    }

    @Override
    public ArrayList<Integer> getIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(getFromAccount());
        ids.add(getToAccount());
        return ids;
    }
}
