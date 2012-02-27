package textTranslator;
/**
 * Makes no changes to the input. 
 * @author Wei Dai
 *
 */

public class IdentityTranslator implements TranslatorInterface{
	private String name="Identity Translator";
	private String discription="Makes no changes to the input.";
	public String getName() {
		return name;
	}
	public String getDescription() {
		return discription;
	}
	public String translate(String text) {
		return text;
	}
}
