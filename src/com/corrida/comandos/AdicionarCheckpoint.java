package com.corrida.comandos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.corrida.utils.LerComandos;

import net.md_5.bungee.api.ChatColor;

public class AdicionarCheckpoint implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length!=5)return false;
		if (sender instanceof Player) {
			Player player = (Player) sender;
			String nomecorrida= args[0];
			File arquivodacorrida=new File("plugins/Corridas/"+nomecorrida+".txt");
			if(!arquivodacorrida.exists()) {
				player.sendMessage(ChatColor.RED+"Corrida Inexistente!");
				return false;
			}
			int[] cords=new int[1];
			String mundo;
			try {
				cords=LerComandos.tocords(Arrays.copyOfRange(args, 1, 4), player);
				mundo=LerComandos.toworld(args[4], player).getName();
			}catch(NumberFormatException n) {
				player.sendMessage(ChatColor.RED+"Coordenadas Inválidas");
				return false;
			}
			try {
				BufferedWriter bw= new BufferedWriter(new FileWriter(arquivodacorrida,true));
				bw.append(Integer.toString(cords[0])+"\n");
				bw.append(Integer.toString(cords[1])+"\n");
				bw.append(Integer.toString(cords[2])+"\n");
				bw.append(mundo+"\n");
				bw.close();
				return true;
			} catch (IOException e) {
				player.sendMessage(ChatColor.DARK_RED+"ERRO INESPERADO AO CRIAR ARQUIVO!");
				e.printStackTrace();
				return false;
			}
			
			
		}else {
			sender.sendMessage(ChatColor.RED+"Esse comando é apenas para players!");
			return true;
		}
	}
}
