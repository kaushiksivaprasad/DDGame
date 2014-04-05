package com.game.models;

public class ItemFactory 
{
	private TileInformation tileInformation;
	private Item item;
	public ItemFactory(String name, TileInformation tileInformation)
	{
		this.tileInformation = tileInformation;
		if(name.equalsIgnoreCase("Ring"))
		{
			item = tileInformation.getRing();
		}
		if(name.equalsIgnoreCase("Armour"))
		{
			item = tileInformation.getArmour();
		}
		if(name.equalsIgnoreCase("Weapon"))
		{
			item = tileInformation.getWeapon();
		}
		
	}
	public Item getItem() 
	{
		return item;
	}
}
