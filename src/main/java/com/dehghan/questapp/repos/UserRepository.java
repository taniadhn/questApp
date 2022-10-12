package com.dehghan.questapp.repos;

import com.dehghan.questapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**Burada hangi Object kullanacağına ve  ID tipini vermemız gerekiyor*/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
