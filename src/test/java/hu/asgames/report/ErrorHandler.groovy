package hu.asgames.report

import groovyx.net.http.HttpResponseException
import org.spockframework.runtime.Condition
import org.spockframework.runtime.ConditionNotSatisfiedError

/**
 * @author AMiklo on 2016.10.21.
 */
class ErrorHandler {

  private final PrintWriter writer;

  ErrorHandler(PrintWriter writer) {
    this.writer = writer
  }

  private void handleError(ConnectException error) {
    writer.println(' - ' + error.message)
  }

  private void handleError(HttpResponseException error) {
    writer.println(' - ' + error.message + ' (statusCode: ' + error.statusCode + ')')
  }

  private void handleError(ConditionNotSatisfiedError error) {
    Condition condition = error.condition
    writer.println(' - Condition: ' + condition.text)
    writer.println(' - Result: ' + condition.expression.value)
  }
}
