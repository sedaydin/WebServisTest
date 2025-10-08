package com.trello.Pages;

import com.trello.Config.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Page Object Pattern için Base sınıf
 * Tüm API sayfaları bu sınıftan türetilecektir
 * OOP prensiplerine uygun olarak inheritance kullanılmıştır
 */
public abstract class BasePage {

    protected RequestSpecification requestSpec;

    public BasePage() {
        RestAssured.baseURI = Config.BASE_URL;
        resetRequestSpec();
    }

    /**
     * RequestSpec'i sıfırlar ve yeni bir tane oluşturur
     */
    protected void resetRequestSpec() {
        this.requestSpec = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("key", Config.API_KEY)
                .queryParam("token", Config.API_TOKEN);
    }

    /**
     * GET request gönderir
     * @param endpoint API endpoint
     * @return Response
     */
    protected Response sendGetRequest(String endpoint) {
        Response response = requestSpec.get(endpoint);
        resetRequestSpec(); // Her istekten sonra sıfırla
        return response;
    }

    /**
     * POST request gönderir
     * @param endpoint API endpoint
     * @return Response
     */
    protected Response sendPostRequest(String endpoint) {
        Response response = requestSpec.post(endpoint);
        resetRequestSpec(); // Her istekten sonra sıfırla
        return response;
    }

    /**
     * PUT request gönderir
     * @param endpoint API endpoint
     * @return Response
     */
    protected Response sendPutRequest(String endpoint) {
        Response response = requestSpec.put(endpoint);
        resetRequestSpec(); // Her istekten sonra sıfırla
        return response;
    }

    /**
     * DELETE request gönderir
     * @param endpoint API endpoint
     * @return Response
     */
    protected Response sendDeleteRequest(String endpoint) {
        Response response = requestSpec.delete(endpoint);
        resetRequestSpec(); // Her istekten sonra sıfırla
        return response;
    }

    /**
     * Query parameter ekler
     * @param key Parametre adı
     * @param value Parametre değeri
     * @return BasePage
     */
    protected BasePage addQueryParam(String key, String value) {
        requestSpec.queryParam(key, value);
        return this;
    }

    /**
     * Response'u loglar
     * @param response Response objesi
     */
    protected void logResponse(Response response) {
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}