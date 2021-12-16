# CS611Bank
--------------------------------
## BACKEND

--------------------------------
#Account Stuff

--Account\
The superclass of all kinds of account\
--CheckingAccount\
The entity of checking account\
--SavingAccount\
The entity of saving account\
--SecuritiesAccount\
The entity of securities account, used to do stock transactions\
--AccountController\
The class controlls the basic operation of all kinds of account, which is the super class of all kinds of account controller\
--CheckingAccountController\
The class controlls all the transactions of checking account\
--SavingAccountController\
The class controlls all the transactions of saving account\
--SecuritiesAccountController\
The class controlls all the transactions of securities account\


--------------------------------
#Strategy Pattern

--TransactionsMayChargeFee\
The super class of any transactions may charge fee, checking account will charge fee for all transactions, other two just charge fee for withdraw money\
--Withdraw\
The interface represent the ability to withdraw money\
--Deposit\
The interface represent the ability to deposit money\
--Transfer\
The interface represent the ability to tranfer money\
--WithdrawWithTransactionFee\
The class contains the method of withdraw money with transaction fee, all kinds of account own it\
--DepositWithoutTransactionFee\
The class contains the method of deposit money without transaction fee, securities account and saving account own it\
--DepositWithTransactionFee\
The class contains the method of deposit money with transaction fee, checking account own it\
--TransferWithoutTransactionFee\
The class contains the method of transfer money without transaction fee, securities account and saving account own it\
--DepositWithTransactionFee\
The class contains the method of transfer money with transaction fee, checking account own it\


--------------------------------
#User Stuff

--User\
The super class of all kinds of user, includes customer and manager\
--Customer\
The entity of customer, contains all the stuff belong to customer\
--Manager\
The entity of manager, who manage the stock and loan\
--CustomerController\
The class controll all the operation of customer\


--------------------------------
#Other bank stuff

--Loan\
The entity of loan\
--LoanController\
The class contains all the operation of loan\
--Stock\
The entity of stock\
--StockController\
The class contains all the operation of stock\
--Currecy\
The emueration of all kinds of currency in the bank\


--------------------------------
#Log Stuff

--Log\
The interface represent the ability to read files\
--LogFactory\
The class for reading log of given type\
--AccountActivityLog\
The class for read log of account activity\
--LoanActivityLog\
The class for read log of loan activity\
--CustomerLog\
The class for read customer information in the file\
--ManagerLog\
The class for read manager information in the file\
--StockLog\
The class for read stock information in the file\
--StockTradeLog\
The class for read log of stock activity\
--TransactionLog\
The class for read log of transactions\
--AccountActivity\
The entity of log of account activity\
--LoanActivity\
The entity of log of loan activity\
--StockTrade\
The entity of log of stock activity\
--Transcation\
The entity of log of transactions\
--HasDate\
The interface representing the ability of containing date in a log\
--HasName\
The interface representing the ability of containing user name in a log\
--HasID\
The interface representing the ability of containing ids in a log, for example account number or loan number\


--------------------------------
## FRONTEND

--CreateUserPage\
The view for creating all kinds of user\
--LoginPage\
The view for user to login\
--CustomerHomePage\
The view for customer information and all operation customer could do\
--OpenAccountPage\
The view of opening an account\
--CheckingAndSavingAccountPage\
The view of information of checking and saving\
--TransferView\
The view of make transfer\
--WithdrawAndDepositView\
The view of making withdrawal and deposit\
--CustomerReportView\
The view of all transactions operated by customer\
--OpenLoanView\
The view of request a loan\
--LoanPage\
The view of all loans belongs to the user\
--SingleLoanView\
The view of information of one loan\
--VerifyLoanView\
The view of manager to verify the requested loan\
--CustomerStockView\
The view of stock stuff in the customer side\
--ManagerHomePage\
The view for manager information and all operation manager could do\
--ManagerStockView\
The view of management of stock stuff in the manager side\
--DailyReportView\
The view of today's transactions, provided for manager\