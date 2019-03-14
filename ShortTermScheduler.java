import java.util.LinkedList;
import java.util.ArrayList;



public class ShortTermScheduler {
    public LinkedList<PCB> readyQueue;
    public PCB block;
    public boolean ready = false;
    PCBManager.PCB_SORT_TYPE sortType;

    public ShortTermScheduler(){
        readyQueue = new LinkedList<PCB>();
    }

    public void PrioSchedule(){
        int totalJobs = 0; 
        if(PCBManager.getCurrentPcbSortType() != PCBManager.PCB_SORT_TYPE.JOB_PRIORITY){
            PCBManager.sortPcbList(PCBManager.PCB_SORT_TYPE.JOB_PRIORITY);
        }
       
        for(int i = 1; i < totalJobs + 1; i++){
            readyQueue.add(PCBManager.getPCB(i));
        }
        sortType = PCBManager.PCB_SORT_TYPE.JOB_PRIORITY;
    }

    public void FIFOSchedule(){
        int totalJobs = PCBManager.getJobListSize();
        PCBManager.sortPcbList(PCBManager.PCB_SORT_TYPE.JOB_NUMBER);

            for(int i = 1; i < totalJobs + 1; i++){
               readyQueue.add(PCBManager.getPCB(i));
            }
         sortType = PCBManager.PCB_SORT_TYPE.JOB_NUMBER;
    }



    public void Schedule(SchedulingType type)
    {
        PCBManager.PCB_SORT_TYPE sort_type = PCBManager.PCB_SORT_TYPE.JOB_NUMBER;
        switch (type.val())
        {
            case 1:
                sort_type = PCBManager.PCB_SORT_TYPE.JOB_PRIORITY;
                break;
            case 2:
                sort_type = PCBManager.PCB_SORT_TYPE.JOB_NUMBER;
                break;
            case 3:
                sort_type = PCBManager.PCB_SORT_TYPE.SHORTEST_JOB;
                break;
        }

        PCBManager.sortPcbList(sort_type, readyQueue);
    }

    public void addToReadyQueue(PCB pcb)
    {
        readyQueue.add(pcb);
    }
}