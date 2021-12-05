public class DepositWithoutTransactionFee extends Transaction implements Deposit{
    private Account account;

    public DepositWithoutTransactionFee(){}

    public DepositWithoutTransactionFee(Account account){
        super(account);
    }

    // return the current balance of given currency
    @Override
    public Double deposit(Currency currency, double money) {
        if(account.getBalance().containsKey(currency))
            account.getBalance().put(currency, account.getBalance().get(currency) + money);
        else
            account.getBalance().put(currency, money);
        return account.getBalance().get(currency);
    }
}
