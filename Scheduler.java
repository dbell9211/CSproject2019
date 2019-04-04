import java.util.*;
import java.io.*;

public class Scheduler {
   public LinkedList<pcb> readyqueue;//ready queue for the pcb.
   
   public Scheduler() {
      readyqueue = new LinkList<pcb>();
   }
   
   @Override 
   public void run () { //looks through the pcb and adds the jobs to the readyqueue
      for(PCB pcb : PCB.status) {
         if (PCB.status() == PCB.status.terminated && RAM.processMapped(pcb)) {
            try { 
               for(int x = 0; x < PCB.getjoblist.size(); x++) {
                  Driver.Scheduler.addlisttoqueue(PCB.getJOB_NUMBER());
                  RAM.TERMINATE.JOB_NUMBER();
               }
            }
            catch ( Invalidexpression e ) {
            //e.printStackTrace;
            System.err.println("job list map has already been traced");
            system.exit(1);  //if an error has occured then the system will exit out.    
            }
         }
     }
     if ( readyqueue.size() > 0 ) {
      priortize(PCB);
     }
     else {
      FirstInFirstOut(PCB);
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
      if(PCB.getCurrentPcbSortProcess() != PCB.SORT_PROCESS.JOB_PRIORITY){
            PCB.sortList(PCB.SORT_PROCESS.JOB_PRIORITY);
        }
       
        for(int i = 1; i < jobs + 1; i++) {
            readyQueue.add(getPCB(i));
        }
        sortProcess = PCB.sortprocess.JOB_PRIORITY;
    }
   
   public void FirstInFirstOut() {
      int jobs = pcb.getJobListSize();
        PCB.sortList(PCB.SORT_PROCESS.JOB_NUMBER);

            for(int i = 1; i < totalJobs + 1; i++){
               readyQueue.add(getPCB(i));
            }
         sortProcess = PCB.SORT_PROCESS.JOB_NUMBER;
    }//priority sorting method
    
     public void schedulecheck (Schedulerprocess policy) {
      PCB.SORT_PROCESS = SORT_PROCESS.JOB_NUMBER; 
      switch(process.procedure()) {
         case 1: 
         sortprocess = PCB.SORT_PROCESS.JOB_PRIORITY; 
         break;
         case 2:
         sortprocess = PCB.SORT_PROCESS.JOB_NUMBER;
         break;
         case 3:
         sortprocess = PCB.SORT_PROCESS.SHORT_JOB; 
         break; 
      }
      pcb.sortlist(sortprocess, readyqueue);
   }//returns the swapping enum value for the process required for the 
   
   public void addlisttoqueue(PCB pcb) {
      readyqueue.add(PCB);
      
   
   }//adds pcb to the ready queue.
   
   //fix up parameters.
   /*
   private static void _sortPcbList(PCB_SORT_TYPE type, LinkedList<PCB> list)
    {
        switch (type) {
            case JOB_NUMBER:
                Collections.sort(list, new Comparator<PCB>() {
                    @Override
                    public int compare(PCB o1, PCB o2) {
                        return o1.getJobNumber()-o2.getJobNumber();
                    }
                });
               // currentSortType=PCB_SORT_TYPE.JOB_NUMBER;
                break;

            case JOB_PRIORITY:
                Collections.sort(list, new Comparator<PCB>() {
                    @Override
                    public int compare(PCB o1, PCB o2) {
                        return o1.getJobPriority()-o2.getJobPriority();
                    }
                });
                //currentSortType=PCB_SORT_TYPE.JOB_PRIORITY;

                break;

            case SHORTEST_JOB:
                Collections.sort(list, new Comparator<PCB>() {
                    @Override
                    public int compare(PCB o1, PCB o2) {
                        return o1.getJobInstructionCount()-o2.getJobInstructionCount();
                    }
                });
                //currentSortType=PCB_SORT_TYPE.SHORTEST_JOB;
                break;
        }
   
}//end scheduler class   