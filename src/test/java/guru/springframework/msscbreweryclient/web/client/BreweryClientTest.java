package guru.springframework.msscbreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import guru.springframework.msscbreweryclient.web.model.BeerDto;

@SpringBootTest
class BreweryClientTest {
	
	@Autowired
	private BreweryClient client;

	
	@Test
	void testGetBeerById() {
		BeerDto dto = this.client.getBeerById(UUID.randomUUID());
		
		assertNotNull(dto);
	}
	
	@Test
	void testSaveBeer() {
		
		BeerDto dto = BeerDto.builder()
				.beerName("Beer 1")
				.build();
		URI uri = this.client.saveBeer(dto);		
		assertNotNull(uri);
		
		System.out.println(uri.toString());
	}
	
	@Test
	void testUpdateBeer() {
		
		BeerDto dto = BeerDto.builder()
				.beerName("Beer 1")
				.build();
		this.client.updateBeer(UUID.randomUUID(), dto);
	}
	
	@Test
	void testDeleteBeer() {
		
		this.client.deleteBeer(UUID.randomUUID());
	}

}
