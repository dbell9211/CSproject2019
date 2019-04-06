import java.util.*;

public class Dispatcher {
   private CPU[] cpus; 
   
   Dispatcher (CPU [] cpus) {
      this.cpus = cpus;
   }
   
   public void run() {//dispatcher sets the cpu process and then loads in the pcb through context switching. 
    for (CPU cpu : this.cpus) {
			if ((cpu.getProcess() == null || PCB.Status.RUNNING != cpu.getJob().getStatus())) {
				
				PCB next;
				next = Scheduler.getReadyQueue().remove();
				cpu.fetch(next);
				RAM cache = cpu.getCache();
				for (int r = 0; r < next.getTotalSize() && r < cache.getCapacity(); r++ ) {
					cache.write(r, RAM.read(next, r));
				   Driver.CPU[r].load(PCB);
            }
         }
   }
  }//end method
}//end class