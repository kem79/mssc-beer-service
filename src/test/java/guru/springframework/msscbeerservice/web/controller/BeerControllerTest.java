package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getBeerById() throws Exception {

        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .param("iscold", "yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer",
                        pathParameters(
                                parameterWithName("beerId").description("UUID of the desired beer to get")
                        ),
                        requestParameters(
                                parameterWithName("iscold").description("Is Beer Cold query parameter")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Id of Beer"),
                                fieldWithPath("version").description("Version number"),
                                fieldWithPath("createdDate").description("Date created"),
                                fieldWithPath("lastModifiedDate").description("Date Updated"),
                                fieldWithPath("beerName").description("Beer name"),
                                fieldWithPath("beerStyle").description("Beer style"),
                                fieldWithPath("upc").description("UPC of Beer"),
                                fieldWithPath("price").description("Price"),
                                fieldWithPath("quantityOnHand").description("Quantity on hand")
                        )));

    }

    @Test
    void saveNewBeer() throws Exception {
        // given
        BeerDto beerDto = BeerDto.builder()
                .price(new BigDecimal("12.95"))
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(100000000L)
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        // when
        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        // given
        BeerDto beerDto = BeerDto.builder()
                .price(new BigDecimal("12.95"))
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(100000000L)
                .build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
        .contentType(MediaType.APPLICATION_JSON)
        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

}