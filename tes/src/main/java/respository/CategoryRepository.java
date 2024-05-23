package respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
