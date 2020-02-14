package com.corrida.inicial;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.corrida.comandos.AdicionarCheckpoint;
import com.corrida.comandos.CriarCorrida;
import com.corrida.comandos.Inscrever;
import com.corrida.comandos.UsarCheckpoint;

public class starter extends JavaPlugin{
	@Override 
	public void onEnable(){
	    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"PLUGIN DE CORRIDA ATIVADO!");
	    
	    setcomandos();
		
	    criarquivo();
	}
	@Override
    public void onDisable() {
    Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"PLUGIN DE CORRIDA DESATIVADO!");

    }
	private void setcomandos() {
		this.getCommand("inscrever").setExecutor(new Inscrever());
		this.getCommand("newcorrida").setExecutor(new CriarCorrida());
		this.getCommand("addcheckpoint").setExecutor(new AdicionarCheckpoint());
		this.getCommand("btc").setExecutor(new UsarCheckpoint());
		
	}
	private void criarquivo() {
		File principal=new File("plugins/Corridas");
		if(!principal.exists())principal.mkdir();
	}
}
