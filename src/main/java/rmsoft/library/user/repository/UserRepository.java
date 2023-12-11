package rmsoft.library.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rmsoft.library.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
