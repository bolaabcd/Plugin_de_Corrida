package com.corrida.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LerArquivos {
	static public boolean hasstring(String arquivo,String string) throws IOException {
		String conteudo = new String(Files.readAllBytes(Paths.get(arquivo)));
		if(conteudo.contains(string))return true;
			
		return false;
	}
	static public boolean chegouasaida(String arquivo, String local) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(arquivo));
		String finalocal="";
		br.readLine();
		br.readLine();
		br.readLine();
		br.readLine();
		finalocal+=br.readLine()+"\n";
		finalocal+=br.readLine()+"\n";
		finalocal+=br.readLine()+"\n";
		finalocal+=br.readLine()+"\n";
		br.close();
		if(finalocal.contains(local))return true;
		return false;
	}
	static public Location getcheckpoint(String arquivo, int posicao) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(arquivo));
		int atual=0;
		br.readLine();
		br.readLine();
		br.readLine();
		br.readLine();
		br.readLine();
		br.readLine();
		br.readLine();
		br.readLine();
		while(atual<((posicao-1)*4)) {
			atual++;
			br.readLine();
		}
		Double x=Double.valueOf(br.readLine())+0.5;
		Double y=Double.valueOf(br.readLine())+0.5;
		Double z=Double.valueOf(br.readLine())+0.5;
		World w=Bukkit.getWorld(br.readLine());
		br.close();
		return new Location(w,x,y,z);
		
	}
}
