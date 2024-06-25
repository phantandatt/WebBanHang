package com.tandat.webdemo.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tandat.webdemo.entity.User;
// nơi mà các phương thức để truy vấn và tương tác với cơ sở dữ liệu được định nghĩa
@Repository
// JpaRepository là một interface trong Spring Data JPA cung cấp các phương thức tiêu chuẩn để thao tác với đối tượng user kiểu dữ liệu long
public interface UserRepository extends JpaRepository<User, Long> {
	// tìm kiếm user bằng userName
	Optional<User> findByUsername(String username);
	// Trả về một đối tượng Optional<User>, cho phép xử lý linh hoạt kết quả tìm thấy hoặc không tìm thấy mà không bị lỗi NullPointerException
}
