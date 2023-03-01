
import problems from "./CodingProblems";
import users from "./Accounts";
import {
  handleUpvote, handleDownvote
} from "../Routes/Main/User/CodingProblemPageU/CPSolutionPage/CPSolutionLogic";

// cpSolutions has (key --> value) pairings of type (String --> JSON obj)
let cpSolutions = new Map([
  ["FizzBuzz",
    {
      adminID: "694202496",
      description: problems.filter(problem => problem.name === "FizzBuzz")[0].description,
      code:
        "class StudentSolver {\n" +
        "  public static String[] solve(int n) {\n" +
        "\n" +
        "    String[] answer = new String[n];\n" +
        "\n" +
        "    for (int i = 1; i <= n; i++) {\n" +
        "      if (i % 3 == 0 && i % 5 == 0) {\n" +
        "        answer[i - 1] = \"FizzBuzz\";\n" +
        "      }\n" +
        "      else if (i % 3 == 0) {\n" +
        "        answer[i - 1] = \"Fizz\";\n" +
        "      }\n" +
        "      else if (i % 5 == 0) {\n" +
        "        answer[i - 1] = \"Buzz\";\n" +
        "      }\n" +
        "      else {\n" +
        "        answer[i - 1] = Integer.toString(i);\n" +
        "      }\n" +
        "    }\n" +
        "    return answer;\n" +
        "  }\n" +
        "}",
      isReported: true,
      upvoteCount: handleUpvote,
      downvoteCount: handleDownvote,
      usersUpvoted: [users[0]],
      usersDownvoted: [users[1]]
    }],
  ["Coin Stacks",
    {
      adminID: "",
      description: problems.filter(problem => problem.name === "Alien Chess")[0],
      code: "",
      isReported: false,
      upvoteCount: handleUpvote,
      downvoteCount: handleDownvote
    }],
  ["Foreman",
    {
      adminID: "",
      description: problems.filter(problem => problem.name === "Alien Chess")[0],
      code: "",
      isReported: false,
      upvoteCount: handleUpvote,
      downvoteCount: handleDownvote
    }],
  ["Logician",
    {
      adminID: "",
      description: problems.filter(problem => problem.name === "Alien Chess")[0],
      code: "",
      isReported: false,
      upvoteCount: handleUpvote,
      downvoteCount: handleDownvote
    }],
  ["Alien Chess",
    {
      adminID: "",
      description: problems.filter(problem => problem.name === "Alien Chess")[0],
      code: "",
      isReported: false,
      upvoteCount: handleUpvote,
      downvoteCount: handleDownvote
    }],
  ["Roomba",
    {
      adminID: "",
      description: problems.filter(problem => problem.name === "Roomba")[0].description,
      code: "",
      isReported: false,
      upvoteCount: handleUpvote,
      downvoteCount: handleDownvote
    }]
]);

export default cpSolutions;
