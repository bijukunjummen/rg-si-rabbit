package rube.amqp;

import com.google.common.base.Joiner;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;


import static java.util.stream.Collectors.toList;

@Configuration
public class EchoFlowOutBound {

	@Bean
	public IntegrationFlow toOutboundQueueFlow(AmqpTemplate rubeExchangeTemplate) {
		return IntegrationFlows.from("requestChannel")
				.split(s -> s.applySequence(true).get().getT2().setDelimiters("\\s"))
				.handle(Amqp.outboundGateway(rubeExchangeTemplate))
				.resequence()
				.aggregate(aggregate ->
						aggregate.outputProcessor(g ->
								Joiner.on(" ").join(g.getMessages()
										.stream()
										.map(m -> (String) m.getPayload()).collect(toList())))
						)
				.get();
	}
}