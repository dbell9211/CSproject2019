package com.kennesaw.edu.os.scheduler;

public enum Schedulerprocess {//scheduling enum tried with mapping but more stable with enum.

   Priority(1), FirstInFirstOut(2), shortestfirst(3);
   
   private int policy;
   
   public int procedure() {
      return policy;
   }
   
   Schedulerprocess(int policy) {
      this.policy = policy;
   }
   
   static Schedulerprocess policycheck(int policy) {
      for (Schedulerprocess type: Schedulerprocess.policy()) {
            if (type.procedure() == policy) {
                return type;
            }
        }
        return null;

   }
}//end enum class