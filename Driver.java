import java.io.*;
import java.util.*;
 

public class Driver {
   
   //instance variables
   private CPU[] cpus;
   private Thread[] threads; //simulated threads
   private Scheduler scheduler;
   private Dispatcher dispatcher;
   private Memory disk;
   private SchedulerProcess schedulerprocess;
   private static Loader loader;
   
   public Driver(int disk, int RAMsize, int registers, int cache) {
      
      this.disk = disk;
      this.RAMsize = RAMsize;
      this.registers = registers; 
      this.cache = cache;
      
      this.dispatcher = new Dispatcher();
      this.register = new Register();
      this.scheduler = new Scheduler();
      this.schedulerprocess = new Schedulerprocess();
      this.loader = new Loader();


   
      this.threads = new Thread[this.cpu.length];
   
      for (int x = 0; x < this.cpus.length; x++ ) {
			CPU cpu = new CPU(x);
			this.cpus[x] = cpu;
			this.threads[x] = new Thread( this.cpus[x] );
         cpu.printDump();
      }
   }
   
   public void run() {//for thread array.
      for(int e = 0; e < cpu.length(); e++) {
         this.threads[e].start();
      }
      if (loader() == null) {
         loader(programfile.txt, disk);
         while(loader != null) {
            this.scheduler.run();
            this.dispatcher.run();
         
            boolean jobcompleted = true;
            if(PCB.getStatus != PCB.Status.Terminated) {
               jobcompleted = false;
            }
            
         boolean notalive = true;
         for (Thread thread : this.threads) {
            if(thread != null) {
               notalive = false;
            }
         }
         
         for (CPU cpu : this.cpu) {
			cpu.signalShutdown();
			synchronized (cpu) {
			}
		}

		// Wait for the threads
		boolean[] joined = new boolean[this.threads.length];
		for ( int x = 0; x < joined.length; x++ ) {
			joined[x] = false;
		}

		boolean allJoined;

		
      do {
			for ( int f = 0; f < this.cpu.length; f++ ) {
				synchronized (this.cpu[f]) {
				}
				this.threads[f].join();
				if ( !this.threads[f].isAlive() ) { 
					joined[f] = true;
				}
			}

			allJoined = true;

			for ( boolean aJoined : joined ) {
				if ( !aJoined ) {
					allJoined = false;
					break;
				}
			}
		} while (!allJoined);        
   }
   
      }
  }
  
   public static void reset() {
      loader = null;
   }
 
   public static void Main(String []args) {
      int[] cpuset = { 1 };
      for (SchedulingPolicy policy : SchedulingPolicy.values()) {
			for ( int numCPUs : cpuSet ) {
				Driver.reset();
			}
		}
   }// end main method
   
   public void dump() {
      System.out.println("Disk size: " + disk +  "RAM usage: " + RAMsize );
      for (CPU cpu : this.cpu) {
        System.out.println( "CPU " + PCB.getCPUID() );
		   cpu.printDump();
		   System.out.println();
      }  
   }
}//end driver class
