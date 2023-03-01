
package com.team1159ers.coffee_coder_db.repository.codingproblemsolution;

import com.team1159ers.coffee_coder_db.model.codingproblemsolution.CodingProblemSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CodingProblemSolutionRepository extends JpaRepository<CodingProblemSolution, Integer> {
    @Query("SELECT cps " +
                "FROM CodingProblemSolution cps " +
                "WHERE cps.codingProblem.problemId = :problemId")
    CodingProblemSolution getProblemSolution(@Param("problemId") int problemId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE CodingProblemSolution cps " +
                "SET cps.solutionCode = :solutionCode " +
                "WHERE cps.solutionId = :solutionId")
    void updateSolutionCode(@Param("solutionId") int solutionId, @Param("solutionCode") String solutionCode);

    @Query(nativeQuery = true, value =
        "SELECT COUNT(ups.user_id) " +
            "FROM upvoted_problem_solutions ups")
    int getUpvoteCount();

    @Query(nativeQuery = true, value =
        "SELECT COUNT(dps.user_id) " +
            "FROM downvoted_problem_solutions dps")
    int getDownvoteCount();

    @Query(nativeQuery = true, value =
            "SELECT ups.user_id " +
                "FROM upvoted_problem_solutions ups " +
                "WHERE ups.user_id = :csulbId AND ups.solution_id = :solutionId")
    Integer getUpvoterId(@Param("solutionId") int solutionId, @Param("csulbId") int csulbId);

    @Query(nativeQuery = true, value =
        "SELECT dps.user_id " +
            "FROM downvoted_problem_solutions dps " +
            "WHERE dps.user_id = :csulbId AND dps.solution_id = :solutionId")
    Integer getDownvoterId(@Param("solutionId") int solutionId, @Param("csulbId") int csulbId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(nativeQuery = true, value =
        "INSERT INTO upvoted_problem_solutions(`user_id`, `solution_id`) " +
            "VALUES (:csulbId, :solutionId)")
    void addUpvoter(@Param("solutionId") int solutionId, @Param("csulbId") int csulbId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(nativeQuery = true, value =
        "INSERT INTO downvoted_problem_solutions(`user_id`, `solution_id`) " +
            "VALUES (:csulbId, :solutionId)")
    void addDownvoter(@Param("solutionId") int solutionId, @Param("csulbId") int csulbId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(nativeQuery = true, value =
        "DELETE " +
            "FROM upvoted_problem_solutions ups " +
            "WHERE ups.user_id = :csulbId AND ups.solution_id = :solutionId")
    void deleteUpvoter(@Param("solutionId") int solutionId, @Param("csulbId") int csulbId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(nativeQuery = true, value =
        "DELETE " +
            "FROM downvoted_problem_solutions dps " +
            "WHERE dps.user_id = :csulbId AND dps.solution_id = :solutionId")
    void deleteDownvoter(@Param("solutionId") int solutionId, @Param("csulbId") int csulbId);
}
