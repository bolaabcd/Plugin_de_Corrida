package com.corrida.comandos;

import java.io.IOException;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.corrida.utils.LerArquivos;

import net.md_5.bungee.api.ChatColor;

public class UsarCheckpoint implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length!=0)return false;
			Set<String> tags=player.getScoreboardTags();
			boolean tanalista=false;
			for(String tag:tags) {
				try {
					if(LerArquivos.hasstring("plugins/Corridas/corridas.txt", tag)){
						tanalista=true;
						int pos=0;
						try {
						pos=player.getScoreboard().getObjective("check").getScore(player.getName()).getScore();
						}catch(NullPointerException n) {
							player.sendMessage(ChatColor.RED+"Você ainda não chegou a nenhum checkpoint!");
							return true;
						}
						if(pos<1) {
							player.sendMessage(ChatColor.RED+"Você ainda não chegou a nenhum checkpoint!");
							return true;
						}
						Location local=LerArquivos.getcheckpoint("plugins/Corridas/"+tag+".txt", pos);
						local.setYaw(player.getLocation().getYaw());
						local.setPitch(player.getLocation().getPitch());
						player.teleport(local);
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			if(!tanalista){
				player.sendMessage(ChatColor.RED+"Vocẽ não está concorrendo em nenhuma corrida no momento!");
				return true;
			}
		}else sender.sendMessage(ChatColor.RED+"Este Comando é apenas para Players!");
		return true;
	}
}
