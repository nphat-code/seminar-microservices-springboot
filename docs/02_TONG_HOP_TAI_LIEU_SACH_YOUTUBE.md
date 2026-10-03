# TỔNG HỢP TÀI LIỆU THAM KHẢO VỀ SPRING MICROSERVICES

Tài liệu được phân loại chi tiết theo **Sách chuyên ngành**, **Kênh & Video YouTube chất lượng**, **Bài viết & Trang web học tập**, và **Kho mã nguồn mẫu (GitHub)**.

---

## I. SÁCH CHUYÊN NGÀNH (BOOKS) - ĐƯỢC ĐÁNH GIÁ CAO NHẤT

### 1. Spring Microservices in Action (Second Edition)
- **Tác giả:** John Carnell, Illary Huaylupo Sánchez
- **Nhà xuất bản:** Manning Publications (2021)
- **Điểm nổi bật:**
  - Cuốn sách gối đầu giường kinh điển về Spring Cloud.
  - Hướng dẫn chi tiết từng thành phần: Eureka, Spring Cloud Gateway, Resilience4j, Spring Cloud Config, Security với Keycloak/OAuth2, Docker và Kubernetes.
  - Văn phong thực chiến, kèm code mẫu từng bước từ cơ bản đến nâng cao.

### 2. Cloud Native Spring in Action: With Spring Boot and Kubernetes
- **Tác giả:** Thomas Vitale
- **Nhà xuất bản:** Manning Publications (2022)
- **Điểm nổi bật:**
  - Hướng dẫn xây dựng hệ sinh thái microservice hiện đại chuẩn Cloud-Native.
  - Tích hợp Spring Boot 3, Spring Cloud, Testcontainers, Docker, Helm và Kubernetes.
  - Tập trung vào tính sẵn sàng cao, bảo mật và khả năng quan sát (observability).

### 3. Building Microservices: Designing Fine-Grained Systems (2nd Edition)
- **Tác giả:** Sam Newman
- **Nhà xuất bản:** O'Reilly Media (2021)
- **Điểm nổi bật:**
  - Cung cấp nền tảng tư duy kiến trúc Microservices chuẩn mực nhất ngành phần mềm.
  - Giải thích sâu sắc về Domain-Driven Design (DDD), phân tách cơ sở dữ liệu, bảo mật, và xử lý distributed transaction (Saga pattern).

---

## II. KÊNH & VIDEO YOUTUBE NỔI TIẾNG NÊN THEO DÕI

### 1. Kênh quốc tế (Tiếng Anh - Chất lượng hàng đầu)
1. **Amigoscode (Nelson Djalo):**
   - *Video nổi bật:* "Spring Boot Microservices Full Course" (Khoá học toàn diện xây dựng hệ thống Microservices hoàn chỉnh từ zero).
   - *Điểm mạnh:* Giải thích cực kỳ trực quan, dễ hiểu, clean code và cập nhật phiên bản mới.
   - *Link:* `https://www.youtube.com/@amigoscode`

2. **Daily Code Buffer:**
   - *Video nổi bật:* "Spring Cloud Tutorial" / "Microservices with Spring Boot and Spring Cloud".
   - *Điểm mạnh:* Hướng dẫn từng bài tập ngắn gọn (5 - 15 phút) cho từng module: Eureka Server, API Gateway, Feign Client, Resilience4j, Zipkin.
   - *Link:* `https://www.youtube.com/@DailyCodeBuffer`

3. **Java Techie:**
   - *Nội dung:* Các pattern nâng cao trong Spring Cloud như Circuit Breaker (Resilience4j), Distributed Tracing (Micrometer + Zipkin), Event-Driven với Kafka.
   - *Link:* `https://www.youtube.com/@JavaTechie`

4. **FreeCodeCamp.org:**
   - *Video:* "Microservices with Spring Boot and Spring Cloud – Full Course" (4-6 tiếng trọn gói lý thuyết và thực hành).
   - *Link:* `https://www.youtube.com/@freecodecamp`

### 2. Kênh Việt Nam
1. **28tech / Lập trình Java:**
   - Các bài chia sẻ tư duy chuyển đổi từ Monolith sang Microservices, so sánh ưu nhược điểm dễ hiểu cho sinh viên.
2. **Kênh Huỳnh Nhật Duy / Viblo Code:**
   - Các video phân tích kiến trúc Microservices thực tế tại các công ty công nghệ Việt Nam.

---

## III. TÀI LIỆU CHÍNH THỐNG & BÀI VIẾT KỸ THUẬT (ARTICLES & DOCS)

### 1. Tài liệu chính thức (Official Documentation)
- **Spring Cloud Official Docs:**
  `https://spring.io/projects/spring-cloud`
- **Spring Cloud Gateway Reference:**
  `https://docs.spring.io/spring-cloud-gateway/reference/`
- **Spring Cloud OpenFeign Docs:**
  `https://docs.spring.io/spring-cloud-openfeign/reference/`

### 2. Các blog kỹ thuật uy tín
- **Baeldung (Chuyên trang hàng đầu về Java/Spring):**
  - Chuyên mục *Spring Cloud*: `https://www.baeldung.com/spring-cloud`
  - Bài viết *Spring Cloud Netflix Eureka*: `https://www.baeldung.com/spring-cloud-netflix-eureka`
  - Bài viết *Spring Cloud Gateway*: `https://www.baeldung.com/spring-cloud-gateway`
  - Bài viết *Resilience4j with Spring Boot*: `https://www.baeldung.com/resilience-4j`
- **Microservices.io (Tác giả Chris Richardson):**
  - Danh mục toàn bộ các Design Pattern của Microservices (Saga, CQRS, API Gateway, Service Registry,...): `https://microservices.io/patterns/index.html`
- **Medium (Kênh Netflix TechBlog):**
  - Câu chuyện thực tế cách Netflix dịch chuyển sang Microservices và tạo ra Eureka, Ribbon, Hystrix.

---

## IV. REPOSITORY GITHUB MẪU ĐỂ KHẢO SÁT & HỌC HỎI

1. **PiggyMetrics (Kho mẫu Microservices kinh điển của cộng đồng Spring):**
   - Đề tài ứng dụng quản lý tài chính cá nhân viết bằng Spring Cloud, Eureka, Config Server, Docker.
   - Link: `https://github.com/sqshq/piggymetrics`
2. **Spring Cloud Samples (Official Samples của Spring team):**
   - Link: `https://github.com/spring-cloud-samples`
3. **Microservices Spring Boot 3 by Amigoscode:**
   - Kho mẫu sử dụng Spring Boot 3 mới nhất với Spring Cloud Gateway, Kafka, Zipkin và Eureka.
