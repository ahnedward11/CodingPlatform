
package com.team1159ers.coffee_coder_db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JDoodleAPI {
    public static String execute(String code) {
        String clientId = "f5aa72e0a02fe348edadc5df82728623";
        String clientSecret = "6bbe457e84b07cb9d10dc4355be58a50f49866477ac1e415dfd6757ce3ca5ee5";
        String script = code
            .replaceAll("\n", "")
            .replaceAll("\t", "")
            .replaceAll("\\p{Zs}+", " ").replaceAll("\"", "\\\\\"");
        String language = "java";
        String versionIndex = "4";
        StringBuilder output = new StringBuilder();

        try {
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            String input = "{\"clientId\": \"" + clientId + "\"," +
                "\"clientSecret\":\"" + clientSecret + "\"," +
                "\"script\":\"" + script + "\"," +
                "\"language\":\"" + language + "\"," +
                "\"versionIndex\":\"" + versionIndex + "\"} ";

            System.out.println("Input:\n" + input);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Please check your inputs : HTTP error code : " + connection.getResponseCode());
            }

            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(
                (connection.getInputStream())));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line);
            }

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Output from JDoodle:\n" + output);
        return output.toString();
    }

    /*public static void main(String[] args) {
        JDoodleAPI.execute("class StudentSolver {\n" +
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
            "}");
    }*/
}
