package multiscripter.tmp.patterns.behavioral;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class StrategyTest {

  /**
   * Демонстрация применения шаблона Strategy (Стратегия).
   */
  @Test
  public void showStrategyUsage() {

    Logger logger = new Logger();

    logger.setLogger(new FileLogger());
    String fileActual = logger.writeToLog("File test message.");

    logger.setLogger(new DBLogger());
    String dbActual = logger.writeToLog("DB test message.");

    assertEquals("I am file logger. File test message.", fileActual);
    assertEquals("I am database logger. DB test message.", dbActual);
  }
}
