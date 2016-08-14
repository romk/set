package net.rnadesign.set;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by romke on 8/8/16.
 */
public class TripleIteratorTest
{

	@Test
	public void testEmpty()
	{
		Collection<Card> hand = new ArrayList<>();

		int count = 0;
		for(TripleIterator i = new TripleIterator(hand); i.hasNext(); i.next())
		{
			count++;
		}

		assertEquals(0, count);

	}

	@Test
	public void testFour()
	{
		Collection<Card> hand = new ArrayList<>();
		hand.add(new Card(new int[]{0, 1, 0, 0}));
		hand.add(new Card(new int[]{0, 1, 1, 2}));
		hand.add(new Card(new int[]{0, 1, 2, 1}));
		hand.add(new Card(new int[]{0, 1, 0, 1}));

		int count = 0;
		for(TripleIterator i = new TripleIterator(hand); i.hasNext(); i.next())
		{
			count++;
		}

		assertEquals(4, count);

	}
}
