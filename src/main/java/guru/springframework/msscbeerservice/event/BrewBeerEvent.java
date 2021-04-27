package guru.springframework.msscbeerservice.event;

import guru.springframework.msscbeerservice.web.model.BeerDto;

/**
 * Created by marecm on 4/27/2021
 */
public class BrewBeerEvent extends BeerEvent {
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
