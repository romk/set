package net.rnadesign.set;

import java.util.*;

/**
 * Created by romke on 8/8/16.
 */
public class Set
{
	private final int n;
	private final boolean keepAll;
	private int minLengthToKeep = 0;
	private int minLengthToLog = 0;

	public Set(int n, boolean keepAll)
	{
		this.n = n;
		this.keepAll = keepAll;
	}

	public void getMaxNosetHandSize()
	{

	}

	public Hand getFullDeck(int n)
	{
		Hand deck = new Hand();

		CardIterator i = new CardIterator(n);

		while (i.hasNext())
		{
			deck.add(i.next());
		}

		return deck;

	}

	public Collection<Triple> findSets(Collection<Card> hand)
	{
		Collection<Triple> triples = new ArrayList<>();
		for (TripleIterator i = new TripleIterator(hand); i.hasNext(); )
		{
			Triple triple = i.next();
			if (triple.isSet())
			{
				triples.add(triple);
			}
		}
		return triples;
	}

	/**
	 * @param hand
	 * @return
	 */
	public Collection<Hand> findSetfreeHands(Hand hand)
	{
		// recurse
		return recurse(new Hand(), new Hand(), hand);
	}

	/**
	 * @param setfree            set free collection of cards
	 * @param visitedAdditions   collection of singleton extensions (of setfree)
	 * @param remainingAdditions collection of singleton extensions
	 *                           <p/>
	 *                           preconditions:
	 *                           visitedAdditions U remainingAdditions equals all singleton extensions of setfree
	 *                           visitedAdditions DISJUNCT remainingAdditions
	 * @return collection of maximal set free extensions contained in setfree U remainingAdditions.
	 * (maximal: there is no set free extension)
	 */
	protected Collection<Hand> recurse(Hand setfree, Hand visitedAdditions, Hand remainingAdditions)
	{

		Collection<Hand> result = new ArrayList<>();


		if (visitedAdditions.isEmpty() && remainingAdditions.isEmpty())
		{
			// maximal, because there are no extensions
			result.add(setfree);

			log(setfree);
		}

		Iterator<Card> iterator = remainingAdditions.iterator();

		// iterate remaining additions
		while (iterator.hasNext())
		{
			Card card = iterator.next();

			iterator.remove();

			// recurse and keep
			keepResults(
					result,
					recurse(
							extend(setfree, card),
							filter(setfree, card, visitedAdditions),
							filter(setfree, card, remainingAdditions)));

			// done visiting
			visitedAdditions.add(card);

		}

		return result;
	}

	private void log(Hand hand)
	{
		int length = hand.size();
		if (length >= minLengthToLog)
		{
			List<Card> list = Arrays.asList(hand.toArray(new Card[hand.size()]));
			Collections.sort(list);
			System.out.println("#" + length + ": " + list);
			minLengthToLog = length;
		}
	}

	private void keepResults(Collection<Hand> result, Collection<Hand> newResult)
	{
		// union
		result.addAll(newResult);


		for (Hand hand : result)
		{
			minLengthToKeep = Math.max(minLengthToKeep, hand.size());
		}

		if (!keepAll)
		{

			Iterator<Hand> i = result.iterator();
			while (i.hasNext())

			{
				Hand next = i.next();
				if (next.size() < minLengthToKeep)
				{
					i.remove();
				}
			}
		}
	}

	private Hand filter(Hand setfree, Card card0, Hand additions)
	{
		Hand hand = new Hand();
		hand.addAll(additions);
		hand.remove(card0);
		for (Card card1 : setfree)
		{
			hand.remove(new Triple(card0, card1).getCard2());
		}
		return hand;
	}

	private Hand extend(Hand hand, Card card)
	{
		Hand result = new Hand();
		result.addAll(hand);
		result.add(card);
		return result;
	}
}
