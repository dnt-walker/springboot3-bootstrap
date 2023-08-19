package kr.co.signal9.springdatatest.repository;

import kr.co.signal9.springdatatest.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostQuerydslRepository{
    Page<Post> findByTitleContains(String title, Pageable pageable);

    @Query("SELECT p FROM Post AS p")
    Page<Post> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM post WHERE id = :id", nativeQuery = true)
    Optional<Post> findByMyId(@Param("id") Long id);


}
