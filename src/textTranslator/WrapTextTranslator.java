package textTranslator;
/**
 * Wraps lines so that no line is longer than 72 characters.
 * @author Wei Dai
 *
 */

public class WrapTextTranslator implements TranslatorInterface{
	String name="Wrap text Translator";
	String description="Wraps lines so that no line is longer than 72 characters.";
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


		String input[]=text.split("\n");
		StringBuffer[] output=new StringBuffer[input.length];
		for(int i=0;i<output.length;i++) {
			output[i]=new StringBuffer();
		}
		for(int nLines=0;nLines<input.length;nLines++) {
			int closestSpace=-1;
			int charCount=0;
			int nextStartPoint=0;
			for(int i=0;i<input[nLines].length();i++) {
				charCount++;
				if(input[nLines].charAt(i)==' ') {
					closestSpace=i;
				}
				if(charCount==73) {
					if(nextStartPoint>=closestSpace) {
						int k=nextStartPoint;
						while(input[nLines].charAt(k)!=' ' &&input[nLines].charAt(k)!='\n' && k<input[nLines].length()) {
							output[nLines].append(input[nLines].charAt(k));
							k++;
						}
						while(input[nLines].charAt(k)==' ') {
							k++;
							output[nLines].append(input[nLines].charAt(k));
						}
						output[nLines].append('\n');
						closestSpace=k-1;
					}
					else {
						for(int j=nextStartPoint;j<closestSpace;j++) {
							output[nLines].append(input[nLines].charAt(j));
						}
						output[nLines].append('\n');
					}
					i=closestSpace;
					charCount=0;
					nextStartPoint=i+1;
				}
			}
			for(int i=input[nLines].length()-charCount;i<input[nLines].length();i++) {
				output[nLines].append(input[nLines].charAt(i));
			}
		}
		String finalOutput=new String("");
		for(int i=0;i<output.length;i++) {
			finalOutput+=output[i].toString();
			if(i<output.length-1) {
				finalOutput+="\n";
			}
		}
		return finalOutput;
	}

}
