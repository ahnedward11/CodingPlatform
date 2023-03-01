
package com.team1159ers.coffee_coder_db;

import com.team1159ers.coffee_coder_db.service.codingproblem.CodingProblemService;
import com.team1159ers.coffee_coder_db.service.codingproblemexample.CodingProblemExampleService;
import com.team1159ers.coffee_coder_db.service.codingproblemtestcase.CodingProblemTestCaseService;
import com.team1159ers.coffee_coder_db.service.dailyexercise.DailyExerciseService;
import com.team1159ers.coffee_coder_db.service.dailyexerciseexample.DailyExerciseExampleService;
import com.team1159ers.coffee_coder_db.service.dailyexercisetestcase.DailyExerciseTestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Initializes DailyExercise and CodingProblem data.
 */
@SpringBootApplication
public class CoffeeCoderDb implements ApplicationRunner {
	private final CodingProblemService codingProblemService;
	private final CodingProblemExampleService codingProblemExampleService;
	private final CodingProblemTestCaseService codingProblemTestCaseService;
	private final DailyExerciseService dailyExerciseService;
	private final DailyExerciseExampleService dailyExerciseExampleService;
	private final DailyExerciseTestCaseService dailyExerciseTestCaseService;

	@Autowired
	public CoffeeCoderDb(CodingProblemService codingProblemService,
						 CodingProblemExampleService codingProblemExampleService,
						 CodingProblemTestCaseService codingProblemTestCaseService,
						 DailyExerciseService dailyExerciseService,
						 DailyExerciseExampleService dailyExerciseExampleService,
						 DailyExerciseTestCaseService dailyExerciseTestCaseService) {
		this.codingProblemService = codingProblemService;
		this.codingProblemExampleService = codingProblemExampleService;
		this.codingProblemTestCaseService = codingProblemTestCaseService;
		this.dailyExerciseService = dailyExerciseService;
		this.dailyExerciseExampleService = dailyExerciseExampleService;
		this.dailyExerciseTestCaseService = dailyExerciseTestCaseService;
	}

	@Override
	public void run(ApplicationArguments args) {
		/*
		// Initialize and save new Daily Exercise data
		DailyExerciseDataInitializer exerciseDataInitializer = new DailyExerciseDataInitializer(
				dailyExerciseService,
				dailyExerciseExampleService,
				dailyExerciseTestCaseService
		);
		exerciseDataInitializer.saveDailyExerciseData();

		// Initialize and save Coding Problem data
		CodingProblemDataInitializer problemDataInitializer = new CodingProblemDataInitializer(
				dailyExerciseService,
				codingProblemService,
				codingProblemExampleService,
				codingProblemTestCaseService
		);
		problemDataInitializer.saveCodingProblemData();
		 */
	}
}
