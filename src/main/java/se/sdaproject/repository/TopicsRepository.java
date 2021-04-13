package se.sdaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sdaproject.model.Topics;

import java.util.Optional;

public interface TopicsRepository extends JpaRepository<Topics, Long> {
    Topics findByName(String name);
}
