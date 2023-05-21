/*
 * EGR227 HW1 HTML Validator Solution, written by Christopher Nugent, Dr. Han
 *
 * The HtmlValidator class provides a simple way to verify the validity of HTML files
 * with simple, stack-based tag matching. Output is indented based on tag depth, and errors
 * are printed in-line.
 */

import java.util.*;

public class HtmlValidator {

    private static final String INDENTATION_MARKER = "    ";

    private Queue<HtmlTag> tags;

    /**
     * Create an empty HtmlValidator
     */
    public HtmlValidator() {
        tags = new LinkedList<>();
    }



    /**
     * Add a tag to the end of the HtmlValidator
     * @param tag the tag to be added
     */
    public void addTag(HtmlTag tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Cannot add a null tag");
        }
        tags.add(tag);
    }

    /**
     * Get a deep copy of the tags in the HtmlValidator
     * @return A deep copy of the tags in the HmtlValidator
     */
    public Queue<HtmlTag> getTags() {
        // return a deep copy of tags
        return new LinkedList<>(tags);
    }

    /**
     * Create an HtmlValidator with the content of tags
     * @param tags the content of new HtmlValidator
     */
    public HtmlValidator(Queue<HtmlTag> tags) {
        if (tags == null) {
            throw new IllegalArgumentException("Initial tags cannot be null.");
        }
        // Create a deep copy of the input queue
        this.tags = new LinkedList<>(tags);
    }

    /**
     * Remove all tags matching element
     * @param element the type of tag to remove
     */
    public void removeAll(String element) {
        if (element == null) throw new IllegalArgumentException();
        tags.removeIf(tag -> tag.getElement().equalsIgnoreCase(element));

    }

    /**
     * Print formatted HTML based on the content of the HtmlValidator. The output is indented according to tag depth.
     *
     * Unexpected and unclosed tags are printed as error messages at 0 depth
     */
    public void validate() {
        Stack<HtmlTag> openTags = new Stack<>();
        for(int i = 0; i < tags.size(); i++) {
            // for(HtmlTag tag : tags){ is NOT allowed by the spec
            // Because we can't use a foreach loop :l
            HtmlTag tag = tags.remove();
            tags.add(tag);

            if (tag.isSelfClosing()) {
                printWithIndentation(tag, openTags.size());
            } else if (tag.isOpenTag()) {
                printWithIndentation(tag, openTags.size());
                openTags.push(tag);
            } else if (!openTags.isEmpty() && tag.matches(openTags.peek())) { // By exhaustion, the tag must be a closing tag
                // Closing tag should be at same depth as opening, so we pop before printing
                openTags.pop();
                printWithIndentation(tag, openTags.size());
            } else {
                System.out.println("ERROR unexpected tag: " + tag.toString());
            }
        }
        // Deal with unclosed tags
        while (!openTags.isEmpty()) {
            HtmlTag tag = openTags.pop();
            System.out.println("ERROR unclosed tag: " + tag.toString());
        }
    }

    // Helper function to make printing at given indentation more convenient
    private static void printWithIndentation(HtmlTag tag, int indentationLevel) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentationLevel; i++) {
            sb.append(INDENTATION_MARKER);

        }

        sb.append(tag.toString());
        System.out.println(sb.toString());

    }
}