package se.sdaproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sdaproject.model.Article;
import se.sdaproject.model.Topics;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TopicsController {

    TopicsRepository topicsRepository;
    ArticleRepository articleRepository;

    public TopicsController(TopicsRepository topicsRepository, ArticleRepository articleRepository){
        this.topicsRepository = topicsRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/topics")
    public List<Topics> listAllTopics(){
        List<Topics> topics = topicsRepository.findAll();
        return topics;
    }

    @GetMapping("/articles/{articleId}/topics")
    public ResponseEntity<Topics> getTopics(@PathVariable Long articleId){
        Topics topics = topicsRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(topics);
    }

    @PostMapping("/topics")
    public ResponseEntity<Topics> createTopics(@RequestBody Topics topics){
        topicsRepository.save(topics);
        return ResponseEntity.status(HttpStatus.CREATED).body(topics);
    }

    @PostMapping("/articles/{articleId}/topics")
    public ResponseEntity<Topics> createTopics(@PathVariable Long articleId, @RequestBody Topics topicsParam){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(ResourceNotFoundException::new);
        Topics topics = topicsRepository.findByName(topicsParam.getName())
                .orElse(topicsParam);
        //List<Article> topicsArticles = topics.getTopic();
        topics.getTopic().add(article);
        topicsRepository.save(topics);
        return ResponseEntity.status(HttpStatus.CREATED).body(topics);
    }

    @GetMapping("/topics/{topicId}/articles")
    public ResponseEntity<List<Article>> listAllArticlesByTopics(@PathVariable Long topicId){
        Topics topics = topicsRepository.findById(topicId).orElseThrow(ResourceNotFoundException::new);
        List<Article> article = topics.getTopic();
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/articles/{articleId}/topics/{topicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopics(@PathVariable Long articleId, @PathVariable Long topicId){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(ResourceNotFoundException::new);
        Topics topics = topicsRepository.findById(topicId)
                .orElseThrow(ResourceNotFoundException::new);
         article.getTopicsList().remove(topics);
        articleRepository.save(article);
    }

    @PutMapping("/topics/{id}")
    public ResponseEntity<Topics> updateTopics(@PathVariable Long id, @Valid @RequestBody Topics updatedTopics){
        Topics topics = topicsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedTopics.setId(id);
        topicsRepository.save(updatedTopics);
        return ResponseEntity.ok(updatedTopics);
    }

    @DeleteMapping("/topics/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTopics(@PathVariable Long id){
        Topics topics = topicsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        topicsRepository.delete(topics);
    }

}
