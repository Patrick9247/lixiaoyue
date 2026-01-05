package com.lixiaoyue;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AliyunAIClient {
    private static final String ACCESS_KEY = "f90c0aec426e104bacc277c2760350bc";
    private static final String SECRET_KEY = "f90c0aec426e104bacc277c2760350bc";
    private static final String APPLICATION_ID = "1b7706a0-92d8-11f0-8f1b-96d94c49ccf4";
    private static final String ENDPOINT = "https://bailian.cdut.edu.cn/cre_llm/application/open_chat";


    // 流式文本生成
    public static void streamGenerate(String prompt, String sessionId) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(ENDPOINT);

        // 构建请求头
        String timestamp = String.valueOf(System.currentTimeMillis());
        post.setHeader("Ak", ACCESS_KEY);
        post.setHeader("Content-Type", "application/json");

        // 构建请求体（含历史上下文）
        String jsonBody = String.format(
                "{ \"model\": \"max\", \"input\": { \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}] }, \"parameters\": { \"stream\": true, \"session_id\": \"%s\" } }",
                prompt, sessionId
        );
        post.setEntity(new StringEntity(jsonBody));

        // 执行流式请求
        try (CloseableHttpResponse response = client.execute(post)) {
            InputStream stream = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    JsonNode node = new ObjectMapper().readTree(line);
                    System.out.print(node.get("output").asText());
                }
            }
        }
    }
}