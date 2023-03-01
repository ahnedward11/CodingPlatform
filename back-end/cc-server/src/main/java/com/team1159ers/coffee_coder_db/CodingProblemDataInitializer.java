package com.team1159ers.coffee_coder_db;

import com.team1159ers.coffee_coder_db.model.codingproblem.CodingProblem;
import com.team1159ers.coffee_coder_db.model.codingproblemexample.CodingProblemExample;
import com.team1159ers.coffee_coder_db.model.codingproblemtestcase.CodingProblemTestCase;
import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;
import com.team1159ers.coffee_coder_db.service.codingproblem.CodingProblemService;
import com.team1159ers.coffee_coder_db.service.codingproblemexample.CodingProblemExampleService;
import com.team1159ers.coffee_coder_db.service.codingproblemtestcase.CodingProblemTestCaseService;
import com.team1159ers.coffee_coder_db.service.dailyexercise.DailyExerciseService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodingProblemDataInitializer {

  private final DailyExerciseService dailyExerciseService;
  private final CodingProblemService codingProblemService;
  private final CodingProblemExampleService codingProblemExampleService;
  private final CodingProblemTestCaseService codingProblemTestCaseService;

  public CodingProblemDataInitializer(DailyExerciseService dailyExerciseService,
                                      CodingProblemService codingProblemService,
                                      CodingProblemExampleService codingProblemExampleService,
                                      CodingProblemTestCaseService codingProblemTestCaseService) {
    this.dailyExerciseService = dailyExerciseService;
    this.codingProblemService = codingProblemService;
    this.codingProblemExampleService = codingProblemExampleService;
    this.codingProblemTestCaseService = codingProblemTestCaseService;
  }

  /**
   * Saves manually-inputted coding problems if the table does not contain a problem with the same name.
   */
  public void saveCodingProblemData() {
    List<DailyExercise> allDailyExercises = dailyExerciseService.getAllDailyExercises();

    CodingProblem fizzBuzz = new CodingProblem(
        // Problem name
        "FizzBuzz",
        // Difficulty category
        "Easy",
        // Problem description
        "<div>\n" +
            "            <p>Given a positive integer <code className=\"inline-code\">n</code>, create an array of integers\n" +
            "              incrementing by <code className=\"inline-code\">1</code> from <code className=\"inline-code\">1</code> to <code className=\"inline-code\">n</code>.\n" +
            "              Write a program that will return a String array of equal length as the array of integers, where for each integer at\n" +
            "              index <code className=\"inline-code\">i</code>:</p>\n" +
            "            <ul>\n" +
            "              <li>If the integer is divisible by <code className=\"inline-code\">3</code> only, set <code className=\"inline-code\">stringArray[i]</code> to <code className=\"inline-code\">“Fizz”</code>.</li>\n" +
            "              <li>If the integer is divisible by <code className=\"inline-code\">5</code> only, set <code className=\"inline-code\">stringArray[i]</code> to <code className=\"inline-code\">“Buzz”</code>.</li>\n" +
            "              <li>If the integer is divisible by both <code className=\"inline-code\">3</code> and <code className=\"inline-code\">5</code>, set <code className=\"inline-code\">stringArray[i]</code> to <code className=\"inline-code\">“FizzBuzz”</code>.</li>\n" +
            "              <li>Else, store the value as is.</li>\n" +
            "            </ul>\n" +
            "          </div>",
        // Code content
        "class StudentSolver {\n" +
            "\tpublic static String[] solve(int n) {\n" +
            "\t\t/* Your code goes here */\n" +
            "\t}\n" +
            "}");

    fizzBuzz.getDailyExercises().addAll(Arrays.asList(
        allDailyExercises.get(0),
        allDailyExercises.get(1),
        allDailyExercises.get(3),
        allDailyExercises.get(4),
        allDailyExercises.get(5)));

    List<CodingProblemExample> fizzBuzzExamples = new ArrayList<>() {{
      add(new CodingProblemExample(
          fizzBuzz,
          "<code className=\"inline-code\">n = 5</code>",
          "<code className=\"inline-code\">[\"1\", \"2\", \"Fizz\", \"4\", \"Buzz\"]</code>"));
      add(new CodingProblemExample(
          fizzBuzz,
          "<code className=\"inline-code\">n = 15</code>",
          "<code className=\"inline-code\">[\"1\", \"2\", \"Fizz\", \"4\", \"Buzz\", \"Fizz\", \"7\", \"8\", \"Fizz\", \"Buzz\", \"11\", \"Fizz\", \"13\", \"14\", \"FizzBuzz\"]</code>"));
    }};
    List<CodingProblemTestCase> fizzBuzzTestCases = new ArrayList<>() {{
      add(new CodingProblemTestCase(
          fizzBuzz,
          "import java.util.Arrays;\n" +
              "\n" +
              "public class Test {\n" +
              "\tpublic static void main(String[] args) {\n" +
              "\t\tSystem.out.print(Arrays.toString(StudentSolver.solve(5)));\n" +
              "\t}\n" +
              "}",
          "[1, 2, Fizz, 4, Buzz]"));
      add(new CodingProblemTestCase(
          fizzBuzz,
          "import java.util.Arrays;\n" +
              "\n" +
              "public class Test {\n" +
              "\tpublic static void main(String[] args) {\n" +
              "\t\tSystem.out.print(Arrays.toString(StudentSolver.solve(15)));\n" +
              "\t}\n" +
              "}",
          "[1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz]"));
    }};

    saveIfNewCodingProblem(fizzBuzz, fizzBuzzExamples, fizzBuzzTestCases);

    CodingProblem coinStacks = new CodingProblem(
        // Problem name
        "Coin Stacks",
        // Difficulty category
        "Hard",
        // Problem description
        "<div>\n" +
            "            <p>Two third-graders, a boy and a girl, are playing King/Queen of the Hill with\n" +
            "              coins in a grid formation. They have labeled the piles with coordinates <code className=\"inline-code\">(i, j)</code>\n" +
            "              where <code className=\"inline-code\">0 &le; i</code>, <code className=\"inline-code\">j &lt; N</code>.\n" +
            "              (These are honors third-graders.) Each pile has a height <code className=\"inline-code\">h(i, j)</code>, a\n" +
            "              non-negative integer that represents the number of coins in the pile. Aside from the coins in the piles,\n" +
            "              the children have an infinite number of spare coins that they can add to any pile.</p>\n" +
            "            <p>The way the game is played is as follows: The girl arranges the piles in the\n" +
            "              shape of a grid with a non-negative number of coins in each pile as described.\n" +
            "              The tallest of the piles is going to hold the Queen of the Hill; that pile will have\n" +
            "              at least as many coins as any other coin stack in the grid. It is now the boy's\n" +
            "              responsibility to add the minimum number of coins to the various piles in the\n" +
            "              grid so that the following restrictions are respected:</p>\n" +
            "            <ol>\n" +
            "              <li>For any given row <code className=\"inline-code\">x</code>, assume that the maximum height of a pile in that\n" +
            "                row occurs in column <code className=\"inline-code\">y</code>. Then the heights of the piles must be decreasing\n" +
            "                outward from column <code className=\"inline-code\">y</code> in row <code className=\"inline-code\">x</code>.\n" +
            "                Mathematically, <code className=\"inline-code\">&forall;i &lt; j &le; y</code>,\n" +
            "                <code className=\"inline-code\">h(x, i) &le; h(x, j)</code> and <code className=\"inline-code\">&forall;i &gt; j &ge; y</code>,\n" +
            "                <code className=\"inline-code\">h(x, i) &le; h(x, j)</code>.</li>\n" +
            "              <li>For any given column <code className=\"inline-code\">x</code>, assume that the maximum height of a pile in that\n" +
            "                column occurs in row <code className=\"inline-code\">y</code>. Then the heights of the piles must be decreasing\n" +
            "                outward from row <code className=\"inline-code\">y</code> in column <code className=\"inline-code\">x</code>.\n" +
            "                Mathematically, <code className=\"inline-code\">&forall;i &lt; j &le; y</code>,\n" +
            "                <code className=\"inline-code\">h(i, x) &le; h(j, x)</code> and <code className=\"inline-code\">&forall;i &gt; j &ge; y</code>,\n" +
            "                <code className=\"inline-code\">h(i, x) &le; h(j, x).</code></li>\n" +
            "            </ol>\n" +
            "            <p>You will write a class <code className=\"inline-code\">StudentSolver</code> that determines the minimum\n" +
            "              number of coins the boy needs to satisfy the grid restrictions, given a grid of coin heights.\n" +
            "              You will also modify the input grid to show how tall the coin stacks should be\n" +
            "              after the boy is finished modifying them.</p>\n" +
            "            <img src=\"https://imgur.com/K73o04C.jpg\"\n" +
            "                 style=\"display: flex; width: 80%; height: 100%; margin: 2em auto;\"\n" +
            "                 alt=\"Multiple stacks of coins.\" />\n" +
            "          </div>",
        // Code content
        "class StudentSolver {\n" +
            "\tpublic static int solve(int[][] grid) {\n" +
            "\t\t/* Your code goes here */\n" +
            "\t}\n" +
            "}");

    coinStacks.getDailyExercises().addAll(Arrays.asList(
        allDailyExercises.get(0),
        allDailyExercises.get(1),
        allDailyExercises.get(2),
        allDailyExercises.get(3),
        allDailyExercises.get(5)));

    List<CodingProblemExample> coinStacksExamples = new ArrayList<>() {{
      add(new CodingProblemExample(
          coinStacks,
          "<div>\n" +
              "              <code className=\"inline-code\">[[1, 2, 5, 3, 3],</code><br/>\n" +
              "              <code className=\"inline-code\"> [2, 4, 1, 5, 1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [2, 1, 1, 5, 2],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1, 1, 5, 1, 3],</code><br/>\n" +
              "              <code className=\"inline-code\"> [4, 3, 1, 5, 1]]</code><br/>\n" +
              "            </div>",
          "<div>\n" +
              "              <code className=\"inline-code\">[[1, 2, 5, 3, 3],</code><br/>\n" +
              "              <code className=\"inline-code\"> [2, 4, 5, 5, 3],</code><br/>\n" +
              "              <code className=\"inline-code\"> [2, 4, 5, 5, 3],</code><br/>\n" +
              "              <code className=\"inline-code\"> [2, 4, 5, 5, 3],</code><br/>\n" +
              "              <code className=\"inline-code\"> [4, 4, 5, 5, 1]]</code><br/>\n" +
              "              <p>(Note: Total coins added = <code className=\"inline-code\">26</code>)</p>\n" +
              "            </div>"));
      add(new CodingProblemExample(
          coinStacks,
          "<div>\n" +
              "              <code className=\"inline-code\">[[ 4,  9,  3,  3,  5,  4,  6,  6, 10,  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [ 1,  3,  6,  3,  3,  6,  3,  8,  4,  4],</code><br/>\n" +
              "              <code className=\"inline-code\"> [ 2,  9,  3,  6, 10,  7,  8,  3, 10,  5],</code><br/>\n" +
              "              <code className=\"inline-code\"> [ 6,  1,  3,  4,  9,  3,  1,  1,  9,  5],</code><br/>\n" +
              "              <code className=\"inline-code\"> [10,  9,  9,  8,  3,  5,  7,  7, 10,  8],</code><br/>\n" +
              "              <code className=\"inline-code\"> [ 2,  7,  8,  7,  4,  6,  8,  3,  1,  9],</code><br/>\n" +
              "              <code className=\"inline-code\"> [ 8,  1,  6,  2,  3,  4,  2,  5,  1,  7],</code><br/>\n" +
              "              <code className=\"inline-code\"> [ 8,  6,  4,  7,  6,  1,  7,  2,  9,  6],</code><br/>\n" +
              "              <code className=\"inline-code\"> [ 8,  3,  8,  6,  9,  6, 10, 10,  8,  3],</code><br/>\n" +
              "              <code className=\"inline-code\"> [10,  7,  9,  1,  6,  2,  2,  1,  2,  6]]</code><br/>\n" +
              "            </div>",
          "<div>\n" +
              "              <code className=\"inline-code\">[[ 4,  9,  9,  9,  9,  9,  9,  9, 10,  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [ 4,  9,  9,  9,  9,  9,  9,  9, 10,  4],</code><br/>\n" +
              "              <code className=\"inline-code\"> [ 4,  9,  9,  9, 10, 10, 10, 10, 10,  5],</code><br/>\n" +
              "              <code className=\"inline-code\"> [ 6,  9,  9,  9, 10, 10, 10, 10, 10,  5],</code><br/>\n" +
              "              <code className=\"inline-code\"> [10, 10, 10, 10, 10, 10, 10, 10, 10,  8],</code><br/>\n" +
              "              <code className=\"inline-code\"> [10, 10, 10, 10, 10, 10, 10, 10,  9,  9],</code><br/>\n" +
              "              <code className=\"inline-code\"> [10, 10, 10, 10, 10, 10, 10, 10,  9,  7],</code><br/>\n" +
              "              <code className=\"inline-code\"> [10, 10, 10, 10, 10, 10, 10, 10,  9,  6],</code><br/>\n" +
              "              <code className=\"inline-code\"> [10, 10, 10, 10, 10, 10, 10, 10,  8,  6],</code><br/>\n" +
              "              <code className=\"inline-code\"> [10,  9,  9,  6,  6,  6,  6,  6,  6,  6]]</code><br/>\n" +
              "              <p>(Note: Total coins added = <code className=\"inline-code\">344</code>)</p>\n" +
              "            </div>"));
    }};
    List<CodingProblemTestCase> coinStacksTestCases = new ArrayList<>() {{
      add(new CodingProblemTestCase(
          coinStacks,
          "[[1, 2, 5, 3, 3], [2, 4, 1, 5, 1], [2, 1, 1, 5, 2], [1, 1, 5, 1, 3], [4, 3, 1, 5, 1]]",
          "[[1, 2, 5, 3, 3], [2, 4, 5, 5, 3], [2, 4, 5, 5, 3], [2, 4, 5, 5, 3], [4, 4, 5, 5, 1]]"));
      add(new CodingProblemTestCase(
          coinStacks,
          "[[4, 9, 3, 3, 5, 4, 6, 6, 10, 1], [1, 3, 6, 3, 3, 6, 3, 8, 4, 4], [2, 9, 3, 6, 10, 7, 8, 3, 10, 5], [6, 1, 3, 4, 9, 3, 1, 1, 9, 5], [10, 9, 9, 8, 3, 5, 7, 7, 10, 8], [2, 7, 8, 7, 4, 6, 8, 3, 1, 9], [8, 1, 6, 2, 3, 4, 2, 5, 1, 7], [8, 6, 4, 7, 6, 1, 7, 2, 9, 6], [8, 3, 8, 6, 9, 6, 10, 10, 8, 3], [10, 7, 9, 1, 6, 2, 2, 1, 2, 6]]",
          "[[4, 9, 9, 9, 9, 9, 9, 9, 10, 1], [4, 9, 9, 9, 9, 9, 9, 9, 10, 4], [4, 9, 9, 9, 10, 10, 10, 10, 10, 5], [6, 9, 9, 9, 10, 10, 10, 10, 10, 5], [10, 10, 10, 10, 10, 10, 10, 10, 10, 8], [10, 10, 10, 10, 10, 10, 10, 10, 9, 9], [10, 10, 10, 10, 10, 10, 10, 10, 9, 7], [10, 10, 10, 10, 10, 10, 10, 10, 9, 6], [10, 10, 10, 10, 10, 10, 10, 10, 8, 6], [10, 9, 9, 6, 6, 6, 6, 6, 6, 6]]"));
    }};

    saveIfNewCodingProblem(coinStacks, coinStacksExamples, coinStacksTestCases);

    CodingProblem foreman = new CodingProblem(
        // Problem name
        "Foreman",
        // Difficulty category
        "Hard",
        // Problem description
        "<div>\n" +
            "            <p>A foreman wants to put together a team to build a monument. The head of HR\n" +
            "              has split the available workforce into two main categories: the <code className=\"inline-code\">A</code>'s\n" +
            "              and the <code className=\"inline-code\">B</code>'s.\n" +
            "              If a worker is in category <code className=\"inline-code\">A</code>, then he is able to complete\n" +
            "              <code className=\"inline-code\">a > 0</code> units of work per hour; similarly, a worker in category\n" +
            "              <code className=\"inline-code\">B</code> can complete <code className=\"inline-code\">b > 0</code> units\n" +
            "              of work per hour.</p>\n" +
            "            <p>The head of HR is both super-competent but extremely forgetful: (super-competent)\n" +
            "              HR has put together a list of potential teams to complete the project\n" +
            "              and the foreman is able to choose any team he wants. A team consists of two\n" +
            "              non-negative integers <code className=\"inline-code\">(x<sub>a</sub>, x<sub>b</sub>)</code> where\n" +
            "              <code className=\"inline-code\">x<sub>a</sub></code> is the number of category\n" +
            "              <code className=\"inline-code\">A</code> people on\n" +
            "              the team and <code className=\"inline-code\">x<sub>b</sub></code> is the number of category\n" +
            "              <code className=\"inline-code\">B</code> people on the team. (extremely\n" +
            "              forgetful) Even though HR has put forward this list of potential teams, HR has\n" +
            "              not informed the foreman of the actual values of <code className=\"inline-code\">a</code> and\n" +
            "              <code className=\"inline-code\">b</code>. The foreman has no knowledge of which of the two is even larger\n" +
            "              than the other. He only knows that because HR is not a total idiot,\n" +
            "              <code className=\"inline-code\">a &ne; b</code>. You may assume that HR is on vacation\n" +
            "              somewhere and is unreachable for this information.</p>\n" +
            "            <p>Given this list of potential teams, the foreman calls you in for a simple\n" +
            "              assignment. Though there are a potentially infinite number of possibilities for\n" +
            "              the values of <code className=\"inline-code\">a</code> and <code className=\"inline-code\">b</code>,\n" +
            "              there are only a finite number of ways to arrange in increasing order the total amount of work that the team\n" +
            "              can accomplish. For example, if the list looks like\n" +
            "              <code className=\"inline-code\">(1, 1), (2, 1), (1, 2)</code>, then there are only two possible\n" +
            "              sorted lists: <code className=\"inline-code\">(1, 1), (2, 1), (1, 2)</code> or\n" +
            "              <code className=\"inline-code\">(1, 1), (1, 2), (2, 1)</code>. The foreman\n" +
            "              wants you to calculate all of the valid possibilities for sorted lists.</p>\n" +
            "            <p>You will write a class <code className=\"inline-code\">StudentSolver</code> that determines all possible\n" +
            "              sorted lists, given the list of possible worker combinations.</p>\n" +
            "            <p>Note: A <code className=\"inline-code\">Pair</code> class is given to you on the side which accepts generic arguments using the\n" +
            "              syntax: <code className=\"inline-code\">Pair myPair = new Pair&lt;&gt;(arg1, arg2);</code>.</p>\n" +
            "            <img src=\"https://imgur.com/BbjHEk4.jpg\"\n" +
            "                 style=\"display: flex; width: 80%; height: 100%; margin: 2em auto;\"\n" +
            "                 alt=\"A confused foreman.\" />\n" +
            "          </div>",
        // Code content
        "class StudentSolver {\n" +
            "\tpublic static ArrayList<ArrayList<Pair<Integer, Integer>>> solve(ArrayList<Pair<Integer, Integer>> list) {\n" +
            "\t\t/* Your code goes here */\n" +
            "\t}\n" +
            "}");

    foreman.getDailyExercises().addAll(Arrays.asList(
        allDailyExercises.get(0),
        allDailyExercises.get(1),
        allDailyExercises.get(3),
        allDailyExercises.get(5),
        allDailyExercises.get(6)));

    List<CodingProblemExample> foremanExamples = new ArrayList<>() {{
      add(new CodingProblemExample(
          foreman,
          "<div>\n" +
              "              <code className=\"inline-code\">[(1, 2), (2, 1), (2, 4), (4, 2)]</code><br/>\n" +
              "            </div>",
          "<div>\n" +
              "                <code className=\"inline-code\">[[(2, 1), (1, 2), (4, 2), (2, 4)],</code><br/>\n" +
              "                <code className=\"inline-code\"> [(1, 2), (2, 1), (2, 4), (4, 2)]]</code><br/>\n" +
              "                <p>(Note: Number of lists = <code className=\"inline-code\">2</code>)</p>\n" +
              "                <p>(Explanation: Notice that the ordering\n" +
              "                  <code className=\"inline-code\">(1, 2), (2, 1), (4, 2), (2, 4)</code> is invalid because if\n" +
              "                  <code className=\"inline-code\">(1, 2) = a + 2b</code> is strictly less than\n" +
              "                  <code className=\"inline-code\">(2, 1) = 2a + b</code>, then\n" +
              "                  <code className=\"inline-code\">(2, 4) = 2(a + 2b)</code>, which is exactly twice as heavy as\n" +
              "                  <code className=\"inline-code\">(1, 2)</code>, must be strictly less heavy than\n" +
              "                  <code className=\"inline-code\">(4, 2) = 2(2a + b)</code>, which is exactly twice as heavy as\n" +
              "                  <code className=\"inline-code\">(2, 1)</code>.</p>\n" +
              "                <p>In other words, with the ordering of\n" +
              "                  <code className=\"inline-code\">(1, 2), (2, 1), (4, 2), (2, 4)</code> we see that\n" +
              "                  <code className=\"inline-code\">(1, 2) = a + 2b</code> is strictly less than\n" +
              "                  <code className=\"inline-code\">(2, 1) = 2a + b</code>. We then have\n" +
              "                  <code className=\"inline-code\">(4, 2) = 4a + 2b = 2(2a + b) = 2 * (2, 1)</code> is strictly less than\n" +
              "                  <code className=\"inline-code\">(2, 4) = 2a + 4b = 2(a + 2b) = 2 * (1, 2)</code>.\n" +
              "                  So if <code className=\"inline-code\">(1, 2)</code> is strictly less than\n" +
              "                  <code className=\"inline-code\">(2, 1)</code>, how can\n" +
              "                  <code className=\"inline-code\">2 * (2, 1)</code> be strictly less than\n" +
              "                  <code className=\"inline-code\">2 * (1, 2)</code>? This would create a contradiction and yield an\n" +
              "                  invalid ordering.)</p>\n" +
              "              </div>"));
      add(new CodingProblemExample(
          foreman,
          "<div>\n" +
              "              <code className=\"inline-code\">[(1, 4), (2, 3), (1, 3), (5, 2), (3, 2)]</code><br/>\n" +
              "            </div>",
          "<div>\n" +
              "              <code className=\"inline-code\">[[(3, 2), (5, 2), (1, 3), (2, 3), (1, 4)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 3), (1, 4), (2, 3), (3, 2), (5, 2)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 3), (3, 2), (2, 3), (1, 4), (5, 2)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(3, 2), (1, 3), (2, 3), (5, 2), (1, 4)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(3, 2), (1, 3), (5, 2), (2, 3), (1, 4)]]</code><br/>\n" +
              "              <p>(Note: Number of lists = <code className=\"inline-code\">5</code>)</p>\n" +
              "            </div>"));
      add(new CodingProblemExample(
          foreman,
          "<div>\n" +
              "              <code className=\"inline-code\">[(4, 2), (10, 4), (8, 1), (1, 1), (9, 10), (10, 9), (3, 2), (3, 9), (3, 10), (4, 5)]</code><br/>\n" +
              "            </div>",
          "<div>\n" +
              "              <code className=\"inline-code\">[[(1, 1), (8, 1), (3, 2), (4, 2), (10, 4), (4, 5), (3, 9), (10, 9), (3, 10), (9, 10)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (3, 2), (3, 9), (3, 10), (4, 2), (4, 5), (8, 1), (9, 10), (10, 4), (10, 9)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (3, 2), (4, 2), (8, 1), (4, 5), (10, 4), (3, 9), (3, 10), (10, 9), (9, 10)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (3, 2), (8, 1), (4, 2), (4, 5), (10, 4), (3, 9), (3, 10), (10, 9), (9, 10)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (8, 1), (3, 2), (4, 2), (4, 5), (10, 4), (3, 9), (3, 10), (10, 9), (9, 10)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (8, 1), (3, 2), (4, 2), (10, 4), (4, 5), (3, 9), (3, 10), (10, 9), (9, 10)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (3, 2), (4, 2), (4, 5), (3, 9), (3, 10), (8, 1), (10, 4), (9, 10), (10, 9)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (3, 2), (4, 2), (4, 5), (8, 1), (3, 9), (3, 10), (10, 4), (9, 10), (10, 9)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (3, 2), (4, 2), (8, 1), (4, 5), (3, 9), (10, 4), (3, 10), (10, 9), (9, 10)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (3, 2), (4, 2), (3, 9), (4, 5), (3, 10), (8, 1), (10, 4), (9, 10), (10, 9)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (3, 2), (4, 2), (4, 5), (3, 9), (8, 1), (3, 10), (10, 4), (9, 10), (10, 9)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (3, 2), (4, 2), (3, 9), (3, 10), (4, 5), (8, 1), (10, 4), (9, 10), (10, 9)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (3, 2), (4, 2), (8, 1), (4, 5), (3, 9), (3, 10), (10, 4), (10, 9), (9, 10)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (3, 2), (4, 2), (3, 9), (3, 10), (4, 5), (8, 1), (9, 10), (10, 4), (10, 9)],</code><br/>\n" +
              "              <code className=\"inline-code\"> [(1, 1), (3, 2), (3, 9), (4, 2), (3, 10), (4, 5), (8, 1), (9, 10), (10, 4), (10, 9)]]</code><br/>\n" +
              "              <p>(Note: Number of lists = <code className=\"inline-code\">15</code>)</p>\n" +
              "            </div>"));
    }};
    List<CodingProblemTestCase> foremanTestCases = new ArrayList<>() {{
      add(new CodingProblemTestCase(
          foreman,
          "[(1,2), (2,1), (2,4), (4,2)]",
          "[[(1, 2), (2, 1), (2, 4), (4, 2)], [(2, 1), (1, 2), (4, 2), (2, 4)]]"));
      add(new CodingProblemTestCase(
          foreman,
          "[(1, 4), (2, 3), (1, 3), (5, 2), (3, 2)]",
          "[[(3, 2), (5, 2), (1, 3), (2, 3), (1, 4)], [(1, 3), (1, 4), (2, 3), (3, 2), (5, 2)], [(1, 3), (3, 2), (2, 3), (1, 4), (5, 2)], [(3, 2), (1, 3), (2, 3), (5, 2), (1, 4)], [(3, 2), (1, 3), (5, 2), (2, 3), (1, 4)]]"));
      add(new CodingProblemTestCase(
          foreman,
          "[(4, 2), (10, 4), (8, 1), (1, 1), (9, 10), (10, 9), (3, 2), (3, 9), (3, 10), (4, 5)]",
          "[[(1, 1), (8, 1), (3, 2), (4, 2), (10, 4), (4, 5), (3, 9), (10, 9), (3, 10), (9, 10)], [(1, 1), (3, 2), (3, 9), (3, 10), (4, 2), (4, 5), (8, 1), (9, 10), (10, 4), (10, 9)], [(1, 1), (3, 2), (4, 2), (8, 1), (4, 5), (10, 4), (3, 9), (3, 10), (10, 9), (9, 10)], [(1, 1), (3, 2), (8, 1), (4, 2), (4, 5), (10, 4), (3, 9), (3, 10), (10, 9), (9, 10)], [(1, 1), (8, 1), (3, 2), (4, 2), (4, 5), (10, 4), (3, 9), (3, 10), (10, 9), (9, 10)], [(1, 1), (8, 1), (3, 2), (4, 2), (10, 4), (4, 5), (3, 9), (3, 10), (10, 9), (9, 10)], [(1, 1), (3, 2), (4, 2), (4, 5), (3, 9), (3, 10), (8, 1), (10, 4), (9, 10), (10, 9)], [(1, 1), (3, 2), (4, 2), (4, 5), (8, 1), (3, 9), (3, 10), (10, 4), (9, 10), (10, 9)], [(1, 1), (3, 2), (4, 2), (8, 1), (4, 5), (3, 9), (10, 4), (3, 10), (10, 9), (9, 10)], [(1, 1), (3, 2), (4, 2), (3, 9), (4, 5), (3, 10), (8, 1), (10, 4), (9, 10), (10, 9)], [(1, 1), (3, 2), (4, 2), (4, 5), (3, 9), (8, 1), (3, 10), (10, 4), (9, 10), (10, 9)], [(1, 1), (3, 2), (4, 2), (3, 9), (3, 10), (4, 5), (8, 1), (10, 4), (9, 10), (10, 9)], [(1, 1), (3, 2), (4, 2), (8, 1), (4, 5), (3, 9), (3, 10), (10, 4), (10, 9), (9, 10)], [(1, 1), (3, 2), (4, 2), (3, 9), (3, 10), (4, 5), (8, 1), (9, 10), (10, 4), (10, 9)], [(1, 1), (3, 2), (3, 9), (4, 2), (3, 10), (4, 5), (8, 1), (9, 10), (10, 4), (10, 9)]]"));
    }};

    saveIfNewCodingProblem(foreman, foremanExamples, foremanTestCases);

    CodingProblem logician = new CodingProblem(
        // Problem name
        "Logician",
        // Difficulty category
        "Hard",
        // Problem description
        " <div>\n" +
            "              <p>A logician is examining a sequence of logical statements in order to create an\n" +
            "                example of an algorithm for one of his classes. The statements are numbered\n" +
            "                from <code className=\"inline-code\">0</code> to <code className=\"inline-code\">N - 1</code>,\n" +
            "                and for any given pair of statements, they are either contradictory\n" +
            "                or non-contradictory. For the purposes of his example, he requires a subset\n" +
            "                <code className=\"inline-code\">S</code> of statements (taken from his original list of\n" +
            "                <code className=\"inline-code\">N</code>) such that each statement in\n" +
            "                <code className=\"inline-code\">S</code> has at least <code className=\"inline-code\">k<sub>1</sub></code> other\n" +
            "                statements in <code className=\"inline-code\">S</code> that are non-contradictory and\n" +
            "                <code className=\"inline-code\">k<sub>2</sub></code> other statements ins\n" +
            "                <code className=\"inline-code\">S</code> that are contradictory. (You should not think of a statement as beings\n" +
            "                contradictory nor non-contradictory to itself.) The logician's goal is to determine, among all possible sets\n" +
            "                <code className=\"inline-code\">S</code>, the largest.</p>\n" +
            "              <p>Input will be a matrix of <code className=\"inline-code\">boolean</code> values, a value fors\n" +
            "                <code className=\"inline-code\">k<sub>1</sub></code>, and a value fors\n" +
            "                <code className=\"inline-code\">k<sub>2</sub></code>. If the values\n" +
            "                <code className=\"inline-code\">[i][j]</code> in the matrix is\n" +
            "                <code className=\"inline-code\">true</code> (respectively <code className=\"inline-code\">false</code>), then\n" +
            "                statement <code className=\"inline-code\">i</code> is not (respectively is) contradictory to statement\n" +
            "                <code className=\"inline-code\">j</code>.</p>\n" +
            "              <p>Your output will be a set of integers that represent the set <code className=\"inline-code\">S</code> of\n" +
            "                statements that you have chosen. It should be the largest among all possible sets that\n" +
            "                satisfy the professor's requirements.</p>\n" +
            "              <p><u>Important note</u>: The diagonal entries in the matrix can essentially be ignored\n" +
            "                for the purposes of this problem.</p>\n" +
            "              <img src=\"https://imgur.com/9JyoZKN.jpg\"\n" +
            "                   style=\"display: flex; width: 80%; height: 100%; margin: 2em auto;\"\n" +
            "                   alt=\"Robert Smullyan - inventor of knights and knaves puzzles and improver\n" +
            "                   of Godel's incompleteness theorem.\" />\n" +
            "            </div>",
        // Code content
        "class StudentSolver {\n" +
            "\tpublic static HashSet<Integer> solve(boolean[][] m, int k1x, int k2x) {\n" +
            "\t\t/* Your code goes here */\n" +
            "\t}\n" +
            "}");

    logician.getDailyExercises().addAll(Arrays.asList(
        allDailyExercises.get(0),
        allDailyExercises.get(1),
        allDailyExercises.get(2),
        allDailyExercises.get(3),
        allDailyExercises.get(5),
        allDailyExercises.get(7),
        allDailyExercises.get(8)));

    List<CodingProblemExample> logicianExamples = new ArrayList<>() {{
      add(new CodingProblemExample(
          logician,
          "<div>\n" +
              "              <code className=\"inline-code\">[[0  0  1  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [0  1  1  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  0  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  1  0  0],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  1  0  0]],</code><br/>\n" +
              "              <code className=\"inline-code\">k<sub>1</sub> = 2,</code><br/>\n" +
              "              <code className=\"inline-code\">k<sub>2</sub> = 1</code>\n" +
              "            </div>",
          "<div>\n" +
              "                <code className=\"inline-code\">[0, 1, 3, 4]</code>\n" +
              "                <p>(Explanation: Let each subsequent integer represent the statement at index\n" +
              "                  <code className=\"inline-code\">i</code>. <code className=\"inline-code\">0</code> does not contradict\n" +
              "                  <code className=\"inline-code\">3</code> and <code className=\"inline-code\">4</code> but does\n" +
              "                  contradict <code className=\"inline-code\">1</code>.\n" +
              "                  <code className=\"inline-code\">1</code> does not contradict <code className=\"inline-code\">3</code> and\n" +
              "                  <code className=\"inline-code\">4</code> but does contradict <code className=\"inline-code\">0</code>.\n" +
              "                  <code className=\"inline-code\">3</code> does not contradict <code className=\"inline-code\">0</code> and\n" +
              "                  <code className=\"inline-code\">1</code> but does contradict <code className=\"inline-code\">4</code>.\n" +
              "                  <code className=\"inline-code\">4</code> does not contradict <code className=\"inline-code\">0</code> and\n" +
              "                  <code className=\"inline-code\">1</code> but does contradict <code className=\"inline-code\">3</code>.)</p>\n" +
              "              </div>"));
      add(new CodingProblemExample(
          logician,
          "<div>\n" +
              "              <code className=\"inline-code\">[[0  1  1  1  1  1  1  0  1  1  1  1  1  1  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  0  1  0  1  1  1  1  1  1  0  1  1  1  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  0  1  1  1  1  1  1  1  1  1  0  1  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  0  1  0  1  1  1  1  1  1  1  1  1  1  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  1  1  0  1  0  1  1  1  1  1  1  1  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  1  1  1  0  1  1  1  1  1  1  1  1  0  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  1  1  0  1  0  1  1  1  1  1  1  1  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [0  1  1  1  1  1  1  0  1  0  1  1  1  1  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  1  1  1  1  1  1  0  1  1  1  1  1  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  1  1  1  1  1  0  1  0  1  0  1  1  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  0  1  1  1  1  1  1  1  1  0  1  1  1  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  1  1  1  1  1  1  1  0  1  0  1  0  0  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  0  1  1  1  1  1  1  1  1  1  0  1  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  1  1  1  1  1  1  1  1  1  0  1  0  1  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  1  1  1  0  1  1  1  1  1  0  1  1  0  1],</code><br/>\n" +
              "              <code className=\"inline-code\"> [1  1  1  1  1  1  1  1  1  1  1  1  1  1  1  0],</code><br/>\n" +
              "              <code className=\"inline-code\">k<sub>1</sub> = 1,</code><br/>\n" +
              "              <code className=\"inline-code\">k<sub>2</sub> = 1</code>\n" +
              "            </div>",
          "<div>\n" +
              "              <code className=\"inline-code\">[0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14]</code>\n" +
              "            </div>"));
    }};
    List<CodingProblemTestCase> logicianTestCases = new ArrayList<>() {{
      add(new CodingProblemTestCase(
          logician,
          "[[0  0  1  1  1], [0  1  1  1  1], [1  1  0  1  1], [1  1  1  0  0], [1  1  1  0  0]], 2, 1",
          "[0, 1, 3, 4]"));
      add(new CodingProblemTestCase(
          logician,
          "[[0  1  1  1  1  1  1  0  1  1  1  1  1  1  1  1], [1  0  1  0  1  1  1  1  1  1  0  1  1  1  1  1], [1  1  0  1  1  1  1  1  1  1  1  1  0  1  1  1], [1  0  1  0  1  1  1  1  1  1  1  1  1  1  1  1], [1  1  1  1  0  1  0  1  1  1  1  1  1  1  1  1], [1  1  1  1  1  0  1  1  1  1  1  1  1  1  0  1], [1  1  1  1  0  1  0  1  1  1  1  1  1  1  1  1], [0  1  1  1  1  1  1  0  1  0  1  1  1  1  1  1], [1  1  1  1  1  1  1  1  0  1  1  1  1  1  1  1], [1  1  1  1  1  1  1  0  1  0  1  0  1  1  1  1], [1  0  1  1  1  1  1  1  1  1  0  1  1  1  1  1], [1  1  1  1  1  1  1  1  1  0  1  0  1  0  0  1], [1  1  0  1  1  1  1  1  1  1  1  1  0  1  1  1], [1  1  1  1  1  1  1  1  1  1  1  0  1  0  1  1], [1  1  1  1  1  0  1  1  1  1  1  0  1  1  0  1], [1  1  1  1  1  1  1  1  1  1  1  1  1  1  1  0], 1, 1",
          "[0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14]"));
    }};

    saveIfNewCodingProblem(logician, logicianExamples, logicianTestCases);

    CodingProblem alienChess = new CodingProblem(
        // Problem name
        "Alien Chess",
        // Difficulty category
        "Hard",
        // Problem description
        "<div>\n" +
            "            <p>On the planet Xeebo, you are given a chess board represented as positive integers within an <code className=\"inline-code\">m</code> × <code className=\"inline-code\">n</code> grid.\n" +
            "              To play alien chess, the game's rules are as follows:</p>\n" +
            "            <ul>\n" +
            "              <li>Any given board has exactly one of either <code className=\"inline-code\">m</code> or <code className=\"inline-code\">n</code> with\n" +
            "                a length within the range <code className=\"inline-code\">[2, 7]</code>, with the other length being large.</li>\n" +
            "              <li>The first player assigns a positive integer value to each square of the board.\n" +
            "                The other player then places pieces on the board so as to maximize the sum of the scores on the squares.</li>\n" +
            "              <li>No piece can be placed on a square that is immediately vertical or horizontal to another piece.</li>\n" +
            "              <li>Chess pieces cannot be placed over an occupied square.</li>\n" +
            "            </ul>\n" +
            "            <p>You are playing this game with a Xeebo who assigns the integers to the board, so you will be placing the\n" +
            "              chess pieces. Your goal is to write a program that can optimally determine the best place to put your\n" +
            "              pieces. You will return an <code className=\"inline-code\">ArrayList</code> of the positions that you choose. (Board positions\n" +
            "              are <code className=\"inline-code\">(row, column)</code>and start counting from <code className=\"inline-code\">0</code>. Repeated\n" +
            "              positions will be counted incorrect.)</p>\n" +
            "            <p>Note: A <code className=\"inline-code\">Pair</code> class is given to you on the side which accepts generic arguments\n" +
            "              using the syntax: <code className=\"inline-code\">Pair myPair = new Pair&lt;&gt;(arg1, arg2);</code>.</p>\n" +
            "            <p>HINT: With one of the rows/columns that small, it might be worth it to precompute, given a particular type of\n" +
            "              column placement in column <code className=\"inline-code\">i</code>, all of the possible next column types that\n" +
            "              could be in column <code className=\"inline-code\">i + 1</code>.</p>\n" +
            "            <img src=\"https://imgur.com/kIlbasZ.jpg\"\n" +
            "                 style=\"display: flex; width: 80%; height: 100%; margin: 2em auto;\"\n" +
            "                 alt=\"A sample of alien chess pieces.\" />\n" +
            "          </div>",
        // Code content
        "class StudentSolver {\n" +
            "\tpublic static ArrayList<Pair<Integer, Integer>> solve(int[][] board) {\n" +
            "\t\t/* Your code goes here */\n" +
            "\t}\n" +
            "}");

    alienChess.getDailyExercises().addAll(Arrays.asList(
        allDailyExercises.get(0),
        allDailyExercises.get(1),
        allDailyExercises.get(2),
        allDailyExercises.get(3),
        allDailyExercises.get(5),
        allDailyExercises.get(6),
        allDailyExercises.get(9),
        allDailyExercises.get(10)));

    List<CodingProblemExample> alienChessExamples = new ArrayList<>() {{
      add(new CodingProblemExample(
          alienChess,
          "<div>\n" +
              "              <code className=\"inline-code\">[[35, 90, 54, 62, 62, 69],</code><br/>\n" +
              "              <code className=\"inline-code\"> [89, 17, 59, 13, 76, 24],</code><br/>\n" +
              "              <code className=\"inline-code\"> [73,  1, 57, 11, 60, 34],</code><br/>\n" +
              "              <code className=\"inline-code\"> [52, 94,  2, 67,  9, 77]]</code>\n" +
              "            </div>",
          "<div>\n" +
              "              <code className=\"inline-code\">[(0, 5), (3, 5), (1, 4), (0, 3), (3, 3), (1, 2), (0, 1), (3, 1), (1, 0)]</code>\n" +
              "              <p>(Note: Sum = 69 + 77 + 76 + 62 + 67 + 59 + 90 + 94 + 89 = 683)</p>\n" +
              "            </div>"));
      add(new CodingProblemExample(
          alienChess,
          "<div>\n" +
              "              <code className=\"inline-code\">[[41, 30, 45, 18, 67],</code><br/>\n" +
              "              <code className=\"inline-code\"> [64,  8, 27, 65, 52],</code><br/>\n" +
              "              <code className=\"inline-code\"> [93, 98, 98,  5, 71],</code><br/>\n" +
              "              <code className=\"inline-code\"> [90, 14, 53, 56, 86],</code><br/>\n" +
              "              <code className=\"inline-code\"> [64, 62, 88, 65, 99],</code><br/>\n" +
              "              <code className=\"inline-code\"> [84, 77, 68, 68, 22],</code><br/>\n" +
              "              <code className=\"inline-code\"> [45, 84, 43, 39, 16]]</code>\n" +
              "            </div>",
          "<div>\n" +
              "              <code className=\"inline-code\">[(0, 0), (2, 0), (4, 0), (6, 0), (1, 1), (3, 1), (5, 1), (0, 2), (2, 2), (4, 2), (6, 2), (1, 3), (3, 3), (5, 3), (0, 4), (2, 4), (4, 4), (6, 4)]</code>\n" +
              "              <p>(Note: Sum = 41 + 93 + 64 + 45 + 8 + 14 + 77 + 45 + 98 + 88 + 43 + 65 + 56 + 68 + 67 + 71 + 99 + 16 = 1058)</p>\n" +
              "            </div>"));
    }};
    List<CodingProblemTestCase> alienChessTestCases = new ArrayList<>() {{
      add(new CodingProblemTestCase(
          alienChess,
          "[[35, 90, 54, 62, 62, 69], [89, 17, 59, 13, 76, 24], [73, 1, 57, 11, 60, 34], [52, 94, 2, 67, 9, 77]]",
          "[(0, 5), (3, 5), (1, 4), (0, 3), (3, 3), (1, 2), (0, 1), (3, 1), (1, 0)]"));
      add(new CodingProblemTestCase(
          alienChess,
          "[[41, 30, 45, 18, 67], [64, 8, 27, 65, 52], [93, 98, 98, 5, 71], [90, 14, 53, 56, 86], [64, 62, 88, 65, 99], [84, 77, 68, 68, 22], [45, 84, 43, 39, 16]]",
          "[(0, 0), (2, 0), (4, 0), (6, 0), (1, 1), (3, 1), (5, 1), (0, 2), (2, 2), (4, 2), (6, 2), (1, 3), (3, 3), (5, 3), (0, 4), (2, 4), (4, 4), (6, 4)]"));
    }};

    saveIfNewCodingProblem(alienChess, alienChessExamples, alienChessTestCases);

    CodingProblem roomba = new CodingProblem(
        // Problem name
        "Roomba",
        // Difficulty category
        "Hard",
        // Problem description
        "<div>\n" +
            "            <p>You are designing an algorithm for the Roomba Minus, an automated floor sweeper. Though the Roomba Minus is a\n" +
            "              major advance in robotic home maintenance, there are some serious design flaws. The engineers are looking to you\n" +
            "              to help them improve the product.</p>\n" +
            "            <ul>\n" +
            "              <li>The Roomba Minus is designed to be placed somewhere along the wall of one side\n" +
            "                of a rectangular room, and will travel to the opposite wall of the room. If you draw the room in such a way that\n" +
            "                one wall lies along the line <code className=\"inline-code\">y = 0</code> and the opposite wall lies along the\n" +
            "                line <code className=\"inline-code\">y = h</code> (for some constant <code className=\"inline-code\">h</code>), then\n" +
            "                the speed of the Roomba Minus in the <code className=\"inline-code\">y</code> direction will always be constant\n" +
            "                (and positive). We will refer to this speed as <code className=\"inline-code\">v</code>.</li>\n" +
            "              <li>The engineers have designed the Roomba Minus so that it can move horizontally\n" +
            "                as well. Given its vertical speed <code className=\"inline-code\">v</code>, it can move horizontally at any speed\n" +
            "                between <code className=\"inline-code\">—v = r</code> and <code className=\"inline-code\">v = r</code> (for some constant\n" +
            "                <code className=\"inline-code\">r</code>) as long as it is still moving vertically while it does so. The horizontal\n" +
            "                speed is not constant and can be changed at any time.</li>\n" +
            "            </ul>\n" +
            "            <p>Given a rectangular room, the Roomba Minus will first identify problematic points in the room and analyze their\n" +
            "              severity. It will then place itself on the south (<code className=\"inline-code\">y = 0</code>) side of the room\n" +
            "              and work its way methodically to the other side of the room (<code className=\"inline-code\">y = h</code>), cleaning\n" +
            "              up the dirty areas of the floor that it reaches as it goes. Given the restrictions that the engineers have designed\n" +
            "              into it, it's clear that the machine will not be able to handle every single problematic point in the room. Your\n" +
            "              goal is to design an algorithm for the Roomba Minus that will (a) specify where on the <code className=\"inline-code\">y = 0</code> axis\n" +
            "              it should start and (b) which problematic points it should clean up on its way to the other side of the room. The\n" +
            "              algorithm should maximize the sum of the severity points of the problematic points in the room that it cleans.</p>\n" +
            "            <p>You will be given <code className=\"inline-code\">r</code> (the ratio of vertical to horizontal speed). Following\n" +
            "              this will be n lines, each containing an <code className=\"inline-code\">x</code> and <code className=\"inline-code\">y</code> coordinate,\n" +
            "              the coordinates of the mess, and a severity <code className=\"inline-code\">s</code> for the mess.</p>\n" +
            "            <p>Your program will list of the messes that your Roomba Minus cleans in chronological order, one per line. Assume\n" +
            "              that the array of messes begins counting at <code className=\"inline-code\">0</code>.</p>\n" +
            "            <p>Note: A <code className=\"inline-code\">Pair</code> class is given to you on the side which accepts generic arguments using the\n" +
            "              syntax: <code className=\"inline-code\">Pair myPair = new Pair&lt;&gt;(arg1, arg2);</code>.</p>\n" +
            "            <p>HINT: It is possible to formulate this problem as the problem of finding the longest path in an appropriately chosen\n" +
            "              directly acyclic graph. Imagine putting an edge from one mess to another if and only if it is possible to reach the\n" +
            "              second from the first.</p>\n" +
            "            <img src=\"https://imgur.com/ZAaAJ8d.jpg\"\n" +
            "                 style=\"display: flex; width: 80%; height: 100%; margin: 2em auto;\"\n" +
            "                 alt=\"A sample of a Roomba.\" />\n" +
            "          </div>",
        // Code content
        "class StudentSolver {\n" +
            "\tpublic static ArrayList<Integer> run(double r, ArrayList<Pair<Pair<Double, Double>, Integer>> mess) {\n" +
            "\t\t/* Your code goes here */\n" +
            "\t}\n" +
            "}");

    roomba.getDailyExercises().addAll(Arrays.asList(
        allDailyExercises.get(0),
        allDailyExercises.get(1),
        allDailyExercises.get(2),
        allDailyExercises.get(3),
        allDailyExercises.get(5),
        allDailyExercises.get(6),
        allDailyExercises.get(9),
        allDailyExercises.get(10)));

    List<CodingProblemExample> roombaExamples = new ArrayList<>() {{
      add(new CodingProblemExample(
          roomba,
          "<div>\n" +
              "              <code className=\"inline-code\">r = 16.7374586913063,</code><br/>\n" +
              "              <code className=\"inline-code\">[((50.53339753651755, 2392.9913262022305), 4),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((91.45350267880721, 2038.5690414517053), 6),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((81.30630354764358, 3002.1564113738523), 9),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((37.16228801441648, 2421.3261154038723), 8),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((16.323410763410276, 696.3036909610697), 1),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((17.558652660436632, 2306.3556734125145), 1),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((30.858752483887464, 751.748794481916), 3),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((30.957197204880696, 944.2879593578829), 8),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((15.042626905954554, 2344.979171359295), 1),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((84.13614550978521, 1310.743768220772), 9)]</code>\n" +
              "            </div>",
          "<div>\n" +
              "              <code className=\"inline-code\">[6, 7, 1, 2]</code>\n" +
              "            </div>"));
      add(new CodingProblemExample(
          roomba,
          "<div>\n" +
              "              <code className=\"inline-code\">r = 4.0970868343508045,</code><br/>\n" +
              "              <code className=\"inline-code\">[((87.21684387143354, 954.8669492163775), 1),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((63.797111370601925, 1599.0719983165416), 10),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((89.38032320395904, 317.4943644252255), 8),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((21.887003117859052, 1623.2066088154834), 4),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((59.72128992893107, 1054.4151412143742), 4),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((65.63722317277708, 715.4761800657637), 4),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((40.945864951008645, 1503.565641866376), 3),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((16.23282429177653, 97.40979265083185), 10),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((92.79901139049763, 908.5599195365235), 7),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((75.07982260480249, 403.74349509408705), 3),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((23.40462172477689, 1260.5966364516757), 7),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((67.75966299566583, 1042.3485806619208), 1),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((87.36334875485645, 407.89154792802043), 9),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((98.30200651337582, 702.0604318874751), 3),</code><br/>\n" +
              "              <code className=\"inline-code\"> ((36.76869215786858, 697.8608975232554), 9)]</code>\n" +
              "            </div>",
          "<div>\n" +
              "              <code className=\"inline-code\">[7, 12, 5, 8, 4, 10, 6, 1]</code>\n" +
              "            </div>"));
    }};
    List<CodingProblemTestCase> roombaTestCases = new ArrayList<>() {{
      add(new CodingProblemTestCase(
          roomba,
          "[[35, 90, 54, 62, 62, 69], [89, 17, 59, 13, 76, 24], [73, 1, 57, 11, 60, 34], [52, 94, 2, 67, 9, 77]]",
          "[(0, 5), (3, 5), (1, 4), (0, 3), (3, 3), (1, 2), (0, 1), (3, 1), (1, 0)]"));
      add(new CodingProblemTestCase(
          roomba,
          "[[41, 30, 45, 18, 67], [64, 8, 27, 65, 52], [93, 98, 98, 5, 71], [90, 14, 53, 56, 86], [64, 62, 88, 65, 99], [84, 77, 68, 68, 22], [45, 84, 43, 39, 16]]",
          "[(0, 0), (2, 0), (4, 0), (6, 0), (1, 1), (3, 1), (5, 1), (0, 2), (2, 2), (4, 2), (6, 2), (1, 3), (3, 3), (5, 3), (0, 4), (2, 4), (4, 4), (6, 4)]"));
    }};

    saveIfNewCodingProblem(roomba, roombaExamples, roombaTestCases);
  }

  /**
   * Persists the problem and its examples and test cases if it's uniquely-named compared to
   * existing problems within the table.
   * @param problem the problem we're attempting to save
   * @param examples the examples for the problem
   * @param testCases the test cases for the problem
   */
  private void saveIfNewCodingProblem(CodingProblem problem, List<CodingProblemExample> examples,
                                      List<CodingProblemTestCase> testCases) {
    if (!problemsContainName(codingProblemService.getAllCodingProblems(), problem.getProblemName())) {
      codingProblemService.saveCodingProblem(problem);
      codingProblemExampleService.saveAllCodingProblemExamples(examples);
      codingProblemTestCaseService.saveAllCodingProblemTestCases(testCases);
      problem.getProblemExamples().addAll(examples);
      problem.getProblemTests().addAll(testCases);
      codingProblemService.saveCodingProblem(problem);
    }
  }

  /**
   * Returns true if any problem within the list contains the input problem name; false otherwise.
   * @param problems the list of problems containing names to compare
   * @param problemName the problem name to compare
   * @return true if the list of problems contains the input category name; false otherwise.
   */
  private boolean problemsContainName(List<CodingProblem> problems, String problemName) {
    for (CodingProblem problem : problems) {
      if (problem.getProblemName().equals(problemName)) {
        return true;
      }
    }

    return false;
  }
}
