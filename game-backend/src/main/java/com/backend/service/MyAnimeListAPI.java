package com.backend.service;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

public class MyAnimeListAPI {

    private static HttpClient client;
    private static MyAnimeListAPI api;

    private MyAnimeListAPI() {
        MyAnimeListAPI.client = HttpClient.newBuilder()
                .version(Version.HTTP_1_1)
                .followRedirects(Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                // .proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 80)))
                .build();
    }

    public static MyAnimeListAPI getInstance() {
        if (MyAnimeListAPI.api == null) {
            MyAnimeListAPI.api = new MyAnimeListAPI();
        }

        return MyAnimeListAPI.api;
    }

    public String getAnimeImage() {
        String url = "";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.myanimelist.net/v2/anime/ranking/?ranking_type=all&limit=10"))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
        return url;
    }
}
