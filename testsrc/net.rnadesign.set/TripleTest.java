package net.rnadesign.set;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by romke on 8/8/16.
 */
public class TripleTest
{
	@Test
	public void testIsSetFalse()
	{
		Triple triple = new Triple(
		new Card(new int[]{0, 1, 0, 0}),
		new Card(new int[]{0, 1, 1, 2}),
		new Card(new int[]{1, 1, 2, 1}));

		boolean b = triple.isSet();

		assertEquals(false, b);

	}

	@Test
	public void testIsSetTrue()
	{
		Triple triple = new Triple(
				new Card(new int[]{0, 1, 0, 0}),
				new Card(new int[]{0, 1, 1, 2}),
				new Card(new int[]{0, 1, 2, 1}));

		boolean b = triple.isSet();

		assertEquals(true, b);

	}

}
