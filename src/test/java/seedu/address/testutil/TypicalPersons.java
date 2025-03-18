package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.client.Client;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Client ALICE = new ClientBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withProductPreference("shampoo")
            .withFrequency(10)
            .build();
    public static final Client BENSON = new ClientBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withProductPreference("cherry shampoo")
            .withFrequency(9)
            .build();
    public static final Client CARL = new ClientBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withProductPreference("coffee book")
            .withFrequency(4)
            .build();
    public static final Client DANIEL = new ClientBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withTags("friends")
            .withProductPreference("recipe book")
            .withFrequency(2)
            .build();
    public static final Client ELLE = new ClientBuilder().withName("Elle Meyer")
            .withPhone("19482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withProductPreference("tea cup")
            .withFrequency(5)
            .build();
    public static final Client FIONA = new ClientBuilder().withName("Fiona Kunz")
            .withPhone("19482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withProductPreference("coffee cup")
            .withFrequency(6)
            .build();
    public static final Client GEORGE = new ClientBuilder().withName("George Best")
            .withPhone("19482442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withProductPreference("tea bag")
            .withFrequency(3)
            .build();

    // Manually added
    public static final Client HOON = new ClientBuilder().withName("Hoon Meier")
            .withPhone("84827424")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withProductPreference("shampoo")
            .withFrequency(5)
            .build();
    public static final Client IDA = new ClientBuilder().withName("Ida Mueller")
            .withPhone("84872131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withProductPreference("coffee")
            .withFrequency(2)
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Client AMY = new ClientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Client BOB = new ClientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Client client : getTypicalClients()) {
            ab.addPerson(client);
        }
        return ab;
    }

    public static List<Client> getTypicalClients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    /**
     * Returns a {@code List} with all the typical clients sorted by frequency.
     */
    public static List<Client> getSortedTypicalClients() {
        List<Client> typicalClients = getTypicalClients();
        typicalClients.sort((c1, c2) ->
                Integer.compare(c2.getFrequency().frequency, c1.getFrequency().frequency)
        );
        return typicalClients;
    }
}
