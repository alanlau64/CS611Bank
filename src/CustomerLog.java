import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CustomerLog implements Log {
    private String content;
    private File logFile;

    public CustomerLog () {
        this.content = "";
        this.logFile = new File("customer.json");
    }

    @Override
    public void createLog (ArrayList managers) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Manager>>(){}.getType();
        this.content = gson.toJson(managers, type);
    }

    @Override
    public ArrayList<Manager> readLog() {
        ArrayList<Manager> managers = new ArrayList<>();
        return managers;
    }
}
