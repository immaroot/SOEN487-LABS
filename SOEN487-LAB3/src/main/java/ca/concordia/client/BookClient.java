package ca.concordia.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class BookClient {

    private static String getBookDetails(String title) {
        String result = null;
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet httpGet = new HttpGet(String.format("http://localhost:8080/library/%s", encode(title)));

            ResponseHandler<String> responseHandler = new BookClientResponseHandler();

            result = client.execute(httpGet, responseHandler);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void updateBook(String title, String isbn, String author) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpPut httpPut = new HttpPut(String.format("http://localhost:8080/library/%s/%s/%s", encode(title), encode(isbn), encode(author)));

            ResponseHandler<String> responseHandler = new BookClientResponseHandler();

            client.execute(httpPut, responseHandler);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static CharSequence encode(String urlUnsafeString) throws UnsupportedEncodingException {
        return URLEncoder.encode(urlUnsafeString, "UTF-8");
    }

    public static void main(String[] args) {

        updateBook("a title", "456", "a brand new author");
        updateBook("b title", "123", "another author");

        System.out.println(getBookDetails("a title"));
        System.out.println(getBookDetails("b title"));
    }

    static class BookClientResponseHandler implements ResponseHandler<String> {

        @Override
        public String handleResponse(HttpResponse httpResponse) throws IOException {
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                HttpEntity entity = httpResponse.getEntity();
                if (entity == null) {
                    return "";
                } else {
                    return EntityUtils.toString(entity);
                }
            }
            return "Bad status code: " + statusCode;
        }
    }
}
