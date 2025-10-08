package trello;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trello.Models.Board;
import com.trello.Models.Card;
import com.trello.Models.TList;
import com.trello.Pages.BoardPage;
import com.trello.Pages.CardPage;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Trello API test senaryoları
 * JUnit 5 kullanılarak test sınıfı oluşturulmuştur
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TrelloApiTest {

    private static BoardPage boardPage;
    private static CardPage cardPage;
    private static Board createdBoard;
    private static List<Card> createdCards;
    private static String listId;

    @BeforeAll
    public static void setup() {
        System.out.println("ok");

        boardPage = new BoardPage();
        cardPage = new CardPage();
        createdCards = new ArrayList<>();
    }

    @Test
    @Order(1)
    @DisplayName(" Create First Board Test")
    public void test01_CreateBoard() {
        // Board oluştur
        createdBoard = boardPage.createBoard("Test Automation Board - " + System.currentTimeMillis());

        // Doğrulamalar
        assertNotNull(createdBoard, "Board Created Successfully!");
        assertNotNull(createdBoard.getId(), "Board ID empty!");
        assertFalse(createdBoard.getName().isEmpty(), "Board name  empty!");

        System.out.println("Board Created Successfully!");

        // Board'un default listesini al
        Response response = boardPage.getBoardLists(createdBoard.getId());
        assertEquals(200, response.getStatusCode(), "Could not fetch the list!");

        Gson gson = new Gson();
        List<TList> lists = gson.fromJson(
                response.getBody().asString(),
                new TypeToken<List<TList>>(){}.getType()
        );

        assertFalse(lists.isEmpty(), "No list found in the board!");
        listId = lists.get(0).getId();

        System.out.println("Liste ID: " + listId);
    }

    @Test
    @Order(2)
    @DisplayName("Create Second Board Test")
    public void test02_CreateTwoCards() {
        assertNotNull(listId, "List ID Can not Found!");

        // İlk kartı oluştur
        Card card1 = cardPage.createCard(
                listId,
                "Test Kartı 1",
                "Bu birinci test kartıdır"
        );
        createdCards.add(card1);

        // İkinci kartı oluştur
        Card card2 = cardPage.createCard(
                listId,
                "Test Kartı 2",
                "Bu ikinci test kartıdır"
        );
        createdCards.add(card2);

        // Doğrulamalar
        // Validations
        assertEquals(2, createdCards.size(), "Exactly two cards should have been created!");
        assertNotNull(card1.getId(), "First card ID is null!");
        assertNotNull(card2.getId(), "Second card ID is null!");

        System.out.println("\n✓ Two cards have been successfully created!");
        System.out.println("Total Number of Cards: " + createdCards.size());

    }

    @Test
    @Order(3)
    @DisplayName("3. Random Card Update Test")
    public void test03_UpdateRandomCard() {
        assertFalse(createdCards.isEmpty(), "No card found to update!");

        // Random bir kart seç
        Random random = new Random();
        int randomIndex = random.nextInt(createdCards.size());
        Card selectedCard = createdCards.get(randomIndex);
        System.out.println("\n=== A Random Card Has Been Selected ===");
        System.out.println("Selected Index: " + randomIndex);
        System.out.println("Selected Card: " + selectedCard.getName());


        // kart güncelleme
        Card updatedCard = cardPage.updateCard(
                selectedCard.getId(),
                selectedCard.getName() + " - UPDATED",
                "This card has been updated - " + System.currentTimeMillis()
        );

// Validations
        assertNotNull(updatedCard, "Card could not be updated!");
        assertTrue(updatedCard.getName().contains("UPDATED"),
                "Card name was not updated!");

// Update the list
        createdCards.set(randomIndex, updatedCard);

        System.out.println("✓ Card has been successfully updated!");

    }

    @Test
    @Order(4)
    @DisplayName("4. Delete Cards Test")
    public void test04_DeleteCards() {
        assertFalse(createdCards.isEmpty(), "No cards found to delete!");

        System.out.println("\n Deleting All Cards....");

        for (Card card : createdCards) {
            Response response = cardPage.deleteCard(card.getId());
            assertEquals(200, response.getStatusCode(),
                    "Failed to delete card! Card ID: " + card.getId());
        }

        System.out.println("✓ All cards have been successfully deleted!");
        System.out.println("Number of deleted cards: " + createdCards.size());
    }

    @Test
    @Order(5)
    @DisplayName("5. Delete Board Test")
    public void test05_DeleteBoard() {
        assertNotNull(createdBoard, "No board found to delete!");

        Response response = boardPage.deleteBoard(createdBoard.getId());
        assertEquals(200, response.getStatusCode(), "Failed to delete the board!");

        System.out.println("✓ Board has been successfully deleted!");
    }


    @AfterAll
    public static void tearDown() {
        System.out.println("ok");
    }
}