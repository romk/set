package net.rnadesign.set;

import java.util.Arrays;

/**
 * Created by romke on 8/8/16.
 */
public class Card
{
	private final int n;
	private int[] characteristics;


	public Card(int n)
	{
		this.n = n;
		characteristics = new int[n];
	}

	public Card(int[] characteristics)
	{
		this.n = characteristics.length;
		this.characteristics = characteristics;
	}

	public int[] getCharacteristics()
	{
		return characteristics;
	}

	public Card copy()
	{
		Card card = new Card(n);
		for (int i = 0; i < n; i++)
		{
			card.characteristics[i] = this.characteristics[i];
		}
		return card;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Card card = (Card) o;

		if (n != card.n) return false;
		return Arrays.equals(characteristics, card.characteristics);

	}

	@Override
	public int hashCode()
	{
		int result = 0;
		for (int element : characteristics)
			result = 3 * result + element;

		return result;
	}

	@Override
	public String toString()
	{
		if (characteristics == null)
			return "null";
		if (characteristics.length == 0)
			return "";

		StringBuilder b = new StringBuilder();
		for (int i = 0; i< characteristics.length; i++) {
			b.append(characteristics[i]);
		}

		return b.toString();
	}

	public int getN()
	{
		return n;
	}
}
