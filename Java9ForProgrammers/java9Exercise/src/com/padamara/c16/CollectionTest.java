package com.padamara.c16;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollectionTest {
	private static void removeCards(Collection<String> mixedList, Collection<String> removedList) {
		// get iterator
		Iterator<String> iterator = mixedList.iterator();
		// loop while collection has items
		while (iterator.hasNext()) {
			if (removedList.contains(iterator.next())) {
				iterator.remove();
			}
		}
	}
	private static void printList(List<String> list) {		
		for (int count = 0; count < list.size(); count++) {
			System.out.printf("%s", list.get(count));
		}
	}

	public static void main(String[] args) {
		// add elements in color array to list
		String[] Hearts = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "King", "Queen", "Ace" };
		String[] Spades = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "King", "Queen", "Ace" };
		String[] Clubs = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "King", "Queen", "Ace" };
		String[] Diamonds = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "King", "Queen", "Ace" };
		// Adding
		List<String> mixedList = new ArrayList<String>();
		for (String heart : Hearts)
			mixedList.add(heart);
		for (String spade : Spades)
			mixedList.add(spade);
		for (String club : Clubs)
			mixedList.add(club);
		for (String diamond : Diamonds)
			mixedList.add(diamond);
		// Selecting
		System.out.println("ArrayList:");
		printList(mixedList);

		String[] removeCards = { "A", "Ace", "Queen" };
		List<String> removedList = new ArrayList<String>();
		for (String removeCard : removeCards)
			removedList.add(removeCard);

		// remove cards from the list removecards
		removeCards(mixedList, removedList);

		System.out.println("\nAfter removed ArrayList:");
		printList(mixedList);

	}

}
