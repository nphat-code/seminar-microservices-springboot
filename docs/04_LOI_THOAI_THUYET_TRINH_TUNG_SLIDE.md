# LỜI THOẠI THUYẾT TRÌNH CÁ NHÂN (CHUẨN MỰC, KHÁCH QUAN, DỄ TIẾP CẬN)
**Chuyên đề:** Spring for Microservices  
**Hình thức:** Thuyết trình cá nhân (1 người trình bày từ đầu đến cuối)  
**Phong cách:** Điềm đạm, rõ ràng, chuẩn mực kỹ thuật; tránh từ ngữ phóng đại, giật gân; tập trung vào kiến trúc và minh họa thực tế.  
**Thời lượng chuẩn:** 20 - 25 phút (15 phút lý thuyết + 7 phút live demo + 3 phút Q&A)

---

## SLIDE 1: MỞ ĐẦU (TRANG TIÊU ĐỀ)
- **Thời lượng:** ~30 - 45 giây
- **Cues:** Phong thái tự tin, đứng cạnh laptop, nhìn bao quát lớp.

> **🗣️ LỜI THOẠI:**  
> "Chào thầy và chào các bạn!  
> 
> Trong thực tế phát triển phần mềm, khi các ứng dụng thương mại điện tử hoặc dịch vụ trực tuyến phải phục vụ lượng truy cập lớn trong cùng một thời điểm, bài toán đảm bảo hệ thống mở rộng linh hoạt và duy trì tính sẵn sàng luôn là một thách thức quan trọng.  
> 
> Một trong những kiến trúc phổ biến được áp dụng để giải quyết bài toán này chính là kiến trúc **Microservices**. Và trong hệ sinh thái Java, **Spring Cloud** là bộ công cụ được ứng dụng rộng rãi nhất để hiện thực hóa mô hình này.  
> 
> Hôm nay, em xin phép trình bày đề tài: **'Spring for Microservices'**.  
> 
> Bài trình bày gồm 3 phần:  
> 1. Bối cảnh và lý do chuyển dịch từ Monolith sang Microservices.  
> 2. Các thành phần cốt lõi trong hệ sinh thái Spring Cloud.  
> 3. **Live Demo** mô hình 4 microservices thực tế để quan sát cơ chế định tuyến và chịu lỗi của hệ thống.  
> 
> Em xin phép đi vào nội dung đầu tiên."

---

## SLIDE 2: NỖI ĐAU CỦA KIẾN TRÚC MONOLITH (MONOLITHIC HELL)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chỉ vào sơ đồ Monolith trên slide.

> **🗣️ LỜI THOẠI:**  
> "Trước tiên, chúng ta cùng nhìn lại kiến trúc truyền thống: **Kiến trúc Monolith**.  
> 
> Trong mô hình này, toàn bộ các thành phần từ giao diện, xử lý nghiệp vụ đến truy xuất cơ sở dữ liệu đều được đóng gói chung trong một khối ứng dụng duy nhất (thường là một file `WAR` hoặc `JAR`).  
> 
> Với các dự án quy mô nhỏ, Monolith mang lại lợi thế phát triển nhanh và triển khai đơn giản. Tuy nhiên, khi hệ thống phát triển lớn hơn và có nhiều đội ngũ cùng tham gia, kiến trúc này bộc lộ một số hạn chế rõ rệt:  
> 
> - **Thứ nhất là quá trình Triển khai (Deployment):** Mỗi thay đổi, dù là nhỏ nhất ở một tính năng phụ, cũng đòi hỏi phải biên dịch, kiểm thử và triển khai lại toàn bộ khối ứng dụng. Điều này làm tăng thời gian phát hành và tiềm ẩn rủi ro cho toàn hệ thống.  
> - **Thứ hai là việc Mở rộng tài nguyên (Scaling):** Khi một tính năng cụ thể gặp tải cao (ví dụ như chức năng tra cứu sản phẩm), hệ thống không thể mở rộng riêng cho chức năng đó mà bắt buộc phải nhân bản toàn bộ ứng dụng, gây lãng phí tài nguyên máy chủ.  
> - **Thứ ba là Rủi ro sự cố lan rộng (Single Point of Failure):** Do toàn bộ mã nguồn chạy chung một tiến trình, một lỗi phát sinh từ một module (ví dụ rò rỉ bộ nhớ từ chức năng xuất báo cáo) có thể ảnh hưởng đến toàn bộ ứng dụng, làm gián đoạn các dịch vụ quan trọng khác.  
> 
> Những hạn chế trên là động lực thúc đẩy sự chuyển dịch sang kiến trúc Microservices."

---

## SLIDE 3: CHIA ĐỂ TRỊ VỚI MICROSERVICES
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chỉ vào sơ đồ so sánh Monolith vs. Microservices.

> **🗣️ LỜI THOẠI:**  
> "Kiến trúc **Microservices** tiếp cận theo hướng phân rã ứng dụng thành một tập hợp các dịch vụ nhỏ, độc lập và tự chủ.  
> 
> Mô hình này được xây dựng trên 3 nguyên tắc nền tảng:  
> 1. **Nguyên tắc Đơn nhiệm (Single Responsibility):** Mỗi dịch vụ chỉ chịu trách nhiệm cho một phạm vi nghiệp vụ cụ thể. Ví dụ: Dịch vụ Đơn hàng chỉ xử lý đơn hàng, Dịch vụ Sản phẩm chỉ quản lý thông tin sản phẩm.  
> 2. **Cơ sở dữ liệu độc lập (Database per Service):** Mỗi dịch vụ sở hữu cơ sở dữ liệu riêng. Các dịch vụ không truy cập trực tiếp vào cơ sở dữ liệu của nhau mà giao tiếp thông qua giao diện mạng chuẩn hóa như RESTful API.  
> 3. **Độc lập triển khai và công nghệ:** Các dịch vụ có thể được phát triển, kiểm thử, triển khai và mở rộng riêng biệt mà không làm gián đoạn các thành phần khác.  
> 
> Để hiện thực hóa kiến trúc này trên nền tảng Java, giải pháp toàn diện và phổ biến nhất hiện nay là bộ đôi Spring Boot và Spring Cloud."

---

## SLIDE 4: VAI TRÒ CỦA SPRING BOOT & SPRING CLOUD
- **Thời lượng:** ~1 phút 15 giây
- **Cues:** Phân biệt rõ vai trò của Spring Boot và Spring Cloud.

> **🗣️ LỜI THOẠI:**  
> "Trong hệ sinh thái Java, việc xây dựng Microservices được hỗ trợ bởi hai công cụ bổ trợ lẫn nhau:  
> 
> - **Spring Boot** đóng vai trò là nền tảng khởi tạo: Nhờ máy chủ nhúng sẵn (Embedded Tomcat) và cơ chế tự động cấu hình (Auto-configuration), Spring Boot giúp tạo các dịch vụ độc lập một cách nhanh chóng, loại bỏ phần lớn cấu hình thủ công phức tạp.  
> - Tuy nhiên, khi hệ thống bao gồm nhiều dịch vụ phân tán, chúng ta cần giải quyết các bài toán về: định danh vị trí dịch vụ, định tuyến lưu lượng, quản lý cấu hình tập trung và xử lý sự cố mạng.  
> 
> Đó chính là nhiệm vụ của **Spring Cloud**. Spring Cloud cung cấp các thư viện triển khai sẵn các mẫu thiết kế kinh điển cho hệ thống phân tán.  
> 
> Mời thầy và các bạn cùng quan sát mô hình kiến trúc tổng thể ở slide tiếp theo."

---

## SLIDE 5: KIẾN TRÚC TỔNG THỂ SPRING CLOUD
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chỉ theo luồng dữ liệu từ Client qua Gateway vào Services.

> **🗣️ LỜI THOẠI:**  
> "Đây là sơ đồ kiến trúc tổng thể của một hệ thống Spring Microservices:  
> 
> - Ở lớp ngoài cùng, các yêu cầu từ phía người dùng đều đi qua **Spring Cloud Gateway**. Gateway đóng vai trò là điểm tiếp nhận duy nhất, chịu trách nhiệm xác thực, kiểm soát lưu lượng và định tuyến yêu cầu vào đúng dịch vụ nội bộ.  
> - Ở lớp nghiệp vụ, các dịch vụ như **Product Service**, **Order Service** hoạt động độc lập với cơ sở dữ liệu riêng. Khi cần trao đổi dữ liệu, các dịch vụ sử dụng **OpenFeign** kết hợp với **Resilience4j** để duy trì tính ổn định.  
> - Ở lớp quản trị hệ thống, **Eureka Server** đảm nhận vai trò quản lý danh bạ dịch vụ động, giúp các thành phần tự động nhận diện địa chỉ mạng của nhau.  
> 
> Tiếp theo, em xin đi sâu vào cơ chế hoạt động của từng thành phần cốt lõi."

---

## SLIDE 6: THÀNH PHẦN 1 - SERVICE DISCOVERY (EUREKA SERVER)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Giọng giải thích mạch lạc, dùng phép so sánh danh bạ logic.

> **🗣️ LỜI THOẠI:**  
> "Thành phần đầu tiên là **Service Discovery & Registry**, được hiện thực thông qua **Eureka Server**.  
> 
> Trong môi trường điện toán đám mây hoặc container, các instance dịch vụ có thể được tạo mới hoặc thay thế liên tục, khiến địa chỉ IP và cổng mạng thay đổi thường xuyên. Do đó, việc cấu hình IP cố định trong mã nguồn là không khả thi.  
> 
> Eureka giải quyết vấn đề này theo quy trình sau:  
> 1. Khi một dịch vụ khởi động, nó tự động gửi thông tin đăng ký (gồm tên định danh logic, IP và Port) lên Eureka Server.  
> 2. Định kỳ mỗi 30 giây, dịch vụ gửi tín hiệu nhịp tim (**Heartbeat**) về Eureka để xác nhận trạng thái hoạt động. Nếu quá thời gian quy định không nhận được tín hiệu, Eureka sẽ tự động loại bỏ instance đó khỏi danh sách.  
> 3. Khi Dịch vụ Đơn hàng cần gọi Dịch vụ Sản phẩm, nó tra cứu qua Eureka bằng tên định danh `PRODUCT-SERVICE` để nhận danh sách địa chỉ khả dụng.  
> 
> Nhờ cơ chế này, hệ thống đạt được khả năng cân bằng tải động mà không cần can thiệp cấu hình thủ công."

---

## SLIDE 7: THÀNH PHẦN 2 - API GATEWAY (SPRING CLOUD GATEWAY)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chỉ vào vị trí trung gian của Gateway.

> **🗣️ LỜI THOẠI:**  
> "Thành phần thứ hai là **Spring Cloud Gateway** — cổng tiếp nhận duy nhất của hệ thống.  
> 
> Gateway giải quyết 3 yêu cầu kiến trúc quan trọng:  
> - **Bảo mật:** Giấu kín cấu trúc mạng nội bộ, chỉ mở duy nhất một cổng ra bên ngoài.  
> - **Xác thực tập trung:** Kiểm tra tính hợp lệ của token bảo mật ngay tại cửa ngõ trước khi chuyển tiếp yêu cầu vào các dịch vụ bên trong.  
> - **Định tuyến linh hoạt:** Dựa vào đường dẫn URL (ví dụ `/api/products` hoặc `/api/orders`) để chuyển tiếp chính xác đến dịch vụ đích tương ứng.  
> 
> Về mặt hiệu năng, Spring Cloud Gateway được xây dựng trên nền tảng **Spring WebFlux (Project Reactor)** theo mô hình **Non-blocking Reactive I/O**. Khác với mô hình Blocking I/O truyền thống (mỗi kết nối chiếm dụng một thread), mô hình này sử dụng ít tài nguyên hơn và có khả năng xử lý đồng thời số lượng lớn yêu cầu."

---

## SLIDE 8: THÀNH PHẦN 3 - GIAO TIẾP VÀ CHỊU LỖI (OPENFEIGN & RESILIENCE4J)
- **Thời lượng:** ~1 phút 45 giây
- **Cues:** Giải thích cơ chế chuyển trạng thái của Circuit Breaker.

> **🗣️ LỜI THOẠI:**  
> "Để giao tiếp giữa các dịch vụ, Spring Cloud cung cấp thư viện **OpenFeign**.  
> Thay vì viết mã gọi HTTP thủ công, OpenFeign cho phép định nghĩa các giao diện (Interface) với cú pháp khai báo rõ ràng, Spring sẽ tự động sinh mã thực thi ngầm định.  
> 
> Tuy nhiên, trong môi trường phân tán, các sự cố mạng hoặc việc một dịch vụ phản hồi chậm có thể làm tắc nghẽn luồng xử lý của dịch vụ gọi, dẫn đến nguy cơ **sập dây chuyền (Cascading Failure)**.  
> 
> Để kiểm soát rủi ro này, chúng ta tích hợp **Resilience4j Circuit Breaker** — hoạt động theo mô hình ngắt mạch:  
> - **Trạng thái CLOSED (Đóng):** Trạng thái bình thường, các yêu cầu được chuyển tiếp thông suốt.  
> - **Trạng thái OPEN (Ngắt mạch):** Khi tỉ lệ lỗi vượt quá ngưỡng cho phép (ví dụ trên 50%), mạch sẽ tự động ngắt. Các yêu cầu mới được chuyển hướng ngay lập tức đến hàm dự phòng (**Fallback Method**) mà không gửi qua mạng, giúp dịch vụ đích có thời gian phục hồi và tránh tắc nghẽn tài nguyên.  
> - **Trạng thái HALF-OPEN (Thăm dò):** Sau một khoảng thời gian chờ, mạch cho phép một số lượng nhỏ yêu cầu đi qua để kiểm tra. Nếu thành công, mạch quay về trạng thái CLOSED; nếu vẫn phát sinh lỗi, mạch tiếp tục duy trì trạng thái OPEN.  
> 
> Cơ chế này đảm bảo tính sẵn sàng của toàn hệ thống khi có dịch vụ thành phần gặp sự cố."

---

## SLIDE 9: ĐÁNH GIÁ KIẾN TRÚC TRONG THỰC TẾ
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Giữ góc nhìn kỹ thuật khách quan, cân bằng giữa lợi ích và thách thức.

> **🗣️ LỜI THOẠI:**  
> "Về mặt thực tiễn, việc lựa chọn kiến trúc phần mềm luôn đòi hỏi sự cân nhắc giữa lợi ích và sự đánh đổi:  
> 
> - **Ưu điểm của Microservices:** Cho phép mở rộng tài nguyên độc lập cho từng thành phần, cô lập lỗi tốt, và giúp các nhóm phát triển làm việc song song một cách tự chủ.  
> - **Thách thức cần giải quyết:**  
>   - Độ phức tạp trong vận hành tăng lên, yêu cầu hạ tầng container hóa và hệ thống giám sát, thu thập log tập trung.  
>   - Giao tiếp qua mạng làm phát sinh độ trễ nhất định so với gọi hàm nội bộ.  
>   - Đòi hỏi các giải pháp xử lý tính nhất quán dữ liệu phân tán (như mô hình Saga Pattern) thay cho các giao dịch ACID truyền thống.  
> 
> Vì vậy, Microservices phù hợp với các hệ thống có quy mô lớn, lưu lượng truy cập cao và nghiệp vụ phức tạp. Đối với các dự án vừa và nhỏ, kiến trúc Monolith vẫn là giải pháp hiệu quả về chi phí và thời gian phát triển.  
> 
> Tiếp theo, em xin phép chuyển sang phần **Live Demo** để minh họa cách hệ thống vận hành trên mã nguồn thực tế."

---

## SLIDE 10: MÔ HÌNH HỆ THỐNG DEMO
- **Thời lượng:** ~1 phút
- **Cues:** Chuyển sang tư thế thao tác máy tính, mở sẵn các cửa sổ cần thiết.

> **🗣️ LỜI THOẠI:**  
> "Hệ thống demo được xây dựng gồm 4 dịch vụ trên nền tảng Spring Boot 3 và Spring Cloud:  
> 1. **`discovery-server`** (cổng 8761): Đóng vai trò là Eureka Server.  
> 2. **`api-gateway`** (cổng 8080): Đóng vai trò là cổng tiếp nhận và định tuyến tập trung.  
> 3. **`product-service`** (cổng 8081): Dịch vụ quản lý thông tin sản phẩm.  
> 4. **`order-service`** (cổng 8082): Dịch vụ tiếp nhận đơn hàng, gọi sang Product Service qua OpenFeign và được bảo vệ bởi Resilience4j Circuit Breaker.  
> 
> Cả 4 dịch vụ hiện đã được khởi chạy trên máy. Em xin phép chuyển sang màn hình thao tác để thực hiện 3 kịch bản kiểm thử."

---

## SLIDE 11: KỊCH BẢN LIVE DEMO (THAO TÁC TRÊN MÀN HÌNH)
- **Thời lượng:** ~6 phút
- **Cues:** Thao tác rõ ràng, giải thích ngắn gọn dựa trên dữ liệu hiển thị.

> **🗣️ LỜI THOẠI TRỰC TIẾP:**  
> 
> *(Mở trình duyệt tab `http://localhost:8761`)*  
> "**Kịch bản 1: Kiểm tra Service Discovery trên Eureka Dashboard.**  
> Trên giao diện quản lý của Eureka Server, ở danh sách các instance đang hoạt động, cả 3 dịch vụ `API-GATEWAY`, `PRODUCT-SERVICE` và `ORDER-SERVICE` đều đang hiển thị trạng thái **UP**.  
> Các dịch vụ đã tự động nhận diện và liên kết với nhau thông qua tên logic mà không cần cấu hình IP tĩnh.  
> 
> ---
> 
> *(Chuyển sang Postman)*  
> **Kịch bản 2: Kiểm tra Định tuyến qua Gateway và giao tiếp OpenFeign.**  
> Yêu cầu từ Client chỉ cần gửi tới địa chỉ của **Gateway (cổng 8080)**:  
> - Đầu tiên, gửi yêu cầu tra cứu danh mục sản phẩm: `GET http://localhost:8080/api/products`.  
>   *(Bấm Send)*  
>   Gateway nhận diện tiền tố đường dẫn và chuyển tiếp yêu cầu đến `PRODUCT-SERVICE` để trả về danh sách sản phẩm.  
> - Tiếp theo, gửi yêu cầu đặt hàng: `POST http://localhost:8080/api/orders?productId=1&quantity=2`.  
>   *(Bấm Send)*  
>   Kết quả trả về đơn hàng được xác nhận thành công. Trong quá trình này, `order-service` đã tự động gọi sang `product-service` qua OpenFeign để xác thực thông tin đơn giá và tính toán tổng tiền một cách thông suốt.  
> 
> ---
> 
> *(Chuyển sang IDE)*  
> **Kịch bản 3: Kiểm nghiệm cơ chế chịu lỗi của Circuit Breaker.**  
> Trong kịch bản này, chúng ta giả lập tình huống `product-service` gặp sự cố bằng cách dừng tiến trình của dịch vụ này.  
> *(Bấm Stop ProductServiceApplication trên IDE, quay lại Postman)*  
> 
> Khi `product-service` không còn hoạt động, chúng ta gửi lại yêu cầu tạo đơn hàng:  
> *(Bấm Send)*  
> Thay vì bị treo thời gian chờ hoặc trả về lỗi 500, yêu cầu nhận được phản hồi ngay lập tức với mã HTTP 200.  
> Nội dung JSON trả về trạng thái `"PENDING_CIRCUIT_BREAKER"`, kèm thông báo từ hàm Fallback:  
> *'[Resilience4j Fallback] Product Service hiện không phản hồi. Đơn hàng tạm lưu ở trạng thái chờ xử lý!'*.  
> 
> Circuit Breaker đã nhận diện dịch vụ đích gián đoạn, tự động ngắt mạch và kích hoạt phương thức dự phòng để bảo vệ tính ổn định của dịch vụ gọi."

---

## SLIDE 12: KẾT LUẬN & ỨNG DỤNG THỰC TẾ
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Quay lại màn hình slide, đúc kết ngắn gọn.

> **🗣️ LỜI THOẠI:**  
> "Như vậy, qua nội dung tìm hiểu và thực nghiệm vừa rồi, chúng ta có thể thấy kiến trúc Microservices giải quyết hiệu quả bài toán về tính sẵn sàng và khả năng co giãn cho các hệ thống lớn.  
> 
> Các nền tảng có lưu lượng truy cập cao như Netflix, Shopee hay Tiki áp dụng mô hình này để đảm bảo dịch vụ vận hành ổn định trong các thời điểm tải tăng đột biến.  
> 
> Tuy nhiên, việc áp dụng kiến trúc cần căn cứ vào nhu cầu cụ thể của dự án và nguồn lực của đội ngũ phát triển, nhằm đạt được sự cân bằng tối ưu giữa tính năng và chi phí vận hành."

---

## SLIDE 13: TỔNG KẾT & CÂU HỎI (Q&A)
- **Thời lượng:** ~30 giây
- **Cues:** Cảm ơn và chuyển sang phần thảo luận.

> **🗣️ LỜI THOẠI:**  
> "Bài trình bày về đề tài Spring for Microservices của em xin được kết thúc tại đây. Em xin cảm ơn thầy và các bạn đã chú ý lắng nghe.  
> 
> Sau đây, em xin phép tiếp nhận các ý kiến nhận xét và giải đáp các câu hỏi từ thầy cùng cả lớp ạ."

---

## 🛡️ HƯỚNG TRẢ LỜI CÂU HỎI PHẢN BIỆN (RÕ RÀNG & CHUẨN XÁC)

### ❓ Thầy hỏi: *"Làm sao đảm bảo tính toàn vẹn dữ liệu khi mỗi Microservice có Database riêng? (Không dùng được `@Transactional` thông thường)"*
> **🗣️ Trả lời:**  
> "Dạ thưa thầy, trong Microservices với nguyên tắc Database per Service, việc sử dụng các giao dịch phân tán 2 pha (Two-Phase Commit) làm tăng độ trễ và giảm tính sẵn sàng của hệ thống.  
> Vì vậy, phương pháp phổ biến là áp dụng nguyên lý **Tính nhất quán cuối cùng (Eventual Consistency)** thông qua mẫu thiết kế **Saga Pattern**.  
> Một giao dịch tổng thể được phân rã thành chuỗi các giao dịch cục bộ tại từng dịch vụ. Nếu một bước xử lý phát sinh lỗi, hệ thống sẽ thực thi các **Giao dịch bù trừ (Compensating Transactions)** để hoàn tác trạng thái trước đó về mặt logic (ví dụ: cập nhật trạng thái hủy đơn và hoàn trả số lượng giữ chỗ trong kho). Cơ chế này thường được điều phối thông qua hàng đợi thông điệp như Kafka hoặc RabbitMQ ạ."

---

### ❓ Thầy hỏi: *"Tại sao Spring Cloud Gateway lại được sử dụng thay thế cho Netflix Zuul 1.x?"*
> **🗣️ Trả lời:**  
> "Dạ thưa thầy, Netflix Zuul 1.x hoạt động theo mô hình **Blocking I/O (Thread-per-request)**, nghĩa là mỗi kết nối sẽ chiếm dụng một luồng xử lý riêng biệt. Khi hệ thống chịu tải cao hoặc các dịch vụ phía sau phản hồi chậm, số lượng thread sẽ nhanh chóng bị cạn kiệt, gây nghẽn hệ thống.  
> Trong khi đó, **Spring Cloud Gateway** được phát triển trên nền tảng **Spring WebFlux (Project Reactor)** theo cơ chế **Non-blocking Reactive I/O**, cho phép một số lượng nhỏ luồng xử lý đồng thời hàng chục nghìn kết nối mà không bị khóa tài nguyên. Ngoài ra, Spring Cloud Gateway tích hợp sâu với Spring Boot 3 và hỗ trợ các bộ lọc Filter linh hoạt hơn ạ."

---

### ❓ Thầy hỏi: *"Circuit Breaker có những trạng thái nào và chuyển đổi ra sao?"*
> **🗣️ Trả lời:**  
> "Dạ thưa thầy, trong Resilience4j, Circuit Breaker quản lý theo 3 trạng thái chính:  
> 1. **CLOSED (Đóng mạch):** Trạng thái bình thường, các yêu cầu được chuyển tiếp thông suốt đến dịch vụ đích. Tỉ lệ lỗi được theo dõi liên tục trong một cửa sổ trượt (sliding window).  
> 2. **OPEN (Ngắt mạch):** Khi tỉ lệ lỗi vượt qua ngưỡng cấu hình (ví dụ trên 50%), mạch chuyển sang trạng thái OPEN. Mọi yêu cầu mới đều bị chặn ngay lập tức và chuyển vào hàm Fallback để giảm tải cho dịch vụ đích.  
> 3. **HALF-OPEN (Thăm dò):** Sau một khoảng thời gian chờ cấu hình trước (ví dụ 10 giây), mạch cho phép một số lượng nhỏ yêu cầu thử nghiệm đi qua. Nếu các yêu cầu này thành công, mạch tự động chuyển về trạng thái **CLOSED**; nếu tiếp tục phát sinh lỗi, mạch quay lại trạng thái **OPEN** để duy trì cách ly ạ."
