package textTranslator;
/**
 * Replaces tabs at the beginning of each line.
 * @author Wei Dai
 *
 */

public class DetabTranslator implements TranslatorInterface{
	private String name="Detab Translator";
	private String description="Replaces tabs at the beginning of each line.";
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
	     String[] inputLines = text.split("\n");
	     String output="";
	     for(int i=0;i<inputLines.length;i++) {
	    	 int j=0;
	    	 int tabCount=0;
	    	 if(inputLines[i].startsWith("\t")) {
		    	 while(inputLines[i].charAt(j)=='\t') {
		    		 tabCount++;
		    		 j++;
		    	 }
	    	 }
	    	 StringBuffer outLine=new StringBuffer();
	    	 for(int k=0;k<tabCount;k++) {
	    		 outLine.append("    ");
	    	 }
	    	 for(int k=j;k<inputLines[i].length();k++) {
	    		 outLine.append(inputLines[i].charAt(k));
	    	 }
	    	 
	    	 output=output+outLine.toString();
	    	 if(i<inputLines.length-1) {
	    		 output+="\n";
	    	 }
	     }
	     return output;
	}

}
