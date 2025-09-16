<h2 align="center">
    <a href="https://dainam.edu.vn/vi/khoa-cong-nghe-thong-tin">
    🎓 Faculty of Information Technology (DaiNam University)
    </a>
</h2>
<h2 align="center">
   ỨNG DỤNG ĐIỀU KHIỂN MÁY TÍNH TỪ XA (REMOTE CONTROL)
</h2>
<div align="center">
    <p align="center">
        <img src="docs/aiotlab_logo.png" alt="AIoTLab Logo" width="170"/>
        <img src="docs/fitdnu_logo.png" alt="AIoTLab Logo" width="180"/>
        <img src="docs/dnu_logo.png" alt="DaiNam University Logo" width="200"/>
    </p>

[![AIoTLab](https://img.shields.io/badge/AIoTLab-green?style=for-the-badge)](https://www.facebook.com/DNUAIoTLab)
[![Faculty of Information Technology](https://img.shields.io/badge/Faculty%20of%20Information%20Technology-blue?style=for-the-badge)](https://dainam.edu.vn/vi/khoa-cong-nghe-thong-tin)
[![DaiNam University](https://img.shields.io/badge/DaiNam%20University-orange?style=for-the-badge)](https://dainam.edu.vn)

</div>

## 📖 1. Giới thiệu
    Ứng dụng điều khiển máy tính từ xa (Remote Control) 
- Cho phép người dùng truy cập, theo dõi và điều khiển máy tính ở xa thông qua mạng Internet hoặc mạng LAN.
- Hệ thống cho phép một máy tính (Client) có thể kết nối và điều khiển một máy tính khác (Server/Host) theo thời gian thực.
- Máy chủ (Remote Server): là máy tính bị điều khiển, có nhiệm vụ chia sẻ màn hình và tiếp nhận các lệnh điều khiển từ xa.
- Máy khách (Remote Client): là máy tính điều khiển, có nhiệm vụ hiển thị màn hình từ xa và gửi các thao tác chuột, bàn phím đến máy chủ.
- Người dùng có thể xem toàn bộ màn hình của máy từ xa.
- Thao tác chuột, bàn phím như ngồi trực tiếp trước máy.
- Giao diện đơn giản, dễ sử dụng, có thể mở rộng thêm tính năng bảo mật và xác thực.
- Làm việc từ xa (Remote Work).
- Quản trị hệ thống & server.

## 🔧 2. Ngôn ngữ lập trình sử dụng: [![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)
    Thư viện sử dụng trong Java:

- java.net → Socket, ServerSocket (gửi/nhận dữ liệu).

- java.awt.Robot → điều khiển chuột, bàn phím.

- javax.imageio.ImageIO → đọc/ghi ảnh (truyền màn hình).

- javax.swing → hiển thị giao diện Client.

## 🚀 3. Các chức năng, hình ảnh

- Sau khi chạy RemoteServer.java:

![alt text](image-4.png)

- Sau khi chạy RemoteClient.java:

![alt text](image-3.png)

## 🚀 4. Các bước cài đặt

Bước 1. Chuẩn bị môi trường

- Cài đặt Java JDK 8 trở lên.

- Thiết lập biến môi trường JAVA_HOME và PATH để có thể dùng lệnh javac và java trong terminal/cmd.

- Kiểm tra bằng lệnh:

java -version
javac -version

Bước 2. Tạo project

- Tạo một thư mục chứa source code

- Bên trong có cấu trúc:

![alt text](image-5.png)

Bước 3. Khởi động server

- Trên máy bị điều khiển (Server):

java RemoteServer

- Kết quả:

RemoteServer đã sẵn sàng...

Bước 4. Kết nối từ Client

- Trên máy điều khiển (Client):

java RemoteClient

- Trong code, nhớ đổi địa chỉ IP:

Registry registry = LocateRegistry.getRegistry("172.16.xx.xx", 1099);

👉 172.16.xx.xx chính là IP của máy bị điều khiển.

Bước 5. Kiểm thử

Sau khi kết nối thành công:

- Client sẽ thấy màn hình của Server.

- Di chuyển chuột trên Client → chuột di chuyển trên Server.

- Gõ bàn phím trên Client → chữ hiển thị trên Server.

- Có thể mở Notepad (trên Server) rồi gõ thử từ Client để kiểm chứng.

Bước 6. Mở rộng (tùy chọn)

- Thêm mật khẩu khi client kết nối.

- Thêm chức năng gửi file, chat.

- Chạy nhiều client cùng kết nối server.

## 📝 License

Họ tên: Nguyễn Chí Nhật

MSV: 1671020231

Lớp: CNTT 16-03

© 2025 AIoTLab, Faculty of Information Technology, DaiNam University. All rights reserved.

---