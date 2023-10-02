package com.drello;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.drello.exceptions.EntityNotFoundException;
import com.drello.model.Attachment;
import com.drello.model.Card;
import com.drello.service.ICardService;

@SpringBootTest
public class CardCRUDTest {
    @Autowired
    private ICardService cardService;

    @Test
    public void verifySetUp() {
        assertNotNull(cardService);
    }

    @Test
    public void saveCardTest() {
        Card card = new Card("title", "description", "listId", "listTitle", "imageCover");
        card.getAttachments().add(new Attachment("attachment", "file to test"));
        Card savedCard = cardService.save(card);
        String id = savedCard.getId();
        assertDoesNotThrow(() -> {
            cardService.findByIdCard(id);
        });

        String expectedTitle = card.getTitle();
        String savedTitle = savedCard.getTitle();
        assertEquals(expectedTitle, savedTitle);

        String expectedDescription = card.getDescription();
        String savedDescription = savedCard.getDescription();
        assertEquals(expectedDescription, savedDescription);

        cardService.delete(id);

    }

    @Test
    public void deleteTest() {
        Card card = new Card();
        Card cardSaved = cardService.save(card);
        String id = cardSaved.getId();

        assertDoesNotThrow(() -> {
            cardService.findByIdCard(id);
        });

        cardService.delete(id);

        assertThrows(EntityNotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                cardService.findByIdCard(id);
            }
        }, "Card with id " + id + " not found");
    }

}
