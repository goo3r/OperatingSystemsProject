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
   
   //Boolean to see if all algorithms are to be executed
   private static boolean doAllAlgorithms = false;
   
   //Boolean to determine if argument inputs are valid
   private static boolean argumentsAreValid = true;
   
   //Number of processes gotten from the input file and process switch
   private static int numberOfProcesses;
   private static int processSwitch;
   
   
   public static void main(String[] args)
   {
      if(args.length == 0)
      {
         //Impossible
         showErrors("Zero argument error: Must at least specify and input file path to execute program.");
      }
      
      //command looks like: java sim input_file
      else if(args.length == 1)
      {
         //Do all algorithms from specified input file in default mode
         doAlgorithms("", false, false, args[0]);
      }
      //command looks like: java sim -d input_file
      else if(args.length == 2)
      {
         if(args[0] == "-d")
         {
            detailedInfoMode = true;
            doAlgorithms("", detailedInfoMode, false, args[1]);
         }
         else
         {
            //Unacceptable!!!
            System.out.println(args[0] + "\n" + args[1] + "\n Number of args " + args.length); //Debugging
            showErrors("First argument must be -d.");            
         }         

      }
      //command looks like: java sim -a algorithm input_file
      else if(args.length == 3)
      {
         boolean errorsInSyntax = false;
         String errors = "";
         if(args[0] == "-a")
         {
            algorithmMode = true;
            //argumentsAreValid = true;
         }
         else
         {
            //error
            errorsInSyntax = true;
            errors += "First argument must be -a.\n";
         }
         
         //Check for appropriate algorithms
         if(!(args[1] == "FCFS" || args[1] == "RR"))
         {
            errorsInSyntax = true;
            errors += "Second argument must be FCFS or RR.";
         }
         
         if(!errorsInSyntax)
         {
            doAlgorithms(args[1], detailedInfoMode, algorithmMode, args[2]);
         }
         else
         {
            showErrors(errors);
         }
         
      }
      
      //command looks like: java sim -d -a algorithm input_file
      else if(args.length == 4)
      {
         boolean errorsInSyntax = false;
         String errors = "";
         if(args[0] == "-d")
         {
            detailedInfoMode = true;
         }
         else
         {
            errorsInSyntax = true;
            errors += "First argument is incorrect. \n";
         }
         
         if(args[1] == "-a")
         {
            algorithmMode = true;
         }
         else
         {
            errorsInSyntax = true;
            errors += "Second argument is incorrect. \n";
         }
         
         if(!(args[2] == "FCFS" || args[2] == "RR"))
         {
            errorsInSyntax = true;
            errors += "Third argument is incorrect. \n";
         }
         
         if(!errorsInSyntax)
         {
            doAlgorithms(args[2], detailedInfoMode, algorithmMode, args[3]);
         }
         else
         {
            showErrors(errors);
         }
         
      }  
   }//void main(String[] args)
   
   //The method that will do a specific or all algorithms, given the modes that are given by the arguments.
   public static void doAlgorithms(String algorithm, boolean dMode, boolean aMode, String path)
   {
      if(aMode)
      {
         if(algorithm == "FCFS")
         {
            /***************Testing**************/
            processes = getProcessesFromInputFile(path);//getProcessesFromInputFile("C:\\Users\\Jaime\\Documents\\GitHub\\OperatingSystemsProject\\RandomProcesses.txt");
            if(processes != null)
            {
               System.out.println("# of Processes: " + numberOfProcesses + "\nProcess Switch: " + processSwitch);
               
               FCFS alg = new FCFS(processes);
               //algorithm.showFCFSQueue();
               alg.doFCFSScheduling(dMode);
            }
            else
            {
               System.out.println("It's null");
            }
            /************End of Testing**********/
         }
         else if(algorithm == "RR")
         {
            //Emmanuel do some magic
         }
      }
      else
      {
         //Do both algorithms
         //FCFS
         /***************Testing**************/
            processes = getProcessesFromInputFile(path);//getProcessesFromInputFile("C:\\Users\\Jaime\\Documents\\GitHub\\OperatingSystemsProject\\RandomProcesses.txt");
            if(processes != null)
            {
               System.out.println("# of Processes: " + numberOfProcesses + "\nProcess Switch: " + processSwitch);
               
               FCFS alg = new FCFS(processes);
               //algorithm.showFCFSQueue();
               alg.doFCFSScheduling(dMode);
            }
            else
            {
               System.out.println("It's null");
            }
            /************End of Testing**********/
            
        //Then RR
        //Emmanuel do a barrel roll
      }
   }
   
   //Method to display any errors to the cosole
   public static void showErrors(String err)
   {
      System.out.println(err);
   }
   
   
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