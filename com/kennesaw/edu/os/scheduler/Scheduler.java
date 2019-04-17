package com.kennesaw.edu.os.scheduler;

import com.kennesaw.edu.os.Driver;
import com.kennesaw.edu.os.memory.Memory;
import com.kennesaw.edu.os.memory.Disk;
import com.kennesaw.edu.os.memory.PCB;
import com.kennesaw.edu.os.scheduler.Schedulerprocess;

import java.util.*;

public class Scheduler implements Runnable {

	private Memory memory;
   private Disk disk;
	private PCB pcb;
	private Schedulerprocess schedulerprocess;
   public LinkedList<PCB> readyqueue = new LinkedList<PCB>();
   public LinkedList<PCB> Jobqueue = new LinkedList<PCB>();
   public LinkedList<PCB> pcblist = new LinkedList<PCB>();//temp. a string list might need to change data structure for other variables as well.
   public String Address = " ";
   public String Address2 = " ";
   int i;
   

	public Scheduler( Memory memory, Disk disk, PCB pcb, Schedulerprocess schedulerprocess ) {

		this.pcb = pcb;
      this.disk = disk;
		this.memory = memory;
		this.schedulerprocess = schedulerprocess;
	}

	@Override public void run() {
		// Remove terminated processes from the RAM, may need to change read or other parameters.
		for (PCB pcb : this.pcblist) {
			if (pcb.status.getStatus_NUM() == 4) {
					for ( int x = 0; x < pcblist.size(); x++ ) {
						this.disk.write(pcb.getStartingAddress(), pcb.getStartingAddress() + Address);//may need to change and add a terminate process to memory
					   //psuedo code here
               }			
         }
		}


		// Find next process
		if ( pcblist.size() > 0 ) {
			pcblist.(i) = Jobqueue.get( 0 );//need to change parameters here and also add a loop.
         i++;
			if ( this.schedulerprocess == Schedulerprocess.Priority ) {
				//Find highest priority process
				for ( PCB pcb : pcblist ) {
					if ( pcb.getPriority() < pcblist.size()) { //need a look ahead for priority check.
						 //something something = pcb;
					}
				}
			} else if ( this.schedulerprocess == Schedulerprocess.FirstInFirstOut ) {
				// Find the next loaded process
			   for(int z = 0; z < pcblist.size(); z++) {
               pcblist.get(z);
            }
			}
         int size = pcblist.size();
         for(int q; q < size; q++) {
            this.memory.write(q, this.disk.read(pcb.getStartingAddress(), pcb.getStartingAddress() + Address2 ));//change string parameter here.
         }
            readyqueue.add(pcb);//pseudo coded.
				pcb.status = pcb.status.READY;
			}
		}
}
//may place enum file here.