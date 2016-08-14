package net.rnadesign.set;

/**
 * Created by romke on 8/8/16.
 */
public class CardIterator implements java.util.Iterator<Card>
{
	final int VALUES = 3;

	private final int n;
	private Card card;


	public CardIterator(int n)
	{
		this.n = n;
		card = null;
	}


	@Override
	public boolean hasNext()
	{
		if (card == null)
		{
			return true;
		}
		int[] characteristics = card.getCharacteristics();
		int i = 0;
		while (i < n && characteristics[i] == VALUES - 1)
		{
			i++;
		}
		return i < n;
	}

	@Override
	public Card next()
	{
		if (card == null)
		{
			card = new Card(n);
		} else
		{
			card = card.copy();

			int[] characteristics = card.getCharacteristics();

			characteristics[0]++;

			int i = 0;

			while (characteristics[i] == VALUES)
			{
				// carry
				characteristics[i++] = 0;

				if (i == n)
				{
					throw new IndexOutOfBoundsException();
				}
				characteristics[i]++;
			}
		}
		return card;
	}

}