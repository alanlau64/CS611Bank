public class WithdrawWithTransactionFee extends TransactionsMayChargeFee implements Withdraw{
    public WithdrawWithTransactionFee(){}

    // return the current balance of given currency
    // return null means transaction fail because of there is not enough balance
    @Override
    public Double withDraw(Account myAccount, Currency currency, double money) {
        if(myAccount.getBalance().containsKey(currency) && myAccount.getBalance().get(currency) >= money
                + Constant.TRANSACTION_FEE) {
            myAccount.getBalance().put(currency, myAccount.getBalance().get(currency) - money - Constant.TRANSACTION_FEE);
            return myAccount.getBalance().get(currency);
        }
        else return null;
    }
}
