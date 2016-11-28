package hu.asgames.report

import groovyx.net.http.HttpResponseException
import org.spockframework.runtime.Condition
import org.spockframework.runtime.ConditionNotSatisfiedError

/**
 * @author AMiklo on 2016.10.21.
 */
class ErrorReporter {

  private final PrintWriter writer

  ErrorReporter(PrintWriter writer) {
    this.writer = writer
  }

  void reportError(ConnectException error) {
    writer.println(' - ' + error.message)
  }

  void reportError(HttpResponseException error) {
    writer.println(' - ' + error.message + ' (statusCode: ' + error.statusCode + ')')
  }

  void reportError(ConditionNotSatisfiedError error) {
    Condition condition = error.condition
    writer.println(' - Condition: ' + condition.text)
    writer.println(' - Result: ' + condition.expression.value)
  }
}