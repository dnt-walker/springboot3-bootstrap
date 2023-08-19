package kr.co.signal9.springdatatest.repository;

import kr.co.signal9.springdatatest.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostQuerydslRepository {
    Optional<Post> findBra();
}
