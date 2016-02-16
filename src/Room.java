
public class Room
{
	private String roomDesc; // description of the room
	private String furnDesc; // description of the furniture
	private Room northRoom; // room to the north
	private Room southRoom; // room to the south
	private Items roomItems; // items in the room
	
	public Room(String roomDesc, String furnDesc, Items roomItems)
	{
		if (roomDesc == null)
			throw new IllegalArgumentException("must provide room description string");
		this.roomDesc = roomDesc;
		
		if (furnDesc == null)
			throw new IllegalArgumentException("must provide furniture description string");
		this.furnDesc = furnDesc;
		
		if (roomItems == null)
			throw new IllegalArgumentException("roomItems cannot be null");
		this.roomItems = roomItems;
	}
	
	public String getRoomDesc()
	{
		return this.roomDesc;
	}
	
	public String getFurnDesc()
	{
		return this.furnDesc;
	}
	
	public Boolean hasNorthDoor()
	{
		return this.northRoom != null;
	}
	
	public Boolean hasSouthDoor()
	{
		return this.southRoom != null;
	}
	
	public Room getNorthRoom()
	{
		return this.northRoom;
	}
	
	public Room getSouthRoom()
	{
		return this.southRoom;
	}
	
	public void setNorthRoom(Room room)
	{
		this.northRoom = room;
	}
	
	public void setSouthRoom(Room room)
	{
		this.southRoom = room;
	}
	
	public void addNorthRoom(Room room)
	{
		if (room == null)
			throw new IllegalArgumentException("cannot add null as room");
		if (this.hasNorthDoor())
			this.getNorthRoom().addNorthRoom(room);
		else
			this.setNorthRoom(room);
			room.setSouthRoom(this);
	}
	
	public Items getRoomItems()
	{
		return this.roomItems;
	}
	
	public void setRoomItems(Items items)
	{
		this.roomItems = items;
	}
}
