General Aspects of Modularity
=

Modularity specifies the interrelation and intercommunication between the parts that comprise a software system. Modular programming defines a concept called  the module. Modules are software components that contain data and functions. Integrated with other modules, together they form a unitary software system. Modular programming provides a technique to decompose an entire system into independent software modules. Modularity plays a crucial role in modern software architecture. It divides a big software system into separate entities and helps reduce the complexity of software applications while simultaneously decreasing the development effort.

The goal of modularity is to define new entities that are easy to understand and use. Modular programming is a style of developing software applications by splitting the functionality into different modules — software units that contain business logic and have the role of implementing a specific piece of functionality. Modularity enables a clear separation of concerns and assures specialization. It also hides the module’s implementation details. Modularity is an important part of agile software development because it allows us to change or refactor modules without breaking other modules.

Two of the most important aspects of modularity are maintainability and reusability, both of which bring great benefits.

***Maintainability***

Maintainability refers to the degree to which a software system is upgraded or modified after delivery. A big, monolithic software application is hard to maintain, especially if it has many dependencies inside the code.

***Reusability***

Reusability is the degree to which we can reuse or replace a module. Reusability avoids duplicating code and reduces the number of lines of code, which has a positive impact
on the number of software defects. It not only improves software quality, it also helps in developing software faster and makes performing updates on it easier.

#### Module Definition

A software module is reusable, testable, manageable, and deployable. Multiple modules can be combined together to form a new module. Modular programming is the key to reducing the number of bugs in complex software systems to a minimum. By dividing the application into very small modules, each modules will have fewer defects because its functionality is not complex. Assembling these less error-prone modules results in an application with fewer errors.

One of the key facets of modularity is breaking the application down into small, thin modules that are easy to implement because they don’t possess a high level of complexity. The modules can be interconnected earlier at compile time or later at runtime. Each module must be able to be bound to the core application.

#### Strong Encapsulation

*Encapsulation* defines the process of preventing data access from outside by allowing a component to declare which of its public types are available to other components and which aren’t. Encapsulation improves code reusability and diminishes the number of software defects. It helps obtain modularity by
decoupling the internal behavior of each object from the other elements of the software application.

#### Explicit Interfaces
The interfaces of a modular system should be as small as possible. If an interface is too big, it should be divided into several smaller interfaces. An interface should make available to a module only the methods
that the module really needs in order to be able to fulfill its business requirements. 
A modular system typically provides ***module management*** and ***configuration management***. *Module management* refers to the capacity to install, uninstall, and deploy a module. The installation could be done from a module repository, for example. In some cases, a module could be deployed instantly without
requiring that the system is restarted. *Configuration management* specifies the capacity to dynamically configure modules and specify the dependencies between them.

#### Tight Coupling vs. Loose Coupling

*Loose coupling* is encountered when a class doesn’t directly use an instance of another class but uses an intermediate layer that primarily defines the object to be injected. One framework that defines loose coupling is the Spring framework, where the dependency objects are being injected by the container
into another object. Loosely coupled modules can be altered with less effort. Loose coupling is generally accomplished by using small or medium-sized modules. Replacing a module won’t affect the system if the new module has the same interface as the module being replaced.

*Tight coupling* means classes are dependent on other classes and it doesn’t allow a module to be replaced so easily because it has dependencies on the implementation of other modules.

Example of tight coupling, The listing defines one class called *Customer* that has dependencies on objects of types CurrentAccount, DepositAccount, and SavingsAccount. In the Main class, we create one object of type Customer. This object further creates three other objects. The Customer class contains an object of type CurrentAccount and calls the method depositMoney(amount) on this
object. This is a tight coupling between class Customer and class CurrentAccount, and because class CurrentAccount is completely tied to class Customer, it depends on it. The class Customer creates objects of types CurrentAccount, DepositAccount, and SavingsAccount in order to execute some business logic that is defined in these three classes.

***CurrentAccount.java***
```
package padamara.java9.chapter1.tightcoupling;

public class CurrentAccount{
  long deposit;

  public void depositMoney(long amount){
    deposit=amount;
  }
  public long getDeposit(){
    return deposit;
  }
}
```
***DepositAccount.java***
```
package padamara.java9.chapter1.tightcoupling;

public class DepositAccount{
  long deposit;

  public void depositMoney(long amount){
    deposit=amount;
  }
  public long getDeposit(){
    return deposit;
  }
}
```
***SavingsAccount.java***
```
package padamara.java9.chapter1.tightcoupling;

public class SavingsAccount{
  long deposit;

  public void depositMoney(long amount){
    deposit=amount;
  }
  public long getDeposit(){
    return deposit;
  }
}
```
***Customer.java***
```
package padamara.java9.chapter1.tightcoupling;

public class Customer{
  private CurrentAccount currentAccount;
  private DepositAccount depositAccount;
  private SavingsAccount savingsAccount;

  public Customer(){
    currentAccount=new CurrentAccount();
    depositAccount=new DepositAccount();
    savingsAccount=new SavingsAccount();
  }

  public void depositMoneyIntoCurrentAccount(long amount){
    currentAccount.depositMoney(amount);
  }
  public void depositMoneyIntoDepositAccount(long amount){
    depositAccount.depositMoney(amount);
  }
  public void depositMoneyIntoSavingsAccount(long amount){
    savingsAccount.depositMoney(amount);
  }
  public CurrentAccount getCurrentAccount(){
    return currentAccount;
  }
  public DepositAccount getDepositAccount(){
    return depositAccount;
  }
  public SavingsAccount getSavingsAccount(){
    return savingsAccount;
  }


}
```
***Main.java***
```
package padamara.java9.chapter1.tightcoupling;

public class Main{
  public static void main(String args[]){
    Customer firstCustomer=new Customer();
    firstCustomer.depositMoneyIntoCurrentAccount(50);

    Customer secondCustomer=new Customer();
    secondCustomer.depositMoneyIntoDepositAccount(100);

    Customer thridCustomer=new Customer();
    thridCustomer.depositMoneyIntoSavingsAccount(200);

    System.out.println("First Customer current account amount:"+firstCustomer.getCurrentAccount().getDeposit());
    System.out.println("Second Customer current account amount:"+secondCustomer.getDepositAccount().getDeposit());
    System.out.println("Third Customer current account amount:"+thridCustomer.getSavingsAccount().getDeposit());

  }
}

```
The previous three listings show tight coupling in which the Customer class instantiates objects of other classes and subsequently accesses methods on them. This results in a very high level of dependency between the Customer class and the other classes it uses. The main problem is that a change in CurrentAccount, DepositAccount, or SavingsAccount classes could eventually obligate us to adapt the class Customer. For example, if the constructor of CurrentAccount changes, we have a problem. To decouple the classes in this example, we should modify the code so that class Customer is not dependent any more on the implementation of classes CurrentAccount, DepositAccount, and SavingsAccount. As a result, we’ll use an interface in order to make class Customer dependent only on the interface.

***AccountInterface.java***
```
package padamara.java9.chapter1.loosecoupling;

public interface AccountInterface{
  void depositMoney(long amount);

  long getDeposit();
}

```
***CurrentAccount.java***
```
package padamara.java9.chapter1.loosecoupling;

public class CurrentAccount implements AccountInterface{
  long deposit;
  public CurrentAccount(){}

  @Override
  public long getDeposit(){
    return deposit;
  }
  @Override
  public void depositMoney(long amount){
    deposit=amount;
  }
}
```
***DepositAccount.java***
```
package padamara.java9.chapter1.loosecoupling;

public class DepositAccount implements AccountInterface{
  long deposit;
  public DepositAccount(){}

  @Override
  public long getDeposit(){
    return deposit;
  }
  @Override
  public void depositMoney(long amount){
    deposit=amount;
  }
}
```
***SavingsAccount.java***
```
package padamara.java9.chapter1.loosecoupling;

public class SavingsAccount implements AccountInterface{
  long deposit;
  public SavingsAccount(){}

  @Override
  public long getDeposit(){
    return deposit;
  }
  @Override
  public void depositMoney(long amount){
    deposit=amount;
  }
}
```
***Customer.java***
```
package padamara.java9.chapter1.loosecoupling;

public class Customer{
  private AccountInterface account;
  public Customer(AccountInterface account){
    this.account=account;
  }
  public void deposit(long amount){
    account.depositMoney(amount);
  }
  public AccountInterface getAccount(){
    return account;
  }
}
```
***Main***
```
package padamara.java9.chapter1.loosecoupling;

public class Main{
  public static void main(String args[]){
    CurrentAccount currentAccount=new CurrentAccount();
    Customer firstCustomer=new Customer(currentAccount);
    firstCustomer.deposit(10);

    DepositAccount depositAccount=new DepositAccount();
    Customer secondCustomer=new Customer(depositAccount);
    secondCustomer.deposit(100);

    SavingsAccount savingsAccount=new SavingsAccount();
    Customer thridCustomer=new Customer(savingsAccount);
    thridCustomer.deposit(200);

    System.out.println("First Customer current account amount:"+firstCustomer.getAccount().getDeposit());
    System.out.println("Second Customer current account amount:"+secondCustomer.getAccount().getDeposit());
    System.out.println("Third Customer current account amount:"+thridCustomer.getAccount().getDeposit());
  }
}
```
### Modular Programming

##### Principles of Modular Programming
The principles of modular programming are continuity, understandability, reusability, combinability, and decomposability.

* Continuity: Refers to the situation when a requirement to change the functionality of
the software system should cause changes in as few modules as possible.
* Understandability: Refers to the fact that each module should be comprehensible as a standalone single unit. Its role should be clear and concise. It’s definitely easier to understand the inner workings of a particular module—which presents a lower level of functionality—than of an entire application. You should avoid situations in which a module fulfills its role only in correlation with some other modules. A module should not cause side issues for other modules.
* Combinability: Allows us to recombine modules so that a new software application results.
* Decomposability: Allows us to decompose a monolith into smaller and simpler parts, which should be independently packaged into a different software unit. The resulting software unit should have a simpler structure and a lower level of complexity than the initial monolith. By breaking a system down into logical modules, we can understand the system much better and adjust it more easily.

### Benefits of Modular Programming

* Modular Programming generally helps make debugging applications easier because only a single module can be debugged and not the entire monolith.

* Modular programming allows a team to work together on the same project at the same time with fewer problems related to source code conflicts. If every developer works on their own module, there won’t be any conflicts at all. Having to write less code is another benefit of using modular programming and it is a direct consequence of the reuse capability. By using modular programming techniques, developers gain better productivity and performance by taking advantage of parallel development.
* Faster development is another key aspect of modular programming. The time required for development decreases because modules can be designed and implemented independently. The development process can be scaled because it’s possible to develop more modules simultaneously by involving a larger team that
can work on different modules at the same time. If a module is being modified, then the other modules will continue to work.
Modules should be easily interchanged with other modules. By defining an interface for the other
* modules of the application, changing or replacing a module implies only assuring that the new interface is equivalent to the old one. The internal implementation of the new module can differ.
* Another important aspect refers to the testing process. Instead of testing an entire application as a whole monolith, the application is divided into modules, and each module is tested separately. Because the modules are independent, multiple modules can be tested at the same time, which speeds test execution
and assures the integrity of the modules. By testing each module separately as a unit, better test coverage is achieved. Integration testing is performed by connecting the modules and looking at them as black boxes.

#### Modular Programming vs. Object-Oriented Programming (OOP)
The similarities between modular programming and OOP include the fact that both break large software applications down into fragments or concerns. Modular programming is not object-oriented. The core principles of OOP, such as polymorphism and inheritance, don’t exist in modular programming. In OOP, polymorphism is used to dynamically change the properties of classes at runtime. This isn’t possible in modular programming because the modules aren’t dynamic. Besides that, in OOP classes use inheritance to enable other classes to inherit variables and method implementations from them. In modular programming, a module can’t inherit another module. 
One of the main differences between modular programming and OOP is the fact that in OOP objects can be created from classes. In modular programming, deriving objects from modules isn’t possible.


#### Monolithic Application vs. Modular Application
A monolithic application is a software application with a high level of complexity that executes an entire group of tasks in order to implement a whole use case. It doesn’t execute only a specific task or function and it doesn’t consist of any logical units that can be identified. It has the role of executing entire functions, not just particular tasks inside these functions. Monolithic applications are constructed without modularity.


***Fig: Architecture of Monolithic Application***

![Architecture of Monolithic Application](https://github.com/nagendramca2011/java9/blob/master/Images/Chapter1-Architecture%20of%20Monolithic%20Application.png)

A monolithic application typically consists of many layers: presentation layer, business layer, data access layer, and database. With this system of layers, using multiple technologies for a single layer is difficult.
We don’t claim that it’s absolutely impossible—it depends on the technologies you’re using. Sometimes it’s possible to use multiple technologies, and sometimes not. But having such constraints is definitely a drawback for every developer. If they find a third-party library that can solve a specific problem easier, they might not be able to use it because it’s incompatible with the technology used on that layer. It would have been easier for them to be able to split the layers into different microservices and have the freedom to use,
for each microservice, whichever technology better suits the task. Being forced sometimes to continue to use the existing technology is bad because the technology may already be old. For a modular system, because you may not have so many dependencies to care about, it is obviously not so time-consuming to update the technology and install the latest version of it, when compared to a monolithic system.

Due to the complexity of the system and the technical know-how required, there may be many cases when multiple teams are working on different layers of the application. To add a new feature to the application, every layer has to be addressed, which means in most cases, when a new feature is added, more than one team has to be involved. That can increase the time needed to develop and test new features because the teams will need to coordinate their work. There may also be more integration work to do— teams will not only have to coordinate with each other, they’ll sometimes also have to wait for certain features to be ready so they can integrate their own piece of developed functionality.

As for scalability, a monolithic system can be scaled only as a whole. It’s impossible to scale only specific parts of the system, and it’s not a good idea to try to scale the entire system if only a part should be actually scaled. Scaling the entire system could mean additional infrastructure costs. This is why monolithic applications aren’t so frequently upgraded and patched compared to modular applications.

A monolithic application can be separated into a set of modules, but that’s not the only way to get a modular application. It can also be designed as modular from the beginning. Redesigning a monolithic application may be not a trivial task, especially when the system is very complex.
