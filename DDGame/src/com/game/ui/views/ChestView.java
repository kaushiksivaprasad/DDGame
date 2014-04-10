package com.game.ui.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.game.models.Armour;
import com.game.models.Configuration;
import com.game.models.GameCharacter;
import com.game.models.Inventory;
import com.game.models.Item;
import com.game.models.Player;
import com.game.models.Ring;
import com.game.models.TileInformation;
import com.game.models.Weapon;

/**
 * this class is used for create chest view 
 * @author º«ÐÅ
 *
 */
public class ChestView extends JDialog implements ActionListener{
	TileInformation tileInformation;
	JCheckBox chkBox[] = new JCheckBox[3];
	GameCharacter player = null;
	int size = 0;
	LinkedList<Item> tileInfoItems = new LinkedList<>();
	public ChestView(TileInformation tileInfo, GameCharacter player) {
		this.tileInformation = tileInfo;
		this.player = player;
		doGui();
	}

	/**
	 * this method is used to create the UI of the chest view
	 */
	public void doGui() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel notePnl = new JPanel();
		notePnl.setLayout(new BoxLayout(notePnl, BoxLayout.X_AXIS));
		JLabel lbl = new JLabel("Contents of this chest :");
		notePnl.add(lbl);
		notePnl.add(Box.createHorizontalGlue());
		panel.add(Box.createVerticalGlue());
		panel.add(notePnl);
		panel.add(Box.createVerticalStrut(20));
		JPanel temp = constructPanelForEachItem(tileInformation.getArmour());
		if(temp != null){
			panel.add(temp);
			panel.add(Box.createVerticalStrut(20));
		}
		temp = constructPanelForEachItem(tileInformation.getRing());
		if(temp != null){
			panel.add(temp);
			panel.add(Box.createVerticalStrut(20));
		}
		temp = constructPanelForEachItem(tileInformation.getWeapon());
		if(temp != null){
			panel.add(temp);
			panel.add(Box.createVerticalStrut(20));
		}
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
		btnPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		btnPanel.add(Box.createHorizontalGlue());
		JButton button = new JButton("Fetch");
		button.setPreferredSize(new Dimension(100,30));
		button.addActionListener(this);
		btnPanel.add(button);
		btnPanel.add(Box.createHorizontalGlue());
		panel.add(btnPanel);
		panel.add(Box.createVerticalGlue());
		add(panel);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();
//		setSize(new Dimension(400, 300));
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		TileInformation info = new TileInformation();
		Armour armr = new Armour();
		armr.setName("Armr1");
		armr.setModifierInForce(Configuration.modifiersList[Configuration.modifiers.Intelligence.ordinal()]);
		armr.setIntelligenceModifier(5);
		info.setArmour(armr);
		Ring ring = new Ring();
		ring.setName("Ring1");
		ring.setModifierInForce(Configuration.modifiersList[Configuration.modifiers.Charisma.ordinal()]);
		ring.setCharismaModifer(5);
		Weapon wpn = new Weapon();
		wpn.setName("Weapon 1");
		wpn.setStrengthModifer(5);
		wpn.setModifierInForce(Configuration.modifiersList[Configuration.modifiers.Attack.ordinal()]);
		info.setRing(ring);
		info.setWeapon(wpn);
		GameCharacter player = new GameCharacter();
		player.setInventory(new Inventory());
		player.getInventory().setItems(new LinkedList<Item>());
		new ChestView(info, player);
	}
	
	/**
	 * this metohd is used to making information for item
	 * @param item an object of item 
	 * @return the information
	 */
	public String fetchContentsOfTheItem(Item item) {
		String mod = item.getModifierInForce();
		StringBuilder builder = new StringBuilder(item.getName());
		builder.append(" : Increases your ");
		builder.append(item.getModifierInForce());
		builder.append(" by ");
		if (mod.equalsIgnoreCase(Configuration.modifiersList[Configuration.modifiers.Intelligence
				.ordinal()])) {
			builder.append(item.getIntelligenceModifier());
		} else if (mod
				.equalsIgnoreCase(Configuration.modifiersList[Configuration.modifiers.Wisdom
						.ordinal()])) {
			builder.append(item.getWisdomModifier());
		} else if (mod
				.equalsIgnoreCase(Configuration.modifiersList[Configuration.modifiers.Armor
						.ordinal()])) {
			builder.append(item.getArmourPts());
		} else if (mod
				.equalsIgnoreCase(Configuration.modifiersList[Configuration.modifiers.Strength
						.ordinal()])
				|| mod.equalsIgnoreCase(Configuration.modifiersList[Configuration.modifiers.Attack
						.ordinal()])) {
			builder.append(item.getStrengthModifer());
		} else if (mod
				.equalsIgnoreCase(Configuration.modifiersList[Configuration.modifiers.Constitution
						.ordinal()])) {
			builder.append(item.getConstitutionModifier());
		} else if (mod
				.equalsIgnoreCase(Configuration.modifiersList[Configuration.modifiers.Charisma
						.ordinal()])) {
			builder.append(item.getCharismaModifer());
		} else if (mod
				.equalsIgnoreCase(Configuration.modifiersList[Configuration.modifiers.Dexterity
						.ordinal()])
				|| mod.equalsIgnoreCase(Configuration.modifiersList[Configuration.modifiers.damage
						.ordinal()])) {
			builder.append(item.getDexterityModifer());
		}
		builder.append(" pts ");
		return builder.toString();
	}
	
	/**
	 * this method is used to make panel for item which will display basic information of this item
	 * @param item the item we need make panel
	 * @return the panel we make
	 */
	public JPanel constructPanelForEachItem(Item item) {
		JPanel panel = null;
		if (item != null) {
			chkBox[size] = new JCheckBox();
			tileInfoItems.add(item);
			panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			JLabel armrLbl = new JLabel(fetchContentsOfTheItem(item));
			armrLbl.setPreferredSize(new Dimension(300, 30));
			armrLbl.setMaximumSize(new Dimension(300, 30));
			armrLbl.setMinimumSize(new Dimension(300, 30));
			panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			panel.add(Box.createHorizontalGlue());
			panel.add(armrLbl);
			panel.add(Box.createHorizontalStrut(10));
			panel.add(chkBox[size]);
			panel.add(Box.createHorizontalGlue());
			size++;
		}
		return panel;
	}
	
	/**
	 * this method is used to implement the function of chest view
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Inventory inv = player.getInventory();
		LinkedList<Item> items = null;
		if(inv != null){
			items = inv.getItems();
			if(items == null)
			{
				items = new LinkedList<Item>();
			}
		}
		else{
			inv = new Inventory();
			items = new LinkedList<Item>();
		}
		int y = 0;
		for(int i = 0; i < tileInfoItems.size(); i++){
			if(chkBox[i].isSelected()){
				Item item = tileInfoItems.get(i);
				items.add(item);
				y++;
				if(item instanceof Armour){
					tileInformation.setArmour(null);
				}
				else if(item instanceof Ring){
					tileInformation.setRing(null);
				}
				else if(item instanceof Weapon){
					tileInformation.setWeapon(null);
				}
//				if(i == 0){
//					tileInformation.setArmour(null);
//				}
//				else if(i == 1){
//					tileInformation.setRing(null);
//				}
//				else if(i == 2){
//					tileInformation.setWeapon(null);
//				}
			}
		}
		if(y == tileInfoItems.size()){
			tileInformation.setChest(false);
		}
		inv.setItems(items);
		player.setInventory(inv);
		this.dispose();
	}
}
