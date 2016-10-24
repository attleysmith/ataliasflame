package hu.asgames.report

import org.spockframework.runtime.model.FeatureInfo

/**
 * @author AMiklo on 2016.10.24.
 */
class FeatureReporter {

  private final PrintWriter writer;

  FeatureReporter(PrintWriter writer) {
    this.writer = writer
  }

  private void reportFeature(FeatureInfo feature) {
    writer.println(feature.name + ':')
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
          writer.println(' ' + blockName + ' ' + text)
          firstText = false
        } else {
          writer.println(' ' + 'and ' + text)
        }
      }
    }
  }
}
