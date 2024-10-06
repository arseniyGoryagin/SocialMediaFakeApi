package com.arseniy.fakeapi.user.repository;


import com.arseniy.fakeapi.user.domain.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query(value = "Update users_table set name = :name username = :username where id =:id", nativeQuery = true)
    User updateUser(@Param("name") String name, @Param("username") String username, @Param("id") Long id);

}
