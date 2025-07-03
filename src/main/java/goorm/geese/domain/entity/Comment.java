package goorm.geese.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> recomments = new ArrayList<>();

    private String content;

    private Comment(Member member, Post post, String content) {
        this.member = member;
        this.post = post;
        this.content = content;

        member.getComments().add(this);
        post.getComments().add(this);
    }

    private Comment(Member member, Post post, Comment parent, String content) {
        this.member = member;
        this.post = post;
        this.parent = parent;
        this.content = content;

        member.getComments().add(this);
        post.getComments().add(this);
        parent.getRecomments().add(this);
    }

    public static Comment createComment(Member member, Post post, String content) {
        return new Comment(member, post, content);
    }

    public static Comment createReply(Member member, Post post, Comment parent, String content) {
        return new Comment(member, post, parent, content);
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
