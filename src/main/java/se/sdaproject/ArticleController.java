package se.sdaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sdaproject.model.Article;

import java.util.List;

@RestController
public class ArticleController {

    ArticleRepository articleRepository;
    TopicsRepository topicsRepository;

   @Autowired
   public ArticleController(ArticleRepository articleRepository){
       this.articleRepository = articleRepository;
   }

    @GetMapping("/articles")
    public List<Article> listAllArticles(){
       List<Article> articles = articleRepository.findAll();
       return articles;
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id){
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(article);
    }
    @PostMapping("/articles")
    public ResponseEntity<Article>  createArticle(@RequestBody Article article) {
        articleRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article updatedArticle){
       articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
       updatedArticle.setId(id);
       Article article = articleRepository.save(updatedArticle);
       return ResponseEntity.ok(article);
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable Long id){
        Article article = articleRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        articleRepository.delete(article);
        return ResponseEntity.ok(article);

    }


}
