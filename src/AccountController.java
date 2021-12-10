public class AccountController {
    protected Account account;

    public AccountController(){}

    public AccountController(Account account){
        this.account = account;
    }

    // return the current balance of given currency
    // return null means transaction fail because of there is not enough balance
    //TODO: add log
    public Double withDraw(Currency currency, double money){
        return account.getWithdrawTransaction().withDraw(account, currency,money);
    }

    // return the current balance of given currency
    //TODO: add log
    public Double deposit(Currency currency, double money){
        return account.getDepositTransaction().deposit(account, currency,money);
    }

    // return the current balance of given currency
    // return null means transaction fail because of there is not enough balance
    //TODO: add log
    public Double transfer(Account in, Currency currency, double money){
        return account.getTransferTransaction().transfer(account, in, currency,money);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
