public class TransferWithTransactionFee extends Transaction implements Transfer{
    private Account account;

    public TransferWithTransactionFee(){}

    public TransferWithTransactionFee(Account account){
        super(account);
    }

    // return the current balance of given currency
    // return null means transaction fail because of there is not enough balance
    @Override
    public Double transfer(Account in, Currency currency, double money) {
        if(account.getBalance().containsKey(currency) && account.getBalance().get(currency) >= money
                + Constant.TRANSACTION_FEE) {
            account.getBalance().put(currency, account.getBalance().get(currency) - money - Constant.TRANSACTION_FEE);
            if(in.getBalance().containsKey(currency))
                in.getBalance().put(currency, account.getBalance().get(currency) + money - Constant.TRANSACTION_FEE);
            else
                in.getBalance().put(currency, money - Constant.TRANSACTION_FEE);
            return account.getBalance().get(currency);
        }
        else return null;
    }
}
