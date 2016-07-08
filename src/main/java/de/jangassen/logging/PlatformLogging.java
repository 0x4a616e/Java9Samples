package de.jangassen.logging;

/**
 * Created by jan on 08/07/16.
 */
public class PlatformLogging {

  public static void main(String args[]) {
    System.Logger logger = System.getLogger(PlatformLogging.class.getSimpleName());

    logger.log(System.Logger.Level.INFO, "Starting log example");
    logger.log(System.Logger.Level.INFO, "Logger: {0}", logger);
  }
}
