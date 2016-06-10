package de.jangassen;

import java.io.IOException;
import java.lang.module.ModuleDescriptor;

public class Overview {
  public static void main(String[] args) {
    if (Overview.class.getModule().getDescriptor() != null) {
      printModule(Overview.class.getModule().getDescriptor());
    } else {
      System.out.println("The class has been started without '-mp' option. Trying to load module-info manually...");
      try {
        printModule(readModuleDescriptorFromClass());
      } catch (IOException e) {
        System.err.println("Unable to generate class overview: " + e.getMessage());
      }
    }
  }

  private static ModuleDescriptor readModuleDescriptorFromClass() throws IOException {
    return ModuleDescriptor.read(Overview.class.getClassLoader().getResourceAsStream("module-info.class"));
  }

  private static void printModule(ModuleDescriptor descriptor) {
    System.out.println("Module " + descriptor.name());
    descriptor.exports().forEach(export -> System.out.println("\texports " + export.source()));
    descriptor.requires()
        .forEach(requires -> System.out.println("\trequires " + requires.name() + " " + requires.modifiers()));
  }
}