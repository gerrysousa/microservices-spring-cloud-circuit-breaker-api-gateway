package br.com.alura.microservice.loja;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableResourceServer
public class LojaApplication {

	@Bean
	public RequestInterceptor getInterceptorDeAutenticacao(){
		return new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate requestTemplate) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication==null){
					return;
				}
				OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
				requestTemplate.header("Authorization", "Bearer "+details.getTokenValue());
			}
		};
	}

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(LojaApplication.class, args);
	}

}
