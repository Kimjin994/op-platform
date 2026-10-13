package com.only4play.genserver.config;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class CodeGenServerEnabledCondition extends SpringBootCondition {

  @Override
  public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
    CodeGenServerProperties serverProperties = getClientProperties(context);

    if (!serverProperties.getServer().isEnabled()) {
      return ConditionOutcome
          .noMatch("CodeGen Server is disabled, because 'codegen.server.enabled' is false.");
    }

    return ConditionOutcome.match();
  }

  private CodeGenServerProperties getClientProperties(ConditionContext context) {
    CodeGenServerProperties serverProperties = new CodeGenServerProperties();
    Binder.get(context.getEnvironment()).bind("codegen.server", Bindable.ofInstance(serverProperties));
    return serverProperties;
  }

}
