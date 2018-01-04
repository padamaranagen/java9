# Project Jigsaw
Project Jigsaw is all about, shows some problems encountered in the past in Java, and discusses the Java Enhancement Proposals that constitute Project Jigsaw. It explains the goals of Project Jigsaw so that we can get a grasp of the reasons that made the JCP team decide to introduce a new module system for the Java platform. It also goes over concepts like ***strong encapsulation*** and ***reliable configuration***.

## Weaknesses in Java Prior to JDK 9

![The Module graph in JDK7](https://github.com/padamaranagen/java9/blob/master/Images/Chapter2-The%20Module%20Graph%20in%20JDK7.png)

The base module is displayed right in the middle. Because there were a lot of dependencies between the classes, there was no way to split the monolith into
smaller pieces. Besides that, the access delimiters didn’t provide sufficient means to completely hide the implementation of the classes. Their scope was limited. For instance, in JDK 7 if we wanted to print “Hello world!” using the System console, we would need a great number of packages besides the base module.

JAR (Java Archive) files are ZIP files that contain class files and other resources. One or more JAR files are placed on the class path, which doesn’t provide encapsulation for the JAR files it contains. This means that *every class in a JAR file is accessible by every other class, and that represents a security vulnerability*. You can imagine the class path like being a row where the JAR files are being searched linearly from left to right. JAR files are not components that can provide modularity for the classes that they contain.

Introduced in JDK 8, compact profiles are a subset of the full Java SE platform. The three compact profiles are called *ompact 1, compact 2, and compact 3*. A list of the packages contained in each compact profile is listed on the Oracle website. The compact profiles were just a small step toward the modularization
of the platform. Being able to get three different versions of the JDK by aggregating the standard packages was not what the community expected with respect to modularization. Besides that, compact profiles don’t hide their internals and therefore brought no improvements to the security of the Java Platform.

*Explicit dependencies were also a huge problem before Java 9*. By packaging source code in a JAR file and putting it on the class path, there was no way of programmatically defining which other JARs were needed in order for the actual JAR to be able to run. Because Java didn’t solve this problem, a few popular build tools have emerged, including Maven and Gradle.

## Weak Encapsulation
For achieving encapsulation, Java prior to version 9 used the well-known access modifiers: private, protected, public, and no modifier. The private access modifier is the most restrictive. It makes the data inside unavailable from outside. The protected access modifier indicates that the member can only be accessed by a subclass of its class in another package or within its own package. The public access modifier makes data available everywhere. Using no access modifier means that availability is granted only inside the same package. 
However, encapsulation has some limitations. It’s impossible to make a type accessible to an external package and at the same time restrict access to it from all other existing packages. To make the type available to an external package, the only way would be to mark it as public, although defining it as public breaks encapsulation and makes it public for all the existing packages. There is no way to reach the desired level of encapsulation using Java prior to version 9. 

## JAR Hell Problem

Prior to JDK 9, the standard style for developing Java applications was to insert all the necessary libraries and JAR files directly on the class path. This approach could give rise to the JAR hell problem. JAR files directly on the class path. This approach could give rise to the JAR hell problem.

***Before JDK 9**, the runtime environment searched on a couple of locations in order to load a class*. *One of the searched locations is the class path, which contained a list of class files that were loaded by the Java Virtual Machine. Searching for a class on the class path was straightforward. The class loader searched a class by exploring all the JAR files listed on the class path. It didn’t take into account a pre-definite order but just searched from the first to the last. It also didn’t take into account aspects of the internal structure of the classes on the class path. Java wasn’t able to take the boundaries between the JAR files into consideration. All the classes from all the JAR files were placed on the class path, and the boundaries between the JAR files disappeared. Each type in a JAR could access all the public types from any other JAR files. Therefore, ***the code couldn’t be encapsulated in order to hide it from external use***.

JAR hell is a common problem encountered prior to JDK 9. *If there were more libraries on the **class path with different versions*** and each library depended on another library, then there was said to be a JAR hell problem on the class path.This so-called dependency hell happened when a package had a dependency not on another package, but only on a version of that package. There were different variations of dependency hell, taking into account the environment being used. The problem with JAR hell is that you could have conflicts on the class path, especially when it contained many JARs. For instance, one library could have two or more different versions of a specific class on the class path. The class path wasn’t the best solution because JAR files aren’t components, and therefore we can’t exactly know if something is missing or is conflicting.

If a specific class wasn’t found on the classpath, ***a runtime exception was triggered—not during the launch of the application but at a later point when, due to an action performed by the user, the missing class was invoked*. The runtime didn’t have the capacity to identify all the existing dependencies until it had to access them. It would have been preferable to have all the errors displayed right during the start of the application and not at a later point***.

## What is Project Jigsaw?

Project Jigsaw represents ***the implementation of the new scalable module system*** introduced in Java 9. It was developed under Open JDK, which is the free, open source implementation of the Java Platform Standard Edition. ***The goal of the newly designed module system for the Java SE Platform is to modularize the JDK and apply the module system to the JDK itsel***f. Jigsaw modularizes the Java SE platform.

***The process of modularizing the Java platform was a complicated and tremendous effort***. A great number of difficult design decisions had to be made. The modularization of the platform is an enormous change with a major impact on the entire ecosystem. It introduces the new concept of modules and significantly changes the way we develop software applications using the Java programming language. Modules are placed in the foreground and are the key concept upon which Project Jigsaw is based. Entire programming techniques have to be adjusted to match the newly introduced concept.

Project Jigsaw started back in 2008 in an exploratory phase. The JEPs that constitute the Java Platform Module System were created starting with year 2014. Project Jigsaw was initially planned for the Java 7 release, but due to its complexity it was not included in the JDK 7 release and was postponed for the JDK 8 release. Then the Java Community Process deferred it for Java 9. Although the official release of Project Jigsaw is at the time of this writing planned for September 2017, early access builds have been available for a long time on the Open JDK website so that the community can test and provide valuable feedback to the JDK developers.

Project Jigsaw consists of ***six JEPs and a JSR***. ***JSR 376 is called the Java Platform Module System***. It designates a standard specification for building a modular version of the Java platform.


|JEP Number |JEP Name |Scope
|-|-|-|
|JEP 200 |Modular |JDK Standard Edition
|JEP 201 |Modular Source Code |Implementation
|JEP 220 |Modular |Run-Time Images |Standard Edition
|JEP 260 |Encapsulate Most Internal APIs |Java Development Kit
|JEP 261 |Module System |Standard Edition
|JEP 282 |jLink: The Java Linker |Java Development Kit

* ***JEP 200—the Modular JDK:*** This Java Enhancement Proposal divided the JDK into a
set of modules. The JDK was modularized, and the source code was organized into
modules. There are two different categories of modules: *standard modules*, with
names that start with ***java***., and *JDK modules*, that start with ***jdk***.

* ***JEP 201—Modular Source Code***: This JEP defines how the JDK build and the source code were reorganized around modules

* ***JEP 220—Modular Run-Time Images:*** JEP 220 presents the new modular runtime image and the enhancements added so that we can build custom modular runtime images. ***The binary structure of the JRE and JDK was changed.***

* ***JEP 260—Encapsulate Most Internal APIs:*** JEP 260 refers to the process of
encapsulating the non-critical internal APIs.

* ***JEP 261—Module System:*** JEP 261 represents the implementation of the
new module system.
* ***JEP 282—jLink: The Java Linker***: This JEP creates a tool that assembles a set of modules into a custom runtime image.

The overall goal of this JSR is to define an approachable yet scalable module system for the Java Platform. It will be approachable, i.e., easy to learn and easy to use, so that developers can use it to construct and maintain libraries and large applications for both the Java SE and Java EE Platforms. It will be scalable so that it can be used to modularize the Java SE Platform itself, and its implementations.

As outlined in the JSR, the specific goals are to provide and enable:

> * Reliable configuration, to replace the brittle, error-prone class-path mechanism with a means for program components to declare explicit dependences upon one another;

> * Strong encapsulation, to allow a component to declare which of its APIs are accessible by other components, and which are not;

> * A scalable Java SE Platform, whose components can be assembled by developers into custom configurations that contain only the functionality actually required by an application;

> * Greater platform integrity, to ensure that code that is internal to a platform implementation is not accessible from outside the implementation; and

> * Improved performance, by applying whole-program optimization techniques to complete configurations of platform, library, and application components.
## Downloading and Installing
[Java9](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html)

Project Jigsaw was merged into JDK 9, so if you download JDK 9, you will have Jigsaw included by default.

The installation is straightforward. You have to set the environment variables on your PC to point to the new JDK. For this, choose the root folder where your Java 9 installation resides. If you use Windows and JDK 9 is in the PATH, you can verify that the environment variables have been successfully set by opening a command line and typing java -version.

## Goals of Project Jigsaw
Before Java 9, JDK was a big, indivisible monolith with more than 5.500 classes. It was impossible to split it into more pieces. The only way to use it was to install it entirely on the target platform. The Java runtime represented by the rt.jar file was also monolithic and couldn’t be split into more parts. JDK consisted of rt.jar, which contained almost all the compiled classes for the base Java runtime. rt.jar grouped together all the runtime class files and had to be placed on the class path in order for the user to be able to access the Java API classes. Inside rt.jar there were—besides the popular java.* and javax.* packages— other packages such as com.oracle.*, com.sun.*, jdk.internal.*, jdk.management.*, jdk.net.*, sun.*, and more. There was no way to split rt.jar into different files. In Java 9, the focus was to break the monolithic JDK into modules and to completely remove the rt.jar file.

The JDK had to be modularized because, after more than 22 years since its first release, it had grown so much that it effectively became too big and too complex. Installing JDK on small devices can be cumbersome in certain situations, because not all small devices have enough CPU, memory, or disk space to be able to hold the entire JDK. Besides that, it’s a huge waste of memory to install the entire monolithic JDK and use only a small portion of it in your application. This problem relates not only to small devices, but to big devices as well, like the ones used to hold the applications in the cloud. Important additional costs could occur using the cloud because the use of hardware resources isn’t optimized.

* ***JDK 1.0***, released at the beginning of 1996, was extremely small and tiny in comparison to the actual release of the JDK. The first version of Java had only a few standard packages: java.lang, java.io, java.applet, java.awt, java.net, and java.util. There were a total of 8 packages and 212 classes and interfaces.
* ***JDK 1.1*** had 504 classes and interfaces.
* ***JDK 1.2***, released in 1998, tripled the number of classes and interfaces to 1,520.
* ***JDK 1.4*** had 2,991 classes and interfaces
* ***JSE 8.0*** had 4,240 classes and interfaces

Previous to **JDK 9**, for a simple program that prints a string in the console, a great number of classes had to be loaded. For example, the base module in JDK 7 depended on a lot of other modules such as **logging, security-smartcardio, security-sunec, security-resources, resources, charsets, client, security-misc, securityjsse, security-kerberos**, and others. All these modules had to be loaded in order to print a basic and very simple “Hello world!” in the console.

## New Concepts Introduced in Jigsaw

Project Jigsaw introduces the new concept of *module* as a central software component that is built inside the Java platform. A module represents a collection of packages. It has a module descriptor that specifies the modules upon which the module depends and also specifies its exported packages that are made available for external use. **A module can be packaged into a new format called modular JAR, which is a JAR file that also contains a module-info.class file. **A modular JAR file can function as a module in Java 9, but also as a regular JAR file on the class path in Java 8 or earlier. There’s another new format called **JMOD**, which is similar to a modular JAR but can also contain native code. A module can be open or not.

The new notion of *module path* is introduced in Project Jigsaw. Module path is the module equivalent of the class path and consists of a list of directories that contain modules.

Jigsaw also introduces a *linking phase* in which a group of modules is assembled by a new linking tool, called Jlink, into a custom binary runtime image. Linking can create a full Java development environment and can also create a Java runtime system incorporated in a program.

Java 9 adds many new options for both the Java compiler and the Java launcher in order to allow the compilation and running of modules.

Jigsaw also introduces new notions like* unnamed module*, *open module*, and *automatic module*.

## Strong Encapsulation

According to the official Jigsaw specification, “strong encapsulation allows a component to declare which of its public types are accessible to other components and which are not.” Strong encapsulation’s role is to forbid code from accessing classes in packages that aren’t exported by their containing modules, or in packages whose containing modules aren’t needed by the module that contains the code.

Strong encapsulation couldn’t be achieved without having a concept like modules, because in Jigsaw the modules represent the base on which the principles of strong encapsulation are applied. Jigsaw allows modules to export only specific packages. The accessibility of modules is provided by their boundaries. Strong encapsulation is accomplished in Jigsaw using the definitions of the modules, where we are able to specify what types are accessible. Strong encapsulation hides module’s internals and prevents them from external access. It also makes it more difficult to achieve reflective access.

In **Java 9**, calling the method **setAccessible()** won’t work unless the object is accessible before the class. To be accessible, the corresponding package has to be exported, and the module has to be read. If both conditions are met, then it is accessible, so the method can be applied to make, for instance, a private field available. Strong encapsulation restricts access even when the accessing class in the target class is in the same class loader. By the way, *strong encapsulation is not dependent on class loaders*.

## Reliable Configuration

*Reliable configuration* is a strong feature introduced in JDK 9. Open JDK states that “reliable configuration replaces the class path mechanism with a means for program components to declare explicit dependences upon one another.” Reliable configuration is based on the capacity to declare dependencies between modules. It allows us to know at compile-time if a module is missing or a dependency isn’t fulfilled. This is something we could’t achieve in versions before Java 9. In JDK 9, modules can manifest their dependencies
on other modules, and the module system certifies that every module dependence is achieved.

The ground for reliable configuration is represented by the readability connections that exist in the module system. Dependencies are analyzed and enforced at both **compile-time** and **runtime**.

## Enhancements Provided by Jigsaw

Jigsaw also provides enhancements in three important areas: *security*, *scalability*, and *performance*.

## Question & Answers
1. What is dependency hell? 
>Dependency hell happened when a package had a dependency not on another package, but only on a version of that package. 

2. What is Project Jigsaw? What is the goal of Jigsaw? 
>Project jigsaw represents the implementation orf the new scalable module system. The goal of jigsaw is newly designed module system for the Java SE Platform is to modularize the JDK and apply the module system to the JDK itself.

3. When was Jigsaw started? 
>Project Jigsaw started back in 2008 in an exploratory phase. The JEPs that constitute the Java Platform Module System were created starting with year 2014.

4. What Jigsaw project consists?
>Project Jigsaw consists of six JEPs and a JSR. JSR 376 is called the Java Platform Module System. It designates a standard specification for building a modular version of the Java platform.

5. What are the primary goals of jigsaw?
> The primary goals of this Project were to:
> * Make it easier for developers to construct and maintain libraries and large applications;
> * Improve the security and maintainability of Java SE Platform Implementations in general, and the JDK in particular;
>* Enable improved application performance; and
>* Enable the Java SE Platform, and the JDK, to scale down for use in small computing devices and dense cloud deployments.

6.What is JMOD?
>It is a modular JAR but can also contain native code.

7.What is Strong Encapsulation? 
>Strong encapsulation allows a component to declare which
of its public types are accessible to other components and which are not.

8.What is Reliable Configuration?

>Reliable configuration replaces the class path mechanism for program components to declare explicit dependences upon one another.

### Abbrevations

JSR - Java Specification Requirements
