# HƯỚNG DẪN CẤU HÌNH & PROMPT TẠO SLIDE TRÊN NOTEBOOKLM
**Chuyên đề:** Spring for Microservices  
**Mục đích:** Hướng dẫn cấu hình giao diện và cung cấp prompt chuẩn mực (không phóng đại, không giật gân) để tạo bộ slide PowerPoint thuyết trình tự động từ tài liệu nguồn.  
**Tài liệu nguồn sử dụng trên NotebookLM:**  
- `03_DAN_Y_SLIDE_VA_KICH_BAN_25P.md`  
- `04_LOI_THOAI_THUYET_TRINH_TUNG_SLIDE.md`  

---

## 🛠️ I. CẤU HÌNH GIAO DIỆN NOTEBOOKLM (CUSTOMIZE SLIDE DECK)

Khi bấm vào nút **Slide Deck** trong NotebookLM, thiết lập các mục như sau:

| Mục cấu hình | Giá trị chọn | Giải thích lý do |
| :--- | :--- | :--- |
| **Format** | **Presenter Slides** | Slide thoáng, ít chữ, làm nổi bật từ khóa và sơ đồ để diễn giả tự do thuyết trình; tránh biến slide thành "bức tường chữ". |
| **Choose language** | **Vietnamese** *(hoặc English tùy nhu cầu)* | Để toàn bộ tiêu đề và nội dung slide sinh ra bằng tiếng Việt chuẩn xác (các thuật ngữ công nghệ AI vẫn giữ nguyên tiếng Anh). |
| **Length** | **Default** | Độ dài mặc định khớp tối ưu với 14 slide đã có trong tài liệu nguồn. |
| **Sources** | **2 sources** | Chọn 2 file `03_DAN_Y...` và `04_LOI_THOAI...`. |
| **Review slide outline** | **BẬT** | Cho phép xem trước và điều chỉnh dàn ý từng slide trước khi hệ thống tạo slide chính thức. |

---

## 📝 II. PROMPT DÁN VÀO Ô MÔ TẢ

Để NotebookLM kích hoạt bộ sinh giao diện trực quan và tự động tạo sơ đồ kiến trúc, hình khối minh họa sinh động (thay vì các slide toàn chữ hoặc gạch đầu dòng nhàm chán), sử dụng các mẫu prompt tối ưu hóa hình ảnh dưới đây:

### 📌 Lựa chọn 1: Bản Tiếng Việt:
```text
Tạo slide thuyết trình trực quan, sinh động cho chuyên đề "Spring for Microservices" dựa trên tài liệu nguồn đã cung cấp.

Yêu cầu về Bố cục & Trực quan:
- Ưu tiên tối đa hình ảnh minh họa, sơ đồ kiến trúc hệ thống, lưu đồ tương tác và các khối thông tin đồ họa.
- Giảm thiểu văn bản dài: Tuyệt đối không tạo slide chỉ toàn gạch đầu dòng hay "bức tường chữ". Mỗi slide kỹ thuật cần có một sơ đồ khối hoặc hình minh họa trung tâm thể hiện luồng dữ liệu và mối quan hệ giữa các thành phần.
- Các sơ đồ cần thể hiện trực quan:
  + Slide Nội dung trình bày gồm 3 phần dạng 3 thẻ cột trực quan.
  + Đối sánh Monolith (1 khối chung DB) vs Microservices (các service độc lập với DB riêng).
  + Sơ đồ kiến trúc tổng thể Spring Cloud (Client -> Gateway -> Eureka -> Services -> DBs).
  + Quy trình Heartbeat & Tra cứu của Eureka Server.
  + Sơ đồ Cổng bảo vệ Spring Cloud Gateway (xác thực, định tuyến).
  + Máy trạng thái Resilience4j Circuit Breaker (Closed -> Open -> Half-Open) và cơ chế Fallback.
  + Topology 4-node của dự án Demo (Discovery, Gateway, Product, Order).
  + Bảng đối sánh 2 cột trực quan cho Ưu/Nhược điểm và Ma trận quyết định Nên/Không nên dùng.

Yêu cầu về Phong cách thiết kế:
- Nền slide màu trắng. Phong cách thiết kế công nghệ cao tối giản, thanh lịch theo trường phái Swiss Minimalist.
- Đường nét sắc sảo, độ tương phản rõ ràng, bố cục cân đối, nhiều khoảng trắng thoáng đãng.
- Ngôn ngữ: Điềm đạm, khách quan, chuẩn mực kỹ thuật chuyên ngành; không dùng từ ngữ phóng đại hay giật gân.
```

---

### 📌 Lựa chọn 2: Bản Tiếng Anh:
*(Khuyên dùng: Động cơ AI của NotebookLM xử lý prompt thiết kế tiếng Anh rất nhạy và sinh sơ đồ/đồ họa cực kỳ chuẩn xác)*
```text
Generate a highly visual, architecture-driven presentation slide deck for "Spring for Microservices" based strictly on the uploaded source materials.

Visual & Layout Directives:
- Highly visual presentation: Transform dense technical concepts into clean architecture diagrams, process flowcharts, component node maps, and structured infographic cards instead of plain text bullet points.
- Strictly avoid text walls or bullet-heavy slides. Every technical slide must be anchored around a central visual illustration/diagram depicting components, relationships, and data flows.
- Visual diagram specifics to render:
  + 3-column milestone cards for Agenda roadmap.
  + Side-by-side contrast between Monolith (shared DB) vs Microservices (database-per-service).
  + Full Spring Cloud system architecture topology (Client -> API Gateway -> Eureka Registry -> Business Services -> DBs).
  + 3-step Eureka Service Discovery & Heartbeat flow.
  + Spring Cloud Gateway reverse-proxy funnel with token authentication and dynamic routing.
  + Resilience4j Circuit Breaker 3-state machine (Closed, Open, Half-Open) and safe fallback path.
  + 4-node cluster demo topology (Discovery Server :8761, Gateway :8080, Product :8081, Order :8082).
  + Balanced 2-column comparison tables for Pros & Cons and Adoption Decision Matrix.

Design & Aesthetic Style:
- Clean white background. Elegant high-tech minimalist aesthetic (Swiss tech style) with crisp geometry, high contrast, balanced negative space, and modern typography.
- Tone: Objective, calm, precise engineering standards; avoid any buzzwords, exaggeration, or sensational phrasing.
```

---

## 🚀 III. CÁC BƯỚC THỰC HIỆN TIẾP THEO
1. Đảm bảo đã tải 2 file nguồn lên NotebookLM:
   - `03_DAN_Y_SLIDE_VA_KICH_BAN_25P.md` (Đã được bổ sung sẵn sơ đồ khối ASCII và đặc tả trực quan).
   - `04_LOI_THOAI_THUYET_TRINH_TUNG_SLIDE.md`.
2. Bấm vào nút **Slide Deck** -> chọn **Customize**.
3. Sao chép một trong hai đoạn prompt trên dán vào ô mô tả của NotebookLM.
4. Bấm nút **Generate now**.
5. Khi dàn ý hiển thị, duyệt nhanh 14 slide và bấm tiếp tục để hệ thống sinh slide hoàn chỉnh kèm sơ đồ và minh họa trực quan.
6. Xuất file slide ra **Google Slides** hoặc tải về định dạng **PowerPoint (.pptx)**:
   - Bạn có thể tùy chỉnh thêm icon, logo Spring chính thức, hoặc thay thế các sơ đồ vector nếu muốn bài thuyết trình càng thêm chuyên nghiệp.

