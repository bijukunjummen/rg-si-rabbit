package config;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AppConfigTest {

	@Autowired
	private Foo foo;

	@Test
	public void testBeanDep() {
		assertThat(foo.bar.name, is("bar1"));
	}


	@Configuration
	public static class AppConfig {

		@Bean
		public Foo foo(@Qualifier("bar1") Bar bar1) {
			return new Foo(bar1);
		}

		@Bean
		public Bar bar1() {
			return new Bar("bar1");
		}

		@Bean
		public Bar bar2() {
			return new Bar("bar2");
		}
	}
}


class Foo {
	final Bar bar;
	public Foo(Bar bar) {
		this.bar = bar;
	}
}

class Bar {
	final String name;
	public Bar(String name) {
		this.name = name;
	}

}