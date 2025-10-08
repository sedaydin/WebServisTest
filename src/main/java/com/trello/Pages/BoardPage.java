package com.trello.Pages;

import com.trello.Config.Config;
import com.trello.Models.Board;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BoardPage extends BasePage {

    public Board createBoard(String boardName) {
        System.out.println("\n Creating the board...");

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("key", Config.API_KEY)
                .queryParam("token", Config.API_TOKEN)
                .queryParam("name", boardName)
                .post(Config.BASE_URL + Config.BOARDS_ENDPOINT);

        logResponse(response);

        Board board = response.as(Board.class);
        board.printBoardInfo();

        return board;
    }

    public Board getBoard(String boardId) {
        Response response = RestAssured.given()
                .queryParam("key", Config.API_KEY)
                .queryParam("token", Config.API_TOKEN)
                .get(Config.BASE_URL + Config.BOARDS_ENDPOINT + "/" + boardId);

        return response.as(Board.class);
    }

    public Board updateBoard(String boardId, String newName) {
        Response response = RestAssured.given()
                .queryParam("key", Config.API_KEY)
                .queryParam("token", Config.API_TOKEN)
                .queryParam("name", newName)
                .put(Config.BASE_URL + Config.BOARDS_ENDPOINT + "/" + boardId);

        return response.as(Board.class);
    }

    public Response deleteBoard(String boardId) {
        System.out.println("\n Deleting the Board");
        System.out.println("Deleted Board ID: " + boardId);

        Response response = RestAssured.given()
                .queryParam("key", Config.API_KEY)
                .queryParam("token", Config.API_TOKEN)
                .delete(Config.BASE_URL + Config.BOARDS_ENDPOINT + "/" + boardId);

        logResponse(response);
        return response;
    }

    public Response getBoardLists(String boardId) {
        return RestAssured.given()
                .queryParam("key", Config.API_KEY)
                .queryParam("token", Config.API_TOKEN)
                .get(Config.BASE_URL + Config.BOARDS_ENDPOINT + "/" + boardId + "/lists");
    }
}