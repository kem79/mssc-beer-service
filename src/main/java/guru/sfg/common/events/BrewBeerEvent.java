package guru.sfg.common.events;

import lombok.NoArgsConstructor;

/**
 * Created by marecm on 4/27/2021
 */
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {
    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
