import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
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
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type type = new TypeToken<ArrayList<Manager>>(){}.getType();
        this.content = gson.toJson(managers, type);
        try {
            this.logFile.createNewFile();
            FileWriter writer = new FileWriter(logFile);
            writer.write(this.content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Customer> readLog() {
        ArrayList<Customer> customers = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try {
            this.logFile.createNewFile();
            BufferedReader reader = new BufferedReader(new FileReader(this.logFile));
            StringBuilder str = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                str.append(line);
                str.append(ls);
            }
            String json = str.toString();
            Type type = new TypeToken<ArrayList<Manager>>(){}.getType();
            customers = gson.fromJson(json, type);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
