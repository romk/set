package net.rnadesign.set;

import java.util.Collection;

/**
 * Created by romke on 8/8/16.
 */
public class Triple
{
	private final Card C0;
	private final Card C1;
	private final Card C2;


	public Triple(Card c0, Card c1, Card c2)
	{
		C0 = c0;
		C1 = c1;
		C2 = c2;
	}

	public Triple(Card card0, Card card1)
	{
		C0=card0;
		C1=card1;
		C2=complete(card0, card1);
	}

	private Card complete(Card card0, Card card1)
	{
		Card card2 =  card0.copy();

		for (int i = 0; i < card2.getN(); i++)
		{
			int c0 = card0.getCharacteristics()[i];
			int c1 = card1.getCharacteristics()[i];
			if (c0 != c1)
			{
				card2.getCharacteristics()[i] = 3 - c0 - c1;
			}
		}
		return card2;
	}

	public boolean isSet()
	{
		return C0.equals(complete(C1, C2));
	}


	public Card getCard0()
	{
		return C0;
	}

	public Card getCard1()
	{
		return C1;
	}

	public Card getCard2()
	{
		return C2;
	}
}
