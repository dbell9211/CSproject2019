import java.util.*;
import java.io.*;
//import packagename memory. ;
//import packagename pcb. ; 

public class Scheduler {
   public LinkedList<pcb> readyqueue;//ready queue for the pcb.
   public pcb.SORT_PROCESS sortprocess;
   
   public Scheduler() {
      readyqueue = new LinkList<pcb>();
   }
   
   @Override 
   public void run () { //looks through the pcb and adds the jobs to the readyqueue
      for(pcb pcb : pcb.taskmanager) {
         if (pcb.status() == pcb.status.terminated && RAM.processMapped(pcb)) {
            try { 
               for(int x = 0; x < pcb.getjoblist.size(); x++) {
                  Driver.Scheduler.addlisttoqueue(pcb.getJOB_NUMBER());
                  RAM.TERMINATE.JOB_NUMBER();
               }
            }
            catch ( Invalidexpression e ) {
            e.printStackTrace;
            System.err.println("job list map has already been traced");
            system.exit(1);  //if an error has occured then the system will exit out.    
            }
         }
     }
     if ( readyqueue.size() > 0 ) {
      priortize(pcb);
     }
     else {
      FirstInFirstOut(pcb);
     }
     if(RAM.next.executes()) {
      remove.queue(next);
      int totalsize = next.getTotalsize();
      for(int q = 0; q < totalsize; q ++) {
         RAM.write(next, q, disk.read(next(getStartDiskAddress())));
      }
     }
   }
   
   public void priortize() {//priority job method
      int jobs = 0;
      if(pcb.getCurrentPcbSortProcess() != pcb.SORT_PROCESS.JOB_PRIORITY){
            pcb.sortList(pcb.SORT_PROCESS.JOB_PRIORITY);
        }
       
        for(int i = 1; i < jobs + 1; i++) {
            readyQueue.add(getPCB(i));
        }
        sortProcess = pcb.sortprocess.JOB_PRIORITY;
    }
   
   public void FirstInFirstOut() {
      int jobs = pcb.getJobListSize();
        pcb.sortList(pcb.SORT_PROCESS.JOB_NUMBER);

            for(int i = 1; i < totalJobs + 1; i++){
               readyQueue.add(getPCB(i));
            }
         sortProcess = pcb.SORT_PROCESS.JOB_NUMBER;
    }//priority sorting method
    
     public void schedulecheck (Schedulerprocess policy) {
      pcb.SORT_PROCESS = SORT_PROCESS.JOB_NUMBER; 
      switch(process.procedure()) {
         case 1: 
         sortprocess = pcb.SORT_PROCESS.JOB_PRIORITY; 
         break;
         case 2:
         sortprocess = pcb.SORT_PROCESS.JOB_NUMBER;
         break;
         case 3:
         sortprocess = pcb.SORT_PROCESS.SHORT_JOB; 
         break; 
      }
      pcb.sortlist(sortprocess, readyqueue);
   }//returns the swapping enum value for the process required for the 
   
   public void addlisttoqueue(pcb pcb) {
      readyqueue.add(pcb);
   }//adds pcb to the ready queue.

   
}//end scheduler class   