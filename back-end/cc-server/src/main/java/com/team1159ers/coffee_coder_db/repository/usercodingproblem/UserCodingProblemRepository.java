
package com.team1159ers.coffee_coder_db.repository.usercodingproblem;

import com.team1159ers.coffee_coder_db.model.usercodingproblem.UserCodingProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserCodingProblemRepository extends JpaRepository<UserCodingProblem, Integer> {
    @Query("SELECT ucp " +
                "FROM UserCodingProblem ucp " +
                "WHERE (ucp.user.csulbId = :csulbId AND " +
                    "ucp.codingProblem.problemId = :problemId)")
    UserCodingProblem getUserCodingProblem(@Param("csulbId") int csulbId, @Param("problemId") int problemId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE UserCodingProblem ucp " +
        "SET ucp.codeContent = :codeContent " +
        "WHERE ucp.user.csulbId = :csulbId")
    void updateCodeContent(@Param("csulbId") int csulbId, @Param("codeContent") String codeContent);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE UserCodingProblem ucp " +
                "SET ucp.isSolved = :isSolved " +
                "WHERE ucp.user.csulbId = :csulbId")
    void updateIsSolved(@Param("csulbId") int csulbId, @Param("isSolved") boolean isSolved);
}
