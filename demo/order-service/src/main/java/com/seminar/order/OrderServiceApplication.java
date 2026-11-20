package com.seminar.order;

import com.seminar.order.client.ProductClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
@EnableFeignClients
@RestController
@RequestMapping("/api/orders")
public class OrderServiceApplication {

    private final ProductClient productClient;
    private final List<Map<String, Object>> orderHistory = new ArrayList<>();

    public OrderServiceApplication(ProductClient productClient) {
        this.productClient = productClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @PostMapping
    @CircuitBreaker(name = "productServiceCB", fallbackMethod = "createOrderFallback")
    public Map<String, Object> createOrder(@RequestParam Long productId, @RequestParam Integer quantity) {
        ProductClient.ProductDto product = productClient.getProductById(productId);

        double totalPrice = product.getPrice() * quantity;
        Map<String, Object> order = new HashMap<>();
        order.put("orderId", UUID.randomUUID().toString());
        order.put("productId", product.getId());
        order.put("productName", product.getName());
        order.put("quantity", quantity);
        order.put("totalPrice", totalPrice);
        order.put("status", "SUCCESS");
        order.put("note", "Đơn hàng được xác nhận qua FeignClient thành công!");

        orderHistory.add(order);
        return order;
    }

    public Map<String, Object> createOrderFallback(Long productId, Integer quantity, Throwable throwable) {
        Map<String, Object> fallbackOrder = new HashMap<>();
        fallbackOrder.put("orderId", UUID.randomUUID().toString());
        fallbackOrder.put("productId", productId);
        fallbackOrder.put("quantity", quantity);
        fallbackOrder.put("status", "PENDING_CIRCUIT_BREAKER");
        fallbackOrder.put("message",
                "[Resilience4j Fallback] Product Service hiện không phản hồi. Đơn hàng tạm lưu ở trạng thái chờ xử lý!");
        fallbackOrder.put("errorDetail", throwable.getMessage());
        return fallbackOrder;
    }

    @GetMapping
    public List<Map<String, Object>> getOrders() {
        return orderHistory;
    }
}
