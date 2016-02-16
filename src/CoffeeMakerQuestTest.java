import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

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
		CMQ.getCurrentRoom().getRoomItems().clear();
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
		CMQ.getCurrentRoom().setRoomItems(new Items(false, true, false));
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
		CMQ.getCurrentRoom().setRoomItems(new Items(true, true, true));
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
	
	@Test
	public void testCMQExecuteCommandZero()
	{
		CoffeeMakerQuest CMQ = new CoffeeMakerQuest();
		CMQ.setRunning(true);
		Character zero = 0;
		CMQ.executeCommand(zero);
		assertFalse(CMQ.isRunning());
	}
	
	@Test
	public void testCMQGetCommand()
	{
		CoffeeMakerQuest CMQ = new CoffeeMakerQuest();
		Scanner input = Mockito.mock(Scanner.class);
		CMQ.getCommand(input);
	}
}
