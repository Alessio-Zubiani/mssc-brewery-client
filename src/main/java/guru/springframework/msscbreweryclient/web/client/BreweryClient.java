package guru.springframework.msscbreweryclient.web.client;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import lombok.Setter;

@Setter

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BreweryClient {

	public final String BEER_PATH_V1 = "/api/v1/beer/";
	private final RestTemplate restTemplate;
	
	private String apiHost;
	
	public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	
	public BeerDto getBeerById(UUID beerId) {
		
		return this.restTemplate.getForObject(this.apiHost.concat(BEER_PATH_V1).concat(beerId.toString()), BeerDto.class);
	}
	
	public URI saveBeer(BeerDto beerDto) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<BeerDto> requestEntity = new HttpEntity<>(beerDto, headers);
		
		return this.restTemplate.postForLocation(this.apiHost.concat(BEER_PATH_V1), requestEntity);
	}
	
	public void updateBeer(UUID beerId, BeerDto beerDto) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<BeerDto> requestEntity = new HttpEntity<>(beerDto, headers);
		
		this.restTemplate.put(this.apiHost.concat(BEER_PATH_V1).concat(beerId.toString()), requestEntity);
	}
	
	public void deleteBeer(UUID beerId) {
		
		this.restTemplate.delete(this.apiHost.concat(BEER_PATH_V1).concat(beerId.toString()));
	}
	
}
