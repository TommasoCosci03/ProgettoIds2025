package it.unicam.cs.ids25.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;


/**
 * Servizio per la geocodifica di un indirizzo tramite l'API di Nominatim di OpenStreetMap.
 * dato un indirizzo in forma testuale,
 * restituisce  la latitudine e la longitudine corrispondenti.
 *
 * Utilizza {@link RestTemplate} per effettuare richieste HTTP GET e
 */
@Service
public class GeocodingService {

    private final RestTemplate restTemplate = new RestTemplate();


    /**
     * Esegue la geocodifica di un indirizzo tramite il servizio Nominatim di OpenStreetMap.
     *
     * @param indirizzo l'indirizzo da geocodificare
     * @return un {@link Optional} contenente un array di due elementi
     *         con latitudine e longitudine se la geocodifica ha successo,
     *
     */
    public Optional<double[]> geocode(String indirizzo) {
        try {
            String url = "https://nominatim.openstreetmap.org/search"
                    + "?q=" + URLEncoder.encode(indirizzo, StandardCharsets.UTF_8)
                    + "&format=json&limit=1";

            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "SpringBootApp/1.0"); // richiesto da Nominatim

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            JSONArray results = new JSONArray(response.getBody());
            if (results.length() > 0) {
                JSONObject location = results.getJSONObject(0);
                double lat = location.getDouble("lat");
                double lon = location.getDouble("lon");
                return Optional.of(new double[]{lat, lon});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
