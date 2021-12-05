public class TransferWithoutTransactionFee extends Transaction implements Transfer{
    private Account account;

    public TransferWithoutTransactionFee(){}

    public TransferWithoutTransactionFee(Account account){
        super(account);
    }

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
        else return -1.0;
    }
}
