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

Project Jigsaw was merged into JDK 9, so if you download JDK 9, you will have Jigsaw included by default.

The installation is straightforward. You have to set the environment variables on your PC to point to the new JDK. For this, choose the root folder where your Java 9 installation resides. If you use Windows and JDK 9 is in the PATH, you can verify that the environment variables have been successfully set by opening a command line and typing java -version.


Question & Answers:
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


JSR - Java Specification Requirements
