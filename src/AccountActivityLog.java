import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;


// Save and load account activity records including opening and closing.
public class AccountActivityLog implements Log {
    private String content;
    private File logFile;

    public AccountActivityLog () {
        this.content = "";
        this.logFile = new File("accountActivity.json");
    }

    //Update log file.
    @Override
    public void createLog (ArrayList transactions) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateSerializer());
        Gson gson = builder.create();
        Type type = new TypeToken<ArrayList<AccountActivity>>(){}.getType();
        this.content = gson.toJson(transactions, type);
        try {
            this.logFile.createNewFile();
            FileWriter writer = new FileWriter(logFile);
            writer.write(this.content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Read from log file.
    @Override
    public ArrayList<AccountActivity> readLog() {
        ArrayList<AccountActivity> transactions = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateDeserializer());
        Gson gson = builder.create();
        try {
            if (this.logFile.createNewFile()) {
                FileWriter writer = new FileWriter(logFile);
                writer.write("[]");
                writer.close();
            }
            BufferedReader reader = new BufferedReader(new FileReader(this.logFile));
            StringBuilder str = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                str.append(line);
                str.append(ls);
            }
            String json = str.toString();
            Type type = new TypeToken<ArrayList<AccountActivity>>(){}.getType();
            transactions = gson.fromJson(json, type);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    //Serialize Date class to simpler format.
    private class DateSerializer implements JsonSerializer<Date> {
        @Override
        public JsonElement serialize (Date src, Type typeOfSec, JsonSerializationContext context)
                throws JsonParseException {
            return new JsonPrimitive(src.getTime());
        }
    }

    //Deserialize Date elements serialized above.
    private class DateDeserializer implements JsonDeserializer<Date> {
        @Override
        public Date deserialize (JsonElement json, Type typeOfSrc, JsonDeserializationContext context)
                throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
    }
}
