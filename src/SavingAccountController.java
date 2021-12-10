public class SavingAccountController extends AccountController{
    public SavingAccountController(){}

    public SavingAccountController(SavingAccount savingAccount){
        setAccount(savingAccount);
    }

    // get the interest when the account having balance more than threshold
    public void getInterest(){
        for(Currency currency: account.getBalance().keySet()){
            if(account.getBalance().get(currency) > Constant.SAVING_INTEREST_THRESHOLD)
                account.getBalance().put(currency, Constant.SAVING_INTEREST_THRESHOLD +
                        (account.getBalance().get(currency) - Constant.SAVING_INTEREST_THRESHOLD)
                                * (1 + Constant.SAVING_INTEREST));
        }
    }
}
