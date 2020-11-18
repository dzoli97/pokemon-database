package hu.unideb.pokemondatabase.repo;

import org.springframework.data.repository.CrudRepository;

import hu.unideb.pokemondatabase.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

}
