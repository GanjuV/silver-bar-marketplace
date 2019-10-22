package com.sbm.exception;

import com.sbm.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.MessageFormat;
import java.util.Optional;

/**
 * Exception Component.
 * @author HCL
 */
@Component
public class SBMException {

  private static PropertiesConfig propertiesConfig;

  @Autowired
  public SBMException(PropertiesConfig propertiesConfig) {
    SBMException.propertiesConfig = propertiesConfig;
  }

  /**
   * Entity NotFound Exception class
   *
   */
  public static class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
          super(message);
      }
  }

  /**
   * Duplicate Entity Exception Class
   */
  public static class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String message) {
          super(message);
      }
  }

  /**
   * Returns new RuntimeException based on template and args
   * @param exceptionType
   * @param entityType
   * @return RuntimeException
   */
  public static RuntimeException throwException(EntityType entityType, ExceptionType exceptionType) {
    String messageTemplate = getMessageTemplate(entityType, exceptionType);
    if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
      return new EntityNotFoundException(format(messageTemplate));
    } else if (ExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {
      return new DuplicateEntityException(format(messageTemplate));
    }
    return new RuntimeException(format(messageTemplate));
  }

  /**
   * Formatting the message template
   * @param template
   * @return String
   */
  private static String format(String template) {
      Optional<String> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(template));
      if (templateContent.isPresent()) {
        return templateContent.get();
      }
      return String.format(template);
  }

  /**
   * Generating message template
   * @param entityType
   * @param exceptionType
   * @return String
   */
  private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
    return entityType.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
  }

}
