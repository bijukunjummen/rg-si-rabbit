package rube.simple;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class EchoFlow {

	@Bean
	public IntegrationFlow simpleEchoFlow() {
		return IntegrationFlows.from("requestChannel")
				.transform((String s) -> s.toUpperCase())
				.get();
	}
}
