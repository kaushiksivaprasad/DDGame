package com.game.models;
/**
 * this is a chestBuilder which is used to build a chest base on player's level
 * @author º«ÐÅ
 *
 */
public class ChestBuilder
{
	GameCharacter player;
	TileInformation tileInformation;
	public ChestBuilder(TileInformation tileInformation, GameCharacter player)
	{
		this.player = player;
		this.tileInformation = tileInformation;
		
	}
	
	/**
	 * change chest information
	 * @return chest after changing
	 */
	public TileInformation build()
	{
		changeRingValue().changeArmourValue().changeWeaponValue();
		return tileInformation;
	}
	
	/**
	 * change the value of Ring based on player's level
	 * @return the object after changing
	 */
	public ChestBuilder changeRingValue()
	{	
	
	    Item item= new ItemFactory("Ring", tileInformation).getItem();
	    
	    if(item !=null){
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
	    
//	    tileInformation.setRing(item);
	   
	    }
	    
	    return this;
	}
	
	/**
	 * change the value of Armour based on player's level
	 * @return the object after changing
	 */
	public ChestBuilder changeArmourValue()
	{
		Item item = new ItemFactory("Armour",tileInformation).getItem();
		
		if(item !=null){
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
	    
		}
	    return this;
	}
	
	/**
	 * change the value of Weapon based on player's level
	 * @return the object after changing
	 */
	public ChestBuilder changeWeaponValue()
	{
		Item item = new ItemFactory("Weapon",tileInformation).getItem();
		if(item !=null){
		String str = item.getModifierInForce();
	    if(str.equalsIgnoreCase("Attack"))
	    {
	    	item.setStrengthModifer(player.getLevel());
	    }
	    else if(str.equalsIgnoreCase("Damage"))
	    {
	    	item.setDexterityModifer(player.getLevel());
	    }
	    
		}
	    return this;
	}
}
