package com.example.bot.spring.echo;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.time.LocalDateTime;

@RestController
public class GetCall {
    @GetMapping("/welcome")
    public LocalDateTime hello() {
        LocalDateTime now = LocalDateTime.now();
        return  now.plusHours(7);
    }
    @PostMapping("/post")
    public String handleTexMessagePush(@RequestBody String post) throws IOException {
        String decode = URLDecoder.decode(post, StandardCharsets.UTF_8.name());
        final LineMessagingClient client = LineMessagingClient
                .builder("BE2CfRAKK6YSXCkQW4GuXWtpyaTWN35poWC8Y8E7qQOepJ4+QiX9OZbGZC+6cz8QqXPDYq3LOlaLfEJ8k5WAQMS8nX7oyH0ZGixTg6GWxo/MV7k+U3mNJWzKHayl80db7DXFNMtqeUgRPl5WmpBKuQdB04t89/1O/w1cDnyilFU=")
                .build();
        final TextMessage textMessage = new TextMessage(decode);
        final PushMessage pushMessage = new PushMessage("Ce8a17deb5c112f51fdf2bab49d1589c2",textMessage);

        final BotApiResponse botApiResponse;
        try {
            botApiResponse = client.pushMessage(pushMessage).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "error";
        }

        System.out.println(botApiResponse);
        return post;
    }

}
