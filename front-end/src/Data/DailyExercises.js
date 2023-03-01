
import users from "./Accounts";

let listIndex = 0;
let exercises = new Map([
  ["Arrays",
    {
      description:
        <div>
          <p>Create an array of integers which contains the numbers <code className={"inline-code"}>1</code> to
            <code className={"inline-code"}>10</code>. Return the integer array.</p>
        </div>,
      examples: [
        {
          input: "None",
          output: <code className={"inline-code"}>[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]</code>
        }
      ],
      testCases: [
        {
          input: null,
          output: "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"
        }
      ],
      codeTemplate:
        "class StudentSolver {\n" +
        "  public static int[] testArrays() {\n" +
        "    /* Your code goes here */\n" +
        "  }\n" +
        "}",
      solution:
        "class StudentSolver {\n" +
        "  public static int[] testArrays() {\n" +
        "\n" +
        "    return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};\n" +
        "  }\n" +
        "}",
      numOfUsersAttempted: 1,
      numOfUsersSolved: 1,
      usersAttempted: [users[0]],
      usersSolved: [users[0]]
    }],
  ["Loops",
    {
      description:
        <div>
          <p>Given two integers <code className={"inline-code"}>n</code> and <code className={"inline-code"}>increment</code>, return
            a String with integers which contains the numbers<code className={"inline-code"}>1</code> up to and including
            <code className={"inline-code"}>n</code>, with each sequential integer element having a step difference equal
            to <code className={"inline-code"}>increment</code>. Follow each element with a
            whitespace (<code className={"inline-code"}>" "</code>) character.</p>
        </div>,
      examples: [
        {
          input: <code className={"inline-code"}>n = 10, increment = 1</code>,
          output: <code className={"inline-code"}>"1 2 3 4 5 6 7 8 9 10 "</code>
        },
        {
          input: <code className={"inline-code"}>n = 15, increment = 2</code>,
          output: <code className={"inline-code"}>"1 3 5 7 9 11 13 15 "</code>
        },
        {
          input: <code className={"inline-code"}>n = 50, increment = 5</code>,
          output: <code className={"inline-code"}>"1 6 11 16 21 26 31 36 41 46 "</code>
        }
      ],
      testCases: [
        {
          input: "[10, 1]",
          output: "\"1, 2, 3, 4, 5, 6, 7, 8, 9, 10 \""
        },
        {
          input: "[15, 2]",
          output: "\"1 3 5 7 9 11 13 15 \""
        },
        {
          input: "[50, 5]",
          output: "\"1 6 11 16 21 26 31 36 41 4 \""
        }
      ],
      codeTemplate:
        "class StudentSolver {\n" +
        "  public static String testLoops(int n, int increment) {\n" +
        "    /* Your code goes here */\n" +
        "  }\n" +
        "}",
      solution:
        "class StudentSolver {\n" +
        "  public static String testLoops(int n, int increment) {\n" +
        "\n" +
        "    String str = \"\";\n" +
        "\n" +
        "    for (int i = 1; i <= n; n += increment) {\n" +
        "      str += i + \" \";\n" +
        "    }\n" +
        "\n" +
        "    return str;\n" +
        "  }\n" +
        "}",
      numOfUsersAttempted: 1,
      numOfUsersSolved: 0,
      usersAttempted: [users[0]],
      usersSolved: []
    }],
  ["Modular Arithmetic",
    {
      description:
        <div>
          <p>Given an integer <code className={"inline-code"}>n</code>, perform a
            modulo (<code className={"inline-code"}>%</code>) operation on
            <code className={"inline-code"}>n</code>. Use <code className={"inline-code"}>10</code> as the modulus operand.
            Return the remainder as an integer.</p>
        </div>,
      examples: [
        {
          input: <code className={"inline-code"}>n = 17</code>,
          output: <code className={"inline-code"}>7</code>
        },
        {
          input: <code className={"inline-code"}>n = 42</code>,
          output: <code className={"inline-code"}>2</code>
        },
        {
          input: <code className={"inline-code"}>n = 359</code>,
          output: <code className={"inline-code"}>9</code>
        }
      ],
      testCases: [
        {
          input: "17",
          output: "7"
        },
        {
          input: "42",
          output: "2"
        },
        {
          input: "359",
          output: "9"
        }
      ],
      codeTemplate:
        "class StudentSolver {\n" +
        "  public static int testModulus(int n) {\n" +
        "    /* Your code goes here */\n" +
        "  }\n" +
        "}",
      solution:
        "class StudentSolver {\n" +
        "  public static int testModulus(int n) {\n" +
        "\n" +
        "    return n % 10;\n" +
        "  }\n" +
        "}",
      numOfUsersAttempted: 0,
      numOfUsersSolved: 0,
      usersAttempted: [],
      usersSolved: []
    }],
  ["Methods",
    {
      description:
        <div>
          <p>Given an integer <code className={"inline-code"}>n</code>, convert the integer into
            a String. Then, return the index at which the String <code className={"inline-code"}>"0"</code> is located
            within your new String. You may assume that the value <code className={"inline-code"}>0</code> always
            occurs exactly once within the original integer.</p>
          <p>Note: You may need to use the following methods offered by the String class:</p>
          <ul>
            <li key={(listIndex++).toString()}><code className={"inline-code"}>String.valueOf(element)</code> -
              Returns a String representation of <code className={"inline-code"}>element</code>.</li>
            <li key={(listIndex++).toString()}><code className={"inline-code"}>str.indexOf(substr)</code> -
              Returns the first index at which the String <code className={"inline-code"}>substr</code> occurs
              within <code className={"inline-code"}>str</code>.</li>
          </ul>
        </div>,
      examples: [
        {
          input: <code className={"inline-code"}>n = 101</code>,
          output: <code className={"inline-code"}>1</code>
        },
        {
          input: <code className={"inline-code"}>n = 1234567890</code>,
          output: <code className={"inline-code"}>9</code>
        },
        {
          input: <code className={"inline-code"}>n = 848471047148296</code>,
          output: <code className={"inline-code"}>6</code>
        }
      ],
      testCases: [
        {
          input: "101",
          output: "1"
        },
        {
          input: "1234567890",
          output: "9"
        },
        {
          input: "848471047148296",
          output: "6"
        }
      ],
      codeTemplate:
        "class StudentSolver {\n" +
        "  public static int testMethods(int n) {\n" +
        "    /* Your code goes here */\n" +
        "  }\n" +
        "}",
      solution:
        "class StudentSolver {\n" +
        "  public static int testMethods(int n) {\n" +
        "\n" +
        "    String str = String.valueOf(n);\n" +
        "    return str.indexOf(\"0\");\n" +
        "  }\n" +
        "}",
      numOfUsersAttempted: 0,
      numOfUsersSolved: 0,
      usersAttempted: [],
      usersSolved: []
    }],
  ["ArrayLists",
    {
      description:
        <div>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
            magna aliqua. Tellus integer feugiat scelerisque varius morbi. Ut ornare lectus sit amet. Pretium lectus quam
            id leo in vitae turpis massa sed. Ullamcorper eget nulla facilisi etiam dignissim diam quis. Sodales ut etiam
            sit amet nisl purus in mollis. Arcu felis bibendum ut tristique et egestas quis ipsum. Sociis natoque penatibus
            et magnis. Cras sed felis eget velit aliquet sagittis. Libero justo laoreet sit amet cursus sit amet dictum.
            Eget mi proin sed libero enim sed faucibus. Pretium viverra suspendisse potenti nullam. At imperdiet dui
            accumsan sit amet. Commodo odio aenean sed adipiscing diam donec adipiscing. Venenatis cras sed felis eget
            velit aliquet sagittis id consectetur. Nisi vitae suscipit tellus mauris a diam.</p>
        </div>,
      examples: [
        {
          input: <code className={"inline-code"}>n = 1,000,000,000</code>,
          output: <code className={"inline-code"}>999,999,999</code>
        },
        {
          input: <code className={"inline-code"}>n = 1,000,000,000</code>,
          output: <code className={"inline-code"}>999,999,999</code>
        },
        {
          input: <code className={"inline-code"}>n = 1,000,000,000</code>,
          output: <code className={"inline-code"}>999,999,999</code>
        }
      ],
      testCases: [
        {
          input: "1,000,000,000",
          output: "999,999,999"
        },
        {
          input: "1,000,000,000",
          output: "999,999,999"
        },
        {
          input: "1,000,000,000",
          output: "999,999,999"
        }
      ],
      codeTemplate:
        "class StudentSolver {\n" +
        "  public static int testArrayLists(int n) {\n" +
        "    /* Your code goes here */\n" +
        "  }\n" +
        "}",
      solution: "",
      numOfUsersAttempted: 0,
      numOfUsersSolved: 0,
      usersAttempted: [],
      usersSolved: []
    }],
  ["Dynamic Programming",
    {
      description:
        <div>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
            magna aliqua. Tellus integer feugiat scelerisque varius morbi. Ut ornare lectus sit amet. Pretium lectus quam
            id leo in vitae turpis massa sed. Ullamcorper eget nulla facilisi etiam dignissim diam quis. Sodales ut etiam
            sit amet nisl purus in mollis. Arcu felis bibendum ut tristique et egestas quis ipsum. Sociis natoque penatibus
            et magnis. Cras sed felis eget velit aliquet sagittis. Libero justo laoreet sit amet cursus sit amet dictum.
            Eget mi proin sed libero enim sed faucibus. Pretium viverra suspendisse potenti nullam. At imperdiet dui
            accumsan sit amet. Commodo odio aenean sed adipiscing diam donec adipiscing. Venenatis cras sed felis eget
            velit aliquet sagittis id consectetur. Nisi vitae suscipit tellus mauris a diam.</p>        </div>,
      examples: [
        {
          input: <code className={"inline-code"}>n = 1,000,000,000</code>,
          output: <code className={"inline-code"}>999,999,999</code>
        },
        {
          input: <code className={"inline-code"}>n = 1,000,000,000</code>,
          output: <code className={"inline-code"}>999,999,999</code>
        },
        {
          input: <code className={"inline-code"}>n = 1,000,000,000</code>,
          output: <code className={"inline-code"}>999,999,999</code>
        }
      ],
      testCases: [
        {
          input: "1,000,000,000",
          output: "999,999,999"
        },
        {
          input: "1,000,000,000",
          output: "999,999,999"
        },
        {
          input: "1,000,000,000",
          output: "999,999,999"
        }
      ],
      codeTemplate:
        "class StudentSolver {\n" +
        "  public static int testDynamicProgramming(int n) {\n" +
        "    /* Your code goes here */\n" +
        "  }\n" +
        "}",
      solution: "",
      numOfUsersAttempted: 0,
      numOfUsersSolved: 0,
      usersAttempted: [],
      usersSolved: []
    }],
  ["Memoization",
    {
      description:
        <div>
          <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
            magna aliqua. Tellus integer feugiat scelerisque varius morbi. Ut ornare lectus sit amet. Pretium lectus quam
            id leo in vitae turpis massa sed. Ullamcorper eget nulla facilisi etiam dignissim diam quis. Sodales ut etiam
            sit amet nisl purus in mollis. Arcu felis bibendum ut tristique et egestas quis ipsum. Sociis natoque penatibus
            et magnis. Cras sed felis eget velit aliquet sagittis. Libero justo laoreet sit amet cursus sit amet dictum.
            Eget mi proin sed libero enim sed faucibus. Pretium viverra suspendisse potenti nullam. At imperdiet dui
            accumsan sit amet. Commodo odio aenean sed adipiscing diam donec adipiscing. Venenatis cras sed felis eget
            velit aliquet sagittis id consectetur. Nisi vitae suscipit tellus mauris a diam.</p>
        </div>,
      examples: [
        {
          input: <code className={"inline-code"}>n = 1,000,000,000</code>,
          output: <code className={"inline-code"}>999,999,999</code>
        },
        {
          input: <code className={"inline-code"}>n = 1,000,000,000</code>,
          output: <code className={"inline-code"}>999,999,999</code>
        },
        {
          input: <code className={"inline-code"}>n = 1,000,000,000</code>,
          output: <code className={"inline-code"}>999,999,999</code>
        }
      ],
      testCases: [
        {
          input: "1,000,000,000",
          output: "999,999,999"
        },
        {
          input: "1,000,000,000",
          output: "999,999,999"
        },
        {
          input: "1,000,000,000",
          output: "999,999,999"
        }
      ],
      codeTemplate:
        "class StudentSolver {\n" +
        "  public static int testMemoization(int n) {\n" +
        "    /* Your code goes here */\n" +
        "  }\n" +
        "}",
      solution: "",
      numOfUsersAttempted: 0,
      numOfUsersSolved: 0,
      usersAttempted: [],
      usersSolved: []
    }]
]);

export default exercises;
