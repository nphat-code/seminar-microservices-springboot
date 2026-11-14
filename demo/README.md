# SPRING MICROSERVICES DEMO PROJECT (SEMINAR J2EE)

Dự án mẫu minh họa thực chiến cho bài báo cáo Seminar **"Spring for Microservices"**.

---

## 🏛️ KIẾN TRÚC MÔ HÌNH HỆ THỐNG DEMO

Hệ thống bao gồm 4 services phối hợp với nhau theo đúng chuẩn Microservices hiện đại:

```
                            [ Client (Browser / Postman / cURL) ]
                                              │
                                              ▼ (Port 8080)
                                     ┌─────────────────┐
                                     │   API GATEWAY   │ (Spring Cloud Gateway)
                                     └────────┬────────┘
                                              │ (Load balanced qua Eureka)
                      ┌───────────────────────┴───────────────────────┐
                      ▼                                               ▼
         ┌─────────────────────────┐                     ┌─────────────────────────┐
         │     PRODUCT-SERVICE     │                     │      ORDER-SERVICE      │
         │       (Port 8081)       │ ◄─── OpenFeign ──── │       (Port 8082)       │
         │                         │   (Resilience4j)    │                         │
         └────────────┬────────────┘                     └────────────┬────────────┘
                      │                                               │
                      └───────────────────────┬───────────────────────┘
                                              │ Heartbeat & Service Registry
                                              ▼
                                   ┌─────────────────────┐
                                   │    EUREKA SERVER    │ (Port 8761)
                                   │  (Service Discovery)│
                                   └─────────────────────┘
```

---

## 📂 DANH SÁCH CÁC MODULE

1. **`eureka-server` (Port `8761`)**: 
   - Đóng vai trò là Service Registry.
   - Cung cấp giao diện trực quan tại: `http://localhost:8761` để xem danh sách các service đang online.
2. **`product-service` (Port `8081`)**: 
   - Cung cấp API quản lý thông tin sản phẩm, giá bán, tồn kho.
   - Endpoint: `GET /api/products`, `GET /api/products/{id}`.
3. **`order-service` (Port `8082`)**: 
   - Cung cấp API tạo đơn hàng: `POST /api/orders?productId=1&quantity=2`.
   - Sử dụng **Spring Cloud OpenFeign** để gọi sang `product-service` lấy thông tin sản phẩm.
   - Tích hợp **Resilience4j Circuit Breaker** để bắt sự cố và kích hoạt Fallback khi `product-service` gặp lỗi/bị tắt.
4. **`api-gateway` (Port `8080`)**:
   - Cổng vào duy nhất của toàn bộ hệ thống.
   - Tự động điều hướng:
     - `http://localhost:8080/api/products/**` ➡️ `PRODUCT-SERVICE`
     - `http://localhost:8080/api/orders/**` ➡️ `ORDER-SERVICE`

---

## 🚀 THỨ TỰ KHỞI ĐỘNG CÁC SERVICE TRÊN IDE (IntelliJ IDEA / Eclipse / VS Code)

> 💡 **Khuyến nghị mở thư mục `demo` bằng IntelliJ IDEA hoặc VS Code:**

Khởi chạy lần lượt 4 file main theo đúng thứ tự:
1. Chạy `EurekaServerApplication` (chờ 10-15s cho Eureka khởi động).
2. Chạy `ProductServiceApplication`.
3. Chạy `OrderServiceApplication`.
4. Chạy `ApiGatewayApplication`.

---

## 🎬 KỊCH BẢN LIVE DEMO KHI THUYẾT TRÌNH TRÊN LỚP (GÂY ẤN TƯỢNG VỚI GIẢNG VIÊN)

### 📌 Bước 1: Trình diễn Service Discovery (Eureka Dashboard)
- Mở trình duyệt truy cập: `http://localhost:8761`
- Chỉ cho giảng viên và cả lớp thấy:
  - 3 instance `API-GATEWAY`, `PRODUCT-SERVICE`, `ORDER-SERVICE` đã tự động đăng ký với trạng thái **UP**.
  - Không cần cấu hình cứng bất kỳ địa chỉ IP hay Port nào giữa các service.

### 📌 Bước 2: Trình diễn API Gateway Routing
- Không cần gọi trực tiếp port `8081` hay `8082`, Client chỉ cần gọi qua Gateway (Port `8080`):
  - Lấy danh sách sản phẩm qua Gateway:
    `GET http://localhost:8080/api/products`
  - Đặt hàng qua Gateway:
    `POST http://localhost:8080/api/orders?productId=1&quantity=2`
  - Kết quả trả về JSON thành công, trong đó `order-service` đã tự gọi sang `product-service` bằng FeignClient để lấy tên món đồ và tính tổng tiền:
    ```json
    {
      "orderId": "c88f28aa-...",
      "productId": 1,
      "productName": "Laptop Dell XPS 15",
      "quantity": 2,
      "totalPrice": 70000000.0,
      "status": "SUCCESS",
      "note": "Đơn hàng được xác nhận qua FeignClient thành công!"
    }
    ```

### 📌 Bước 3: Trình diễn Khả năng chịu lỗi với Circuit Breaker (Resilience4j Fallback)
- **Tình huống giả định:** `product-service` bị sập do server chết hoặc mất mạng.
- **Thực hiện:**
  1. Nhấn nút **Stop** dừng `ProductServiceApplication` trên IDE.
  2. Gửi lại request đặt hàng qua Gateway:
     `POST http://localhost:8080/api/orders?productId=1&quantity=2`
- **Kết quả bất ngờ:**
  - Hệ thống **KHÔNG HỀ bị crash** hay quăng lỗi `500 Internal Server Error`.
  - Hàm `@CircuitBreaker(fallbackMethod = "createOrderFallback")` lập tức can thiệp và trả về:
    ```json
    {
      "orderId": "b11a54ec-...",
      "productId": 1,
      "quantity": 2,
      "status": "PENDING_CIRCUIT_BREAKER",
      "message": "[Resilience4j Fallback] Product Service hiện không phản hồi. Đơn hàng tạm lưu ở trạng thái chờ xử lý!"
    }
    ```
- **Điểm nhấn thuyết trình:** "Nhờ Circuit Breaker, sự cố ở một service không làm sập dây chuyền toàn bộ hệ thống, đơn hàng vẫn được tiếp nhận an toàn ở trạng thái chờ."

---

## 🛠️ HƯỚNG DẪN PHÁT TRIỂN VÀ MỞ RỘNG DỰ ÁN

### 1. Môi trường và công cụ cần thiết
- **JDK 17** trở lên (`java.version` đang cấu hình `17` trong `pom.xml`).
- **Maven 3.8+** để build dự án multi-module.
- Dự án sử dụng **Spring Boot `3.2.5`** và **Spring Cloud `2023.0.1`**.

### 2. Build và chạy bằng dòng lệnh
Tại thư mục `demo/`, biên dịch toàn bộ 4 module:
```bash
mvn clean compile
```

Nếu muốn chạy từng service bằng Maven thay vì bấm Run trên IDE, mở 4 terminal riêng và chạy lần lượt:
```bash
mvn -pl eureka-server spring-boot:run
mvn -pl product-service spring-boot:run
mvn -pl order-service spring-boot:run
mvn -pl api-gateway spring-boot:run
```

### 3. Quy trình thêm một Service mới
Ví dụ cần bổ sung `payment-service` vào hệ thống:
1. **Khai báo module:** Tạo thư mục `payment-service/` ngang hàng với các service hiện tại, thêm `<module>payment-service</module>` vào `demo/pom.xml`.
2. **Thêm dependency:** Trong `payment-service/pom.xml`, khai báo `spring-boot-starter-web` và `spring-cloud-starter-netflix-eureka-client`.
3. **Cấu hình đăng ký Eureka:** Tạo `payment-service/src/main/resources/application.yml` với cổng mới chưa bị trùng (ví dụ `8083`), đặt `spring.application.name: payment-service` và trỏ `eureka.client.service-url.defaultZone` về `http://localhost:8761/eureka/`.
4. **Mở route trên API Gateway:** Thêm một route mới trong `api-gateway/src/main/resources/application.yml`:
   ```yaml
   - id: payment-service-route
     uri: lb://PAYMENT-SERVICE
     predicates:
       - Path=/api/payments/**
   ```

### 4. Giao tiếp giữa các Service bằng OpenFeign
1. Ở service gọi đi, thêm `@EnableFeignClients` trên class `@SpringBootApplication` (tham khảo `OrderServiceApplication.java`).
2. Tạo interface client gắn `@FeignClient(name = "ten-service-dich")` và khai báo chữ ký endpoint cần gọi (tham khảo `ProductClient.java`). Không cần ghi cứng IP hay Port vì Eureka sẽ tự phân giải địa chỉ.

### 5. Bảo vệ lời gọi bằng Resilience4j Circuit Breaker
1. Gắn `@CircuitBreaker(name = "tenInstance", fallbackMethod = "tenHamFallback")` lên method có gọi sang service khác.
2. Viết hàm `Fallback` cùng kiểu trả về, cùng danh sách tham số với method gốc và thêm tham số cuối `Throwable` để nhận ngoại lệ khi service đích gặp sự cố.
3. Khai báo ngưỡng ngắt mạch và thời gian chờ phục hồi dưới `resilience4j.circuitbreaker.instances` trong `application.yml` của service đó (tham khảo `order-service/src/main/resources/application.yml`).

### 6. Lưu ý khi phát triển
- Mỗi service phải dùng một `server.port` khác nhau khi chạy local (`8761`, `8080`, `8081`, `8082`).
- Khi sửa `pom.xml` gốc hoặc thêm module mới, nhớ chạy `mvn clean compile` để kiểm tra toàn bộ cây phụ thuộc trước khi commit.
- Đặt tên class, package và tài liệu luôn ghi rõ `Service`, không viết tắt.
