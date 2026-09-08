package com.ofs.domain;

import com.ofs.domain.order.application.command.OrderCommandService;
import com.ofs.domain.order.application.command.OrderCommandServiceImpl;
import com.ofs.domain.order.application.query.OrderQueryService;
import com.ofs.domain.order.application.query.OrderQueryServiceImpl;
import com.ofs.domain.order.application.query.OrderView;
import com.ofs.domain.order.domain.service.OrderDomainService;
import com.ofs.domain.order.domain.model.OrderId;
import com.ofs.domain.order.domain.model.OrderRepository;
import com.ofs.domain.order.domain.state.OrderState;
import com.ofs.domain.order.infrastructure.repository.InMemoryOrderRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * 端到端演示：创建草稿 -> 提交 -> 标记支付 -> 发货，展示状态机流转。
 * 运行方式：在 IDE 中运行 main，或 mvn exec:java -pl ofs-domain -Dexec.mainClass="com.ofs.domain.ScenarioDemo"
 */
public class ScenarioDemo {

    public static void main(String[] args) {
        OrderRepository repo = new InMemoryOrderRepository();
        OrderCommandService orderService = new OrderCommandServiceImpl(new OrderDomainService(repo));
        // 读走 CQRS 读侧，不再借用写侧接口
        OrderQueryService queryService = new OrderQueryServiceImpl(repo);

        OrderCommandService.OrderLineDto line1 = new OrderCommandService.OrderLineDto("SKU-001", 2, new BigDecimal("99.00"));
        OrderCommandService.OrderLineDto line2 = new OrderCommandService.OrderLineDto("SKU-002", 1, new BigDecimal("199.00"));

        // 1. 创建草稿
        OrderId orderId = orderService.createDraft("user-1", List.of(line1, line2));
        System.out.println("Created order: " + orderId.getValue());

        OrderView order = require(queryService, orderId);
        System.out.println("  state=" + order.state() + ", total=" + order.totalAmount());

        // 2. 提交
        orderService.submit(orderId);
        order = require(queryService, orderId);
        System.out.println("After submit: state=" + order.state());

        // 3. 支付成功
        orderService.markPaid(orderId, "PAY-001");
        order = require(queryService, orderId);
        System.out.println("After pay: state=" + order.state());

        // 4. 发货
        orderService.ship(orderId);
        order = require(queryService, orderId);
        System.out.println("After ship: state=" + order.state());

        // 5. 若再发 PAY 事件会抛 IllegalOrderStateException（可注释掉试取消流程）
        // orderService.cancel(orderId);
        // order = require(queryService, orderId);
        // System.out.println("After cancel: state=" + order.state());

        System.out.println("Done. Final state=" + order.state() + " (expected " + OrderState.SHIPPED + ")");
    }

    private static OrderView require(OrderQueryService queryService, OrderId orderId) {
        return queryService.getById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found: " + orderId.getValue()));
    }
}
