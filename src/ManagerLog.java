import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ManagerLog implements Log {
    private String content;
    private File logFile;

    public ManagerLog () {
        this.content = "";
        this.logFile = new File("manager.json");
    }
    @Override
    public void createLog (ArrayList managers) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        this.content = gson.toJson(managers, type);
    }

    @Override
    public ArrayList<Customer> readLog() {
        ArrayList<Customer> customers = new ArrayList<>();
        return customers;
    }
}
