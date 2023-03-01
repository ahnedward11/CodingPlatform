package com.team1159ers.coffee_coder_db;

import com.team1159ers.coffee_coder_db.model.dailyexercise.DailyExercise;
import com.team1159ers.coffee_coder_db.model.dailyexerciseexample.DailyExerciseExample;
import com.team1159ers.coffee_coder_db.model.dailyexercisetestcase.DailyExerciseTestCase;
import com.team1159ers.coffee_coder_db.service.dailyexercise.DailyExerciseService;
import com.team1159ers.coffee_coder_db.service.dailyexerciseexample.DailyExerciseExampleService;
import com.team1159ers.coffee_coder_db.service.dailyexercisetestcase.DailyExerciseTestCaseService;

import java.util.ArrayList;
import java.util.List;

public class DailyExerciseDataInitializer {

    private final DailyExerciseService dailyExerciseService;
    private final DailyExerciseExampleService dailyExerciseExampleService;
    private final DailyExerciseTestCaseService dailyExerciseTestCaseService;

    public DailyExerciseDataInitializer(DailyExerciseService dailyExerciseService,
                                        DailyExerciseExampleService dailyExerciseExampleService,
                                        DailyExerciseTestCaseService dailyExerciseTestCaseService) {
        this.dailyExerciseService = dailyExerciseService;
        this.dailyExerciseExampleService = dailyExerciseExampleService;
        this.dailyExerciseTestCaseService = dailyExerciseTestCaseService;
    }

    /**
     * Saves manually-inputted daily exercises if the table does not contain an exercise with the same name.
     */
    public void saveDailyExerciseData() {
        DailyExercise conditions = new DailyExercise(
            // Category
            "Conditions",
            // Exercise description
            "<div>" +
                "<p>Given two integers, return <code className={\"inline-code\"}>true</code> if the first integer " +
                "is greater than or equal to the second integer. Return " +
                "<code className={\"inline-code\"}>false</code> otherwise.</div>",
            // Code content
            "class StudentSolver {\n" +
                      "\tpublic static int testConditions(int num1, int num2) {\n" +
                        "\t\t/* Your code goes here */\n" +
                      "\t}\n" +
                    "}");

        List<DailyExerciseExample> conditionsExamples = new ArrayList<>() {{
            add(new DailyExerciseExample(
                conditions,
                "<code className={\"inline-code\"}>num1 = 7, num2 = 9</code>",
                "<code className={\"inline-code\"}>true</code>"));
            add(new DailyExerciseExample(
                conditions,
                "<code className={\"inline-code\"}>num1 = 22, num2 = 13</code>",
                "<code className={\"inline-code\"}>false</code>"));
            add(new DailyExerciseExample(
                conditions,
                "<code className={\"inline-code\"}>num1 = 328, num2 = 328</code>",
                "<code className={\"inline-code\"}>true</code>"));
        }};
        List<DailyExerciseTestCase> conditionsTestCases = new ArrayList<>() {{
            add(new DailyExerciseTestCase(
                conditions,
                "public class Test {\n" +
                        "public static void main(String[] args) {\n" +
                            "System.out.print(StudentSolver.testConditions(7, 9));\n" +
                        "}\n" +
                    "}",
                "true"));
            add(new DailyExerciseTestCase(
                conditions,
                "public class Test {\n" +
                        "public static void main(String[] args) {\n" +
                            "System.out.print(StudentSolver.testConditions(22, 13));\n" +
                        "}\n" +
                    "}",
                "false"));
            add(new DailyExerciseTestCase(
                conditions,
                "public class Test {\n" +
                        "public static void main(String[] args) {\n" +
                            "System.out.print(StudentSolver.testConditions(328, 328));\n" +
                        "}\n" +
                    "}",
                "true"));
        }};

        saveIfNewDailyExercise(conditions, conditionsExamples, conditionsTestCases);

        DailyExercise arrays = new DailyExercise(
                // Category
                "Arrays",
                // Exercise description
                "<div>" +
                        "<p>Create an array of integers which contains the numbers <code className={\"inline-code\"}>1</code> to " +
                        "<code className={\"inline-code\"}>10</code>. Return the integer array.</p>" +
                        "</div>",
                // Code content
                "class StudentSolver {\n" +
                          "\tpublic static int[] testArrays() {\n" +
                            "\t\t/* Your code goes here */\n" +
                          "\t}\n" +
                       "}");

        List<DailyExerciseExample> arraysExamples = new ArrayList<>() {{
            add(new DailyExerciseExample(
                    arrays,
                    "None",
                    "<code className={\"inline-code\"}>[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]</code>"));
        }};
        List<DailyExerciseTestCase> arraysTestCases = new ArrayList<>() {{
            add(new DailyExerciseTestCase(
                    arrays,
                    "import java.util.Arrays;\n" +
                        
                        "public class Test {\n" +
                            "public static void main(String[] args) {\n" +
                                "System.out.print(Arrays.toString(StudentSolver.testArrays()));\n" +
                            "}\n" +
                        "}",
                    "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"));
        }};

        saveIfNewDailyExercise(arrays, arraysExamples, arraysTestCases);

        DailyExercise twoDArrays = new DailyExercise(
            // Category
            "2D Arrays",
            // Exercise description
            "<div>" +
                "<p>Given an integer <code className={\"inline-code\"}>n</code>, create a two-dimensional array with " +
                "an equal number of columns and rows. Initialize each value to the integer " +
                "<code className={\"inline-code\"}>0</code>, except for the diagonal values, which should be set to " +
                "the integer <code className={\"inline-code\"}>1</code>."+
                "</div>",
            // Code content
            "class StudentSolver {\n" +
                      "public static int test2DArrays(int n) {\n" +
                        "/* Your code goes here */\n" +
                      "}\n" +
                    "}");

        List<DailyExerciseExample> twoDArraysExamples = new ArrayList<>() {{
            add(new DailyExerciseExample(
                twoDArrays,
                "<code className={\"inline-code\"}>n = 3</code>",
                "[[1, 0, 1],\n" +
                     " [0, 1, 0],\n" +
                     " [1, 0, 1]]"));
            add(new DailyExerciseExample(
                twoDArrays,
                "<code className={\"inline-code\"}>n = 5</code>",
                "[[1, 0, 0, 0, 1],\n" +
                     " [0, 1, 0, 1, 0],\n" +
                     " [0, 0, 1, 0, 0],\n" +
                     " [0, 1, 0, 1, 0],\n" +
                     " [1, 0, 0, 0, 1]]"));
            add(new DailyExerciseExample(
                twoDArrays,
                "<code className={\"inline-code\"}>n = 7</code>",
                "[[1, 0, 0, 0, 0, 0, 1],\n" +
                     " [0, 1, 0, 0, 0, 1, 0],\n" +
                     " [0, 0, 1, 0, 1, 0, 0],\n" +
                     " [0, 0, 0, 1, 0, 0, 0],\n" +
                     " [0, 0, 1, 0, 1, 0, 0],\n" +
                     " [0, 1, 0, 0, 0, 1, 0],\n" +
                     " [1, 0, 0, 0, 0, 0, 1]]"));
        }};
        List<DailyExerciseTestCase> twoDArraysTestCases = new ArrayList<>() {{
            add(new DailyExerciseTestCase(
                twoDArrays,
                "import java.util.Arrays;\n" +
                    
                    "public class Test {\n" +
                        "\tpublic static void main(String[] args) {\n" +
                            "\t\tSystem.out.print(Arrays.toString(StudentSolver.test2DArrays(3)));\n" +
                        "\t}\n" +
                    "}",
                "[[1, 0, 1], [0, 1, 0], [1, 0, 1]]"));
            add(new DailyExerciseTestCase(
                twoDArrays,
                "import java.util.Arrays;\n" +
                    
                    "public class Test {\n" +
                        "\tpublic static void main(String[] args) {\n" +
                            "\t\tSystem.out.print(Arrays.toString(StudentSolver.test2DArrays(5)));\n" +
                        "\t}\n" +
                    "}",
                "[[1, 0, 0, 0, 1], [0, 1, 0, 1, 0], [0, 0, 1, 0, 0], [0, 1, 0, 1, 0], [1, 0, 0, 0, 1]]"));
            add(new DailyExerciseTestCase(
                twoDArrays,
                "import java.util.Arrays;\n" +
                    
                    "public class Test {\n" +
                        "\tpublic static void main(String[] args) {\n" +
                            "\t\tSystem.out.print(Arrays.toString(StudentSolver.test2DArrays(7)));\n" +
                        "\t}\n" +
                    "}",
                "[[1, 0, 0, 0, 0, 0, 1], [0, 1, 0, 0, 0, 1, 0], [0, 0, 1, 0, 1, 0, 0], [0, 0, 0, 1, 0, 0, 0], [0, 0, 1, 0, 1, 0, 0], [0, 1, 0, 0, 0, 1, 0], [1, 0, 0, 0, 0, 0, 1]]"));
        }};

        saveIfNewDailyExercise(twoDArrays, twoDArraysExamples, twoDArraysTestCases);

        DailyExercise loops = new DailyExercise(
                // Category
                "Loops",
                // Exercise description
                "<div>" +
                        "<p>Given two integers <code className={\"inline-code\"}>n</code> and <code className={\"inline-code\"}>increment</code>, return " +
                        "a String with integers which contains the numbers<code className={\"inline-code\"}>1</code> up to and including " +
                        "<code className={\"inline-code\"}>n</code>, with each sequential integer element having a step difference equal " +
                        "to <code className={\"inline-code\"}>increment</code>. Follow each element with a " +
                        "whitespace (<code className={\"inline-code\"}>\" \"</code>) character.</p>" +
                        "</div>",
                // Code content
                "class StudentSolver {\n" +
                          "\tpublic static String testLoops(int n, int increment) {\n" +
                            "\t\t/* Your code goes here */\n" +
                          "\t}\n" +
                        "}");

        List<DailyExerciseExample> loopsExamples = new ArrayList<>() {{
            add(new DailyExerciseExample(
                    loops,
                    "<code className={\"inline-code\"}>n = 10, increment = 1</code>",
                    "<code className={\"inline-code\"}>\"1 2 3 4 5 6 7 8 9 10 \"</code>"));
            add(new DailyExerciseExample(
                    loops,
                    "<code className={\"inline-code\"}>n = 15, increment = 2</code>",
                    "<code className={\"inline-code\"}>\"1 3 5 7 9 11 13 15 \"</code>"));
            add(new DailyExerciseExample(
                    loops,
                    "<code className={\"inline-code\"}>n = 50, increment = 5</code>",
                    "<code className={\"inline-code\"}>\"1 6 11 16 21 26 31 36 41 46 \"</code>"));
        }};
        List<DailyExerciseTestCase> loopsTestCases = new ArrayList<>() {{
            add(new DailyExerciseTestCase(
                    loops,
                    "public class Test {\n" +
                            "\tpublic static void main(String[] args) {\n" +
                                "\t\tSystem.out.print(StudentSolver.testLoops(10, 1));\n" +
                            "\t}\n" +
                        "}",
                    "\"1 2 3 4 5 6 7 8 9 10 \""));
            add(new DailyExerciseTestCase(
                    loops,
                "public class Test {\n" +
                        "\tpublic static void main(String[] args) {\n" +
                            "\t\tSystem.out.print(StudentSolver.testLoops(15, 2));\n" +
                        "\t}\n" +
                    "}",
                    "\"1 3 5 7 9 11 13 15 \""));
            add(new DailyExerciseTestCase(
                    loops,
                    "public class Test {\n" +
                            "\tpublic static void main(String[] args) {\n" +
                                "\t\tSystem.out.print(StudentSolver.testLoops(50, 5));\n" +
                            "\t}\n" +
                        "}",
                    "\"1 6 11 16 21 26 31 36 41 4 \""));

        }};

        saveIfNewDailyExercise(loops, loopsExamples, loopsTestCases);

        DailyExercise modularArithmetic = new DailyExercise(
                // Category
                "Modular Arithmetic",
                // Exercise description
                "<div>" +
                        "<p>Given an integer <code className={\"inline-code\"}>n</code>, perform a " +
                        "modulo (<code className={\"inline-code\"}>%</code>) operation on " +
                        "<code className={\"inline-code\"}>n</code>. Use <code className={\"inline-code\"}>10</code> as the modulus operand. " +
                        "Return the remainder as an integer.</p>" +
                        "</div>",
                // Code content
                "class StudentSolver {\n" +
                          "\tpublic static int testModulus(int n) {\n" +
                            "\t\t/* Your code goes here */\n" +
                          "\t}\n" +
                        "}");

        List<DailyExerciseExample> modularArithmeticExamples = new ArrayList<>() {{
            add(new DailyExerciseExample(
                    modularArithmetic,
                    "<code className={\"inline-code\"}>n = 17</code>",
                    "<code className={\"inline-code\"}>7</code>"));
            add(new DailyExerciseExample(
                    modularArithmetic,
                    "<code className={\"inline-code\"}>n = 42</code>",
                    "<code className={\"inline-code\"}>2</code>"));
            add(new DailyExerciseExample(
                    modularArithmetic,
                    "<code className={\"inline-code\"}>n = 359</code>",
                    "<code className={\"inline-code\"}>9</code>"));
        }};
        List<DailyExerciseTestCase> modularArithmeticTestCases = new ArrayList<>() {{
            add(new DailyExerciseTestCase(
                    modularArithmetic,
                    "public class Test {\n" +
                            "\tpublic static void main(String[] args) {\n" +
                                "\t\tSystem.out.print(StudentSolver.testModulus(17));\n" +
                            "\t}\n" +
                        "}",
                    "7"));
            add(new DailyExerciseTestCase(
                    modularArithmetic,
                    "public class Test {\n" +
                            "\tpublic static void main(String[] args) {\n" +
                                "\t\tSystem.out.print(StudentSolver.testModulus(42));\n" +
                            "\t}\n" +
                        "}",
                    "2"));
            add(new DailyExerciseTestCase(
                    modularArithmetic,
                    "public class Test {\n" +
                            "\tpublic static void main(String[] args) {\n" +
                                "\t\tSystem.out.print(StudentSolver.testModulus(359));\n" +
                            "\t}\n" +
                        "}",
                    "9"));
        }};

        saveIfNewDailyExercise(modularArithmetic, modularArithmeticExamples, modularArithmeticTestCases);

        DailyExercise methods = new DailyExercise(
                // Category
                "Methods",
                // Exercise description
                "<div>" +
                        "<p>Given an integer <code className={\"inline-code\"}>n</code>, convert the integer into " +
                        "a String. Then, return the index at which the String <code className={\"inline-code\"}>\"0\"</code> is located " +
                        "within your new String. You may assume that the value <code className={\"inline-code\"}>0</code> always " +
                        "occurs exactly once within the original integer.</p>" +
                        "<p>Note: You may need to use the following methods offered by the String class:</p>" +
                        "<ul>" +
                        "<li key={(listIndex++).toString()}><code className={\"inline-code\"}>String.valueOf(element)</code> - " +
                        "Returns a String representation of <code className={\"inline-code\"}>element</code>.</li> " +
                        "<li key={(listIndex++).toString()}><code className={\"inline-code\"}>str.indexOf(substr)</code> - " +
                        "Returns the first index at which the String <code className={\"inline-code\"}>substr</code> occurs " +
                        "within <code className={\"inline-code\"}>str</code>.</li>" +
                        "</ul>" +
                        "</div>",
                // Code content
                "class StudentSolver {\n" +
                          "\tpublic static int testMethods(int n) {\n" +
                            "\t\t* Your code goes here */\n" +
                          "\t}\n" +
                        "}");

        List<DailyExerciseExample> methodsExamples = new ArrayList<>() {{
            add(new DailyExerciseExample(
                    methods,
                    "<code className={\"inline-code\"}>n = 101</code>",
                    "<code className={\"inline-code\"}>1</code>"));
            add(new DailyExerciseExample(
                    methods,
                    "<code className={\"inline-code\"}>n = 1234567890</code>",
                    "<code className={\"inline-code\"}>9</code>"));
            add(new DailyExerciseExample(
                    methods,
                    "<code className={\"inline-code\"}>n = 848471047148296</code>",
                    "<code className={\"inline-code\"}>6</code>"));
        }};
        List<DailyExerciseTestCase> methodsTestCases = new ArrayList<>() {{
            add(new DailyExerciseTestCase(
                    methods,
                    "public class Test {\n" +
                            "\tpublic static void main(String[] args) {\n" +
                                "\t\tSystem.out.print(StudentSolver.testMethods(101));\n" +
                            "\t}\n" +
                        "}",
                    "1"));
            add(new DailyExerciseTestCase(
                    methods,
                    "public class Test {\n" +
                            "\tpublic static void main(String[] args) {\n" +
                                "\t\tSystem.out.print(StudentSolver.testMethods(1234567890));\n" +
                            "\t}\n" +
                        "}",
                    "9"));
            add(new DailyExerciseTestCase(
                    methods,
                    "public class Test {\n" +
                            "\tpublic static void main(String[] args) {\n" +
                                "\t\tSystem.out.print(StudentSolver.testMethods(848471047148296));\n" +
                            "\t}\n" +
                        "}",
                    "6"));
        }};

        saveIfNewDailyExercise(methods, methodsExamples, methodsTestCases);

        DailyExercise arrayLists = new DailyExercise(
                // Category
                "ArrayLists",
                // Exercise description
                "<div>" +
                        "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore " +
                        "magna aliqua. Tellus integer feugiat scelerisque varius morbi. Ut ornare lectus sit amet. Pretium lectus quam " +
                        "id leo in vitae turpis massa sed. Ullamcorper eget nulla facilisi etiam dignissim diam quis. Sodales ut etiam " +
                        "sit amet nisl purus in mollis. Arcu felis bibendum ut tristique et egestas quis ipsum. Sociis natoque penatibus " +
                        "et magnis. Cras sed felis eget velit aliquet sagittis. Libero justo laoreet sit amet cursus sit amet dictum. " +
                        "Eget mi proin sed libero enim sed faucibus. Pretium viverra suspendisse potenti nullam. At imperdiet dui " +
                        "accumsan sit amet. Commodo odio aenean sed adipiscing diam donec adipiscing. Venenatis cras sed felis eget " +
                        "velit aliquet sagittis id consectetur. Nisi vitae suscipit tellus mauris a diam.</p>" +
                        "</div>",
                // Code content
                "class StudentSolver {\n" +
                          "\tpublic static int testArrayLists(int n) {\n" +
                            "\t\t/* Your code goes here */\n" +
                          "\t}\n" +
                        "}");

        List<DailyExerciseExample> arrayListsExamples = new ArrayList<>() {{
            add(new DailyExerciseExample(
                    arrayLists,
                    "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                    "<code className={\"inline-code\"}>999,999,999</code>"));
            add(new DailyExerciseExample(
                    arrayLists,
                    "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                    "<code className={\"inline-code\"}>999,999,999</code>"));
            add(new DailyExerciseExample(
                    arrayLists,
                    "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                    "<code className={\"inline-code\"}>999,999,999</code>"));
        }};
        List<DailyExerciseTestCase> arrayListsTestCases = new ArrayList<>() {{
            add(new DailyExerciseTestCase(
                    arrayLists,
                    "1,000,000,000",
                    "999,999,999"));
            add(new DailyExerciseTestCase(
                    arrayLists,
                    "1,000,000,000",
                    "999,999,999"));
            add(new DailyExerciseTestCase(
                    arrayLists,
                    "1,000,000,000",
                    "999,999,999"));
        }};

        saveIfNewDailyExercise(arrayLists, arrayListsExamples, arrayListsTestCases);

        DailyExercise sets = new DailyExercise(
            // Category
            "Sets",
            // Exercise description
            "<div>" +
                "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore " +
                "magna aliqua. Tellus integer feugiat scelerisque varius morbi. Ut ornare lectus sit amet. Pretium lectus quam " +
                "id leo in vitae turpis massa sed. Ullamcorper eget nulla facilisi etiam dignissim diam quis. Sodales ut etiam " +
                "sit amet nisl purus in mollis. Arcu felis bibendum ut tristique et egestas quis ipsum. Sociis natoque penatibus " +
                "et magnis. Cras sed felis eget velit aliquet sagittis. Libero justo laoreet sit amet cursus sit amet dictum. " +
                "Eget mi proin sed libero enim sed faucibus. Pretium viverra suspendisse potenti nullam. At imperdiet dui " +
                "accumsan sit amet. Commodo odio aenean sed adipiscing diam donec adipiscing. Venenatis cras sed felis eget " +
                "velit aliquet sagittis id consectetur. Nisi vitae suscipit tellus mauris a diam.</p>" +
                "</div>",
            // Code content
            "class StudentSolver {\n" +
                      "\tpublic static int testSets(int n) {\n" +
                        "\t\t/* Your code goes here */\n" +
                      "\t}\n" +
                    "}");

        List<DailyExerciseExample> setsExamples = new ArrayList<>() {{
            add(new DailyExerciseExample(
                sets,
                "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                "<code className={\"inline-code\"}>999,999,999</code>"));
            add(new DailyExerciseExample(
                sets,
                "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                "<code className={\"inline-code\"}>999,999,999</code>"));
            add(new DailyExerciseExample(
                sets,
                "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                "<code className={\"inline-code\"}>999,999,999</code>"));
        }};
        List<DailyExerciseTestCase> setsTestCases = new ArrayList<>() {{
            add(new DailyExerciseTestCase(
                sets,
                "1,000,000,000",
                "999,999,999"));
            add(new DailyExerciseTestCase(
                sets,
                "1,000,000,000",
                "999,999,999"));
            add(new DailyExerciseTestCase(
                sets,
                "1,000,000,000",
                "999,999,999"));
        }};

        saveIfNewDailyExercise(sets, setsExamples, setsTestCases);

        DailyExercise hashSets = new DailyExercise(
            // Category
            "HashSets",
            // Exercise description
            "<div>" +
                "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore " +
                "magna aliqua. Tellus integer feugiat scelerisque varius morbi. Ut ornare lectus sit amet. Pretium lectus quam " +
                "id leo in vitae turpis massa sed. Ullamcorper eget nulla facilisi etiam dignissim diam quis. Sodales ut etiam " +
                "sit amet nisl purus in mollis. Arcu felis bibendum ut tristique et egestas quis ipsum. Sociis natoque penatibus " +
                "et magnis. Cras sed felis eget velit aliquet sagittis. Libero justo laoreet sit amet cursus sit amet dictum. " +
                "Eget mi proin sed libero enim sed faucibus. Pretium viverra suspendisse potenti nullam. At imperdiet dui " +
                "accumsan sit amet. Commodo odio aenean sed adipiscing diam donec adipiscing. Venenatis cras sed felis eget " +
                "velit aliquet sagittis id consectetur. Nisi vitae suscipit tellus mauris a diam.</p>" +
                "</div>",
            // Code content
            "class StudentSolver {\n" +
                      "\tpublic static int testSets(int n) {\n" +
                        "\t\t/* Your code goes here */\n" +
                      "\t}\n" +
                    "}");

        List<DailyExerciseExample> hashSetsExamples = new ArrayList<>() {{
            add(new DailyExerciseExample(
                hashSets,
                "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                "<code className={\"inline-code\"}>999,999,999</code>"));
            add(new DailyExerciseExample(
                hashSets,
                "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                "<code className={\"inline-code\"}>999,999,999</code>"));
            add(new DailyExerciseExample(
                hashSets,
                "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                "<code className={\"inline-code\"}>999,999,999</code>"));
        }};
        List<DailyExerciseTestCase> hashSetsTestCases = new ArrayList<>() {{
            add(new DailyExerciseTestCase(
                hashSets,
                "1,000,000,000",
                "999,999,999"));
            add(new DailyExerciseTestCase(
                hashSets,
                "1,000,000,000",
                "999,999,999"));
            add(new DailyExerciseTestCase(
                hashSets,
                "1,000,000,000",
                "999,999,999"));
        }};

        saveIfNewDailyExercise(hashSets, hashSetsExamples, hashSetsTestCases);

        DailyExercise dynamicProgramming = new DailyExercise(
                // Category
                "Dynamic Programming",
                // Exercise description
                "<div>" +
                        "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore " +
                        "magna aliqua. Tellus integer feugiat scelerisque varius morbi. Ut ornare lectus sit amet. Pretium lectus quam " +
                        "id leo in vitae turpis massa sed. Ullamcorper eget nulla facilisi etiam dignissim diam quis. Sodales ut etiam " +
                        "sit amet nisl purus in mollis. Arcu felis bibendum ut tristique et egestas quis ipsum. Sociis natoque penatibus " +
                        "et magnis. Cras sed felis eget velit aliquet sagittis. Libero justo laoreet sit amet cursus sit amet dictum. " +
                        "Eget mi proin sed libero enim sed faucibus. Pretium viverra suspendisse potenti nullam. At imperdiet dui " +
                        "accumsan sit amet. Commodo odio aenean sed adipiscing diam donec adipiscing. Venenatis cras sed felis eget " +
                        "velit aliquet sagittis id consectetur. Nisi vitae suscipit tellus mauris a diam.</p>" +
                        "</div>",
                // Code content
                "class StudentSolver {\n" +
                          "\tpublic static int testHashSets(int n) {\n" +
                            "\t\t/* Your code goes here */\n" +
                          "\t}\n" +
                        "}");

        List<DailyExerciseExample> dynamicProgrammingExamples = new ArrayList<>() {{
            add(new DailyExerciseExample(
                    dynamicProgramming,
                    "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                    "<code className={\"inline-code\"}>999,999,999</code>"));
            add(new DailyExerciseExample(
                    dynamicProgramming,
                    "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                    "<code className={\"inline-code\"}>999,999,999</code>"));
            add(new DailyExerciseExample(
                    dynamicProgramming,
                    "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                    "<code className={\"inline-code\"}>999,999,999</code>"));
        }};
        List<DailyExerciseTestCase> dynamieProgrammingTestCases = new ArrayList<>() {{
            add(new DailyExerciseTestCase(
                    dynamicProgramming,
                    "1,000,000,000",
                    "999,999,999"));
            add(new DailyExerciseTestCase(
                    dynamicProgramming,
                    "1,000,000,000",
                    "999,999,999"));
            add(new DailyExerciseTestCase(
                    dynamicProgramming,
                    "1,000,000,000",
                    "999,999,999"));
        }};

        saveIfNewDailyExercise(dynamicProgramming, dynamicProgrammingExamples, dynamieProgrammingTestCases);

        DailyExercise memoization = new DailyExercise(
                // Category
                "Memoization",
                // Exercise description
                "<div>" +
                        "<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore " +
                        "magna aliqua. Tellus integer feugiat scelerisque varius morbi. Ut ornare lectus sit amet. Pretium lectus quam " +
                        "id leo in vitae turpis massa sed. Ullamcorper eget nulla facilisi etiam dignissim diam quis. Sodales ut etiam " +
                        "sit amet nisl purus in mollis. Arcu felis bibendum ut tristique et egestas quis ipsum. Sociis natoque penatibus " +
                        "et magnis. Cras sed felis eget velit aliquet sagittis. Libero justo laoreet sit amet cursus sit amet dictum. " +
                        "Eget mi proin sed libero enim sed faucibus. Pretium viverra suspendisse potenti nullam. At imperdiet dui " +
                        "accumsan sit amet. Commodo odio aenean sed adipiscing diam donec adipiscing. Venenatis cras sed felis eget " +
                        "velit aliquet sagittis id consectetur. Nisi vitae suscipit tellus mauris a diam.</p>" +
                        "</div>",
                // Code content
                "class StudentSolver {\n" +
                          "\tpublic static int testMemoizationint n) {\n" +
                            "\t\t/* Your code goes here */\n" +
                          "\t}\n" +
                        "}");

        List<DailyExerciseExample> memoizationExamples = new ArrayList<>() {{
            add(new DailyExerciseExample(
                    memoization,
                    "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                    "<code className={\"inline-code\"}>999,999,999</code>"));
            add(new DailyExerciseExample(
                    memoization,
                    "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                    "<code className={\"inline-code\"}>999,999,999</code>"));
            add(new DailyExerciseExample(
                    memoization,
                    "<code className={\"inline-code\"}>n = 1,000,000,000</code>",
                    "<code className={\"inline-code\"}>999,999,999</code>"));
        }};
        List<DailyExerciseTestCase> memoizationTestCases = new ArrayList<>() {{
            add(new DailyExerciseTestCase(
                    dynamicProgramming,
                    "1,000,000,000",
                    "999,999,999"));
            add(new DailyExerciseTestCase(
                    dynamicProgramming,
                    "1,000,000,000",
                    "999,999,999"));
            add(new DailyExerciseTestCase(
                    dynamicProgramming,
                    "1,000,000,000",
                    "999,999,999"));
        }};

        saveIfNewDailyExercise(memoization, memoizationExamples, memoizationTestCases);
    }

    /**
     * Returns true if any exercise within the list contains the input category name; false otherwise.
     * @param exercises the list of exercises containing names to compare
     * @param exerciseName the category name to compare
     * @return true if the list of exercises contains the input category name; false otherwise.
     */
    private boolean exercisesContainName(List<DailyExercise> exercises, String exerciseName) {
        for (DailyExercise exercise : exercises) {
            if (exercise.getCategory().equals(exerciseName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Persists the exercise and its examples and test cases if it's uniquely-named compared to
     * existing exercises within the table.
     * @param exercise the exercise we're attempting to save
     * @param examples the examples for the exercise
     * @param testCases the test cases for the exercise
     */
    private void saveIfNewDailyExercise(DailyExercise exercise, List<DailyExerciseExample> examples,
                                        List<DailyExerciseTestCase> testCases) {
        if (!exercisesContainName(dailyExerciseService.getAllDailyExercises(), exercise.getCategory())) {
            dailyExerciseService.saveDailyExercise(exercise);
            dailyExerciseExampleService.saveAllDailyExerciseExamples(examples);
            dailyExerciseTestCaseService.saveAllDailyExerciseTestCases(testCases);
            exercise.getExerciseExamples().addAll(examples);
            exercise.getExerciseTestCases().addAll(testCases);
            dailyExerciseService.saveDailyExercise(exercise);
        }
    }
}
