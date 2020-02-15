package com.corrida.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.corrida.listeners.Movemento;
import net.md_5.bungee.api.ChatColor;

public class CorridaAcontecendo {
public static String Startar(String nome) {
	String saida="";
	File inscrs=new File("plugins/Corridas/"+nome+"Inscritos.txt");
	File main=new File("plugins/Corridas/"+nome+".txt");
	try {
		BufferedReader cords=new BufferedReader(new FileReader(main));
		double x=Double.valueOf(cords.readLine());
		double y=Double.valueOf(cords.readLine());
		double z=Double.valueOf(cords.readLine());
		World mundo=Bukkit.getWorld(cords.readLine());
		Location entrada=new Location(mundo,x+0.5,y+0.5,z+0.5);
		/*
		String linha;
		String[] cordsatual=new String[4];
		int numlinha=0;
		int numcheck=0;
		Location inicio;
		while((linha=cords.readLine())!=null) {
			numlinha++;
			cordsatual[(numlinha%4)-1]=linha;
			if(numlinha%4==0) {
				if(numcheck==0)inicio=new Location(Bukkit.getWorld(cordsatual[3]),Integer.valueOf(cordsatual[0]),Integer.valueOf(cordsatual[1]),Integer.valueOf(cordsatual[2]));
				else if(numcheck!=1) {
					
				}
				
				numcheck++;
			}
		}
		*/
		cords.close();
		BufferedReader br=new BufferedReader(new FileReader(inscrs));
		int atual= Integer.valueOf(br.readLine());
		br.readLine();
		int min=Integer.valueOf(br.readLine());
		br.readLine();
		if(atual<min) {
			br.close();
			return ChatColor.RED+"Quantidade insuficiente de players inscritos! O mínimo é "+ChatColor.GOLD+Integer.toString(min);
		}
		String playernome;
		while((playernome=br.readLine())!=null) {
			try {
			Bukkit.getPlayer(playernome);
			Bukkit.getPlayer(playernome).isOnline();
			}catch(NullPointerException n) {
				saida+=ChatColor.RED+"Player "+playernome+" indisponível"+"\n";
				continue;
			}
			{
				Player p=Bukkit.getPlayer(playernome);
				Set<String> tags=p.getScoreboardTags();
				boolean pular=false;
				for(String tag:tags) {
					try {
						if(LerArquivos.hasstring("plugins/Corridas/corridas.txt", tag)){
							saida+=ChatColor.RED+"Player "+p.getName()+" já está participando de uma corrida!\n";
							pular=true;
						}
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
				if(pular) {
					pular=false;
					continue;
				}
				p.addScoreboardTag(nome);
				entrada.setPitch(p.getLocation().getPitch());
				entrada.setYaw(p.getLocation().getYaw());
				p.teleport(entrada);
				p.sendMessage(ChatColor.GREEN+"CORRIDA "+nome+" INICIADA!");
				Bukkit.getServer().getPluginManager().registerEvents(new Movemento(),Bukkit.getPluginManager().getPlugin("CorridaPlugin"));
				saida+=ChatColor.GREEN+p.getName()+" adicionado à corrida!\n";
			}
		}
		br.close();
	}catch(IOException ioe) {
		ioe.printStackTrace();
		return ChatColor.RED+"ERRO INESPERADO AO ABRIR ARQUIVO!";
	}
	if(saida.isEmpty())saida=ChatColor.RED+"Nenhum player inscrito!";
	return saida;
}
public static void Terminar(String nome,Player vencedor) {
	Bukkit.broadcastMessage(ChatColor.DARK_GREEN+vencedor.getName()+" É O VENCEDOR DA CORRIDA "+nome+"!!!");

	try {
		File inscrs=new File("plugins/Corridas/"+nome+"Inscritos.txt");
		BufferedReader br=new BufferedReader(new FileReader(inscrs));
		br.readLine();
		br.readLine();
		br.readLine();
		br.readLine();
		String playernome;
		while((playernome=br.readLine())!=null) {
			if(!(Bukkit.getPlayer(playernome)==null)) {
				Bukkit.getPlayer(playernome).removeScoreboardTag(nome);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "scoreboard players set "+playernome+" check 0");
			}
		}
		br.close();
		String tudo=new String(Files.readAllBytes(inscrs.toPath()));
		String[] dividido=tudo.split("\n");
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(inscrs));
		bw.write("0"+"\n"+dividido[1]+"\n"+dividido[2]+"\n"+dividido[3]+"\n");
		bw.close();
		
	} catch (IOException e) {
		Bukkit.broadcastMessage(ChatColor.RED+"Erro inesperado ao terminar corrida!");
		e.printStackTrace();
	}
	
}
}
