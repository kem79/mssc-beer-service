package guru.springframework.msscbeerservice.service.inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled // for manual test only
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getOnHandInventory() {
        //todo evolve to use UPC
//        Integer qoh = beerInventoryService.getOnHandInventory(BeerLoader.BEER_1_UUID);
//
//        System.out.println(qoh);
    }
}