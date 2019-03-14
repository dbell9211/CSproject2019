import java.util.*;


public class LongTermScheduler {
    public LongTermScheduler() {

    }

    public void Schedule()
    {
        if(!RAM.isRAMFull())//calls to the RAM class
        {
            int totalJobs = PCBManager.getJobListSize();//set total jobs to be an integer then sorts them out one by one.
            for (int i  = 1; i <= totalJobs; i++)
            {
                if(!PCBManager.getPCB(i).isJobInMemory() && PCBManager.getPCB(i).getProcessStatus() == PCB.PROCESS_STATUS.NEW && !RAM.isRAMFull())
                    loadJobToRAM(PCBManager.getPCB(i));
            }
        }
    }


    public void loadJobToRAM(PCB block) {//need PCB block or class files to execute.

//        if (PCBManager.getCurrentPcbSortType() != PCBManager.PCB_SORT_TYPE.JOB_NUMBER){
//            PCBManager.sortPcbList(PCBManager.PCB_SORT_TYPE.JOB_NUMBER);
//        }

        int jobNo = block.getJobNumber();
        int i = 0;
        int k;
        int m = PCBManager.getPCB(jobNo).getJobInstructionCount();
        int dataCardSize = block.getInputBuffer() + block.getOutputBuffer() + block.getTemporaryBuffer(); //44 or 40% of (5)
        int memory = PCBManager.getPCB(jobNo).getJobInstructionCount() + dataCardSize;
        int numPages= (int)Math.ceil((double)memory/(double)RAM.getPageSize());
        int startAddress=block.getJobDiskAddress();
        int currentDiskAddress=startAddress;
        int physPageNo;
        int virtualPageNo = RAM.getNextAvailableVirtualPageNumber();
        String[] chunk;
        ArrayList<Integer> virtualAllocatedPages = RAM.allocate(numPages);
        if(virtualAllocatedPages.size() != 0)
        {
            System.out.println(jobNo);
            PCBManager.getPCB(jobNo).setJobInMemory(true);
            PCBManager.getPCB(jobNo).setJobMemoryAddress( virtualPageNo * RAM.getPageSize());
            PCBManager.getPCB(jobNo).setProcessStatus(PCB.PROCESS_STATUS.READY);
            PCBManager.getPCB(jobNo).setPagesNeeded(numPages);
            PCBManager.getPCB(jobNo).setAllocatedVirtualPages(virtualAllocatedPages);

            int n = RAM.getNextAvailableVirtualPageNumber();

            for (int j=0;j<virtualAllocatedPages.size(); j++)
            {
                //physPageNo=RAM.allocate(Helpers.getPageNumberFromAddress(currentDiskAddress));
                physPageNo=RAM.getPhysicalPageNumber(virtualAllocatedPages.get(j));

//                chunk=Disk.getChunk(currentDiskAddress, (currentDiskAddress+RAM.getPageSize()>startAddress+memory)
//                        ? (startAddress+memory)-currentDiskAddress
//                        : RAM.getPageSize());

                chunk=Disk.getChunk(currentDiskAddress, calulateChunkSize(block, currentDiskAddress));

                try {
                    RAM.fillPage(physPageNo, chunk);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    System.exit(1);
                }

                currentDiskAddress+=RAM.getPageSize();
            }
            JobMetrics metrics = new JobMetrics();
            metrics.setTimestamp(System.currentTimeMillis());
            metrics.setJobNumber(jobNo);
            metrics.setStartWaitTime(metrics.getTimestamp());
            metrics.setBlocksUsed(memory);
            Driver.jobMetricses[jobNo-1].update(metrics);
            Driver.ShortTermScheduler.addToReadyQueue(PCBManager.getPCB(jobNo));
        }



}
    private int calulateChunkSize(PCB pcb, int currentDiskAddress)
    {
        int dataCardSize = pcb.getInputBuffer() + pcb.getOutputBuffer() + pcb.getTemporaryBuffer(); //44 or 40% of (5)
        int memory = pcb.getJobInstructionCount() + dataCardSize;
        if(currentDiskAddress+RAM.getPageSize()>pcb.getJobDiskAddress()+memory)
            return (pcb.getJobDiskAddress()+memory)-currentDiskAddress;
        else
            return RAM.getPageSize();

    }
}
