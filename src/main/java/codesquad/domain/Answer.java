package codesquad.domain;

import codesquad.util.DateUtil;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_user"))
    private User writer;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_question"))
    private Question question;

    @Lob
    private String contents;

    private Date date;

    private boolean deleted;


    public Answer() {
        date = new Date();
        deleted = false;
    }

    public Answer(User writer, Question question, String contents) {
        this();
        this.writer = writer;
        this.question = question;
        this.contents = contents;

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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return DateUtil.getFormatYyyymmddhhmm(this.date);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted() {
        this.deleted = true;
    }

    public boolean isWriter(User loginUser) {
        return this.writer.isSameUser(loginUser);
    }
}
