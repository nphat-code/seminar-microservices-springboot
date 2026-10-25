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

### 📌 Bản Tiếng Việt:
```text
Tạo slide thuyết trình cho chuyên đề "Spring for Microservices" dựa trên tài liệu nguồn đã cung cấp.

Yêu cầu:
- Thiết kế nền slide màu trắng. Tự do sáng tạo bố cục và phong cách giao diện trực quan.
- Ngôn ngữ: Điềm đạm, khách quan, chuẩn mực kỹ thuật; không dùng từ ngữ phóng đại hay giật gân.
```

---

### 📌 Bản Tiếng Anh:
```text
Create a presentation slide deck for "Spring for Microservices" based on the provided source documents.

Requirements:
- Design: White background. Feel free to creatively design the layout and visual presentation.
- Language: Objective, calm, and technically accurate; avoid any exaggerated or sensational phrasing.
```

---

## 🚀 III. CÁC BƯỚC THỰC HIỆN TIẾP THEO
1. Sao chép đoạn prompt trên và dán vào ô mô tả của NotebookLM.
2. Bấm nút **Generate now**.
3. Khi dàn ý hiển thị, kiểm tra xem đã đủ các slide trọng tâm (Eureka, Gateway, Circuit Breaker, Live Demo) hay chưa, sau đó bấm tiếp tục để tạo slide.
4. Xuất file slide ra **Google Slides** hoặc tải về định dạng **PowerPoint (.pptx)** để chỉnh sửa màu sắc, template theo ý thích.
