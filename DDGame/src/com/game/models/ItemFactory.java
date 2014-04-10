package com.game.models;

/**
 * this class is used to return item with particular type
 * @author º«ÐÅ
 *
 */
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
	/**
	 * this is the geter for item
	 * @return
	 */
	public Item getItem() 
	{
		return item;
	}
}
