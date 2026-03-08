package com.jicjo.apis.repository.core;

import com.jicjo.apis.dto.core.UsersInfoDto;
import com.jicjo.apis.model.core.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>, Serializable {
    @Query("SELECT U FROM Users U WHERE U.userName = :userName")
    Optional<Users> findByUsername(@Param("userName") String userName);

    @Query("SELECT U FROM Users U WHERE (U.clntName = :clntName or :clntName is null) AND (U.userName = :userName or :userName is null)")
    List<Users> getAllUsers(String userName, String clntName);

    @Query("SELECT new com.jicjo.apis.dto.core.UsersInfoDto(U.userName, U.clntName, U.fullArFullName, U.fullEnName, U.type, U.preferredLang) FROM Users U WHERE U.userName = :userName")
    Optional<UsersInfoDto> loadUserInfo(@Param("userName") String userName);
}