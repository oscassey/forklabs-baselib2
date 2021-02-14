package ca.forklabs.baselib2.text;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import ca.forklabs.baselib2.io.Files;
import ca.forklabs.baselib2.util.Comparators;
import ca.forklabs.baselib2.util.comparator.MagnitudeComparator;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * Class {@code CompositeNumberFormat} holds one primary number format and a list of secondary
 * number format.
 *
 * The formatting operation is done using the the primary number format.
 *
 * The parsing operation is done using the primary and all the secondary number formats and then
 * using result that is the most precise.
 *
 * By default, the result that is the most precise is the result whose magnitude is the greatest,
 * indicating that the parser managed to parse the greater number of characters (there are thoughts
 * of using the index from the parse position object instead).
 *
 * Number formats are usually not thread safe making this class not thread safe.
 */
public class CompositeNumberFormat extends NumberFormat {

//---------------------------
// Class variables
//---------------------------

    /** The default comparator. */
    private static final Comparator<Number> MAGNITUDE_COMPARATOR = Comparators.magnitude();


//---------------------------
// Instance variables
//---------------------------

    /** The primary number format, use for formatting. */
    private final NumberFormat _primaryNumberFormat;
    /** All the number format, the primary and the secondaries, used for parsing. */
    private final List<NumberFormat> _allNumberFormats = new LinkedList<>();
    /** The comparator to select the parsed value. */
    private final Comparator<Number> _comparator;


//---------------------------
// Constructors
//---------------------------

    /**
     * Creates a new {@code CompositeNumberFormat} from the primary number
     * format and secondary number formats, and using a
     * {@link MagnitudeComparator} to determine which parsed value to use.
     * @param  primaryNumberFormat  the primary number format.
     * @param  secondaryNumberFormats  the list of secondary number formats.
     */
    public CompositeNumberFormat(@NonNull NumberFormat primaryNumberFormat, NumberFormat... secondaryNumberFormats) {
        this(MAGNITUDE_COMPARATOR, primaryNumberFormat, secondaryNumberFormats);
    }

    /**
     * Creates a new {@code CompositeNumberFormat} from the primary number
     * format and secondary number formats, and using the specified comparator
     * to determine which parsed value to use.
     * @param  comparator  the comparator.
     * @param  primaryNumberFormat  the primary number format.
     * @param  secondaryNumberFormats  the list of secondary number formats.
     */
    public CompositeNumberFormat(@NonNull Comparator<Number> comparator, @NonNull NumberFormat primaryNumberFormat, NumberFormat... secondaryNumberFormats) {
        _comparator = comparator;
        _primaryNumberFormat = primaryNumberFormat;
        _allNumberFormats.add(primaryNumberFormat);
        _allNumberFormats.addAll(Arrays.asList(secondaryNumberFormats));
    }


//---------------------------
// format() is forwarded to the main number format
//---------------------------

    /**
     * Uses the primary number format to format the number.
     *
     * {@inheritDoc}
     */
    @Override
    public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
        var sb = _primaryNumberFormat.format(number, toAppendTo, pos);
        return sb;
    }

    /**
     * Uses the primary number format to format the number.
     *
     * {@inheritDoc}
     */
    @Override
    public StringBuffer format(long number, StringBuffer toAppendTo, FieldPosition pos) {
        var sb = _primaryNumberFormat.format(number, toAppendTo, pos);
        return sb;
    }


//---------------------------
// the complex parse()
//---------------------------

    /**
     * Asks each number format to parse, then return the value that the
     * comparator decides is the greatest.
     *
     * The logic behind the comparator is that if we are having an English
     * (with grouping disabled) and French number formats parsing the string
     * "3,1415926", the English number format with give 3 while the French one
     * will give us 3.1415926. Taking the greatest value indicates taking the
     * most precise value.
     *
     * <b>Note:</b> Would taking the number format whose parse position went
     * the farthest be a better metric?
     *
     * {@inheritDoc}
     */
    @Override
    public Number parse(String source, ParsePosition parsePosition) {
        // 1) parse using every number format each using a clone of the parsePosition
        //    we generate a list because we will go through it twice and we cannot
        //    do that with a stream
        var listOfParsedValuesWithTheirParsePosition = _allNumberFormats.stream()
                .map(nf -> {
                    var clonedParsePosition = clone(parsePosition);
                    var number = nf.parse(source, clonedParsePosition);
                    var pnwcp = new ParsedNumberWithCloneParsePosition(number, clonedParsePosition);
                    return pnwcp;
                })
                .collect(Collectors.toList());

        // 2) compare the signs, if they are not the same then throw NumberFormatException
        var distinctCount = listOfParsedValuesWithTheirParsePosition.stream()
                .map(pnwcp -> pnwcp.getNumber())
                .map(number -> number.doubleValue())
                .map(d -> Math.signum(d))
                .distinct()
                .count();
        if (distinctCount > 1L) {
            throw new NumberFormatException("Not all parsed values are of the same sign!");
        }

        // 3) compare the values, take the largest, update the parsePosition with the clone
        var optionalWinner = listOfParsedValuesWithTheirParsePosition.stream()
                .max((pnwcp1, pnwcp2) -> _comparator.compare(pnwcp1.getNumber(), pnwcp2.getNumber()));

        // there will always be a winner because we always have at least the main number format
        var winner = optionalWinner.get();

        // optionally log the results to compare magnitude and parse index
        log(source, parsePosition, winner, listOfParsedValuesWithTheirParsePosition);

        // copy the values from the winner parse position into the original parse position
        var cloneParsePosition = winner.getParsePosition();
        var cloneIndex = cloneParsePosition.getIndex();
        var cloneErrorIndex = cloneParsePosition.getErrorIndex();
        parsePosition.setIndex(cloneIndex);
        parsePosition.setErrorIndex(cloneErrorIndex);

        // 4) return the value
        var value = winner.getNumber();
        return value;
    }

    @Data
    @Accessors(prefix = {"_"})
    private static class ParsedNumberWithCloneParsePosition {
        @NonNull private final Number _number;
        @NonNull private final ParsePosition _parsePosition;
    }

    private ParsePosition clone(ParsePosition original) {
        var index = original.getIndex();
        var errorIndex = original.getErrorIndex();
        var clone = new ParsePosition(index);
        clone.setErrorIndex(errorIndex);
        return clone;
    }


//---------------------------
// Logging stuff, totally optional
//---------------------------

    private File _log = null;

    public void setLogFile(File file) {
        _log = file;
    }

    private void log(String numberToParse, ParsePosition parsePosition, ParsedNumberWithCloneParsePosition winner, List<ParsedNumberWithCloneParsePosition> candidates) {
        if (_log != null) {
            var headerLine = "=== Parsing text '" + numberToParse + "' with initial " +  parsePosition + " ===";
            var winnerLine = "* winner is value '" + winner.getNumber() + "' with " + winner.getParsePosition();
            var candidateLines = candidates.stream()
                    .map(x -> "* candidate is '" + x.getNumber() + "' with " + x.getParsePosition())
                    .collect(Collectors.toList());

            var lines = new LinkedList<String>();
            lines.add(headerLine);
            lines.add(winnerLine);
            lines.addAll(candidateLines);

            try {
                Files.appendAllLines(_log, lines);
            }
            catch (IOException ioe) {
                // do nothing on purpose
            }
        }
    }


//---------------------------
// Children need to be configured individually
//---------------------------

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public void setParseIntegerOnly(boolean value) {
        throw new UnsupportedOperationException("Configure the children number format individually!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public boolean isParseIntegerOnly() {
        throw new UnsupportedOperationException("Ask each children individually for its configuration!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public void setGroupingUsed(boolean newValue) {
        throw new UnsupportedOperationException("Configure the children number format individually!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public boolean isGroupingUsed() {
        throw new UnsupportedOperationException("Ask each children individually for its configuration!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public void setMaximumIntegerDigits(int newValue) {
        throw new UnsupportedOperationException("Configure the children number format individually!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public int getMaximumIntegerDigits() {
        throw new UnsupportedOperationException("Ask each children individually for its configuration!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public void setMinimumIntegerDigits(int newValue) {
        throw new UnsupportedOperationException("Configure the children number format individually!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public int getMinimumIntegerDigits() {
        throw new UnsupportedOperationException("Ask each children individually for its configuration!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public void setMaximumFractionDigits(int newValue) {
        throw new UnsupportedOperationException("Configure the children number format individually!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public int getMaximumFractionDigits() {
        throw new UnsupportedOperationException("Ask each children individually for its configuration!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public void setMinimumFractionDigits(int newValue) {
        throw new UnsupportedOperationException("Configure the children number format individually!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public int getMinimumFractionDigits() {
        throw new UnsupportedOperationException("Ask each children individually for its configuration!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public void setCurrency(Currency currency) {
        throw new UnsupportedOperationException("Configure the children number format individually!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public Currency getCurrency() {
        throw new UnsupportedOperationException("Ask each children individually for its configuration!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public void setRoundingMode(RoundingMode roundingMode) {
        throw new UnsupportedOperationException("Configure the children number format individually!");
    }

    /**
     * This composite number format is immutable, configure each children individually before
     * assigning to their composite instance.
     * @throw  UnsupportedOperationException   always.
     */
    @Override
    public RoundingMode getRoundingMode() {
        throw new UnsupportedOperationException("Ask each children individually for its configuration!");
    }

}
