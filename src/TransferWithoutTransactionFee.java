public class TransferWithoutTransactionFee extends Transaction implements Transfer{
    public TransferWithoutTransactionFee(){}

    public TransferWithoutTransactionFee(Account account){
        super(account);
    }

    // return the current balance of given currency
    // return null means transaction fail because of there is not enough balance
    @Override
    public Double transfer(Account in, Currency currency, double money) {
        if(account.getBalance().containsKey(currency) && account.getBalance().get(currency) >= money) {
            account.getBalance().put(currency, account.getBalance().get(currency) - money);
            if(in.getBalance().containsKey(currency))
                in.getBalance().put(currency, account.getBalance().get(currency) + money);
            else
                in.getBalance().put(currency, money);
            return account.getBalance().get(currency);
        }
        else return null;
    }
}
