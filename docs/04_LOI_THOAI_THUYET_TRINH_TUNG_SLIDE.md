# LỜI THOẠI THUYẾT TRÌNH CÁ NHÂN (TỰ NHIÊN, KHÁCH QUAN, GẦN GŨI)
**Chuyên đề:** Spring for Microservices  
**Hình thức:** Thuyết trình cá nhân (1 người trình bày từ đầu đến cuối)  
**Phong cách:** Tự nhiên, thân thiện, tập trung truyền tải kiến thức & demo trực quan (không cần rào đón hay nhấn mạnh việc tự làm).  
**Thời lượng chuẩn:** 20 - 25 phút (15 phút lý thuyết + 7 phút live demo + 3 phút Q&A)

---

## SLIDE 1: MỞ ĐẦU (TRANG TIÊU ĐỀ)
- **Thời lượng:** ~30 - 45 giây
- **Cues:** Cười tươi, tự tin, đứng cạnh laptop.

> **🗣️ LỜI THOẠI:**  
> "Chào thầy và chào tất cả các bạn!  
> 
> Chắc hẳn ở đây ai cũng từng canh giờ săn sale Shopee lúc 0h, hay chen chân đặt vé concert rồi đúng không? Có bao giờ mọi người thắc mắc: Tại sao hàng triệu người cùng ùa vào một lúc bấm mua hàng liên tục mà hệ thống của họ vẫn chạy phà phà, không hề bị sập hay chưa?  
> 
> Bí mật đằng sau đó chính là kiến trúc **Microservices**. Và trong thế giới Java, bộ công cụ phổ biến hàng đầu để hiện thực hóa điều đó chính là hệ sinh thái **Spring Cloud**.  
> 
> Hôm nay, em xin phép được trình bày với thầy và cả lớp đề tài: **'Spring for Microservices'**.  
> 
> Bài chia sẻ hôm nay sẽ gồm 3 phần: Đầu tiên là lý do vì sao thế giới lại chuyển dịch sang Microservices; tiếp theo là cách các thành phần trong Spring Cloud giải quyết các bài toán phân tán; và cuối cùng là phần **Live Demo trực tiếp 4 microservices** để minh họa thực tế cách hệ thống vận hành.  
> 
> Bây giờ em xin phép bắt đầu vào nội dung đầu tiên!"

---

## SLIDE 2: NỖI ĐAU CỦA KIẾN TRÚC NGUYÊN KHỐI (MONOLITHIC HELL)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Giọng tự nhiên, chỉ tay vào sơ đồ Monolith trên slide.

> **🗣️ LỜI THOẠI:**  
> "Trước khi nói về cái mới, mình cùng nhìn lại cách làm quen thuộc trong các đồ án trước giờ: đó là **Monolith (Nguyên khối)**.  
> 
> Hiểu đơn giản là: tất cả code từ Controller, Service, kết nối Database, tới file giao diện HTML đều được gom chung vào một project duy nhất, rồi build ra đúng một file `WAR` hoặc `JAR` đem chạy trên server.  
> 
> Lúc làm đồ án ít người thì rất tiện, dễ viết dễ chạy. Nhưng khi dự án lớn dần lên, vài chục lập trình viên cùng đụng vào, nó sẽ gặp phải những giới hạn rất lớn:  
> 
> - **Khổ nhất là lúc Deploy:** Đôi khi chỉ cần sửa mỗi một dòng chữ khuyến mãi trên web, nhưng ta vẫn phải ngồi chờ build lại cả một cục ứng dụng nặng mấy trăm Megabyte, deploy mất cả tiếng đồng hồ. Lỡ ai đó viết lỗi một chỗ là cả đợt release bị hoãn lại.  
> - **Khổ thứ hai là mở rộng (Scale):** Mọi người tưởng tượng ngày 11/11, khách chỉ vào lướt xem hàng thôi chứ chưa chắc đã mua nhiều. Lượng xem tăng vọt thì máy chủ xem hàng đuối. Thế nhưng vì Monolith đóng chung một cục, ta không thể nhân bản riêng tính năng Xem hàng được, mà phải thuê thêm server nhân bản cả con web nặng nề đó. Vừa tốn chi phí, vừa lãng phí tài nguyên.  
> - **Và nguy hiểm nhất là sự cố dây chuyền:** Chỉ cần một chức năng phụ như Xuất báo cáo Excel viết câu query không tối ưu làm tràn bộ nhớ RAM, là cả server lăn ra chết. Kéo theo các tính năng cốt lõi như Đăng nhập, Giỏ hàng, Thanh toán cũng chết theo luôn.  
> 
> Chính vì những hạn chế này, người ta mới chuyển hướng sang giải pháp chia nhỏ hệ thống!"

---

## SLIDE 3: CHIA ĐỂ TRỊ VỚI MICROSERVICES
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Giọng tươi tỉnh, dùng tay làm động tác 'cắt nhỏ'.

> **🗣️ LỜI THOẠI:**  
> "Và thế là **Microservices** ra đời, dựa trên đúng tư tưởng: 'Chia để trị'.  
> 
> Thay vì nhét tất cả vào một cục to đùng, ứng dụng được chia thành nhiều dịch vụ nhỏ, mỗi dịch vụ là một chương trình chạy độc lập hoàn toàn.  
> 
> Kiến trúc này xoay quanh 3 nguyên tắc rất rõ ràng:  
> 1. **Nguyên tắc Đơn nhiệm (Single Responsibility):** Order Service chỉ chuyên tâm lo đơn hàng. Product Service chỉ lo sản phẩm. Không ai dẫm chân lên nghiệp vụ của ai.  
> 2. **Cơ sở dữ liệu riêng (Database per Service):** Mỗi service có một cơ sở dữ liệu riêng. Order Service không được truy cập trực tiếp vào database của Product Service. Muốn lấy thông tin gì thì phải gửi request qua API để hỏi.  
> 3. **Độc lập triển khai và công nghệ:** Service này viết bằng Java, service kia viết bằng Go hay Node.js cũng được; một con gặp sự cố thì các con khác vẫn hoạt động bình thường.  
> 
> Vậy với nền tảng Java, công nghệ nào giúp ta hiện thực hóa mô hình này một cách chuẩn mực nhất? Đó chính là Spring Boot và Spring Cloud."

---

## SLIDE 4: TẠI SAO LẠI LÀ BỘ ĐÔI SPRING BOOT & SPRING CLOUD?
- **Thời lượng:** ~1 phút 15 giây
- **Cues:** Giơ 2 ngón tay minh họa 2 thành phần.

> **🗣️ LỜI THOẠI:**  
> "Để làm Microservices trong Java, giải pháp tiêu chuẩn công nghiệp hiện nay chính là: **Spring Boot kết hợp Spring Cloud**.  
> 
> - **Spring Boot** đóng vai trò nền móng: Nhờ nhúng sẵn Tomcat và cơ chế Auto-configuration, nó giúp việc khởi tạo một microservice độc lập trở nên rất nhanh chóng, chỉ mất vài phút.  
> - Nhưng khi hệ thống có cả chục microservice cùng chạy, các bài toán phân tán bắt đầu xuất hiện: Các service làm sao tìm thấy IP của nhau khi IP thay đổi liên tục? Khách hàng bên ngoài gọi vào đâu? Lỡ một service bị treo thì các service khác xử lý thế nào?  
> 
> Đó là lúc **Spring Cloud** phát huy vai trò. Spring Cloud là một bộ công cụ toàn diện giải quyết sẵn tất cả các bài toán kinh điển của hệ thống phân tán, giúp lập trình viên không phải tự xây dựng lại từ đầu.  
> 
> Để cả lớp dễ hình dung các thành phần này phối hợp với nhau ra sao, mời mọi người nhìn vào sơ đồ kiến trúc ở slide tiếp theo."

---

## SLIDE 5: BỨC TRANH TOÀN CẢNH SPRING CLOUD
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chỉ vào từng khối trên màn hình theo luồng từ ngoài vào trong.

> **🗣️ LỜI THOẠI:**  
> "Đây là mô hình tổng quan về luồng hoạt động của một hệ thống Spring Microservices:  
> 
> - Ngoài cùng là người dùng (điện thoại hay trình duyệt). Người dùng không cần biết bên trong có bao nhiêu service, mà chỉ tương tác qua một cổng duy nhất là **Spring Cloud Gateway**. Gateway đứng ở cửa: kiểm tra xác thực, giới hạn lưu lượng, rồi điều hướng request vào đúng service bên trong.  
> - Đi qua Gateway là các dịch vụ nghiệp vụ như **Product, Order, Payment**. Mỗi service có cơ sở dữ liệu riêng. Khi cần gọi lẫn nhau, chúng dùng **OpenFeign** kết hợp **Resilience4j** để bảo vệ chống sập dây chuyền.  
> - Ở phía sau, hệ thống có các thành phần điều phối gồm: **Eureka Server** làm danh bạ lưu trữ IP động, **Config Server** quản lý file cấu hình tập trung, và hệ thống **Tracing** để truy vết đường đi của request.  
> 
> Bây giờ, em xin phép đi sâu vào 3 thành phần cốt lõi và quan trọng nhất của kiến trúc này."

---

## SLIDE 6: THÀNH PHẦN 1 - EUREKA (SERVICE DISCOVERY)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Giọng thoải mái, dùng ví von danh bạ điện thoại.

> **🗣️ LỜI THOẠI:**  
> "Thành phần đầu tiên là **Eureka Server - Service Discovery & Registry**.  
> 
> Trong môi trường Cloud hay Container, các service được bật tắt và co giãn liên tục, đồng nghĩa với việc **địa chỉ IP và cổng Port thay đổi thường xuyên**. Nếu cấu hình IP tĩnh trong code thì hệ thống sẽ rất dễ bị gián đoạn.  
> 
> Eureka giải quyết việc này giống như một cuốn **danh bạ điện thoại thông minh**:  
> 1. Mỗi khi một service khởi động, nó tự động gửi thông tin đăng ký lên Eureka: *'Tên tôi là PRODUCT-SERVICE, tôi đang chạy ở IP và Port này'*.  
> 2. Định kỳ mỗi 30 giây, service gửi tín hiệu nhịp tim (**Heartbeat**). Nếu quá thời gian quy định không nhận được tín hiệu, Eureka sẽ tự động xóa instance đó khỏi danh bạ.  
> 3. Khi Order Service cần gọi Product Service, nó chỉ việc hỏi Eureka: *'Cho tôi địa chỉ của PRODUCT-SERVICE đang khả dụng'*. Eureka trả về địa chỉ, và Order Service gọi trực tiếp tới.  
> 
> Nhờ đó, việc giao tiếp giữa các service diễn ra hoàn toàn tự động mà không cần gõ chết bất kỳ địa chỉ IP nào trong code."

---

## SLIDE 7: THÀNH PHẦN 2 - SPRING CLOUD GATEWAY (CỔNG ĐIỀU HƯỚNG TẬP TRUNG)
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chỉ vào khối Gateway nằm giữa Client và Services.

> **🗣️ LỜI THOẠI:**  
> "Thành phần thứ hai là **Spring Cloud Gateway**.  
> 
> Nếu hệ thống có nhiều service con, việc để client gọi trực tiếp vào từng port riêng lẻ sẽ gây khó khăn cho việc quản lý và tiềm ẩn rủi ro bảo mật.  
> 
> Gateway sinh ra để làm vai trò: **Điểm tiếp nhận duy nhất (Single Entry Point)**.  
> - Khách hàng chỉ cần gọi vào đúng một địa chỉ Gateway duy nhất.  
> - Tại đây, Gateway sẽ kiểm tra tính hợp lệ của request (như kiểm tra Token JWT, giới hạn số lượng request để chống spam).  
> - Sau đó, Gateway tự động điều hướng: request xem sản phẩm thì chuyển về Product Service, request đặt hàng thì chuyển về Order Service.  
> 
> Khác với Netflix Zuul thế hệ cũ vốn chạy theo kiểu Blocking (mỗi request chiếm 1 thread), Spring Cloud Gateway được xây dựng trên nền tảng **WebFlux Reactive Non-blocking**, giúp tiêu tốn rất ít tài nguyên mà vẫn xử lý được lưu lượng đồng thời rất cao."

---

## SLIDE 8: THÀNH PHẦN 3 - OPENFEIGN & RESILIENCE4J (GIAO TIẾP VÀ CHỊU LỖI)
- **Thời lượng:** ~1 phút 45 giây
- **Cues:** Nhấn mạnh cơ chế Circuit Breaker giống cầu dao điện.

> **🗣️ LỜI THOẠI:**  
> "Khi các service cần trao đổi dữ liệu với nhau, Spring Cloud cung cấp **OpenFeign**.  
> Thay vì phải tự viết mã gọi HTTP thủ công bằng RestTemplate, với OpenFeign ta chỉ cần khai báo một Interface Java với annotation `@FeignClient`, Spring sẽ tự động xử lý việc gọi qua mạng ngầm định, cú pháp gọn gàng như gọi hàm nội bộ.  
> 
> Tuy nhiên, bài toán đặt ra là: **Nếu service đích bị chết hoặc bị treo mạng thì sao?**  
> Nếu Order gọi sang Product mà Product không phản hồi, các luồng xử lý của Order sẽ bị giữ lại chờ timeout. Khi lượng người dùng tăng cao, tài nguyên của Order Service sẽ cạn kiệt và sập theo. Đó chính là hiện tượng **Cascading Failure (Sập dây chuyền)**.  
> 
> Để khắc phục, ta kết hợp thêm **Resilience4j Circuit Breaker** — hoạt động theo cơ chế của chiếc **cầu dao điện**:  
> - Trạng thái bình thường (**CLOSED**): Request đi qua bình thường.  
> - Khi tỉ lệ lỗi vượt ngưỡng cài đặt (ví dụ trên 50%): Cầu dao lập tức chuyển sang trạng thái ngắt (**OPEN**). Lúc này, mọi request mới gửi đến sẽ bị chặn ngay lập tức, không gửi qua mạng nữa để tránh làm tắc nghẽn thêm.  
> - Đồng thời, hệ thống kích hoạt hàm dự phòng (**Fallback Method**), trả về một kết quả an toàn (ví dụ: thông báo đơn hàng đang ở trạng thái chờ xử lý).  
> 
> Nhờ cơ chế này, toàn bộ hệ thống vẫn đứng vững ngay cả khi một service thành phần gặp sự cố."

---

## SLIDE 9: ƯU ĐIỂM & NHƯỢC ĐIỂM TRONG THỰC TẾ
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Giữ góc nhìn khách quan, cân bằng giữa ưu và nhược điểm.

> **🗣️ LỜI THOẠI:**  
> "Nhìn một cách khách quan, Microservices không phải là giải pháp phù hợp cho mọi trường hợp, mà luôn có sự đánh đổi:  
> 
> - **Về ưu điểm:** Khả năng mở rộng độc lập từng phần rất tốt, lỗi được cô lập triệt để, và các nhóm lập trình có thể làm việc song song mà không phụ thuộc nhau.  
> - **Về thách thức:**  
>   - Việc vận hành và giám sát phức tạp hơn rất nhiều, đòi hỏi kiến thức về Docker, Kubernetes và hệ thống log tập trung.  
>   - Giao tiếp qua mạng làm tăng độ trễ so với gọi hàm nội bộ.  
>   - Và phức tạp nhất là bài toán **Toàn vẹn dữ liệu phân tán**: không thể dùng `@Transactional` thông thường giữa các database riêng lẻ, mà phải áp dụng nguyên lý **Eventual Consistency** và mô hình **Saga Pattern**.  
> 
> Vì vậy, bài học thực tế là: Với các hệ thống lớn, lưu lượng truy cập cao thì Microservices là lựa chọn cần thiết. Nhưng với các dự án nhỏ, giai đoạn MVP ban đầu, kiến trúc **Monolith** vẫn là giải pháp tối ưu nhất về chi phí và thời gian triển khai.  
> 
> Tiếp theo đây, em xin phép chuyển sang phần **Live Demo** để cùng thầy và các bạn quan sát thực tế cách hệ thống vận hành."

---

## SLIDE 10: GIỚI THIỆU MÔ HÌNH DỰ ÁN DEMO
- **Thời lượng:** ~1 phút
- **Cues:** Chuyển sang tư thế thao tác máy tính, mở sẵn các cửa sổ cần thiết.

> **🗣️ LỜI THOẠI:**  
> "Mô hình demo gồm 4 service chạy trên nền Spring Boot 3:  
> 1. **`discovery-server`** chạy port **8761**: Đóng vai trò là Eureka Server.  
> 2. **`api-gateway`** chạy port **8080**: Cổng điều hướng tập trung.  
> 3. **`product-service`** chạy port **8081**: Cung cấp API thông tin sản phẩm.  
> 4. **`order-service`** chạy port **8082**: Nhận tạo đơn hàng, gọi sang Product Service qua OpenFeign và bọc bởi Resilience4j Circuit Breaker.  
> 
> Hiện tại 4 service đều đã được khởi động sẵn trên máy. Bây giờ em xin phép chuyển màn hình sang Desktop để thực hiện 3 kịch bản demo."

---

## SLIDE 11: KỊCH BẢN LIVE DEMO (THAO TÁC TRÊN MÀN HÌNH)
- **Thời lượng:** ~6 phút
- **Cues:** Thao tác rõ ràng, phóng to màn hình trình duyệt và Postman để cả lớp nhìn rõ dữ liệu JSON.

> **🗣️ LỜI THOẠI TRỰC TIẾP:**  
> 
> *(Mở trình duyệt tab `http://localhost:8761`)*  
> "**Kịch bản 1: Kiểm tra Service Discovery trên Eureka Dashboard.**  
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

## SLIDE 12: KẾT LUẬN & ỨNG DỤNG THỰC TẾ
- **Thời lượng:** ~1 phút 30 giây
- **Cues:** Chuyển lại màn hình slide, giọng tự nhiên, đúc kết ngắn gọn.

> **🗣️ LỜI THOẠI:**  
> "Như vậy, qua phần tìm hiểu và demo vừa rồi, ta thấy kiến trúc Microservices giải quyết rất tốt bài toán về quy mô và tính sẵn sàng của hệ thống lớn.  
> 
> Các nền tảng như Netflix, Shopee hay Tiki sử dụng mô hình này để đảm bảo hệ thống không bị gián đoạn khi lượng truy cập tăng đột biến.  
> 
> Tuy nhiên, việc áp dụng kiến trúc nào phụ thuộc vào bài toán cụ thể. Microservices phù hợp khi hệ thống đủ lớn và có nhiều đội ngũ phát triển; còn với những bài toán vừa và nhỏ, Monolith vẫn là giải pháp hiệu quả và tiết kiệm chi phí nhất."

---

## SLIDE 13: TỔNG KẾT & CÂU HỎI (Q&A)
- **Thời lượng:** ~30 giây
- **Cues:** Cười tươi, cảm ơn và mở lời mời nhận xét.

> **🗣️ LỜI THOẠI:**  
> "Bài trình bày về Spring for Microservices của em xin được kết thúc tại đây. Em xin cảm ơn thầy và các bạn đã chú ý lắng nghe!  
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
