package org.example.convent;
import org.example.convent.Convent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConventRepository extends JpaRepository<Convent, Long> {
}
