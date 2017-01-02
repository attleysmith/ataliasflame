package hu.asgames

import org.junit.rules.ExternalResource
import org.slf4j.Logger

import java.lang.reflect.Field
import java.lang.reflect.Modifier

class ReplaceSlf4jLogger extends ExternalResource {

  Field loggerField
  Logger replacedLogger
  Logger originalLogger

  ReplaceSlf4jLogger(Class loggedClass, Logger logger) {
    loggerField = loggedClass.getDeclaredField("LOGGER")
    replacedLogger = logger
  }

  @Override
  protected void before() throws Throwable {
    loggerField.accessible = true

    Field modifiersField = Field.getDeclaredField("modifiers")
    modifiersField.accessible = true
    modifiersField.setInt(loggerField, loggerField.getModifiers() & ~Modifier.FINAL)

    originalLogger = (Logger) loggerField.get(null)
    loggerField.set(null, replacedLogger)
  }

  @Override
  protected void after() {
    loggerField.set(null, originalLogger)
  }
}
