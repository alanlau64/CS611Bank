import java.util.ArrayList;
import java.util.Date;

// Template of account activity record.
public class AccountActivity implements HasDate, HasName, HasID{
    private String username;
    private int accountNo;
    private Date time;
    private String type;
    private String activity;

    //Two different constructor to fit different occasions.
    public AccountActivity () {}
    public AccountActivity (Customer user, int accountNo, Date time, String type, String activity) {
        this(user.getUsername(), accountNo, time, type, activity);
    }
    public AccountActivity (String username, int accountNo, Date time, String type, String activity) {
        this.username = username;
        this.time = time;
        this.accountNo = accountNo;
        if (type.equalsIgnoreCase("saving") || type.equalsIgnoreCase("checking")
        || type.equalsIgnoreCase("security"))
            this.type = type;
        else
            this.type = "error";
        if (activity.equalsIgnoreCase("open") || activity.equalsIgnoreCase("close"))
            this.activity = activity;
        else
            this.activity = "error";
    }

    //Getter methods.
    public String getUsername() {
        return username;
    }

    public Date getTime() {
        return time;
    }

    public String getActivity() {
        return activity;
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
        ids.add(accountNo);
        return ids;
    }

    @Override
    public String getName() {
        return getUsername();
    }
}
