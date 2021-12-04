import com.google.gson.Gson;
import java.lang.reflect.Type;

public class Utils {
    private static Gson gson = new Gson();

    // Convert an object to json string.
    // TODO: save the string in a file. 
    public static String phaseJson (Object o) {
        String json = gson.toJson(o);
        return json;
    }

    // Read a json string and return the corresponding object.
    // TODO: read from a file and return the object contained in the file.
    public static Object readJson (String json, Type type) {
        Object o = gson.fromJson(json, type);
        return o;
    }
}
