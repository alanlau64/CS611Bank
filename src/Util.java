import java.util.*;

public class Util {
    public Util(){}

    public <T extends HasName> ArrayList<T> filterLogByName(ArrayList<T> logList, String Name){
        ArrayList<T> result = new ArrayList<>();
        for(T log : logList){
            if (log == null)
                continue;
            if(log.getName().equals(Name))
                result.add(log);
        }
        return result;
    }

    public <T extends HasDate> ArrayList<T> filterLogByDate(ArrayList<T> logList, Date date){
        ArrayList<T> result = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int requiredYear = calendar.get(Calendar.YEAR);
        int requiredMonth = calendar.get(Calendar.MONTH);
        int requiredDate = calendar.get(Calendar.DATE);
        for(T log : logList){
            if (log == null)
                continue;
            calendar.setTime(log.getDate());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DATE);
            if(day == requiredDate && month == requiredMonth && year == requiredYear)
                result.add(log);
        }
        return result;
    }

    public <T extends HasID> ArrayList<T> filterLogByID(ArrayList<T> logList, ArrayList<Integer> ids){
        ArrayList<T> result = new ArrayList<>();
        for(T log : logList){
            if (log == null)
                continue;
            if(!Collections.disjoint(log.getIDs(),ids))
                result.add(log);
        }
        return result;
    }
}
