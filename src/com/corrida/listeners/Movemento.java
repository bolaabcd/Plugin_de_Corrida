package com.corrida.listeners;

import java.io.IOException;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import com.corrida.utils.CorridaAcontecendo;
import com.corrida.utils.LerArquivos;

import net.md_5.bungee.api.ChatColor;

public class Movemento implements Listener{//Eu sei que o nome tá errado!
	@EventHandler
	public void correndo(PlayerMoveEvent pme) {
		Set<String> tags=pme.getPlayer().getScoreboardTags();
		for(String tag:tags) {
			try {
				if(LerArquivos.hasstring("plugins/Corridas/corridas.txt", tag)){
					if(LerArquivos.hasstring("plugins/Corridas/"+tag+"Inscritos.txt", pme.getPlayer().getName())) {
						String onde=pme.getTo().getBlockX()+"\n"+pme.getTo().getBlockY()+"\n"+pme.getTo().getBlockZ()+"\n"+pme.getTo().getWorld().getName();
						if(LerArquivos.chegouasaida("plugins/Corridas/"+tag+".txt", onde)) {
							CorridaAcontecendo.Terminar(tag,pme.getPlayer());
							PlayerMoveEvent.getHandlerList().unregister(this);
						}
						int score=pme.getPlayer().getScoreboard().getObjective("check").getScore(pme.getPlayer().getName()).getScore();
						Location local=LerArquivos.getcheckpoint("plugins/Corridas/"+tag+".txt", score+1);
						boolean xig=pme.getTo().getBlockX()==local.getBlockX();
						boolean yig=pme.getTo().getBlockY()==local.getBlockY();
						boolean zig=pme.getTo().getBlockZ()==local.getBlockZ();
						if(xig&&yig&&zig) {
							pme.getPlayer().sendMessage(ChatColor.GREEN+"Novo Checkpoint Atingido!");
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players add bolabcd check 1");
						}
					}
				}
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW+"Pequeno errinho no arquivo Movemento.java, mas provavelmente não é nada não.");
				return;
			}
		}
	}
}
