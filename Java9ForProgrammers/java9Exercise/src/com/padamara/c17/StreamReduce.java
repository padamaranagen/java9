package com.padamara.c17;

import java.util.stream.IntStream;

public class StreamReduce {	
	public static void main(String[] args) {
//		int total=0;
//		for(int number=1;number<=10;number++) {
//			total+=number;
//		}
//		System.out.println(""+total);
		System.out.printf("Sum of 1 through 10 is: %d%n",
				IntStream.rangeClosed(1, 10)
				.sum());		
	}
}
