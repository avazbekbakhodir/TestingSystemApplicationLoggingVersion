package com;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class TelegramNotifyHandler extends StreamHandler {
    public TelegramNotifyHandler() {
        super.setFilter(new TelegramNotifyFilter());
    }
     
    @Override
    public synchronized void publish(LogRecord record) {
        if (!super.getFilter().isLoggable(record)) { return; }

        String message = getFormatter().format(record);
        String url = "https://api.telegram.org/bot6169432544:AAF6CmUn25fCDEhJZb32h0EN5wKW8XeCXC4/sendMessage";
        String chatId = "1117819392";

        //List<String> list = List.of("616525392", "1807487029", "1823805888", "2136347205");


            String messageBody = """
                {
                    "chat_id" : "%s",
                    "text" : "%s"
                }
                """.formatted(chatId, message);

//        java.net.*
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(messageBody))
                    .header("Content-Type", "application/json")
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            try {
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

