/*
Description:
This class represents a process.
The process will have the process number,
the arrival time, and the cpu burst time.
*/

public class SimProcess
{
   private int processNumber;
   private int arrivalTime;
   private int cpuBurstTime;
   
   public SimProcess(int procNum, int arrivalTime, int cpuTime)
   {
      processNumber = procNum;
      this.arrivalTime = arrivalTime;
      cpuBurstTime = cpuTime;
   }
   
   public int getProcessNumber(){return processNumber;}
   public int getArrivalTime(){return arrivalTime;}
   public int getcpuBurstTime(){return cpuBurstTime;}
}