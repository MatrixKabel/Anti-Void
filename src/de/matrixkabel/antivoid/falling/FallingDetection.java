package de.matrixkabel.antivoid.falling;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.matrixkabel.antivoid.main.AntiVoid;

public class FallingDetection {
	
	static FileConfiguration config = AntiVoid.getPlugin().getConfig();
	
	public static void ifplayerfall() {
		
		for(Player player : Bukkit.getOnlinePlayers()) {
			
			//variables
			int y = getplayery(player);
			
			String worldname = player.getWorld().getName();
			
			boolean worldteleport = getworldteleport(worldname);
			
			
			if(y < 0 && worldteleport) {
				
				teleportplayerback(player, worldname);
				
			}
			
		}
		
	}
	
	public static int getplayery(Player player) {
		int y = 1;
		
		y = player.getLocation().getBlockY();
		
		return y;
	}
	
	public static boolean getworldteleport(String worldname) {
		
		//variables
		boolean respawn = false;
		
		if(config.isSet(worldname + ".Config.respawn?")) {
			
		    respawn = config.getBoolean(worldname + ".Config.respawn?");
		
		}else {
			
			respawn = false;
			
			config.set(worldname + ".Config.respawn?", false);
			
			AntiVoid.getPlugin().saveConfig();
		}
		
		return respawn;
	}
	
	public static String getrespawnworld(String worldname) {
		
		//variables
		String respawnworldname = worldname;
		
		if(config.isSet(worldname + ".Config.respawn-World?")) {
			
			respawnworldname = config.getString(worldname + ".Config.respawn-World?");
			
		}else {
			
			config.set(worldname + ".Config.respawn-World?", respawnworldname);
			
			AntiVoid.getPlugin().saveConfig();
		}
		
		return respawnworldname;
	}
	
	public static boolean ifworldexist(String respawnworldname) {
		
		//variables
		boolean exist = false;
		
		if(Bukkit.getWorld(respawnworldname) != null) {
			
			exist = true;
			
		}
		
		return exist;
	}
	
	public static Location setteleport(String worldname) {
		FileConfiguration config = AntiVoid.getPlugin().getConfig();
		
		int x = 0;
		int y = 0;
		int z = 0;
		
		int yaw = 0;
		int pitch = 0;
		
		World world = Bukkit.getWorld(worldname);
		
		if(config.isSet(worldname+".respawn.X")) x = config.getInt(worldname+".respawn.X");
		if(config.isSet(worldname+".respawn.Y")) y = config.getInt(worldname+".respawn.Y");
		if(config.isSet(worldname+".respawn.Z")) z = config.getInt(worldname+".respawn.Z");
		
		if(config.isSet(worldname+".respawn.yaw")) yaw = config.getInt(worldname+".respawn.yaw");
		if(config.isSet(worldname+".respawn.pitch")) pitch = config.getInt(worldname+".respawn.pitch");
		
		Location teleport = new Location(world, x, y, z, yaw, pitch);
		
		return teleport;
	}
	
	public static String getmessage() {
		
		String message = "Message not found";
		
		if(config.isSet("Config.Message")) {
			
			message = config.getString("Config.Message");
			
		}else {
			
			config.set("Config.Messge", "§d[Anti-Void] >> §cYou fall out of world and got teleportet to the spawn!");
			
			AntiVoid.getPlugin().saveConfig();
			
		}
		
		return message;
		
	}
	
	public static void teleportplayerback(Player player, String worldname) {
		
		//variables
		String respawnworldname = getrespawnworld(worldname);
		
		boolean worldexist = ifworldexist(respawnworldname);
		
		if(worldexist) {
			
			Location teleport = setteleport(worldname);
			
			String message = getmessage();
			
			player.teleport(teleport);
			
			player.sendMessage(message);
			
		}
		
	}

}
