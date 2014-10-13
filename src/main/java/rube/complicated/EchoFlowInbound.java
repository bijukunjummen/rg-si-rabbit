package rube.complicated;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import rube.config.RabbitConfig;

@Configuration
//@ImportResource("classpath:/rube/complicated/broker.xml")
public class EchoFlowInbound {

	@Autowired
	private RabbitConfig rabbitConfig;

	@Bean
	public IntegrationFlow inboundFlow() {
		return IntegrationFlows.from(Amqp
				.inboundGateway(connectionFactory, rabbitConfig.rubeQueue()))
				.transform((String s) -> s.toUpperCase())
				.get();
	}

	@Autowired
	private ConnectionFactory connectionFactory;
}
