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
   public LinkedList<String> List = new LinkedList<String>();//temp. a string list

	public Scheduler( Disk disk, PCB pcb, Schedulerprocess schedulerprocess ) {

		this.pcb = pcb;
		this.disk = disk;
		this.schedulerprocess = schedulerprocess;
	}

	@Override public void run() {
		// Remove terminated processes from the RAM
		for (PCB pcb : this.pcb) {
			if (pcb.Status == pcb.Status.BLOCKED) {
					for ( int x = 0; x < disk.read(); x++ ) {
						this.disk.write(pcb.getStartingAddress());
					}			
         }
		}


		// Find next process
		if ( Jobqueue.size() > 0 ) {
			pcb.Status = List.get( 0 );
			if ( this.schedulerprocess == Schedulerprocess.Priority ) {
				//Find highest priority process
				for ( PCB.Status pcb : List ) {
					if ( next.getPriority() < PCB.getPriority() ) { //need a look ahead for priority check.
						 //psedo code here. moves to next pcb.maybe change list parameters?
					}
				}
			} else if ( this.schedulerprocess == Schedulerprocess.FirstInFirstOut ) {
				// Find the next loaded process
			   List.get( 0 );
			}
            readyqueue.add(pcb.Status);//pseudo coded.
				pcb.Status = pcb.Status.READY;
			}
		}
}
//need something to check for memory mapping.