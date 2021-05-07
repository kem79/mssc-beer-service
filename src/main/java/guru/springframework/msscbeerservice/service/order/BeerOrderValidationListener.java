package guru.springframework.msscbeerservice.service.order;

import guru.sfg.brewery.model.event.ValidateBeerOrderRequestEvent;
import guru.sfg.brewery.model.event.ValidateBeerOrderResponseEvent;
import guru.springframework.msscbeerservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by marecm on 5/7/2021
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final BeerOrderValidator beerOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateBeerOrderRequestEvent event) {
        log.debug("Got beer order validation request even " + event.toString());

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESULT_QUEUE,
                ValidateBeerOrderResponseEvent.builder()
                        .isValid(beerOrderValidator.validateOrder(event.getBeerOrderDto()))
                        .orderId(event.getBeerOrderDto().getId().toString())
                        .build()
        );
    }

}
