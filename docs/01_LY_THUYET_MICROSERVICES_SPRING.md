# CHUYÊN ĐỀ SEMINAR: SPRING FOR MICROSERVICES

---

## I. TỔNG QUAN VỀ KIẾN TRÚC MICROSERVICES

### 1. Kiến trúc Monolith vs. Kiến trúc Microservices
- **Monolith (Nguyên khối):**
  - Toàn bộ ứng dụng (UI, Business Logic, Data Access, Background Jobs) được đóng gói trong một file duy nhất (`.war` hoặc `.jar`) và chạy chung trong một tiến trình (process).
  - *Hạn chế khi mở rộng:* Khi hệ thống phình to, mã nguồn cồng kềnh, khó kiểm thử, một lỗi nhỏ (ví dụ memory leak ở một module) có thể làm sập toàn bộ ứng dụng; khó nâng cấp công nghệ và khó mở rộng theo từng tính năng cụ thể.
- **Microservices (Vi dịch vụ):**
  - Phân rã ứng dụng thành một tập hợp các dịch vụ nhỏ độc lập (small, autonomous services).
  - Mỗi dịch vụ tập trung vào một nghiệp vụ cụ thể (theo nguyên tắc **Single Responsibility Principle** và **Domain-Driven Design - DDD**).
  - Các service chạy trong các tiến trình độc lập, giao tiếp với nhau qua giao thức mạng nhẹ (HTTP REST, gRPC, hoặc Message Broker như Kafka, RabbitMQ).
  - Mỗi service có thể sở hữu cơ sở dữ liệu riêng (**Database per Service**).

```
[Monolith]                       [Microservices Architecture]
+-------------------------+      +------------------+     +------------------+
|   All-In-One Application|      | Product Service  |     |  Order Service   |
| (UI + Order + Product)  |      |   (DB Product)   |     |    (DB Order)    |
+-------------------------+      +------------------+     +------------------+
             |                                     \       /
      (Shared Database)                         [ API Gateway ]
             v                                         ^
         [ DB All ]                                    |
                                                  [ Client App ]
```

---

## II. HỆ SINH THÁI SPRING CHO MICROSERVICES (SPRING CLOUD)

Spring Boot giúp tạo các standalone microservice nhanh chóng. Để kết nối và quản lý cụm microservices phân tán, Spring cung cấp **Spring Cloud** với các module cốt lõi:

| Thành phần | Module trong Spring Cloud | Chức năng chính |
| :--- | :--- | :--- |
| **Service Discovery & Registry** | `Spring Cloud Netflix Eureka` hoặc `Consul` | Đăng ký và tra cứu địa chỉ (IP, Port) của các instance service một cách động khi scale. |
| **API Gateway** | `Spring Cloud Gateway` | Điểm tiếp nhận duy nhất cho Client, routing request, xác thực JWT, rate limiting, logging. |
| **Inter-Service Communication** | `Spring Cloud OpenFeign`, `WebClient` | Gọi API giữa các service với cú pháp khai báo (declarative) như gọi hàm nội bộ. |
| **Circuit Breaker & Resilience** | `Resilience4j` (thay thế cho Hystrix) | Ngắt mạch tự động khi một service gặp sự cố, trả fallback data, ngăn sụp đổ dây chuyền (Cascading Failure). |
| **Centralized Configuration** | `Spring Cloud Config Server` | Quản lý cấu hình tập trung (Git/Vault), cập nhật config realtime qua `@RefreshScope` mà không cần restart service. |
| **Distributed Tracing & Monitoring** | `Micrometer Tracing` + `Zipkin`/`Jaeger` | Truy vết đường đi của một request qua nhiều service phân tán bằng Trace ID và Span ID. |
| **Event-Driven Messaging** | `Spring Cloud Stream` (Kafka / RabbitMQ) | Giao tiếp bất đồng bộ, xử lý luồng sự kiện và đồng bộ dữ liệu phi tập trung. |

---

## III. ƯU ĐIỂM VÀ NHƯỢC ĐIỂM CỦA SPRING MICROSERVICES

### 1. Ưu điểm nổi bật (Pros)
1. **Khả năng mở rộng độc lập (Independent Scalability):** Chỉ scale những service có tải cao (ví dụ: service Tìm kiếm hoặc Flash-sale), tiết kiệm tài nguyên máy chủ.
2. **Triển khai độc lập (Independent Deployment & CI/CD):** Các team có thể release tính năng của service mình mà không phụ thuộc hoặc làm gián đoạn các service khác.
3. **Cách ly lỗi (Fault Isolation):** Khi một service gặp sự cố (ví dụ: Payment Service tạm thời gián đoạn), các chức năng khác (xem sản phẩm, thêm vào giỏ hàng) vẫn hoạt động bình thường nhờ cơ chế Fallback / Circuit Breaker.
4. **Linh hoạt công nghệ (Polyglot Persistence / Technology):** Mỗi service có thể chọn loại database tối ưu cho nghiệp vụ của nó (Relational DB cho Order/Payment, NoSQL MongoDB cho Catalog, Redis cho Caching).
5. **Hệ sinh thái Spring hoàn thiện:** Tích hợp liền mạch từ bảo mật (Spring Security), truy xuất dữ liệu (Spring Data JPA) đến các công cụ cloud của Spring Cloud.

### 2. Khuyết điểm và thách thức (Cons & Challenges)
1. **Độ phức tạp vận hành và triển khai (Operational Complexity):** Thay vì quản lý 1 ứng dụng, nay phải quản lý hàng chục service, cần container hóa (Docker) và điều phối (Kubernetes).
2. **Tính nhất quán dữ liệu phân tán (Distributed Data Consistency):** Không thể dùng ACID transaction thông thường giữa các database độc lập; phải áp dụng mô hình **Eventual Consistency** và giải pháp **Saga Pattern** (Choreography hoặc Orchestration).
3. **Độ trễ mạng (Network Latency & Reliability):** Các lệnh gọi qua mạng tiềm ẩn nguy cơ chậm (latency) hoặc mất kết nối; cần xử lý Retry, Timeout và Circuit Breaker.
4. **Khó khăn trong gỡ lỗi và kiểm thử (Debugging & Testing):** Luồng dữ liệu chạy qua nhiều service phân tán; đòi hỏi phải cấu hình hệ thống Distributed Tracing và Centralized Logging (ELK/Grafana Loki).

---

## IV. CÁC PATTERN THIẾT KẾ KINH ĐIỂN TRONG SPRING MICROSERVICES

1. **API Gateway Pattern:** 
   - Ẩn toàn bộ kiến trúc microservice phía sau Gateway. 
   - Client chỉ giao tiếp qua 1 URL duy nhất của Gateway. Gateway xử lý xác thực, phân quyền (AuthN/AuthZ), ghi log và cân bằng tải.
2. **Service Discovery Pattern (Eureka):**
   - Không cần hardcode IP và Port của từng service. Khi một service khởi động, nó tự đăng ký với Eureka Server. Khi scale thêm instance, Eureka tự động cập nhật.
3. **Circuit Breaker Pattern (Resilience4j):**
   - Giám sát tỉ lệ lỗi gọi đến service khác. Nếu tỉ lệ lỗi vượt ngưỡng (ví dụ > 50%), circuit chuyển sang trạng thái `OPEN`, ngắt lệnh gọi tiếp theo và kích hoạt hàm fallback ngay lập tức, bảo vệ hệ thống.
4. **Database per Service:**
   - Mỗi microservice quản lý schema dữ liệu riêng. Không service nào được truy cập trực tiếp vào DB của service khác, chỉ được tương tác thông qua API.
5. **Saga Pattern (Quản lý Transaction phân tán):**
   - Thay thế Distributed 2PC (Two-Phase Commit) nặng nề bằng một chuỗi các transaction cục bộ kèm theo các Compensating Transactions (giao dịch bù trừ) để rollback khi có bước thất bại.

---

## V. ỨNG DỤNG THỰC TẾ TRONG DOANH NGHIỆP

- **Thương mại điện tử (Amazon, Shopee, Tiki):** Phân chia thành Order Service, Product Catalog Service, Payment Service, Inventory Service, Notification Service,...
- **Dịch vụ Streaming & Giải trí (Netflix):** Tiên phong sáng tạo kiến trúc Spring Cloud Netflix (Eureka, Zuul, Ribbon, Hystrix) để phục vụ hàng trăm triệu người dùng toàn cầu.
- **Ứng dụng Gọi xe & Giao đồ ăn (Uber, Grab):** Định vị tài xế, tính toán lộ trình, tính cước và thanh toán đều là các microservice chuyên biệt hoạt động song song.
- **Tài chính & Ngân hàng:** Quản lý tài khoản, thẩm định tín dụng, cổng thanh toán liên ngân hàng và phát hiện gian lận (Fraud Detection).
