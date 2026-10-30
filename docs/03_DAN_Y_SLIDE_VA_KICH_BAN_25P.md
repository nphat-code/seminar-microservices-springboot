# DÀN Ý SLIDE & KỊCH BẢN BÁO CÁO SEMINAR (DƯỚI 25 PHÚT)
**Chủ đề:** Spring for Microservices  
**Môn học:** Chuyên đề J2EE / Kiến trúc hệ thống  
**Thời lượng dự kiến:** 20 - 25 phút (15 phút lý thuyết + 7 phút demo + 3 phút Q&A)

---

## I. PHÂN BỔ THỜI GIAN THUYẾT TRÌNH

| Thời gian | Nội dung | Mục tiêu |
| :---: | :--- | :--- |
| **00:00 - 03:00** | Mở đầu, Nội dung trình bày & So sánh Monolith vs. Microservices | Giới thiệu đề tài, cấu trúc 3 phần, chỉ ra vì sao kiến trúc Monolith gặp nghẽn |
| **03:00 - 08:00** | Hệ sinh thái Spring Cloud & Các thành phần cốt lõi | Trình bày kiến trúc: Eureka, API Gateway, OpenFeign, Resilience4j |
| **08:00 - 12:00** | Đánh giá Ưu điểm, Nhược điểm & Thách thức | Phân tích sự đánh đổi kiến trúc |
| **12:00 - 19:00** | **Live Demo Project** (Chạy trực tiếp minh họa tính năng) | Trình diễn Eureka Dashboard, gọi qua Gateway, OpenFeign, và test Circuit Breaker |
| **19:00 - 22:00** | Ứng dụng thực tế & Kinh nghiệm đúc kết | Liên hệ các hệ thống lớn (Netflix, Shopee, Tiki) |
| **22:00 - 25:00** | Tổng kết và hỏi đáp | Thảo luận và giải đáp thắc mắc chuyên sâu |

---

## II. CHI TIẾT NỘI DUNG TỪNG SLIDE

### Slide 1: Trang tiêu đề
- **Tiêu đề chính (Bắt buộc):** SPRING FOR MICROSERVICES
- **Tiêu đề phụ:** Xây dựng hệ thống phân tán trong Java Enterprise với Spring Boot & Spring Cloud
- **Chủ đề:** Báo cáo Seminar môn học J2EE
- **📐 Thiết kế trực quan và bố cục:**
  - Tiêu đề chính **SPRING FOR MICROSERVICES** in hoa toàn bộ, font chữ to đậm, nổi bật nhất ở trung tâm bên trái.
  - Tiêu đề phụ: *Xây dựng hệ thống phân tán trong Java Enterprise với Spring Boot & Spring Cloud*.
  - Bố cục tối giản công nghệ cao, nền trắng, typography hiện đại.
  - Minh họa: Logo chiếc lá Spring xanh đặc trưng bên cạnh biểu tượng mạng lưới microservices dạng đồ thị phân tán.

---

### Slide 2: Nội dung trình bày
- **Tiêu đề chính:** NỘI DUNG TRÌNH BÀY
- **Nội dung chính:**
  - 1. Bối cảnh & Lý do chuyển dịch (Monolith ➔ Microservices).
  - 2. Hệ sinh thái Spring Cloud & Các thành phần kiến trúc cốt lõi.
  - 3. Live Demo thực chiến mô hình 4 microservices (Định tuyến & Chịu lỗi).
- **📐 Bố cục 3 thẻ lộ trình:**
  - **Cột 1 - Bối cảnh & Kiến trúc nền tảng:**
    - 🏛️ Bối cảnh chuyển dịch từ khối Monolith cồng kềnh sang Microservices.
    - 🎯 3 nguyên lý nền tảng: Single Responsibility, Database per Service, Giao tiếp chuẩn hóa.
  - **Cột 2 - Hệ sinh thái Spring Cloud:**
    - 🍃 4 thành phần trụ cột: Eureka Server, Spring Cloud Gateway, OpenFeign, Resilience4j Circuit Breaker.
    - ⚖️ Phân tích khách quan: Ưu điểm và thách thức thực tế.
  - **Cột 3 - Thực nghiệm Live Demo:**
    - 💻 Mô phỏng hệ thống 4 Microservices thực chiến trên Spring Boot 3.
    - 💥 Thử thách chịu lỗi trực tiếp: Gateway Routing & Resilience4j Circuit Breaker tự ngắt/phục hồi khi service tắt.

---

### Slide 3: Nỗi đau của kiến trúc Monolith
- **Nội dung chính:**
  - Một ứng dụng Web Java truyền thống (`WAR/JAR` gom chung Controller, Service, DAO vào một khối duy nhất).
  - 3 hạn chế chí mạng: Triển khai chậm chạp, Không thể mở rộng chọn lọc, Nguy cơ sập toàn bộ hệ thống.
- **📐 Thiết kế trực quan và sơ đồ khối:**
  - **Sơ đồ khối Monolith cồng kềnh:**
    ```text
    ┌──────────────────────────────────────────────────────────────────┐
    │                       ỨNG DỤNG MONOLITH                          │
    │  [UI Controller] ── [Order Service] ── [Product Service]         │
    │  [Payment Module] ── [Report Generator (Nguy cơ rò rỉ RAM!)]     │
    └────────────────────────────────┬─────────────────────────────────┘
                                     ▼ (Chung một CSDL lớn)
                        [( Single Shared Database )]
    💥 NGUY CƠ: Module Report quá tải RAM ──► TOÀN BỘ ỨNG DỤNG NGỪNG HOẠT ĐỘNG
    ```
  - **3 Thẻ cảnh báo trực quan:**
    - 🐢 **Triển khai chậm:** Sửa 1 dòng code phải build và deploy lại toàn bộ file WAR hàng trăm MB.
    - 🧱 **Scale lãng phí:** Tải chức năng xem hàng tăng vọt nhưng phải nhân bản cả server chứa chức năng thanh toán.
    - 💣 **Nguy cơ sập hệ thống:** Lỗi rò rỉ bộ nhớ ở 1 module kéo sập toàn bộ tiến trình ứng dụng.

---

### Slide 4: Giới thiệu Kiến trúc Microservices
- **Tiêu đề chính:** KIẾN TRÚC MICROSERVICES
- **Nội dung chính:**
  - Định nghĩa: Phân rã ứng dụng thành các service nhỏ, độc lập, tự chủ.
  - 3 nguyên lý nền tảng: Single Responsibility, Database per Service, CI/CD độc lập.
- **📐 Sơ đồ đối sánh:**
  - **Sơ đồ đối sánh trực quan:**
    ```text
    KIẾN TRÚC MONOLITH                          KIẾN TRÚC MICROSERVICES
    ┌───────────────────────────────┐           ┌─────────────────┐    ┌─────────────────┐
    │  Order + Product + Payment    │           │  Order Service  │    │ Product Service │
    └───────────────┬───────────────┘           └────────┬────────┘    └────────┬────────┘
                    ▼                                    ▼                      ▼
       [( Shared Large Database )]                 [( Order DB )]         [( Product DB )]
       (Ràng buộc chặt, khó scale)                 (Độc lập hoàn toàn, tự do lựa chọn DB)
    ```
  - **3 Huy hiệu nguyên lý:**
    - 🎯 **Single Responsibility:** Mỗi service làm tốt một việc duy nhất.
    - 🗄️ **Database per Service:** Không bao giờ dùng chung cơ sở dữ liệu giữa các service.
    - 🚀 **CI/CD độc lập:** Nâng cấp và phát hành từng service trong vài phút mà không ảnh hưởng nhau.

---

### Slide 5: Tại sao chọn Spring cho Microservices?
- **Tiêu đề chính:** TẠI SAO CHỌN SPRING CHO MICROSERVICES?
- **Nội dung chính:**
  - Spring Boot: Nền tảng xây dựng microservice độc lập cực nhanh chỉ trong vài phút.
  - Spring Cloud: Bộ công cụ toàn diện giải quyết toàn bộ bài toán hóc búa của hệ thống phân tán.
- **📐 Bố cục 2 cột:**
  - **Cột 1 - Spring Boot:**
    - ⚡ Khởi tạo tức thì qua Spring Initializr.
    - 📦 Nhúng sẵn Web Server Tomcat hoặc Jetty, đóng gói thành JAR độc lập.
    - ⚙️ Auto-configuration tự động thiết lập cấu hình theo dependency.
    - 📊 Spring Boot Actuator cung cấp sẵn Health Check và Metrics.
  - **Cột 2 - Spring Cloud:**
    - 📡 Service Discovery: Đăng ký và định vị service động.
    - 🚪 Intelligent Routing: Cổng giao tiếp tập trung và cân bằng tải.
    - 🔌 Resilience4j Circuit Breaker: Tự động ngắt mạch bảo vệ hệ thống khi có lỗi.
    - 📝 Centralized Config: Quản lý cấu hình tập trung từ xa qua Git.

---

### Slide 6: Kiến trúc tổng thể hệ sinh thái Spring Cloud
- **Nội dung chính:**
  - Toàn cảnh một hệ thống Microservices chuẩn mực xây dựng trên nền tảng Spring Cloud.
  - Các thành phần phối hợp nhịp nhàng: Client -> Gateway -> Service Registry -> Business Services -> DBs.
- **📐 Sơ đồ kiến trúc toàn cảnh:**
  ```text
                  [ 📱 Mobile App / 💻 Web Client ]
                                  │ (HTTP / HTTPS)
                                  ▼
          ┌────────────────────────────────────────────────────────┐
          │            SPRING CLOUD GATEWAY (Port 8080)            │
          │        (Reverse Proxy, Token Filter, Rate Limit)       │
          └───────────┬────────────────────────────────┬───────────┘
                      │ Tra cứu Route                  │
                      ▼                                │
            ┌──────────────────┐                       │
            │  EUREKA SERVER   │                       │
            │(Service Registry)│                       │
            └─────────▲────────┘                       │
                      │ Heartbeat / Register           │
          ┌───────────┴────────────────────────────────┴───────────┐
          │               CÁC MICROSERVICES NGHIỆP VỤ              │
          │ ┌──────────────────┐  OpenFeign   ┌──────────────────┐ │
          │ │  Order Service   │ ───────────► │ Product Service  │ │
          │ │   (Resilience4j) │ ◄(Fallback)─ │                  │ │
          │ └────────┬─────────┘              └────────┬─────────┘ │
          └──────────┼─────────────────────────────────┼───────────┘
                     ▼                                 ▼
              [( Order DB )]                   [( Product DB )]
  ```

---

### Slide 7: Service Discovery với Eureka Server
- **Nội dung chính:**
  - Bài toán: Trong Cloud/Docker/K8s, địa chỉ IP và Port của container thay đổi liên tục khi scale hoặc restart.
  - Giải pháp: Eureka đóng vai trò như cuốn "Danh bạ thông minh" tự động cập nhật.
- **📐 Sơ đồ tương tác 3 bước:**
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
  - **3 Cơ chế nổi bật:**
    - 💓 **Heartbeat định kỳ:** Service gửi tín hiệu sống mỗi 30s; quá hạn Eureka tự động xóa khỏi danh bạ.
    - 🔍 **Logic Name Lookup:** Gọi qua tên service `http://PRODUCT-SERVICE/` thay vì IP tĩnh.
    - 🛡️ **Self-Preservation Mode:** Tự bảo vệ không xóa nhầm instance khi mạng chập chờn.

---

### Slide 8: API Gateway với Spring Cloud Gateway
- **Nội dung chính:**
  - Cổng kiểm soát truy cập duy nhất cho toàn bộ hệ thống từ phía ngoài.
  - Xử lý các tác vụ chung tập trung.
- **📐 Sơ đồ Cổng An ninh:**
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
    - Xây dựng trên **Spring WebFlux** theo mô hình **Non-blocking Reactive I/O** cho hiệu năng vượt trội so với Zuul 1.x blocking truyền thống.
    - Giấu kín cấu trúc mạng nội bộ, ngăn chặn hacker tấn công trực tiếp vào từng microservice.

---

### Slide 9: Giao tiếp và chịu lỗi với OpenFeign và Resilience4j
- **Nội dung chính:**
  - **OpenFeign:** Gọi REST API dạng Declarative REST Client, loại bỏ hoàn toàn boilerplate code của RestTemplate.
  - **Resilience4j Circuit Breaker:** Cơ chế ngắt mạch chống sập dây chuyền.
- **📐 Sơ đồ máy trạng thái Circuit Breaker:**
  ```text
      ┌──────────────────────────────────────────────────────────────┐
      │  [ CLOSED ] ─────────(Tỉ lệ lỗi > 50%)────────► [ OPEN ]     │
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

### Slide 10: Đánh giá kiến trúc - Ưu điểm và thách thức thực tế
- **Nội dung chính:** Đánh giá khách quan, đa chiều về các yếu tố đánh đổi trong kiến trúc.
- **📐 Bảng so sánh 2 cột:**

| 🟢 ƯU ĐIỂM | 🔴 THÁCH THỨC VÀ NHƯỢC ĐIỂM |
| :--- | :--- |
| ⚡ **Mở rộng linh hoạt:** Mở rộng đúng service chịu tải cao, tiết kiệm chi phí tài nguyên. | 🌐 **Độ trễ mạng:** Giao tiếp qua HTTP và JSON có độ trễ lớn hơn gọi hàm trong bộ nhớ. |
| 🚢 **Triển khai độc lập:** Phát hành tính năng mới cho từng service mà không ảnh hưởng toàn hệ thống. | 🔄 **Tính nhất quán dữ liệu:** Cần áp dụng Saga Pattern thay cho transaction cục bộ ACID. |
| 🛡️ **Cô lập lỗi:** Lỗi ở một service không gây sập dây chuyền toàn bộ ứng dụng. | 🛠️ **Vận hành phức tạp:** Đòi hỏi công cụ giám sát phân tán và quản lý tập trung. |
| 🤹 **Tự do công nghệ:** Mỗi service có thể chọn công nghệ và hệ cơ sở dữ liệu phù hợp nhất. | 🧪 **Kiểm thử phức tạp:** Việc kiểm thử tích hợp toàn diện trên môi trường cục bộ tốn nhiều công sức. |

---

### Slide 11: Giới thiệu Kiến trúc Demo Project
- **Nội dung chính:** Mô hình 4 microservices thực chiến được xây dựng trong chuyên đề.
- **📐 Sơ đồ hệ thống 4 Service Demo:**
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
    - 📦 `product-service` (:8081) - Service dữ liệu sản phẩm.
    - 🛒 `order-service` (:8082) - Service đơn hàng tích hợp OpenFeign & Resilience4j Circuit Breaker.

---

### Slide 12: Kịch bản Live Demo
- **Nội dung chính:** 3 tình huống thử nghiệm trực tiếp trên màn hình chiếu.
- **📐 Sơ đồ 3 bước thử nghiệm:**
  - **Bước 1: Khởi động & Giám sát**  
    🖥️ Mở `http://localhost:8761` trên trình duyệt -> Xem Dashboard Eureka hiển thị đủ 3 instance xanh (`API-GATEWAY`, `ORDER-SERVICE`, `PRODUCT-SERVICE`).
  - **Bước 2: Định tuyến qua Gateway & Gọi liên service**  
    🚀 Gửi Request qua Gateway `POST http://localhost:8080/api/orders` -> Gateway định tuyến sang `order-service`, `order-service` gọi tiếp `product-service` qua OpenFeign lấy giá tiền -> Trả về đơn hàng hoàn chỉnh (Status: 200 OK).
  - **Bước 3: Thử thách chịu lỗi (Resilience4j Circuit Breaker kích hoạt)**  
    💥 Tắt trực tiếp `product-service` (mô phỏng sự cố server) -> Gửi lại request tạo đơn -> Resilience4j Circuit Breaker lập tức kích hoạt hàm Fallback, trả về đơn hàng an toàn với thông tin sản phẩm tạm thời thay vì sập lỗi 500!

---

### Slide 13: Ứng dụng thực tế & Khi nào NÊN / KHÔNG NÊN dùng?
- **Nội dung chính:** Ma trận quyết định kiến trúc cho các doanh nghiệp và dự án phần mềm.
- **📐 Ma trận quyết định:**
  - 🟢 **KHI NÀO NÊN DÙNG (THỰC TẾ ÁP DỤNG THÀNH CÔNG):**
    - Doanh nghiệp lớn với lượng truy cập khổng lồ: Netflix, Amazon, Shopee, Tiki, các hệ thống Core Banking.
    - Quy mô dự án lớn, có từ 3 nhóm phát triển theo nghiệp vụ trở lên cùng làm việc song song.
    - Các hệ thống yêu cầu độ sẵn sàng cực cao (99.99%) và khả năng scale tự động theo mùa vụ.
  - 🔴 **KHI NÀO KHÔNG NÊN DÙNG (CÂN NHẮC KỸ):**
    - Dự án khởi nghiệp giai đoạn MVP: Yêu cầu tính năng thay đổi hàng ngày, chưa rõ mô hình kinh doanh.
    - Đội ngũ lập trình nhỏ (dưới 5 người): Chi phí vận hành, bảo trì microservices sẽ vượt quá lợi ích mang lại.
    - **Lời khuyên Martin Fowler:** *"Đừng bắt đầu bằng Microservices nếu ứng dụng của bạn chưa thực sự cần nó. Hãy bắt đầu từ Monolith được thiết kế tốt!"*

---

### Slide 14: Tổng kết và hỏi đáp
- **Nội dung chính:**
  - Tóm tắt 3 thông điệp kỹ thuật then chốt của bài seminar.
  - Tài liệu tham khảo chuẩn chỉnh & Lời cảm ơn.
- **📐 Bố cục 3 thẻ đúc kết:**
  - 🧩 **1. Chia tách hợp lý:** Microservices không chỉ là chia nhỏ code, mà là chia ranh giới nghiệp vụ và cơ sở dữ liệu độc lập.
  - 🍃 **2. Spring Cloud là tiêu chuẩn:** Hệ sinh thái hoàn chỉnh nhất trên Java giúp giải quyết nhanh gọn Eureka, Gateway, Feign và Circuit Breaker.
  - ⚖️ **3. Kiến trúc là đánh đổi:** Không có kiến trúc hoàn hảo, chỉ có kiến trúc phù hợp với bài toán, quy mô và con người.
  - 💬 **Khu vực Hỏi - Đáp:** Logo các đầu sách tham khảo (Manning, O'Reilly) & Lời mời thầy cô, bạn học đặt câu hỏi thảo luận.

---

## III. BỘ CÂU HỎI PHẢN BIỆN THƯỜNG GẶP CỦA GIẢNG VIÊN & HƯỚNG TRẢ LỜI CHUẨN XÁC

### Câu 1: "Làm sao đảm bảo tính toàn vẹn dữ liệu khi mỗi Microservice có một Database riêng thay vì dùng @Transactional thông thường?"
- **Trả lời:**
  - Trong Microservices, ta áp dụng nguyên tắc **Tính nhất quán cuối cùng** thay vì Strict ACID.
  - Giải pháp kinh điển là **Saga Pattern**:
    - *Choreography-based Saga:* Các service phát sự kiện qua Message Broker như Kafka hoặc RabbitMQ, service tiếp theo lắng nghe và xử lý.
    - *Orchestration-based Saga:* Có một Orchestrator service điều phối luồng giao dịch.
  - Khi một bước ở giữa bị lỗi, hệ thống sẽ kích hoạt các **Giao dịch bù trừ** để hoàn tác trạng thái trước đó.

### Câu 2: "Tại sao nên dùng Spring Cloud Gateway thay vì Netflix Zuul cũ?"
- **Trả lời:**
  - Netflix Zuul 1.x hoạt động theo mô hình Blocking I/O (mỗi request chiếm một Thread), hiệu năng kém khi chịu tải lớn.
  - Spring Cloud Gateway được xây dựng trên **Spring WebFlux**, sử dụng mô hình **Non-blocking Reactive I/O**, tiêu tốn ít tài nguyên bộ nhớ hơn và hỗ trợ WebSockets, rate-limiting mượt mà hơn.

### Câu 3: "Circuit Breaker có những trạng thái nào? Nó hoạt động ra sao?"
- **Trả lời:**
  - Gồm 3 trạng thái chính:
    1. **CLOSED:** Trạng thái bình thường, tất cả các request được chuyển tiếp đến service đích.
    2. **OPEN:** Khi tỉ lệ lỗi vượt ngưỡng cho phép (ví dụ > 50%), mạch ngắt; mọi request đến sau đều bị chặn lại ngay lập tức và gọi vào hàm Fallback (không gửi request qua mạng nữa để tránh nghẽn server đích).
    3. **HALF-OPEN:** Sau một khoảng thời gian chờ, mạch cho phép một số lượng request nhỏ đi qua để kiểm tra xem service đích đã bình phục chưa. Nếu thành công -> chuyển về CLOSED; nếu vẫn lỗi -> quay lại OPEN.
