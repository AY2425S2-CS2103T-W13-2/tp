package seedu.address.model.client;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private Optional<Frequency> frequency;
    private Optional<ProductPreference> productPreference;

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address,
                  Set<Tag> tags, Optional<Frequency> frequency, Optional<ProductPreference> productPreference) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        if (frequency.isEmpty() && productPreference.isPresent()) {
            this.frequency = Optional.of(new Frequency(1));
            this.productPreference = productPreference;
        } else {
            this.frequency = frequency;
            this.productPreference = productPreference;
        }
    }

    /**
     * Constructs a {@code Client} with the specified mandatory fields:
     * name, phone, email, address, and tags. The {@code frequency} and
     * {@code productPreference} fields are initialized as empty.
     *
     * @param name    The client's name.
     * @param phone   The client's phone number.
     * @param email   The client's email address.
     * @param address The client's mailing address.
     * @param tags    A set of tags associated with the client.
     * @throws NullPointerException If any of the provided parameters are null.
     */
    public Client(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.frequency = Optional.empty();
        this.productPreference = Optional.empty();
    }


    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Optional<Frequency> getFrequency() {
        return frequency;
    }

    public Optional<ProductPreference> getProductPreference() {
        return productPreference;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Client otherClient) {
        if (otherClient == this) {
            return true;
        }

        return otherClient != null
                && otherClient.getName().equals(getName())
                && otherClient.getPhone().equals(getPhone())
                && otherClient.getAddress().equals(getAddress());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Client)) {
            return false;
        }

        Client otherClient = (Client) other;
        return name.equals(otherClient.name)
                && phone.equals(otherClient.phone)
                && email.equals(otherClient.email)
                && address.equals(otherClient.address)
                && tags.equals(otherClient.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }

}
