package com.padamara.c17;

import java.util.stream.IntStream;

public class StreamMapReduce {

	public static void main(String[] args) {
		System.out.printf("Sum of the even ints from 2 through 20 is: %d%n",
				IntStream.rangeClosed(1, 10)//1 to 10
				.map((int x)->{return x*2;})// multiply by 2
				.sum()//sum
				);

	}

}
