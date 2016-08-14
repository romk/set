package net.rnadesign.set;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by romke on 8/8/16.
 */
public class TripleIterator implements Iterator<Triple>
{
	private final Card[] hand;
	private int i = 0, j = 1, k = 2;

	public TripleIterator(Collection<Card> hand)
	{
		this.hand = hand.toArray(new Card[hand.size()]);
	}

	@Override
	public boolean hasNext()
	{
		return k < hand.length;
	}

	@Override
	public Triple next()
	{
		if (k >= hand.length)
		{
			throw new IndexOutOfBoundsException();
		}
		// create triple
		Triple next = new Triple(hand[i], hand[j], hand[k]);

		// advance to next or beyond
		advance();
		return next;
	}

	private void advance()
	{
		k++;
		if (k == hand.length)
		{
			j++;
			if (j == hand.length - 1)
			{
				i++;
				j = i + 1;
			}
			k = j + 1;
		}
	}
}
