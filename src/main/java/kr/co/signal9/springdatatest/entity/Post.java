package kr.co.signal9.springdatatest.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name="post")
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue
    Long id;

    String title;

    String context;

    //@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    Date createdDate;

    @PrePersist
    public void updateDate() {
        // Audit 사용하지 않고 Entity Life 사이클 콜백함수를 사용해서 Audit 기능 구현.
        this.createdDate = new Date();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
