# DÀN Ý SLIDE & KỊCH BẢN BÁO CÁO SEMINAR (DƯỚI 25 PHÚT)
**Chủ đề:** Spring for Microservices  
**Môn học:** Chuyên đề J2EE / Kiến trúc hệ thống  
**Thời lượng dự kiến:** 20 - 25 phút (15 phút lý thuyết + 7 phút demo + 3 phút Q&A)

---

## I. PHÂN BỔ THỜI GIAN THUYẾT TRÌNH (TIMELINE)

| Thời gian | Nội dung | Mục tiêu |
| :---: | :--- | :--- |
| **00:00 - 03:00** | Đặt vấn đề & So sánh Monolith vs. Microservices | Thu hút người nghe, chỉ ra vì sao kiến trúc nguyên khối gặp nghẽn |
| **03:00 - 08:00** | Hệ sinh thái Spring Cloud & Các thành phần cốt lõi | Trình bày kiến trúc: Eureka, API Gateway, OpenFeign, Resilience4j |
| **08:00 - 12:00** | Phân tích Ưu điểm, Nhược điểm & Thách thức thực tế | Thể hiện tư duy phản biện (không phải lúc nào cũng nên dùng Microservices) |
| **12:00 - 19:00** | **Live Demo Project** (Chạy trực tiếp minh họa tính năng) | Trình diễn Eureka Dashboard, gọi qua Gateway, OpenFeign, và test Circuit Breaker |
| **19:00 - 22:00** | Ứng dụng thực tế & Kinh nghiệm đúc kết | Liên hệ các hệ thống lớn (Netflix, Shopee, Tiki) |
| **22:00 - 25:00** | Tổng kết & Hỏi đáp (Q&A) | Trả lời câu hỏi phản biện từ thầy cô và bạn học |

---

## II. CHI TIẾT NỘI DUNG TỪNG SLIDE (SLIDE DECK OUTLINE)

### Slide 1: Trang tiêu đề
- **Tiêu đề:** TÌM HIỂU KIẾN TRÚC VI DỊCH VỤ VỚI SPRING CLOUD (SPRING FOR MICROSERVICES)
- **Thông tin:** Giảng viên hướng dẫn, Tên nhóm, Thành viên thực hiện, Ngày báo cáo.

### Slide 2: Đặt vấn đề - Nỗi đau của kiến trúc Monolith (Monolithic Hell)
- Một ứng dụng Web Java truyền thống (`WAR/JAR` gom chung Controller, Service, DAO vào một chỗ).
- **Hạn chế lớn:**
  - Triển khai chậm (deploy lại toàn bộ hệ thống dù chỉ sửa 1 dòng code).
  - Khó mở rộng tài nguyên có chọn lọc (cần scale module tính tiền nhưng phải nhân bản cả server).
  - Nguy cơ sập dây chuyền (Single Point of Failure): một module tràn RAM làm chết cả ứng dụng.

### Slide 3: Giới thiệu Kiến trúc Microservices
- Định nghĩa: Chia nhỏ ứng dụng thành các dịch vụ độc lập, tự chủ (Autonomous Services).
- Nguyên lý: Single Responsibility, Database per service, giao tiếp qua HTTP REST / Message Queue.
- Sơ đồ trực quan so sánh Monolith vs. Microservices.

### Slide 4: Tại sao chọn Spring cho Microservices?
- Spring Boot: Khởi tạo service độc lập cực nhanh chỉ trong vài phút (auto-configuration, embedded server Tomcat/Jetty).
- Spring Cloud: Bộ công cụ toàn diện giải quyết toàn bộ các bài toán hóc búa của hệ thống phân tán (Distributed Systems Patterns).

### Slide 5: Kiến trúc tổng thể hệ sinh thái Spring Cloud (Sơ đồ kiến trúc)
- **Client** -> **Spring Cloud Gateway** -> **Microservices (Product, Order, Payment)**
- Cụm phụ trợ trung tâm:
  - **Eureka Server** (Service Registry & Discovery)
  - **Config Server** (Cấu hình tập trung)
  - **Zipkin / Micrometer** (Distributed Tracing)

### Slide 6: Thành phần 1 - Service Discovery (Eureka Server)
- Vì sao cần? Trong môi trường Cloud/Container, địa chỉ IP và Port của service thay đổi liên tục.
- Cơ chế hoạt động:
  - Service khởi động -> Gửi "Heartbeat" đăng ký với Eureka.
  - Các service khác tra cứu service qua tên logic (ví dụ `http://PRODUCT-SERVICE/`) thay vì IP tĩnh.

### Slide 7: Thành phần 2 - API Gateway (Spring Cloud Gateway)
- Đóng vai trò là "Cổng bảo vệ duy nhất":
  - Routing: Điều hướng request từ bên ngoài vào đúng microservice bên trong.
  - Cross-cutting concerns: Xác thực Token JWT, giới hạn lưu lượng (Rate Limiter), bảo vệ an ninh.

### Slide 8: Thành phần 3 - Giao tiếp giữa các Service (OpenFeign & Resilience4j)
- **Spring Cloud OpenFeign:** Viết interface khai báo gọn gàng, tự động gọi REST API ngầm định.
- **Resilience4j (Circuit Breaker):**
  - Tránh sập dây chuyền (Cascading Failure).
  - Khi service đích chết -> ngắt mạch (Open) -> trả về kết quả dự phòng (Fallback) ngay lập tức.

### Slide 9: Ưu điểm & Nhược điểm - Góc nhìn chuyên gia
- **Ưu điểm:** Scale độc lập, CI/CD nhanh, công nghệ đa dạng, cô lập lỗi tốt.
- **Nhược điểm:** Phức tạp khi vận hành, độ trễ mạng, khó xử lý giao dịch dữ liệu phân tán (Distributed Transaction / Saga).

### Slide 10: Giới thiệu Kiến trúc Demo Project
- Sơ đồ demo gồm 4 thành phần:
  1. `discovery-server` (Port 8761 - Eureka Server)
  2. `api-gateway` (Port 8080 - Spring Cloud Gateway)
  3. `product-service` (Port 8081 - Quản lý sản phẩm)
  4. `order-service` (Port 8082 - Tạo đơn hàng, gọi sang Product Service qua OpenFeign có bọc Resilience4j)

### Slide 11: Kịch bản Live Demo (Trình chiếu thực tế trên màn hình)
- Tình huống 1: Mở Eureka Dashboard xem các service tự động đăng ký.
- Tình huống 2: Client gọi API tạo đơn hàng thông qua Gateway (Port 8080).
- Tình huống 3: Tắt `product-service` để kích hoạt Circuit Breaker -> `order-service` trả về Fallback an toàn thay vì báo lỗi 500.

### Slide 12: Ứng dụng thực tế & Khi nào NÊN / KHÔNG NÊN dùng?
- **NÊN:** Hệ thống lớn, lượng người dùng cao, nhiều team cùng phát triển (Shopee, Netflix, Banking).
- **KHÔNG NÊN:** Dự án nhỏ (MVP), startup mới khởi đầu, team dưới 5 người (Monolith vẫn là lựa chọn số 1 về chi phí và tốc độ).

### Slide 13: Tổng kết & Tài liệu tham khảo
- Danh mục sách khuyên đọc (Manning, O'Reilly).
- Lời cảm ơn và chuyển sang phần Q&A.

---

## III. BỘ CÂU HỎI PHẢN BIỆN THƯỜNG GẶP CỦA GIẢNG VIÊN & HƯỚNG TRẢ LỜI CHUẨN XÁC

### Câu 1: "Làm sao đảm bảo tính toàn vẹn dữ liệu khi mỗi Microservice có một Database riêng? (Không dùng được @Transactional thông thường)"
- **Trả lời:**
  - Trong Microservices, ta áp dụng nguyên lý **Eventual Consistency** (Tính nhất quán cuối cùng) thay vì Strict ACID.
  - Giải pháp kinh điển là **Saga Pattern**:
    - *Choreography-based Saga:* Các service phát sự kiện (Event) qua Message Broker (Kafka/RabbitMQ), service tiếp theo lắng nghe và xử lý.
    - *Orchestration-based Saga:* Có một Orchestrator service điều phối luồng giao dịch.
  - Khi một bước ở giữa bị lỗi, hệ thống sẽ kích hoạt các **Compensating Transactions** (Giao dịch bù trừ) để hoàn tác trạng thái trước đó.

### Câu 2: "Tại sao nên dùng Spring Cloud Gateway thay vì Netflix Zuul cũ?"
- **Trả lời:**
  - Netflix Zuul 1.x hoạt động theo mô hình Blocking I/O (mỗi request chiếm một Thread), hiệu năng kém khi chịu tải lớn.
  - Spring Cloud Gateway được xây dựng trên **Spring WebFlux (Project Reactor)**, sử dụng mô hình **Non-blocking / Reactive I/O**, tiêu tốn ít tài nguyên bộ nhớ hơn và hỗ trợ WebSockets, rate-limiting mượt mà hơn.

### Câu 3: "Circuit Breaker có những trạng thái nào? Nó hoạt động ra sao?"
- **Trả lời:**
  - Gồm 3 trạng thái chính:
    1. **CLOSED (Đóng):** Trạng thái bình thường, tất cả các request được chuyển tiếp đến service đích.
    2. **OPEN (Mở):** Khi tỉ lệ lỗi vượt ngưỡng cho phép (ví dụ > 50%), mạch ngắt; mọi request đến sau đều bị chặn lại ngay lập tức và gọi vào hàm Fallback (không gửi request qua mạng nữa để tránh nghẽn server đích).
    3. **HALF-OPEN (Nửa mở):** Sau một khoảng thời gian chờ (wait duration), mạch cho phép một số lượng request nhỏ đi qua để kiểm tra xem service đích đã bình phục chưa. Nếu thành công -> chuyển về CLOSED; nếu vẫn lỗi -> quay lại OPEN.
