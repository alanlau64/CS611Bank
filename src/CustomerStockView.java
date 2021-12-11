import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerStockView extends JFrame implements ActionListener {

    private Customer customer;

    private Map<Stock, Integer> customerStocks;
    private SecuritiesAccount securitiesAccount;
    private SecuritiesAccountController securitiesAccountController;
    private ArrayList<Stock> stocks;

    private Container container;

    private JLabel ownedStocksLabel;
    private JComboBox<Stock> ownedStocks;
    private JLabel ownedAmount;
    private JLabel price;
    private JFormattedTextField sellAmount;
    private JButton sellStock;

    private JLabel stockMarket;
    private JComboBox<String> availableStocks;
    private JLabel stockPrice;
    private JFormattedTextField buyAmount;
    private JButton buyStock;

    private JButton back;

    public CustomerStockView(Customer customer) {
        this.container = getContentPane();
        this.customer = customer;
        this.securitiesAccount = customer.getSecuritiesAccount();
        this.securitiesAccountController = new SecuritiesAccountController(securitiesAccount);

        if(securitiesAccount == null) {
            customerStocks = new HashMap<>();
        } else {
            this.customerStocks = customer.getSecuritiesAccount().getStocks();
        }
        this.stocks = BankSystem.getAvailableStocks();

        ownedStocksLabel = new JLabel("Owned Stocks: ");
        ownedStocks = new JComboBox<>(customerStocks.keySet().toArray(new Stock[0]));
        ownedAmount = new JLabel();
        price = new JLabel();

        NumberFormat format = NumberFormat.getIntegerInstance();
        format.setGroupingUsed(false);

        NumberFormatter numberFormatter = new NumberFormatter(format);
        numberFormatter.setValueClass(Integer.class);
        numberFormatter.setAllowsInvalid(false);
        sellAmount = new JFormattedTextField(numberFormatter);
        sellStock =  new JButton("Sell");

        stockMarket = new JLabel("Stock Market: ");
        availableStocks = new JComboBox<>(BankSystem.getAvailableStockNames().toArray(String[]::new));
        stockPrice = new JLabel();
        buyAmount = new JFormattedTextField(buyAmount);
        buyStock = new JButton("Buy");

        back = new JButton("Back");
    }

    public void showPage() {
        container.setLayout(null);

        ownedStocksLabel.setBounds(50, 100, 150, 30);
        ownedStocks.setBounds(50, 150, 150, 30);
        ownedAmount.setBounds(50, 200, 150, 30);
        price.setBounds(300, 300, 150, 30);
        sellAmount.setBounds(50, 250, 150, 30);
        sellStock.setBounds(50, 300, 150, 30);

        stockMarket.setBounds(50, 350, 150, 30);
        availableStocks.setBounds(50, 400, 150, 30);
        buyAmount.setBounds(50, 450, 150, 30);
        stockPrice.setBounds(250, 450, 150, 30);
        buyStock.setBounds(50, 500, 150, 30);

        back.setBounds(50, 600, 150, 30);

        container.add(ownedStocksLabel);
        container.add(ownedStocks);
        container.add(ownedAmount);
        container.add(sellAmount);
        container.add(sellStock);
        container.add(stockMarket);
        container.add(availableStocks);
        container.add(buyAmount);
        container.add(buyStock);
        container.add(back);
        container.add(stockPrice);
        container.add(price);

        ownedStocks.addActionListener(this);
        availableStocks.addActionListener(this);
        sellStock.addActionListener(this);
        buyStock.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ownedStocks) {
            Stock stock = (Stock) ownedStocks.getSelectedItem();
            ownedAmount.setText(String.valueOf(customerStocks.get(stock)));
            price.setText("Price: " + String.valueOf(stock.getPrice()));

            this.setVisible(false);
            this.setVisible(true);
        }

        if(e.getSource() == availableStocks) {
            Stock stock = (Stock) availableStocks.getSelectedItem();
            stockPrice.setText("Price: " + String.valueOf(stock.getPrice()));

            this.setVisible(false);
            this.setVisible(true);
        }

        if (e.getSource() == sellStock) {

            if(ownedStocks.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Please select the stock you wish to sell");
                this.setVisible(false);
                this.setVisible(true);
            }

            if(sellAmount.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please select the number of shares you wish to sell");
                this.setVisible(false);
                this.setVisible(true);
            }

            boolean flag = securitiesAccountController.sellStock((Stock) ownedStocks.getSelectedItem(), Integer.parseInt(sellAmount.getText()));

            if(flag) {
                JOptionPane.showMessageDialog(this, "Shares sold successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Not enough shares to sell. Please select a lower amount.");
            }
        }

        if (e.getSource() == buyStock) {

            if(availableStocks.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Please select the stock you wish to buy");
            }

            if(buyAmount.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please select the number of shares you wish to buy");
            }

            boolean flag = securitiesAccountController.buyStock((Stock) availableStocks.getSelectedItem(), Integer.parseInt(buyAmount.getText()));

            if(flag) {
                JOptionPane.showMessageDialog(this, "Shares bought successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Not enough money in account to buy. Please select a lower amount or deposit money to your securities account.");
            }
        }

        if (e.getSource() == back) {
            CustomerHomePage frame = new CustomerHomePage(customer);
            frame.setTitle("Customer home page");
            frame.setVisible(true);
            frame.setBounds(10, 10, 370, 700);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            dispose();
            frame.showPage();
        }
    }
}
