# KỊCH BẢN THAO TÁC VÀ LỜI THOẠI LIVE DEMO (KÈM SOI CODE VÀ CẤU HÌNH)

Tài liệu hướng dẫn thực chiến dành cho người thuyết trình bài seminar **Spring for Microservices**. Được thiết kế gồm 3 chặng rõ ràng: **Soi cấu hình & code trên IDE ➔ Chạy kiểm chứng trên Eureka & Postman ➔ Thử thách chịu lỗi và đối chiếu hàm Fallback**.

---

## 🛠️ CHUẨN BỊ TRƯỚC GIỜ BÁO CÁO (TRÊN IDE)
Mở sẵn 4 tab file sau trên IDE (IntelliJ IDEA hoặc VS Code) để khi demo chỉ cần bấm chuột chuyển tab:
1. [EurekaServerApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/eureka-server/src/main/java/com/seminar/eureka/EurekaServerApplication.java) và [application.yml](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/eureka-server/src/main/resources/application.yml) (Eureka Server)
2. [application.yml](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/api-gateway/src/main/resources/application.yml) (API Gateway)
3. [ProductClient.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/order-service/src/main/java/com/seminar/order/client/ProductClient.java)
4. [OrderServiceApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/order-service/src/main/java/com/seminar/order/OrderServiceApplication.java)

---

## 🎬 TIẾN TRÌNH DEMO CHI TIẾT

### 1. Mở đầu chuyển sang phần demo
> "Cả 4 service này em đều đã bật chạy sẵn trên máy rồi. Sau đây em xin phép bắt đầu phần demo luôn ạ."

---

### 2. Chặng 1: Giới thiệu Cấu hình & Code cốt lõi trên IDE (~1.5 phút)

* **Bước 1.1: Cấu hình Eureka Server**
  * **Thao tác:** Bấm tab [EurekaServerApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/eureka-server/src/main/java/com/seminar/eureka/EurekaServerApplication.java) và [application.yml](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/eureka-server/src/main/resources/application.yml).
  * **Lời nói:**
    > "Đầu tiên là trung tâm điều phối `Eureka Server` ở cổng `8761`. Trong code Java, em chỉ cần gắn duy nhất annotation `@EnableEurekaServer` là Spring Cloud tự động thiết lập toàn bộ máy chủ đăng ký dịch vụ. Trong file YML, em cấu hình tắt tự bảo tồn để phục vụ việc kiểm thử cục bộ."

* **Bước 1.2: Cấu hình định tuyến API Gateway**
  * **Thao tác:** Bấm tab [application.yml](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/api-gateway/src/main/resources/application.yml) của API Gateway, chỉ chuột vào các dòng `uri: lb://...`.
  * **Lời nói:**
    > "Tiếp theo là `API Gateway` ở cổng `8080`. Tại đây em định nghĩa 2 route. Mọi người chú ý tiền tố `lb://` — Gateway không trỏ cứng vào bất kỳ địa chỉ IP nào mà gọi qua tên logic `PRODUCT-SERVICE` và `ORDER-SERVICE`, kết hợp với Eureka để tự động cân bằng tải."

* **Bước 1.3: Code giao tiếp OpenFeign & Resilience4j Circuit Breaker**
  * **Thao tác 1:** Bấm tab [ProductClient.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/order-service/src/main/java/com/seminar/order/client/ProductClient.java).
  * **Lời nói:**
    > "Để gọi liên service, ở Order Service em tạo interface `ProductClient` gắn `@FeignClient(name = "product-service")`. Chúng ta hoàn toàn không cần viết mã gọi HTTP thủ công, OpenFeign sẽ tự tìm địa chỉ thực tế từ Eureka."
  * **Thao tác 2:** Bấm tab [OrderServiceApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/order-service/src/main/java/com/seminar/order/OrderServiceApplication.java), chỉ vào annotation `@CircuitBreaker` ở dòng 30 và hàm `createOrderFallback` ở dòng 51.
  * **Lời nói:**
    > "Và trên hàm tạo đơn hàng, em gắn `@CircuitBreaker` với phương thức dự phòng `createOrderFallback`. Khi `Product Service` gặp sự cố, hàm fallback ngay bên dưới sẽ được kích hoạt để bảo vệ hệ thống."

---

### 3. Chặng 2: Kiểm chứng Vận hành bình thường (~2 phút)

* **Bước 2.1: Kiểm tra Service Discovery trên Eureka Dashboard**
  * **Thao tác:** Mở trình duyệt tab `http://localhost:8761`, bấm **F5**, chỉ vào bảng **Instances currently registered with Eureka**.
  * **Lời nói:**
    > "Bây giờ chúng ta kiểm tra thực tế trên trình duyệt: Tại giao diện Eureka Dashboard, cả 3 service `API-GATEWAY`, `PRODUCT-SERVICE` và `ORDER-SERVICE` đều đang hiển thị trạng thái **UP** nhờ tự động gửi nhịp tim về đây."

* **Bước 2.2: Kiểm tra Định tuyến qua Gateway và tính tiền bằng OpenFeign**
  * **Thao tác 1:** Mở Postman hoặc Terminal, chạy `GET http://localhost:8080/api/products` rồi bấm **Send**.
  * **Lời nói:**
    > "Client chỉ gửi request vào một đầu mối duy nhất là Gateway ở cổng `8080`. Khi em gọi `/api/products`, Gateway định tuyến chính xác xuống `Product Service` ở cổng `8081` để trả về danh sách 3 sản phẩm."
  * **Thao tác 2:** Chạy `POST http://localhost:8080/api/orders?productId=1&quantity=2` rồi bấm **Send**.
  * **Lời nói:**
    > "Bây giờ em tạo đơn hàng đặt 2 chiếc laptop. Gateway chuyển yêu cầu sang `Order Service`. Nhờ `ProductClient` vừa xem trong code, Order Service đã gọi ngầm sang Product Service để lấy đơn giá 35 triệu, nhân đôi và trả về tổng tiền 70 triệu với trạng thái `SUCCESS`."

---

### 4. Chặng 3: Thử thách Chịu lỗi & Đối chiếu trực tiếp hàm Fallback (~2 phút)

* **Bước 3.1: Giả lập sự cố máy chủ**
  * **Thao tác:** Chuyển sang IDE, tìm tiến trình [ProductServiceApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/product-service/src/main/java/com/seminar/product/ProductServiceApplication.java) và bấm nút **Stop** màu đỏ.
  * **Lời nói:**
    > "Bây giờ em giả lập tình huống thực tế: `Product Service` bị sập đột ngột do sự cố phần cứng hoặc mạng."

* **Bước 3.2: Gửi lại request đặt hàng và đối chiếu lại code**
  * **Thao tác 1:** Quay lại Postman, giữ nguyên request `POST http://localhost:8080/api/orders?productId=1&quantity=2` rồi bấm **Send** lại. Chỉ vào mã `200 OK` và dòng `PENDING_CIRCUIT_BREAKER`.
  * **Lời nói:**
    > "Thầy và các bạn thấy dù `Product Service` đã tắt hoàn toàn, request vẫn phản hồi ngay lập tức với mã `200 OK` và trạng thái `PENDING_CIRCUIT_BREAKER`. Hệ thống không hề bị treo chờ timeout hay báo lỗi 500."
  * **Thao tác 2:** Quay lại IDE, bấm tab [OrderServiceApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/order-service/src/main/java/com/seminar/order/OrderServiceApplication.java) và chỉ đúng vào hàm `createOrderFallback` ở dòng 51.
  * **Lời nói chốt:**
    > "Chính hàm `createOrderFallback` này trong code đã được Resilience4j Circuit Breaker kích hoạt tức thì để tạm lưu đơn hàng ở trạng thái chờ, bảo vệ Order Service không bị nghẽn luồng. Khi Product Service khởi động lại, hệ thống sẽ tự động phục hồi bình thường."

---

### 🛡️ MẸO XỬ LÝ NẾU THẦY HỎI VỀ DÒNG CẢNH BÁO ĐỎ TRÊN EUREKA
Nếu trên giao diện Eureka xuất hiện dòng chữ đỏ:  
*`EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT...`*

👉 **Trả lời ngay:**  
> "Dạ thưa thầy, đó là cơ chế **Self-Preservation Mode** của Eureka. Khi số lượng nhịp tim gửi về bị tụt đột ngột dưới ngưỡng threshold, Eureka sẽ nghi ngờ mạng chập chờn diện rộng nên tự động đóng băng danh sách, không xóa service để tránh xóa nhầm. Trong file YML em đã thêm cấu hình `enable-self-preservation: false` để phục vụ việc kiểm thử cục bộ ạ."
