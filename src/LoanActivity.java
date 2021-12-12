import java.util.Date;

public class LoanActivity {
    private Date time;
    private Loan loan;
    private String activity;

    public LoanActivity () {}
    public LoanActivity (Date time, Loan loan, String activity) {
        this.time = time;
        this.loan = loan;
        if (activity.equalsIgnoreCase("request") || activity.equalsIgnoreCase("approve")
        || activity.equalsIgnoreCase("deny") || activity.equalsIgnoreCase("expire"))
            this.activity = activity;
        else
            this.activity = "error";
    }

    public String getActivity() {
        return activity;
    }

    public Date getTime() {
        return time;
    }

    public Loan getLoan() {
        return loan;
    }
}
