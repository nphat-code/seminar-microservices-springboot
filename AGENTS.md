# QUY TẮC LÀM VIỆC DỰ ÁN (PROJECT RULES)

## 📌 Quy tắc quản lý Git
- **Xong phần nào push lên Git ngay:** Mỗi khi hoàn thành xong một phần việc (tài liệu mới, cập nhật nội dung, sửa code demo, cấu hình,...), thực hiện kiểm tra `git status`, tạo commit rõ nghĩa và `git push` ngay lên remote repo (`origin/main`).
- Giữ lịch sử commit sạch, thông điệp commit bằng tiếng Việt hoặc tiếng Anh rõ ràng theo chuẩn: `feat:`, `docs:`, `fix:`, `refactor:`.

## 📌 Quy tắc đặt tên & Thuật ngữ (Naming & Conventions)
- **Ghi "Service" rõ ràng, tuyệt đối không viết tắt/rút gọn thành "Svc":**
  - Áp dụng trên toàn bộ tài liệu (docs, slide, kịch bản, diagram/sơ đồ text, README, báo cáo).
  - Áp dụng trong mã nguồn (source code, tên class, package, service name, interface, config, endpoint).
  - Luôn viết tường minh: `Order Service`, `Product Service`, `OrderService`, `ProductService`,...
