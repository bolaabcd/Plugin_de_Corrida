package com.corrida.comandos;
/*
 * 3 primeiras linhas: coordenadas do ponto inicial.
 * linha 4: nome do mundo do ponto inicial.
 * linhas 5,6 e 7: coordenadas do ponto final.
 * linha 8: nome do mundo do ponto final.
 * */

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

public class CriarCorrida implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			String nomecorrida= args[0];
			int[] cordsin=new int[1],cordsfin=new int[1];
			String mundoinicio,mundofinal;
			
			try {
				cordsin=LerComandos.tocords(Arrays.copyOfRange(args, 1, 4), player);
				cordsfin=LerComandos.tocords(Arrays.copyOfRange(args, 5, 8), player);
				mundoinicio=LerComandos.toworld(args[4], player).getName();
				mundofinal=LerComandos.toworld(args[8], player).getName();
			}catch(NumberFormatException n) {
				player.sendMessage(ChatColor.RED+"Coordenadas Inválidas");
				return false;
			}
			File arquivodacorrida=new File("plugins/Corridas/"+nomecorrida+".txt");
			if(arquivodacorrida.exists()) {
				player.sendMessage("Corrida já Existente! ");
				return false;
			}else {
				try {
					arquivodacorrida.createNewFile();
					BufferedWriter bw= new BufferedWriter(new FileWriter(arquivodacorrida));
					bw.append(cordsin[0]+"\n"+cordsin[1]+"\n"+cordsin[2]+"\n"+mundoinicio+"\n"+cordsfin[0]+"\n"+cordsfin[1]+"\n"+cordsfin[2]+"\n"+mundofinal+"\n");
					bw.close();
					return true;
				} catch (IOException e) {
					player.sendMessage(ChatColor.DARK_RED+"ERRO INESPERADO AO CRIAR ARQUIVO!");
					e.printStackTrace();
					return false;
				}
			}
			
		}else {
			sender.sendMessage(ChatColor.RED+"Esse comando é apenas para players!");
			return true;
		}
	}
}
