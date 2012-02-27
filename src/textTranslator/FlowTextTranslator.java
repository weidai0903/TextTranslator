package textTranslator;
/**
 * Flows text (the way web pages do)
 * @author Wei Dai
 *
 */

public class FlowTextTranslator implements TranslatorInterface{
	String name="Flow text Translator";
	String description="Flows text (the way web pages do)";
	
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
		String[] input=text.split("\n");
		StringBuffer output=new StringBuffer();
		boolean blank=false;
		for(int i=0;i<input.length;i++) {
			if(input[i].equals("")) {
				output.append("\n\n");
			}
			else {
				blank=true;
				for(int j=0;j<input[i].length();j++) {
					if(input[i].charAt(j)!='\t' && input[i].charAt(j)!=' ') {
						blank=false;
						break;
					}
				}
				if(blank==false) {
					if(i!=0&&output.charAt(output.length()-1)!='\n') {
						output.append(' ');
					}
					output.append(input[i]);
				}
				else {
					output.append("\n\n");
				}
			
			}
		}
		int nCount=0;
		for(int i=0;i<output.length();i++) {
			if(output.charAt(i)=='\n') {
				while(i<output.length()&&output.charAt(i)=='\n') {
					nCount++;
					//System.out.print(output.charAt(i));
					//System.out.print(nCount);
					i++;
				}
				if(nCount>=3) {
					output.delete(i-nCount+1, i-1);
					nCount=0;
					i--;
				}
				else {
					nCount=0;
					i--;
				}
			}
			
		}
		WrapTextTranslator wt=new WrapTextTranslator();
		return wt.translate(output.toString());
	}

}
