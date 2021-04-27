package guru.springframework.msscbeerservice.event;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by marecm on 4/27/2021
 */
@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 4129804180705537841L;

    private final BeerDto beerDto;
}
