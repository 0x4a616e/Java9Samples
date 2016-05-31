package de.jangassen;

import java.io.IOException;
import java.lang.module.ModuleDescriptor;

public class Overview {
  public static void main(String[] args) {
    try {
      generateModuleOverview();
    } catch (IOException e) {
      System.err.println("Unable to generate class overview: " + e.getMessage());
    }
  }

  private static void generateModuleOverview() throws IOException {
    ModuleDescriptor descriptor =
        ModuleDescriptor.read(Overview.class.getClassLoader().getResourceAsStream("module-info.class"));

    System.out.println("Module " + descriptor.name());
    descriptor.exports().forEach(export -> System.out.println("\texports " + export.source()));
    descriptor.requires()
        .forEach(requires -> System.out.println("\trequires " + requires.name() + " " + requires.modifiers()));
  }
}