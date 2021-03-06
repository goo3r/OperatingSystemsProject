//Matthew Gyure
//Jaime Alvarez
//Emmanuel Valdivia
//ECE 46810 - Operating Systems
//Project 1
//
//Program Name: SimFileGen
//Language: Java
//Last Updated: 3/13/2014
//Description: This program generates a text file of 30 random prcoesses. The arrival times and process times are all random as to simulate a set of processes an operating system would encounter.

import java.util.*;
import java.io.*;

public class SimFileGen{
	
   public static int numberOfProcesses = 4; //30; testing
   public int processSwitch = 2;

	public static void main(String args[]){
		SimFileGen test = new SimFileGen();
		int processCount = 0;       

		while(processCount<numberOfProcesses){
			if(processCount == 0){
				test.writeFile(false, processCount);
			}else{
				test.writeFile(true, processCount);
			}
			processCount++;
		}
	}
	
	public void writeFile(boolean append, int processCount) {
    		String fileName = "testRR.txt"; //Testing
         //String fileName = "RandomProcesses.txt";
     
    		try {
    			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,append));
            if(processCount == 0)
            {
               writer.write(numberOfProcesses + " " + processSwitch);
               writer.newLine();
            }
            writer.write(processCount + " " + randoGen() + " " + (randoGen()+1)); //Changes were made to follow her format
    			//writer.write("Process#"+processCount+","+randoGen()+","+randoGen());
    			writer.newLine();
			writer.close();
    		    } catch (IOException e) {
    			e.printStackTrace();
    		    }
    	}

	public int randoGen(){
		Random rand = new Random();
		
		return rand.nextInt(5);
	}
}
