package se.sdaproject;

import javax.persistence.*;
import java.util.List;

@Entity
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<Article> topic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getTopic() {
        return topic;
    }

    public void setTopic(List<Article> topic) {
        this.topic = topic;
    }
}
