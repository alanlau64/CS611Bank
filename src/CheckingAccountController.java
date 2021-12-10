public class CheckingAccountController extends AccountController{
    public CheckingAccountController(){}

    public CheckingAccountController(CheckingAccount checkingAccount){
        setAccount(checkingAccount);
    }
}
