import static org.junit.Assert.*;

import org.junit.Test;

public class ItemsTest
{
	// An Items object should specify each item as true or false, not null.
	// Create Items with null arguments an expect an exception.
	@Test
	public void testItemsConstructorNull()
	{
		try
		{
			new Items(null, null, null);
			fail();
		}
		catch (IllegalArgumentException e) {}
	}
	
	// Items can make coffee if all three items are collected.
	// Make an Items with all three. It should be able to make coffee.
	@Test
	public void testItemsCanMakeCoffeeIsTrue()
	{
		Items items = new Items(true, true, true);
		assertTrue(items.canMakeCoffee());
	}
	
	// Items cannot make coffee if all three items are not collected.
	// Make an Items that doesn't have all three. It should not be able to make coffee.
	@Test
	public void testItemsCanMakeCoffeeIsFalse()
	{
		Items items = new Items(true, true, false);
		assertFalse(items.canMakeCoffee());
	}
	
	// Items should be empty if all three items are absent.
	// Make an Items with all items absent. It should be empty.
	@Test
	public void testItemsIsEmptyIsTrue()
	{
		Items items = new Items(false, false, false);
		assertTrue(items.isEmpty());
	}
	
	// Item should not be empty if an item is present.
	// Make an Items with one item present. It should not be empty.
	@Test
	public void testItemsIsEmptyIsFalse()
	{
		Items items = new Items(true, false, false);
		assertFalse(items.isEmpty());
	}

	// Clearing an Items should make it empty.
	// Make an Items with items present, then clear it. It should be empty.
	@Test
	public void testItemsClearBecomesEmpty() {
		Items items = new Items(true, true, true);
		items.clear();
		assertTrue(items.isEmpty());
	}
	
	// Acquiring items should move the items from one Items to the other.
	// Make an Items with an item present. Make another Items without that item.
	// Have the second Items acquire from the first. The item should have traded.
	@Test
	public void testItemsAquireItemsGetsItem()
	{
		Items from = new Items(true, false, false);
		Items to = new Items(false, false, false);
		to.acquireItems(from);
		assertTrue(to.hasCoffee() && !from.hasCoffee());
	}
	
	// Acquiring items from an empty Items should have no effect.
	// Make two Items with no items present. Have one acquire from
	// the first. Both should be empty still.
	@Test
	public void testItemsAquireItemsDoesNothing()
	{
		Items from = new Items(false, false, false);
		Items to = new Items(false, false, false);
		to.acquireItems(from);
		assertTrue(to.isEmpty() && from.isEmpty());
	}
	
	@Test
	// Acquiring items from null should not be possible.
	// Make an Items and have it acquire null. It should
	// raise an argument exception.
	public void testItemsAcquireNull()
	{
		Items items = new Items(false, false, false);
		try
		{
			items.acquireItems(null);
			fail();
		}
		catch (IllegalArgumentException e) {}
	}
}
