import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class LoanActivityLog implements Log {
    private String content;
    private File logFile;

    public LoanActivityLog () {
        this.content = "";
        this.logFile = new File("loanActivity.json");
    }
    @Override
    public void createLog (ArrayList transactions) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Withdraw.class, new LoanActivityLog.WithdrawSerializer());
        builder.registerTypeAdapter(Deposit.class, new LoanActivityLog.DepositSerializer());
        builder.registerTypeAdapter(Transfer.class, new LoanActivityLog.TransferSerializer());
        builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateSerializer());
        Gson gson = builder.create();
        Type type = new TypeToken<ArrayList<LoanActivity>>(){}.getType();
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

    @Override
    public ArrayList<LoanActivity> readLog() {
        ArrayList<LoanActivity> transactions = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateDeserializer());
        builder.registerTypeAdapter(Deposit.class, new LoanActivityLog.DepositDeserializer());
        builder.registerTypeAdapter(Withdraw.class, new LoanActivityLog.WithdrawDeserializer());
        builder.registerTypeAdapter(Transfer.class, new LoanActivityLog.TransferDeserializer());
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
            Type type = new TypeToken<ArrayList<LoanActivity>>(){}.getType();
            transactions = gson.fromJson(json, type);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    private class DateSerializer implements JsonSerializer<Date> {
        @Override
        public JsonElement serialize (Date src, Type typeOfSec, JsonSerializationContext context)
                throws JsonParseException {
            return new JsonPrimitive(src.getTime());
        }
    }

    private class DateDeserializer implements JsonDeserializer<Date> {
        @Override
        public Date deserialize (JsonElement json, Type typeOfSrc, JsonDeserializationContext context)
                throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong());
        }
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
}
