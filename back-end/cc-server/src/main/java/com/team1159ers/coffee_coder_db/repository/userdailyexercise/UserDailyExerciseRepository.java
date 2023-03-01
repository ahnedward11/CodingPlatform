
package com.team1159ers.coffee_coder_db.repository.userdailyexercise;

import com.team1159ers.coffee_coder_db.model.userdailyexercise.UserDailyExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserDailyExerciseRepository extends JpaRepository<UserDailyExercise, Integer> {
    @Query("SELECT ude " +
                "FROM UserDailyExercise ude " +
                "WHERE (ude.user.csulbId = :csulbId AND " +
                "ude.dailyExercise.exerciseId = :exerciseId)")
    UserDailyExercise getUserDailyExercise(@Param("csulbId") int csulbId, @Param("exerciseId") int exerciseId);

    @Query("SELECT ude " +
                "FROM UserDailyExercise ude " +
                "WHERE ude.user.csulbId = :csulbId AND " +
                "ude.problemId = :problemId")
    List<UserDailyExercise> getUserDailyExercisesByProblemId(@Param("csulbId") int csulbId, @Param("problemId") int problemId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE " +
                "FROM UserDailyExercise ude " +
                "WHERE ude.userId = :csulbId AND ude.exerciseId = :exerciseId")
    void deleteUserDailyExercise(@Param("csulbId") int csulbId, @Param("exerciseId") int exerciseId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE UserDailyExercise ude " +
                "SET ude.codeContent = :codeContent " +
                "WHERE ude.user.csulbId = :csulbId")
    void updateCodeContent(@Param("csulbId") int csulbId, @Param("codeContent") String codeContent);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE UserDailyExercise ude " +
                "SET ude.isSolved = :isSolved " +
                "WHERE (ude.user.csulbId = :csulbId AND " +
                "ude.dailyExercise.exerciseId = :exerciseId)")
    void updateIsSolved(@Param("csulbId") int csulbId, @Param("exerciseId") int exerciseId, @Param("isSolved") boolean isSolved);
}
