## Design Document of Bank System

### Team 24

Members: \
Hantian Liu U49252828 \
In charge of file system design and document writing. \
Zhicheng Dong U63145812 \
In charge of back end design and class diagram. \
Duruvan Saravanan U48495000 \
In charge of front end design.

**General Design Choice**

Generally, we applied Swing as our front end frame 
and Gson to save all the necessary information in
Json files. We also applied model view controller (MVC)
design pattern to manage our system and factory pattern
to create log instances, as well as strategy pattern to
describe behavior of accounts. With the help of these 
design patterns, we can manage the whole system including
front end and back end as well as the file system more clearly.

We implemented all features mentioned in the document
including but not limited to create accounts, deposit, 
withdraw and transfer money through accounts, take loans,
check activities of all accounts, buy and sell securities
for customers and view customer activities, verify loans,
edit securities in market for managers. 

You can view the general UML class diagram in
`diagram.png` we submitted, and more specific design
details will be covered in following sections.

**Object Model**

The entry of the program is `BankSystem` class, which can
be seperated as several parts listed below, each part 
contains some classes. They are shown at the top of each
part. 

`User` `Customer` `Manager`:\
First, there is an abstract class `User`, in which defines
some basic attributes and methods for both `Customer` and
`Manager`. The two classes are inherited from `User`. 
`Manager` is in charge of verifying loan requests submitted
by customers and is able to edit securities in stock market.
`Customer` is the class representing customers of the bank.
It contains different kinds of `Account`s.

`Account` `CheckingAccount` `SavingAccount` `SecurityAccount`: \
`Account` is a super class and there are three
different kinds of accounts including `CheckingAccount`,
`SavingAccount` and `SecurityAccount`. Each `Account` has
an ID, some amount of balance in three different kinds of
currencies. Different from other kinds of accounts, `SecurityAccount`
has a list of stocks and each customer can only have one
`SecurityAccount`. Restrictions apply according to the document.

`Depoist` `DepositWithTransactionFee` `DepositWithoutTransactionFee`
`Withdraw` `WithdrawWithTransactionFee`
`Transfer` `TransferWithTransactionFee` `TransferWithoutTransactionFee`:\
Checking account will charge fee for all kinds of transactions, on the contrary, saving and securities account only charge fee for withdraw money. So we use Strategy pattern. To implement the Strategy pattern we define three transactions' interface representing the ability to do these transactions. We implement each transactions in two versions, including charge fee and not charge fee. And according to the type of the account, account own different implementation of the transaction interface. For example, class `CheckingAccount` own `DepositWithTransactionFee`, `WithdrawWithTransactionFee` and `TransferWithTransactionFee`.

`Loan`: \
Customers can take out loans with proper collateral. Once
a loan is requested by a customer, it is moved into a list
`loansWaitToVerify` and managers can approve or deny the
loan. All loans may charge interest daily after approval.

`Stock`: \
Customers are allowed to make stock trades in the system.
The list of stocks is maintained by managers including
stock name and price. Customers can buy or sell stock with
the balance in their `SecurityAccount`. Only customers 
with more than $5000 in their saving accounts are allowed
to open a security account. And if their saving accounts' balance less than $2500, they can't operate their securities.

**File System**

All logs and system information are saved as Json file with
the help of Gson library and factory design pattern. This 
section introduces basic object model and design detail of
the system. 

`AccountActivity` `LoanActivity` `StockTrade` `Transaction`: \
These classes are templates of a line of corresponding log.

`Log` `LogFactory`: \
`Log` is an interface describes behavior of all log classes
and `LogFactory` create logs.

`CustomerLog` `ManagerLog` `StockLog`: \
Classes used to save all information of users and stocks.

`AccountActivityLog` `LoanActivityLog` `StockTradeLog`
`TransactionLog`: \
Lists contain all activities happened in the system.

`HasDate` `HasName` `HasID`: \
Interfaces used to filter activity logs.

**Object and GUI Relationship**

With MVC design pattern, objects and GUI views are connected
with controller classes. Object model classes only contain
basic methods like getters and setters. Controller classes
contain other more complex methods and can also update views
with these methods. Generally, controllers use objects and
update views.

`AccountController` `CheckingAccountController` `SavingAccountController`
`CheckingAndSavingAccountPage` `SecurityAccountController`
`OpenAccountPage`: \
These classes are controllers of accounts and corresponding
views. When users interact with views, they call controller
methods to get or update values of accounts like balance.
Controllers then update views shown to users.

`CustomerController` `CustomerHomePage`: \
Customers are owners of accounts and `CustomerController`
is in charge of opening or closing accounts and taking loans.

`LoanController` `LoanPage` `OpenLoanView` `SingleLoanView` `VerifyLoanView`: \
Some deeper operation and views about loans including 
`VerifyLoanView` for managers to review loan requests.

`StockController` `CustomerStockView` `ManagerStockView`: \
Some deeper operation and views about stocks. Managers
use `ManagerStockView` to manage securities and customers
use `CustomerStockView` to buy and sell securities.

`WithdrawAndDepositPage` `TransferView`: \
These two pages are used to make transactions by customers.

`LoginPage` `CreateUserPage`: \
`LoginPage` is the entry point of the program and if 
a user hasn't registered, `CreateUserPage` can create
customers and managers.

`DailyReportView` `CustomerReportView`: \
`DailyReportView` is for managers to view all activities
happen today and `CustomerReportView` can be used by both
customers and managers to view activities of a specific
customer. Customers can only view themselves.

