/*
Description:
This class represents a process.
The process will have the process number,
the arrival time, and the cpu burst time.
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
   
   //Incremental methods for waiting time and turnaround time
   public void incrementWaitingTime(int inc){waitingTime+=inc;}
   public void incrementTurnaroundTime(int inc){turnaroundTime+=inc;}
}