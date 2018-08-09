package com.example.book.net;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Request {
    public static String makeRequest(String url) {
        URL request;
        URLConnection rq;
        StringBuilder sb = new StringBuilder();
        try {
            request = new URL(url);
            rq = request.openConnection();
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            rq.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    sb.append(inputLine.trim());
            }
        } catch (IOException ignored) {
        }
        return sb.toString();
    }

}
