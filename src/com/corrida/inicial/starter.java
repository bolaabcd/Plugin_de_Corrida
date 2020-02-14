package com.corrida.inicial;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class starter extends JavaPlugin{
	@Override 
	public void onEnable(){
	    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"PLUGIN ATIVADO!");
	    
	    setcomandos();
		
	    criarquivo();
	}
	@Override
    public void onDisable() {
    Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"PLUGIN DESATIVADO!");

    }
	private void setcomandos() {
		
	}
	private void criarquivo() {
		
	}
}
