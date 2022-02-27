package de.matrixkabel.antivoid.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import de.matrixkabel.antivoid.commands.AddWorld;
import de.matrixkabel.antivoid.commands.DeleteWorld;
import de.matrixkabel.antivoid.commands.VoidMessage;
import de.matrixkabel.antivoid.falling.FallingDetection;

public class AntiVoid extends JavaPlugin{
	
	private static AntiVoid plugin;
	
	public void onEnable() {
		plugin = this;
		
		System.out.println("[anti-void] >> Aktiv!");
		
		getCommand("voidmessage").setExecutor(new VoidMessage());
		
		getCommand("addworld").setExecutor(new AddWorld());
		
		getCommand("deleteworld").setExecutor(new DeleteWorld());
		
		AntiVoid.getPlugin().saveDefaultConfig();
		
		//check if void
		Bukkit.getScheduler().runTaskTimer(AntiVoid.getPlugin(), new Runnable() {
			@Override
			public void run() {
				
				FallingDetection.ifplayerfall();
				
			}
		}, 40, 40); // 20ticks = 1sec
		
	}
	
	
	public static AntiVoid getPlugin() {
		
		return plugin;
		
	}
	
}
