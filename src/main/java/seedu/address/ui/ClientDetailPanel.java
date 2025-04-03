// ClientDetailPanel.java
package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.client.Client;
import seedu.address.model.client.Description;
import seedu.address.model.client.ProductPreference;

/**
 * Ui component that displays the expanded client details
 */
public class ClientDetailPanel extends UiPart<Region> {
    private static final String FXML = "ClientDetailPanel.fxml";

    @FXML private Label name;
    @FXML private Label phone;
    @FXML private Label email;
    @FXML private Label address;
    @FXML private Label frequency;
    @FXML private Label productPreference;
    @FXML private VBox productPreferenceSection;
    @FXML private Label description;
    @FXML private VBox descriptionSection;
    @FXML private Label priority; // Add priority label
    @FXML private VBox prioritySection;

    /**
     * Constructor that takes in a client in order to display target's details
     * @param client
     */
    public ClientDetailPanel(Client client) {
        super(FXML);
        name.setText(client.getName().fullName);
        phone.setText("Phone: " + client.getPhone().value);
        email.setText("Email: " + client.getEmail().value);
        address.setText("Address: " + client.getAddress().value);
        
        // Display priority if available
        if (client.getPriority() != null) {
            priority.setText("Priority: " + client.getPriority().toString());
        } else {
            prioritySection.setVisible(false);
            prioritySection.setManaged(false);
        }
        
        if (client.getProductPreference().isPresent()) {
            productPreference.setText("Preferred Products: " + client.getProductPreference()
                    .map(ProductPreference::toString).orElse(""));
            frequency.setText("Purchase Frequency: " + client.getProductPreference().get().getFrequency());
        } else {
            productPreferenceSection.setVisible(false);
            productPreferenceSection.setManaged(false);
        }
        if (client.getDescription().isPresent()) {
            description.setText("Description: " + client.getDescription()
                    .map(Description::toString).orElse(""));
        } else {
            descriptionSection.setVisible(false);
            descriptionSection.setManaged(false);
        }
    }
}
