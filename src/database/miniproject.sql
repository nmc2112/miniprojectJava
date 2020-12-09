-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 09, 2020 lúc 08:43 AM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `miniproject`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tblpositions`
--

CREATE TABLE `tblpositions` (
  `position_id` int(11) NOT NULL,
  `position_name` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tblpositions`
--

INSERT INTO `tblpositions` (`position_id`, `position_name`) VALUES
(1, 'công nhân'),
(2, 'nhân viên'),
(3, 'kỹ sư');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tblstaffs`
--

CREATE TABLE `tblstaffs` (
  `staff_id` int(11) NOT NULL,
  `staff_name` varchar(50) DEFAULT NULL,
  `staff_age` int(11) NOT NULL,
  `staff_gender` varchar(10) NOT NULL,
  `staff_address` varchar(100) DEFAULT NULL,
  `staff_salary` bigint(20) NOT NULL,
  `position_id` int(11) NOT NULL,
  `staff_startYearofwork` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tblstaffs`
--

INSERT INTO `tblstaffs` (`staff_id`, `staff_name`, `staff_age`, `staff_gender`, `staff_address`, `staff_salary`, `position_id`, `staff_startYearofwork`) VALUES
(1, 'MTP Tung', 20, 'Nam', 'Hà Nội', 10, 3, 2015),
(2, 'Đạt 69', 20, 'Nữ', 'Hải Phòng', 12, 1, 2015);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tblpositions`
--
ALTER TABLE `tblpositions`
  ADD PRIMARY KEY (`position_id`);

--
-- Chỉ mục cho bảng `tblstaffs`
--
ALTER TABLE `tblstaffs`
  ADD PRIMARY KEY (`staff_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tblpositions`
--
ALTER TABLE `tblpositions`
  MODIFY `position_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `tblstaffs`
--
ALTER TABLE `tblstaffs`
  MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
