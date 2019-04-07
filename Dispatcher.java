//import
//import
//import
//import


import java.util.*;

public class Dispatcher implements Runnable {
   private CPU[] cpus; 
   private PCB pcb;
   private String Status;
   private Disk disk;
   
   
   Dispatcher (CPU [] cpus, PCB pcb, String Status, Disk disk) {
      this.cpus = cpus;
      this.pcb = pcb;
      this.Status = Status;
      this.disk = disk;
   }
   
   public void run() {//dispatcher sets the cpu process and then loads in the pcb through context switching. 
    for (CPU cpu : this.cpus) {
		if ((pcb.getcpuID() > 0) || (!pcb.Status.RUNNING)) {
				//pcb next;
				//next = Scheduler.getReadyQueue().remove(); may use for later.
				if ((pcb.getcpuID() > 0) || (!pcb.Status.RUNNING)) {
				//Disk cache = cpu.getCache();//Is Cache located in the memory or the scheduler?
				//for (int r = 0; r < next.getTotalSize() && r < cache.getCapacity(); r++ ) {
					//cache.write(r, Disk.read(next, r));
				Driver.CPU[r].load(pcb);
            }
         }
   }
  }//end method
  
}//end class