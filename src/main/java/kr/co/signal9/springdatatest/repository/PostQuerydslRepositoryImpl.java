package kr.co.signal9.springdatatest.repository;

import com.querydsl.jpa.JPQLQuery;
import kr.co.signal9.springdatatest.entity.Post;
import kr.co.signal9.springdatatest.entity.QPost;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;


public class PostQuerydslRepositoryImpl extends QuerydslRepositorySupport implements PostQuerydslRepository {

    public PostQuerydslRepositoryImpl() {
        super(Post.class);
    }

    @Override
    public Optional<Post> findBra() {
        QPost postTable = QPost.post;
        JPQLQuery<Post> query = from(postTable).where(postTable.title.contains("bra"));
        return query.stream().findFirst();
    }
}
