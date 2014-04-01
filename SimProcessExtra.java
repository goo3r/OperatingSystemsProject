//Matthew Gyure
//Jaime Alvarez
//Emmanuel Valdivia
//ECE 46810 - Operating Systems
//Project 1
//
//Program Name: SimProcessExtra
//Language: Java
//Last Updated: 3/13/2014
/*Description:
This class represents a process.
The process will have the process number,
the arrival time, and the cpu burst time.
As well as keeping track of its waiting time
and turnaround time.
*/

import java.util.*;

public class SimProcessExtra
{
   //A processes predefined fields
   private int processNumber;
   private int arrivalTime;
   private int numberOfCpuBurstTimes;
   private LinkedList<Integer> cpuBurstTime = new LinkedList<Integer>();
   private LinkedList<Integer> ioBurstTime = new LinkedList<Integer>();
   
   //A process' waiting time and turnaround time
   private int waitingTime = 0;
   private int turnaroundTime = 0;
   
   public SimProcessExtra(int procNum, int arrivalTime, int numberOfCpuBurstTimes)
   {
      processNumber = procNum;
      this.arrivalTime = arrivalTime;
      this.numberOfCpuBurstTimes = numberOfCpuBurstTimes;
   }
   
   //Get methods
   public int getProcessNumber(){return processNumber;}
   public int getArrivalTime(){return arrivalTime;}
   
   public int getCpuBurstTime()
   {
      if(!cpuBurstTime.isEmpty())
         return cpuBurstTime.remove();
      else
         return -1;    
   }
   
   public int getIoBurstTime()
   {
      if(!ioBurstTime.isEmpty())
         return ioBurstTime.remove();
      else
         return -1;
   }
   
   public int getWaitingTime(){return waitingTime;}
   public int getTurnaroundTime(){return turnaroundTime;}
   public int getNumberOfCpuBurstTimes(){return numberOfCpuBurstTimes;}
   
   //Set methods
   public void setWaitingTime(int time){waitingTime = time;}
   public void setTurnaroundTime(int time){turnaroundTime = time;}
   //public void setCpuBurstTime(int time){cpuBurstTime = time;}
   
   //Add methods
   public void addCpuBurstTime(int cpuTime){cpuBurstTime.add(cpuTime);}
   public void addIoBurstTime(int ioTime){ioBurstTime.add(ioTime);}
   
   //Incremental methods for waiting time and turnaround time
   public void incrementWaitingTime(int inc){waitingTime+=inc;}
   public void incrementTurnaroundTime(int inc){turnaroundTime+=inc;}
}