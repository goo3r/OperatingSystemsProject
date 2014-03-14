
import java.util.*;
import java.io.*;

public class sim
{
   //An array list to hold the processes obtained from the input file
   private ArrayList<SimProcess> processes;
   
   //The queue to use for FCFS
   private Queue<SimProcess> fcfsQueue = new LinkedList<SimProcess>();
   
   //The flags for detailed information mode and algorithm mode
   private static boolean detailedInfoMode = false;
   private static boolean algorithmMode = false;
   
   //Number of processes gotten from the input file and process switch
   private int numberOfProcesses;
   private int processSwitch;
   
   
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
      
      
   }//void main(String[] args)
   
   public ArrayList<SimProcess> getProcessesFromInputFile()
   {
      ArrayList<SimProcess> list = new ArrayList<SimProcess>();
      try
      {
         BufferedReader reader = new BufferedReader(new FileReader());
         
         //First read the number of processes and process switch
         int commaCounter = 0;
         int inputChar;
         String data = "";
         while((inputChar = reader.read()) != -1)
         {
            if((char)inputChar == ',')
            {
               numberOfProcesses = Integer.parseInt(data);
               commaCounter += 1;
            }
            else
            {
               data += (char)inputChar;                  
            }
         }
         
      }
      catch(IOException e)
      {
         StringWriter sw = new StringWriter();
         e.printStackTrace(new PrintWriter(sw));
         System.out.println(e.getMessage() + "\n" + sw.toString());
      }
   
   }




}//class sim