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
    private JLabel transactionLabel;
    private JLabel loanLabel;
    private JLabel accountLabel;
    private JLabel stockLabel;
    private JButton back;

    public DailyReportView(Manager manager) {
        this.manager = manager;
        container = getContentPane();

        transactionLabel = new JLabel("Transaction Report");
        loanLabel = new JLabel("Loan Report");
        accountLabel = new JLabel("Account Report");
        stockLabel = new JLabel("Stock Report");

        ArrayList<Transaction> transactions = BankSystem.getTransactions();
        Date date = Constant.CURRENT_TIME;

        Util util = new Util();
        ArrayList<Transaction> filteredTransactions = util.filterLogByDate(transactions, date);
        ArrayList<LoanActivity> filteredLoans = util.filterLogByDate(new LoanActivityLog().readLog(), date);
        ArrayList<AccountActivity> accountActivities = util.filterLogByDate(new AccountActivityLog().readLog(), date);
        ArrayList<StockTrade> stockTrades = util.filterLogByDate(new StockTradeLog().readLog(), date);

//        ArrayList<Transaction> filteredTransactions = BankSystem.getTransactions();
//        ArrayList<LoanActivity> filteredLoans = BankSystem.getLoanActivities();
//        ArrayList<AccountActivity> accountActivities = BankSystem.getAccountActivities();
//        ArrayList<StockTrade> stockTrades = BankSystem.getStockTrades();

        String[][] data = new String[filteredTransactions.size()][6];

        for(int i = 0; i < filteredTransactions.size(); i++) {
            Transaction transaction = filteredTransactions.get(i);
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
        this.addWindowListener(BankSystem.close());
    }

    public void showPage() {
        container.setLayout(null);

        transactionLabel.setBounds(0, 0, 150, 30);
        scrollPane.setBounds(0, 40, 370, 150);
        loanLabel.setBounds(0, 190, 150, 30);
        loanPane.setBounds(0, 230,370, 150);
        accountLabel.setBounds(0, 370, 150, 30);
        accountPane.setBounds(0, 400, 370, 150);
        stockLabel.setBounds(0, 570, 150, 30);
        stockPane.setBounds(0, 600, 370, 100);

        back.setBounds(50, 800, 150, 30);

        container.add(transactionLabel);
        container.add(loanLabel);
        container.add(stockLabel);
        container.add(accountLabel);
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
