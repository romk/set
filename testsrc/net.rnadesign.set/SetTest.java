package net.rnadesign.set;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by romke on 8/8/16.
 */
public class SetTest
{
	private Set set;

	@Before
	public void before()
	{
		set = new Set(4, true);
	}

	@Test
	public void test()
	{
		set.getMaxNosetHandSize();
	}

	@Test
	public void testFullDeck()
	{
		int size = set.getFullDeck(4).size();

		assertEquals(81, size);
	}

	@Test
	public void testCountSets()
	{
		Collection<Card> hand = new ArrayList<>();
		hand.add(new Card(new int[]{0, 1, 0, 0}));
		hand.add(new Card(new int[]{0, 1, 1, 2}));
		hand.add(new Card(new int[]{0, 1, 2, 1}));
		hand.add(new Card(new int[]{0, 1, 0, 1}));

		int n = set.findSets(hand).size();

		assertEquals(1, n);

	}

	@Test
	public void testZeroDimensions()
	{
		Collection<Card> hand = new HashSet<>();
		hand.add(new Card(new int[]{}));

		int n = set.findSets(hand).size();

		assertEquals(0, n);

	}

	@Test
	public void testOneDimension()
	{
		Collection<Card> hand = new HashSet<>();
		hand.add(new Card(new int[]{0}));
		hand.add(new Card(new int[]{1}));
		hand.add(new Card(new int[]{2}));

		int n = set.findSets(hand).size();

		assertEquals(1, n);

	}

	@Test
	public void testRecurseSingleDimension()
	{

		Hand hand = new Hand();
		hand.add(new Card(new int[]{0}));
		hand.add(new Card(new int[]{1}));
		hand.add(new Card(new int[]{2}));

		Collection<Hand> results = set.findSetfreeHands(hand);

		assertEquals(3, results.size());

		// verify no sets
		for (Hand result : results)
		{
			assertEquals(0, set.findSets(hand).size());
		}
	}

	@Test
	public void testRecurseDimensions()
	{

		int n = 3;

		Hand hand = set.getFullDeck(n);

		Collection<Hand> results = set.findSetfreeHands(hand);

		assertFalse(results.isEmpty());

		// verify no sets
		for (Hand result : results)
		{
			assertEquals(0, set.findSets(hand).size());
		}
	}

	@Test
	public void testFindMaximal()
	{

		int n = 10;

		Set set = new Set(n, false);

		Hand hand = set.getFullDeck(n);

		Collection<Hand> results = set.findSetfreeHands(hand);

		assertFalse(results.isEmpty());

		// verify no sets
		for (Hand result : results)
		{
			assertEquals(0, set.findSets(hand).size());
		}

		assertEquals(20, results.iterator().next().size());
	}

}
