package com.corrida.comandos;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class RemoverCorrida implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length!=1)return false;
		File arq=new File("plugins/Corridas/"+args[0]+".txt");
		File inscrs=new File("plugins/Corridas/"+args[0]+"Inscritos.txt");
		if((!arq.exists())||(!inscrs.exists())) {
			sender.sendMessage(ChatColor.RED+"Arquivo inexistente!");
			return false;
		}
		try {
		arq.delete();
		inscrs.delete();
		}catch(Exception e) {
			sender.sendMessage(ChatColor.RED+"Erro ao deletar arquivo! (talvez seja um arquivo inexistente?)");
		return false;
		}
		return true;
	}
}
