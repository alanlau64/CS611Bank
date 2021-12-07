import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserLog implements Log{
    @Override
    public String createLog (ArrayList users) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        String json = gson.toJson(users, type);
        return json;
    }
}


