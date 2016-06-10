package de.jangassen.processApi;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProcessTree {

  private final Predicate<ProcessHandle> filter;

  private void dumpProcessHandle(ProcessHandle handle, int depth) {
    IntStream.range(0, depth).forEach(n -> System.out.print("  "));
    System.out.printf("[%d] %s - %s\n", handle.getPid(), handle.info().user().orElse("unknown"),
        handle.info().commandLine().orElse("none"));
    handle.children().filter(h -> h.getPid() > 1).forEach(h -> dumpProcessHandle(h, depth + 1));
  }

  public void dump() {
    ProcessHandle.allProcesses().filter(filter).collect(Collectors.toList())
        .stream().forEach(h -> dumpProcessHandle(h, 0));
  }

  public ProcessTree(Collection<Long> processes) {
    if (processes.isEmpty()) {
      this.filter = (h) -> !h.parent().isPresent();
    } else {
      this.filter = (h) -> processes.contains(h.getPid());
    }
  }

  public static void main(String[] args) {
    new ProcessTree(Arrays.stream(args).map(Long::parseLong).collect(Collectors.toSet())).dump();
  }
}
