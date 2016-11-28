package hu.asgames.report

import org.spockframework.runtime.model.BlockKind
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.IterationInfo

/**
 * @author AMiklo on 2016.10.24.
 */
class FeatureReporter {

  private static final String TEST_CASE_DESCRIPTION = "testCase"
  private static final List<BlockKind> CAPITALIZED_BLOCKS = [BlockKind.SETUP, BlockKind.WHEN, BlockKind.EXPECT]

  private final PrintWriter writer

  FeatureReporter(PrintWriter writer) {
    this.writer = writer
  }

  void reportFeature(FeatureInfo feature) {
    reportFeature(feature, null, null)
  }

  void reportIteration(IterationInfo iteration) {
    reportFeature(iteration.feature, iteration.name, iteration.dataValues)
  }

  void reportFeature(FeatureInfo feature, String testName, Object... params) {
    String testCase = testName ?: feature.name
    int testCaseParamCount = feature.dataVariables.indexOf(TEST_CASE_DESCRIPTION)
    if (params && testCaseParamCount > -1) {
      testCase = "$testCase - ${params[testCaseParamCount]}"
    }
    writer.println("$testCase:")
    for (block in feature.blocks) {
      String blockName = block.kind.name().toLowerCase()
      if (CAPITALIZED_BLOCKS.contains(block.kind)) {
        blockName = blockName.capitalize()
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
      if (params && paramName != TEST_CASE_DESCRIPTION) {
        writer.println("   [$paramName: ${params[i]}]")
      }
    }
  }
}
