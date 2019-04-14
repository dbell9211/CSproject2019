package com.kennesaw.edu.os.scheduler;

import com.kennesaw.edu.os.memory.Disk;
import com.kennesaw.edu.os.memory.PCB;
import com.kennesaw.edu.os.scheduler.Schedulerprocess;

import java.util.*;

public class Scheduler implements Runnable {

	private Disk disk;
	private PCB pcb;
	private Schedulerprocess schedulerprocess;
   public LinkedList<String> readyqueue = new LinkedList<String>();
   public LinkedList<String> Jobqueue = new LinkedList<String>();
   public LinkedList<String> List = new LinkedList<String>();//temp. a string list might need to change data structure for other variables as well.

	public Scheduler( Disk disk, PCB pcb, Schedulerprocess schedulerprocess ) {

		this.pcb = pcb;
		this.disk = disk;
		this.schedulerprocess = schedulerprocess;
	}

	@Override public void run() {
		// Remove terminated processes from the RAM, may need to change read or other parameters.
		for (PCB pcb : this.pcb) {
			if (pcb.status.getStatus_NUM() == 2) {
					for ( int x = 0; x < disk.read(); x++ ) {
						this.disk.write(pcb.getStartingAddress());
					}			
         }
		}


		// Find next process
		if ( Jobqueue.size() > 0 ) {
			pcb.status = List.get( 0 );
			if ( this.schedulerprocess == Schedulerprocess.Priority ) {
				//Find highest priority process
				for ( PCB.Status pcb : List ) {
					if ( next.getPriority() < pcb.getPriority() ) { //need a look ahead for priority check.
						 //psedo code here. moves to next pcb.maybe change list parameters?
					}
				}
			} else if ( this.schedulerprocess == Schedulerprocess.FirstInFirstOut ) {
				// Find the next loaded process
			   List.get( 0 );
			}
            readyqueue.add(pcb.status);//pseudo coded.
				pcb.status = pcb.status.READY;
			}
		}
}
//need something to check for memory mapping.