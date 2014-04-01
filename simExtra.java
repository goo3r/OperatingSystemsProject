//Matthew Gyure
//Jaime Alvarez
//Emmanuel Valdivia
//ECE 46810 - Operating Systems
//Project 1
//
//Program Name: simExtra
//Language: Java
//Last Updated: 3/13/2014
//Description: The main program

import java.util.*;
import java.io.*;

public class simExtra
{
   //An array list to hold the processes obtained from the input file
   private static ArrayList<SimProcessExtra> processes;  
   
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
         if(args[0].equals("-d"))
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
         if(args[0].equals("-a"))
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
         if(!(args[1].equals("FCFS") || args[1].equals("RR")))
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
            System.out.println("Args length: " + args.length + "\nargs[0]: " + args[0] + "\nargs[1]: "+ args[1] + "\nargs[2]: " + args[2]);
         }
         
      }
      
      //command looks like: java sim -d -a algorithm input_file
      else if(args.length == 4)
      {
         boolean errorsInSyntax = false;
         String errors = "";
         if(args[0].equals("-d"))
         {
            detailedInfoMode = true;
         }
         else
         {
            errorsInSyntax = true;
            errors += "First argument is incorrect. \n";
         }
         
         if(args[1].equals("-a"))
         {
            algorithmMode = true;
         }
         else
         {
            errorsInSyntax = true;
            errors += "Second argument is incorrect. \n";
         }
         
         if(!(args[2].equals("FCFS") || args[2].equals("RR")))
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
         if(algorithm.equals("FCFS"))
         {
            /***************Testing**************/
            processes = getProcessesFromInputFile(path);//getProcessesFromInputFile("C:\\Users\\Jaime\\Documents\\GitHub\\OperatingSystemsProject\\RandomProcesses.txt");
            if(processes != null)
            {
               System.out.println("# of Processes: " + numberOfProcesses + "\nProcess Switch: " + processSwitch);
               
               FCFSExtra alg = new FCFSExtra(processes, processSwitch);
               //algorithm.showFCFSQueue();
               alg.doFCFSScheduling(dMode);
            }
            else
            {
               showErrors("An error occurred reading the file. Please check the error message and stack trace.");
            }
            /************End of Testing**********/
         }
         /***************Testing**************//*else if(algorithm.equals("RR"))
         {
            //Emmanuel do some magic
            
            processes = getProcessesFromInputFile(path);//getProcessesFromInputFile("C:\\Users\\Jaime\\Documents\\GitHub\\OperatingSystemsProject\\RandomProcesses.txt");
            if(processes != null)
            {
               System.out.println("# of Processes: " + numberOfProcesses + "\nProcess Switch: " + processSwitch);
               
               RR alg = new RR(processes, processSwitch);
               //algorithm.showRRQueue();
               alg.doRRScheduling(dMode);
            }
            else
            {
               showErrors("An error occurred reading the file. Please check the error message and stack trace.");
            }
            
         }*//************End of Testing**********/
      }
      else
      {
         //Do both algorithms
         //FCFS
         /***************Testing**************/
         processes = getProcessesFromInputFile(path);//getProcessesFromInputFile("C:\\Users\\Emmanuel\\Documents\\GitHub\\OperatingSystemsProject\\RandomProcesses.txt");
         if(processes != null)
         {
            System.out.println("# of Processes: " + numberOfProcesses + "\nProcess Switch: " + processSwitch);
               
            FCFSExtra alg = new FCFSExtra(processes, processSwitch);
            alg.showFCFSQueue();
            //alg.doFCFSScheduling(dMode);
         }
         else
         {
            showErrors("An error occurred reading the file. Please check the error message and stack trace.");
         }
         /************End of Testing**********/
            
         //Then RR
         /***************Testing**************//*
            processes = getProcessesFromInputFile(path);//getProcessesFromInputFile("C:\\Users\\Jaime\\Documents\\GitHub\\OperatingSystemsProject\\RandomProcesses.txt");
            if(processes != null)
            {
               System.out.println("# of Processes: " + numberOfProcesses + "\nProcess Switch: " + processSwitch);
               
               RR alg = new RR(processes, processSwitch);
               //algorithm.showFCFSQueue();
               alg.doRRScheduling(dMode);
            }
            else
            {
               showErrors("An error occurred reading the file. Please check the error message and stack trace.");
            }*/
            /************End of Testing**********/
       }  
   }//doAlgorithms 
         
   //Method to display any errors to the cosole
   public static void showErrors(String err)
   {
      System.out.println(err);
   }
   
   
   //This method gets the processes from an input file
   public static ArrayList<SimProcessExtra> getProcessesFromInputFile(String path)
   {
      //A list to hold the incoming processes
      ArrayList<SimProcessExtra> list = new ArrayList<SimProcessExtra>();
      
      //Current reference to a SimProcessExtra object
      SimProcessExtra p = new SimProcessExtra(0,0,0);
      
      //Try to read the input file
      try
      {
         BufferedReader reader = new BufferedReader(new FileReader(path));
         
         //Local variables
         //Helps keep track of where we are in each line in the file
         int spaceCounter = 0;
         
         //A boolean to know the first line in the input file is being read
         boolean firstLine = true;
         
         //A boolean to know it's the basic info of the process
         boolean basicInfo = true;
         
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
         int processIoBurstTime = 0;
         
         //A temp variable to keep track how many cpu burst times are there
         int numberOfBurstTimes = 0;
         
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
               spaceCounter = 0;   
               //It's a line that has a process' information
               if(basicInfo)
               {
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
                  //End of line for this process basic info, add the final data as number of Cpu Burst Times
                  //processCpuBurstTime = Integer.parseInt(data);
                  numberOfBurstTimes = Integer.parseInt(data);
                  data = "";
                  spaceCounter = 0;
                  //Add this new process to the list
                  p = new SimProcessExtra(processNumber, processArrivalTime, numberOfBurstTimes);
                  basicInfo = false;       
               }
               //Read the processes' cpu and io burst times section
               else
               {
                  System.out.println("Process #: " + p.getProcessNumber());
                  //Read in the lines that contain both cpu and io burst times
                  for(int i = 0; i < numberOfBurstTimes-1; i++)
                  {  
                     System.out.println(i + 1);
                     System.out.println("# of Burst times " + numberOfBurstTimes);
                     spaceCounter = 0;
                     if(i != 0)
                     {
                        line = reader.readLine();
                        lineChar = line.toCharArray();
                     }
                     for(int j = 0; j<lineChar.length; j++)
                     {
                        //Again look for spaces
                        if(lineChar[j] == ' ')
                        {
                           //Increment space counter
                           spaceCounter++;
                           if(spaceCounter == 1)
                           {
                              //Ignore the first number
                              data = "";
                           }
                           else if(spaceCounter == 2)
                           {
                              //Add the cpu burst time
                              p.addCpuBurstTime(Integer.parseInt(data));
                              data = "";
                           }
                           /*else if(spaceCounter == 3)
                           {
                              //Add the io burst time
                              p.addIoBurstTime(Integer.parseInt(data));
                              data = "";
                           }*/
                        }
                        else
                        {
                           data += Character.toString(lineChar[j]);
                        } 
                     }
                     p.addIoBurstTime(Integer.parseInt(data));
                     data = "";
                  }//EndOfLinesWithBothCpuAndIOtimes   
                     
                  //Last line of cpu burst time
                  spaceCounter = 0;
                  data = "";
                  line = reader.readLine();
                  lineChar = line.toCharArray();
                  for(int k = 0; k<lineChar.length; k++)
                  {
                     //Again look for spaces
                     if(lineChar[k] == ' ')
                     {
                        //Increment space counter
                        spaceCounter++;
                        if(spaceCounter == 1)
                        {                              
                           //Ignore the first number
                           data = "";
                        }
                        /*else if(spaceCounter == 2)
                        {
                           //The second piece of data is the last cpu burst time
                           //Add the cpu burst time
                           p.addCpuBurstTime(Integer.parseInt(data));
                           data = "";
                        }*/                           
                     }
                     else
                     {
                        data += Character.toString(lineChar[k]);
                     } 
                  }
                  
                  p.addCpuBurstTime(Integer.parseInt(data));
                  data = "";
                  //Finally add the process to the list                  
                  list.add(p);
                  //Set boolean back to true for next process' basic info section
                  basicInfo = true;
                  spaceCounter = 0;  
                }//EndOfCpuIOsection
             
            }            
         }//EndOfMainWhileLoop
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

}//class simExtra