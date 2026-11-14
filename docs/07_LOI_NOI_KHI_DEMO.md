# KỊCH BẢN LỜI NÓI KHI LIVE DEMO

Tài liệu tóm tắt ngắn gọn thao tác và lời thoại từng bước để đọc nhanh hoặc trình bày trực tiếp khi chạy Live Demo trên lớp.

---

## 1. Mở đầu khi chuyển sang màn hình máy tính

> "Thưa thầy và các bạn, hệ thống demo của nhóm em gồm 4 service: `Eureka Server` ở cổng `8761`, `API Gateway` ở cổng `8080`, `Product Service` ở cổng `8081` và `Order Service` ở cổng `8082`. Cả 4 service hiện đã được khởi chạy sẵn. Sau đây em xin phép đi vào 3 kịch bản kiểm thử thực tế."

---

## 2. Kịch bản 1: Service Discovery trên Eureka Dashboard

- **Thao tác:** Mở trình duyệt tab `http://localhost:8761`, bấm **F5**, chỉ vào bảng **Instances currently registered with Eureka**.
- **Lời nói:**

> "Đầu tiên là giao diện của `Eureka Server`. Thầy và các bạn có thể thấy cả 3 service là `API-GATEWAY`, `PRODUCT-SERVICE` và `ORDER-SERVICE` đều đang hiển thị trạng thái **UP**. Khi khởi động, các service tự động đăng ký địa chỉ lên đây và giao tiếp với nhau thông qua tên logic mà không cần cấu hình cứng địa chỉ IP."

---

## 3. Kịch bản 2: API Gateway Routing và OpenFeign

- **Thao tác 1:** Chuyển sang Postman hoặc Terminal, chạy `GET http://localhost:8080/api/products` rồi bấm **Send**.
- **Lời nói:**

> "Tiếp theo, client sẽ chỉ gửi request vào một đầu mối duy nhất là `API Gateway` ở cổng `8080`. Khi em gọi `/api/products`, Gateway tự động định tuyến yêu cầu xuống `Product Service` ở cổng `8081` để trả về danh sách 3 sản phẩm như trên màn hình."

- **Thao tác 2:** Chạy `POST http://localhost:8080/api/orders?productId=1&quantity=2` rồi bấm **Send**.
- **Lời nói:**

> "Bây giờ em thử tạo đơn hàng với mã sản phẩm `1` và số lượng `2`. Gateway chuyển request sang `Order Service`. Vì `Order Service` không lưu dữ liệu sản phẩm, nó dùng `OpenFeign` gọi sang `Product Service` để lấy đơn giá `35 triệu`, nhân với `2` và trả về tổng tiền `70 triệu` với trạng thái `SUCCESS`."

---

## 4. Kịch bản 3: Chịu lỗi với Resilience4j Circuit Breaker

- **Thao tác 1:** Chuyển sang IDE, tìm tiến trình [ProductServiceApplication.java](../demo/product-service/src/main/java/com/seminar/product/ProductServiceApplication.java) và bấm nút **Stop** màu đỏ.
- **Lời nói:**

> "Bây giờ em giả lập tình huống thực tế: `Product Service` bị sập đột ngột do sự cố máy chủ hoặc đứt mạng."

- **Thao tác 2:** Quay lại Postman hoặc Terminal, giữ nguyên request `POST http://localhost:8080/api/orders?productId=1&quantity=2` rồi bấm **Send** lại. Chỉ vào mã `200 OK` và dòng `PENDING_CIRCUIT_BREAKER` trong JSON trả về.
- **Lời nói:**

> "Thầy và các bạn thấy dù `Product Service` đã tắt hoàn toàn, hệ thống không hề bị treo hay văng lỗi `500`. Request phản hồi ngay lập tức với mã `200 OK` và trạng thái `PENDING_CIRCUIT_BREAKER`. Nhờ `Resilience4j Circuit Breaker`, sự cố đã được cách ly và hàm `Fallback` kích hoạt để tạm lưu đơn hàng ở trạng thái chờ xử lý, giúp `Order Service` vẫn hoạt động ổn định. Khi `Product Service` chạy lại, hệ thống sẽ tự động khôi phục bình thường."

---

## 5. Mẹo xử lý nhanh khi trình bày

- Lúc bấm **Send** ở Kịch bản 3, nhấn mạnh cụm **"phản hồi ngay lập tức, không bị treo"** để giảng viên thấy rõ tác dụng của `Circuit Breaker`.
- Nếu bấm **F5** trên Eureka mà chưa thấy đủ 3 service, chỉ cần nói *"Eureka cần vài giây nhận heartbeat từ các service"* rồi chờ 5 đến 10 giây bấm **F5** lại.
