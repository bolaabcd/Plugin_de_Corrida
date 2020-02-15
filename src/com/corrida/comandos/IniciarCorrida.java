package com.corrida.comandos;

import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.corrida.utils.CorridaAcontecendo;

public class IniciarCorrida implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length!=1)return false;
		File inscrs=new File("plugins/Corridas/"+args[0]+"Inscritos.txt");
		if(inscrs.exists()) {
			sender.sendMessage(CorridaAcontecendo.Startar(args[0]));
			return true;
		}
		else return false;
	}
}