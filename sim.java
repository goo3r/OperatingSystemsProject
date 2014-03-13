
import java.util.*;

public class sim
{
   //An array list to hold the processes obtained from the input file
   private ArrayList<SimProcess> processes = new ArrayList<SimProcess>();
   
   //The queue to use for FCFS
   private Queue<SimProcess> fcfsQueue = new LinkedList<SimProcess>();
   
   //The flags for detailed information mode and algorithm mode
   private static boolean detailedInfoMode = false;
   private static boolean algorithmMode = false;
   
   
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




}//class sim