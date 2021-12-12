import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class ManagerStockView extends JFrame implements ActionListener {

    private Manager manager;

    private Container container;

    private JLabel stockMarket;
    private JComboBox<String> availableStocks;
    private JLabel stockPrice;
    private JLabel changePriceLabel;

    private JFormattedTextField newPrice;

    private JButton changePrice;
    private JTextField stockName;
    private JFormattedTextField price;
    private JButton addStock;
    private JButton removeStock;
    private JButton back;

    public ManagerStockView(Manager manager) {
        this.manager = manager;
        this.container = getContentPane();

        stockMarket = new JLabel("Stock Market: ");
        availableStocks = new JComboBox<>(BankSystem.getAvailableStockNames().toArray(String[]::new));
        stockPrice = new JLabel();
        changePriceLabel = new JLabel("Enter new price: ");

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);

        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Double.class);
        numberFormatter.setAllowsInvalid(false);
        newPrice = new JFormattedTextField(numberFormatter);
        price = new JFormattedTextField(numberFormatter);
        stockName = new JTextField("Enter stock name here");

        changePrice = new JButton("Change stock price");
        addStock = new JButton("Add stock");
        removeStock = new JButton("Remove stock");
        back = new JButton("Back");
        this.addWindowListener(BankSystem.close());
    }

    public void showPage() {
        container.setLayout(null);

        stockMarket.setBounds(50, 100, 150, 30);
        availableStocks.setBounds(50, 150, 150, 30);
        stockPrice.setBounds(50, 200, 150, 30);
        removeStock.setBounds(50, 250, 150, 30);
        newPrice.setBounds(150, 300, 150, 30);
        changePriceLabel.setBounds(50, 300, 250, 30);
        changePrice.setBounds(50, 350, 150, 30);

        stockName.setBounds(50, 450, 150, 30);
        price.setBounds(50, 500, 150, 30);
        addStock.setBounds(50, 550, 150, 30);
        back.setBounds(50, 600, 150, 30);

        container.add(stockPrice);
        container.add(stockMarket);
        container.add(stockName);
        container.add(availableStocks);
        container.add(removeStock);
        container.add(newPrice);
        container.add(changePrice);
        container.add(stockName);
        container.add(price);
        container.add(addStock);
        container.add(back);
        container.add(changePriceLabel);

        removeStock.addActionListener(this);
        addStock.addActionListener(this);
        back.addActionListener(this);
        availableStocks.addActionListener(this);
        changePrice.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == availableStocks) {

            for (Stock stock : BankSystem.getAvailableStocks()) {
                if (availableStocks.getSelectedItem().equals(stock.getName())) {
                    stockPrice.setText("Price: " + stock.getPrice());
                }
            }

                this.setVisible(false);
                this.setVisible(true);
        }

        if(e.getSource() == changePrice) {

            if(availableStocks.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Please select the stock you wish to remove");
                this.setVisible(false);
                this.setVisible(true);
            }

            if(newPrice.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter the new value of the stock");
                this.setVisible(false);
                this.setVisible(true);
            }

            for(Stock stock : BankSystem.getAvailableStocks()) {
                if(availableStocks.getSelectedItem().equals(stock.getName())) {
                    int newStockPrice = Integer.parseInt(newPrice.getText());

                    if (newStockPrice > stock.getPrice()) {
                        manager.increaseStockPrice(stock, newStockPrice - stock.getPrice());
                        JOptionPane.showMessageDialog(this, "Price increased");
                    } else if (newStockPrice < stock.getPrice()) {
                        manager.decreaseStockPrice(stock, stock.getPrice() - newStockPrice);
                        JOptionPane.showMessageDialog(this, "Price decreased");
                    } else {
                        JOptionPane.showMessageDialog(this, "Entered value is the same as current value.");
                    }
                }
            }

        }

        if(e.getSource() == removeStock) {
            if(availableStocks.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Please select the stock you wish to remove");
                this.setVisible(false);
                this.setVisible(true);
            }

            for(Stock stock : BankSystem.getAvailableStocks()) {
                if (availableStocks.getSelectedItem().equals(stock.getName())) {
                    if (manager.deleteStock(BankSystem.getAvailableStocks(), stock.getName())) {
                        JOptionPane.showMessageDialog(this, "Stock removed successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Stock could not be removed.");
                    }
                }
            }
        }

        if(e.getSource() == addStock) {

            if(stockName.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter the name of the stock you wish to add");
                this.setVisible(false);
                this.setVisible(true);
            }

            if(price.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter the value of the stock you wish to add");
                this.setVisible(false);
                this.setVisible(true);
            }

            if(manager.createStock(BankSystem.getAvailableStocks(), stockName.getText(), Integer.parseInt(price.getText()))) {
                JOptionPane.showMessageDialog(this, "Stock created successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Stock created successfully");
            }
        }

        if (e.getSource() == back) {
            ManagerHomePage frame = new ManagerHomePage(manager);
            frame.setTitle("Manager home page");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        }
    }
}
