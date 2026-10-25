# DÀN Ý SLIDE & KỊCH BẢN BÁO CÁO SEMINAR (DƯỚI 25 PHÚT)
**Chủ đề:** Spring for Microservices  
**Môn học:** Chuyên đề J2EE / Kiến trúc hệ thống  
**Thời lượng dự kiến:** 20 - 25 phút (15 phút lý thuyết + 7 phút demo + 3 phút Q&A)  
**Hình thức:** Thuyết trình cá nhân (1 người)

---

## I. PHÂN BỔ THỜI GIAN THUYẾT TRÌNH (TIMELINE)

| Thời gian | Nội dung | Mục tiêu |
| :---: | :--- | :--- |
| **00:00 - 03:00** | Đặt vấn đề & So sánh Monolith vs. Microservices (Slide 1 - 3) | Thu hút người nghe, dùng hình ảnh Tháp Jenga chỉ ra vì sao Monolith nghẽn |
| **03:00 - 08:30** | Hệ sinh thái Spring Cloud & Các thành phần cốt lõi (Slide 4 - 8) | Trình bày kiến trúc: Eureka (Radar), Gateway (Non-blocking), OpenFeign & Resilience4j (Cầu dao) |
| **08:30 - 10:30** | Dữ liệu phân tán & Saga Pattern (Slide 9) | Điểm nhấn kiến trúc: Giải quyết toàn vẹn dữ liệu qua Compensating Transactions |
| **10:30 - 17:30** | **Live Demo Project** (Slide 10 - 11) | Trình chiếu sơ đồ 4-node & thao tác trực tiếp: Eureka UP, Gateway routing, Circuit Breaker Fallback |
| **17:30 - 21:00** | Đánh giá kiến trúc & Ma trận quyết định 2x2 (Slide 12 - 13) | Thể hiện tư duy chuyên gia: Ma trận Trade-off & mô hình chẩn đoán 2x2 (Quy mô vs Đội ngũ) |
| **21:00 - 25:00** | Tổng kết & Hỏi đáp (Slide 14 - Q&A) | Trả lời phản biện từ thầy cô và bạn học |

---

## II. CHI TIẾT NỘI DUNG 14 SLIDE (SLIDE DECK OUTLINE)

### Slide 1: Exploring Microservices Architecture with Spring Cloud
- **Visual Design:** Phong cách Smart Grid hiện đại, tông màu công nghệ cao.
- **Nội dung:** Tiêu đề chuyên đề, môn học, người trình bày, định hình phong thái buổi seminar.

### Slide 2: The Monolith Problem (Nỗi đau kiến trúc nguyên khối)
- **Visual Metaphor:** Ẩn dụ **Tháp rút gỗ Jenga** (rút 1 thanh gỗ là cả tháp lung lay/sập).
- **Hạn chế lớn:**
  - Deployment bottlenecks (Triển khai chậm chạp).
  - Scaling limitations (Lãng phí tài nguyên khi scale).
  - Single point of failure (Sự cố dây chuyền làm sập toàn bộ hệ thống).

### Slide 3: Shifting to Microservices (Chuyển dịch sang vi dịch vụ)
- **Nội dung:** Đối lập kiến trúc nguyên khối với mô hình module hóa độc lập.
- **3 Nguyên tắc vàng:** Single Responsibility, Database per Service, và tính tự chủ (Autonomy).

### Slide 4: The Spring Boot & Spring Cloud Solution
- **Visual Metaphor:** **Động cơ 2 thành phần**:
  - *Spring Boot:* Khối động cơ máy nền tảng (Embedded Tomcat, Auto-configuration).
  - *Spring Cloud:* Bảng điều khiển và bộ điều phối hệ thống phân tán (Distributed Orchestration).

### Slide 5: Architecture Blueprint (Bức tranh toàn cảnh hệ sinh thái)
- **Visual Design:** Bản đồ trực quan đẳng cự (Isometric map) toàn bộ luồng đi từ Client -> Gateway -> Microservices -> Trung tâm hỗ trợ (Eureka, Config, Tracing).

### Slide 6: Eureka Service Discovery (Định vị dịch vụ động)
- **Visual Metaphor:** Ẩn dụ **Màn hình Radar quét mục tiêu**.
- **Cơ chế:** Service khởi động gửi Heartbeat đăng ký vào danh bạ; các service khác tra cứu vị trí tự động mà không cần IP tĩnh (No static IPs).

### Slide 7: Spring Cloud Gateway Traffic Routing (Định tuyến thông minh)
- **So sánh then chốt:** Mô hình Blocking I/O (Thread-per-request) cũ vs. Reactive Non-blocking I/O (Spring WebFlux).
- **Vai trò:** Cổng bảo vệ duy nhất, điều hướng request, tập trung xác thực và tối ưu hiệu năng chịu tải.

### Slide 8: Circuit Breaker with Resilience4j (Giao tiếp & Cầu dao chịu lỗi)
- **Visual Metaphor:** Sơ đồ **Mạch điện và Cầu dao an toàn**.
- **Cơ chế:**
  - Spring Cloud OpenFeign: Gọi REST API dạng khai báo giao diện (declarative).
  - Resilience4j: Quản lý 3 trạng thái **Closed -> Open -> Half-Open**, tự động nhảy vào hàm Fallback khi service đích gặp sự cố.

### Slide 9: Distributed Data & The Saga Pattern (Dữ liệu phân tán & Mẫu thiết kế Saga)
- **Visual Design:** Lưu đồ (Flowchart) mô tả chuỗi giao dịch cục bộ (Local Transactions).
- **Cốt lõi:** Thay thế `@Transactional` bằng **Compensating Transactions (Giao dịch bù trừ)** để đạt tính nhất quán cuối cùng (**Eventual Consistency**).

### Slide 10: Live Demo Project Schematic (Sơ đồ hệ thống demo)
- **Visual Design:** Sơ đồ tập trung 4 nút (4-node diagram):
  1. `discovery-server` (8761 - Eureka)
  2. `api-gateway` (8080 - Gateway)
  3. `product-service` (8081 - Quản lý sản phẩm)
  4. `order-service` (8082 - Đặt hàng, Feign, Resilience4j)

### Slide 11: Live Demo Script (Phao neo mắt khi thao tác trực tiếp)
- **Visual Anchor:** Chiếu cố định trên màn hình 3 điểm mốc quan sát trong lúc chuyển tab thao tác Postman / IDE:
  - Điểm 1: Eureka Dashboard (3 service online UP).
  - Điểm 2: API Gateway Routing & OpenFeign tính tổng tiền đơn hàng.
  - Điểm 3: Tắt `product-service` -> kích hoạt Fallback HTTP 200 tức thì.

### Slide 12: Architectural Evaluation (Đánh giá kiến trúc chuyên sâu)
- **Visual Design:** Ma trận cân bằng Trade-off (Ưu điểm vs. Thách thức).
- **Phân tích:** Lợi thế mở rộng, độc lập triển khai vs. Độ phức tạp vận hành, độ trễ mạng và bài toán dữ liệu.

### Slide 13: Decision Matrix: When to Adopt Microservices? (Ma trận quyết định 2x2)
- **Visual Design:** Ma trận 4 góc phần tư 2x2 (Quy mô dự án vs. Độ lớn đội ngũ phát triển).
- **Khuyến nghị:**
  - Startup / Team nhỏ / Dự án sơ khởi (MVP) -> Chọn **Monolith** với Spring Boot.
  - Doanh nghiệp lớn / Lưu lượng cao / Nhiều team độc lập -> Chọn **Microservices**.

### Slide 14: Summary & Learning Resources (Tổng kết & Mở rộng)
- **Nội dung:** Tổng kết các trụ cột kiến trúc cốt lõi, mở lời cảm ơn và bước vào phần hỏi đáp (Q&A).

---

## III. BỘ CÂU HỎI PHẢN BIỆN THƯỜNG GẶP CỦA GIẢNG VIÊN & HƯỚNG TRẢ LỜI CHUẨN XÁC

### Câu 1: "Làm sao đảm bảo tính toàn vẹn dữ liệu khi mỗi Microservice có một Database riêng? (Không dùng được @Transactional thông thường)"
- **Trả lời:**
  - Áp dụng nguyên lý **Eventual Consistency** (Tính nhất quán cuối cùng) thay vì Strict ACID.
  - Sử dụng **Saga Pattern**: chia giao dịch thành chuỗi các giao dịch cục bộ. Khi một bước thất bại, kích hoạt các **Compensating Transactions** (Giao dịch bù trừ) để hoàn tác trạng thái (ví dụ: nhả kho, đổi trạng thái đơn sang ĐÃ HỦY).

### Câu 2: "Tại sao nên dùng Spring Cloud Gateway thay vì Netflix Zuul cũ?"
- **Trả lời:**
  - Netflix Zuul 1.x chạy theo mô hình Blocking I/O (Thread-per-request), dễ cạn kiệt thread pool khi có tải cao hoặc mạng trễ.
  - Spring Cloud Gateway xây dựng trên **Spring WebFlux (Project Reactor)** theo cơ chế **Non-blocking / Reactive I/O**, tiêu tốn ít bộ nhớ và chịu tải đồng thời vượt trội.

### Câu 3: "Circuit Breaker có những trạng thái nào? Nó hoạt động ra sao?"
- **Trả lời:**
  - Gồm 3 trạng thái:
    1. **CLOSED (Đóng):** Trạng thái bình thường, request đi qua bình thường.
    2. **OPEN (Mở):** Tỉ lệ lỗi vượt ngưỡng cho phép (ví dụ > 50%), cầu dao ngắt, chặn đứng request và chuyển ngay sang hàm Fallback.
    3. **HALF-OPEN (Nửa mở):** Sau thời gian chờ, cho một số ít request thăm dò đi qua. Nếu thành công -> chuyển về CLOSED; nếu vẫn lỗi -> quay lại OPEN.
