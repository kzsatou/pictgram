package com.example.pictgram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pictgram.entity.User;

@Repository
/*型指定、インスタンス化する時に型の指定が必要*/
/*対象エンティティクラス:User、プライマリキー:String*/
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}
