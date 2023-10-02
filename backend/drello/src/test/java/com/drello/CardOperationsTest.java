package com.drello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.drello.model.Attachment;
import com.drello.model.Card;
import com.drello.model.Comment;
import com.drello.model.Label;
import com.drello.model.Member;
import com.drello.service.ICardService;

@SpringBootTest
public class CardOperationsTest {
    @Autowired
    private ICardService cardService;

    @Test
    public void verifySetUp() {
        assertNotNull(cardService);
    }

    @BeforeEach
    public void setUp() {

        Member member01 = new Member("651842e8ac884c65e4ee256c", "author01", "author@gmail.com",
                "https://example.com/author.zip");

        Member member02 = new Member("651842e8ac884c65e4ee289c", "author02", "author02@gmail.com",
                "https://example.com/author02.zip");

        // Card 1: Product Page
        Card card1 = new Card("Product Page Design", "Design the product page for our e-commerce website", "List 1 ID",
                "To-Do", "product-page.jpg");
        card1.setId("651843b189709f17866d096e");

        card1.getComments().add(
                new Comment("Let's use a clean and modern design for this page.", member01,
                        "651843b189709f17866d096e"));
        card1.getAttachments().add(new Attachment("Product Images", "https://example.com/product-images.zip"));
        card1.getLabels().add(new Label("Design", "blue"));
        card1.getLabels().add(new Label("Urgent", "red"));

        // Card 2: Shopping Cart Functionality
        Card card2 = new Card("Shopping Cart Functionality", "Implement shopping cart functionality for our website",
                "List 1 ID", "To-Do", "shopping-cart.jpg");
        card2.getComments()
                .add(new Comment("Developer", member02,
                        "6518434f6d8c88126acf8bb6"));
        card2.getAttachments()
                .add(new Attachment("Shopping Cart API Documentation", "https://example.com/shopping-cart-api.pdf"));
        card2.getLabels().add(new Label("Development", "green"));
        card2.getLabels().add(new Label("High Priority", "red"));

        card2.setId("6518434f6d8c88126acf8bb6");
        // Card 3: Marketing Campaign
        Card card3 = new Card("Marketing Campaign", "Plan and execute a marketing campaign for the new product launch",
                "List 2 ID", "In Progress", "marketing-campaign.jpg");
        card3.setId("651843c13c7ba1032e82901a");

        card3.getComments().add(new Comment("Marketing Manager", member01,
                "651843c13c7ba1032e82901a"));
        card3.getAttachments().add(new Attachment("Campaign Assets", "https://example.com/marketing-assets.zip"));
        card3.getLabels().add(new Label("Marketing", "purple"));
        card3.getLabels().add(new Label("Campaign", "orange"));

        cardService.save(card1);
        cardService.save(card2);
        cardService.save(card3);

    }

    @Test
    public void verifyAchieved() {
        Card card = cardService.findByIdCard("651843b189709f17866d096e");
        assertTrue(card.isStatus());
        cardService.archive("651843b189709f17866d096e");
        card = cardService.findByIdCard("651843b189709f17866d096e");

        assertFalse(card.isStatus());
    }

    @Test
    public void verifyAddLabel() {
        Card card = cardService.findByIdCard("651843b189709f17866d096e");
        Label actualLabel = card.getLabels().get(0);
        Label actualLabel01 = card.getLabels().get(1);
        Label actualLabel02 = null;
        assertEquals(new Label("Design", "blue"), actualLabel);
        assertEquals(new Label("Urgent", "red"), actualLabel01);

        card = cardService.addLabel("651843b189709f17866d096e", new Label("LOW", "yellow"));

        actualLabel = card.getLabels().get(0);
        actualLabel01 = card.getLabels().get(1);
        actualLabel02 = card.getLabels().get(2);

        assertEquals(new Label("Design", "blue"), actualLabel);
        assertEquals(new Label("Urgent", "red"), actualLabel01);
        assertEquals(new Label("LOW", "yellow"), actualLabel02);
    }

    @Test
    public void verifyRemoveLabel() {
        Card card = cardService.findByIdCard("651843b189709f17866d096e");
        Label actualLabel = card.getLabels().get(0);
        Label actualLabel01 = card.getLabels().get(1);

        assertEquals(new Label("Design", "blue"), actualLabel);
        assertEquals(new Label("Urgent", "red"), actualLabel01);

        card = cardService.removeLabel("651843b189709f17866d096e", new Label("Design", "blue"));

        actualLabel = card.getLabels().get(0);
        int amount = card.getLabels().size();
        assertEquals(1, amount);
        assertEquals(new Label("Urgent", "red"), actualLabel01);
    }

    @Test
    public void verifyAddComment() {
        Card card = cardService.findByIdCard("651843b189709f17866d096e");

        Member member01 = new Member("651842e8ac884c65e4ee256c", "author01", "author@gmail.com",
                "https://example.com/author.zip");

        Comment newComment = new Comment("Developer", member01, "651843b189709f17866d096e");

        card = cardService.addComment("651843b189709f17866d096e", newComment);

        assertEquals(2, card.getComments().size());
    }

    @Test
    public void verifyRemoveComment() {
        Card card = cardService.findByIdCard("651843b189709f17866d096e");

        Member member01 = new Member("651842e8ac884c65e4ee256c", "author01", "author@gmail.com",
                "https://example.com/author.zip");

        Comment newComment = new Comment("Developer", member01, "651843b189709f17866d096e");
        newComment.setId("651843b189709f188887d096e");
        card = cardService.addComment("651843b189709f17866d096e", newComment);

        card = cardService.removeComment("651843b189709f17866d096e", "651843b189709f188887d096e");
        assertEquals(1, card.getComments().size());

    }

    @Test
    public void verifyUpdateComment() {
        Card card = cardService.findByIdCard("651843b189709f17866d096e");
        card.getComments().get(0).setId("651843b189709f188887d096e");
        cardService.save(card);
        card = cardService.updateComment("651843b189709f17866d096e", "651843b189709f188887d096e", "updated");

        assertEquals(1, card.getComments().size());
        assertEquals("updated", card.getComments().get(0).getContent());
    }

    @AfterEach
    public void tearDown() {
        cardService.delete("651843b189709f17866d096e");
        cardService.delete("6518434f6d8c88126acf8bb6");
        cardService.delete("651843c13c7ba1032e82901a");
    }

}
