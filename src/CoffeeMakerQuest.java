import java.util.*;

public class CoffeeMakerQuest
{
	private Room currentRoom;
	private Items inventory;
	private Boolean running;
	
	public CoffeeMakerQuest()
	{
		this.currentRoom = new Room("a circular room", "an elegant armchair", new Items(true, false, false));
		this.currentRoom.addNorthRoom(new Room("a green room", "a large TV", new Items(false, false, false)));
		this.currentRoom.addNorthRoom(new Room("a furry room", "a groovy beanbag", new Items(false, false, false)));
		this.currentRoom.addNorthRoom(new Room("a throne room", "a royal table", new Items(false, false, false)));
		this.currentRoom.addNorthRoom(new Room("an active room", "a tall bed", new Items(false, true, false)));
		this.currentRoom.addNorthRoom(new Room("a pool room", "a wet countertop", new Items(false, false, true)));
		this.inventory = new Items(false, false, false);
		this.running = false;
	}
	
	public void setRunning(Boolean value)
	{
		this.running = value;
	}
	
	public Boolean isRunning()
	{
		return this.running;
	}
	
	public Room getCurrentRoom()
	{
		return this.currentRoom;
	}
	
	public Items getInventory()
	{
		return this.inventory;
	}

	public void moveNorth()
	{
		if (this.currentRoom.hasNorthDoor())
		{
			System.out.println("You walk through the north door.");
			this.currentRoom = this.currentRoom.getNorthRoom();
		}
		else
			System.out.println("The room has no north door.");
	}
	
	public void moveSouth()
	{
		if (this.currentRoom.hasSouthDoor())
		{
			System.out.println("You walk through the south door.");
			this.currentRoom = this.currentRoom.getSouthRoom();	
		}
		else
			System.out.println("The room has no south door.");
	}
	
	public void lookAround()
	{
		Items roomItems = this.currentRoom.getRoomItems();
		if (roomItems.isEmpty())
			System.out.println("There's nothing of use here.");
		else
		{
			if (roomItems.hasCoffee())
				System.out.println("You found coffee grounds!");
			if (roomItems.hasCream())
				System.out.println("You found half-and-half!");
			if (roomItems.hasSugar())
				System.out.println("You found packets of sugar!");
			this.inventory.acquireItems(roomItems);
		}
	}
	
	public void describeRoom()
	{
		System.out.println("You are in " + this.currentRoom.getRoomDesc() + ".");
		System.out.println("You see " + this.currentRoom.getFurnDesc() + ".");
		if (this.currentRoom.hasNorthDoor())
			System.out.println("There is a door leading north.");
		if (this.currentRoom.hasSouthDoor())
			System.out.println("There is a door leading south.");
	}
	
	public void checkInventory()
	{
		if (this.inventory.isEmpty())
			System.out.println("You don't have any items.");
		else
		{
			if (this.inventory.hasCoffee())
				System.out.println("You have coffee grounds.");
			if (this.inventory.hasCream())
				System.out.println("You have half-and-half.");
			if (this.inventory.hasSugar())
				System.out.println("You have packets of sugar.");
		}
	}
	
	public void drinkCoffee()
	{
		if (this.inventory.canMakeCoffee())
			System.out.println("You make a delicious cup of coffee and drink it. You win!");
		else
			System.out.println("You don't have all the ingredients! You lose...");
		running = false;
	}
	
	public void showHelp()
	{
		System.out.println("N\tMove north");
		System.out.println("S\tMove south");
		System.out.println("L\tLook around");
		System.out.println("I\tCheck inventory");
		System.out.println("D\tDrink coffee");
		System.out.println("H\tShow help message");
	}
	
	public void executeCommand(Character command)
	{
		if (command == 'N')
			this.moveNorth();

		else if (command == 'S')
			this.moveSouth();
		
		else if (command == 'L')
			this.lookAround();
		
		else if (command == 'I')
			this.checkInventory();
		
		else if (command == 'D')
			this.drinkCoffee();
		
		else if (command == 'H')
			this.showHelp();
		
		else if (command == 0)
			running = false;

		else
			System.out.println("What?");
	}
	
	public static Character getCommand(Scanner input)
	{
		System.out.print("> ");
		if (!input.hasNextLine())
			return 0;
			
		String line = input.nextLine();
		if (line.isEmpty())
			return 0;
			
		Character command = Character.toUpperCase(line.charAt(0));	
		return command;
	}
	
	public static void main(String[] args)
	{
		CoffeeMakerQuest quest = new CoffeeMakerQuest();
		System.out.println("CoffeeMakerQuest 2.0");
		System.out.println("Enter N, S, L, I, D, or H");
		Scanner input = new Scanner(System.in);	
		quest.setRunning(true);
		while (quest.isRunning())
		{
			System.out.println("______________________________");
			quest.describeRoom();
			Character command = CoffeeMakerQuest.getCommand(input);
			quest.executeCommand(command);
		}
		input.close();
	}
}