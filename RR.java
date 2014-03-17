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

public class RR{

   //Constant quantum number
   private final static int quantum = 10;

   //Queue to use for RR ready queue
   private Queue<SimProcess> rrQueue = new LinkedList<SimProcess>();
   
   //Number of process to be schedule
   private int numberOfProcessesToBeScheduled = 0; 
   
   //Constructor, sort the arraylist passed in by arrival time order and store it in a queue
   public RR(ArrayList<SimProcess> listOfProcesses)
   {      
      //Sort the arraylist of processes first by arrival time
      Collections.sort(listOfProcesses, new Comparator<SimProcess>()
      {
         public int compare(SimProcess sp1, SimProcess sp2){return (sp1.getArrivalTime() - sp2.getArrivalTime());}
      }
      );
   //Add the sorted list in the FCFS queue     
      for(SimProcess p: listOfProcesses)
      {
         rrQueue.add(p);
      }      
   }

//Method to show what's in the FCFSQueue
   public void showRRQueue()
   {
      boolean okieDokie = true;
      SimProcess p;
      while(okieDokie)
      {
         //Retreive the element from the queue and display it with its arrival time and process number
         try
         {
            p = rrQueue.remove();
            System.out.println("Arrival Time: " + p.getArrivalTime() + " , Process#: " + p.getProcessNumber());
         }
         catch(NoSuchElementException e)
         {
            okieDokie = false;
         }
      }
   }//showFCFSQueue()
   
   public void doRRScheduling(boolean detailedMode){
   
   
   
   
   
   }//doRRScheduling()


}// class RR