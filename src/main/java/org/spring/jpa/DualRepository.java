package org.spring.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DualRepository extends JpaRepository<Dual, String> {
    @Query(nativeQuery = true, value = "SELECT to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') current_time" +
            " FROM dual")
    List<Dual> currentTime();
}
