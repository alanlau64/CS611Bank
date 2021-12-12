import com.google.gson.*;
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
    public void createLog (ArrayList customers) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Withdraw.class, new WithdrawSerializer());
        builder.registerTypeAdapter(Deposit.class, new DepositSerializer());
        builder.registerTypeAdapter(Transfer.class, new TransferSerializer());
        Gson gson = builder.create();
        Type type = new TypeToken<ArrayList<Customer>>(){}.getType();
        this.content = gson.toJson(customers, type);
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
        builder.registerTypeAdapter(Deposit.class, new DepositDeserializer());
        builder.registerTypeAdapter(Withdraw.class, new WithdrawDeserializer());
        builder.registerTypeAdapter(Transfer.class, new TransferDeserializer());
        builder.registerTypeAdapter(Stock.class, new StockDeserializer());
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
            Type type = new TypeToken<ArrayList<Customer>>(){}.getType();
            customers = gson.fromJson(json, type);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private class DepositSerializer implements JsonSerializer<Deposit> {
        public JsonElement serialize (Deposit src, Type typeOfSrc, JsonSerializationContext context)
            throws JsonParseException {
            return new JsonPrimitive(src.getClass().toString());
        }
    }

    private class WithdrawSerializer implements JsonSerializer<Withdraw> {
        public JsonElement serialize (Withdraw src, Type typeOfSrc, JsonSerializationContext context)
                throws JsonParseException {
            return new JsonPrimitive(src.getClass().toString());
        }
    }

    private class TransferSerializer implements JsonSerializer<Transfer> {
        public JsonElement serialize (Transfer src, Type typeOfSrc, JsonSerializationContext context)
                throws JsonParseException {
            return new JsonPrimitive(src.getClass().toString());
        }
    }

    private class DepositDeserializer implements JsonDeserializer<Deposit> {
        public Deposit deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
            if (json.getAsString().contains("DepositWithTransactionFee"))
                return new DepositWithTransactionFee();
            else if (json.getAsString().contains("DepositWithoutTransactionFee"))
                return new DepositWithoutTransactionFee();
            else
                return null;
        }
    }

    private class TransferDeserializer implements JsonDeserializer<Transfer> {
        public Transfer deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            if (json.getAsString().contains("TransferWithTransactionFee"))
                return new TransferWithTransactionFee();
            else if (json.getAsString().contains("TransferWithoutTransactionFee"))
                return new TransferWithoutTransactionFee();
            else
                return null;
        }
    }

    private class WithdrawDeserializer implements JsonDeserializer<Withdraw> {
        public Withdraw deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return new WithdrawWithTransactionFee();
        }
    }

    private class StockDeserializer implements JsonDeserializer<Stock> {
        public Stock deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
            for (Stock s : BankSystem.getAvailableStocks()) {
                if (s.getName().equals(json.getAsString()))
                    return s;
            }
            return null;
        }
    }
}
