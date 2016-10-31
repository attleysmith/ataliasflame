package hu.asgames.report

import org.spockframework.runtime.AbstractRunListener
import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.ErrorInfo
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.IterationInfo
import org.spockframework.runtime.model.SpecInfo

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author AMiklo on 2016.10.21.
 */
class ReportExtension extends AbstractAnnotationDrivenExtension<Report> {

  private static final String REPORT_PATH = "reports/"
  private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern('yyyyMMddhhmmss')

  @Override
  void visitSpecAnnotation(Report annotation, SpecInfo spec) {
    spec.addListener(new AbstractRunListener() {

      PrintWriter writer;
      boolean success = true;

//      @Override
//      void beforeFeature(FeatureInfo feature) {
//        println(feature.name + ':')
//        boolean firstBlock = true
//        for (block in feature.blocks) {
//          String blockName = block.kind.name().toLowerCase()
//          if (firstBlock) {
//            blockName = blockName.capitalize()
//            firstBlock = false
//          }
//          boolean firstText = true
//          for (text in block.texts) {
//            if (firstText) {
//              println(blockName + ' ' + text)
//              firstText = false
//            } else {
//              println('and ' + text)
//            }
//          }
//        }
//        println()
//      }

      @Override
      void beforeSpec(SpecInfo specInfo) {
//        println('--- ' + specInfo.name + ' ---')
        writer = printWriter(specInfo.name)
      }

      @Override
      void beforeIteration(IterationInfo iteration) {
        success = true
        new FeatureReporter(writer).reportIteration(iteration)
      }

      @Override
      void afterIteration(IterationInfo iteration) {
        if (success) {
          writer.println('SUCCESS')
        }
        writer.println()
      }

//      @Override
//      void afterFeature(FeatureInfo feature) {
//        println(feature)
//      }

      @Override
      void afterSpec(SpecInfo specInfo) {
        writer.close()
      }

      @Override
      void error(ErrorInfo errorInfo) {
        success = false
        writer.println('ERROR:')
        def error = errorInfo.error
        try {
          new ErrorReporter(writer).reportError(errorInfo.error)
        } catch (Exception e) {
          writer.println('!!!Error handler failure for error type!!! ( ' + error.class + ' )')
          writer.println(' - ' + error.message)
        }
      }

      @Override
      void specSkipped(SpecInfo specInfo) {
        PrintWriter skipWriter = printWriter(specInfo.name)
        specInfo.getAllFeatures().each { feature ->
          reportSkippedFeature(skipWriter, feature)
        }
        skipWriter.close()
      }

      @Override
      void featureSkipped(FeatureInfo feature) {
        reportSkippedFeature(writer, feature)
      }

      private PrintWriter printWriter(String fileName) {
        String timestamp = LocalDateTime.now().format(DT_FORMATTER)
        return new PrintWriter("${REPORT_PATH + fileName + '_' + timestamp}.txt", "UTF-8");
      }

      private void reportSkippedFeature(PrintWriter skipWriter, FeatureInfo feature) {
        new FeatureReporter(skipWriter).reportFeature(feature)
        skipWriter.println('SKIPPED')
        skipWriter.println()
      }
    })
  }
}
