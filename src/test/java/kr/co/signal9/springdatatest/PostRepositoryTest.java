package kr.co.signal9.springdatatest;

import kr.co.signal9.springdatatest.entity.Post;
import kr.co.signal9.springdatatest.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    public void querydslTest() {
        Post post = new Post();
        post.setTitle("bra test post 1");
        post.setContext("this post 1.. bra~ bra~ bra~ ");
        Post savePost = postRepository.save(post);

        Optional<Post> braPost = postRepository.findBra();
        assertThat(braPost).isNotEmpty();

    }

    /*

    도메인 이벤트 테스트 코드
    AbstractAggregationRoot<E> 객체를 상속 받아서 구현함에 아래 코드로 이벤트로 날릴 필요가 없음.
    스프링에 구현되어 있는 걸 사용하면 됨.
    별도로 이벤트 발생 시킬 도메인 액션에 등록 메쏘드를 호출만 하면됨.
    예> Post Entity에 publish method추가.

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void eventTest() {
        Post post = new Post();
        post.setTitle("event test post");
        post.setContext("bra~ bra~ ");
        PostPublishedEvent event = new PostPublishedEvent(post);
        applicationContext.publishEvent(event);
    }
    */

    @Test
    @Rollback(false)
    public void post_test() {
        Post post = new Post();
        post.setTitle("post 1");
        post.setContext("this post 1.. bra~ bra~ bra~ ");

        //Post newPost = postRepository.save(post);
        // 도메인 이벤트 처리를 위해서 별도의 메쏘드 호울
        Post newPost = postRepository.save(post);

        assertThat(newPost.getId()).isNotNull();

        List<Post> posts = postRepository.findAll();
        assertThat(posts).contains(newPost);

        Page<Post> page = postRepository.findAll(PageRequest.of(0, 10));
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getTotalPages()).isEqualTo(1);

        //Page<Post> titlePage = postRepository.findByTitleContains("post", PageRequest.of(0, 10));
        Page<Post> titlePage
                = postRepository.findByTitleContains("post",
                PageRequest.of(0, 10, Sort.by("createdDate").descending()));
        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getTotalPages()).isEqualTo(1);

    }

}
