package com.design.elegant.config;

import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "charge")
@Component
@Data
public class ChargeFilterConfigProperties {

  private Map<String, List<String>> configs;

}
