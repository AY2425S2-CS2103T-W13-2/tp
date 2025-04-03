package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Description;
import seedu.address.model.client.Email;
import seedu.address.model.client.Frequency;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Priority;
import seedu.address.model.client.ProductPreference;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses an {@code Optional<String>} into an {@code Optional<ProductPreference>}.
     * <p>
     * Leading and trailing whitespaces will be trimmed. If the input value is empty,
     * this method returns {@code Optional.empty()}.
     *
     * @param productPreference The optional string that may contain a product preference.
     * @return An Optional containing a {@code ProductPreference} if the string is present,
     *         or {@code Optional.empty()} if not.
     * @throws ParseException If the string is invalid and cannot form a valid ProductPreference.
     */
    public static Optional<ProductPreference> parseProductPreference(
            Optional<String> productPreference, Optional<Frequency> frequency) throws ParseException {
        requireNonNull(productPreference);
        int freqValue = frequency.map(x -> x.frequency).orElse(0);

        if (productPreference.isEmpty()) {
            return Optional.empty();
        }

        String trimmed = productPreference.get().trim();
        if (trimmed.isEmpty()) {
            throw new ParseException("Product preference cannot be empty or whitespace only");
        }

        return Optional.of(new ProductPreference(trimmed, new Frequency(freqValue)));
    }

    /**
     * Parses an {@code Optional<String>} into an {@code Optional<Frequency>}.
     * <p>
     * Leading and trailing whitespaces will be trimmed. If the input value is empty,
     * this method returns {@code Optional.empty()}. Otherwise, it attempts to parse
     * the trimmed string as an integer, then wraps it in a {@code Frequency} object.
     *
     * @param frequency The optional string that may represent a frequency.
     * @return An Optional containing a {@code Frequency} if the string is present and valid,
     *         or {@code Optional.empty()} if not.
     * @throws ParseException If the string cannot be parsed into an integer or otherwise invalid.
     */
    public static Optional<Frequency> parseFrequency(
            Optional<String> frequency) throws ParseException {
        requireNonNull(frequency);
        if (!frequency.isPresent()) {
            return Optional.empty();
        }

        int freqValue;
        try {
            freqValue = Integer.parseInt(frequency.get());
        } catch (NumberFormatException e) {
            throw new ParseException(Frequency.MESSAGE_CONSTRAINTS);
        }

        if (!Frequency.isValidFrequency(freqValue)) {
            throw new ParseException(Frequency.MESSAGE_CONSTRAINTS);
        }

        return Optional.of(new Frequency(freqValue));
    }



    /**
     * Parses an {@code Optional<String>} into an {@code Optional<Description>}.
     * <p>
     * Leading and trailing whitespaces will be trimmed. If the input value is empty,
     * this method returns {@code Optional.empty()}. Otherwise, it attempts to wrap it
     * in a {@code Description} object.
     *
     * @param description The optional string that may represent a description.
     * @return An Optional containing a {@code Description} if the string is present,
     *         or {@code Optional.empty()} if not.
     */
    public static Optional<Description> parseDescription(
            Optional<String> description) {
        requireNonNull(description);
        return description
                .map(String::trim)
                .map(Description::new);
    }


    /**
     * Parses an {@code Optional<String>} into an {@code Optional<Priority>}.
     * <p>
     * Leading and trailing whitespaces will be trimmed. If the input value is empty,
     * this method returns {@code Optional.empty()}. Otherwise, it attempts to parse
     * the trimmed string as an integer, then wraps it in a {@code Priority} object.
     *
     * @param priority The optional string that may represent a priority.
     * @return An Optional containing a {@code Priority} if the string is present and valid,
     *         or {@code Optional.empty()} if not.
     * @throws ParseException If the string cannot be parsed into an integer or otherwise invalid.
     */
    public static Optional<Priority> parsePriority(Optional<String> priority) throws ParseException {
        requireNonNull(priority);

        if (!priority.isPresent()) {
            return Optional.empty();
        }

        String trimmedValue = priority.get().trim();
        if (trimmedValue.isEmpty()) {
            return Optional.empty();
        }

        int priorityValue;

        try {
            priorityValue = Integer.parseInt(trimmedValue);
        } catch (NumberFormatException e) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS_INT);
        }

        if (!Priority.isValidIntegerPriority(priorityValue)) {
            throw new ParseException(Priority.MESSAGE_CONSTRAINTS_INT);
        }

        return Optional.of(Priority.fromInt(priorityValue));
    }
}
