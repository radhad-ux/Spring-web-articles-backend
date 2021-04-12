package se.sdaproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicsController {

    TopicsRepository topicsRepository;
    ArticleRepository articleRepository;

    public TopicsController(TopicsRepository topicsRepository, ArticleRepository articleRepository){
        this.topicsRepository = topicsRepository;
        this.articleRepository = articleRepository;
    }

    @PostMapping("topics")
    public ResponseEntity<Topics> createTopics(@RequestBody Topics topics){
        topicsRepository.save(topics);
        return ResponseEntity.status(HttpStatus.CREATED).body(topics);
    }

    @PostMapping("/articles/{articleId}/topics")
    public ResponseEntity<Topics> createTopics(@PathVariable Long articleId, @RequestBody Topics topics){
        Article article = articleRepository.findById(articleId)
                .orElseThrow(ResourceNotFoundException::new);
        topics.getTopic().add(article);
        topicsRepository.save(topics);
        return ResponseEntity.status(HttpStatus.CREATED).body(topics);
    }


}
