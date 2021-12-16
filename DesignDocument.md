## Design Document of Bank System

### Team 24

Members: \
Hantian Liu U49252828 \
In charge of file system design and document writing. \
Zhicheng Dong U \
In charge of back end design and class diagram. \
Duruvan Saravanan U \
In charge of front end design.

**General Design Choice**

Generally, we applied Swing as our front end frame 
and Gson to save all the necessary information in
Json files. We also applied model view controller (MVC)
design pattern to manage our system and factory pattern
to create log instances. With the help of these design 
patterns, we can manage the whole system including front
end and back end as well as the file system more clearly.

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

`Account` `CheckingAccount` `SavingAccount` `SecurityAccount` \
`Depoist` `DepositWithTransactionFee` `DepositWithoutTransactionFee` \
`Withdraw` `WithdrawWithTransactionFee` \
`Transfer` `TransferWithTransactionFee` `TransferWithoutTransactionFee`: \
`Account` is also an abstract class and there are three
different kinds of accounts including `CheckingAccount`,
`SavingAccount` and `SecurityAccount`. Each `Account` has
an ID, some amount of balance in three different kinds of
currencies and instances of `Deposit`, `Withdraw` and `Transfer`.
They indicate if there is any transaction fee apply to the
process. Different from other kinds of accounts, `SecurityAccount`
has a list of stocks and each customer can only have one
`SecurityAccount`. Restrictions apply according to the document.

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
to open a security account. 

**Object and GUI Relationship**
