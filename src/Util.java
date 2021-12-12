import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {
    public Util(){}

    public <T extends HasName> ArrayList<T> filterLogByName(ArrayList<T> logList, String Name){
        ArrayList<T> result = new ArrayList<>();
        for(T log : logList){
            if(log.getName().equals(Name))
                result.add(log);
        }
        return result;
    }

    public <T extends HasName> ArrayList<T> filterLogByDate(ArrayList<T> logList, Date date){
        ArrayList<T> result = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int requiredDate = calendar.get(Calendar.DATE);
        for(T log : logList){
            calendar.setTime(((HasDate) log).getDate());
            int day = calendar.get(Calendar.DATE);
            if(day == requiredDate)
                result.add(log);
        }
        return result;
    }

    public static ArrayList<Transaction> filterTransactionLogByDate(ArrayList<Transaction> logList, Date date){
        ArrayList<Transaction> result = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int requiredDate = calendar.get(Calendar.DATE);

        for(Transaction log : logList){
            calendar.setTime(((HasDate) log).getDate());
            int day = calendar.get(Calendar.DATE);
            if(day == requiredDate)
                result.add(log);
        }
        return result;
    }

    public static ArrayList<AccountActivity> filterAccountLogByDate(ArrayList<AccountActivity> logList, Date date){
        ArrayList<AccountActivity> result = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int requiredDate = calendar.get(Calendar.DATE);
        for(AccountActivity log : logList){
            calendar.setTime(((HasDate) log).getDate());
            int day = calendar.get(Calendar.DATE);
            if(day == requiredDate)
                result.add(log);
        }
        return result;
    }

    public static ArrayList<LoanActivity> filterLoanLogByDate(ArrayList<LoanActivity> logList, Date date){
        ArrayList<LoanActivity> result = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int requiredDate = calendar.get(Calendar.DATE);
        for(LoanActivity log : logList){
            calendar.setTime(((HasDate) log).getDate());
            int day = calendar.get(Calendar.DATE);
            if(day == requiredDate)
                result.add(log);
        }
        return result;
    }

    public static ArrayList<StockTrade> filterStockLogByDate(ArrayList<StockTrade> logList, Date date){
        ArrayList<StockTrade> result = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int requiredDate = calendar.get(Calendar.DATE);
        for(StockTrade log : logList){
            calendar.setTime(((HasDate) log).getDate());
            int day = calendar.get(Calendar.DATE);
            if(day == requiredDate)
                result.add(log);
        }
        return result;
    }

    public <T extends HasID> ArrayList<T> filterLogByID(ArrayList<T> logList, int ID){
        ArrayList<T> result = new ArrayList<>();
        for(T log : logList){
            if(log.getIDs().contains(ID))
                result.add(log);
        }
        return result;
    }
}
