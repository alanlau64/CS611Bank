public class DepositWithTransactionFee extends TransactionsMayChargeFee implements Deposit{
    public DepositWithTransactionFee(){}

    // return the current balance of given currency
    @Override
    public Double deposit(Account myAccount, Currency currency, double money) {
        if(myAccount.getBalance().containsKey(currency))
            myAccount.getBalance().put(currency, myAccount.getBalance().get(currency)
                    + money - Constant.TRANSACTION_FEE);
        else
            myAccount.getBalance().put(currency, money - Constant.TRANSACTION_FEE);
        return myAccount.getBalance().get(currency);
    }
}
