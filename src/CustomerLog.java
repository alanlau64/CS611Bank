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
        builder.registerTypeAdapter(DepositWithTransactionFee.class, new CheckingDepositDeserializer());
        builder.registerTypeAdapter(DepositWithoutTransactionFee.class, new SavingDepositDeserializer());
        builder.registerTypeAdapter(WithdrawWithTransactionFee.class, new WithdrawDeserializer());
        builder.registerTypeAdapter(TransferWithTransactionFee.class, new CheckingTransferDeserializer());
        builder.registerTypeAdapter(TransferWithoutTransactionFee.class, new SavingTransferDeserializer());
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
            return null;
        }
    }

    private class WithdrawSerializer implements JsonSerializer<Withdraw> {
        public JsonElement serialize (Withdraw src, Type typeOfSrc, JsonSerializationContext context)
                throws JsonParseException {
            return null;
        }
    }

    private class TransferSerializer implements JsonSerializer<Transfer> {
        public JsonElement serialize (Transfer src, Type typeOfSrc, JsonSerializationContext context)
                throws JsonParseException {
            return null;
        }
    }

    private class CheckingDepositDeserializer implements JsonDeserializer<DepositWithTransactionFee> {
        public DepositWithTransactionFee deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
            return new DepositWithTransactionFee();
        }
    }

    private class SavingDepositDeserializer implements JsonDeserializer<DepositWithoutTransactionFee> {
        public DepositWithoutTransactionFee deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
            return new DepositWithoutTransactionFee();
        }
    }

    private class CheckingTransferDeserializer implements JsonDeserializer<TransferWithTransactionFee> {
        public TransferWithTransactionFee deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return new TransferWithTransactionFee();
        }
    }

    private class SavingTransferDeserializer implements JsonDeserializer<TransferWithoutTransactionFee> {
        public TransferWithoutTransactionFee deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return new TransferWithoutTransactionFee();
        }
    }

    private class WithdrawDeserializer implements JsonDeserializer<WithdrawWithTransactionFee> {
        public WithdrawWithTransactionFee deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return new WithdrawWithTransactionFee();
        }
    }
}
