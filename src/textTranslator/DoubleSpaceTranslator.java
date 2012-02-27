package textTranslator;
/**
 * Replaces each newline in the input with two newlines
 * @author Wei Dai
 *
 */

public class DoubleSpaceTranslator implements TranslatorInterface {
	String name="Double space Translator";
	String description="Replaces each newline in the input with two newlines";
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String translate(String text) {
		StringBuffer output=new StringBuffer();
		for(int i=0;i<text.length();i++) {
			if(text.charAt(i)=='\n') {
				output.append('\n');
				if(text.charAt(i+1)=='\n') {
					i++;
					while(text.charAt(i)=='\n') {
						output.append("\n\n");
						i++;
						if(i==text.length()) {
							break;
						}
					}
					i--;
				}
			}
			else {
				output.append(text.charAt(i));
			}
		}
		return output.toString();
	}
	

}
