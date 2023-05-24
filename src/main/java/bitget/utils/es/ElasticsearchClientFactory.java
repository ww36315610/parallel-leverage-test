//package com.bitget.es;
//
//import org.apache.http.HttpHost;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//
//public class ElasticsearchClientFactory {
//    public static RestHighLevelClient createClient() {
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("elasticsearch-logging9200.test8.bitget.tools", 80, "http")
//                ).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
//                    @Override
//                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
//                        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//                        credentialsProvider.setCredentials(AuthScope.ANY,
//                                new UsernamePasswordCredentials("elastic", "gKa31hVSnaxauGhU2N8m"));
//                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//                    }
//                }));
//        return client;
//    }
//}
