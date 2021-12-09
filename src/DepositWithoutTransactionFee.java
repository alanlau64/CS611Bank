public class DepositWithoutTransactionFee extends TransactionsMayChargeFee implements Deposit{
    public DepositWithoutTransactionFee(){}

    // return the current balance of given currency
    @Override
    public Double deposit(Account myAccount, Currency currency, double money) {
        if(myAccount.getBalance().containsKey(currency))
            myAccount.getBalance().put(currency, myAccount.getBalance().get(currency) + money);
        else
            myAccount.getBalance().put(currency, money);
        return myAccount.getBalance().get(currency);
    }
}
