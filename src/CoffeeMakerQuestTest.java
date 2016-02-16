import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Scanner;


import org.junit.Test;
import org.mockito.Mockito;

public class CoffeeMakerQuestTest
{
	// The quest should start in the south-most room.
	// Make a CoffeeMakerQuest. The current room should
	// not have a south door.
	@Test
	public void testCMQStartInSouthmostRoom()
	{
		CoffeeMakerQuest CMQ = new CoffeeMakerQuest();
		assertFalse(CMQ.getCurrentRoom().hasSouthDoor());
	}
	
	// The quest should start with an empty inventory.
	// Make a CoffeeMakerQuest. The inventory should be
	// empty.
	@Test
	public void testCMQStartWithEmptyInventory()
	{
		CoffeeMakerQuest CMQ = new CoffeeMakerQuest();
		assertTrue(CMQ.getInventory().isEmpty());
	}
	
	// Moving north through a north door should make the room
	// to the north the current room. Make a CoffeeMakerQuest
	// and get the current room. Then move north. The current
	// room should be the room to the north of the first room.
	@Test
	public void testCMQMoveNorthWithNorthDoor()
	{
		CoffeeMakerQuest CMQ = new CoffeeMakerQuest();
		Room first = CMQ.getCurrentRoom();
		CMQ.moveNorth();
		assertEquals(CMQ.getCurrentRoom(), first.getNorthRoom());
	}
	
	// Moving north without a north door should not change the
	// current room. Make a CoffeeMakerQuest and move north until
	// getting to a room with no north door. Get the current room.
	// Then move north. The current room should not have changed.
	@Test
	public void testCMQMoveNorthWithoutNorthDoor()
	{
		CoffeeMakerQuest CMQ = new CoffeeMakerQuest();
		while (CMQ.getCurrentRoom().hasNorthDoor())
			CMQ.moveNorth();
		Room first = CMQ.getCurrentRoom();
		CMQ.moveNorth();
		assertEquals(CMQ.getCurrentRoom(), first);
	}
	
	// Moving south through a south door should make the room to
	// the south become the current room. Make a CoffeeMakerQuest.
	// Move north so that there is a southern door. Get the current
	// room and move south. The current room should be the room to
	// the south of the previous room.
	@Test
	public void testCMQMoveSouthWithSouthDoor()
	{
		CoffeeMakerQuest CMQ = new CoffeeMakerQuest();
		CMQ.moveNorth();
		Room prev = CMQ.getCurrentRoom();
		CMQ.moveSouth();
		assertEquals(prev.getSouthRoom(), CMQ.getCurrentRoom());
	}
	
	// Moving south without a south door should not change the current
	// room. Make a CoffeeMakerQuest, get the current room, and move
	// south. The current room should not have changed.
	@Test
	public void testCMQMoveSouthWithoutSouthDoor()
	{
		CoffeeMakerQuest CMQ = new CoffeeMakerQuest();
		Room first = CMQ.getCurrentRoom();
		CMQ.moveSouth();
		assertEquals(first, CMQ.getCurrentRoom());
	}
	
	// Looking around in a room with no items should leave the
	// inventory unchanged. Make a CoffeeMakerQuest. Clear the
	// current room of items, then look around. The inventory
	// should still be empty.
	@Test
	public void testCMQLookAroundNoItems()
	{
		CoffeeMakerQuest CMQ = new CoffeeMakerQuest();
		Items noItems = Mockito.mock(Items.class);
		when(noItems.hasCoffee()).thenReturn(false);
		when(noItems.hasCream()).thenReturn(false);
		when(noItems.hasSugar()).thenReturn(false);
		CMQ.getCurrentRoom().setRoomItems(noItems);
		CMQ.lookAround();
		assertTrue(CMQ.getInventory().isEmpty());
	}
	
	// Looking around in a room that has items should add
	// the items to the inventory. Make a CoffeeMakerQuest.
	// Set the current room to have an item. Look around.
	// The inventory should no longer be empty.
	@Test
	public void testCMQLookAroundAcquireItem()
	{
		CoffeeMakerQuest CMQ = new CoffeeMakerQuest();
		Items oneItem = Mockito.mock(Items.class);
		when(oneItem.hasCoffee()).thenReturn(false);
		when(oneItem.hasCream()).thenReturn(true);
		when(oneItem.hasSugar()).thenReturn(false);
		CMQ.getCurrentRoom().setRoomItems(oneItem);
		CMQ.lookAround();
		assertFalse(CMQ.getInventory().isEmpty());
	}
	
	// Attempting to drink coffee with all the ingredients
	// should end the game with a win. Make a CoffeeMakerQuest,
	// add all items to the inventory, and drink coffee. The game
	// should not be running.
	@Test
	public void testCMQDrinkCoffeeAllIngredients()
	{
		CoffeeMakerQuest CMQ = new CoffeeMakerQuest();
		CMQ.setRunning(true);
		Items allItems = Mockito.mock(Items.class);
		when(allItems.hasCoffee()).thenReturn(true);
		when(allItems.hasCream()).thenReturn(true);
		when(allItems.hasSugar()).thenReturn(true);
		CMQ.getCurrentRoom().setRoomItems(allItems);
		CMQ.lookAround();
		CMQ.drinkCoffee();
		assertFalse(CMQ.isRunning());
	}
	
	// Attempting to drink coffee without all the ingredients
	// should end the game with a lose. Make a CoffeeMakerQuest,
	// and drink coffee. The game should not be running.
	@Test
	public void testCMQDrinkCoffeeNotAllIngredients()
	{
		CoffeeMakerQuest CMQ = new CoffeeMakerQuest();
		CMQ.setRunning(true);
		CMQ.drinkCoffee();
		assertFalse(CMQ.isRunning());
	}
	
	// Executing 0 as a command should stop the program running.
	// Make a CoffeeMakerQuest and set it as running.
	// Execute the 0 command. It should no longer be running.
	@Test
	public void testCMQExecuteCommandZero()
	{
		CoffeeMakerQuest CMQ = new CoffeeMakerQuest();
		CMQ.setRunning(true);
		Character zero = 0;
		CMQ.executeCommand(zero);
		assertFalse(CMQ.isRunning());
	}
}
