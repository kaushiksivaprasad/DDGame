package com.game.models;
public class ChestBuilder
{
	Player player;
	TileInformation tileInformation;
	public ChestBuilder()
	{
		
	}
	public void setData (TileInformation tileInformation, Player player)
	{	
		this.player = player;
		this.tileInformation = tileInformation;
		
	}
	public TileInformation build()
	{
		changeRingValue().changeArmourValue().changeWeaponValue();
		return tileInformation;
	}
	public ChestBuilder changeRingValue()
	{	
	    Item item= new ItemFactory("Ring", tileInformation).getItem();
	    String str = item.getModifierInForce();
	    if(str.equalsIgnoreCase("Strength"))
	    {
	    	item.setStrengthModifer(player.getLevel());
	    }
	    else if(str.equalsIgnoreCase("Constitution"))
	    {
	    	item.setConstitutionModifier(player.getLevel());
	    }  
	    else if(str.equalsIgnoreCase("Wisdom"))
	    {
	    	item.setWisdomModifier(player.getLevel());
	    }  
	    else if(str.equalsIgnoreCase("Charisma"))
	    {
	    	item.setCharismaModifer(player.getLevel());
	    } 
	    else if(str.equalsIgnoreCase("Armor"))
	    {
	    	item.setArmourPts(player.getLevel());
	    }
	    
	    return this;
	}
	
	public ChestBuilder changeArmourValue()
	{
		Item item = new ItemFactory("Armour",tileInformation).getItem();
		String str = item.getModifierInForce();
	    if(str.equalsIgnoreCase("Intelligence"))
	    {
	    	item.setIntelligenceModifier(player.getLevel());
	    } 
	    else if(str.equalsIgnoreCase("Wisdom"))
	    {
	    	item.setWisdomModifier(player.getLevel());
	    }
	    else if(str.equalsIgnoreCase("Strength"))
	    {
	    	item.setStrengthModifer(player.getLevel());
	    }
	    else if(str.equalsIgnoreCase("Constitution"))
	    {
	    	item.setConstitutionModifier(player.getLevel());
	    }
	    else if(str.equalsIgnoreCase("Dexterity"))
	    {
	    	item.setDexterityModifer(player.getLevel());
	    }
	    else if(str.equalsIgnoreCase("Armor"))
	    {
	    	item.setArmourPts(player.getLevel());
	    }
	    return this;
	}
	
	public ChestBuilder changeWeaponValue()
	{
		Item item = new ItemFactory("Weapon",tileInformation).getItem();
		String str = item.getModifierInForce();
	    if(str.equalsIgnoreCase("Attack"))
	    {
	    	item.setStrengthModifer(player.getLevel());
	    }
	    else if(str.equalsIgnoreCase("Damage"))
	    {
	    	item.setDexterityModifer(player.getLevel());
	    }
	    return this;
	}
}
