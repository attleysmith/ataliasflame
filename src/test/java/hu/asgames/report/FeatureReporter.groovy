package hu.asgames.report

import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.IterationInfo

/**
 * @author AMiklo on 2016.10.24.
 */
class FeatureReporter {

  private static final String TEST_CASE_DESCRIPTION = "testCase"
  private final PrintWriter writer;

  FeatureReporter(PrintWriter writer) {
    this.writer = writer
  }

  public void reportFeature(FeatureInfo feature) {
    reportFeature(feature, null, [])
  }

  public void reportIteration(IterationInfo iteration) {
    reportFeature(iteration.feature, iteration.name, iteration.dataValues)
  }

  public void reportFeature(FeatureInfo feature, String testName, Object... params) {
    String testCase = testName ?: feature.name
    int testCaseParamCount = feature.dataVariables.indexOf(TEST_CASE_DESCRIPTION)
    if (testCaseParamCount > -1) {
      testCase = "$testCase - ${params[testCaseParamCount]}"
    }
    writer.println("$testCase:")
    boolean firstBlock = true
    for (block in feature.blocks) {
      String blockName = block.kind.name().toLowerCase()
      if (firstBlock) {
        blockName = blockName.capitalize()
        firstBlock = false
      }
      boolean firstText = true
      for (text in block.texts) {
        if (firstText) {
          writer.println(" $blockName $text")
          firstText = false
        } else {
          writer.println(" and $text")
        }
      }
    }
    feature.dataVariables.eachWithIndex { String paramName, int i ->
      if (paramName != TEST_CASE_DESCRIPTION) {
        writer.println("   [$paramName: ${params[i]}]")
      }
    }
  }
}
