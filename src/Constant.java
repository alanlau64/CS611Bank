import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

//contain all the constant may used in the code
public class Constant {
    public static int MAX_ACCOUNT_NUMBER = 10000000;

    public static int MAX_LOAN_NUMBER = 100000000;

    public static Date CURRENT_TIME = new Date();

    public final static Double OPEN_SECURITIES_THRESHOLD = 5000.0;

    public final static Double TRANSACTION_FEE = 10.0;

    public final static Double SAVING_INTEREST_THRESHOLD = 1000.0;

    public final static Double SAVING_INTEREST = 0.01;

    public final static Double LOAN_INTEREST = 0.01;

    public static void readConfig () {
        try {
            File file = new File("config");
            file.createNewFile();
            Scanner in = new Scanner(file);
            if(in.hasNext()) {
                String line = in.nextLine();
                String[] args = line.split(" ");
                if (args.length < 3) {
                    return;
                }
                MAX_ACCOUNT_NUMBER = Integer.parseInt(args[0]);
                MAX_LOAN_NUMBER = Integer.parseInt(args[1]);
                CURRENT_TIME = new Date(Long.parseLong(args[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeConfig () {
        try {
            File file = new File("config");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(MAX_ACCOUNT_NUMBER + " " + MAX_LOAN_NUMBER + " " + CURRENT_TIME.getTime());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
