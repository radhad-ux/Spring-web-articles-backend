package se.sdaproject.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40)
    private String title;
    @Column(length = 100)
    private String body;

    @Column(length = 20)
    private String authorName;

    @OneToMany(mappedBy = "comArticle", cascade = CascadeType.ALL)
    private List<Comment> comments;


    @ManyToMany
    private List<Topics> topicsList;


    public Article(){

    }

    public Article(String title, String body, String authorName){
        this.title = title;
        this.body = body;
        this.authorName = authorName;
    }

    public List<Topics> getTopicsList() {
        return topicsList;
    }

    public void setTopicsList(List<Topics> topicsList) {
        this.topicsList = topicsList;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
