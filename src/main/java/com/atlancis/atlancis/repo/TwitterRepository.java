package com.atlancis.atlancis.repo;

import com.atlancis.atlancis.model.Twitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface TwitterRepository extends JpaRepository<Twitter,Integer> {
    <T> Optional<T> findById(Long noteId);

       /* Iterable<Twitter> findAllById();
        //List<Twitter> findAllById(Integer id);*/

      /* Iterable<Twitter> findOne(Integer id);*/
}
