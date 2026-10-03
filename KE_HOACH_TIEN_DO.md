# KẾ HOẠCH VÀ TIẾN ĐỘ THỰC HIỆN SEMINAR J2EE
**Chủ đề 5:** Spring for Microservices  
**Mục tiêu:** Xung phong báo cáo sớm vào **Tuần 8 - 9** để lấy điểm cộng tối đa; hoàn tất nộp sản phẩm ở **Tuần 12**.

---

## 📅 I. LỘ TRÌNH THỰC HIỆN (TIMELINE ROADMAP)

```
[ Hiện tại ] ────► [ Trước Tuần 8 ] ────► [ Tuần 8 - 9 ] ────────► [ Tuần 12 ]
  Chuẩn bị            Làm Slide &             BÁO CÁO XUNG PHONG       Hoàn thiện & nộp
  Lý thuyết &         Luyện tập Demo          (Lấy điểm cộng)          sản phẩm chính thức
  Mã nguồn demo       nói thử <25'
```

- **Tuần 7 (Hiện tại):** Chuẩn bị đầy đủ tài liệu lý thuyết, kiến trúc, mã nguồn demo và kịch bản.
- **Trước Tuần 8:** Hoàn thành file trình chiếu PowerPoint (.pptx), test chạy demo trên máy tính cá nhân, chia vai trò các thành viên trong nhóm.
- **Tuần 8 - Tuần 9:** Xung phong báo cáo trước lớp (thời lượng <25 phút).
- **Tuần 10 - 11:** Tiếp thu góp ý của giảng viên (nếu có bổ sung gì thêm) và chuẩn bị nộp bài.
- **Tuần 12:** Đóng gói toàn bộ source code + tài liệu + slide nộp bản cuối kỳ cho giảng viên.

---

## ✅ II. NHỮNG CÔNG VIỆC ĐÃ HOÀN THÀNH (DONE)

### 1. Nghiên cứu & Lý thuyết chuyên sâu
- [x] Đã biên soạn tài liệu lý thuyết chi tiết: [01_LY_THUYET_MICROSERVICES_SPRING.md](file:///c:/Study/HK1Nam3/J2EE/Seminar/docs/01_LY_THUYET_MICROSERVICES_SPRING.md)
  - Phân tích cặn kẽ Monolith vs. Microservices.
  - Khảo sát toàn bộ hệ sinh thái Spring Cloud (Eureka, Gateway, OpenFeign, Resilience4j, Config Server, Micrometer/Zipkin).
  - Phân tích ưu điểm, nhược điểm, thách thức (Saga pattern, eventual consistency, network latency).
  - Tổng kết các ứng dụng thực tế (Netflix, Shopee, Tiki, Banking).

### 2. Danh mục tài liệu tham khảo chuẩn chỉnh
- [x] Đã tuyển chọn tài liệu tham khảo: [02_TONG_HOP_TAI_LIEU_SACH_YOUTUBE.md](file:///c:/Study/HK1Nam3/J2EE/Seminar/docs/02_TONG_HOP_TAI_LIEU_SACH_YOUTUBE.md)
  - 3 cuốn sách kinh điển: *Spring Microservices in Action (Manning)*, *Cloud Native Spring in Action*, *Building Microservices (O'Reilly)*.
  - 4 kênh/khóa học YouTube chất lượng: Amigoscode, Daily Code Buffer, Java Techie, FreeCodeCamp.
  - Tài liệu chính thống Spring Docs, Baeldung, Microservices.io và link GitHub repo mẫu (PiggyMetrics).

### 3. Xây dựng Dàn ý Slide & Kịch bản báo cáo
- [x] Đã hoàn thành kịch bản báo cáo: [03_DAN_Y_SLIDE_VA_KICH_BAN_25P.md](file:///c:/Study/HK1Nam3/J2EE/Seminar/docs/03_DAN_Y_SLIDE_VA_KICH_BAN_25P.md)
  - Phân bổ thời gian chuẩn: 15' lý thuyết + 7' demo + 3' Q&A.
  - Chi tiết nội dung cho 13 slide thuyết trình.
  - Soạn sẵn câu trả lời cho **3 câu hỏi phản biện bẫy** thường gặp của giảng viên (Data Consistency/Saga, Spring Cloud Gateway vs. Zuul, Trạng thái Circuit Breaker).

### 4. Xây dựng Mã nguồn Demo thực chiến (Demo Project)
- [x] Đã tạo trọn vẹn dự án Maven multi-module chuẩn Spring Boot 3 & Spring Cloud: [demo/](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/)
  - `eureka-server` (Port 8761): Service Discovery & Registry có Dashboard trực quan.
  - `api-gateway` (Port 8080): Spring Cloud Gateway điều hướng tập trung.
  - `product-service` (Port 8081): REST API sản phẩm.
  - `order-service` (Port 8082): Gọi sang Product qua Spring Cloud OpenFeign, bọc Resilience4j Circuit Breaker.
- [x] Đã viết tài liệu hướng dẫn và kịch bản 3 bước Live Demo: [demo/README.md](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/README.md).

---

## 📋 III. NHỮNG CÔNG VIỆC CẦN LÀM TIẾP THEO (TO-DO LIST)

### 📌 Giai đoạn 1: Chuẩn bị thuyết trình (Trước Tuần 8)
- [ ] **Tạo file Slide PowerPoint (`.pptx`):**
  - Sao chép nội dung từ [03_DAN_Y_SLIDE_VA_KICH_BAN_25P.md](file:///c:/Study/HK1Nam3/J2EE/Seminar/docs/03_DAN_Y_SLIDE_VA_KICH_BAN_25P.md) đưa vào PowerPoint/Canva/Google Slides.
  - Sử dụng template màu sắc hiện đại, ít chữ, nhiều sơ đồ kiến trúc (đã có sẵn trong tài liệu).
- [ ] **Mở và test chạy thử Demo Project trên máy tính cá nhân:**
  - Mở thư mục `demo` bằng IntelliJ IDEA hoặc VS Code.
  - Chạy thử 4 file Application theo đúng thứ tự (`Eureka` -> `Product` -> `Order` -> `Gateway`).
  - Kiểm tra link `http://localhost:8761` và gọi thử API bằng Postman/Browser.
  - Test tính năng ngắt `product-service` để tận mắt thấy hàm Fallback hoạt động.
- [ ] **Phân chia nhiệm vụ trong nhóm:**
  - *Thành viên 1:* Mở đầu, trình bày bối cảnh Monolith vs. Microservices + Kiến trúc tổng quan Spring Cloud.
  - *Thành viên 2:* Trình bày chi tiết các thành phần (Eureka, Gateway, Feign, Resilience4j) + Ưu/Nhược điểm.
  - *Thành viên 3:* Thao tác trực tiếp Live Demo trên màn hình chiếu + giải thích code.
  - *Cả nhóm:* Cùng ôn luyện các câu hỏi phản biện trong mục Q&A để tự tin trả lời giảng viên.
- [ ] **Tập dượt thuyết trình (Rehearsal):**
  - Canh đồng hồ bấm giờ để toàn bộ phần nói + demo kết thúc trong khoảng **20 - 23 phút** (không vượt quá 25 phút).

### 📌 Giai đoạn 2: Báo cáo & Lấy điểm cộng (Tuần 8 - Tuần 9)
- [ ] Vào đầu buổi học Tuần 8, **chủ động giơ tay xung phong báo cáo đầu tiên** để gây ấn tượng mạnh với thầy cô và ghi nhận điểm cộng.
- [ ] Trình chiếu slide kết hợp chuyển màn hình sang chạy demo trực tiếp.
- [ ] Ghi chú lại câu hỏi/nhận xét của giảng viên vào sổ tay.

### 📌 Giai đoạn 3: Nộp sản phẩm cuối kỳ (Tuần 12)
- [ ] Nén toàn bộ thư mục `Seminar` thành file nộp (hoặc đẩy lên GitHub nhóm nếu giảng viên yêu cầu link repo).
- [ ] Đính kèm file Slide PPTX và tài liệu báo cáo.
