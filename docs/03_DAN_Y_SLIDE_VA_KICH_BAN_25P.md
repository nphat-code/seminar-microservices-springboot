# DÀN Ý SLIDE & KỊCH BẢN BÁO CÁO SEMINAR (DƯỚI 25 PHÚT)
**Chủ đề:** Spring for Microservices  
**Môn học:** Chuyên đề J2EE / Kiến trúc hệ thống  
**Thời lượng dự kiến:** 20 - 25 phút (15 phút lý thuyết + 7 phút demo + 3 phút Q&A)

---

## I. PHÂN BỔ THỜI GIAN THUYẾT TRÌNH (TIMELINE)

| Thời gian | Nội dung | Mục tiêu |
| :---: | :--- | :--- |
| **00:00 - 03:00** | Đặt vấn đề & So sánh Monolith vs. Microservices | Thu hút người nghe, chỉ ra vì sao kiến trúc Monolith gặp nghẽn |
| **03:00 - 08:00** | Hệ sinh thái Spring Cloud & Các thành phần cốt lõi | Trình bày kiến trúc: Eureka, API Gateway, OpenFeign, Resilience4j |
| **08:00 - 12:00** | Đánh giá Ưu điểm, Nhược điểm & Thách thức | Phân tích sự đánh đổi kiến trúc (Architectural Trade-offs) |
| **12:00 - 19:00** | **Live Demo Project** (Chạy trực tiếp minh họa tính năng) | Trình diễn Eureka Dashboard, gọi qua Gateway, OpenFeign, và test Circuit Breaker |
| **19:00 - 22:00** | Ứng dụng thực tế & Kinh nghiệm đúc kết | Liên hệ các hệ thống lớn (Netflix, Shopee, Tiki) |
| **22:00 - 25:00** | Tổng kết & Hỏi đáp (Q&A) | Thảo luận và giải đáp thắc mắc chuyên sâu |

---

## II. CHI TIẾT NỘI DUNG TỪNG SLIDE (SLIDE DECK OUTLINE & VISUAL SPECIFICATION)

### Slide 1: Trang tiêu đề
- **Tiêu đề chính (Bắt buộc):** SPRING FOR MICROSERVICES
- **Tiêu đề phụ:** Xây dựng hệ thống phân tán trong Java Enterprise với Spring Boot & Spring Cloud
- **Chủ đề:** Báo cáo Seminar môn học J2EE
- **📐 Trực quan hóa & Bố cục (Visual Layout):**
  - Tiêu đề chính **SPRING FOR MICROSERVICES** in hoa toàn bộ, font chữ to đậm, nổi bật nhất ở trung tâm bên trái.
  - Tiêu đề phụ: *Xây dựng hệ thống phân tán trong Java Enterprise với Spring Boot & Spring Cloud*.
  - Bố cục tối giản công nghệ cao (Clean Minimal Tech), nền trắng, typography hiện đại.
  - Minh họa: Logo chiếc lá Spring xanh đặc trưng bên cạnh biểu tượng mạng lưới microservices dạng đồ thị phân tán (Distributed Network Mesh).

---

### Slide 2: Đặt vấn đề - Nỗi đau của kiến trúc Monolith (Monolithic Hell)
- **Nội dung chính:**
  - Một ứng dụng Web Java truyền thống (`WAR/JAR` gom chung Controller, Service, DAO vào một khối duy nhất).
  - 3 hạn chế chí mạng: Triển khai chậm chạp, Không thể mở rộng chọn lọc, Nguy cơ sập dây chuyền (Single Point of Failure).
- **📐 Trực quan hóa & Sơ đồ khối (Visual Layout & Architecture Box):**
  - **Sơ đồ khối Monolith cồng kềnh:**
    ```text
    ┌──────────────────────────────────────────────────────────────────┐
    │             ỨNG DỤNG MONOLITH (MONOLITH ALL-IN-ONE)              │
    │  [UI Controller] ── [Order Service] ── [Product Service]         │
    │  [Payment Module] ── [Report Generator (Nguy cơ rò rỉ RAM!)]     │
    └────────────────────────────────┬─────────────────────────────────┘
                                     ▼ (Chung một CSDL lớn)
                        [( Single Shared Database )]
    💥 NGUY CƠ: Module Report quá tải RAM ──► TOÀN BỘ ỨNG DỤNG NGỪNG HOẠT ĐỘNG
    ```
  - **3 Thẻ cảnh báo trực quan (Warning Visual Cards):**
    - 🐢 **Triển khai chậm:** Sửa 1 dòng code phải build và deploy lại toàn bộ file WAR hàng trăm MB.
    - 🧱 **Scale lãng phí:** Tải chức năng xem hàng tăng vọt nhưng phải nhân bản cả server chứa chức năng thanh toán.
    - 💣 **Single Point of Failure:** Lỗi rò rỉ bộ nhớ ở 1 module kéo sập toàn bộ tiến trình ứng dụng.

---

### Slide 3: Giới thiệu Kiến trúc Microservices (Chia để trị)
- **Nội dung chính:**
  - Định nghĩa: Phân rã ứng dụng thành các dịch vụ nhỏ, độc lập, tự chủ (Autonomous Services).
  - 3 nguyên lý nền tảng: Single Responsibility, Database per Service, Giao tiếp qua mạng chuẩn hóa (REST / Message Queue).
- **📐 Trực quan hóa & Sơ đồ đối sánh (Comparison Layout):**
  - **Sơ đồ đối sánh trực quan:**
    ```text
    KIẾN TRÚC MONOLITH                          KIẾN TRÚC MICROSERVICES
    ┌───────────────────────────────┐           ┌──────────────┐    ┌──────────────┐
    │  Order + Product + Payment    │           │  Order Svc   │    │ Product Svc  │
    └───────────────┬───────────────┘           └──────┬───────┘    └──────┬───────┘
                    ▼                                  ▼                   ▼
       [( Shared Large Database )]               [( Order DB )]      [( Product DB )]
       (Ràng buộc chặt, khó scale)               (Độc lập hoàn toàn, tự do lựa chọn DB)
    ```
  - **3 Huy hiệu nguyên lý (Principle Badges):**
    - 🎯 **Đơn nhiệm (Single Responsibility):** Mỗi service làm tốt một việc duy nhất.
    - 🗄️ **Database per Service:** Không bao giờ dùng chung CSDL giữa các dịch vụ.
    - 🚀 **CI/CD độc lập:** Nâng cấp và phát hành từng service trong vài phút mà không ảnh hưởng nhau.

---

### Slide 4: Tại sao chọn Spring cho Microservices?
- **Nội dung chính:**
  - Spring Boot: Nền tảng xây dựng microservice độc lập cực nhanh chỉ trong vài phút.
  - Spring Cloud: Bộ công cụ toàn diện giải quyết toàn bộ bài toán hóc búa của hệ thống phân tán (Distributed Patterns).
- **📐 Trực quan hóa & Bố cục 2 cột (Two-Column Feature Grid):**
  - **Cột 1 - Spring Boot (Microservice Core Engine):**
    - ⚡ Khởi tạo tức thì qua Spring Initializr.
    - 📦 Nhúng sẵn Web Server (Tomcat/Jetty), đóng gói thành JAR độc lập.
    - ⚙️ Auto-configuration tự động thiết lập cấu hình theo dependency.
    - 📊 Spring Boot Actuator cung cấp sẵn Health Check và Metrics.
  - **Cột 2 - Spring Cloud (Distributed Systems Orchestrator):**
    - 📡 Service Discovery: Đăng ký và định vị dịch vụ động.
    - 🚪 Intelligent Routing: Cổng giao tiếp tập trung và cân bằng tải.
    - 🔌 Circuit Breaker: Tự động ngắt mạch bảo vệ hệ thống khi có lỗi.
    - 📝 Centralized Config: Quản lý cấu hình tập trung từ xa qua Git.

---

### Slide 5: Kiến trúc tổng thể hệ sinh thái Spring Cloud (Sơ đồ kiến trúc)
- **Nội dung chính:**
  - Toàn cảnh một hệ thống Microservices chuẩn mực xây dựng trên nền tảng Spring Cloud.
  - Các thành phần phối hợp nhịp nhàng: Client -> Gateway -> Service Registry -> Business Services -> DBs.
- **📐 Trực quan hóa & Sơ đồ kiến trúc toàn cảnh (Full System Architecture Diagram):**
  ```text
                  [ 📱 Mobile App / 💻 Web Client ]
                                  │ (HTTP / HTTPS)
                                  ▼
          ┌──────────────────────────────────────────────┐
          │       SPRING CLOUD GATEWAY (Port 8080)       │
          │   (Reverse Proxy, Token Filter, Rate Limit)  │
          └───────┬───────────────────────────────┬──────┘
                  │ Tra cứu Route                 │
                  ▼                               │
        ┌──────────────────┐                      │
        │  EUREKA SERVER   │                      │
        │(Service Registry)│                      │
        └─────────▲────────┘                      │
                  │ Heartbeat / Register          │
          ┌───────┴───────────────────────────────┴──────┐
          │          CÁC MICROSERVICES NGHIỆP VỤ         │
          │  ┌──────────────────┐    OpenFeign     ┌───────────┐ │
          │  │  Order Service   │ ───────────────► │Product Svc│ │
          │  │   (Resilience4j) │ ◄- (Fallback) ── │           │ │
          │  └────────┬─────────┘                  └─────┬─────┘ │
          └───────────┼──────────────────────────────────┼───────┘
                      ▼                                  ▼
               [( Order DB )]                     [( Product DB )]
  ```

---

### Slide 6: Thành phần 1 - Service Discovery (Eureka Server)
- **Nội dung chính:**
  - Bài toán: Trong Cloud/Docker/K8s, địa chỉ IP và Port của container thay đổi liên tục khi scale hoặc restart.
  - Giải pháp: Eureka đóng vai trò như cuốn "Danh bạ thông minh" tự động cập nhật.
- **📐 Trực quan hóa & Sơ đồ tương tác 3 bước (Hub-and-Spoke Flow):**
  ```text
                            ┌────────────────────────┐
                            │     EUREKA SERVER      │
                            │   (Service Registry)   │
                            └───────▲────────▲───────┘
                                    │ (1)    │ (2)
                       Register & Heartbeat  │ Tra cứu IP động
                                    │        │
             ┌──────────────────────┴┐      ┌┴──────────────────────┐
             │    PRODUCT-SERVICE    │      │     ORDER-SERVICE     │
             │ (IP: 192.168.1.10:8081)│ ◄─── │ (Gọi REST qua Feign)  │
             └───────────────────────┘  (3) └───────────────────────┘
                                     Gọi REST API trực tiếp
  ```
  - **3 Cơ chế nổi bật (Feature Callouts):**
    - 💓 **Heartbeat định kỳ:** Service gửi tín hiệu sống mỗi 30s; quá hạn Eureka tự động xóa khỏi danh bạ.
    - 🔍 **Logic Name Lookup:** Gọi qua tên dịch vụ `http://PRODUCT-SERVICE/` thay vì IP tĩnh.
    - 🛡️ **Self-Preservation Mode:** Tự bảo vệ không xóa nhầm instance khi mạng chập chờn.

---

### Slide 7: Thành phần 2 - API Gateway (Spring Cloud Gateway)
- **Nội dung chính:**
  - Cổng kiểm soát truy cập duy nhất (Single Entry Point) cho toàn bộ hệ thống từ phía ngoài.
  - Xử lý các mối quan tâm chéo (Cross-cutting Concerns) tập trung.
- **📐 Trực quan hóa & Sơ đồ Cổng An ninh (Reverse Proxy Funnel):**
  ```text
  [ Client Request ] ──► ┌─────────────────────────────────────────┐
                         │       SPRING CLOUD GATEWAY              │
                         │ ├─ 🛡️ Authentication: Xác thực JWT Token│
                         │ ├─ ⏱️ Rate Limiter: Chống spam request  │
                         │ └─ 🔀 Dynamic Routing: Phân luồng       │
                         └───────┬─────────────────────────┬───────┘
                                 ▼                         ▼
                         [ /api/products ]          [ /api/orders ]
                         Product Service            Order Service
  ```
  - **Điểm mạnh cốt lõi:**
    - Xây dựng trên **Spring WebFlux (Non-blocking Reactive I/O)** cho hiệu năng vượt trội so với Zuul 1.x blocking truyền thống.
    - Giấu kín cấu trúc mạng nội bộ, ngăn chặn hacker tấn công trực tiếp vào từng microservice.

---

### Slide 8: Thành phần 3 - Giao tiếp giữa các Service (OpenFeign & Resilience4j)
- **Nội dung chính:**
  - **OpenFeign:** Gọi REST API dạng khai báo giao diện (Declarative REST Client), loại bỏ hoàn toàn code boilerplate của RestTemplate.
  - **Resilience4j Circuit Breaker:** Cơ chế ngắt mạch chống sập dây chuyền (Cascading Failure).
- **📐 Trực quan hóa & Sơ đồ Máy Trạng thái (Circuit Breaker State Machine):**
  ```text
      ┌──────────────────────────────────────────────────────────────┐
      │  [ CLOSED (Đóng) ] ────(Tỉ lệ lỗi > 50%)────► [ OPEN (Mở) ]  │
      │  (Bình thường:                                (Ngắt mạch:    │
      │   Request đi qua thông suốt)                   Chặn request, │
      │               ▲                                Fallback ngay)│
      │               │                                      │       │
      │         (Thử nghiệm đạt)                      (Timeout 10s)  │
      │               │                                      ▼       │
      │               └───────── [ HALF-OPEN ] ◄─────────────┘       │
      │                          (Cho 5 request                      │
      │                           thăm dò phục hồi)                  │
      └──────────────────────────────────────────────────────────────┘
  ```
  - **Minh họa Fallback:** Khi Product Service gặp sự cố -> Order Service lập tức trả về thông tin rỗng an toàn mà không bị treo connection.

---

### Slide 9: Đánh giá kiến trúc - Ưu điểm & Thách thức thực tế (Architectural Trade-offs)
- **Nội dung chính:** Đánh giá khách quan, đa chiều về mặt đánh đổi kiến trúc (Architectural Trade-offs).
- **📐 Trực quan hóa & Bảng so sánh 2 cột cân bằng (Balanced Pros & Cons Table):**

| 🟢 ƯU ĐIỂM (ADVANTAGES) | 🔴 THÁCH THỨC & NHƯỢC ĐIỂM (CHALLENGES) |
| :--- | :--- |
| ⚡ **Mở rộng linh hoạt (Elastic Scalability):** Scale đúng service chịu tải cao, tiết kiệm chi phí phần cứng. | 🌐 **Độ trễ mạng (Network Latency):** Giao tiếp qua HTTP/JSON chậm hơn gọi hàm trong bộ nhớ (In-memory). |
| 🚢 **Triển khai độc lập (Independent Deployment):** Release tính năng mới mà không sợ ảnh hưởng module khác. | 🔄 **Tính nhất quán dữ liệu (Data Consistency):** Cần dùng Saga Pattern thay cho `@Transactional` ACID. |
| 🛡️ **Cô lập lỗi (Fault Isolation):** Lỗi ở một tính năng phụ không làm sập toàn bộ hệ thống. | 🛠️ **Vận hành phức tạp (Operational Complexity):** Cần giám sát phân tán (Distributed Tracing, Actuator). |
| 🤹 **Tự do công nghệ (Polyglot):** Mỗi service có thể dùng phiên bản Java hoặc DB tối ưu nhất. | 🧪 **Kiểm thử khó khăn (Testing):** Khó thiết lập môi trường test tích hợp end-to-end trên máy cá nhân. |

---

### Slide 10: Giới thiệu Kiến trúc Demo Project
- **Nội dung chính:** Mô hình 4 microservices thực chiến được xây dựng trong chuyên đề.
- **📐 Trực quan hóa & Sơ đồ Topology Demo Lab (4-Node Cluster Layout):**
  ```text
  [ Client / Postman ] ────────► :8080 [ api-gateway ] (Spring Cloud Gateway)
                                             │
                      ┌──────────────────────┴──────────────────────┐
                      ▼ :8082                                       ▼ :8081
              [ order-service ] ─────(OpenFeign + CB)─────► [ product-service ]
                      │                                             │
                      └──────────────► :8761 ◄──────────────────────┘
                                [ discovery-server ]
                                  (Eureka Server)
  ```
  - **Bảng thông số 4 Service:**
    - 📡 `discovery-server` (:8761) - Trung tâm điều phối danh bạ.
    - 🚪 `api-gateway` (:8080) - Cổng tiếp nhận toàn bộ request từ người dùng.
    - 📦 `product-service` (:8081) - Dịch vụ dữ liệu sản phẩm.
    - 🛒 `order-service` (:8082) - Dịch vụ đơn hàng tích hợp OpenFeign & Resilience4j.

---

### Slide 11: Kịch bản Live Demo (3 Bước trực quan trên màn hình)
- **Nội dung chính:** 3 tình huống thử nghiệm trực tiếp trên màn hình chiếu.
- **📐 Trực quan hóa & Sơ đồ luồng 3 bước (Step-by-Step Flowchart):**
  - **Bước 1: Khởi động & Giám sát**  
    🖥️ Mở `http://localhost:8761` trên trình duyệt -> Xem Dashboard Eureka hiển thị đủ 3 instance xanh (`API-GATEWAY`, `ORDER-SERVICE`, `PRODUCT-SERVICE`).
  - **Bước 2: Định tuyến qua Gateway & Gọi liên dịch vụ**  
    🚀 Gửi Request qua Gateway `POST http://localhost:8080/api/orders` -> Gateway định tuyến sang `order-service`, `order-service` gọi tiếp `product-service` qua OpenFeign lấy giá tiền -> Trả về đơn hàng hoàn chỉnh (Status: 200 OK).
  - **Bước 3: Thử thách chịu lỗi (Circuit Breaker kích hoạt)**  
    💥 Tắt trực tiếp `product-service` (mô phỏng sự cố server) -> Gửi lại request tạo đơn -> Circuit Breaker lập tức kích hoạt hàm Fallback, trả về đơn hàng an toàn với thông tin sản phẩm tạm thời thay vì sập lỗi 500!

---

### Slide 12: Ứng dụng thực tế & Khi nào NÊN / KHÔNG NÊN dùng?
- **Nội dung chính:** Ma trận quyết định kiến trúc cho các doanh nghiệp và dự án phần mềm.
- **📐 Trực quan hóa & Ma trận quyết định (Decision Matrix Cards):**
  - 🟢 **KHI NÀO NÊN DÙNG (THỰC TẾ ÁP DỤNG THÀNH CÔNG):**
    - Doanh nghiệp lớn với lượng truy cập khổng lồ: Netflix, Amazon, Shopee, Tiki, các hệ thống Core Banking.
    - Quy mô dự án lớn, có từ 3 đội ngũ lập trình (Domain Teams) trở lên cùng phát triển song song.
    - Các hệ thống yêu cầu độ sẵn sàng cực cao (99.99%) và khả năng scale tự động theo mùa vụ.
  - 🔴 **KHI NÀO KHÔNG NÊN DÙNG (CÂN NHẮC KỸ):**
    - Dự án khởi nghiệp giai đoạn MVP: Yêu cầu tính năng thay đổi hàng ngày, chưa rõ mô hình kinh doanh.
    - Đội ngũ lập trình nhỏ (dưới 5 người): Chi phí vận hành, bảo trì microservices sẽ vượt quá lợi ích mang lại.
    - **Lời khuyên Martin Fowler:** *"Đừng bắt đầu bằng Microservices nếu ứng dụng của bạn chưa thực sự cần nó. Hãy bắt đầu từ Monolith được thiết kế tốt!"*

---

### Slide 13: Tổng kết & Hỏi đáp (Q&A)
- **Nội dung chính:**
  - Tóm tắt 3 thông điệp kỹ thuật then chốt của bài seminar.
  - Tài liệu tham khảo chuẩn chỉnh & Lời cảm ơn.
- **📐 Trực quan hóa & Bố cục 3 Thẻ Đúc Kết (Key Takeaway Cards):**
  - 🧩 **1. Chia tách hợp lý:** Microservices không chỉ là chia nhỏ code, mà là chia ranh giới nghiệp vụ (Bounded Context) và CSDL độc lập.
  - 🍃 **2. Spring Cloud là tiêu chuẩn:** Hệ sinh thái hoàn chỉnh nhất trên Java giúp giải quyết nhanh gọn Eureka, Gateway, Feign và Circuit Breaker.
  - ⚖️ **3. Kiến trúc là đánh đổi:** Không có kiến trúc hoàn hảo, chỉ có kiến trúc phù hợp với bài toán, quy mô và con người.
  - 💬 **Khu vực Hỏi - Đáp:** Logo các đầu sách tham khảo (Manning, O'Reilly) & Lời mời thầy cô, bạn học đặt câu hỏi thảo luận.

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
