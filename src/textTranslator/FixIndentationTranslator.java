package textTranslator;
/**
 * Given a Java (or Java-like) program, indent it properly.
 * @author Wei Dai
 *
 */

public class FixIndentationTranslator implements TranslatorInterface{
	String name="Fix Indentation Translator";
	String description="Indent a Java (or Java-like) program";
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

		String[] rawInput=text.split("\n");
		String[] input=new String[rawInput.length];
		StringBuffer output=new StringBuffer();
		boolean firstLineSpace=false;
		int openBraceCount=0;//figure out how many spaces should be put
		input[0]=rawInput[0];
		for(int i=1;i<rawInput.length;i++) {
			int j=0;
			while(rawInput[i].charAt(j)==' ') {
				j++;
			}
			input[i]=rawInput[i].substring(j);
		}
		for(int i=0;i<input[0].length();i++) {
			if(input[0].charAt(i)!=' ') {
				break;
			}
			openBraceCount++;
			firstLineSpace=true;
		}
		for(int i=0;i<input.length;i++) {
			boolean braceCountChange=false;
			if(firstLineSpace==false) {
				for(int braces=0;braces<openBraceCount;braces++) {
					output.append(" ");
				}
			}
			else {
				firstLineSpace=false;
			}
			
			for(int j=0;j<input[i].length();j++) {
				if(input[i].charAt(j)=='{') {
					openBraceCount+=4;
					braceCountChange=true;
				}
				else if(input[i].charAt(j)=='}') {
					openBraceCount-=4;
					if(openBraceCount>=0&&braceCountChange==false) {
						output.delete(output.length()-4, output.length());
					}
				}
			}
			output.append(input[i]);
			if(i<input.length-1) {
				output.append('\n');
			}
		}
		return output.toString();
	}

}
