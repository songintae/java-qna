package codesquad.domain;

import codesquad.util.DateUtil;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @Column(length = 100, nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @Column(length = 16, nullable = false)
    private Date date;

    @Column
    @ColumnDefault(value = "false")
    private boolean deleted;

    public Question() {
        date = new Date();
        deleted = false;
    }

    public Question(User writer, String title, String contents) {
        this();
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public String getDate() {
        return DateUtil.getFormatYyyymmddhhmm(this.date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean updateQuestion(Question updateQuestion, User user) {
        if (!isOwner(user))
            return false;
        setTitle(updateQuestion.getTitle());
        setContents(updateQuestion.getContents());
        return true;
    }

    public boolean isOwner(User user) {
        if (user == null)
            return false;
        return this.writer.isSameUser(user);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted() {
        this.deleted = true;
    }

    public boolean isDeleteQuestion(User user, List<Answer> answers) {
        if(!writer.isSameUser(user))
            return false;
        for (Answer answer : answers) {
            if(!answer.isWriter(user))
                return false;
        }
        return true;
    }
}
