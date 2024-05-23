-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 28, 2023 lúc 05:12 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `phone_store`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `username` char(255) NOT NULL,
  `password` char(255) NOT NULL,
  `dob` date NOT NULL,
  `email` char(255) NOT NULL,
  `address` char(255) NOT NULL,
  `role` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`username`, `password`, `dob`, `email`, `address`, `role`) VALUES
('admin', 'admin', '2001-01-15', 'pdat646@gmail.com', '123 TP.HCM', 1),
('chuotcon', 'dat12345', '2023-11-10', 'pdat646@gmail.com', '224 linh trung', 0),
('gacon', '123456', '2023-10-31', 'chuotcon@123', '224 linh trung tp thu duc', 1),
('tandat', 'tandat', '2000-10-28', 'phantandat@gmail.com', '224 linh trung tp thu duc', 0),
('user1', '123456', '2023-09-28', 'pdat646@gmail.com', '224 linh trung, phường Linh Trung, quận Thủ Đức, TPHCM', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
--

CREATE TABLE `bill` (
  `OderID` tinyint(4) NOT NULL,
  `username` char(255) NOT NULL,
  `dateOrder` date NOT NULL,
  `TotalPrice` int(11) NOT NULL,
  `Status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `username` char(255) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `ProductName` char(255) NOT NULL,
  `amount` tinyint(4) NOT NULL,
  `price` int(11) NOT NULL,
  `imgpath` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`username`, `ProductID`, `ProductName`, `amount`, `price`, `imgpath`) VALUES
('admin2', 15, 'Xiaomi 13T Pro Xanh', 1, 23, 'xiaomi-13t-pro-xanh.jpg'),
('admin2', 18, 'Nokia 8210 4G Đỏ', 1, 23, 'nokia 8210-4g-do.jpg'),
('admin2', 10, 'Samsung Galaxy S23 Fe Xanh', 2, 231, 'samsung-galaxy-s23-fe-xanh.jpg'),
('admin2', 16, 'Xiaomi 13T Xanh Dương', 1, 213, 'xiaomi-13-t-xanh-duong.jpg'),
('admin2', 12, 'Samsung Galaxy Z-Flip 5 Xanh Mint', 1, 1, 'samsung-galaxy-z-flip5-xanh-mint.jpg'),
('chuotcon', 26, 'Nokia 105', 1, 21, 'nokia-105-4g-blue.jpg'),
('gacon', 14, 'Samsung Galaxy M54 5G', 3, 34, 'samsung-galaxy-m54-5g.jpg'),
('admin', 23, 'Oppo A78', 1, 231, 'oppo-a78-xanh.jpg'),
('admin', 14, 'Samsung Galaxy M54 5G', 3, 34, 'samsung-galaxy-m54-5g.jpg'),
('admin', 2, 'Samsung S23 Ultra', 1, 500, 'samsung-galaxy-s21-ultra.jpg'),
('admin', 1, 'IPhone 11', 1, 400, 'iphone-11.jpg'),
('gacon', 26, 'Nokia 105', 1, 21, 'nokia-105-4g-blue.jpg'),
('gacon', 25, 'Nokia C21 Plus', 1, 2, 'nokia-c21-plus-blue.jpg'),
('admin', 24, 'Oppo Renno 8T 5G', 1, 200, 'oppo-reno8t-5g-vang.jpg'),
('admin', 3, 'IPhone 15 Pro Max', 3, 300, 'iphone-15-pro-max.jpg'),
('tandat', 1, 'iPhone 11', 1, 400, 'iphone-11.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cartdetail`
--

CREATE TABLE `cartdetail` (
  `OrderDetailID` int(10) UNSIGNED NOT NULL,
  `OrderID` int(10) UNSIGNED NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `CategoryID` tinyint(4) NOT NULL,
  `Category` char(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`CategoryID`, `Category`) VALUES
(1, 'IPhone'),
(2, 'Samsung'),
(3, 'Xiaomi'),
(5, 'Oppo'),
(7, 'Nokia');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `discount`
--

CREATE TABLE `discount` (
  `code` char(10) NOT NULL,
  `value` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `discount`
--

INSERT INTO `discount` (`code`, `value`) VALUES
('DHNONGLAM', 11),
('KHOACNTT', 20),
('NONGLAM', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `ProductID` int(10) UNSIGNED NOT NULL,
  `ProductName` varchar(255) NOT NULL,
  `CategoryID` tinyint(4) NOT NULL,
  `Description` varchar(5000) NOT NULL,
  `Price` int(11) NOT NULL,
  `StockQuantity` smallint(6) NOT NULL,
  `Unit_price` char(255) NOT NULL,
  `imgpath` char(255) NOT NULL,
  `color` char(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`ProductID`, `ProductName`, `CategoryID`, `Description`, `Price`, `StockQuantity`, `Unit_price`, `imgpath`, `color`) VALUES
(1, 'iPhone 11', 1, '																																													iPhone 11 là một sản phẩm điện thoại di động của Apple, thuộc dòng iPhone, được ra mắt lần đầu vào tháng 9 năm 2017. Dưới đây là một số thông tin chung về iPhone 8:\r\n\r\nThiết kế: iPhone 8 có thiết kế tương tự như iPhone 7, với vỏ nhôm và kính ở mặt trước và sau. Nó có màu sắc đa dạng, bao gồm Silver (Bạc), Gold (Vàng) và Space Gray (Xám).\r\n\r\nMàn hình: Máy được trang bị màn hình Retina IPS LCD kích thước 4.7 inch với độ phân giải 750 x 1334 pixel, cho hình ảnh rõ nét và sắc nét.\r\n\r\nHiệu năng: iPhone 8 sử dụng chip A11 Bionic của Apple, mạnh mẽ và hiệu quả trong việc xử lý ứng dụng và nhiệm vụ đa nhiệm.\r\n\r\nCamera: iPhone 8 có camera 12 MP ở phía sau với khả năng quay video 4K và chụp ảnh chất lượng cao. Camera trước là 7 MP cho chất lượng selfie tốt.\r\n\r\nHệ điều hành: iPhone 8 chạy trên hệ điều hành iOS, phiên bản tùy thuộc vào thời điểm bạn mua máy, nhưng sau đó có thể nâng cấp lên phiên bản iOS mới nhất.\r\n\r\nBộ nhớ: Có hai phiên bản với dung lượng lưu trữ khác nhau, bao gồm 64GB và 256GB, \r\n															\r\n															\r\n															', 400, 10, '$', 'iphone-11.jpg', 'Trắng'),
(2, 'Samsung S23 Ultra', 2, 'Samsung Galaxy S21 Ultra là một trong những mẫu điện thoại thông minh hàng đầu của Samsung, thuộc dòng Galaxy S21, được ra mắt vào tháng 1 năm 2021. Đây là một trong những sản phẩm cao cấp của hãng Samsung, và dưới đây là một số thông tin chung về Samsung Galaxy S21 Ultra:\r\n\r\nThiết kế: Samsung Galaxy S21 Ultra có thiết kế hiện đại với vỏ kim loại và mặt kính cường lực. Máy có màn hình cong với các góc màn hình vô cực, tạo ra một hình ảnh rất đẹp.\r\n\r\nMàn hình: Máy có màn hình Dynamic AMOLED 2X kích thước 6.8 inch hoặc 6.9 inch (tùy từng phiên bản) với độ phân giải Quad HD+ (3200 x 1440 pixel), cho hình ảnh sắc nét và màu sắc sống động. Màn hình hỗ trợ tốt cho việc xem nội dung đa phương tiện và chơi game.\r\n\r\nHiệu năng: Samsung Galaxy S21 Ultra sử dụng một chip xử lý mạnh mẽ, thường là chip Exynos hoặc Snapdragon tùy theo khu vực. Điều này giúp máy chạy mượt mà và xử lý tất cả các tác vụ đa nhiệm một cách dễ dàng.\r\n\r\nCamera: Một trong những điểm đáng chú ý nhất của Galaxy S21 Ultra là hệ thống camera mạnh mẽ với nhiều ống kính. Nó bao gồm camera chính 108 MP, camera góc rộng 12 MP, camera telephoto 10 MP và camera telephoto 10 MP với khả năng zoom quang học 10x và 100x, giúp bạn chụp ảnh chất lượng cao và thực hiện chức năng zoom mạnh mẽ.\r\n\r\nHệ điều hành: Máy chạy trên hệ điều hành Android với giao diện người dùng Samsung One UI, có thể nâng cấp lên phiên bản Android mới nhất.\r\n\r\nBộ nhớ: Có các phiên bản với dung lượng lưu trữ khác nhau, bao gồm 128GB, 256GB, và 512GB, cho phép bạn lựa chọn theo nhu cầu cá nhân.\r\n\r\nKhả năng kết nối: Samsung Galaxy S21 Ultra hỗ trợ nhiều tính năng kết nối, bao gồm 4G LTE, 5G, Wi-Fi 6E, Bluetooth, NFC, và USB-C.\r\n\r\nS Pen: Máy hỗ trợ S Pen (bút cảm ứng) tùy chọn, cho phép bạn viết, vẽ và thực hiện các tác vụ tương tác khác trên màn hình.\r\n\r\nPin: Máy được trang bị pin lớn, giúp sử dụng cả ngày mà không cần sạc lại liên tục.', 500, 10, '$', 'samsung-galaxy-s21-ultra.jpg', 'Đen'),
(3, 'iPhone 15 Pro Max', 1, '																																																												\r\n															\r\n															\r\n															\r\n															', 300, 10, '$', 'iphone-15-pro-max.jpg', 'Tím'),
(4, 'IPhone 15 Pro', 1, '															\r\n															', 222, 4, '$', 'iphone-15-pro.jpg', 'Đen'),
(5, 'IPhone 14 Pro Max', 1, '																														\r\n															\r\n															', 111, 1, '$', 'iphone-14-pro-max.jpg', 'Đen'),
(6, 'IPhone 15 Plus', 1, '															\r\n															', 231, 1, '$', 'iphone-15-plus.jpg', 'Đen'),
(7, 'Samsung Galaxy A05', 2, '															\r\n															', 231, 23, '$', 'samsung-galaxy-a05.jpg', 'Xanh'),
(9, 'Samsung Galaxy S23', 2, '', 23, 23, '$', 'samsung-galaxy-s23.jpg', 'Xám'),
(10, 'Samsung Galaxy S23 Fe Xanh', 2, '', 231, 2, '$', 'samsung-galaxy-s23-fe-xanh.jpg', 'Xanh'),
(11, 'Samsung Galaxy S23 Plus', 2, '															dasdasdasd\r\n															', 1, 1, '$', 'samsung-galaxy-s23-plus.jpg', 'Xám'),
(12, 'Samsung Galaxy Z-Flip 5 Xanh Mint', 2, '', 1, 1, '$', 'samsung-galaxy-z-flip5-xanh-mint.jpg', 'Xanh'),
(13, 'Samsung Galaxy Z-Fold 5', 2, '															sdadas\r\n															', 2, 4, '$', 'samsung-galaxy-z-fold5.jpg', 'Kem'),
(14, 'Samsung Galaxy M54 5G', 2, '															\r\n															', 34, 3, '$', 'samsung-galaxy-m54-5g.jpg', 'Tím'),
(16, 'Xiaomi 13T', 3, '															\r\n															', 213, 1, '$', 'xiaomi-13-t-xanh-duong.jpg', 'Xanh dương'),
(18, 'Nokia 8210 4G Đỏ', 7, '', 23, 123, '$', 'nokia 8210-4g-do.jpg', 'Đỏ'),
(19, 'IPhone 11', 1, 'asdasd', 232, 10, '$', 'iphone-11.jpg', 'Xanh'),
(20, 'IPhone 11', 1, 'asdaskjd', 3874, 2, '$', 'iphone-11.jpg', 'Đỏ'),
(21, 'Xiaomi Redmi 12', 3, '																																													xiaomi redmi 12 bac											\r\n															\r\n															\r\n															', 231, 18, '$', 'xiaomi-redmi-12-bac.jpg', 'Bạc'),
(22, 'Oppo A57 ', 5, '															Oppo A57 xanh													\r\n															', 23, 4, '$', 'oppo-a57-xanh.jpeg', 'Xanh'),
(23, 'Oppo A78', 5, 'Oppo A78													', 231, 1, '$', 'oppo-a78-xanh.jpg', 'Xanh'),
(24, 'Oppo Renno 8T 5G', 5, '															Oppo Renno 8T 5G mau vang												\r\n															', 200, 4, '$', 'oppo-reno8t-5g-vang.jpg', 'Vàng'),
(25, 'Nokia C21 Plus', 7, '															Nokia c21 plus mau xanh duong												\r\n															', 2, 4, '$', 'nokia-c21-plus-blue.jpg', 'Xanh dương'),
(26, 'Nokia 105', 7, '															nokia 105 4g xanh duong														\r\n															', 21, 2, '$', 'nokia-105-4g-blue.jpg', 'Xanh dương');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reviews`
--

CREATE TABLE `reviews` (
  `ProductID` int(5) NOT NULL,
  `username` char(255) NOT NULL,
  `Comment` varchar(1000) NOT NULL,
  `sosao` double NOT NULL,
  `TimeReviews` char(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `reviews`
--

INSERT INTO `reviews` (`ProductID`, `username`, `Comment`, `sosao`, `TimeReviews`) VALUES
(11, 'admin', 'Sản phẩm tuyệt vời', 5, '2023-11-02'),
(11, 'admin', 'Sản phẩm tuyệt vời', 5, '2023-11-02'),
(1, 'admin', 'Sản phẩm tuyệt vời', 5, '12:33:32 2023-33-02'),
(18, 'admin', 'tuyệt vời', 5, '12:39:41   02-11-2023'),
(20, 'admin', 'Sản phẩm tuyệt vời.', 5, '10:17:52   02-11-2023'),
(20, 'admin', 'Sản phẩm tuyệt vời.', 5, '10:19:19   02-11-2023'),
(20, 'admin', 'sdaskdjksa', 5, '10:19:53   02-11-2023'),
(20, 'admin', 'sdsd', 3, '10:21:20   02-11-2023'),
(5, 'admin', 'tuet', 5, '12:53:00   07-11-2023'),
(5, 'admin', '', 3, '12:54:14   07-11-2023');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`username`);

--
-- Chỉ mục cho bảng `cartdetail`
--
ALTER TABLE `cartdetail`
  ADD PRIMARY KEY (`OrderDetailID`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CategoryID`);

--
-- Chỉ mục cho bảng `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`code`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ProductID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
