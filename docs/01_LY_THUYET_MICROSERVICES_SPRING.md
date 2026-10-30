# CHUYÊN ĐỀ SEMINAR: SPRING FOR MICROSERVICES

---

## I. TỔNG QUAN VỀ KIẾN TRÚC MICROSERVICES

### 1. Kiến trúc Monolith vs. Kiến trúc Microservices
- **Monolith:**
  - Toàn bộ ứng dụng gồm giao diện, nghiệp vụ, truy xuất dữ liệu và tiến trình nền được đóng gói trong một file duy nhất (`.war` hoặc `.jar`) và chạy chung trong một tiến trình.
  - *Hạn chế khi mở rộng:* Khi hệ thống phình to, mã nguồn cồng kềnh, khó kiểm thử, một lỗi nhỏ như rò rỉ bộ nhớ ở một module có thể làm sập toàn bộ ứng dụng; khó nâng cấp công nghệ và khó mở rộng theo từng tính năng cụ thể.
- **Microservices:**
  - Phân rã ứng dụng thành một tập hợp các dịch vụ nhỏ độc lập.
  - Mỗi dịch vụ tập trung vào một nghiệp vụ cụ thể theo nguyên tắc **Single Responsibility** và **Domain-Driven Design**.
  - Các service chạy trong các tiến trình độc lập, giao tiếp với nhau qua HTTP REST, gRPC hoặc Message Broker như Kafka, RabbitMQ.
  - Mỗi service sở hữu cơ sở dữ liệu riêng biệt theo nguyên tắc **Database per Service**.

```
[Monolith]                       [Microservices Architecture]
+-------------------------+      +------------------+     +------------------+
|   All-In-One Application|      | Product Service  |     |  Order Service   |
| (UI + Order + Product)  |      |   (DB Product)   | ◄─REST API─ |    (DB Order)    |
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
| **Service Discovery & Registry** | `Spring Cloud Netflix Eureka` hoặc `Consul` | Đăng ký và tra cứu địa chỉ IP, Port của các instance service một cách động khi scale. |
| **API Gateway** | `Spring Cloud Gateway` | Điểm tiếp nhận duy nhất cho Client, routing request, xác thực JWT, rate limiting, logging. |
| **Inter-Service Communication** | `Spring Cloud OpenFeign`, `WebClient` | Gọi API giữa các service với cú pháp khai báo như gọi hàm nội bộ. |
| **Circuit Breaker & Resilience** | `Resilience4j` | Ngắt mạch tự động khi một service gặp sự cố, trả dữ liệu Fallback, ngăn sụp đổ dây chuyền. |
| **Centralized Configuration** | `Spring Cloud Config Server` | Quản lý cấu hình tập trung từ Git hoặc Vault, cập nhật cấu hình tức thì qua `@RefreshScope` mà không cần khởi động lại service. |
| **Distributed Tracing & Monitoring** | `Micrometer Tracing` + `Zipkin`/`Jaeger` | Truy vết đường đi của một request qua nhiều service phân tán bằng Trace ID và Span ID. |
| **Event-Driven Messaging** | `Spring Cloud Stream` (Kafka / RabbitMQ) | Giao tiếp bất đồng bộ, xử lý luồng sự kiện và đồng bộ dữ liệu phi tập trung. |

---

## III. ƯU ĐIỂM VÀ NHƯỢC ĐIỂM CỦA SPRING MICROSERVICES

### 1. Ưu điểm nổi bật
1. **Independent Scalability:** Chỉ mở rộng quy mô cho những service có tải cao như Tìm kiếm hoặc Flash-sale, tiết kiệm tài nguyên máy chủ.
2. **Independent Deployment:** Các nhóm phát triển có thể phát hành tính năng của service mình mà không phụ thuộc hoặc làm gián đoạn các service khác.
3. **Fault Isolation:** Khi một service gặp sự cố như gián đoạn thanh toán, các chức năng khác như xem sản phẩm, thêm vào giỏ hàng vẫn hoạt động bình thường nhờ Circuit Breaker và Fallback.
4. **Polyglot Persistence:** Mỗi service có thể chọn loại cơ sở dữ liệu tối ưu cho nghiệp vụ của nó như cơ sở dữ liệu quan hệ cho đơn hàng, MongoDB cho danh mục sản phẩm, Redis cho bộ nhớ đệm.
5. **Hệ sinh thái Spring hoàn thiện:** Tích hợp liền mạch từ bảo mật với Spring Security, truy xuất dữ liệu với Spring Data JPA đến các công cụ đám mây của Spring Cloud.

### 2. Khuyết điểm và thách thức
1. **Operational Complexity:** Thay vì quản lý một ứng dụng, nay phải vận hành hàng chục service độc lập, đòi hỏi công cụ đóng gói Docker và nền tảng điều phối như Kubernetes.
2. **Data Consistency:** Không thể dùng transaction ACID thông thường giữa các database độc lập; phải áp dụng nguyên tắc **Tính nhất quán cuối cùng** và giải pháp **Saga Pattern** theo hướng Choreography hoặc Orchestration.
3. **Network Latency:** Các lệnh gọi qua mạng tiềm ẩn nguy cơ tăng độ trễ hoặc mất kết nối; cần xử lý Retry, Timeout và Circuit Breaker.
4. **Debugging & Testing:** Luồng dữ liệu chạy qua nhiều service phân tán gây khó khăn cho việc gỡ lỗi và kiểm thử; đòi hỏi hệ thống Distributed Tracing và ghi log tập trung.

---

## IV. CÁC PATTERN THIẾT KẾ KINH ĐIỂN TRONG SPRING MICROSERVICES

1. **API Gateway Pattern:** 
   - Ẩn toàn bộ kiến trúc microservice phía sau Gateway. 
   - Client chỉ giao tiếp qua một URL duy nhất của Gateway. Gateway xử lý xác thực, phân quyền, ghi log và cân bằng tải.
2. **Service Discovery Pattern:**
   - Không cần hardcode IP và Port của từng service. Khi một service khởi động, nó tự đăng ký với Eureka Server. Khi scale thêm instance, Eureka tự động cập nhật.
3. **Circuit Breaker Pattern:**
   - Giám sát tỉ lệ lỗi gọi đến service khác. Nếu tỉ lệ lỗi vượt ngưỡng cho phép (ví dụ trên 50%), mạch chuyển sang trạng thái `OPEN`, ngắt lệnh gọi tiếp theo và kích hoạt hàm fallback ngay lập tức để bảo vệ hệ thống.
4. **Database per Service:**
   - Mỗi microservice quản lý schema dữ liệu riêng. Không service nào được truy cập trực tiếp vào cơ sở dữ liệu của service khác, chỉ tương tác thông qua API.
5. **Saga Pattern:**
   - Thay thế cơ chế Two-Phase Commit nặng nề bằng một chuỗi các transaction cục bộ kèm theo các giao dịch bù trừ để hoàn tác khi có bước thất bại.

---

## V. ỨNG DỤNG THỰC TẾ TRONG DOANH NGHIỆP

- **Thương mại điện tử:** Phân chia thành Order Service, Product Catalog Service, Payment Service, Inventory Service, Notification Service. Tiêu biểu như Amazon, Shopee, Tiki.
- **Dịch vụ phát trực tuyến:** Tiên phong sáng tạo kiến trúc Spring Cloud Netflix (Eureka, Zuul, Ribbon, Hystrix) để phục vụ hàng trăm triệu người dùng toàn cầu như Netflix.
- **Ứng dụng gọi xe và giao đồ ăn:** Định vị tài xế, tính toán lộ trình, tính cước và thanh toán đều là các microservice chuyên biệt hoạt động song song như Grab, Uber.
- **Tài chính và ngân hàng:** Quản lý tài khoản, thẩm định tín dụng, cổng thanh toán liên ngân hàng và phát hiện gian lận.
