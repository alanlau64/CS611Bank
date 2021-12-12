import java.util.Date;

public class AccountActivity {
    private String username;
    private int accountNo;
    private Date time;
    private String type;
    private String activity;

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
}
