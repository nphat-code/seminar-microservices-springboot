# LỜI THOẠI THUYẾT TRÌNH CÁ NHÂN (CHUẨN MỰC, KHÁCH QUAN, DỄ TIẾP CẬN)
**Chuyên đề:** Spring for Microservices  
**Hình thức:** Thuyết trình cá nhân (1 người trình bày từ đầu đến cuối)  
**Phong cách:** Điềm đạm, rõ ràng, chuẩn mực kỹ thuật; tránh từ ngữ phóng đại, giật gân; tập trung vào kiến trúc và minh họa thực tế.  
**Thời lượng chuẩn:** 20 - 25 phút (15 phút lý thuyết + 7 phút live demo + 3 phút Q&A)

---

## SLIDE 1: TRANG TIÊU ĐỀ
- **Thời lượng:** ~20 - 30 giây
- **Cues:** Phong thái tự tin, đứng cạnh laptop, nhìn bao quát lớp.

> **🗣️ LỜI THOẠI:**  
> "Chào thầy và chào các bạn!  
> 
> Hôm nay, em xin phép được trình bày bài báo cáo seminar với đề tài: **'Spring for Microservices'** – Xây dựng hệ thống phân tán trong Java Enterprise với Spring Boot và Spring Cloud.  
> 
> Trong thực tế phát triển phần mềm hiện đại, khi các ứng dụng thương mại điện tử hay dịch vụ trực tuyến phải phục vụ lượng truy cập lớn trong cùng một thời điểm, bài toán đảm bảo hệ thống có thể mở rộng linh hoạt và duy trì tính sẵn sàng cao luôn là một thách thức vô cùng quan trọng. Và **Microservices kết hợp với Spring Cloud** là một trong những hướng tiếp cận phổ biến mà nhiều doanh nghiệp đang dùng để xử lý vấn đề này."

---

## SLIDE 2: NỘI DUNG TRÌNH BÀY
- **Thời lượng:** ~20 - 30 giây
- **Cues:** Chuyển sang slide Nội dung trình bày, hướng tay về 3 cột nội dung trên màn hình.

> **🗣️ LỜI THOẠI:**  
> "Bài trình bày hôm nay của em sẽ gồm 3 phần chính:  
> 
> - Phần đầu tiên, em sẽ nói về bối cảnh và lý do vì sao chúng ta chuyển dịch từ kiến trúc Monolith sang Microservices.  
> - Tiếp theo ở phần thứ hai, chúng ta sẽ cùng tìm hiểu các thành phần kiến trúc cốt lõi trong hệ sinh thái Spring Cloud như Eureka Server, Spring Cloud Gateway, OpenFeign và Resilience4j Circuit Breaker.  
> - Và phần thứ ba, em sẽ **demo** trực tiếp một hệ thống gồm 4 service để mọi người cùng xem cách định tuyến và khả năng chịu lỗi khi có sự cố."

---

## SLIDE 3: NỖI ĐAU CỦA KIẾN TRÚC MONOLITH
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chỉ vào sơ đồ Monolith trên slide.

> **🗣️ LỜI THOẠI:**  
> "Mở đầu phần thứ nhất, chúng ta cùng nhìn lại kiến trúc **Monolith** truyền thống và những hạn chế của nó.  
> 
> Trong mô hình này, toàn bộ các thành phần từ giao diện, xử lý nghiệp vụ đến truy xuất cơ sở dữ liệu đều được đóng gói chung trong một khối ứng dụng duy nhất (thường là một file `WAR` hoặc `JAR`).  
> 
> Với các dự án quy mô nhỏ, Monolith mang lại lợi thế phát triển nhanh và triển khai đơn giản. Tuy nhiên, khi hệ thống phát triển lớn hơn và có nhiều đội ngũ cùng tham gia, mô hình này bắt đầu gặp phải mấy vấn đề lớn:  
> 
> - Đầu tiên là **vấn đề triển khai** — mỗi thay đổi, dù là nhỏ nhất ở một tính năng phụ, cũng đòi hỏi phải biên dịch, kiểm thử và triển khai lại toàn bộ khối ứng dụng. Điều này làm tăng thời gian phát hành và tiềm ẩn rủi ro cho toàn hệ thống.  
> - Thứ hai là **việc mở rộng tài nguyên** — khi chỉ một tính năng cụ thể gặp tải cao như chức năng tra cứu sản phẩm, hệ thống không thể mở rộng riêng cho chức năng đó mà bắt buộc phải nhân bản toàn bộ ứng dụng, gây lãng phí tài nguyên máy chủ.  
> - Và thứ ba là **rủi ro sự cố lan rộng** — do toàn bộ mã nguồn chạy chung một tiến trình, một lỗi phát sinh từ một module như rò rỉ bộ nhớ ở chức năng xuất báo cáo có thể làm sập cả ứng dụng và gián đoạn các service quan trọng khác.  
> 
> Những hạn chế trên là động lực thúc đẩy sự chuyển dịch sang kiến trúc Microservices."

---

## SLIDE 4: GIỚI THIỆU KIẾN TRÚC MICROSERVICES
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chỉ vào sơ đồ so sánh Monolith vs. Microservices và 3 thẻ nguyên lý trên slide.

> **🗣️ LỜI THOẠI:**  
> "Kiến trúc **Microservices** tiếp cận bài toán theo tư duy 'Chia để trị' — phân rã khối ứng dụng lớn thành một tập hợp các service nhỏ, độc lập và tự chủ.  
> 
> Như thầy và các bạn quan sát trên slide, mô hình này dựa trên 3 nguyên lý chính:  
> 1. Nguyên lý đầu tiên là **🎯 Single Responsibility** — mỗi service chỉ tập trung làm tốt một phạm vi nghiệp vụ duy nhất. Ví dụ như trên sơ đồ, Order Service chỉ chuyên xử lý đơn hàng, còn Product Service chỉ chuyên quản lý dữ liệu sản phẩm.  
> 2. Nguyên lý thứ hai là **🗄️ Database per Service** — mỗi service sở hữu hoàn toàn cơ sở dữ liệu riêng biệt, tuyệt đối không dùng chung database và mọi giao tiếp dữ liệu bắt buộc phải đi qua giao diện mạng chuẩn hóa như RESTful API.  
> 3. Và nguyên lý thứ ba là **🚀 CI/CD độc lập** — nhờ tách biệt mã nguồn và tiến trình chạy, từng service có thể được nâng cấp, kiểm thử và phát hành lên môi trường thực tế chỉ trong vài phút mà không làm gián đoạn hay phụ thuộc vào các service còn lại.  
> 
> Và để hiện thực hóa kiến trúc này trên nền tảng Java Enterprise, bộ đôi **Spring Boot và Spring Cloud** đang là lựa chọn hàng đầu hiện nay."

---

## SLIDE 5: TẠI SAO CHỌN SPRING CHO MICROSERVICES?
- **Thời lượng:** ~1 phút 15 giây
- **Cues:** Hướng tay lần lượt về Cột 1 (Spring Boot) và Cột 2 (Spring Cloud) trên màn hình.

> **🗣️ LỜI THOẠI:**  
> "Vậy hai công cụ này phối hợp với nhau như thế nào?  
> 
> - Đầu tiên là **Spring Boot**, đóng vai trò làm nền tảng khởi tạo từng microservice đơn lẻ. Nhờ máy chủ nhúng Embedded Tomcat và cơ chế Auto-configuration, Spring Boot giúp đóng gói toàn bộ ứng dụng thành một file JAR độc lập, loại bỏ hầu hết cấu hình rườm rà. Ngoài ra, Spring Boot Actuator cũng cung cấp sẵn các endpoint Health Check cực kỳ tiện lợi.  
> - Tuy nhiên, khi ráp nhiều service lại với nhau, chúng ta phải giải quyết các bài toán phức tạp của hệ thống phân tán như định vị service, điều phối luồng truy cập, quản lý cấu hình và kiểm soát sự cố mạng.  
> - Để xử lý những bài toán đó, chúng ta dùng đến **Spring Cloud** — bộ công cụ này cung cấp sẵn **Service Discovery** để tự động tìm kiếm vị trí service, **Intelligent Routing** để định tuyến lưu lượng, **Resilience4j Circuit Breaker** để ngắt mạch chống sập dây chuyền, và **Centralized Config** để quản lý cấu hình tập trung."

---

## SLIDE 6: KIẾN TRÚC TỔNG THỂ SPRING CLOUD
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chỉ theo luồng dữ liệu từ Client qua Gateway vào Services.

> **🗣️ LỜI THOẠI:**  
> "Và để dễ hình dung hơn, đây là sơ đồ kiến trúc tổng thể của một hệ thống Spring Microservices:  
> 
> - Ở lớp ngoài cùng, các yêu cầu từ phía người dùng đều đi qua **Spring Cloud Gateway**. Gateway đóng vai trò là điểm tiếp nhận duy nhất, chịu trách nhiệm xác thực, kiểm soát lưu lượng và định tuyến yêu cầu vào đúng service nội bộ.  
> - Ở lớp nghiệp vụ, các service như **Product Service**, **Order Service** hoạt động độc lập với cơ sở dữ liệu riêng. Khi cần trao đổi dữ liệu, các service sử dụng **OpenFeign** kết hợp với **Resilience4j Circuit Breaker** để duy trì tính ổn định.  
> - Ở lớp quản trị hệ thống, **Eureka Server** đảm nhận vai trò quản lý danh bạ service động, giúp các thành phần tự động nhận diện địa chỉ mạng của nhau.  
> 
> Tiếp theo, em xin đi sâu vào cơ chế hoạt động của từng thành phần cốt lõi."

---

## SLIDE 7: SERVICE DISCOVERY VỚI EUREKA SERVER
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Giọng giải thích mạch lạc, dùng phép so sánh danh bạ logic.

> **🗣️ LỜI THOẠI:**  
> "Thành phần đầu tiên là **Service Discovery & Registry**, được hiện thực thông qua **Eureka Server**.  
> 
> Trong môi trường Cloud hoặc container, các instance service có thể được tạo mới hoặc thay thế liên tục, khiến địa chỉ IP và cổng mạng thay đổi thường xuyên. Do đó, việc cấu hình IP cố định trong mã nguồn là không khả thi.  
> 
> Eureka giải quyết vấn đề này theo quy trình sau:  
> 1. Khi một service khởi động, nó tự động gửi thông tin đăng ký (gồm tên định danh logic, IP và Port) lên Eureka Server.  
> 2. Định kỳ mỗi 30 giây, service gửi tín hiệu nhịp tim (**Heartbeat**) về Eureka để xác nhận trạng thái hoạt động. Nếu quá thời gian quy định không nhận được tín hiệu, Eureka sẽ tự động loại bỏ instance đó khỏi danh sách. Ngoài ra, khi mạng chập chờn diện rộng khiến nhiều service mất tín hiệu cùng lúc, Eureka kích hoạt **Self-Preservation Mode** để tạm giữ lại danh sách thay vì xóa nhầm các service vẫn đang chạy.  
> 3. Khi Order Service cần gọi Product Service, nó tra cứu qua Eureka bằng tên định danh `PRODUCT-SERVICE` để nhận danh sách địa chỉ khả dụng.  
> 
> Nhờ cơ chế này, hệ thống đạt được khả năng cân bằng tải động mà không cần can thiệp cấu hình thủ công."

---

## SLIDE 8: API GATEWAY VỚI SPRING CLOUD GATEWAY
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chỉ vào vị trí trung gian của Gateway.

> **🗣️ LỜI THOẠI:**  
> "Thành phần thứ hai là **Spring Cloud Gateway** — cổng tiếp nhận duy nhất của hệ thống.  
> 
> Gateway giải quyết các yêu cầu kiến trúc trọng yếu sau:  
> - Thứ nhất là **bảo mật mạng nội bộ** — Gateway giấu kín cấu trúc mạng bên trong và chỉ mở duy nhất một cổng ra bên ngoài.  
> - Thứ hai là **Authentication tập trung** — toàn bộ việc kiểm tra tính hợp lệ của JWT Token được xử lý ngay tại Gateway trước khi chuyển tiếp yêu cầu vào các service bên trong.  
> - Thứ ba là **Rate Limiter** — giúp giới hạn tần suất gọi API nhằm chống spam và bảo vệ các service phía sau trước lưu lượng tăng đột biến.  
> - Và cuối cùng là **Dynamic Routing** — dựa vào đường dẫn URL như `/api/products` hoặc `/api/orders` để chuyển tiếp chính xác đến service đích tương ứng.  
> 
> Về mặt hiệu năng, Spring Cloud Gateway được xây dựng trên nền tảng **Spring WebFlux** theo mô hình **Non-blocking Reactive I/O**. Khác với mô hình Blocking I/O truyền thống (mỗi kết nối chiếm dụng một thread), mô hình này sử dụng ít tài nguyên hơn và có khả năng xử lý đồng thời số lượng lớn yêu cầu."

---

## SLIDE 9: GIAO TIẾP VÀ CHỊU LỖI VỚI OPENFEIGN VÀ RESILIENCE4J
- **Thời lượng:** ~1 phút 45 giây
- **Cues:** Giải thích cơ chế chuyển trạng thái của Circuit Breaker.

> **🗣️ LỜI THOẠI:**  
> "Để giao tiếp giữa các service, Spring Cloud cung cấp thư viện **OpenFeign**.  
> Thay vì viết mã gọi HTTP thủ công, OpenFeign cho phép định nghĩa các interface với cú pháp khai báo rõ ràng, Spring sẽ tự động sinh mã thực thi ngầm định.  
> 
> Tuy nhiên, trong môi trường Microservices, các sự cố mạng hoặc việc một service phản hồi chậm có thể làm tắc nghẽn luồng xử lý của service gọi, dẫn đến nguy cơ **sập dây chuyền**.  
> 
> Để kiểm soát rủi ro này, chúng ta tích hợp **Resilience4j Circuit Breaker**, hoạt động theo 3 trạng thái ngắt mạch:  
> - Ở trạng thái **CLOSED**, mọi thứ diễn ra bình thường và các yêu cầu được chuyển tiếp thông suốt.  
> - Khi tỉ lệ lỗi vượt quá ngưỡng cho phép, ví dụ trên 50%, mạch tự động chuyển sang trạng thái **OPEN** để ngắt kết nối. Lúc này, mọi yêu cầu mới được chuyển hướng ngay lập tức đến hàm dự phòng **Fallback** mà không gửi qua mạng, giúp service đích có thời gian phục hồi và tránh tắc nghẽn tài nguyên.  
> - Sau một khoảng thời gian chờ, mạch chuyển sang trạng thái **HALF-OPEN** và cho phép một số lượng nhỏ yêu cầu thử nghiệm đi qua. Nếu thành công, mạch quay về trạng thái CLOSED; còn nếu vẫn phát sinh lỗi, mạch tiếp tục duy trì trạng thái OPEN.  
> 
> Cơ chế này đảm bảo tính sẵn sàng của toàn hệ thống khi có service thành phần gặp sự cố."

---

## SLIDE 10: ĐÁNH GIÁ KIẾN TRÚC - ƯU ĐIỂM & THÁCH THỨC THỰC TẾ
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Giữ góc nhìn kỹ thuật khách quan; vừa nói vừa chỉ tay đối sánh giữa 2 cột Ưu điểm (xanh) và Thách thức (đỏ) trên bảng slide.

> **🗣️ LỜI THOẠI:**  
> "Về mặt thực tiễn, trong kỹ thuật phần mềm không có kiến trúc nào là hoàn hảo tuyệt đối, mọi quyết định đều là **sự đánh đổi kiến trúc**. Như bảng đối sánh trên slide:  
> 
> **Về mặt ưu điểm:**  
> - Đầu tiên là **khả năng mở rộng linh hoạt** — thay vì phải nhân bản cả hệ thống lớn, mình chỉ cần scale riêng những service đang chịu tải cao, chẳng hạn như chỉ nhân bản thêm Product Service khi lượng người xem tăng mạnh, nhờ vậy tiết kiệm tối đa chi phí phần cứng.  
> - Thứ hai là **triển khai độc lập** — các team có thể cập nhật và phát hành tính năng mới cho từng service riêng lẻ mà không lo ảnh hưởng đến các service còn lại.  
> - Điểm thứ ba rất quan trọng là **khả năng cô lập lỗi** — nếu một service phụ chẳng may gặp sự cố, lỗi đó sẽ được khoanh vùng lại chứ không làm sập toàn bộ hệ thống.  
> - Và cuối cùng là **sự tự do về công nghệ** — tùy vào đặc thù bài toán, mỗi service có thể tự chọn phiên bản công nghệ hoặc hệ cơ sở dữ liệu tối ưu nhất cho riêng mình.  
> 
> **Tuy nhiên, để đổi lấy những lợi thế trên, chúng ta phải chấp nhận 4 thách thức thực tế:**  
> - Thứ nhất là **độ trễ mạng** — việc giao tiếp giữa các service qua HTTP/REST chắc chắn sẽ có độ trễ lớn hơn so với gọi hàm nội bộ trong RAM.  
> - Thứ hai là **tính nhất quán dữ liệu** — việc chia tách database làm mất đi giao dịch `@Transactional` ACID truyền thống, buộc chúng ta phải áp dụng các mô hình phân tán phức tạp hơn như Saga Pattern.  
> - Thứ ba là **vận hành phức tạp** — hệ thống đòi hỏi hạ tầng container hóa cùng các giải pháp giám sát, theo dõi log phân tán như Actuator hay Distributed Tracing.  
> - Và thứ tư là **kiểm thử khó khăn** — rất khó để dựng lên toàn bộ môi trường test tích hợp end-to-end trên một máy cá nhân.  
> 
> Tiếp theo, em xin phép chuyển sang phần **demo** hệ thống."

---

## SLIDE 11: MÔ HÌNH HỆ THỐNG DEMO
- **Thời lượng:** ~1 phút
- **Cues:** Chiếu sơ đồ 4 node trên slide để cả lớp nắm cấu trúc trước, sau đó chuyển sang thao tác máy tính.

> **🗣️ LỜI THOẠI:**  
> "Hệ thống demo được xây dựng gồm 4 service trên nền tảng Spring Boot 3 và Spring Cloud:  
> 1. **`eureka-server`** (cổng 8761): Đóng vai trò là Service Discovery và Service Registry trung tâm.  
> 2. **`api-gateway`** (cổng 8080): Đóng vai trò là cổng tiếp nhận và định tuyến tập trung.  
> 3. **`product-service`** (cổng 8081): Service quản lý thông tin sản phẩm.  
> 4. **`order-service`** (cổng 8082): Service tiếp nhận đơn hàng, gọi sang Product Service qua OpenFeign và được bảo vệ bởi Resilience4j Circuit Breaker.  
> 
> Cả 4 service này em đều đã bật chạy sẵn trên máy rồi. Sau đây em xin phép bắt đầu phần demo luôn ạ."

---

## PHẦN DEMO THỰC TẾ TRÊN MÁY TÍNH (KHÔNG DÙNG SLIDE)
- **Thời lượng:** ~5 - 6 phút
- **Cues:** Chuyển màn hình chiếu sang máy tính (mở sẵn Trình duyệt, Postman và 4 tab file code trên IDE). Vừa thao tác vừa nói chậm rãi, chỉ vào đúng khu vực trên màn hình.

### 📌 Chặng 1: Giới thiệu Cấu hình & Code cốt lõi trên IDE (~1.5 phút)
- **Cấu hình Eureka Server:** Mở tab `[EurekaServerApplication.java]` và `[application.yml]`.  
  > "Đầu tiên là `Eureka Server` ở cổng `8761`. Trong code Java, em chỉ cần gắn duy nhất annotation `@EnableEurekaServer` là Spring Cloud sẽ tự động thiết lập Service Registry để quản lý danh bạ dịch vụ."
- **Định tuyến Gateway:** Mở tab `[application.yml]` của API Gateway, chỉ vào các dòng `uri: lb://...`.  
  > "Tiếp theo là `API Gateway` ở cổng `8080`. Tại đây em định nghĩa 2 route. Mọi người chú ý tiền tố `lb://` — Gateway không trỏ cứng vào bất kỳ địa chỉ IP nào mà gọi qua tên logic `product-service` và `order-service`, kết hợp với Eureka để tự động cân bằng tải."
- **OpenFeign & Circuit Breaker:** Mở `[ProductClient.java]` và `[OrderServiceApplication.java]` (dòng 30 và 51).  
  > "Để Order Service gọi sang Product Service lấy giá, em tạo interface `ProductClient` gắn `@FeignClient(name = "product-service")` mà không cần viết mã gọi HTTP thủ công. Đồng thời trên hàm tạo đơn hàng, em gắn `@CircuitBreaker` với phương thức dự phòng `createOrderFallback`. Khi `Product Service` gặp sự cố, hàm fallback ngay bên dưới sẽ được kích hoạt để kiểm soát lỗi."

---

### 📌 Chặng 2: Kiểm chứng Vận hành bình thường (~2 phút)
- **Eureka Dashboard:** Mở trình duyệt tab `http://localhost:8761`, bấm **F5**.  
  > "Bây giờ chúng ta kiểm tra thực tế trên trình duyệt: Tại giao diện Eureka Dashboard, cả 3 service `API-GATEWAY`, `PRODUCT-SERVICE` và `ORDER-SERVICE` đều đang hiển thị trạng thái **UP** nhờ tự động gửi nhịp tim về đây."
- **Gọi qua Gateway:** Mở Postman, chạy `GET http://localhost:8080/api/products` và `POST http://localhost:8080/api/orders?productId=1&quantity=2`.  
  > "Client chỉ gửi request vào một đầu mối duy nhất là Gateway ở cổng `8080`. Khi em gọi lấy sản phẩm, Gateway định tuyến chính xác xuống `Product Service`. Khi em tạo đơn hàng, Order Service đã sử dụng `ProductClient` vừa xem trong code để gọi ngầm sang Product Service lấy đơn giá 35 triệu và trả về tổng tiền 70 triệu thành công với trạng thái `SUCCESS`."

---

### 📌 Chặng 3: Thử thách Chịu lỗi & Đối chiếu trực tiếp hàm Fallback (~2 phút)
- **Giả lập sự cố:** Quay lại IDE, bấm nút **Stop** màu đỏ dừng `[ProductServiceApplication.java]`.  
  > "Bây giờ em giả lập tình huống thực tế: `Product Service` bị sập đột ngột do sự cố phần cứng hoặc mạng."
- **Kiểm chứng Fallback & Soi lại code:** Quay lại Postman, bấm **Send** lại request đặt hàng. Chỉ vào mã `200 OK` và dòng `PENDING_CIRCUIT_BREAKER`. Sau đó quay lại IDE chỉ đúng vào hàm `createOrderFallback` ở dòng 51 của `[OrderServiceApplication.java]`.  
  > "Thầy và các bạn thấy dù `Product Service` đã tắt hoàn toàn, request vẫn phản hồi ngay lập tức với mã `200 OK` và trạng thái `PENDING_CIRCUIT_BREAKER`. Chính hàm `createOrderFallback` này trong code đã được Resilience4j Circuit Breaker kích hoạt tức thì để tạm lưu đơn hàng ở trạng thái chờ, bảo vệ Order Service không bị nghẽn luồng."

---

### 💡 Mẹo xử lý nếu thầy hỏi về dòng chữ đỏ trên Eureka
Nếu trên giao diện Eureka xuất hiện dòng cảnh báo màu đỏ, bạn trả lời ngay:  
> "Dạ thưa thầy, đó là cơ chế **Self-Preservation Mode** của Eureka. Khi số lượng nhịp tim gửi về bị tụt đột ngột dưới ngưỡng threshold, Eureka sẽ nghi ngờ mạng chập chờn diện rộng nên tự động đóng băng danh sách, không xóa service để tránh xóa nhầm. Trong file YML em đã thêm cấu hình `enable-self-preservation: false` để phục vụ việc kiểm thử cục bộ ạ."

---

## SLIDE 12: ỨNG DỤNG THỰC TẾ & KHI NÀO NÊN / KHÔNG NÊN DÙNG (SLIDE KẾT THÚC)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Quay lại màn hình slide (Slide 12), chỉ lần lượt vào phần màu xanh và phần màu đỏ của ma trận quyết định. Đọc câu danh ngôn đúc kết và chào kết thúc bài.

> **🗣️ LỜI THOẠI:**  
> "Từ phần lý thuyết và thực nghiệm vừa rồi, câu hỏi quan trọng nhất là khi nào nên áp dụng Microservices trong thực tế? Chúng ta có thể thấy rõ qua hai trường hợp sau:  
> 
> - Đầu tiên là **khi nào nên dùng** — kiến trúc này phát huy hiệu quả rõ rệt với các hệ thống có lưu lượng truy cập lớn như Netflix, Amazon, Shopee, Tiki hay các hệ thống Core Banking; dự án có từ 3 nhóm phát triển trở lên làm việc song song và đòi hỏi độ sẵn sàng cao 99,99%.  
> - Ngược lại là **khi nào không nên dùng** — với các dự án khởi nghiệp ở giai đoạn MVP khi nghiệp vụ còn thay đổi liên tục, hoặc đội ngũ nhỏ dưới 5 lập trình viên, chi phí vận hành và gỡ lỗi phân tán sẽ vượt quá lợi ích mang lại.  
> 
> Như lời khuyên nổi tiếng của Martin Fowler: *'Đừng bắt đầu bằng Microservices nếu ứng dụng của bạn chưa thực sự cần nó. Hãy bắt đầu từ một Monolith được thiết kế tốt.'*  
> 
> Bài trình bày về đề tài Spring for Microservices của em xin được kết thúc tại đây. Em xin cảm ơn thầy và các bạn đã chú ý lắng nghe."

---

## 🛡️ HƯỚNG TRẢ LỜI CÂU HỎI PHẢN BIỆN

### ❓ Thầy hỏi: *"Làm sao đảm bảo tính toàn vẹn dữ liệu khi mỗi Microservice có Database riêng thay vì dùng `@Transactional` thông thường?"*
> **🗣️ Trả lời:**  
> "Dạ thưa thầy, trong Microservices với nguyên tắc Database per Service, việc sử dụng các giao dịch phân tán Two-Phase Commit làm tăng độ trễ và giảm tính sẵn sàng của hệ thống.  
> Vì vậy, phương pháp phổ biến là áp dụng nguyên lý **Tính nhất quán cuối cùng** thông qua mẫu thiết kế **Saga Pattern**.  
> Một giao dịch tổng thể được phân rã thành chuỗi các giao dịch cục bộ tại từng service. Nếu một bước xử lý phát sinh lỗi, hệ thống sẽ thực thi các **Giao dịch bù trừ** để hoàn tác trạng thái trước đó về mặt logic (ví dụ: cập nhật trạng thái hủy đơn và hoàn trả số lượng giữ chỗ trong kho). Cơ chế này thường được điều phối thông qua hàng đợi thông điệp như Kafka hoặc RabbitMQ ạ."

---

### ❓ Thầy hỏi: *"Tại sao Spring Cloud Gateway lại được sử dụng thay thế cho Netflix Zuul 1.x?"*
> **🗣️ Trả lời:**  
> "Dạ thưa thầy, Netflix Zuul 1.x hoạt động theo mô hình **Blocking I/O**, nghĩa là mỗi kết nối sẽ chiếm dụng một luồng xử lý riêng biệt. Khi hệ thống chịu tải cao hoặc các service phía sau phản hồi chậm, số lượng thread sẽ nhanh chóng bị cạn kiệt, gây nghẽn hệ thống.  
> Trong khi đó, **Spring Cloud Gateway** được phát triển trên nền tảng **Spring WebFlux** theo cơ chế **Non-blocking Reactive I/O**, cho phép một số lượng nhỏ luồng xử lý đồng thời hàng chục nghìn kết nối mà không bị khóa tài nguyên. Ngoài ra, Spring Cloud Gateway tích hợp sâu với Spring Boot 3 và hỗ trợ các bộ lọc Filter linh hoạt hơn ạ."

---

### ❓ Thầy hỏi: *"Circuit Breaker có những trạng thái nào và chuyển đổi ra sao?"*
> **🗣️ Trả lời:**  
> "Dạ thưa thầy, trong Resilience4j, Circuit Breaker quản lý theo 3 trạng thái chính:  
> 1. Ở trạng thái **CLOSED**, mọi thứ hoạt động bình thường, các yêu cầu được chuyển tiếp thông suốt đến service đích và tỉ lệ lỗi được theo dõi liên tục trong một cửa sổ trượt.  
> 2. Khi tỉ lệ lỗi vượt qua ngưỡng cấu hình, ví dụ trên 50%, mạch chuyển sang trạng thái **OPEN** — lúc này mọi yêu cầu mới đều bị chặn ngay lập tức và chuyển vào hàm Fallback để giảm tải cho service đích.  
> 3. Và sau một khoảng thời gian chờ cấu hình trước, ví dụ 10 giây, mạch chuyển sang trạng thái **HALF-OPEN** để cho phép một số lượng nhỏ yêu cầu thử nghiệm đi qua. Nếu các yêu cầu này thành công, mạch tự động quay về trạng thái **CLOSED**; ngược lại nếu tiếp tục phát sinh lỗi, mạch quay lại trạng thái **OPEN** để duy trì cách ly ạ."
