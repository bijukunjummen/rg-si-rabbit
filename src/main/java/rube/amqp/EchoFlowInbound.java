package rube.amqp;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import rube.config.RabbitConfig;

@Configuration
public class EchoFlowInbound {

	@Autowired
	private RabbitConfig rabbitConfig;
	@Autowired
	private ConnectionFactory connectionFactory;

	@Bean
	public IntegrationFlow inboundFlow() {
		return IntegrationFlows.from(Amqp
				.inboundGateway(connectionFactory, rabbitConfig.rubeQueue()))
				.transform((String s) -> s.toUpperCase())
				.get();
	}
}
