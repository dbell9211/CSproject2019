package com.kennesaw.edu.os.memory;

import java.io.*; 
public class PCB
{
   int cpuID;
   //private String Status;
   int counter;
   int priority;
   int startingAddress;
   char PC;
   public Status status;
  
  

   public enum Status
   {
      RUNNING(0), READY(1), BLOCKED(2), NEW(3);
      
      public int Status_TYPE;
      Status(int Status_NUM) {
         Status_TYPE = Status_NUM;
      }
      
      public int getStatus_NUM() {
         return Status_TYPE;
      }
   }
   
    

   //Setters and getters
   public void setPC(char PC)
   {
      this.PC = PC;
   }
   public int getPC()
   {
     return this.PC;
   }
   
   public void setcpuID(int cpuID)
   {
      this.cpuID = cpuID;
   }
   public int getcpuID()
   {
     return this.cpuID;
   }
 
   
   public void setPriority(int priority)
   {
      this.priority = priority;
   }
   public int getPriority()
   {
     return this.priority;
   }
   
   public void setStartingAddress(int startingAddress)
   {
      this.startingAddress = startingAddress;
   }  
   
   public int getStartingAddress()
   {
     return this.startingAddress;
   }


   public PCB(int cpuID, Status status, int counter, int priority, int startingAddress )
   {
      this.cpuID = cpuID;
      this.status = status;
      this.counter = counter;
      this.priority = priority;
      this.startingAddress = startingAddress;

   }

}