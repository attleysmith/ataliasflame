package hu.asgames.report

import org.spockframework.runtime.AbstractRunListener
import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.ErrorInfo
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
        String timestamp = LocalDateTime.now().format(DT_FORMATTER)
        writer = new PrintWriter("${REPORT_PATH + specInfo.name + '_' + timestamp}.txt", "UTF-8");
      }

      @Override
      void beforeIteration(IterationInfo iteration) {
        success = true
        writer.println(iteration.name + ':')

        def feature = iteration.feature
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
          new ErrorHandler(writer).handleError(errorInfo.error)
        } catch (Exception e) {
          writer.println('!!!Error handler failure for error type!!! ( ' + error.class + ' )')
          writer.println(' - ' + error.message)
        }
      }

//      @Override
//      void specSkipped(SpecInfo specInfo) {
//        println(specInfo)
//      }
//
//      @Override
//      void featureSkipped(FeatureInfo feature) {
//        println(feature)
//      }
    })
  }
}
