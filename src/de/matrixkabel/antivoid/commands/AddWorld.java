package de.matrixkabel.antivoid.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.matrixkabel.antivoid.main.AntiVoid;

public class AddWorld implements CommandExecutor{
	
	static FileConfiguration config = AntiVoid.getPlugin().getConfig();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			
			Player player = (Player) sender;
			
			if(!player.hasPermission("antivoid.addworld")) {
				
				player.sendMessage("§7[§aAnti-Void§7] >> §cYou dont have the Permission for that command!");
				
				return true;
			}
			
			if(args.length < 0) {
				
				player.sendMessage("§7[§aAnti-Void§7] >> §cPlease use this command like this: \n §d/addworld");
				
				return true;
			}
			
			String worldname = player.getWorld().getName();
			
			playerMessage(worldname, player);
			
			setWorldSpawn(player, worldname);
		}
		return false;
	}
	
	public static void playerMessage(String worldname, Player player) {
		
		
		
		if(config.isSet(worldname+".Config.respawn?")) {
			
			player.sendMessage("§7[§aAnti-Void§7] >> §aThe World has been updatet!");
			
		}else {
			
			player.sendMessage("§7[§aAnti-Void§7] >> §aThe World has been added!");
			
		}
		
	}
	
	public static void setWorldSpawn(Player player, String worldname) {
		
		config.set(player.getWorld().getName()+".Config.respawn-World?", worldname);
		
		config.set(player.getWorld().getName()+".Config.respawn?", true);
		
		config.set(player.getWorld().getName()+".respawn.X",player.getLocation().getX());
		
		config.set(player.getWorld().getName()+".respawn.Y",player.getLocation().getY());
		
		config.set(player.getWorld().getName()+".respawn.Z",player.getLocation().getZ());
		
		config.set(player.getWorld().getName()+".respawn.yaw",player.getLocation().getYaw());

		config.set(player.getWorld().getName()+".respawn.pitch",player.getLocation().getPitch());
		
		AntiVoid.getPlugin().saveConfig();
		
	}

}
