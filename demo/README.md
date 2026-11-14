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

## 🛠️ CÁCH PHÁT TRIỂN HỆ THỐNG HIỆN TẠI

Hệ thống demo được xây dựng theo cấu trúc Maven Multi-Module gồm 1 `pom.xml` gốc quản lý chung phiên bản Java 17, Spring Boot `3.2.5` và Spring Cloud `2023.0.1`, bên dưới chia thành 4 module độc lập được phát triển theo thứ tự từ hạ tầng điều phối đến các service nghiệp vụ và cổng vào.

### Bước 1: Tạo `pom.xml` gốc quản lý phiên bản chung
- Khai báo `<packaging>pom</packaging>` tại `demo/pom.xml`.
- Kế thừa `spring-boot-starter-parent` phiên bản `3.2.5`.
- Import `spring-cloud-dependencies` phiên bản `2023.0.1` trong `<dependencyManagement>` để đồng bộ thư viện cho toàn bộ 4 module con: `eureka-server`, `api-gateway`, `product-service`, `order-service`.

### Bước 2: Phát triển `eureka-server` (Service Discovery)
- **Mục đích:** Tạo trung tâm đăng ký và tra cứu địa chỉ mạng cho các service.
- **Mã nguồn:** [EurekaServerApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/eureka-server/src/main/java/com/seminar/eureka/EurekaServerApplication.java) chỉ cần gắn annotation `@EnableEurekaServer`.
- **Cấu hình:** Trong [application.yml](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/eureka-server/src/main/resources/application.yml):
  - Đặt `server.port: 8761`.
  - Tắt tự đăng ký lên chính nó bằng `register-with-eureka: false` và `fetch-registry: false`.

### Bước 3: Phát triển `product-service` (Service cung cấp dữ liệu)
- **Mục đích:** Quản lý danh mục sản phẩm và cung cấp REST API cho hệ thống.
- **Mã nguồn:** [ProductServiceApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/product-service/src/main/java/com/seminar/product/ProductServiceApplication.java) sử dụng `Map<Long, Product>` lưu dữ liệu mẫu trên bộ nhớ và mở 2 endpoint dưới `/api/products`:
  - `GET /api/products`: trả về toàn bộ danh sách sản phẩm.
  - `GET /api/products/{id}`: trả về chi tiết một sản phẩm theo `id`.
- **Cấu hình:** Trong [application.yml](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/product-service/src/main/resources/application.yml):
  - Đặt `server.port: 8081` và `spring.application.name: product-service`.
  - Khai báo `eureka.client.service-url.defaultZone: http://localhost:8761/eureka/` để tự động đăng ký khi khởi động.

### Bước 4: Phát triển `order-service` (Service nghiệp vụ gọi liên service và chịu lỗi)
- **Mục đích:** Tiếp nhận đơn hàng, gọi sang `product-service` lấy giá sản phẩm và tự bảo vệ khi service đích gặp sự cố.
- **Kết nối OpenFeign:**
  - Tạo interface [ProductClient.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/order-service/src/main/java/com/seminar/order/client/ProductClient.java) gắn `@FeignClient(name = "product-service")` để gọi `GET /api/products/{id}` thông qua tên logic trên Eureka mà không cần ghi cứng IP.
  - Bật `@EnableFeignClients` tại [OrderServiceApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/order-service/src/main/java/com/seminar/order/OrderServiceApplication.java).
- **Chịu lỗi với Resilience4j:**
  - Gắn `@CircuitBreaker(name = "productServiceCB", fallbackMethod = "createOrderFallback")` trên hàm `createOrder`.
  - Khi `product-service` tắt hoặc lỗi, hàm `createOrderFallback` tự động chạy và trả về trạng thái `PENDING_CIRCUIT_BREAKER` thay vì làm treo hoặc sập `order-service`.
- **Cấu hình:** Trong [application.yml](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/order-service/src/main/resources/application.yml), đặt `server.port: 8082`, `spring.application.name: order-service`, đăng ký Eureka và cấu hình `resilience4j.circuitbreaker.instances.productServiceCB`.

### Bước 5: Phát triển `api-gateway` (Cổng vào duy nhất)
- **Mục đích:** Định tuyến mọi request từ Client vào đúng service phía sau và cân bằng tải.
- **Mã nguồn:** [ApiGatewayApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/api-gateway/src/main/java/com/seminar/gateway/ApiGatewayApplication.java) chỉ cần `@SpringBootApplication`.
- **Cấu hình:** Trong [application.yml](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/api-gateway/src/main/resources/application.yml):
  - Đặt `server.port: 8080` và `spring.application.name: api-gateway`.
  - Định nghĩa 2 route dùng tiền tố `lb://` kết hợp Eureka để cân bằng tải:
    - `/api/products/**` trỏ về `lb://PRODUCT-SERVICE`
    - `/api/orders/**` trỏ về `lb://ORDER-SERVICE`

### Bước 6: Biên dịch và kiểm thử cục bộ
Tại thư mục `demo/`, biên dịch toàn bộ 4 module bằng Maven:
```bash
mvn clean compile
```
Sau đó chạy lần lượt 4 service theo thứ tự `eureka-server` → `product-service` → `order-service` → `api-gateway` (qua IDE hoặc `mvn -pl <ten-module> spring-boot:run`) và kiểm tra trên `http://localhost:8761`.
