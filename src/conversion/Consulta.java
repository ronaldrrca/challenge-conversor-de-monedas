package conversion;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consulta {
    public Tasa consultaTasa(String monedaBase, String monedaObjetivo){
        String apiKey = "56cae83c5dd3498dab07f25c";
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/"+ apiKey +"/pair/"+ monedaBase +"/" + monedaObjetivo);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();

            HttpResponse<String> response;
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Tasa.class);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
