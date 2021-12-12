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
    private JScrollPane scrollPane;
    private JButton back;

    public DailyReportView(Manager manager) {
        this.manager = manager;
        container = getContentPane();

        ArrayList<Transaction> transactions = BankSystem.getTransactions();
        Date date = new Date();
        ArrayList<Transaction> filteredTransactions = Util.filterTransactionLogByDate(transactions, date);

        String data [][] = new String[filteredTransactions.size()][6];

        for(int i = 0; i < filteredTransactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            data[i][0] = String.valueOf(transaction.getTime());
            data[i][1] = String.valueOf(transaction.getFromAccount());
            data[i][2] = transaction.getType();
            data[i][3] = String.valueOf(transaction.getAmount());
            data[i][4] = String.valueOf(transaction.getCurrency());
            data[i][5] = String.valueOf(transaction.getToAccount());
        }

        String column[]={"TIME","FROM ACCOUNT","TYPE", "AMOUNT", "CURRENCY", "TO ACCOUNT"};

        table = new JTable(data, column);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        back = new JButton("Back");

    }

    public void showPage() {
        container.setLayout(null);

        scrollPane.setBounds(0, 0, 370, 500);
        back.setBounds(50, 550, 150, 30);

        container.add(scrollPane);
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
