# HƯỚNG DẪN CHI TIẾT CÁC BƯỚC LIVE DEMO TRÊN LỚP

Tài liệu hướng dẫn thao tác thực chiến dành cho người thuyết trình bài seminar **Spring for Microservices**. Được thiết kế dạng sổ tay tra cứu nhanh, rõ ràng từng bước thao tác, câu lệnh kiểm thử và cách giải thích trước giảng viên.

---

## 📋 I. KHUNG CHUẨN BỊ TRƯỚC KHI BÁO CÁO (5 PHÚT TRƯỚC GIỜ G)

### 1. Chuẩn bị công cụ trên máy tính
- Mở sẵn **IDE** (IntelliJ IDEA hoặc VS Code) chứa toàn bộ thư mục [demo/](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/).
- Mở sẵn **Trình duyệt Web** với một tab trống trỏ tới: `http://localhost:8761`.
- Mở sẵn **Postman** (hoặc mở sẵn cửa sổ Terminal nếu dùng cURL) đã tạo sẵn 2 request:
  - Request 1: `GET http://localhost:8080/api/products`
  - Request 2: `POST http://localhost:8080/api/orders?productId=1&quantity=2`

### 2. Thứ tự khởi động 4 Service chuẩn xác
Khởi chạy lần lượt từng file `Application.java` theo đúng thứ tự sau:

| Thứ tự | Service | File khởi chạy | Cổng | Thời gian chờ |
| :---: | :--- | :--- | :---: | :--- |
| **1** | **Eureka Server** | [EurekaServerApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/eureka-server/src/main/java/com/seminar/eureka/EurekaServerApplication.java) | `8761` | Chờ 10 - 15 giây cho máy chủ khởi động hoàn tất |
| **2** | **Product Service** | [ProductServiceApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/product-service/src/main/java/com/seminar/product/ProductServiceApplication.java) | `8081` | Khởi chạy xong sau ~3-5 giây |
| **3** | **Order Service** | [OrderServiceApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/order-service/src/main/java/com/seminar/order/OrderServiceApplication.java) | `8082` | Khởi chạy xong sau ~5 giây |
| **4** | **API Gateway** | [ApiGatewayApplication.java](file:///c:/Study/HK1Nam3/J2EE/Seminar/demo/api-gateway/src/main/java/com/seminar/gateway/ApiGatewayApplication.java) | `8080` | Khởi chạy xong sau ~3-5 giây |

> 💡 **Mẹo:** Nên khởi động sẵn cả 4 service từ lúc ngồi dưới lớp chờ đến lượt mình lên báo cáo. Khi bắt đầu chuyển sang phần demo chỉ việc thao tác ngay mà không mất thời gian chờ ứng dụng compile/build.

---

## 🎬 II. KỊCH BẢN THAO TÁC 3 BƯỚC LIVE DEMO

### 📌 Kịch bản 1: Trình diễn Service Discovery trên Eureka Dashboard
- **Màn hình hiển thị:** Chuyển sang trình duyệt tại địa chỉ `http://localhost:8761`.
- **Thao tác:** Bấm F5 làm mới trang.
- **Vị trí chỉ tay trên màn hình:** Khu vực bảng **"Instances currently registered with Eureka"**.
- **Dấu hiệu thành công:**
  - Nhìn thấy đủ 3 dòng service màu xanh lá cây:
    - `API-GATEWAY` (Cổng 8080)
    - `ORDER-SERVICE` (Cổng 8082)
    - `PRODUCT-SERVICE` (Cổng 8081)
- **Lời nói ngắn gọn khi thao tác:**  
  *"Thưa thầy và các bạn, đây là giao diện trung tâm điều phối của Eureka Server. Cả 3 microservice khi khởi động đã tự động đăng ký địa chỉ mạng với trạng thái UP. Các service không cần phải khai báo cứng địa chỉ IP của nhau mà chỉ cần định danh qua tên logic."*

---

### 📌 Kịch bản 2: Trình diễn API Gateway Routing & Gọi liên service qua OpenFeign
- **Màn hình hiển thị:** Chuyển sang phần mềm Postman (hoặc Terminal).
- **Thao tác 2.1 - Kiểm tra định tuyến danh mục sản phẩm:**
  - Chọn phương thức `GET`.
  - URL: `http://localhost:8080/api/products`
  - Bấm **Send**.
  - **Kết quả trả về:** Mã HTTP 200 OK với danh sách 3 sản phẩm:
    ```json
    [
      { "id": 1, "name": "Laptop Dell XPS 15", "price": 35000000.0, "stock": 10 },
      { "id": 2, "name": "iPhone 15 Pro Max", "price": 29990000.0, "stock": 25 },
      { "id": 3, "name": "Bàn phím cơ Keychron Q1", "price": 4200000.0, "stock": 50 }
    ]
    ```
  - **Lời nói:** *"Client chỉ cần gửi yêu cầu duy nhất vào Gateway ở cổng 8080. Nhờ cơ chế Client-side Load Balancing, Gateway tự động chuyển tiếp yêu cầu đến Product Service ở cổng 8081 để lấy dữ liệu."*

- **Thao tác 2.2 - Kiểm tra tạo đơn hàng và tính tiền qua OpenFeign:**
  - Chọn phương thức `POST`.
  - URL: `http://localhost:8080/api/orders?productId=1&quantity=2`
  - Bấm **Send**.
  - **Kết quả trả về:** Mã HTTP 200 OK với thông tin đơn hàng đã tính toán:
    ```json
    {
      "orderId": "6f2e96d7-...",
      "productId": 1,
      "productName": "Laptop Dell XPS 15",
      "quantity": 2,
      "totalPrice": 70000000.0,
      "status": "SUCCESS",
      "note": "Đơn hàng được xác nhận qua FeignClient thành công!"
    }
    ```
  - **Lời nói:** *"Khi Client tạo đơn hàng, Gateway chuyển yêu cầu đến Order Service. Order Service không có sẵn dữ liệu sản phẩm, nó đã sử dụng OpenFeign gọi sang Product Service để lấy thông tin đơn giá 35 triệu, nhân với số lượng 2 và tính ra tổng tiền 70 triệu đồng thành công."*

---

### 📌 Kịch bản 3: Thử thách chịu lỗi với Resilience4j Circuit Breaker (Điểm nhấn ăn điểm)
- **Màn hình hiển thị:** Chuyển sang màn hình IDE.
- **Thao tác 3.1 - Giả lập sự cố:**
  - Tìm tiến trình `ProductServiceApplication`.
  - Bấm nút **Stop** màu đỏ trên thanh điều khiển của IDE để dừng service này.
  - **Lời nói:** *"Bây giờ, em xin giả định tình huống thực tế: máy chủ Product Service bị sập đột ngột do sự cố phần cứng hoặc đứt kết nối mạng."*
- **Thao tác 3.2 - Gửi lại yêu cầu đặt hàng:**
  - Quay lại Postman, giữ nguyên request tạo đơn hàng vừa rồi:
    `POST http://localhost:8080/api/orders?productId=1&quantity=2`
  - Bấm **Send**.
- **Kết quả thực tế hiển thị trên màn hình:**
  - Yêu cầu phản hồi **ngay lập tức** (không bị đơ, không bị treo chờ timeout).
  - Trả về mã **HTTP 200 OK** (hoàn toàn không bị mã lỗi 500 Internal Server Error).
  - Nội dung JSON hiển thị cơ chế Fallback:
    ```json
    {
      "orderId": "a918f0cb-...",
      "productId": 1,
      "quantity": 2,
      "status": "PENDING_CIRCUIT_BREAKER",
      "message": "[Resilience4j Fallback] Product Service hiện không phản hồi. Đơn hàng tạm lưu ở trạng thái chờ xử lý!",
      "errorDetail": "[503 Service Unavailable] during [GET] to [http://product-service/api/products/1]..."
    }
    ```
- **Lời nói chốt hạ:**  
  *"Như thầy và các bạn thấy, mặc dù Product Service đã chết hoàn toàn, hệ thống không hề bị sập dây chuyền. Nhờ Resilience4j Circuit Breaker, sự cố được cách ly an toàn, hàm Fallback lập tức kích hoạt để lưu tạm đơn hàng ở trạng thái chờ xử lý. Khi Product Service hoạt động trở lại, hệ thống sẽ tự động khôi phục bình thường."*

---

## 💻 III. BẢNG CÂU LỆNH CURL TRA CỨU NHANH (NẾU DÙNG TERMINAL)

Nếu không dùng Postman, bạn có thể mở sẵn cửa sổ PowerShell và chạy trực tiếp các lệnh sau:

### 1. Xem danh sách sản phẩm qua Gateway
```bash
curl -X GET http://localhost:8080/api/products
```

### 2. Tạo đơn hàng 2 chiếc Laptop Dell qua Gateway
```bash
curl -X POST "http://localhost:8080/api/orders?productId=1&quantity=2"
```

### 3. Xem toàn bộ lịch sử đơn hàng đã tạo
```bash
curl -X GET http://localhost:8080/api/orders
```

---

## 🛠️ IV. XỬ LÝ NHANH CÁC TÌNH HUỐNG PHÁT SINH (TROUBLESHOOTING)

| Tình huống phát sinh | Nguyên nhân | Cách xử lý tức thì |
| :--- | :--- | :--- |
| **Vào `http://localhost:8761` nhưng chưa thấy service nào hiện lên** | Eureka cần chu kỳ nhận Heartbeat ban đầu (khoảng 10-20 giây). | Chờ khoảng 10 giây rồi bấm F5 tải lại trang. |
| **Báo lỗi `Port 8080/8761/8081/8082 already in use`** | Có tiến trình chạy ngầm từ lần chạy trước chưa được tắt triệt để. | Mở PowerShell chạy lệnh tìm và diệt: <br>`netstat -ano \| findstr :8080`<br>`taskkill /F /PID <PID_tìm_thấy>` |
| **Gọi API báo lỗi 404 Not Found từ Gateway** | Gateway chưa kịp load route từ Eureka. | Chờ 5 giây rồi bấm Send lại lần nữa. |
