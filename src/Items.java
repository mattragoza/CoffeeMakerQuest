
public class Items
{
	// just represent the presence or absence of each item
	private Boolean coffee;
	private Boolean cream;
	private Boolean sugar;
	
	public Items(Boolean coffee, Boolean cream, Boolean sugar)
	{
		if (coffee == null || cream == null || sugar == null)
			throw new IllegalArgumentException("no item can be null");
		this.coffee = coffee;
		this.cream = cream;
		this.sugar = sugar;
	}
	
	// check if no items are present
	public Boolean isEmpty()
	{
		return !(this.coffee || this.cream || this.sugar);
	}
	
	// check if all items are present
	public Boolean canMakeCoffee()
	{
		return this.coffee && this.cream && this.sugar;
	}
	
	public Boolean hasCoffee()
	{
		return this.coffee;
	}
	
	public Boolean hasCream()
	{
		return this.cream;
	}
	
	public Boolean hasSugar()
	{
		return this.sugar;
	}
	
	// acquire items from another Items instance
	public void acquireItems(Items items)
	{
		if (items == null)
			throw new IllegalArgumentException("can't acquire items from null");
		this.coffee |= items.hasCoffee();
		this.cream |= items.hasCream();
		this.sugar |= items.hasSugar();
		items.clear();
	}
	
	public void clear()
	{
		this.coffee = false;
		this.cream = false;
		this.sugar = false;
	}
}
