package util;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;
/**
 * Sanitization utility methods for text values given by users.
 */
//TODO: Might want another policy for *no* tags for things like names?
public final class Sanitization {

  /**
   * Allows common table elements.
   *
   * Most of this is borrowed from the master branch of the github repo for
   * OWASP's sanitization library.
   */
  public static final PolicyFactory TABLES = new HtmlPolicyBuilder()
                                                 .allowStandardUrlProtocols()
                                                 .allowElements(
                                                     "table", "tr", "td",
                                                     "th", "colgroup",
                                                     "caption", "col",
                                                     "thead", "tbody",
                                                     "tfoot")
                                                 .allowAttributes("summary").onElements("table")
                                                 .allowAttributes(
                                                     "align", "valign")
                                                 .onElements(
                                                     "table", "tr",
                                                     "td", "th", "colgroup",
                                                     "col", "thead",
                                                     "tbody", "tfoot")
                                                 .allowAttributes(
                                                     "colspan", "rowspan")
                                                 .onElements("td")
                                                 .allowTextIn("table")  // WIDGY
                                                 .toFactory();
  public static final PolicyFactory SHORT_POLICY = Sanitizers.FORMATTING;
  public static final PolicyFactory LONG_POLICY = Sanitizers.FORMATTING.and(
                                                  Sanitizers.BLOCKS).and(
                                                  TABLES);

  /**
   * Sanitizes an input string of all tags except for formatting tags.
   */
  public static String sanitizeShortText(String inputText) {
    return SHORT_POLICY.sanitize(inputText);
  }

  /**
   * Sanitizes an input string of all tags except for formatting, block, and
   * table tags (currently does not allow rowspan/colspan attributes).
   */
  public static String sanitizeLongText(String inputText) {
    return LONG_POLICY.sanitize(inputText);
  }

  /* Should not be instantiated */
  private Sanitization() { }
}
