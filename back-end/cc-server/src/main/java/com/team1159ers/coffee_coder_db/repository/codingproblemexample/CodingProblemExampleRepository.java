
package com.team1159ers.coffee_coder_db.repository.codingproblemexample;

import com.team1159ers.coffee_coder_db.model.codingproblemexample.CodingProblemExample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodingProblemExampleRepository extends JpaRepository<CodingProblemExample, Integer> {

}
