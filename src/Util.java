import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Util {
    public static ArrayList<Object> filterLogByName(ArrayList<Object> logList, String Name){
        ArrayList<Object> result = new ArrayList<>();
        for(Object log : logList){
            if(((HasName) log).getName().equals(Name))
                result.add(log);
        }
        return result;
    }

    public static ArrayList<Object> filterLogByDate(ArrayList<Object> logList, Date date){
        ArrayList<Object> result = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int requiredDate = calendar.get(Calendar.DATE);
        for(Object log : logList){
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

    public static ArrayList<Object> filterLogByID(ArrayList<Object> logList, int ID){
        ArrayList<Object> result = new ArrayList<>();
        for(Object log : logList){
            if(((HasID) log).getIDs().contains(ID))
                result.add(log);
        }
        return result;
    }
}
