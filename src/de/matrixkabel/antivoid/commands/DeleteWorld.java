package de.matrixkabel.antivoid.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.matrixkabel.antivoid.main.AntiVoid;

public class DeleteWorld implements CommandExecutor{
	
	static FileConfiguration config = AntiVoid.getPlugin().getConfig();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			String worldname = player.getWorld().getName();
			
			if(!player.hasPermission("antivoid.deleteworld")) {
				
				player.sendMessage("§7[§aAnti-Void§7] >> §cYou dont have the Permission for that command!");
				
				return false;
			}
			
			if(args.length < 0) {
				
				player.sendMessage("§7[§aAnti-Void§7] >> §cPlease use this command like this: \n §d/deleteworld");
				
				return true;
			}
			
			playerMessage(player, worldname);
			
		}
		
		return false;
	}
	
	public static void playerMessage(Player player, String worldname) {
		
		if(config.isSet(worldname+".respawn")) {
			
			player.sendMessage("§7[§aAnti-Void§7] >> §aThe world has been delete!");
			
			deleteWorldSetting(worldname);
			
		}else {
			
			player.sendMessage("§7[§aAnti-Void§7] >> §cThe world doesn´t exist!");
			
		}
		
	}
	
	public static void deleteWorldSetting(String worldname) {
		
		config.set(worldname+".respawn", null);
		
		config.set(worldname+".Config.respawn?", false);
		
		AntiVoid.getPlugin().saveConfig();
		
	}
	
}
