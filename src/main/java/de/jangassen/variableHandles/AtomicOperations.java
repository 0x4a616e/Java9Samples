package de.jangassen.variableHandles;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jan on 10/06/16.
 */
public class AtomicOperations {

  private final VarHandle intHandle;

  public int var;

  public AtomicOperations() throws NoSuchFieldException, IllegalAccessException {
    intHandle = MethodHandles.lookup().in(AtomicOperations.class).findVarHandle(AtomicOperations.class, "var", int.class);
    var = 0;
  }

  public void increment() {
    System.out.println(Thread.currentThread().getName() + " was number " + intHandle.addAndGet(this, 1));
  }

  public static void main(String args[]) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
    AtomicOperations sample = new AtomicOperations();
    runConcurrently(() -> sample.increment());
  }

  private static void runConcurrently(Runnable runnable) {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 100; i++) {
      executor.submit(runnable);
    }
    executor.shutdown();
  }
}
