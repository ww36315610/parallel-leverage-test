package bitget.api.risk;

import bitget.api.object.BasicApi;

import java.io.IOException;

public class TestApi extends BasicApi {
    public void event_check_method(){
        String url = "http://172.23.22.165:8080/inner/v1/dataRisk/EventCheckController/eventCheck";
        String json = "{\n" +
                "    \"bizId\": \"28a64bfa-9c6d-4a92-8ed7-777d32133010\",\n" +
                "    \"eventCode\": \"CardBuyCoin\",\n" +
                "    \"params\": \"{\\\"amountToU\\\":\\\"21.63682588\\\",\\\"appName\\\":\\\"BITGET\\\",\\\"applyId\\\":\\\"1043744195833057280\\\",\\\"billAddress\\\":\\\" Avenida Francesc Macía 51 2 4\\\",\\\"billCity\\\":\\\"Sabadell \\\",\\\"billCountry\\\":\\\"ES\\\",\\\"billPostCode\\\":\\\"08207\\\",\\\"billType\\\":\\\"BUY\\\",\\\"cardBank\\\":\\\"UAB FINANSINES PASLAUGOS CONTIS\\\",\\\"cardBin\\\":\\\"474503\\\",\\\"cardCountry\\\":\\\"LT\\\",\\\"cardHolderName\\\":\\\"David\\\",\\\"cardId\\\":\\\"src_k3o4qqpsxq2eljldtztsuan46a\\\",\\\"cardNoLastFour\\\":\\\"1974\\\",\\\"cardOrganization\\\":\\\"001\\\",\\\"cardType\\\":\\\"DEBIT\\\",\\\"channelProvider\\\":\\\"CHECKOUT\\\",\\\"currentCardNum\\\":2,\\\"deviceId\\\":\\\"a-241622267-1683571640710-239255832\\\",\\\"deviceToken\\\":\\\"64688db5FjsLwPhsZEw3iIxVqFVHEJoYVuC1h4W3\\\",\\\"eventTime\\\":\\\"2023-05-20 17:12:09\\\",\\\"expiryMonth\\\":\\\"5\\\",\\\"expiryYear\\\":\\\"2024\\\",\\\"fee\\\":\\\"0.36\\\",\\\"feeToU\\\":\\\"0.38946287\\\",\\\"fingerPrint\\\":\\\"E47612F47CB1DFAF673DA057081C9A84CBBC717386C6F2C255B2B82A48C67F81\\\",\\\"historyCardNum\\\":2,\\\"ip\\\":\\\"31.4.207.110\\\",\\\"lan\\\":\\\"es-IN\\\",\\\"legalTenderAmount\\\":\\\"20\\\",\\\"legalTenderType\\\":\\\"EUR\\\",\\\"platform\\\":\\\"ANDROID\\\",\\\"userAgent\\\":\\\"{\\\\\\\"browser\\\\\\\":\\\\\\\"CHROME_MOBILE\\\\\\\",\\\\\\\"browserVersion\\\\\\\":{\\\\\\\"majorVersion\\\\\\\":\\\\\\\"113\\\\\\\",\\\\\\\"minorVersion\\\\\\\":\\\\\\\"0\\\\\\\",\\\\\\\"version\\\\\\\":\\\\\\\"113.0.5672.77\\\\\\\"},\\\\\\\"id\\\\\\\":251727716,\\\\\\\"operatingSystem\\\\\\\":\\\\\\\"ANDROID1\\\\\\\"}\\\",\\\"userId\\\":\\\"1224034403\\\",\\\"valuationTenderAmount\\\":\\\"20.27177893\\\",\\\"valuationTenderType\\\":\\\"USDT\\\"}\"\n" +
                "}";
        try {
            System.out.println(postAPI(url, json));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void feature_method(){
        String url = "http://172.23.30.81:8080/inner/v1/dataFeature/featureSearch/search";
        String json = "{\n" +
                "    \"featureCodeList\": [\n" +
                "        \"offLine_cardInCnt7d\",\n" +
                "        \"offLine_cardInCnt30d\",\n" +
                "        \"offLine_cardInCnt365d\",\n" +
                "        \"offLine_accuCardInCnt\",\n" +
                "        \"offLine_cardInAmt7d\",\n" +
                "        \"offLine_cardInAmt30d\",\n" +
                "        \"offLine_cardInAmt365d\",\n" +
                "        \"offLine_accuCardInAmt\",\n" +
                "        \"offLine_p2pInCnt30d\",\n" +
                "        \"offLine_p2pInAmt30d\",\n" +
                "        \"offLine_firstP2pInTime\",\n" +
                "        \"offLine_p2pInCoinCnt7d\",\n" +
                "        \"offLine_p2pIoCoinCnt7d\",\n" +
                "        \"offLine_p2pOutCnt30d\",\n" +
                "        \"offLine_p2pOutAmt30d\",\n" +
                "        \"offLine_p2pOutCoinCnt7d\",\n" +
                "        \"offLine_firstP2pOutTime\",\n" +
                "        \"offLine_accuSpotTradeFeeAmt\",\n" +
                "        \"offLine_spotTradeFeeAmt30d\",\n" +
                "        \"offLine_accuCntrTradeFeeAmt\",\n" +
                "        \"offLine_cntrTradeFeeAmt30d\",\n" +
                "        \"offLine_firstChainInTime\",\n" +
                "        \"offLine_firstChainOutTime\",\n" +
                "        \"offLine_p2pBusinessLevel\",\n" +
                "        \"offLine_regType\",\n" +
                "        \"offLine_inviteUserId\",\n" +
                "        \"offLine_regDeviceId\",\n" +
                "        \"offLine_UserKyc_FAIL_30D_Count\",\n" +
                "        \"offLine_UserKyc_FAIL_7D_Count\"\n" +
                "    ],\n" +
                "    \"featureParams\": {\n" +
                "        \"deviceId\": \"eb179bf559354aa8e6e9209ffd48e890\",\n" +
                "        \"userId\": \"3622359887\",\n" +
                "        \"cardId\": \"src_ywflh2ebzqherd3ugadatk2kx4\"\n" +
                "    }\n" +
                "}";
        try {
            System.out.println(postAPI(url, json));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        TestApi testApi = new TestApi();
        testApi.feature_method();
    }
}
