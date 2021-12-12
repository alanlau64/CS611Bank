import java.util.ArrayList;
import java.util.Date;

public class LoanActivity implements HasDate, HasName, HasID{
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

    @Override
    public Date getDate() {
        return getTime();
    }

    @Override
    public ArrayList<Integer> getIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(loan.getLoanNum());
        return ids;
    }

    @Override
    public String getName() {
        return loan.getUserName();
    }
}
