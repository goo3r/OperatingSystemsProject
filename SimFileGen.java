//Matthew Gyure
//Jaime Alvarez
//Emmanuel Valdivia
//ECE 448 - Operating System
//Project 1
//
//Program Name: SimFileGen
//Language: Java
//Last Updated: 3/12/2014
//Description: This program generates a text file of 30 random prcoesses. The arrival times and process times are all random as to simulate a set of processes an operating system would encounter.

import java.util.*;
import java.io.*;

public class SimFileGen{
	

	public static void main(String args[]){
		SimFileGen test = new SimFileGen();
		int processCount = 0;

		while(processCount<30){
			if(processCount == 0){
				test.writeFile(false, processCount);
			}else{
				test.writeFile(true, processCount);
			}
			processCount++;
		}
	}
	
	public void writeFile(boolean append, int processCount) {
    		String fileName = "RandomProcesses.txt";
     
    		try {
    			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,append));
    			writer.write("Process#"+processCount+","+randoGen()+","+randoGen());
    			writer.newLine();
			writer.close();
    		    } catch (IOException e) {
    			e.printStackTrace();
    		    }
    	}

	public int randoGen(){
		Random rand = new Random();
		
		return rand.nextInt(10);
	}
}
