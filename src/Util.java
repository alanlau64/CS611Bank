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
        for(Object log : logList){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(((HasDate) log).getDate());
            int day = calendar.get(Calendar.DATE);
            calendar.setTime(date);
            int currentDay = calendar.get(Calendar.DATE);
            if(day == currentDay)
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
