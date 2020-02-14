package com.corrida.utils;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class LerComandos {
	public static boolean tudonumero(String[] args) {
		for(String arg:args) {
			try {
				Integer.valueOf(arg);
			}catch(Exception e){
				return false;
			}
			return true;
			
		}
		return false;
	}
	public static int[] tocords(String[] args,Player p) throws NumberFormatException{
		int[] res=new int[3];
		if(args[0].equals("^")) {
			res[0]=p.getTargetBlockExact(5).getX();
		}else if(args[0].equals("~")) {
			res[0]=p.getLocation().getBlockX();
		}else if(tudonumero(Arrays.copyOfRange(args, 0, 1))) {
			res[0]=Integer.valueOf(args[0]);
		}
		
		if(args[1].equals("^")) {
			res[1]=p.getTargetBlockExact(5).getY();
		}else if(args[1].equals("~")) {
			res[1]=p.getLocation().getBlockY();
		}else if(tudonumero(Arrays.copyOfRange(args, 1, 2))) {
			res[1]=Integer.valueOf(args[1]);
		}
		
		if(args[2].equals("^")) {
			res[2]=p.getTargetBlockExact(5).getZ();
		}else if(args[2].equals("~")) {
			res[2]=p.getLocation().getBlockZ();
		}else if(tudonumero(Arrays.copyOfRange(args, 2, 3))) {
			res[2]=Integer.valueOf(args[2]);
		}
		return res;
	}
	public static World toworld(String arg,Player p) throws NumberFormatException{
		if(arg.equals("here"))return p.getWorld();
		else {
			World saida=Bukkit.getWorld(arg);
			if(saida==null)throw new NumberFormatException();
			else return saida;
		}
	}
}
