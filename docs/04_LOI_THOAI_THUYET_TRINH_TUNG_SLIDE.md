# LỜI THOẠI THUYẾT TRÌNH CÁ NHÂN (CHUẨN 14 SLIDE)
**Chuyên đề:** Spring for Microservices  
**Hình thức:** Thuyết trình cá nhân (1 người trình bày từ đầu đến cuối)  
**Phong cách:** Tự nhiên, thân thiện, súc tích, dùng ví von trực quan (không cần rào đón, không nhắc repo).  
**Thời lượng chuẩn:** 20 - 25 phút (15 phút lý thuyết + 7 phút live demo + 3 phút Q&A)

---

## SLIDE 1: EXPLORING MICROSERVICES ARCHITECTURE WITH SPRING CLOUD
- **Thời lượng:** ~30 - 45 giây
- **Cues:** Cười tươi, tự tin, đứng cạnh máy tính.

> **🗣️ LỜI THOẠI:**  
> "Chào thầy và chào tất cả các bạn!  
> 
> Chắc hẳn ở đây ai cũng từng canh giờ săn sale Shopee lúc 0h, hay chen chân đặt vé concert rồi đúng không? Có bao giờ mọi người thắc mắc: Tại sao hàng triệu người cùng ùa vào một lúc bấm mua hàng liên tục mà hệ thống của họ vẫn chạy phà phà, không hề bị sập hay chưa?  
> 
> Bí mật đằng sau đó chính là kiến trúc **Microservices**. Và trong thế giới Java, bộ công cụ hàng đầu để hiện thực hóa điều đó chính là hệ sinh thái **Spring Cloud**.  
> 
> Hôm nay, em xin phép được trình bày với thầy và cả lớp chuyên đề: **'Exploring Microservices Architecture with Spring Cloud'**.  
> 
> Bài chia sẻ hôm nay sẽ đi từ nguyên nhân vì sao phải chuyển dịch kiến trúc, cách Spring Cloud giải quyết các bài toán phân tán, và đặc biệt là phần **Live Demo trực tiếp 4 microservices** để minh họa thực tế cách hệ thống vận hành.  
> 
> Bây giờ em xin phép bắt đầu vào nội dung đầu tiên!"

---

## SLIDE 2: THE MONOLITH PROBLEM (NỖI ĐAU KIẾN TRÚC NGUYÊN KHỐI)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chỉ tay vào hình Tháp rút gỗ Jenga trên slide.

> **🗣️ LỜI THOẠI:**  
> "Trước khi nói về cái mới, mình cùng nhìn lại cách làm truyền thống trong các đồ án trước giờ: đó là **Monolith (Nguyên khối)**.  
> 
> Hiểu đơn giản là: tất cả code từ Controller, Service, kết nối Database, tới file giao diện HTML đều được gom chung vào một project duy nhất, rồi build ra đúng một file `WAR` hoặc `JAR` đem chạy trên server.  
> 
> Trên màn hình, em dùng hình ảnh **Tháp rút gỗ Jenga** để mô tả Monolith: Lúc tháp còn thấp thì rất vững, nhưng càng xây cao lên, nó sẽ bộc lộ 3 giới hạn chí mạng:  
> 
> - **Thứ nhất là tắc nghẽn triển khai (Deployment Bottlenecks):** Đôi khi chỉ sửa một dòng chữ khuyến mãi nhỏ, ta vẫn phải build lại cả file ứng dụng nặng mấy trăm Megabyte, deploy mất cả tiếng đồng hồ. Lỡ ai đó viết lỗi một chỗ là cả đợt release bị hoãn lại.  
> - **Thứ hai là hạn chế mở rộng (Scaling Limitations):** Vào ngày 11/11, khách chỉ vào lướt xem hàng chứ chưa chắc đã mua nhiều. Ta không thể nhân bản riêng module Xem hàng được, mà phải thuê thêm server nhân bản cả con web nặng nề đó. Vừa tốn chi phí, vừa lãng phí tài nguyên.  
> - **Và nguy hiểm nhất là sự cố dây chuyền (Single Point of Failure):** Giống hệt như rút nhầm một thanh gỗ Jenga, chỉ cần chức năng Xuất báo cáo Excel viết câu query ẩu làm tràn RAM, là cả server lăn ra chết, kéo theo tính năng Đăng nhập và Thanh toán cũng chết theo luôn.  
> 
> Chính vì những hạn chế này, người ta mới chuyển hướng sang kiến trúc Vi dịch vụ!"

---

## SLIDE 3: SHIFTING TO MICROSERVICES (CHUYỂN DỊCH SANG VI DỊCH VỤ)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Giọng tươi tỉnh, dùng tay làm động tác 'chia nhỏ module'.

> **🗣️ LỜI THOẠI:**  
> "Để giải quyết bài toán trên, **Microservices** ra đời dựa trên đúng tư tưởng: 'Chia để trị'.  
> 
> Thay vì nhét tất cả vào một khối to đùng, ứng dụng được phân rã thành nhiều dịch vụ nhỏ, mỗi dịch vụ là một chương trình chạy độc lập hoàn toàn.  
> 
> Kiến trúc này xoay quanh 3 nguyên tắc vàng:  
> 1. **Nguyên tắc Đơn nhiệm (Single Responsibility):** Mỗi service chỉ chuyên tâm làm một việc duy nhất. Order Service lo đơn hàng, Product Service lo sản phẩm. Không ai dẫm chân lên nghiệp vụ của ai.  
> 2. **Cơ sở dữ liệu riêng (Independent Databases):** Mỗi service sở hữu một cơ sở dữ liệu riêng biệt. Order Service không được truy cập trực tiếp vào DB của Product Service, mà bắt buộc phải gửi request qua API để trao đổi dữ liệu.  
> 3. **Tính tự chủ (Autonomy):** Mỗi service có thể tự do chọn ngôn ngữ, tự do deploy và scale riêng rẽ. Một con gặp sự cố thì các con khác vẫn hoạt động bình thường.  
> 
> Vậy với nền tảng Java, công nghệ nào giúp ta hiện thực hóa mô hình này một cách chuẩn mực nhất? Mời mọi người sang slide tiếp theo!"

---

## SLIDE 4: THE SPRING BOOT & SPRING CLOUD SOLUTION
- **Thời lượng:** ~1 phút 15 giây
- **Cues:** Giơ 2 ngón tay minh họa 2 thành phần động cơ.

> **🗣️ LỜI THOẠI:**  
> "Để làm Microservices trong Java, giải pháp tiêu chuẩn công nghiệp hiện nay chính là bộ đôi: **Spring Boot kết hợp Spring Cloud**.  
> 
> Ở đây có một hình ảnh ẩn dụ rất hay: **Động cơ 2 thành phần**:  
> - **Spring Boot** chính là khối động cơ motor nền tảng: Nhờ nhúng sẵn Tomcat và cơ chế Auto-configuration, nó giúp khởi tạo một vi dịch vụ độc lập chỉ trong vài phút mà không cần cấu hình XML phức tạp.  
> - Nhưng khi có cả chục microservice cùng chạy, ta cần một chiếc vô lăng và bảng điều khiển trung tâm: Đó chính là **Spring Cloud**. Spring Cloud cung cấp đầy đủ các công cụ giải quyết sẵn tất cả các bài toán hóc búa của hệ thống phân tán: từ tra cứu IP, định tuyến request, cho đến bảo vệ chống sập hệ thống.  
> 
> Để cả lớp dễ hình dung bộ đôi này phối hợp ra sao, mời mọi người nhìn vào bản đồ kiến trúc ở slide số 5."

---

## SLIDE 5: ARCHITECTURE BLUEPRINT (BẢN ĐỒ KIẾN TRÚC TỔNG THỂ)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chỉ vào từng khối trên màn hình theo luồng từ ngoài vào trong.

> **🗣️ LỜI THOẠI:**  
> "Trên màn hình là bản đồ tổng quan về luồng hoạt động của một hệ sinh thái Spring Microservices:  
> 
> - Ngoài cùng là người dùng (điện thoại hay trình duyệt). Người dùng không bao giờ gọi thẳng vào từng service con, mà chỉ tương tác qua một cổng duy nhất là **Spring Cloud Gateway**. Gateway kiểm tra xác thực, giới hạn lưu lượng, rồi điều hướng request vào đúng service bên trong.  
> - Đi qua Gateway là các dịch vụ nghiệp vụ như **Product, Order, Payment**, mỗi service có cơ sở dữ liệu riêng. Khi cần gọi lẫn nhau, chúng dùng **OpenFeign** kết hợp **Resilience4j** để bảo vệ chống sập dây chuyền.  
> - Ở hậu phương, hệ thống có cụm điều phối gồm: **Eureka Server** làm danh bạ IP động, **Config Server** quản lý file cấu hình tập trung, và hệ thống **Tracing** để truy vết đường đi của request.  
> 
> Bây giờ, em xin phép đi sâu vào từng thành phần cốt lõi của bức tranh này!"

---

## SLIDE 6: EUREKA SERVICE DISCOVERY (RADAR ĐỊNH VỊ DỊCH VỤ)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Dùng hình ảnh Radar quét mục tiêu trên slide.

> **🗣️ LỜI THOẠI:**  
> "Thành phần đầu tiên là **Eureka Server - Service Discovery & Registry**, hoạt động hệt như một **màn hình Radar định vị**:  
> 
> Trong môi trường Cloud hay Container, các service được bật tắt và co giãn liên tục, đồng nghĩa với việc **địa chỉ IP và cổng Port thay đổi thường xuyên**. Nếu cấu hình IP tĩnh trong code thì hệ thống sẽ gãy ngay.  
> 
> Eureka giải quyết việc này như sau:  
> 1. Mỗi khi một service khởi động, nó tự động gửi thông tin đăng ký lên Radar của Eureka: *'Tên tôi là PRODUCT-SERVICE, tôi đang chạy ở IP và Port này'*.  
> 2. Định kỳ mỗi 30 giây, service gửi tín hiệu nhịp tim (**Heartbeat**). Nếu quá thời gian quy định không nhận được tín hiệu, Radar sẽ tự động xóa instance đó khỏi danh bạ.  
> 3. Khi Order Service cần gọi Product Service, nó không cần biết IP cứng, nó chỉ việc hỏi Eureka: *'Cho tôi địa chỉ của PRODUCT-SERVICE đang online'*. Eureka trả về địa chỉ, và Order Service kết nối tới.  
> 
> Nhờ đó, việc giao tiếp giữa các service diễn ra hoàn toàn tự động mà không cần gõ chết bất kỳ địa chỉ IP nào trong code!"

---

## SLIDE 7: SPRING CLOUD GATEWAY TRAFFIC ROUTING (ĐỊNH TUYẾN THÔNG MINH)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** So sánh 2 mô hình Blocking vs Non-blocking trên slide.

> **🗣️ LỜI THOẠI:**  
> "Thành phần thứ hai là **Spring Cloud Gateway** — cổng tiếp nhận duy nhất của toàn hệ thống.  
> 
> Gateway mang lại 3 giá trị sống còn: Ẩn giấu địa chỉ mạng nội bộ để bảo mật; Kiểm tra Token JWT tập trung ngay tại cửa ngõ; và Định tuyến thông minh theo đường dẫn URL.  
> 
> Điểm đắt giá nhất ở slide này là sự so sánh giữa 2 mô hình xử lý:  
> - Trước đây, người ta hay dùng Netflix Zuul 1.x, nhưng Zuul hoạt động theo mô hình **Blocking I/O (Thread-per-request)**: mỗi request chiếm trọn 1 luồng xử lý, khi gặp tải cao hoặc mạng trễ thì thread pool cạn kiệt, server bị nghẽn tắc.  
> - Còn **Spring Cloud Gateway** được xây dựng trên **Spring WebFlux (Project Reactor)** theo cơ chế **Reactive Non-blocking I/O**. Các thread không bao giờ đứng chờ dữ liệu mà liên tục tiếp nhận kết nối mới. Nhờ đó, chỉ với một lượng tài nguyên rất nhỏ, Gateway có thể xử lý mượt mà hàng chục nghìn kết nối đồng thời!"

---

## SLIDE 8: CIRCUIT BREAKER WITH RESILIENCE4J (GIAO TIẾP & CẦU DAO CHỊU LỖI)
- **Thời lượng:** ~1 phút 45 giây
- **Cues:** Chỉ vào sơ đồ 3 trạng thái của Circuit Breaker trên slide.

> **🗣️ LỜI THOẠI:**  
> "Khi các service gọi lẫn nhau, ta dùng **Spring Cloud OpenFeign**: chỉ cần khai báo một Java Interface với annotation `@FeignClient`, Spring sẽ tự sinh mã gọi HTTP ngầm định, cú pháp gọn gàng như gọi hàm nội bộ.  
> 
> Nhưng nếu service đích bị sập hoặc treo mạng, request gửi sang sẽ bị kẹt lại chờ timeout, gây cạn kiệt tài nguyên và sập luôn service gọi. Hiện tượng này gọi là **Cascading Failure (Sập dây chuyền)**.  
> 
> Để khắc phục, ta bọc ngoài bằng **Resilience4j Circuit Breaker** — hoạt động y hệt **chiếc cầu dao điện**:  
> 1. Trạng thái bình thường (**CLOSED**): Mạch đóng, request đi qua bình thường.  
> 2. Khi tỉ lệ lỗi vượt ngưỡng (ví dụ > 50%): Cầu dao lập tức ngắt sang **OPEN**. Mọi request mới gửi đến sẽ bị chặn ngay lập tức, không gửi qua mạng nữa để tránh làm tắc nghẽn thêm. Đồng thời, hệ thống kích hoạt hàm dự phòng (**Fallback Method**) để trả về kết quả an toàn.  
> 3. Trạng thái thăm dò (**HALF-OPEN**): Sau một khoảng thời gian chờ (ví dụ 10 giây), cầu dao hé mở cho một vài request đi qua thăm dò. Nếu thành công, mạch tự động đóng lại bình thường (**CLOSED**); nếu vẫn lỗi, nó tiếp tục ngắt (**OPEN**).  
> 
> Nhờ đó, hệ thống luôn giữ được tính sẵn sàng cao ngay cả khi có service thành phần gặp sự cố!"

---

## SLIDE 9: DISTRIBUTED DATA & THE SAGA PATTERN (DỮ LIỆU PHÂN TÁN & SAGA)
- **Thời lượng:** ~1 phút 45 giây
- **Cues:** Chỉ vào lưu đồ (flowchart) mô tả chiều đi tới và chiều đi lùi trên slide.

> **🗣️ LỜI THOẠI:**  
> "Đây chính là bài toán hóc búa nhất của Microservices: **Làm sao đảm bảo toàn vẹn dữ liệu khi mỗi service có một Database riêng?**  
> 
> Vì mỗi service ôm một DB độc lập, ta **không thể dùng annotation `@Transactional` thông thường** để rollback giữa các database được nữa.  
> 
> Giải pháp là chấp nhận nguyên lý **Tính nhất quán cuối cùng (Eventual Consistency)** và áp dụng mẫu thiết kế **Saga Pattern**:  
> - Thay vì một giao dịch lớn duy nhất, Saga chia thành một chuỗi các **giao dịch cục bộ (Local Transactions)**:  
>   - Bước 1: Order Service tạo đơn ở trạng thái chờ.  
>   - Bước 2: Inventory Service khóa giữ chỗ sản phẩm trong kho.  
>   - Bước 3: Payment Service tiến hành trừ tiền.  
> - **Điểm cốt lõi là Giao dịch bù trừ (Compensating Transactions):** Nếu ở Bước 3 khách thanh toán thất bại (thẻ hết hạn hoặc hết tiền), hệ thống không rollback bằng database, mà **tự động chạy giật lùi bằng code logic**:  
>   - Gọi ngược lại Inventory để nhả hàng đã giữ chỗ ra lại kho.  
>   - Gọi ngược lại Order để cập nhật trạng thái đơn thành ĐÃ HỦY.  
> 
> Nhờ các giao dịch bù trừ này, dữ liệu của toàn bộ hệ thống sau cùng luôn trở về trạng thái nhất quán và chính xác hoàn toàn!"

---

## SLIDE 10: LIVE DEMO PROJECT SCHEMATIC (SƠ ĐỒ DỰ ÁN DEMO)
- **Thời lượng:** ~1 phút
- **Cues:** Chỉ vào sơ đồ 4 nút (4-node diagram) chuẩn bị bước vào demo.

> **🗣️ LỜI THOẠI:**  
> "Để kiểm chứng toàn bộ lý thuyết trên, hệ thống demo gồm 4 service chạy trên nền Spring Boot 3:  
> 1. **`discovery-server`** chạy port **8761**: Đóng vai trò là Eureka Server.  
> 2. **`api-gateway`** chạy port **8080**: Cổng điều hướng tập trung.  
> 3. **`product-service`** chạy port **8081**: Cung cấp API thông tin sản phẩm.  
> 4. **`order-service`** chạy port **8082**: Nhận tạo đơn hàng, gọi sang Product Service qua OpenFeign và được bọc bởi Resilience4j Circuit Breaker.  
> 
> Hiện tại cả 4 service đều đã được khởi động sẵn trên máy. Bây giờ em xin phép chuyển sang màn hình thao tác trực tiếp."

---

## SLIDE 11: LIVE DEMO SCRIPT (THAO TÁC TRỰC TIẾP TRÊN MÀN HÌNH)
- **Thời lượng:** ~6 phút
- **Cues:** Giữ slide 11 trên máy chiếu làm visual anchor, chuyển sang trình duyệt/Postman/IDE thao tác rõ ràng.

> **🗣️ LỜI THOẠI TRỰC TIẾP:**  
> 
> *(Mở trình duyệt tab `http://localhost:8761`)*  
> "**Kịch bản 1: Quan sát Eureka Dashboard.**  
> Đây là giao diện của Eureka Server. Ở bảng danh sách các instance, mọi người có thể thấy cả 3 service: `API-GATEWAY`, `PRODUCT-SERVICE`, và `ORDER-SERVICE` đều đang ở trạng thái **UP**.  
> Các service tự động tìm thấy nhau qua tên logic mà không cần khai báo IP hay Port cố định trong file cấu hình.  
> 
> ---
> 
> *(Chuyển sang Postman)*  
> **Kịch bản 2: Gọi API qua Gateway và giao tiếp OpenFeign.**  
> Lúc này, Client chỉ cần gọi qua một địa chỉ duy nhất là cổng **Gateway port 8080**:  
> - Đầu tiên, gửi request lấy danh sách sản phẩm: `GET http://localhost:8080/api/products`.  
>   *(Bấm Send)*  
>   Gateway lập tức nhận diện đường dẫn và điều hướng sang `PRODUCT-SERVICE` để trả về dữ liệu.  
> - Tiếp theo, gửi request đặt hàng: `POST http://localhost:8080/api/orders?productId=1&quantity=2`.  
>   *(Bấm Send)*  
>   Kết quả trả về đơn hàng thành công với tổng tiền là 70 triệu. Để tính được tổng tiền này, `order-service` đã tự động gọi sang `product-service` qua OpenFeign để lấy giá của 'Laptop Dell XPS 15' rồi tính toán, toàn bộ diễn ra trong suốt với người dùng.  
> 
> ---
> 
> *(Chuyển sang IDE)*  
> **Kịch bản 3: Thử nghiệm cơ chế chịu lỗi với Circuit Breaker.**  
> Bây giờ là tình huống giả định `product-service` gặp sự cố hoặc bị tắt.  
> Em sẽ dừng tiến trình `ProductServiceApplication`.  
> *(Bấm nút Stop trên IDE, sau đó quay lại Postman)*  
> 
> Khi không có service sản phẩm, ta gửi lại lệnh đặt hàng:  
> *(Bấm Send)*  
> Kết quả trả về ngay lập tức với mã HTTP 200 chứ không bị treo hay báo lỗi 500.  
> Trong JSON trả về hiển thị status: `"PENDING_CIRCUIT_BREAKER"`, kèm thông báo:  
> *'[Resilience4j Fallback] Product Service hiện không phản hồi. Đơn hàng tạm lưu ở trạng thái chờ xử lý!'*.  
> 
> Nhờ có Circuit Breaker, sự cố ở `product-service` không làm ảnh hưởng đến tính sẵn sàng của `order-service`, đơn hàng vẫn được tiếp nhận an toàn."

---

## SLIDE 12: ARCHITECTURAL EVALUATION (ĐÁNH GIÁ KIẾN TRÚC)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Quay lại màn hình slide, phân tích ma trận Trade-off.

> **🗣️ LỜI THOẠI:**  
> "Sau phần demo thực tế, ta cùng nhìn lại ma trận đánh giá hai mặt của Microservices:  
> 
> - **Về ưu điểm:** Khả năng mở rộng độc lập từng phần rất linh hoạt, lỗi được cô lập triệt để ở từng service, và các nhóm lập trình có thể release tính năng liên tục mà không phụ thuộc nhau.  
> - **Về thách thức (Trade-off):**  
>   - Vận hành và giám sát phức tạp hơn rất nhiều, đòi hỏi hạ tầng Container hóa và hệ thống log tập trung.  
>   - Giao tiếp qua mạng làm tăng độ trễ (Network latency) so với gọi hàm nội bộ.  
>   - Và như đã phân tích ở Slide 9, việc quản lý giao dịch dữ liệu phân tán đòi hỏi áp dụng Saga Pattern thay vì ACID đơn giản.  
> 
> Vậy làm thế nào để quyết định khi nào nên áp dụng kiến trúc này? Mời mọi người cùng nhìn vào ma trận chẩn đoán ở slide 13!"

---

## SLIDE 13: DECISION MATRIX: WHEN TO ADOPT MICROSERVICES? (MA TRẬN QUYẾT ĐỊNH 2X2)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chỉ vào 4 góc phần tư của biểu đồ 2x2 (Team Size vs Project Scale).

> **🗣️ LỜI THOẠI:**  
> "Trên màn hình là **Ma trận quyết định 2x2**, dựa trên hai trục: **Quy mô dự án** và **Độ lớn đội ngũ phát triển**:  
> 
> - **Ở góc dưới bên trái (Team nhỏ, Dự án mới / MVP):** **Monolith với Spring Boot luôn là sự lựa chọn số 1**. Tốc độ phát triển nhanh, chi phí vận hành rẻ, cấu trúc đơn giản để đưa sản phẩm ra thị trường sớm nhất.  
> - **Ở góc trên bên phải (Hệ thống lớn, Nhiều đội ngũ phát triển độc lập):** Lúc này bài toán mở rộng và tính sẵn sàng trở thành sống còn. **Microservices với Spring Cloud là lựa chọn bắt buộc** để các nền tảng như Netflix, Shopee hay Tiki có thể phục vụ hàng triệu người dùng mà không bị gián đoạn.  
> 
> Thông điệp cốt lõi là: *Đừng chọn Microservices chỉ vì trào lưu, hãy chọn kiến trúc phù hợp với bài toán của doanh nghiệp!*"

---

## SLIDE 14: SUMMARY & LEARNING RESOURCES (TỔNG KẾT & Q&A)
- **Thời lượng:** ~45 giây
- **Cues:** Cười tươi, cảm ơn và mở lời mời nhận xét.

> **🗣️ LỜI THOẠI:**  
> "Tổng kết lại, bài chia sẻ hôm nay đã đi qua 4 trụ cột chính: Lý do chuyển dịch kiến trúc, hệ sinh thái giải pháp của Spring Cloud, mô hình giải quyết dữ liệu phân tán với Saga, và kịch bản thực nghiệm chịu lỗi với Circuit Breaker.  
> 
> Bài trình bày về Spring for Microservices của em xin được kết thúc tại đây. Em xin cảm ơn thầy và các bạn đã chú ý lắng nghe!  
> 
> Sau đây, em rất mong nhận được những lời nhận xét, góp ý cũng như sẵn sàng trả lời các câu hỏi phản biện từ thầy và cả lớp ạ."

---

## 🛡️ SCRIPT TRẢ LỜI CÂU HỎI PHẢN BIỆN CỦA THẦY CÔ (TỰ NHIÊN & RÕ RÀNG)

### ❓ Thầy hỏi: *"Mỗi service một Database riêng thì làm sao giữ toàn vẹn dữ liệu? Lỡ thanh toán lỗi thì rollback kiểu gì khi không dùng được `@Transactional`?"*
> **🗣️ Trả lời:**  
> "Dạ thưa thầy, trong Microservices vì mỗi service có database độc lập nên không thể dùng `@Transactional` kiểu ACID thông thường. Giải pháp thực tế là chấp nhận nguyên lý **Eventual Consistency (Tính nhất quán cuối cùng)** và áp dụng **Saga Pattern** ạ.  
> Giao dịch lớn được chia thành các giao dịch nhỏ tại từng service. Nếu một bước ở giữa thất bại (ví dụ thanh toán không thành công), hệ thống sẽ kích hoạt các **Giao dịch bù trừ (Compensating Transactions)** để hoàn tác lại trạng thái trước đó, ví dụ như hủy đơn hàng và trả lại số lượng tồn kho. Việc điều phối này thường được thực hiện qua Message Broker như Kafka hoặc RabbitMQ ạ."

---

### ❓ Thầy hỏi: *"Tại sao lại dùng Spring Cloud Gateway mà không dùng Netflix Zuul?"*
> **🗣️ Trả lời:**  
> "Dạ thưa thầy, Netflix Zuul đời 1 hoạt động theo mô hình **Blocking I/O (Thread-per-request)**, mỗi request chiếm trọn một thread nên khi tải cao hoặc có service bị chậm thì thread pool dễ bị nghẽn.  
> Còn **Spring Cloud Gateway** được xây dựng trên nền tảng **Spring WebFlux (Project Reactor)** theo cơ chế **Non-blocking Reactive I/O**, giúp một số lượng nhỏ thread có thể xử lý đồng thời lượng kết nối rất lớn mà không bị khóa tài nguyên. Ngoài ra nó tích hợp tốt hơn với Spring Boot 3 và hỗ trợ bộ lọc Filter linh hoạt hơn nên hiện nay đã thay thế hoàn toàn Zuul 1 ạ."

---

### ❓ Thầy hỏi: *"Circuit Breaker có những trạng thái nào và hoạt động ra sao?"*
> **🗣️ Trả lời:**  
> "Dạ thưa thầy, trong Resilience4j, Circuit Breaker gồm 3 trạng thái:  
> 1. **CLOSED (Đóng mạch):** Trạng thái bình thường, request được chuyển tiếp đến service đích.  
> 2. **OPEN (Mở mạch):** Khi tỉ lệ lỗi vượt quá ngưỡng cài đặt (ví dụ trên 50%), mạch ngắt; mọi request đến sau bị chặn ngay lập tức và chuyển vào hàm Fallback để tránh làm nghẽn mạng và giúp service đích có thời gian phục hồi.  
> 3. **HALF-OPEN (Nửa mở):** Sau một khoảng thời gian chờ (ví dụ 10 giây), mạch cho phép một số lượng nhỏ request thăm dò đi qua. Nếu các request này thành công, mạch tự động đóng lại (**CLOSED**); nếu vẫn thất bại, nó quay lại trạng thái **OPEN** để tiếp tục cách ly ạ."
