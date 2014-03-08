import java.util.*;
import java.io.*;

public class SimFileGen{
	
	public SimFileGen(){
		String processName;
		int arrivalTime;
		int processTime;
		int processCount;
	}

	public static void main(String args[]){
		SimFileGen test = new SimFileGen();
		test.writeFile(true);
	}
	
	public void writeFile(boolean closer) {
    		String fileName = "test.txt";
     
    		try {
    			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,false));
    			writer.write(processName+","+arrivalTime+","+processTime);
    			writer.newLine();
    			if(closer){
				writer.close();
			}
    		    } catch (IOException e) {
    			e.printStackTrace();
    		    }
    	}

	public void randoGen(){
		Random rand = new Random();
	}
}
