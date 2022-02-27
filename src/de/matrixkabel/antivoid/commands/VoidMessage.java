package de.matrixkabel.antivoid.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import de.matrixkabel.antivoid.main.AntiVoid;

public class VoidMessage implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration config = AntiVoid.getPlugin().getConfig();
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if(!p.hasPermission("antivoid.voidmessage")) {
				
				p.sendMessage("§7[§aAnti-Void§7] >> §cYou dont have the Permission to use this command!");
				
				return true;
			}
			
			if(args.length < 1) {
				
				p.sendMessage("§7[§aAnti-Void§7] >> §cPlease use this command like this: \n §d/voidmessage {message}");
				
				return true;
			}
			
			String format = "";
			
			int i = 0;
			
			while(i < args.length) {
				
				format = format + " " + args[i];
				
				if(i == 0) {
					
					format = args[0];
				
				}
				
				i++;
			}
			
			config.set("Config.Message", format);
			
			AntiVoid.getPlugin().saveConfig();
			
			p.sendMessage("§7[§aAnti-Void§7] >> §aThe VoidMessage has been changed!");
			p.sendMessage("New VoidMessage: ");
			p.sendMessage("< "+format+" >");
		}
		return false;
	}

}
