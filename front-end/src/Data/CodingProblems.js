
import users, { admins } from "./Accounts";

// Preload the image by defining it as a source
const profileImage = new Image();
profileImage.src = "https://icon-library.com/images/anonymous-person-icon/anonymous-person-icon-18.jpg";
profileImage.src = "https://imgur.com/kIlbasZ.jpg";
profileImage.src = "https://imgur.com/ZAaAJ8d.jpg";

let listIndex = 0;
export let problems = [
  {
    name: "FizzBuzz",
    difficulty: "Easy",
    categories: ["Arrays", "Loops", "Modular Arithmetic", "Methods"],
    description:
      <div>
        <p>Given a positive integer <code className="inline-code">n</code>, create an array of integers
          incrementing by <code className="inline-code">1</code> from <code className="inline-code">1</code> to <code className="inline-code">n</code>.
          Write a program that will return a String array of equal length as the array of integers, where for each integer at
          index <code className="inline-code">i</code>:</p>
        <ul>
          <li key={(listIndex++).toString()}>If the integer is divisible by <code className="inline-code">3</code> only, set <code className="inline-code">stringArray[i]</code> to <code className="inline-code">“Fizz”</code>.</li>
          <li key={(listIndex++).toString()}>If the integer is divisible by <code className="inline-code">5</code> only, set <code className="inline-code">stringArray[i]</code> to <code className="inline-code">“Buzz”</code>.</li>
          <li key={(listIndex++).toString()}>If the integer is divisible by both <code className="inline-code">3</code> and <code className="inline-code">5</code>, set <code className="inline-code">stringArray[i]</code> to <code className="inline-code">“FizzBuzz”</code>.</li>
          <li key={(listIndex++).toString()}>Else, store the value as is.</li>
        </ul>
      </div>,
    examples: [
      {
        input: <code className="inline-code">n = 5</code>,
        output: <code className="inline-code">["1", "2", "Fizz", "4", "Buzz"]</code>
      },
      {
        input: <code className="inline-code">n = 15</code>,
        output: <code className="inline-code">["1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "FizzBuzz"]</code>
      },
    ],
    testCases: [
      {
        input: "5",
        output: "[\"1\", \"2\", \"Fizz\", \"4\", \"Buzz\"]"
      },
      {
        input: "15",
        output: "[\"1\", \"2\", \"Fizz\", \"4\", \"Buzz\", \"Fizz\", \"7\", \"8\", \"Fizz\", \"Buzz\", \"11\", \"Fizz\", \"13\", \"14\", \"FizzBuzz\"]"
      },
    ],
    codeTemplate:
      "class StudentSolver {\n" +
      "  public static String[] solve(int n) {\n" +
      "    /* Your code goes here */\n" +
      "  }\n" +
      "}",
    numOfUsersAttempted: 13,
    numOfUsersSolved: 10,
    usersAttempted: [users[0]],
    usersSolved: []
  },
  {
    name: "Coin Stacks",
    difficulty: "Hard",
    categories: ["Arrays", "Loops", "Methods"],
    description:
      <div>
        <p>Two third-graders, a boy and a girl, are playing King/Queen of the Hill with
          coins in a grid formation. They have labeled the piles with coordinates <code className="inline-code">(i, j)</code>
          where <code className="inline-code">0 &le; i</code>, <code className="inline-code">j &lt; N</code>.
          (These are honors third-graders.) Each pile has a height <code className="inline-code">h(i, j)</code>, a
          non-negative integer that represents the number of coins in the pile. Aside from the coins in the piles,
          the children have an infinite number of spare coins that they can add to any pile.</p>
        <p>The way the game is played is as follows: The girl arranges the piles in the
          shape of a grid with a non-negative number of coins in each pile as described.
          The tallest of the piles is going to hold the Queen of the Hill; that pile will have
          at least as many coins as any other coin stack in the grid. It is now the boy's
          responsibility to add the minimum number of coins to the various piles in the
          grid so that the following restrictions are respected:</p>
        <ol>
          <li>For any given row <code className="inline-code">x</code>, assume that the maximum height of a pile in that
            row occurs in column <code className="inline-code">y</code>. Then the heights of the piles must be decreasing
            outward from column <code className="inline-code">y</code> in row <code className="inline-code">x</code>.
            Mathematically, <code className="inline-code">&forall;i &lt; j &le; y</code>,
            <code className="inline-code">h(x, i) &le; h(x, j)</code> and <code className="inline-code">&forall;i &gt; j &ge; y</code>,
            <code className="inline-code">h(x, i) &le; h(x, j)</code>.</li>
          <li>For any given column <code className="inline-code">x</code>, assume that the maximum height of a pile in that
            column occurs in row <code className="inline-code">y</code>. Then the heights of the piles must be decreasing
            outward from row <code className="inline-code">y</code> in column <code className="inline-code">x</code>.
            Mathematically, <code className="inline-code">&forall;i &lt; j &le; y</code>,
            <code className="inline-code">h(i, x) &le; h(j, x)</code> and <code className="inline-code">&forall;i &gt; j &ge; y</code>,
            <code className="inline-code">h(i, x) &le; h(j, x).</code></li>
        </ol>
        <p>You will write a class StudentSolver that determines the minimum number of
          coins the boy needs to satisfy the grid restrictions, given a grid of coin heights.
          You will also modify the input grid to show how tall the coin stacks should be
          after the boy is finished modifying them.</p>
        <img src="https://imgur.com/K73o04C.jpg"
             style="display: flex; width: 80%; height: 100%; margin: 2em auto;"
             alt="Multiple stacks of coins." />
      </div>,
    examples: [
      {
        input:
          <div>
            <code className="inline-code">[[1, 2, 5, 3, 3],</code><br/>
            <code className="inline-code">[2, 4, 1, 5, 1],</code><br/>
            <code className="inline-code">[2, 1, 1, 5, 2],</code><br/>
            <code className="inline-code">[1, 1, 5, 1, 3],</code><br/>
            <code className="inline-code">[4, 3, 1, 5, 1]]</code><br/>
          </div>,
        output:
          <div>
            <code className="inline-code">[[1, 2, 5, 3, 3],</code><br/>
            <code className="inline-code">[2, 4, 5, 5, 3],</code><br/>
            <code className="inline-code">[2, 4, 5, 5, 3],</code><br/>
            <code className="inline-code">[2, 4, 5, 5, 3],</code><br/>
            <code className="inline-code">[4, 4, 5, 5, 1]]</code><br/>
            <p>(Note: Total coins added = <code className="inline-code">26</code>)</p>
          </div>
      },
      {
        input:
          <div>
            <code className="inline-code">[[ 4,  9,  3,  3,  5,  4,  6,  6, 10,  1],</code><br/>
            <code className="inline-code">[ 1,  3,  6,  3,  3,  6,  3,  8,  4,  4],</code><br/>
            <code className="inline-code">[ 2,  9,  3,  6, 10,  7,  8,  3, 10,  5],</code><br/>
            <code className="inline-code">[ 6,  1,  3,  4,  9,  3,  1,  1,  9,  5],</code><br/>
            <code className="inline-code">[10,  9,  9,  8,  3,  5,  7,  7, 10,  8],</code><br/>
            <code className="inline-code">[ 2,  7,  8,  7,  4,  6,  8,  3,  1,  9],</code><br/>
            <code className="inline-code">[ 8,  1,  6,  2,  3,  4,  2,  5,  1,  7],</code><br/>
            <code className="inline-code">[ 8,  6,  4,  7,  6,  1,  7,  2,  9,  6],</code><br/>
            <code className="inline-code">[ 8,  3,  8,  6,  9,  6, 10, 10,  8,  3],</code><br/>
            <code className="inline-code">[10,  7,  9,  1,  6,  2,  2,  1,  2,  6]]</code><br/>
          </div>,
        output:
          <div>
            <code className="inline-code">[[ 4,  9,  9,  9,  9,  9,  9,  9, 10,  1],</code><br/>
            <code className="inline-code">[ 4,  9,  9,  9,  9,  9,  9,  9, 10,  4],</code><br/>
            <code className="inline-code">[ 4,  9,  9,  9, 10, 10, 10, 10, 10,  5],</code><br/>
            <code className="inline-code">[ 6,  9,  9,  9, 10, 10, 10, 10, 10,  5],</code><br/>
            <code className="inline-code">[10, 10, 10, 10, 10, 10, 10, 10, 10,  8],</code><br/>
            <code className="inline-code">[10, 10, 10, 10, 10, 10, 10, 10,  9,  9],</code><br/>
            <code className="inline-code">[10, 10, 10, 10, 10, 10, 10, 10,  9,  7],</code><br/>
            <code className="inline-code">[10, 10, 10, 10, 10, 10, 10, 10,  9,  6],</code><br/>
            <code className="inline-code">[10, 10, 10, 10, 10, 10, 10, 10,  8,  6],</code><br/>
            <code className="inline-code">[10,  9,  9,  6,  6,  6,  6,  6,  6,  6]]</code><br/>
            <p>(Note: Total coins added = <code className="inline-code">344</code>)</p>
          </div>
      },
    ],
    testCases: [
      {
        input: "[[1, 2, 5, 3, 3], [2, 4, 1, 5, 1], [2, 1, 1, 5, 2], [1, 1, 5, 1, 3], [4, 3, 1, 5, 1]]",
        output: "[[1, 2, 5, 3, 3], [2, 4, 5, 5, 3], [2, 4, 5, 5, 3], [2, 4, 5, 5, 3], [4, 4, 5, 5, 1]]"
      },
      {
        input: "[[4, 9, 3, 3, 5, 4, 6, 6, 10, 1], [1, 3, 6, 3, 3, 6, 3, 8, 4, 4], [2, 9, 3, 6, 10, 7, 8, 3, 10, 5], [6, 1, 3, 4, 9, 3, 1, 1, 9, 5], [10, 9, 9, 8, 3, 5, 7, 7, 10, 8], [2, 7, 8, 7, 4, 6, 8, 3, 1, 9], [8, 1, 6, 2, 3, 4, 2, 5, 1, 7], [8, 6, 4, 7, 6, 1, 7, 2, 9, 6], [8, 3, 8, 6, 9, 6, 10, 10, 8, 3], [10, 7, 9, 1, 6, 2, 2, 1, 2, 6]]",
        output: "[[4, 9, 9, 9, 9, 9, 9, 9, 10, 1], [4, 9, 9, 9, 9, 9, 9, 9, 10, 4], [4, 9, 9, 9, 10, 10, 10, 10, 10, 5], [6, 9, 9, 9, 10, 10, 10, 10, 10, 5], [10, 10, 10, 10, 10, 10, 10, 10, 10, 8], [10, 10, 10, 10, 10, 10, 10, 10, 9, 9], [10, 10, 10, 10, 10, 10, 10, 10, 9, 7], [10, 10, 10, 10, 10, 10, 10, 10, 9, 6], [10, 10, 10, 10, 10, 10, 10, 10, 8, 6], [10, 9, 9, 6, 6, 6, 6, 6, 6, 6]]"
      }
    ],
    codeTemplate:
      "public class StudentSolver {\n" +
      "  public static int solve(int[][] grid) {\n" +
      "    /* Your code goes here */\n" +
      "  }\n" +
      "}",
    numOfUsersAttempted: 0,
    numOfUsersSolved: 0,
    usersAttempted: [],
    usersSolved: []
  },
  {
    name: "Foreman",
    difficulty: "Hard",
    categories: ["Loops, Methods, ArrayLists"],
    description:
      <div>
        <p>A foreman wants to put together a team to build a monument. The head of HR
          has split the available workforce into two main categories: the <code className="inline-code">A</code>'s
          and the <code className="inline-code">B</code>'s.
          If a worker is in category <code className="inline-code">A</code>, then he is able to complete
          <code className="inline-code">a > 0</code> units of work per hour; similarly, a worker in category
          <code className="inline-code">B</code> can complete <code className="inline-code">b > 0</code> units
          of work per hour.</p>
        <p>The head of HR is both super-competent but extremely forgetful: (super-
          competent) HR has put together a list of potential teams to complete the project
          and the foreman is able to choose any team he wants. A team consists of two
          non-negative integers <code className="inline-code">(x<sub>a</sub>, x<sub>b</sub>)</code> where
          <code className="inline-code">x<sub>a</sub></code> is the number of category
          <code className="inline-code">A</code> people on
          the team and <code className="inline-code">x<sub>b</sub></code> is the number of category
          <code className="inline-code">B</code> people on the team. (extremely
          forgetful) Even though HR has put forward this list of potential teams, HR has
          not informed the foreman of the actual values of <code className="inline-code">a</code> and
          <code className="inline-code">b</code>. The foreman has no knowledge of which of the two is even larger
          than the other. He only knows that because HR is not a total idiot,
          <code className="inline-code">a &ne; b</code>. You may assume that HR is on vacation
          somewhere and is unreachable for this information.</p>
        <p>Given this list of potential teams, the foreman calls you in for a simple
          assignment. Though there are a potentially infinite number of possibilities for
          the values of <code className="inline-code">a</code> and <code className="inline-code">b</code>,
          there are only a finite number of ways to arrange in increasing order the total amount of work that the team
          can accomplish. For example, if the list looks like <code className="inline-code">(1, 1)</code>,
          <code className="inline-code">(2, 1), (1, 2)</code>, then there are only two possible
          sorted lists: <code className="inline-code">(1, 1)</code>, <code className="inline-code">(2, 1)</code>,
          <code className="inline-code">(1, 2)</code> or <code className="inline-code">(1, 1)</code>,
          <code className="inline-code">(1, 2)</code>, <code className="inline-code">(2, 1)</code>. The foreman
          wants you to calculate all of the valid possibilities for sorted lists.</p>
        <p>You will write a class StudentSolver that determines all possible sorted lists,
          given the list of possible worker combinations.</p>
        <p>Note: A <code className="inline-code">Pair</code> class is given to you on the side which accepts generic arguments using the
          syntax: <code className="inline-code">Pair myPair = new Pair&lt;&gt;(arg1, arg2);</code>.</p>
        <img src="https://imgur.com/BbjHEk4.jpg"
             style="display: flex; width: 80%; height: 100%; margin: 2em auto;"
             alt="A confused foreman." />
      </div>,
    examples: [
      {
        input:
        <div>
          <code className="inline-code">[(1, 2), (2, 1), (2, 4), (4, 2)]</code><br/>
        </div>,
        output:
        <div>
          <code className="inline-code">[[(2, 1), (1, 2), (4, 2), (2, 4)],</code><br/>
          <code className="inline-code">[(1, 2), (2, 1), (2, 4), (4, 2)]]</code><br/>
          <p>(Note: Number of lists: <code className="inline-code">2</code>)</p>
        </div>
      },
      {
        input:
          <div>
            <code className="inline-code">[(1, 4), (2, 3), (1, 3), (5, 2), (3, 2)]</code><br/>
          </div>,
        output:
          <div>
            <code className="inline-code">[[(3, 2), (5, 2), (1, 3), (2, 3), (1, 4)],</code><br/>
            <code className="inline-code">[(1, 3), (1, 4), (2, 3), (3, 2), (5, 2)],</code><br/>
            <code className="inline-code">[(1, 3), (3, 2), (2, 3), (1, 4), (5, 2)],</code><br/>
            <code className="inline-code">[(3, 2), (1, 3), (2, 3), (5, 2), (1, 4)],</code><br/>
            <code className="inline-code">[(3, 2), (1, 3), (5, 2), (2, 3), (1, 4)]]</code><br/>
            <p>(Note: Number of lists: <code className="inline-code">5</code>)</p>
          </div>
      },
      {
        input:
          <div>
            <code className="inline-code">[(4, 2), (10, 4), (8, 1), (1, 1), (9, 10), (10, 9), (3, 2), (3, 9), (3, 10), (4, 5)]</code><br/>
          </div>,
        output:
          <div>
            <code className="inline-code">[[(1, 1), (8, 1), (3, 2), (4, 2), (10, 4), (4, 5), (3, 9), (10, 9), (3, 10), (9, 10)],</code><br/>
            <code className="inline-code">[(1, 1), (3, 2), (3, 9), (3, 10), (4, 2), (4, 5), (8, 1), (9, 10), (10, 4), (10, 9)],</code><br/>
            <code className="inline-code">[(1, 1), (3, 2), (4, 2), (8, 1), (4, 5), (10, 4), (3, 9), (3, 10), (10, 9), (9, 10)],</code><br/>
            <code className="inline-code">[(1, 1), (3, 2), (8, 1), (4, 2), (4, 5), (10, 4), (3, 9), (3, 10), (10, 9), (9, 10)],</code><br/>
            <code className="inline-code">[(1, 1), (8, 1), (3, 2), (4, 2), (4, 5), (10, 4), (3, 9), (3, 10), (10, 9), (9, 10)],</code><br/>
            <code className="inline-code">[(1, 1), (8, 1), (3, 2), (4, 2), (10, 4), (4, 5), (3, 9), (3, 10), (10, 9), (9, 10)],</code><br/>
            <code className="inline-code">[(1, 1), (3, 2), (4, 2), (4, 5), (3, 9), (3, 10), (8, 1), (10, 4), (9, 10), (10, 9)],</code><br/>
            <code className="inline-code">[(1, 1), (3, 2), (4, 2), (4, 5), (8, 1), (3, 9), (3, 10), (10, 4), (9, 10), (10, 9)],</code><br/>
            <code className="inline-code">[(1, 1), (3, 2), (4, 2), (8, 1), (4, 5), (3, 9), (10, 4), (3, 10), (10, 9), (9, 10)],</code><br/>
            <code className="inline-code">[(1, 1), (3, 2), (4, 2), (3, 9), (4, 5), (3, 10), (8, 1), (10, 4), (9, 10), (10, 9)],</code><br/>
            <code className="inline-code">[(1, 1), (3, 2), (4, 2), (4, 5), (3, 9), (8, 1), (3, 10), (10, 4), (9, 10), (10, 9)],</code><br/>
            <code className="inline-code">[(1, 1), (3, 2), (4, 2), (3, 9), (3, 10), (4, 5), (8, 1), (10, 4), (9, 10), (10, 9)],</code><br/>
            <code className="inline-code">[(1, 1), (3, 2), (4, 2), (8, 1), (4, 5), (3, 9), (3, 10), (10, 4), (10, 9), (9, 10)],</code><br/>
            <code className="inline-code">[(1, 1), (3, 2), (4, 2), (3, 9), (3, 10), (4, 5), (8, 1), (9, 10), (10, 4), (10, 9)],</code><br/>
            <code className="inline-code">[(1, 1), (3, 2), (3, 9), (4, 2), (3, 10), (4, 5), (8, 1), (9, 10), (10, 4), (10, 9)]]</code><br/>
            <p>(Note: Number of lists: <code className="inline-code">15</code>)</p>
          </div>
      }
    ],
    testCases: [
      {
        input: "[(1,2), (2,1), (2,4), (4,2)]",
        output: "[[(1, 2), (2, 1), (2, 4), (4, 2)], [(2, 1), (1, 2), (4, 2), (2, 4)]]"
      },
      {
        input: "[(1, 4), (2, 3), (1, 3), (5, 2), (3, 2)]",
        output: "[[(3, 2), (5, 2), (1, 3), (2, 3), (1, 4)], [(1, 3), (1, 4), (2, 3), (3, 2), (5, 2)], [(1, 3), (3, 2), (2, 3), (1, 4), (5, 2)], [(3, 2), (1, 3), (2, 3), (5, 2), (1, 4)], [(3, 2), (1, 3), (5, 2), (2, 3), (1, 4)]]"
      },
      {
        input: "[(4, 2), (10, 4), (8, 1), (1, 1), (9, 10), (10, 9), (3, 2), (3, 9), (3, 10), (4, 5)]",
        output: "[[(1, 1), (8, 1), (3, 2), (4, 2), (10, 4), (4, 5), (3, 9), (10, 9), (3, 10), (9, 10)], [(1, 1), (3, 2), (3, 9), (3, 10), (4, 2), (4, 5), (8, 1), (9, 10), (10, 4), (10, 9)], [(1, 1), (3, 2), (4, 2), (8, 1), (4, 5), (10, 4), (3, 9), (3, 10), (10, 9), (9, 10)], [(1, 1), (3, 2), (8, 1), (4, 2), (4, 5), (10, 4), (3, 9), (3, 10), (10, 9), (9, 10)], [(1, 1), (8, 1), (3, 2), (4, 2), (4, 5), (10, 4), (3, 9), (3, 10), (10, 9), (9, 10)], [(1, 1), (8, 1), (3, 2), (4, 2), (10, 4), (4, 5), (3, 9), (3, 10), (10, 9), (9, 10)], [(1, 1), (3, 2), (4, 2), (4, 5), (3, 9), (3, 10), (8, 1), (10, 4), (9, 10), (10, 9)], [(1, 1), (3, 2), (4, 2), (4, 5), (8, 1), (3, 9), (3, 10), (10, 4), (9, 10), (10, 9)], [(1, 1), (3, 2), (4, 2), (8, 1), (4, 5), (3, 9), (10, 4), (3, 10), (10, 9), (9, 10)], [(1, 1), (3, 2), (4, 2), (3, 9), (4, 5), (3, 10), (8, 1), (10, 4), (9, 10), (10, 9)], [(1, 1), (3, 2), (4, 2), (4, 5), (3, 9), (8, 1), (3, 10), (10, 4), (9, 10), (10, 9)], [(1, 1), (3, 2), (4, 2), (3, 9), (3, 10), (4, 5), (8, 1), (10, 4), (9, 10), (10, 9)], [(1, 1), (3, 2), (4, 2), (8, 1), (4, 5), (3, 9), (3, 10), (10, 4), (10, 9), (9, 10)], [(1, 1), (3, 2), (4, 2), (3, 9), (3, 10), (4, 5), (8, 1), (9, 10), (10, 4), (10, 9)], [(1, 1), (3, 2), (3, 9), (4, 2), (3, 10), (4, 5), (8, 1), (9, 10), (10, 4), (10, 9)]]"
      }
    ],
    codeTemplate:
      "public class StudentSolver {\n" +
      "  public static ArrayList<ArrayList<Pair<Integer, Integer>>> solve(ArrayList<Pair<Integer, Integer>> list) {" +
      "    /* Your code goes here */\n" +
      "  }\n" +
      "}",
    numOfUsersAttempted: 0,
    numOfUsersSolved: 0,
    usersAttempted: [],
    usersSolved: []
  },
  {
    name: "Logician",
    difficulty: "Hard",
    categories: ["Conditions, Arrays, 2D Arrays, Loops, Methods, Sets, HashSets"],
    description:
      <div>
        <p>A logician is examining a sequence of logical statements in order to create an
          example of an algorithm for one of his classes. The statements are numbered
          from <code className="inline-code">0</code> to <code className="inline-code">N - 1</code>,
          and for any given pair of statements, they are either contradictory
          or non-contradictory. For the purposes of his example, he requires a subset
          <code className="inline-code">S</code> of statements (taken from his original list of
          <code className="inline-code">N</code>) such that each statement in
          <code className="inline-code">S</code> has at least <code className="inline-code">k<sub>1</sub></code> other
          statements in <code className="inline-code">S</code> that are non-contradictory and
          <code className="inline-code">k<sub>2</sub></code> other statements in
          <code className="inline-code">S</code> that are contradictory. (You should not think of a statement as being
          contradictory nor non-contradictory to itself.) The logician's goal is to determine, among all possible sets
          <code className="inline-code">S</code>, the largest.</p>
        <p>Input will be a matrix of <code className="inline-code">boolean</code> values, a value for
          <code className="inline-code">k<sub>1</sub></code>, and a value for
          <code className="inline-code">k<sub>2</sub></code>. If the value
          <code className="inline-code">[i][j]</code> in the matrix is
          <code className="inline-code">true</code> (respectively <code className="inline-code">false</code>), then
          statement <code className="inline-code">i</code> is not (respectively is) contradictory to statement
          <code className="inline-code">j</code>.</p>
        <p>Your output will be a set of integers that represent the set <code className="inline-code">S</code> of
          statements that you have chosen. It should be the largest among all possible sets that
          satisfy the professor's requirements.</p>
        <p><u>Important note</u>: The diagonal entries in the matrix can essentially be ignored
          for the purposes of this problem.</p>
        <img src="https://imgur.com/9JyoZKN.jpg"
             style="display: flex; width: 80%; height: 100%; margin: 2em auto;"
             alt="A confused foreman." />
      </div>,
    examples: [
      {
        input:
          <div>
            <code className="inline-code">[[0  0  1  1  1],</code><br/>
            <code className="inline-code"> [0  1  1  1  1],</code><br/>
            <code className="inline-code"> [1  1  0  1  1],</code><br/>
            <code className="inline-code"> [1  1  1  0  0],</code><br/>
            <code className="inline-code"> [1  1  1  0  0]],</code><br/>
            <code className="inline-code">k<sub>1</sub> = 2,</code><br/>
            <code className="inline-code">k<sub>2</sub> = 1</code>
          </div>,
        output:
          <div>
            <code className="inline-code">[0, 1, 3, 4]</code>
            <p>(Note: Let each subsequent integer represent the statement at index
              <code className="inline-code">i</code>. 0 does not contradict 3 and 4 but does contradict 1.
              1 does not contradict 3 and 4 but does contradict 0. 3 does not contradict 0 and 1 but
              does contradict 4. 4 does not contradict 0 and 1 but does contradict 3.)</p>
          </div>
      },
      {
        input:
          <div>
            <code className="inline-code">[[0  1  1  1  1  1  1  0  1  1  1  1  1  1  1  1],</code><br/>
            <code className="inline-code"> [1  0  1  0  1  1  1  1  1  1  0  1  1  1  1  1],</code><br/>
            <code className="inline-code"> [1  1  0  1  1  1  1  1  1  1  1  1  0  1  1  1],</code><br/>
            <code className="inline-code"> [1  0  1  0  1  1  1  1  1  1  1  1  1  1  1  1],</code><br/>
            <code className="inline-code"> [1  1  1  1  0  1  0  1  1  1  1  1  1  1  1  1],</code><br/>
            <code className="inline-code"> [1  1  1  1  1  0  1  1  1  1  1  1  1  1  0  1],</code><br/>
            <code className="inline-code"> [1  1  1  1  0  1  0  1  1  1  1  1  1  1  1  1],</code><br/>
            <code className="inline-code"> [0  1  1  1  1  1  1  0  1  0  1  1  1  1  1  1],</code><br/>
            <code className="inline-code"> [1  1  1  1  1  1  1  1  0  1  1  1  1  1  1  1],</code><br/>
            <code className="inline-code"> [1  1  1  1  1  1  1  0  1  0  1  0  1  1  1  1],</code><br/>
            <code className="inline-code"> [1  0  1  1  1  1  1  1  1  1  0  1  1  1  1  1],</code><br/>
            <code className="inline-code"> [1  1  1  1  1  1  1  1  1  0  1  0  1  0  0  1],</code><br/>
            <code className="inline-code"> [1  1  0  1  1  1  1  1  1  1  1  1  0  1  1  1],</code><br/>
            <code className="inline-code"> [1  1  1  1  1  1  1  1  1  1  1  0  1  0  1  1],</code><br/>
            <code className="inline-code"> [1  1  1  1  1  0  1  1  1  1  1  0  1  1  0  1],</code><br/>
            <code className="inline-code"> [1  1  1  1  1  1  1  1  1  1  1  1  1  1  1  0],</code><br/>
            <code className="inline-code">k<sub>1</sub> = 1,</code><br/>
            <code className="inline-code">k<sub>2</sub> = 1</code>
          </div>,
        output:
          <div>
            <code className="inline-code">[0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14]</code>
          </div>
      }
    ],
    testCases: [
      {
        input: "2, 1, [[0  0  1  1  1], [0  1  1  1  1], [1  1  0  1  1], [1  1  1  0  0], [1  1  1  0  0]]",
        output: "[0, 1, 3, 4]"
      },
      {
        input: "[[0  1  1  1  1  1  1  0  1  1  1  1  1  1  1  1], [1  0  1  0  1  1  1  1  1  1  0  1  1  1  1  1], [1  1  0  1  1  1  1  1  1  1  1  1  0  1  1  1], [1  0  1  0  1  1  1  1  1  1  1  1  1  1  1  1], [1  1  1  1  0  1  0  1  1  1  1  1  1  1  1  1], [1  1  1  1  1  0  1  1  1  1  1  1  1  1  0  1], [1  1  1  1  0  1  0  1  1  1  1  1  1  1  1  1], [0  1  1  1  1  1  1  0  1  0  1  1  1  1  1  1], [1  1  1  1  1  1  1  1  0  1  1  1  1  1  1  1], [1  1  1  1  1  1  1  0  1  0  1  0  1  1  1  1], [1  0  1  1  1  1  1  1  1  1  0  1  1  1  1  1], [1  1  1  1  1  1  1  1  1  0  1  0  1  0  0  1], [1  1  0  1  1  1  1  1  1  1  1  1  0  1  1  1], [1  1  1  1  1  1  1  1  1  1  1  0  1  0  1  1], [1  1  1  1  1  0  1  1  1  1  1  0  1  1  0  1], [1  1  1  1  1  1  1  1  1  1  1  1  1  1  1  0]",
        output: "[0, 1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14]"
      }
    ],
    codeTemplate:
      "public class StudentSolver {\n" +
      "  public static HashSet<Integer> solve(boolean[][] m, int k1x, int k2x) {\n" +
      "    /* Your code goes here */\n" +
      "  }\n" +
      "}",
    numOfUsersAttempted: 0,
    numOfUsersSolved: 0,
    usersAttempted: [],
    usersSolved: []
  },
  {
    name: "Alien Chess",
    difficulty: "Hard",
    categories: ["Loops", "Methods", "ArrayLists", "Dynamic Programming", "Memoization"],
    description:
      <div>
        <p>On the planet Xeebo, you are given a chess board represented as positive integers within an <code className="inline-code">m</code> × <code className="inline-code">n</code> grid.
          To play alien chess, the game's rules are as follows:</p>
        <ul>
          <li key={(listIndex++).toString()}>Any given board has exactly one of either <code className="inline-code">m</code> or <code className="inline-code">n</code> with
            a length within the range <code className="inline-code">[2, 7]</code>, with the other length being large.</li>
          <li key={(listIndex++).toString()}>The first player assigns a positive integer value to each square of the board.
            The other player then places pieces on the board so as to maximize the sum of the scores on the squares.</li>
          <li key={(listIndex++).toString()}>No piece can be placed on a square that is immediately vertical or horizontal to another piece.</li>
          <li key={(listIndex++).toString()}>Chess pieces cannot be placed over an occupied square.</li>
        </ul>
        <p>You are playing this game with a Xeebo who assigns the integers to the board, so you will be placing the
          chess pieces. Your goal is to write a program that can optimally determine the best place to put your
          pieces. You will return an <code className="inline-code">ArrayList</code> of the positions that you choose. (Board positions
          are <code className="inline-code">(row, column)</code>and start counting from <code className="inline-code">0</code>. Repeated
          positions will be counted incorrect.)</p>
        <p>Note: A <code className="inline-code">Pair</code> class is given to you on the side which accepts generic arguments
          using the syntax: <code className="inline-code">Pair myPair = new Pair&lt;&gt;(arg1, arg2);</code>.</p>
        <p>HINT: With one of the rows/columns that small, it might be worth it to precompute, given a particular type of
          column placement in column <code className="inline-code">i</code>, all of the possible next column types that
          could be in column <code className="inline-code">i + 1</code>.</p>
        <img src="https://imgur.com/kIlbasZ.jpg"
             style="display: flex; width: 80%; height: 100%; margin: 2em auto;"
             alt="A sample of alien chess pieces." />
      </div>,
    examples: [
      {
        input:
          <div>
            <code className="inline-code">[[35  90  54  62  62  69],</code><br/>
            <code className="inline-code">[89  17  59  13  76  24],</code><br/>
            <code className="inline-code">[73   1  57  11  60  34],</code><br/>
            <code className="inline-code">[52  94   2  67   9  77]]</code>
          </div>,
        output:
          <div>
            <code className="inline-code">[(0, 5), (3, 5), (1, 4), (0, 3), (3, 3), (1, 2), (0, 1), (3, 1), (1, 0)]</code>
            <p>(Note: Sum = 69 + 77 + 76 + 62 + 67 + 59 + 90 + 94 + 89 = 683)</p>
          </div>
      },
      {
        input:
          <div>
            <code className="inline-code">[[41  30  45  18  67],</code><br/>
            <code className="inline-code">[64   8  27  65  52],</code><br/>
            <code className="inline-code">[93  98  98   5  71],</code><br/>
            <code className="inline-code">[90  14  53  56  86],</code><br/>
            <code className="inline-code">[64  62  88  65  99],</code><br/>
            <code className="inline-code">[84  77  68  68  22],</code><br/>
            <code className="inline-code">[45  84  43  39  16]]</code>
          </div>,
        output:
          <div>
            <code className="inline-code">[(0, 0), (2, 0), (4, 0), (6, 0), (1, 1), (3, 1), (5, 1), (0, 2), (2, 2), (4, 2), (6, 2), (1, 3), (3, 3), (5, 3), (0, 4), (2, 4), (4, 4), (6, 4)]</code>
            <p>(Note: Sum = 41 + 93 + 64 + 45 + 8 + 14 + 77 + 45 + 98 + 88 + 43 + 65 + 56 + 68 + 67 + 71 + 99 + 16 = 1058)</p>
          </div>
      }
    ],
    testCases: [
      {
        input: "[[35, 90, 54, 62, 62, 69], [89, 17, 59, 13, 76, 24], [73, 1, 57, 11, 60, 34], [52, 94, 2, 67, 9, 77]]",
        output: "[(0, 5), (3, 5), (1, 4), (0, 3), (3, 3), (1, 2), (0, 1), (3, 1), (1, 0)]"
      },
      {
        input: "[[41, 30, 45, 18, 67], [64, 8, 27, 65, 52], [93, 98, 98, 5, 71], [90, 14, 53, 56, 86], [64, 62, 88, 65, 99], [84, 77, 68, 68, 22], [45, 84, 43, 39, 16]]",
        output: "[(0, 0), (2, 0), (4, 0), (6, 0), (1, 1), (3, 1), (5, 1), (0, 2), (2, 2), (4, 2), (6, 2), (1, 3), (3, 3), (5, 3), (0, 4), (2, 4), (4, 4), (6, 4)]"
      }
    ],
    codeTemplate:
      "public class StudentSolver {\n" +
      "  public static ArrayList<Pair<Integer, Integer>> solve(int[][] board) {\n" +
      "    /* Your code goes here */\n" +
      "  }\n" +
      "}",
    numOfUsersAttempted: 8,
    numOfUsersSolved: 1,
    usersAttempted: [admins[0]],
    usersSolved: []
  },
  {
    name: "Roomba",
    difficulty: "Hard",
    categories: ["Loops", "Methods", "ArrayLists", "Dynamic Programming", "Memoization"],
    description:
      <div>
        <p>You are designing an algorithm for the Roomba Minus, an automated floor sweeper. Though the Roomba Minus is a
          major advance in robotic home maintenance, there are some serious design flaws. The engineers are looking to you
          to help them improve the product.</p>
        <ul>
          <li key={(listIndex++).toString()}>The Roomba Minus is designed to be placed somewhere along the wall of one side
            of a rectangular room, and will travel to the opposite wall of the room. If you draw the room in such a way that
            one wall lies along the line <code className="inline-code">y = 0</code> and the opposite wall lies along the
            line <code className="inline-code">y = h</code> (for some constant <code className="inline-code">h</code>), then
            the speed of the Roomba Minus in the <code className="inline-code">y</code> direction will always be constant
            (and positive). We will refer to this speed as <code className="inline-code">v</code>.</li>
          <li key={(listIndex++).toString()}>The engineers have designed the Roomba Minus so that it can move horizontally
            as well. Given its vertical speed <code className="inline-code">v</code>, it can move horizontally at any speed
            between <code className="inline-code">—v = r</code> and <code className="inline-code">v = r</code> (for some constant
            <code className="inline-code">r</code>) as long as it is still moving vertically while it does so. The horizontal
            speed is not constant and can be changed at any time.</li>
        </ul>
        <p>Given a rectangular room, the Roomba Minus will first identify problematic points in the room and analyze their
          severity. It will then place itself on the south (<code className="inline-code">y = 0</code>) side of the room
          and work its way methodically to the other side of the room (<code className="inline-code">y = h</code>), cleaning
          up the dirty areas of the floor that it reaches as it goes. Given the restrictions that the engineers have designed
          into it, it's clear that the machine will not be able to handle every single problematic point in the room. Your
          goal is to design an algorithm for the Roomba Minus that will (a) specify where on the <code className="inline-code">y = 0</code> axis
          it should start and (b) which problematic points it should clean up on its way to the other side of the room. The
          algorithm should maximize the sum of the severity points of the problematic points in the room that it cleans.</p>
        <p>You will be given <code className="inline-code">r</code> (the ratio of vertical to horizontal speed). Following
          this will be n lines, each containing an <code className="inline-code">x</code> and <code className="inline-code">y</code> coordinate,
          the coordinates of the mess, and a severity <code className="inline-code">s</code> for the mess.</p>
        <p>Your program will list of the messes that your Roomba Minus cleans in chronological order, one per line. Assume
          that the array of messes begins counting at <code className="inline-code">0</code>.</p>
        <p>Note: A <code className="inline-code">Pair</code> class is given to you on the side which accepts generic arguments using the
          syntax: <code className="inline-code">Pair myPair = new Pair&lt;&gt;(arg1, arg2);</code>.</p>
        <p>HINT: It is possible to formulate this problem as the problem of finding the longest path in an appropriately chosen
          directly acyclic graph. Imagine putting an edge from one mess to another if and only if it is possible to reach the
          second from the first.</p>
        <img src="https://imgur.com/ZAaAJ8d.jpg"
             style="display: flex; width: 80%; height: 100%; margin: 2em auto;"
             alt="A sample of a Roomba." />
      </div>,
    examples: [
      {
        input:
          <div>
            <code className="inline-code">r = 16.7374586913063,</code><br/>
            <code className="inline-code">[((50.53339753651755, 2392.9913262022305), 4),</code><br/>
            <code className="inline-code">((91.45350267880721, 2038.5690414517053), 6),</code><br/>
            <code className="inline-code">((81.30630354764358, 3002.1564113738523), 9),</code><br/>
            <code className="inline-code">((37.16228801441648, 2421.3261154038723), 8),</code><br/>
            <code className="inline-code">((16.323410763410276, 696.3036909610697), 1),</code><br/>
            <code className="inline-code">((17.558652660436632, 2306.3556734125145), 1),</code><br/>
            <code className="inline-code">((30.858752483887464, 751.748794481916), 3),</code><br/>
            <code className="inline-code">((30.957197204880696, 944.2879593578829), 8),</code><br/>
            <code className="inline-code">((15.042626905954554, 2344.979171359295), 1),</code><br/>
            <code className="inline-code">((84.13614550978521, 1310.743768220772), 9)]</code>
          </div>,
        output:
          <div>
            <code className="inline-code">[6, 7, 1, 2]</code>
          </div>
      },
      {
        input:
          <div>
            <code className="inline-code">r = 4.0970868343508045,</code><br/>
            <code className="inline-code">[((87.21684387143354, 954.8669492163775), 1),</code><br/>
            <code className="inline-code">((63.797111370601925, 1599.0719983165416), 10),</code><br/>
            <code className="inline-code">((89.38032320395904, 317.4943644252255), 8),</code><br/>
            <code className="inline-code">((21.887003117859052, 1623.2066088154834), 4),</code><br/>
            <code className="inline-code">((59.72128992893107, 1054.4151412143742), 4),</code><br/>
            <code className="inline-code">((65.63722317277708, 715.4761800657637), 4),</code><br/>
            <code className="inline-code">((40.945864951008645, 1503.565641866376), 3),</code><br/>
            <code className="inline-code">((16.23282429177653, 97.40979265083185), 10),</code><br/>
            <code className="inline-code">((92.79901139049763, 908.5599195365235), 7),</code><br/>
            <code className="inline-code">((75.07982260480249, 403.74349509408705), 3),</code><br/>
            <code className="inline-code">((23.40462172477689, 1260.5966364516757), 7),</code><br/>
            <code className="inline-code">((67.75966299566583, 1042.3485806619208), 1),</code><br/>
            <code className="inline-code">((87.36334875485645, 407.89154792802043), 9),</code><br/>
            <code className="inline-code">((98.30200651337582, 702.0604318874751), 3),</code><br/>
            <code className="inline-code">((36.76869215786858, 697.8608975232554), 9)]</code>
          </div>,
        output:
          <div>
            <code className="inline-code">[7, 12, 5, 8, 4, 10, 6, 1]</code>
          </div>
      }
    ],
    testCases: [
      {
        input: "[[35, 90, 54, 62, 62, 69], [89, 17, 59, 13, 76, 24], [73, 1, 57, 11, 60, 34], [52, 94, 2, 67, 9, 77]]",
        output: "[(0, 5), (3, 5), (1, 4), (0, 3), (3, 3), (1, 2), (0, 1), (3, 1), (1, 0)]"
      },
      {
        input: "[[41, 30, 45, 18, 67], [64, 8, 27, 65, 52], [93, 98, 98, 5, 71], [90, 14, 53, 56, 86], [64, 62, 88, 65, 99], [84, 77, 68, 68, 22], [45, 84, 43, 39, 16]]",
        output: "[(0, 0), (2, 0), (4, 0), (6, 0), (1, 1), (3, 1), (5, 1), (0, 2), (2, 2), (4, 2), (6, 2), (1, 3), (3, 3), (5, 3), (0, 4), (2, 4), (4, 4), (6, 4)]"
      }
    ],
    codeTemplate:
      "public class StudentSolver {\n" +
      "  public static ArrayList<Integer> run(double r, ArrayList<Pair<Pair<Double, Double>, Integer>> mess) {\n" +
      "    /* Your code goes here */\n" +
      "  }\n" +
      "}",
    numOfUsersAttempted: 8,
    numOfUsersSolved: 1,
    usersAttempted: [],
    usersSolved: []
  },
  {
    name: "",
    difficulty: "",
    categories: [""],
    description:
      <div>

      </div>,
    examples: [
      {}
    ],
    testCases: [
      {}
    ],
    codeTemplate:
      "",
    numOfUsersAttempted: 0,
    numOfUsersSolved: 0,
    usersAttempted: [],
    usersSolved: []
  },
  {
    name: "",
    difficulty: "",
    categories: [""],
    description:
      <div>

      </div>,
    examples: [
      {}
    ],
    testCases: [
      {}
    ],
    codeTemplate:
      "",
    numOfUsersAttempted: 0,
    numOfUsersSolved: 0,
    usersAttempted: [],
    usersSolved: []
  },
  {
    name: "",
    difficulty: "",
    categories: [""],
    description:
      <div>

      </div>,
    examples: [
      {}
    ],
    testCases: [
      {}
    ],
    codeTemplate:
      "",
    numOfUsersAttempted: 0,
    numOfUsersSolved: 0,
    usersAttempted: [],
    usersSolved: []
  },
  {
    name: "",
    difficulty: "",
    categories: [""],
    description:
      <div>

      </div>,
    examples: [
      {}
    ],
    testCases: [
      {}
    ],
    codeTemplate:
      "",
    numOfUsersAttempted: 0,
    numOfUsersSolved: 0,
    usersAttempted: [],
    usersSolved: []
  },
  {
    name: "",
    difficulty: "",
    categories: [""],
    description:
      <div>

      </div>,
    examples: [
      {}
    ],
    testCases: [
      {}
    ],
    codeTemplate:
      "",
    numOfUsersAttempted: 0,
    numOfUsersSolved: 0,
    usersAttempted: [],
    usersSolved: []
  },
  {
    name: "",
    difficulty: "",
    categories: [""],
    description:
      <div>

      </div>,
    examples: [
      {}
    ],
    testCases: [
      {}
    ],
    codeTemplate:
      "",
    numOfUsersAttempted: 0,
    numOfUsersSolved: 0,
    usersAttempted: [],
    usersSolved: []
  }
];

export default problems;
