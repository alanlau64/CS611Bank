import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class DailyReportView extends JFrame implements ActionListener {

    private Manager manager;

    private Container container;

    private JTable table;
    private JTable loanTable;
    private JTable accountTable;
    private JTable stockTable;
    private JScrollPane scrollPane;
    private JScrollPane loanPane;
    private JScrollPane accountPane;
    private JScrollPane stockPane;
    private JButton back;

    public DailyReportView(Manager manager) {
        this.manager = manager;
        container = getContentPane();

        ArrayList<Transaction> transactions = BankSystem.getTransactions();
        Date date = new Date();
        /***
        ArrayList<Transaction> filteredTransactions = Util.filterTransactionLogByDate(transactions, date);
        ArrayList<LoanActivity> filteredLoans = Util.filterLoanLogByDate(new LoanActivityLog().readLog(), date);
        ArrayList<AccountActivity> accountActivities = Util.filterAccountLogByDate(new AccountActivityLog().readLog(), date);
        ArrayList<StockTrade> stockTrades = Util.filterStockLogByDate(new StockTradeLog().readLog(), date);
         ***/

        ArrayList<Transaction> filteredTransactions = BankSystem.getTransactions();
        ArrayList<LoanActivity> filteredLoans = BankSystem.getLoanActivities();
        ArrayList<AccountActivity> accountActivities = BankSystem.getAccountActivities();
        ArrayList<StockTrade> stockTrades = BankSystem.getStockTrades();

        String[][] data = new String[filteredTransactions.size()][6];

        for(int i = 0; i < filteredTransactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            data[i][0] = String.valueOf(transaction.getTime());
            data[i][1] = String.valueOf(transaction.getFromAccount());
            data[i][2] = transaction.getType();
            data[i][3] = String.valueOf(transaction.getAmount());
            data[i][4] = String.valueOf(transaction.getCurrency());
            data[i][5] = String.valueOf(transaction.getToAccount());
        }

        String[] column ={"TIME","FROM ACCOUNT","TYPE", "AMOUNT", "CURRENCY", "TO ACCOUNT"};

        table = new JTable(data, column);

        String[][] loanData = new String[filteredLoans.size()][4];

        for(int i = 0; i < filteredLoans.size(); i++) {
            LoanActivity transaction = filteredLoans.get(i);
            loanData[i][0] = String.valueOf(transaction.getDate());
            loanData[i][1] = String.valueOf(transaction.getLoan().getUserName());
            loanData[i][2] = String.valueOf(transaction.getLoan().getLoanNum());
            loanData[i][3] = String.valueOf(transaction.getLoan().getAmount());
        }

        String[] loanColumn = {"TIME","USER","LOAN NUMBER", "AMOUNT"};

        loanTable = new JTable(loanData, loanColumn);

        String[][] accountData = new String[accountActivities.size()][4];

        for(int i = 0; i < accountActivities.size(); i++) {
            AccountActivity transaction = accountActivities.get(i);
            accountData[i][0] = String.valueOf(transaction.getTime());
            accountData[i][1] = String.valueOf(transaction.getUsername());
            accountData[i][2] = String.valueOf(transaction.getActivity());
            accountData[i][3] = String.valueOf(transaction.getType());
        }

        String[] accountColumn = {"TIME","USER","ACTIVITY", "TYPE"};

        accountTable = new JTable(accountData, accountColumn);

        String[][] stockData = new String[stockTrades.size()][6];

        for(int i = 0; i < stockTrades.size(); i++) {
            StockTrade transaction = stockTrades.get(i);
            stockData[i][0] = String.valueOf(transaction.getDate());
            stockData[i][1] = String.valueOf(transaction.getAccountNo());
            stockData[i][2] = String.valueOf(transaction.getStockName());
            stockData[i][3] = String.valueOf(transaction.getPrice());
            stockData[i][4] = String.valueOf(transaction.getNumber());
            stockData[i][5] = String.valueOf(transaction.getActivity());
        }

        String[] stockColumn = {"TIME","ACCOUNT","STOCK", "PRICE", "NUMBER", "ACTIVITY"};

        stockTable = new JTable(stockData, stockColumn);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        loanTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        stockTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        accountTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        loanPane = new JScrollPane(loanTable);
        loanPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        loanPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        accountPane = new JScrollPane(accountTable);
        accountPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        accountPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        stockPane = new JScrollPane(stockTable);
        stockPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        stockPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        back = new JButton("Back");

    }

    public void showPage() {
        container.setLayout(null);

        scrollPane.setBounds(0, 0, 370, 150);
        loanPane.setBounds(0, 200,370, 150);
        accountPane.setBounds(0, 400, 370, 150);
        stockPane.setBounds(0, 600, 370, 100);

        back.setBounds(50, 800, 150, 30);

        container.add(scrollPane);
        container.add(loanPane);
        container.add(accountPane);
        container.add(stockPane);
        container.add(back);

        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back) {
            ManagerHomePage frame = new ManagerHomePage(manager);
            frame.setTitle(manager.getUsername() + "'s home page");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        }
    }
}
