package textTranslator;
/**
 * Replaces consecutive pairs of newlines with a single newline.
 * @author Wei Dai
 *
 */

public class SingleSpaceTranslator implements TranslatorInterface{
	String name="Single space Translator";
	String description="Replaces consecutive pairs of newlines with a single newline.";
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
		StringBuffer outLine=new StringBuffer();
		for(int i=0;i<text.length();i++) {
			int nCount=0;
			if(text.charAt(i)=='\n') {
				while(text.charAt(i)=='\n') {
					nCount++;
					i++;
					if(i==text.length()) {
						break;
					}
				}
				int addSpace;
				if(nCount==2) {
					addSpace=1;
				}
				else {
					addSpace=Math.round((nCount-1)/2);
				}
				for(int j=0;j<addSpace+1;j++) {
					outLine.append('\n');
				}
				i--;
			}
			else {
				outLine.append(text.charAt(i));
			}
		}
		return outLine.toString();	
	}

}
