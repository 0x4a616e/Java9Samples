package de.jangassen.processApi;

/**
 * Created by jan on 31/05/16.
 */
public class Kill {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.err.println("Usage: " + ProcessHandle.current().info().commandLine().orElse("<kill>") + " <pid>");
      return;
    }

    ProcessHandle.of(Long.parseLong(args[0])).ifPresent(handle -> handle.destroy());
  }
}
