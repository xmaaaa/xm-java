package com.ofs.domain.order;

import com.ofs.domain.order.application.command.OrderCommandService;
import com.ofs.domain.order.application.command.OrderCommandServiceImpl;
import com.ofs.domain.order.application.query.OrderQueryService;
import com.ofs.domain.order.application.query.OrderQueryServiceImpl;
import com.ofs.domain.order.application.query.OrderView;
import com.ofs.domain.order.domain.service.OrderDomainService;
import com.ofs.domain.order.domain.exception.IllegalOrderStateException;
import com.ofs.domain.order.domain.model.Order;
import com.ofs.domain.order.domain.model.OrderId;
import com.ofs.domain.order.domain.model.OrderRepository;
import com.ofs.domain.order.domain.state.OrderState;
import com.ofs.domain.order.infrastructure.repository.InMemoryOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderCommandServiceTest {

    private OrderCommandService orderService;
    private OrderRepository repo;
    private OrderQueryService queryService;

    @BeforeEach
    void setUp() {
        repo = new InMemoryOrderRepository();
        orderService = new OrderCommandServiceImpl(new OrderDomainService(repo));
        queryService = new OrderQueryServiceImpl(repo);
    }

    @Test
    void createDraftAndSubmitAndPayAndShip() {
        OrderId id = orderService.createDraft("user-1", List.of(
                new OrderCommandService.OrderLineDto("SKU-1", 2, new BigDecimal("50.00"))
        ));
        assertNotNull(id.getValue());
        assertTrue(id.getValue().startsWith("ORD-"));

        OrderView order = queryService.getById(id).orElseThrow();
        assertEquals(OrderState.DRAFT, order.state());
        assertEquals(1, order.lines().size());
        assertEquals(new BigDecimal("100.00"), order.totalAmount());

        orderService.submit(id);
        assertEquals(OrderState.SUBMITTED, queryService.getById(id).orElseThrow().state());

        orderService.markPaid(id, "PAY-1");
        assertEquals(OrderState.PAID, queryService.getById(id).orElseThrow().state());

        orderService.ship(id);
        assertEquals(OrderState.SHIPPED, queryService.getById(id).orElseThrow().state());
    }

    @Test
    void illegalTransitionThrows() {
        OrderId id = orderService.createDraft("user-1", List.of(
                new OrderCommandService.OrderLineDto("SKU-1", 1, new BigDecimal("10.00"))
        ));
        assertThrows(IllegalOrderStateException.class, () -> orderService.markPaid(id, "PAY-1")); // DRAFT 不能直接 PAY
    }

    @Test
    void cancelFromDraft() {
        OrderId id = orderService.createDraft("user-1", List.of(
                new OrderCommandService.OrderLineDto("SKU-1", 1, new BigDecimal("10.00"))
        ));
        orderService.cancel(id);
        assertEquals(OrderState.CANCELLED, queryService.getById(id).orElseThrow().state());
    }
}
