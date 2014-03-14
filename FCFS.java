//Matthew Gyure
//Jaime Alvarez
//Emmanuel Valdivia
//ECE 46810 - Operating Systems
//Project 1
//
//Program Name: FCFS
//Language: Java
//Last Updated: 3/13/2014
/*Description:
This class represents the algorithm
for the FCFS policy.
*/
import java.util.*;

public class FCFS
{
   //The queue to use for FCFS
   private Queue<SimProcess> fcfsQueue = new LinkedList<SimProcess>();
   
   //Constructor, sort the arraylist passed in by arrival time order and store it in a queue
   public FCFS(ArrayList<SimProcess> listOfProcesses)
   {      
      //Sort the arraylist of processes first
      Collections.sort(listOfProcesses, new Comparator<SimProcess>()
      {
         public int compare(SimProcess sp1, SimProcess sp2){return (sp1.getArrivalTime() - sp2.getArrivalTime());}
      }
      );
      
      //Add the sorted list in the FCFS queue     
      for(SimProcess p: listOfProcesses)
      {
         fcfsQueue.add(p);
      }      
   }
   
   //Method to show what's in the FCFSQueue
   public void showFCFSQueue()
   {
      boolean goodTogo = true;
      SimProcess p;
      while(goodTogo)
      {
         //Retreive the element from the queue and display it with its arrival time and process number
         try
         {
            p = fcfsQueue.remove();
            System.out.println("Arrival Time: " + p.getArrivalTime() + " , Process#: " + p.getProcessNumber());
         }
         catch(NoSuchElementException e)
         {
            goodTogo = false;
         }
      }
   }

}