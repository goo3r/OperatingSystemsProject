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
   
   //The number of processes to schedule
   private int numberOfProcessesToBeScheduled = 0;
   
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
   
   public void doFCFSScheduling()
   {
      if(!fcfsQueue.isEmpty())
      {
         //The CPU time units
         int cpuCurrentTotalTimeUnits = 0;
         
         //The service time for a process
         int serviceTime = 0;
         
         //A boolean to know when to get the new process waiting in the queue
         boolean getANewProcessFromReadyQueue = true;
         
         //A boolean to keep track of processes processing
        // boolean stillProcessing = true;
         
         //Done queue
         ArrayList<SimProcess> doneQueue = new ArrayList<SimProcess>();
         
         //A reference to manipulate processes
         SimProcess processCurrentlyProcessing = null;
         
         while(true)
         {
            //Check if the ready queue is not empty first
            if(processCurrentlyProcessing == null)
            {
               if(!fcfsQueue.isEmpty())
               {
                  //Get the next process in the ready queue
                  processCurrentlyProcessing = fcfsQueue.remove();
                  
                  //Track it's waiting time
                  processCurrentlyProcessing.setWaitingTime(cpuCurrentTotalTimeUnits-processCurrentlyProcessing.getArrivalTime());
                  serviceTime = processCurrentlyProcessing.getCpuBurstTime();
               }
               else
               {
                  break;
               }
            }
            
            //Process currently running
            cpuCurrentTotalTimeUnits++;
            serviceTime--;
            if(serviceTime == 0)
            {
               //Porcess is done send it to the done queue and have the reference set to null
               //Also, track the turnaround time
               processCurrentlyProcessing.setTurnaroundTime(cpuCurrentTotalTimeUnits-processCurrentlyProcessing.getArrivalTime());
               doneQueue.add(processCurrentlyProcessing);
               processCurrentlyProcessing = null;               
            }               
            
         }
         
         System.out.println("First Come First Serve");
         System.out.println("Total Time required is " + cpuCurrentTotalTimeUnits + " time units");
         System.out.println("CPU Utilization is 100%");
         for(SimProcess p: doneQueue)
         {
            System.out.println("Process #" + p.getProcessNumber() + "\nArrival Time: " + p.getArrivalTime() + "\nCpu Burst Time: " + p.getCpuBurstTime() +  "\nWaiting Time: " + p.getWaitingTime() + "\nTurnaround Time: " + p.getTurnaroundTime() + "\n");
         }
         
      }
   }

}