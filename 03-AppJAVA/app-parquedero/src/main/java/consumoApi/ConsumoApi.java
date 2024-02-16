package consumoApi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;


public class ConsumoApi {
    public ConsumoApi() {
    }

    //consumo post con datos
    public String consumoPOST(String endpoint, Map<String, String> postData) {
        try {
            URL url = new URL(endpoint);

            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

            conexion.setRequestMethod("POST");
            conexion.setDoOutput(true);
            StringBuilder data = new StringBuilder();
            for (Map.Entry<String, String> entry : postData.entrySet()) {
                if (data.length() != 0) data.append('&');
                data.append(entry.getKey());
                data.append('=');
                data.append(entry.getValue());
            }
            try (DataOutputStream wr = new DataOutputStream(conexion.getOutputStream())) {
                wr.writeBytes(data.toString());
                wr.flush();
            }
            int responseCode = conexion.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new java.io.InputStreamReader(conexion.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                conexion.disconnect();
                return response.toString();
            } else {
                System.out.println("Error al consumir la API. Código de respuesta: " + responseCode);
                conexion.disconnect();
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    //consumo get normal
    public String consumoGET(String endpoint){
        try {

            // URL de la API
            URL url = new URL(endpoint);

            // Abrir una conexión HTTP y configurar para Metodo GET
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Obtener el código de respuesta enviado por el Servidor
            int responseCode = connection.getResponseCode();

            // En caso de respuesta exitosa convertir la respuesta en String
            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                connection.disconnect();
                return response.toString();
            } else {
                System.out.println("Error al consumir la API. Código de respuesta: " + responseCode);
                connection.disconnect();
                return null;
            }
        } catch (Exception e) {
            System.out.println(" -- Catch -- ");
            e.printStackTrace();
            return null;
        }
    }


    //consumo get con datos
    public String consumoGETV1(String endpoint, Map<String, String> getData){
        try {

            // Construir la URL con los datos GET
            StringBuilder urlBuilder = new StringBuilder(endpoint);
            if (!getData.isEmpty()) {
                urlBuilder.append('?');
                for (Map.Entry<String, String> entry : getData.entrySet()) {
                    urlBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    urlBuilder.append('=');
                    urlBuilder.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                    urlBuilder.append('&');
                }
                urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Eliminar el último '&'
            }

            // URL de la API
            URL url = new URL(urlBuilder.toString());

            // Abrir una conexión HTTP y configurar para Metodo GET
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Obtener el código de respuesta enviado por el Servidor
            int responseCode = connection.getResponseCode();

            // En caso de respuesta exitosa convertir la respuesta en String
            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                connection.disconnect();
                return response.toString();
            } else {
                System.out.println("Error al consumir la API. Código de respuesta: " + responseCode);
                connection.disconnect();
                return null;
            }
        } catch (Exception e) {
            System.out.println(" -- Catch -- ");
            e.printStackTrace();
            return null;
        }
    }

}
