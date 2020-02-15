package com.corrida.listeners;

import java.io.IOException;
import java.util.Set;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.corrida.utils.CorridaAcontecendo;
import com.corrida.utils.LerArquivos;

public class Movemento implements Listener{//Eu sei que o nome tá errado!
	@EventHandler
	public void correndo(PlayerMoveEvent pme) {
		Set<String> tags=pme.getPlayer().getScoreboardTags();
		for(String tag:tags) {
			try {
				if(LerArquivos.hasstring("plugins/Corridas/corridas.txt", tag)){
					if(LerArquivos.hasstring("plugins/Corridas/"+tag+"Inscritos.txt", pme.getPlayer().getName())) {
						String onde=pme.getTo().getBlockX()+"\n"+pme.getTo().getBlockY()+"\n"+pme.getTo().getBlockZ()+"\n"+pme.getTo().getWorld().getName();
						if(LerArquivos.chegouasaida("plugins/Corridas/"+tag+".txt", onde))CorridaAcontecendo.Terminar(tag,pme.getPlayer());
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
