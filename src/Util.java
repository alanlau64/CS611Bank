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

    public <T extends HasDate> ArrayList<T> filterLogByDate(ArrayList<T> logList, Date date){
        ArrayList<T> result = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int requiredDate = calendar.get(Calendar.DATE);
        for(T log : logList){
            calendar.setTime(log.getDate());
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
