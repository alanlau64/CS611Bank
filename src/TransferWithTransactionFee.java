public class TransferWithTransactionFee extends TransactionsMayChargeFee implements Transfer{
    public TransferWithTransactionFee(){}

    // return the current balance of given currency
    // return null means transaction fail because of there is not enough balance
    @Override
    public Double transfer(Account myAccount, Account in, Currency currency, double money) {
        if(myAccount.getBalance().containsKey(currency) && myAccount.getBalance().get(currency) >= money
                + Constant.TRANSACTION_FEE) {
            myAccount.getBalance().put(currency, myAccount.getBalance().get(currency) - money - Constant.TRANSACTION_FEE);
            if(in.getBalance().containsKey(currency))
                in.getBalance().put(currency, in.getBalance().get(currency) + money);
            else
                in.getBalance().put(currency, money);
            return myAccount.getBalance().get(currency);
        }
        else return null;
    }
}
