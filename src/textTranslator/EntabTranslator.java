package textTranslator;
/**
 * Replaces each group of four spaces at the beginning of each line with a tab character.
 * @author Wei Dai
 *
 */

public class EntabTranslator implements TranslatorInterface{
	private String name="Entab Translator";
	private String description="Replaces each group of four spaces at the beginning of each line with a tab character.";

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
		if(text.equals("")) {
			return text;
		}
		 String[] inputLines = text.split("\n");
	     String output="";
	     for(int i=0;i<inputLines.length;i++) {
	    	 int spaceCount=0;
	    	 int j=0;
	    	 while(inputLines[i].charAt(j)==' ') {
	    			 spaceCount++;
	    			 j++;
	    		 }
	    	 int tabCount=(int)spaceCount/4;
	    	 StringBuffer outLine=new StringBuffer();
	    	 for(int k=0;k<tabCount;k++) {
	    		 outLine.append('\t');
	    	 }
	    	 for(int k=j-(spaceCount-tabCount*4);k<inputLines[i].length();k++) {
	    		 outLine.append(inputLines[i].charAt(k));
	    	 }
	    	 
	    	 output=output+outLine;
	    	 if(i<inputLines.length-1) {
	    		 output+="\n";
	    	 }
	     }
	     return output;
	}
	

}
