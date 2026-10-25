# BỘ TÀI LIỆU VÀ MÃ NGUỒN SEMINAR J2EE: SPRING FOR MICROSERVICES

> 🎓 **Đề tài số 5:** Spring for Microservices  
> 📅 **Mục tiêu:** Báo cáo xung phong lấy điểm cộng Tuần 8 - Tuần 9, sản phẩm hoàn thiện nộp Tuần 12.

---

## 🗂️ CẤU TRÚC TOÀN BỘ BỘ TÀI LIỆU TRONG THƯ MỤC

```
c:/Study/HK1Nam3/J2EE/Seminar/
├── KE_HOACH_TIEN_DO.md                         # Bảng theo dõi tiến độ, việc ĐÃ LÀM và CẦN LÀM tiếp theo
├── docs/
│   ├── 01_LY_THUYET_MICROSERVICES_SPRING.md   # Bài viết đầy đủ: Khái niệm, kiến trúc, Ưu/Nhược điểm, Patterns, Ứng dụng thực tế
│   ├── 02_TONG_HOP_TAI_LIEU_SACH_YOUTUBE.md    # Danh mục sách kinh điển (Manning, O'Reilly), kênh YouTube, Baeldung, GitHub mẫu
│   ├── 03_DAN_Y_SLIDE_VA_KICH_BAN_25P.md       # Dàn ý chi tiết 13 slide, phân bổ thời gian <25 phút và câu hỏi phản biện
│   ├── 04_LOI_THOAI_THUYET_TRINH_TUNG_SLIDE.md # LỜI THOẠI CHI TIẾT TỪNG SLIDE (Speaker Notes) kèm kịch bản nói khi Live Demo
│   └── 05_PROMPT_NOTEBOOKLM_TAO_SLIDE.md       # Hướng dẫn cấu hình & Prompt chuẩn mực để sinh Slide trên NotebookLM
│
├── demo/                                       # Project mã nguồn thực chiến minh hoạ
│   ├── pom.xml                                 # Maven parent cấu hình Spring Boot 3 & Spring Cloud
│   ├── eureka-server/                          # Service Discovery & Registry (Port 8761)
│   ├── api-gateway/                            # Spring Cloud Gateway (Port 8080)
│   ├── product-service/                        # REST API quản lý sản phẩm (Port 8081)
│   ├── order-service/                          # Gọi liên dịch vụ qua OpenFeign & bọc Circuit Breaker Resilience4j (Port 8082)
│   └── README.md                               # Hướng dẫn khởi chạy & Kịch bản Live Demo 3 bước trước lớp
│
└── README.md                                   # Tổng quan dự án (file này)
```

---

## 🎯 ĐÁP ỨNG TRỌN VẸN CÁC YÊU CẦU CỦA GIẢNG VIÊN

| Yêu cầu của Giảng viên | Vị trí tài liệu / Mã nguồn tương ứng |
| :--- | :--- |
| **1. Tìm bài viết về công nghệ (Ưu điểm, khuyết điểm, ứng dụng,...)** | [01_LY_THUYET_MICROSERVICES_SPRING.md](file:///c:/Study/HK1Nam3/J2EE/Seminar/docs/01_LY_THUYET_MICROSERVICES_SPRING.md) |
| **2. Tổng hợp sách, video YouTube, liên kết tài liệu liên quan** | [02_TONG_HOP_TAI_LIEU_SACH_YOUTUBE.md](file:///c:/Study/HK1Nam3/J2EE/Seminar/docs/02_TONG_HOP_TAI_LIEU_SACH_YOUTUBE.md) |
| **3. Dàn ý Slide PPTX dưới 25 phút & Bộ câu hỏi phản biện** | [03_DAN_Y_SLIDE_VA_KICH_BAN_25P.md](file:///c:/Study/HK1Nam3/J2EE/Seminar/docs/03_DAN_Y_SLIDE_VA_KICH_BAN_25P.md) |
| **4. Example Project thể hiện tính năng hay nhất để demo** | Thư mục [demo/](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/) (Minh họa Eureka Discovery, Gateway Routing, FeignClient và Circuit Breaker Resilience4j) |
