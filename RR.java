/*
//Matthew Gyure
//Jaime Alvarez
//Emmanuel Valdivia
//ECE 46810 - Operating Systems
//Project 1
//
//Program Name: RR
//Language: Java
//Last Updated: 3/17/2014
Description:
This class file represents the Round Robin Algorithm, 
*/

import java.util.*;

public class RR
{  
   //The global time quantum
   private final static int quantum = 10; 
   //The queue to use for RR as a ready queue
   private Queue<SimProcess> rrQueue = new LinkedList<SimProcess>();
   
   //The number of processes to schedule
   private int numberOfProcessesToBeScheduled = 0;
   private int processSwitchTime = 0;
   
   //Constructor, sort the arraylist passed in by arrival time order and store it in a queue
   public RR(ArrayList<SimProcess> listOfProcesses, int processSwitchTime)
   {      
      //Set the process switch time
      this.processSwitchTime = processSwitchTime;
      
      //Sort the arraylist of processes first by arrival time
      Collections.sort(listOfProcesses, new Comparator<SimProcess>()
      {
         public int compare(SimProcess sp1, SimProcess sp2){return (sp1.getArrivalTime() - sp2.getArrivalTime());}
      }
      );
      
      //Add the sorted list in the RR queue     
      for(SimProcess p: listOfProcesses)
      {
         rrQueue.add(p);
      }   
   }
   
   //Method to show what's in the RRQueue
   public void showRRQueue()
   {
      boolean goodTogo = true;
      SimProcess p;
      while(goodTogo)
      {
         //Retreive the element from the queue and display it with its arrival time and process number
         try
         {
            p = rrQueue.remove();
            System.out.println("Arrival Time: " + p.getArrivalTime() + " , Process#: " + p.getProcessNumber());
         }
         catch(NoSuchElementException e)
         {
            goodTogo = false;
         }
      }
   }//showRRQueue()
   
   //The method that does the scheduling
   public void doRRScheduling(boolean detailedMode)
   {
      //Do RR if there's processes
      if(!rrQueue.isEmpty())
      {
         //The CPU time units
         int cpuCurrentTotalTimeUnits = 0;
         
         //The service time for a process
         int serviceTime = 0;
         
         //Track the total idle time
         int idleTotalTime = 0;
         
         //A boolean to know when to get the new process waiting in the queue
         boolean getANewProcessFromReadyQueue = true;
         
         //A boolean to keep track of processes processing
        // boolean stillProcessing = true;
         
         //Done queue
         ArrayList<SimProcess> doneQueue = new ArrayList<SimProcess>();
         
         //Temporary queue to add back.
         Queue<SimProcess> tempQueue = new LinkedList<SimProcess>();
         
         
         //A reference to manipulate processes
         SimProcess processCurrentlyProcessing = null;
         
         //A reference to check for
         
         while(true)
         {
            //Check if there's no current process running
            if(processCurrentlyProcessing == null)
            {
               if(tempQueue.isEmpty())
               {
                     //Then check if there are more processes waiting to be ran
                     if(!rrQueue.isEmpty())
                     {
                        //Get the next process in the ready queue if it has "arrived" yet
                        if(cpuCurrentTotalTimeUnits >= (rrQueue.peek()).getArrivalTime())
                        {
                           //Get the next process in the ready queue
                           processCurrentlyProcessing = rrQueue.remove();
                           
                           //Getting the next process takes time, so add up the idle time according to the process switch time
                           idleTotalTime += processSwitchTime;
                           
                           //Also, increment the cpu current total time units by the switch
                           cpuCurrentTotalTimeUnits += processSwitchTime;
                           
                           //Track it's waiting time
                           processCurrentlyProcessing.setWaitingTime(cpuCurrentTotalTimeUnits-processCurrentlyProcessing.getArrivalTime());
                           serviceTime = processCurrentlyProcessing.getCpuBurstTime();
                        }                  
                     }
               
                     //No more processes to run, exit out
                     else
                     {
                        break;
                     }
               }
               //When tempQueue is not empty
               else
               {
                  //Get the next process in the temporary queue
                   processCurrentlyProcessing = tempQueue.remove();
                           
                  //Getting the next process takes time, so add up the idle time according to the process switch time
                  idleTotalTime += processSwitchTime;
                           
                  //Also, increment the cpu current total time units by the switch
                  cpuCurrentTotalTimeUnits += processSwitchTime;
                           
                 //Track it's waiting time
                  processCurrentlyProcessing.setWaitingTime(cpuCurrentTotalTimeUnits-processCurrentlyProcessing.getArrivalTime());
                  serviceTime = processCurrentlyProcessing.getCpuBurstTime();              
               }      
            }
            
            
            //A process is now currently running
            if(processCurrentlyProcessing != null)
            {
               //Only allows process to run for a certain time quantum
               int count = 0;
               while(count < quantum || serviceTime > 0)  
               {
                  //Process currently running
                  cpuCurrentTotalTimeUnits++;
                  serviceTime--;
                  count++;
                  
                  if(!rrQueue.isEmpty())
                  {
                      //Set the next process in the ready queue if it has "arrived" into the temp queue
                      if(cpuCurrentTotalTimeUnits >= (rrQueue.peek()).getArrivalTime())
                      {
                        tempQueue.add(rrQueue.remove());                      
                      }
                  }    
               }
               
               //Service time of the current process is up
               if(serviceTime == 0)
               {
                  //Process is done send it to the done queue and have the reference set to null
                  //Also, track the turnaround time
                  processCurrentlyProcessing.setTurnaroundTime(cpuCurrentTotalTimeUnits-processCurrentlyProcessing.getArrivalTime());
                  doneQueue.add(processCurrentlyProcessing);
                  processCurrentlyProcessing = null;               
               }
               //Service time is not up but quantum time is up.
               else
               {  
                  //Process is not done yet, so add it back to RR queue
                  processCurrentlyProcessing.setCpuBurstTime(serviceTime); 
                  tempQueue.add(processCurrentlyProcessing);
                  processCurrentlyProcessing = null;              
               }
                  
            }
            //No process is currently running, CPU is idle for this current time unit
            else
            {
               cpuCurrentTotalTimeUnits++;
               idleTotalTime++;
            }                       
         }
         
         //Print it out
         System.out.println("\nRound Robin: \n");
         System.out.println("Total Time required is " + cpuCurrentTotalTimeUnits + " time units");
         System.out.println("Idle Total Time: "+idleTotalTime);
         //System.out.println("Cpu Current Total Time: "+cpuCurrentTotalTimeUnits);
         //System.out.println("Division"+(float)(idleTotalTime/cpuCurrentTotalTimeUnits));
         System.out.println("CPU Utilization is " + String.format("%.2f", 100*(1.0f-(((float)idleTotalTime)/cpuCurrentTotalTimeUnits))) +"%\n");

         if(detailedMode)
         {
            //Sort the done queue by Process Number
            Collections.sort(doneQueue, new Comparator<SimProcess>()
            {
               public int compare(SimProcess p1, SimProcess p2)
               {
                  return (p1.getProcessNumber() - p2.getProcessNumber());
               }
            });
            
            //Print out with details for each process
            for(SimProcess p: doneQueue)
            {
               System.out.println("Process " + p.getProcessNumber() + ": \nArrival Time: " + p.getArrivalTime() + "\nService Time: " + p.getCpuBurstTime() +  " units \nTurnaround Time: " + p.getTurnaroundTime() + " units \nFinished Time: " + (p.getArrivalTime() + p.getTurnaroundTime() + " units \n"));
            }
         }         
      }
   }//doRRScheduling()

}//class RR