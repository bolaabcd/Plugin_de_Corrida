package com.corrida.comandos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/*
 * linha 1: quantidade atual de inscritos
 * linha 2: máximo de inscritos
 * linha 3: mínimo de inscritos
 * linha 4: iniciar automaticamente a corrida?
 * linha 5+: um nome de player por linha.
 * */

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.corrida.utils.CorridaAcontecendo;
import com.corrida.utils.LerComandos;

import net.md_5.bungee.api.ChatColor;

public class Inscrever implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length<2)return false;
		if(!(new File("plugins/Corridas/"+args[1]+"Inscritos.txt")).exists()) {
			sender.sendMessage(ChatColor.RED+"Corrida Inexistente!");
			return false;
		}
		File inscrs=new File("plugins/Corridas/"+args[1]+"Inscritos.txt");
		if(args[0].equals("list")) {
			try {
				BufferedReader br=new BufferedReader(new FileReader(inscrs));
				br.readLine();
				br.readLine();
				br.readLine();
				br.readLine();
				String linha;
				int num=0;
				while((linha=br.readLine())!=null) {
					num++;
					sender.sendMessage(num+": "+linha);
				}
				br.close();
				if (num==0)sender.sendMessage(ChatColor.GOLD+"Ninguém inscrito");
			} catch (IOException e) {
				sender.sendMessage(ChatColor.DARK_RED+"ERRO INESPERADO AO ABRIR ARQUIVO!");
				e.printStackTrace();
				return false;
			}
			return true;
		}else if(args.length!=3)return false;
		try {
			String tudo=new String(Files.readAllBytes(inscrs.toPath()));
			boolean templayer=tudo.contains(args[2]);
			BufferedWriter bw=new BufferedWriter(new FileWriter(inscrs));
			String[] dividido=tudo.split("\n");
			if(args[0].equals("add")) {
				if(templayer) {
					sender.sendMessage(ChatColor.RED+"Player já inscrito!");
					bw.write(tudo);
					bw.close();
					return false;
				}
				if(Integer.valueOf(dividido[0])>=Integer.valueOf(dividido[1])) {
					sender.sendMessage(ChatColor.RED+"Quantidade máxima de players atingida!");
					bw.write(tudo);
					bw.close();
					return false;
				}
				dividido[0]=Integer.toString(Integer.valueOf(dividido[0])+1);
				tudo="";
				for(String linha:dividido) {
					tudo+=linha+"\n";
				}
				bw.write(tudo+args[2]+"\n");
				bw.close();
				if(Integer.valueOf(dividido[0])>=Integer.valueOf(dividido[2])&&dividido[3].equals("true"))
					sender.sendMessage(CorridaAcontecendo.Startar(args[1]));

			}else if(args[0].equals("rem")) {
				if(!templayer) {
					sender.sendMessage(ChatColor.RED+"Player não inscrito!");
					bw.write(tudo);
					bw.close();
					return false;
				}
				dividido[0]=Integer.toString(Integer.valueOf(dividido[0])-1);
				tudo="";
				for(String linha:dividido) {
					tudo+=linha+"\n";
				}
				bw.write(tudo.replaceFirst("\n"+args[2], ""));
				bw.close();
			}else if(args[0].equals("min")) {
				if(!LerComandos.tudonumero(new String[] {args[2]})) {
					bw.write(tudo);
					bw.close();
					sender.sendMessage(ChatColor.RED+"Número inválido!");
					return false;
				}
				dividido[2]=args[2];
				tudo="";
				for(String linha:dividido) {
					tudo+=linha+"\n";
				}
				bw.write(tudo);
				bw.close();
			}else if(args[0].equals("max")) {
				if(!LerComandos.tudonumero(new String[] {args[2]})) {
					bw.write(tudo);
					bw.close();
					sender.sendMessage(ChatColor.RED+"Número inválido!");
					return false;
				}
				dividido[1]=args[2];
				tudo="";
				for(String linha:dividido) {
					tudo+=linha+"\n";
				}
				bw.write(tudo);
				bw.close();
			}else if(args[0].equals("auto")) {
				if(!args[2].equals("true")&&!args[2].equals("false")) {
					sender.sendMessage(ChatColor.RED+"Segundo argumento inválido!");
					bw.write(tudo);
					bw.close();
					return false;
				}
				dividido[3]=args[2];
				tudo="";
				for(String linha:dividido) {
					tudo+=linha+"\n";
				}
				bw.write(tudo);
				bw.close();
			}
			
		} catch (IOException e) {
			sender.sendMessage(ChatColor.DARK_RED+"ERRO INESPERADO AO ABRIR ARQUIVO!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
