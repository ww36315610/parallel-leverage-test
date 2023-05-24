package bitget.utils.es;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

public class EsClient {
    public static void main(String[] args) {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("elasticsearch-logging9200.test8.bitget.tools", 80, "http")
                ).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                        credentialsProvider.setCredentials(AuthScope.ANY,
                                new UsernamePasswordCredentials("elastic", "gKa31hVSnaxauGhU2N8m"));
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                }));
        index(client);

        String query = "{\"query\":{\"bool\":{\"filter\":[{\"term\":{\"eventCode\":{\"value\":\"UserLoginEventWillistTestTwo\"}}}],\"must\":[{\"terms\":{\"deviceId\":[\"33d8a5241f3c75b34b20413193de45d6\"]}}]}},\"sort\":[{\"eventTime\":{\"order\":\"desc\"}}],\"size\":20}";
//        query="{\"size\":0,\"query\":{\"bool\":{\"filter\":[{\"terms\":{\"type\":[5,6],\"boost\":1.0}},{\"range\":{\"create_time\":{\"from\":\"2023-05-10 21:50:00\",\"to\":\"2023-05-16 21:50:00\",\"include_lower\":true,\"include_upper\":false,\"format\":\"yyyy-MM-dd HH:mm:ss\",\"boost\":1.0}}}],\"adjust_pure_negative\":true,\"boost\":1.0}},\"aggregations\":{\"user_id\":{\"terms\":{\"field\":\"user_id\",\"size\":65535,\"min_doc_count\":1,\"shard_min_doc_count\":0,\"show_term_doc_count_error\":false,\"order\":[{\"_count\":\"desc\"},{\"_key\":\"asc\"}]}}}}";
        try {
            search("risk-event-result-*", query, client);
//            search("dc-otc-user-bill-*",query,client);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String aa = "{\"amount\":\"10682.24\",\"appVersion\":\"\",\"applyId\":\"1022910192511180800\",\"bankCode\":\"\",\"bankUserBame\":\"Alexander Holzinger\",\"cardNumber\":\"DE52500105175416757164\",\"channelCode\":\"OUITRUST\",\"currency\":\"EUR\",\"deviceId\":\"5a73e920cc639fdadde0084a25316979\",\"eventTime\":\"2023-03-24 05:25:15\",\"fee\":\"0.50\",\"feeAmount\":\"0.53815520\",\"ip\":\"217.235.169.250\",\"orderType\":\"WITHDRAW\",\"paymentMethod\":\"BANK_TRANSFER\",\"usdAmount\":\"11497.40609192\",\"userAgent\":\"\",\"userId\":\"5812677390\",\"recordCode\":\"1025639795847766016\"}";
        String bb = "{\"appName\":\"bitget\",\"applyId\":\"1020168621265371136\",\"eventTime\":\"2023-03-16 15:51:14\",\"billType\":\"DEPOSIT\",\"cardHolderName\":\"harry\",\"cardNo\":\"EE247700560348771667\",\"channelCode\":\"ouitrust\",\"eventCode\":\"FiatDeposit\",\"fee\":\"0\",\"legalTenderAmount\":\"32\",\"legalTenderType\":\"EUR\",\"userId\":\"2149194207\"}";
        String cc = "{\"amountToU\":\"18.33734\",\"appName\":\"BITGET\",\"applyId\":\"1022707885689446400\",\"billType\":\"BUY\",\"cardBank\":\"BANK OF BEIJING\",\"cardBin\":\"621468\",\"cardCountry\":\"CN\",\"cardHolderName\":\"\",\"cardId\":\"src_65muh3xmtkbuzfmdmzpka4n7li\",\"cardNoLastFour\":\"8612\",\"cardOrganization\":\"002\",\"cardType\":\"DEBIT\",\"channelProvider\":\"CHECKOUT\",\"deviceId\":\"bdf5f30cc1d7804a27d11d275725bfe7\",\"deviceToken\":\"641c0593afUWte1MpvcOCDRZrn9EgCZHTEt7Mfa1\",\"eventTime\":\"2023-03-23 16:01:22\",\"expiryMonth\":\"12\",\"expiryYear\":\"2025\",\"fee\":\"0.36\",\"feeToU\":\"0.33007212\",\"ip\":\"127.0.0.1\",\"legalTenderAmount\":\"20\",\"legalTenderType\":\"EUR\",\"platform\":\"WEB\",\"userAgent\":\" \",\"userId\":\"8263281142\",\"valuationTenderAmount\":\"20.59827864\",\"valuationTenderType\":\"USDT\"}";
    }


    static void index(RestHighLevelClient esClient) {
        try {
            GetRequest getRequest = new GetRequest("risk-event-result-202305", "_doc", "G5KudocBpcaAoyw0zdR3");
            GetResponse response = esClient.get(getRequest, RequestOptions.DEFAULT);
            System.out.println(response.getId());
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void search(String indexName, String query, RestHighLevelClient client) throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("title", query));

        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            String title = hit.getSourceAsMap().get("title").toString();
            String content = hit.getSourceAsMap().get("content").toString();
            System.out.println(title + " - " + content);
        }
    }

//    public void querySearch() {
//        String searchText = "bike";
//
//        SearchResponse response = esClient.search(s -> s
//                        .index("products")
//                        .query(q -> q
//                                .match(t -> t
//                                        .field("name")
//                                        .query(searchText)
//                                )
//                        ),
//                Product.class
//        );
//
//        TotalHits total = response.hits().total();
//        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;
//
//        if (isExactResult) {
//            System.out.println("There are " + total.value() + " results");
//        } else {
//            System.out.println("There are more than " + total.value() + " results");
//        }
//
//        List<Hit<Product>> hits = response.hits().hits();
//        for (Hit<Product> hit : hits) {
//            Product product = hit.source();
//            System.out.println("Found product " + product.getSku() + ", score " + hit.score());
//        }
//    }
}
