# Bai9-ChatBot
## Trần Ngọc Nhất - 24162086
Tìm hiểu về socketIO để viết chức năng Hỗ trợ khách hàng (chat realtime) giữa khách hàng và manager.
- Sử dụng SocketIO để tạo chức năng Chatbot realtime
- Sử dụng SQLite để lưu trữ các đoạn chat hội thoại giữa Khách và Manager
- Chia quyền để tùy vào bạn là **KHÁCH** hay **MANAGER** mà sẽ hiển thị các đoạn nội dung 1 cách khác nhau

## Logic demo chức năng:
- Khi mở app người dùng có thể chọn là khách hoặc manager (nếu là manager yêu cầu đăng nhập (hiện tại chưa có tính năng đăng nhập vì chỉ là demo))
- Nếu là KHÁCH người dùng lập tức chuyển qua 1 view để chat
- Nếu là MANAGER thì chuyển qua 1 view khách để chọn đoạn hội thoại hiện có và sau khi chọn xong sẽ chuyển qua view khách để nhắn tin với KHÁCH
## Demo giao diện app 
> ẢNH 1: giao diện app khi mở 
![image](https://github.com/TngocNhut/Bai9-ChatBot/blob/02be95efc644ae588c4fd27a0b6b69da00e45c7d/app/Screenshot%202025-12-18%20115901.png)
> ẢNH 2: giao diện khi Login as guest - chuyển qua view nhắn tin ngay lập tức 
![image2](https://github.com/TngocNhut/Bai9-ChatBot/blob/02be95efc644ae588c4fd27a0b6b69da00e45c7d/app/Screenshot%202025-12-18%20115940.png)
> ẢNH 3: Nếu là MANAGER khi chọn tại giao diện ở ẢNH 1 bạn sẽ được chuyển đến 1 View khác nơi bạn có thể lựa chọn các đoạn hội thoại của khách (Manager có thể nhắn tin tới mọi khách)
![image3](https://github.com/TngocNhut/Bai9-ChatBot/blob/02be95efc644ae588c4fd27a0b6b69da00e45c7d/app/Screenshot%202025-12-18%20115956.png)
> ẢNH 4: Cách MANAGER nhắn tin
![image4](https://github.com/TngocNhut/Bai9-ChatBot/blob/02be95efc644ae588c4fd27a0b6b69da00e45c7d/app/Screenshot%202025-12-18%20120051.png)
