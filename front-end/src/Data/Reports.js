
let listIndex = 0;
let reports = [
    {
    reporterID: "694206942",
    adminID: "694202496",
    codingProblemName: "FizzBuzz",
    dateReported: "03/04/2022",
    reportedDescription:
      <div>
        <p>Given a positive integer <code className={"inline-code"}>n</code>, create an array of
          integers <span style={{color: "var(--red)"}}>incrementing
          by <code className={"inline-code"}>1</code> from <code className={"inline-code"}>1</code> to <code className={"inline-code"}>n</code>.
          Write a program that will return a String array of equal length as the array of integers</span>, where for each integer at
          index <code className={"inline-code"}>i</code>:</p>
        <ul>
          <li key={(listIndex++).toString()}>If the integer is divisible by <code className={"inline-code"}>3</code> only, set <code className={"inline-code"}>stringArray[i]</code> to <code className={"inline-code"}>“Fizz”</code>.</li>
          <li key={(listIndex++).toString()}>If the integer is divisible by <code className={"inline-code"}>5</code> only, set <code className={"inline-code"}>stringArray[i]</code> to <code className={"inline-code"}>“Buzz”</code>.</li>
          <li key={(listIndex++).toString()}>If the integer is divisible by both <code className={"inline-code"}>3</code> and <code className={"inline-code"}>5</code>, set <code className={"inline-code"}>stringArray[i]</code> to <code className={"inline-code"}>“FizzBuzz”</code>.</li>
          <li key={(listIndex++).toString()}>Else, store the value as is.</li>
        </ul>
      </div>,
    hasBeenReviewed: false
  },
  {
    reporterID: "123456789",
    adminID: "987654321",
    codingProblemName: "Alien Chess",
    dateReported: "03/05/2022",
    reportedDescription:
      <div>
        <p>On the planet Xeebo, you are given a chess board represented as positive integers within an <code className={"inline-code"}>m</code> × <code className={"inline-code"}>n</code> grid.
          To play alien chess, the game's rules are as follows:</p>
        <span style={{color: "var(--red)"}}><ul>
          <li key={(listIndex++).toString()}>Any given board has exactly one of either <code className={"inline-code"}>m</code> or <code className={"inline-code"}>n</code> with
            a length within the range <code className={"inline-code"}>[2, 7]</code>, with the other length being large.</li>
          <li key={(listIndex++).toString()}>The first player assigns a positive integer value to each square of the board.
            The other player then places pieces on the board so as to maximize the sum of the scores on the squares.</li>
          <li key={(listIndex++).toString()}>No piece can be placed on a square that is immediately vertical or horizontal to another piece.</li>
          <li key={(listIndex++).toString()}>Chess pieces cannot be placed over an occupied square.</li>
        </ul></span>
        <p>You are playing this game with a Xeebo who assigns the integers to the board, so you will be placing the
          chess pieces. Your goal is to write a program that can optimally determine the best place to put your
          pieces. You will return an <code className={"inline-code"}>ArrayList</code> of the positions that you choose. (Board positions
          are <code className={"inline-code"}>(row, column)</code>and start counting from <code className={"inline-code"}>0</code>. Repeated
          positions will be counted incorrect.)</p>
        <p>Note: A <code className={"inline-code"}>Pair</code> class is given to you on the side which accepts generic arguments using the syntax: <code className={"inline-code"}>Pair myPair = new Pair&lt;&gt;(arg1, arg2);</code>.</p>
        <p>HINT: With one of the rows/columns that small, it might be worth it to precompute, given a particular type of
          column placement in column <code className={"inline-code"}>i</code>, all of the possible next column types that could be in column <code className={"inline-code"}>i + 1</code>.</p>
        <img src={"https://imgur.com/kIlbasZ.jpg"}
             style={{display: "flex", width: "80%", height: "100%", margin: "2em auto"}}
             alt={"A sample of alien chess pieces."} />
      </div>,
    hasBeenReviewed: false
  }
]

export default reports;
