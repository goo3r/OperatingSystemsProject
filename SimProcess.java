//Matthew Gyure
//Jaime Alvarez
//Emmanuel Valdivia
//ECE 46810 - Operating Systems
//Project 1
//
//Program Name: SimProcess
//Language: Java
//Last Updated: 3/13/2014
/*Description:
This class represents a process.
The process will have the process number,
the arrival time, and the cpu burst time.
As well as keeping track of its waiting time
and turnaround time.
*/

public class SimProcess
{
   //A processes predefined fields
   private int processNumber;
   private int arrivalTime;
   private int cpuBurstTime;
   
   //A process' waiting time and turnaround time
   private int waitingTime = 0;
   private int turnaroundTime = 0;
   
   public SimProcess(int procNum, int arrivalTime, int cpuTime)
   {
      processNumber = procNum;
      this.arrivalTime = arrivalTime;
      cpuBurstTime = cpuTime;
   }
   
   //Get methods
   public int getProcessNumber(){return processNumber;}
   public int getArrivalTime(){return arrivalTime;}
   public int getCpuBurstTime(){return cpuBurstTime;}
   public int getWaitingTime(){return waitingTime;}
   public int getTurnaroundTime(){return turnaroundTime;}
   
   //Set methods
   public void setWaitingTime(int time){waitingTime = time;}
   public void setTurnaroundTime(int time){turnaroundTime = time;}
   public void setCpuBurstTime(int time){cpuBurstTime = time;}
   
   //Incremental methods for waiting time and turnaround time
   public void incrementWaitingTime(int inc){waitingTime+=inc;}
   public void incrementTurnaroundTime(int inc){turnaroundTime+=inc;}
}