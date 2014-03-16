//Matthew Gyure
//Jaime Alvarez
//Emmanuel Valdivia
//ECE 46810 - Operating Systems
//Project 1
//
//Program Name: sim
//Language: Java
//Last Updated: 3/13/2014
//Description: The main program

import java.util.*;
import java.io.*;

public class sim
{
   //An array list to hold the processes obtained from the input file
   private static ArrayList<SimProcess> processes;  
   
   //The flags for detailed information mode and algorithm mode
   private static boolean detailedInfoMode = false;
   private static boolean algorithmMode = false;
   
   //Number of processes gotten from the input file and process switch
   private static int numberOfProcesses;
   private static int processSwitch;
   
   
   public static void main(String[] args)
   {
      if(args.length == 0)
      {
         //Impossible
      }
      else if(args.length == 1)
      {
         //Do both algorithms from specified input file in default mode
      }
      else if(args.length == 2)
      {
         if(args[0] == "-d")
         {
            detailedInfoMode = true;
         }
         else if(args[0] == "-a")
         {
            algorithmMode = true;
         }
         else if(args[0] == "FCFS")
         {
            //Do FCFS only in default mode
         }
         else if(args[0] == "RR")
         {
            //Do RR only in default mode
         }
         else
         {
            //Unacceptable!!!
         }

      }
      else if(args.length == 3)
      {
         //Detailed and execute specified algorithm
      }
      
      /***************Testing**************/
      processes = getProcessesFromInputFile("C:\\Users\\Jaime\\Documents\\GitHub\\OperatingSystemsProject\\RandomProcesses.txt");
      if(processes != null)
      {
         System.out.println("# of Processes: " + numberOfProcesses + "\nProcess Switch: " + processSwitch);
         for(SimProcess p: processes)
         {
           // System.out.println(p.getProcessNumber() + ", " + p.getArrivalTime() + ", " + p.getCpuBurstTime());
         }
         
         FCFS algorithm = new FCFS(processes);
         //algorithm.showFCFSQueue();
         algorithm.doFCFSScheduling();
      }
      else
      {
         System.out.println("It's null");
      }
      /************End of Testing**********/
      
      
   }//void main(String[] args)
   
   
   //This method gets the processes from an input file
   public static ArrayList<SimProcess> getProcessesFromInputFile(String path)
   {
      ArrayList<SimProcess> list = new ArrayList<SimProcess>();
      
      //Try to read the input file
      try
      {
         BufferedReader reader = new BufferedReader(new FileReader(path));
         
         //Local variables
         //Helps keep track of where we are in each line in the file
         int spaceCounter = 0;
         
         //A boolean to know the first line in the input file is being read
         boolean firstLine = true;
         
         //A string to get the reader.readLine() object
         //A char array to go through each character
         String line;
         char[] lineChar;
         
         //A string to buffer data to later parse it into int
         String data = "";
         
         //A current process' process number, arrival time, and cpu burst time
         int processNumber = 0;
         int processArrivalTime = 0;
         int processCpuBurstTime = 0;
         
         while((line = reader.readLine()) != null)
         {
            lineChar = line.toCharArray();
            
            //First read the number of processes and process switch
            if(firstLine)
            {
               for(int i = 0; i<lineChar.length;i++)
               {
                  //Check for spaces, if found one then add the collected data to the number of processes variable
                  if(lineChar[i] == ' ')
                  {
                     numberOfProcesses = Integer.parseInt(data);                     
                     //Clean the data buffer
                     data = "";
                  }
                  else
                  {
                     data += Character.toString(lineChar[i]);
                  }
               }
               //Reach end of line, add the data as process switch
               processSwitch = Integer.parseInt(data);
               //Clean the data buffer
               data = "";
               firstLine = false;               
            }
            else
            {               
               //It's a line that has a process' information
               for(int i = 0; i<lineChar.length; i++)
               {
                  //Again look for commas
                  if(lineChar[i] == ' ')
                  {
                     //Increment comma counter
                     spaceCounter++;
                     if(spaceCounter == 1)
                     {
                        //First piece of data is the process number
                        processNumber = Integer.parseInt(data);
                        data = "";
                     }
                     else if(spaceCounter == 2)
                     {
                        //The second piece of data is the arrival time
                        processArrivalTime = Integer.parseInt(data);
                        data = "";
                     }
                  }
                  else
                  {
                     data += Character.toString(lineChar[i]);
                  } 
               }
               //End of line for this process, add the final data as Cpu Burst Time
               processCpuBurstTime = Integer.parseInt(data);
               data = "";
               spaceCounter = 0;
               //Add this new process to the list
               list.add(new SimProcess(processNumber, processArrivalTime, processCpuBurstTime));
            }            
         }
         return list;         
      }
      catch(IOException e)
      {
         StringWriter sw = new StringWriter();
         e.printStackTrace(new PrintWriter(sw));
         System.out.println(e.getMessage() + "\n" + sw.toString());
         return null;
      }   
   }//getProcessesFromInputFile()

}//class sim