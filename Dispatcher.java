public class Dispatcher {

    public static void dispatch() {
        terminateJobs();
        loadJobs();

        if (Driver.ShortTermScheduler.readyQueue.size() == 0 && Driver.areAllCPUsIdle()) {
            terminateJobs();
            System.err.println("There were no errors");
            System.out.println("OS finished successfully");
            Driver.executorService.shutdown();
            Driver.isOSComplete = true;
            Driver.osEndTIme = System.currentTimeMillis();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    MainFrame.signalOSFinish();
                }
            });
        }
    }

    public static void terminateJobs()
    {
        for (int i = 0; i < Driver.CPUs.length; i++)
        {
            int currentJobNumber = Driver.CPUs[i].currentJobNumber();
            try {
            if (Driver.CPUs[i].isIdle() && Driver.CPUs[i].shouldTerminate()) {
                System.out.println(String.format("TERMINATING " + currentJobNumber + ": is idle:%s shouldTerminate:%s cpu loaded:%s shouldUnload:%s", Driver.CPUs[i].isIdle(), Driver.CPUs[i].shouldTerminate(), Driver.CPUs[i].isJobLoaded(), Driver.CPUs[i].shouldUnload()));

                System.out.println("TERMINATING JOB " + currentJobNumber + " ON CPU: " + i);
                Driver.commands[currentJobNumber] += "TERMINATING JOB " + currentJobNumber + " ON CPU: " + i;
                Driver.CPUs[i].unload(PCBManager.getPCB(currentJobNumber));
                MMU.synchronizeCache(PCBManager.getPCB(currentJobNumber));
                RAM.deallocatePcb(PCBManager.getPCB(currentJobNumber));
                System.out.println(String.format("TERMINATED " + currentJobNumber + ": is idle:%s shouldTerminate:%s cpu loaded:%s shouldUnload:%s", Driver.CPUs[i].isIdle(), Driver.CPUs[i].shouldTerminate(), Driver.CPUs[i].isJobLoaded(), Driver.CPUs[i].shouldUnload()));
                if(PCBManager.getPCB(currentJobNumber).getProcessStatus() == PCB.PROCESS_STATUS.TERMINATE)
                    Driver.completedJobs++;
                Driver.updateOsMetric();
            }
            }
            catch (ArrayIndexOutOfBoundsException ex)
            {
                throw new ArrayIndexOutOfBoundsException("EXCEPTION TERMINATING JOB " + currentJobNumber + " ON CPU: " + i + " " + ex.toString());
            }
        }
    }

    
    public static void loadJobs()
    {
        for (int i = 0; i < Driver.CPUs.length; i++) {
            if (Driver.CPUs[i].isIdle() && Driver.ShortTermScheduler.readyQueue.size() > 0)
            {
                PCB pcb  = Driver.ShortTermScheduler.readyQueue.pop();
                if(Driver.CPUs[i].shouldUnload())
                {
                    System.out.println(String.format("Jobs:%CPU:%sTrapped!",Driver.CPUs[i].currentJobNumber(), i));
                    terminateJobs();
                }
                Driver.jobMetricses[pcb.getJobNumber()-1].setTimestamp(System.currentTimeMillis());
                Driver.jobMetricses[pcb.getJobNumber()-1].setJobNumber(pcb.getJobNumber());
                Driver.jobMetricses[pcb.getJobNumber()-1].setEndWaitTime(System.currentTimeMillis());
                Driver.jobMetricses[pcb.getJobNumber()-1].setCpuNo(i+1);
                Driver.updateJobMetrics(Driver.jobMetricses[pcb.getJobNumber()-1]);
                Driver.commands[pcb.getJobNumber()] += "loading job #: " + pcb.getJobNumber() + " cpu: " + i;
                Driver.CPUs[i].load(pcb);

            }
        }
    }
}