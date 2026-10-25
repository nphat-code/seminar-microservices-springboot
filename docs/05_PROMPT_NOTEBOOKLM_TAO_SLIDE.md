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
| **Length** | **Default** | Độ dài mặc định khớp tối ưu với 13 slide đã có trong tài liệu nguồn. |
| **Sources** | **2 sources** | Chọn 2 file `03_DAN_Y...` và `04_LOI_THOAI...`. |
| **Review slide outline** | **BẬT (ON - Màu xanh)** | Cho phép xem trước và điều chỉnh dàn ý từng slide trước khi hệ thống tạo slide chính thức. |

---

## 📝 II. PROMPT DÁN VÀO Ô MÔ TẢ (DESCRIBE THE SLIDE DECK YOU WANT TO CREATE)

### 📌 Bản Tiếng Việt (Khuyến nghị dùng để báo cáo môn học):
```text
Tạo một bộ slide báo cáo học thuật chuyên đề J2EE với chủ đề "Spring for Microservices", dựa sát vào 2 tài liệu nguồn đã cung cấp.

Yêu cầu chi tiết về nội dung và phong cách:
1. Cấu trúc bài: Bám sát dàn ý 13 slide trong tài liệu nguồn: Đặt vấn đề Monolith, Khái niệm Microservices, Hệ sinh thái Spring Boot & Spring Cloud, Service Discovery (Eureka), API Gateway, Giao tiếp & Chịu lỗi (OpenFeign & Resilience4j Circuit Breaker), Đánh giá ưu nhược điểm, Sơ đồ và Kịch bản Demo 4 service, Kết luận.
2. Giọng văn và từ ngữ: Chuẩn mực kỹ thuật, khách quan, điềm đạm; tuyệt đối không dùng từ ngữ phóng đại, giật gân hay khẩu ngữ. Giữ nguyên các thuật ngữ kỹ thuật chuẩn (Monolith, Microservices, Eureka, Gateway, FeignClient, Circuit Breaker, Fallback, Saga Pattern).
3. Thiết kế nội dung slide: Trình bày dạng bullet points ngắn gọn, súc tích; mỗi ý làm nổi bật từ khóa chính; sử dụng bảng so sánh (Monolith vs Microservices) và sơ đồ luồng dữ liệu thay vì các đoạn văn dài.
4. Đối tượng người nghe: Giảng viên và sinh viên ngành Công nghệ thông tin.
```

---

### 📌 Bản Tiếng Anh (Dành cho trường hợp muốn làm slide tiếng Anh học thuật):
```text
Create a professional academic presentation deck for a university J2EE seminar titled "Spring for Microservices", strictly based on the provided source documents.

Requirements:
1. Structure: Follow the 13-slide outline in the sources: Monolith bottlenecks, Microservices principles, Spring Boot & Spring Cloud ecosystem, Eureka Service Discovery, Spring Cloud Gateway, OpenFeign & Resilience4j Circuit Breaker, Trade-offs & Architecture evaluation, Demo project architecture & 3 testing scenarios, Conclusion.
2. Tone: Academic, professional, objective, and realistic. Avoid exaggeration, sensationalism, or informal slang. Maintain standard technical terminology.
3. Slide Layout: Concise bullet points, clear key takeaways, comparative tables, and flow diagrams rather than long paragraphs.
4. Audience: Computer Science professors and engineering students.
```

---

## 🚀 III. CÁC BƯỚC THỰC HIỆN TIẾP THEO
1. Sao chép đoạn prompt trên và dán vào ô mô tả của NotebookLM.
2. Bấm nút **Generate now**.
3. Khi dàn ý hiển thị, kiểm tra xem đã đủ các slide trọng tâm (Eureka, Gateway, Circuit Breaker, Live Demo) hay chưa, sau đó bấm tiếp tục để tạo slide.
4. Xuất file slide ra **Google Slides** hoặc tải về định dạng **PowerPoint (.pptx)** để chỉnh sửa màu sắc, template theo ý thích.
