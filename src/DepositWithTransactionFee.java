public class DepositWithTransactionFee extends Transaction implements Deposit{
    private Account account;

    public DepositWithTransactionFee(){}

    public DepositWithTransactionFee(Account account){
        super(account);
    }

    @Override
    public Double deposit(Currency currency, double money) {
        if(account.getBalance().containsKey(currency))
            account.getBalance().put(currency, account.getBalance().get(currency)
                    + money - Constant.TRANSACTION_FEE);
        else
            account.getBalance().put(currency, money - Constant.TRANSACTION_FEE);
        return account.getBalance().get(currency);
    }
}
