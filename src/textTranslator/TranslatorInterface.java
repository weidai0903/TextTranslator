package textTranslator;
/**
 * Every translation class will implement this interface
 * @author Wei Dai
 *
 */
public interface TranslatorInterface{
	/**
	 * get the name of translator
	 * @return String
	 */
	String getName();
	/**
	 * get the description of the translator
	 * @return String
	 */
	String getDescription();
	/**
	 * translate the text
	 * @param text
	 * @return String
	 */
	String translate(String text);
}
