import static org.junit.Assert.*;

import org.junit.Test;

public class RoomTest
{
	// A Room should specify a room description string.
	// Make a Room with a null room description. An exception
	// should be raised.
	@Test
	public void testRoomConstructorNullRoomDesc()
	{
		try
		{
			new Room(null, "test", new Items(false, false, false));
			fail();
		}
		catch (IllegalArgumentException e) {}
	}
	
	// A Room should specify a non-null furniture description string.
	// Make a Room with a null furniture description. An exception
	// should be raised.
	@Test
	public void testRoomConstructorNullFurnDesc()
	{
		try
		{
			new Room("test", null, new Items(false, false, false));
			fail();
		}
		catch (IllegalArgumentException e) {}
	}
	
	// A Room should have a non-null Items object representing what
	// items are there. Make a Room with null items. An exception
	// should be raised.
	@Test
	public void testRoomConstructorNullRoomItems()
	{
		try
		{
			new Room("test", "test", null);
			fail();
		}
		catch (IllegalArgumentException e) {}
	}
	
	// Adding a Room to the north of a Room should make a doorway to the
	// north leading to the added Room. Make two Rooms, and add the second
	// one to the north of the first. The first should have a north door
	// that leads to the second.
	@Test
	public void testRoomAddNorthRoomNorthDoor()
	{
		Room one = new Room("one", "one", new Items(false, false, false));
		Room two = new Room("two", "two", new Items(false, false, false));
		one.addNorthRoom(two);
		assertTrue(one.hasNorthDoor() && one.getNorthRoom() == two);
	}
	
	// Adding a Room to the north of a Room should make a doorway in the
	// added room to the south, leading to the first room. Make two Rooms,
	// and add the second one to the north of the first. The second should
	// have a south door that leads to the first.
	@Test
	public void testRoomAddNorthRoomSouthDoor()
	{
		Room one = new Room("one", "one", new Items(false, false, false));
		Room two = new Room("two", "two", new Items(false, false, false));
		one.addNorthRoom(two);
		assertTrue(two.hasSouthDoor() && two.getSouthRoom() == one);
	}
	
	// Adding null as a room should not be possible. Make a Room, and add
	// null to the north. This should cause an exception.
	@Test
	public void testRoomAddNorthRoomNull()
	{
		Room one = new Room("one", "one", new Items(false, false, false));
		try
		{
			one.addNorthRoom(null);
			fail();
		}
		catch (IllegalArgumentException e) {}
	}
	
	@Test
	public void testRooGetRoomDesc()
	{
		Room room = new Room("room", "furn", new Items(false, false, false));
		assertEquals(room.getRoomDesc(), "room");
	}
	
	@Test
	public void testRooGetFurnDesc()
	{
		Room room = new Room("room", "furn", new Items(false, false, false));
		assertEquals(room.getFurnDesc(), "furn");
	}
	
	// Adding a north room to a Room that already has a north room should
	// recursively try to add it to the north room. Make three Rooms.
	// add the second Room to the first. Then add the third Room to the 
	// first. The first should have the second Room to the north, and the
	// second should have the third Room to the north.
	@Test
	public void testRoomAddNorthRoomRecursion()
	{
		Room one = new Room("one", "one", new Items(false, false, false));
		Room two = new Room("two", "two", new Items(false, false, false));
		Room three = new Room("three", "three", new Items(false, false, false));
		one.addNorthRoom(two);
		one.addNorthRoom(three);
		assertTrue(one.getNorthRoom() == two && two.getNorthRoom() == three);
	}

}
