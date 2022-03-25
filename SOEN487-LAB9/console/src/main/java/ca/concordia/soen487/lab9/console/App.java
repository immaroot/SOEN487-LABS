package ca.concordia.soen487.lab9.console;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        String processID = createHungryProcess();
        
        System.out.println("Are you hungry?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");

        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();

        boolean hungry = false;
        switch (choice) {
            case "1":
                hungry = true;
                break;
            case "2":
                break;
        }
        input.close();

        String body = createBodyForVariable(hungry);

        //Process ID and Task ID are completely different!!!!
        String taskId = getTaskID(processID);


        System.out.println("body: " + body);
        completeTask(taskId, body);
    }

    public static String createHungryProcess() {
        // Create closeable http client to execute requests with
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Creating the request to execute
            HttpPost httpPost = new HttpPost(
                    "http://localhost:8080/engine-rest/process-definition/key/HungryProcess/submit-form");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(new StringEntity("{}"));

            // Executing the request using the http client and obtaining the response
            CloseableHttpResponse response = client.execute(httpPost);
            String jsonResponse = readResponse(response);
            System.out.println("jsonResponse: " + jsonResponse);

            String processId = getProcessIdFromJSON(jsonResponse);
            System.out.println("processID: " + processId);

            return processId;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to get customers";
        }
    }

    public static void completeTask(String taskId, String body) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String URL = String.format("http://localhost:8080/engine-rest/task/%s/complete", taskId);
            // Creating the request to execute
            HttpPost httpPost = new HttpPost(URL);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(new StringEntity(body));

            // Executing the request using the http client and obtaining the response
            client.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTaskID(String processID) {
        JSONArray jsonArray = getTaskList(processID);

        System.out.println("getTaskID(" + processID + ")");
        System.out.println("jsonArray: " + jsonArray);

        JSONObject obj = (JSONObject) jsonArray.get(0);

        System.out.println("---");

        return obj.getString("id");
    }

    private static JSONArray getTaskList(String processID) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            String URL = String.format("http://localhost:8080/engine-rest/task/?processInstanceId=%s",
                    processID);
            HttpGet httpGet = new HttpGet(URL);
            CloseableHttpResponse response = client.execute(httpGet);

            System.out.println("getTaskList(" + processID + ")");
            String jsonResponse = readResponse(response);

            System.out.println("jsonResponse: " + jsonResponse);
            System.out.println("---");

            return new JSONArray(jsonResponse);
        } catch (IOException e ) {
            return new JSONArray("[]");
        }
    }

    private static String createBodyForVariable(boolean hungry) {
        JSONObject variables = new JSONObject();
        JSONObject hungryJson = new JSONObject();
        hungryJson.put("value", hungry);
        hungryJson.put("type", "boolean");
        variables.put("hungry", hungryJson);
        JSONObject body = new JSONObject();
        body.put("variables", variables);
        return body.toString();
    }

    public static String getProcessIdFromJSON(String json) {
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getString("id");
    }

    public static String readResponse(CloseableHttpResponse response) throws IOException {
        // Handling the IO Stream from the response using scanner
        Scanner sc = new Scanner(response.getEntity().getContent());
        StringBuilder stringResponse = new StringBuilder();
        while (sc.hasNext()) {
            stringResponse.append(sc.nextLine());
        }
        response.close();
        return stringResponse.toString();
    }
}
