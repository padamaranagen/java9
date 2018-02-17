# Strings, Characters and Regular Expressions

[![facebook][1.1]][1]
[![google-plus][1.2]][2]
[![twitter][1.3]][3]
[![github][1.4]][4]

[1.1]: https://github.com/nagendramca2011/Scala/blob/master/social/facebook.png 
[1.2]: https://github.com/nagendramca2011/Scala/blob/master/social/google-plus.png
[1.3]: https://github.com/nagendramca2011/Scala/blob/master/social/twitter.png 
[1.4]: https://github.com/nagendramca2011/Scala/blob/master/social/github.png 

[1]: https://www.facebook.com/nagendramca4u
[2]: https://plus.google.com/u/0/107349924696808464956
[3]: https://twitter.com/nagendramca2011
[4]: https://github.com/nagendramca2011



```
package com.padamara.c14;

public class StringConstructors {

  public static void main(String[] args) {
    char[] charArray= {'N','A','G','E','N','D','R','A','P','A','D','A','M','A','R','A'};
    String s=new String("Nagendra");
    
    String firstObject=new String();
    String secondObject=new String(s);
    String thirdObject=new String(charArray);
    String fourthObject=new String(charArray,0,8);
    
    System.out.printf("FirstObject Data=%S, %nSecondObject Data=%S, %nThirdObject Data=%S,%nFourthObject Data=%S",firstObject,secondObject,thirdObject,fourthObject);

  }

}

Output:

FirstObject Data=, 
SecondObject Data=NAGENDRA, 
ThirdObject Data=NAGENDRAPADAMARA,
FourthObject Data=NAGENDRA

```














`