package com.kennesaw.edu.os.dispatcher;

import com.kennesaw.edu.os.cpu.CPU;
import com.kennesaw.edu.os.memory.PCB;
import com.kennesaw.edu.os.memory.Disk;
import com.kennesaw.edu.os.Driver;

import java.util.*;

public class Dispatcher implements Runnable {
   private CPU[] cpus; 
   private PCB pcb;
   private String Status;
   private Disk disk;
   //private int r = 1; //placeholder
   
   
   
  public Dispatcher (CPU [] cpus, PCB pcb, String Status, Disk disk) {
      this.cpus = cpus;
      this.pcb = pcb;
      this.Status = Status;
      this.disk = disk;
   }
   
   public void run() {//dispatcher sets the cpu process and then loads in the pcb through context switching. 
    for (CPU cpu : this.cpus) {
		if ((pcb.getcpuID() > 0) || (!pcb.Status.RUNNING)) {
				//pcb next need to change parameter and have it move to next pcb.
				//next = Scheduler.getReadyQueue().remove(); may use for later.
				if ((pcb.getcpuID() > 0) || (!pcb.Status.RUNNING)) {
				//Disk cache = cpu.getCache();//Is Cache located in the memory or the scheduler?
				//for (int r = 0; r < next.getTotalSize() && r < cache.getCapacity(); r++ ) {
					//cache.write(r, Disk.read(next, r));
				//Driver.CPU[r].load(pcb);
            }
            //synchronized (cpu) {
            //pseudo code here.
            //}
         }
   }
  }//end method
  
}//end class