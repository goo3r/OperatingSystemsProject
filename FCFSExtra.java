//Matthew Gyure
//Jaime Alvarez
//Emmanuel Valdivia
//ECE 46810 - Operating Systems
//Project 1
//
//Program Name: FCFSExtra
//Language: Java
//Last Updated: 3/13/2014
/*Description:
This class represents the algorithm
for the FCFS policy.
*/
import java.util.*;

public class FCFSExtra
{
   //The queue to use for FCFS as a ready queue
   private Queue<SimProcessExtra> fcfsQueue = new LinkedList<SimProcessExtra>();
   
   //The number of processes to schedule
   private int numberOfProcessesToBeScheduled = 0;
   private int processSwitchTime = 0;
   
   //Constructor, sort the arraylist passed in by arrival time order and store it in a queue
   public FCFSExtra(ArrayList<SimProcessExtra> listOfProcesses, int processSwitchTime)
   {      
      //Set the process switch time
      this.processSwitchTime = processSwitchTime;
      
      //Sort the arraylist of processes first by arrival time
      Collections.sort(listOfProcesses, new Comparator<SimProcessExtra>()
      {
         public int compare(SimProcessExtra sp1, SimProcessExtra sp2){return (sp1.getArrivalTime() - sp2.getArrivalTime());}
      }
      );
      
      //Add the sorted list in the FCFS queue     
      for(SimProcessExtra p: listOfProcesses)
      {
         fcfsQueue.add(p);
      }      
   }
   
   //Method to show what's in the FCFSQueue
   public void showFCFSQueue()
   {
      boolean goodTogo = true;
      SimProcessExtra p;
      while(goodTogo)
      {
         //Retreive the element from the queue and display it with its arrival time and process number
         try
         {
            p = fcfsQueue.remove();
            System.out.println("Arrival Time: " + p.getArrivalTime() + " , Process#: " + p.getProcessNumber());
            int i = p.getNumberOfCpuBurstTimes();
            System.out.print("Cpu burst times: [ ");
            while(i>0)
            {
               System.out.print(p.getCpuBurstTime() + ", ");
               i--;
            }
            System.out.println("]");
            i = p.getNumberOfCpuBurstTimes();
            System.out.print("IO burst times: [ ");
            while(i>1)
            {
               System.out.print(p.getIoBurstTime() + ", ");
               i--;
            }
            System.out.println("]");
         }
         catch(NoSuchElementException e)
         {
            goodTogo = false;
         }
      }
   }//showFCFSQueue()
   
   //The method that does the scheduling
   public void doFCFSScheduling(boolean detailedMode)
   {
      //Do FCFS if there's processes
      if(!fcfsQueue.isEmpty())
      {
         //The CPU time units
         int cpuCurrentTotalTimeUnits = 0;
         
         //The service time for a process
         int serviceTime = 0;
         
         //IO service time for a process
         int ioServiceTime = 0;
         
         //Track the total idle time
         int idleTotalTime = 0;
         
         //Track the total io time
         int ioTotalTime = 0;
         
         //A boolean to know when to get the new process waiting in the queue
         boolean getANewProcessFromReadyQueue = true;
         
         //Done queue for later to display results
         ArrayList<SimProcessExtra> doneQueue = new ArrayList<SimProcessExtra>();
         
         //A reference to manipulate processes
         SimProcessExtra processCurrentlyProcessing = null;
         
         while(true)
         {
            //Check if there's no current process running
            if(processCurrentlyProcessing == null)
            {
               //Then check if there are more processes waiting to be ran
               if(!fcfsQueue.isEmpty())
               {
                  //Get the next process in the ready queue if it has "arrived" yet
                  if(cpuCurrentTotalTimeUnits >= (fcfsQueue.peek()).getArrivalTime())
                  {
                     //Get the next process in the ready queue
                     processCurrentlyProcessing = fcfsQueue.remove();
                     
                     //Getting the next process takes time, so add up the idle time according to the process switch time
                     idleTotalTime += processSwitchTime;
                     
                     //Also, increment the cpu current total time units by the switch
                     cpuCurrentTotalTimeUnits += processSwitchTime;
                     
                     //Track it's waiting time
                     processCurrentlyProcessing.setWaitingTime(cpuCurrentTotalTimeUnits-processCurrentlyProcessing.getArrivalTime());
                  }                  
               }
               //No more processes to run, exit out
               else
               {
                  break;
               }
            }
            
            
            //A process is now currently running
            if(processCurrentlyProcessing != null)
            {
               //Get the number of total CPU burst times for this process
               int numberOfCpuBursts = processCurrentlyProcessing.getNumberOfCpuBurstTimes();
               
               //Start the CPU bursts and the IO bursts
               for(int i = 0; i < numberOfCpuBursts; i++)
               {
                  //Do CPU burst
                  if((serviceTime = processCurrentlyProcessing.getCpuBurstTime())!= -1)
                  {
                     cpuCurrentTotalTimeUnits += serviceTime;
                     
                     //Track the total service time for this process currently running
                     processCurrentlyProcessing.incrementTotalServiceTime(serviceTime);
                     
                  }
                  //Then do IO burst
                  if((ioServiceTime = processCurrentlyProcessing.getIoBurstTime()) != -1)
                  {
                     cpuCurrentTotalTimeUnits += ioServiceTime;
                     idleTotalTime += ioServiceTime;
                     ioTotalTime += ioServiceTime;
                  }
               }
                                             
               //Porcess is done send it to the done queue and have the reference set to null
               //Also, track the turnaround time
               processCurrentlyProcessing.setTurnaroundTime(cpuCurrentTotalTimeUnits-processCurrentlyProcessing.getArrivalTime());
               doneQueue.add(processCurrentlyProcessing);
               processCurrentlyProcessing = null;               
               
            }
            //No process is currently running, CPU is idle for this current time unit
            else
            {
               cpuCurrentTotalTimeUnits++;
               idleTotalTime++;
            }                       
         }
         
         //Print it out
         System.out.println("\nFirst Come First Serve: \n");
         System.out.println("Total Time required is " + cpuCurrentTotalTimeUnits + " time units");
         System.out.println("Idle Total Time: "+idleTotalTime);
         System.out.println("IO Burst Total Time: " + ioTotalTime);
         //System.out.println("Cpu Current Total Time: "+cpuCurrentTotalTimeUnits);
         //System.out.println("Division"+(float)(idleTotalTime/cpuCurrentTotalTimeUnits));
         System.out.println("CPU Utilization is " + String.format("%.2f", 100*(1.0f-(((float)idleTotalTime)/cpuCurrentTotalTimeUnits))) +"%\n");

         if(detailedMode)
         {
            //Sort the done queue by Process Number
            Collections.sort(doneQueue, new Comparator<SimProcessExtra>()
            {
               public int compare(SimProcessExtra p1, SimProcessExtra p2)
               {
                  return (p1.getProcessNumber() - p2.getProcessNumber());
               }
            });
            
            //Print out with details for each process
            for(SimProcessExtra p: doneQueue)
            {
               System.out.println("Process " + p.getProcessNumber() + ": \nArrival Time: " + p.getArrivalTime() + "\nTotal Service Time: " + p.getTotalServiceTime() +  " units \nTurnaround Time: " + p.getTurnaroundTime() + " units \nFinished Time: " + (p.getArrivalTime() + p.getTurnaroundTime() + " units \n"));
            }
         }         
      }
   }//doFCFSScheduling()

}//class FCFSExtra