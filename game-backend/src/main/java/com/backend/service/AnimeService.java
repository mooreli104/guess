package com.backend.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AnimeService {

    private static HttpClient client;
    private static AnimeService api;
    private ObjectMapper objectMapper = new ObjectMapper();

    private AnimeService() {
        AnimeService.client = HttpClient.newBuilder()
                .version(Version.HTTP_1_1)
                .followRedirects(Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                // .proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
                .build();
    }

    public static AnimeService getInstance() {
        if (AnimeService.api == null) {
            AnimeService.api = new AnimeService();
        }

        return AnimeService.api;
    }

    public Map<String, Object> getAnime() {
        Map<String, Object> map = new ConcurrentHashMap<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.myanimelist.net/v2/anime/ranking?ranking_type=all&limit=10"))
                .timeout(Duration.ofMinutes(2))
                .header("X-MAL-CLIENT-ID", "d2dd83dfa266b694097f5a0365955ff8")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            String jsonString = response.body();
            map = objectMapper.readValue(jsonString, new TypeReference<>() {
            });
        } catch (IOException | InterruptedException e) {
            System.out.println("Error get MAL endpoint");
        }
        return map;
    }
}
