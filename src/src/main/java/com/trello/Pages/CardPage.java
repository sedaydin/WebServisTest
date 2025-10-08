package com.trello.Pages;

import com.trello.Config.Config;
import com.trello.Models.Card;
import io.restassured.response.Response;

/**
 * Card işlemleri için Page Object sınıfı
 * Page Object Pattern kullanılmıştır
 */
public class CardPage extends BasePage {

    /**
     * Yeni bir kart oluşturur
     * @param listId Liste ID
     * @param cardName Kart adı
     * @param cardDesc Kart açıklaması
     * @return Card objesi
     */
    public Card createCard(String listId, String cardName, String cardDesc) {
        System.out.println("\n Creating the Card");

        addQueryParam("idList", listId);
        addQueryParam("name", cardName);

        if (cardDesc != null && !cardDesc.isEmpty()) {
            addQueryParam("desc", cardDesc);
        }

        Response response = sendPostRequest(Config.CARDS_ENDPOINT);
        logResponse(response);

        Card card = response.as(Card.class);
        card.printCardInfo();

        return card;
    }

    /**
     * Kart bilgilerini getirir
     * @param cardId Kart ID
     * @return Card objesi
     */
    public Card getCard(String cardId) {
        Response response = sendGetRequest(Config.CARDS_ENDPOINT + "/" + cardId);
        return response.as(Card.class);
    }

    /**
     * Kartı günceller
     * @param cardId Kart ID
     * @param newName Yeni kart adı
     * @param newDesc Yeni kart açıklaması
     * @return Card objesi
     */
    public Card updateCard(String cardId, String newName, String newDesc) {
        System.out.println("\n Updating The Card");
        System.out.println("Updated Kart ID: " + cardId);

        if (newName != null && !newName.isEmpty()) {
            addQueryParam("name", newName);
        }

        if (newDesc != null && !newDesc.isEmpty()) {
            addQueryParam("desc", newDesc);
        }

        Response response = sendPutRequest(Config.CARDS_ENDPOINT + "/" + cardId);
        logResponse(response);

        Card card = response.as(Card.class);
        card.printCardInfo();

        return card;
    }

    /**
     * Kartı siler
     * @param cardId Kart ID
     * @return Response
     */
    public Response deleteCard(String cardId) {
        System.out.println("\n Deleting the Card");
        System.out.println("Deleted Kart ID: " + cardId);

        Response response = sendDeleteRequest(Config.CARDS_ENDPOINT + "/" + cardId);
        logResponse(response);

        return response;
    }
}