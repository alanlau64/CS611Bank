import java.util.Map;

public class AccountController {
    protected Account account;

    public AccountController(){}

    public AccountController(Account account){
        this.account = account;
    }

    // return the current balance of given currency
    // return null means transaction fail because of there is not enough balance
    public Double withDraw(Currency currency, double money){
        BankSystem.addTransaction(new Transaction(account.getAccountNum(),
                null, Constant.CURRENT_TIME, currency, money, "withdraw"));
        return account.getWithdrawTransaction().withDraw(account, currency,money);
    }

    // return the current balance of given currency
    public Double deposit(Currency currency, double money){
        BankSystem.addTransaction(new Transaction(account.getAccountNum(),
                null, Constant.CURRENT_TIME, currency, money, "deposit"));
        return account.getDepositTransaction().deposit(account, currency,money);
    }

    // return the current balance of given currency
    // return null means transaction fail because of there is not enough balance
    public Double transfer(Account in, Currency currency, double money){
        BankSystem.addTransaction(new Transaction(account.getAccountNum(), in.getAccountNum(),
                Constant.CURRENT_TIME, currency, money, "transfer"));
        return account.getTransferTransaction().transfer(account, in, currency,money);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
