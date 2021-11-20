package com.example.backcoursework.repos;

import com.example.backcoursework.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    @Query("SELECT user FROM User user WHERE user.login=:login")
    User findUserByLogin(@Param("login") String login);

    @Modifying
    @Query(value = "UPDATE User u SET u.rating = u.rating - 1 WHERE u.id=:id")
    void decreaseRating(@Param("id") Integer id);

    @Modifying
    @Query(value = "UPDATE User u SET u.rating = u.rating + 1 WHERE u.id=:id")
    void increaseRating(@Param("id") Integer id);
}

