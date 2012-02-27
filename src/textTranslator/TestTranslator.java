package textTranslator;
/**
 * unittest for translator
 * Wei Dai
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTranslator {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIdentity() {
		IdentityTranslator it=new IdentityTranslator();
		assertEquals(it.translate("s4&*@$(90032ded\nd\t"),"s4&*@$(90032ded\nd\t");
	}
	@Test
	public void testDetab() {
		DetabTranslator dt=new DetabTranslator();
		assertEquals("    fdfdf\ndsds\tds\n    dsdsdfdds",
				dt.translate("\tfdfdf\ndsds\tds\n\tdsdsdfdds"));
		
		assertEquals("    fdfdf  \ndsds\td  s\n    dsdsdfdds",
				dt.translate("\tfdfdf  \ndsds\td  s\n\tdsdsdfdds"));

	}
	@Test
	public void testEntab() {
		EntabTranslator et=new EntabTranslator();
		assertEquals(et.translate("      dsds\ndsds\n    dsds  \n          dsds"),
				"\t  dsds\ndsds\n\tdsds  \n\t\t  dsds");
	}
	@Test
	public void testSingleSpace() {
		SingleSpaceTranslator sst=new SingleSpaceTranslator();
		assertEquals(sst.translate("dsdsds\n\n\ndsdsds"),
				"dsdsds\n\ndsdsds");
		assertEquals(sst.translate("dsdsds\n\n\ndsdsds\n\n\n\n\ndfdfd"),
				"dsdsds\n\ndsdsds\n\n\ndfdfd");
	}
	@Test
	public void testDoubleSpace() {
		DoubleSpaceTranslator dst=new DoubleSpaceTranslator();
		assertEquals(dst.translate("dsdsds\n\ndsdsds"),"dsdsds\n\n\ndsdsds");
		
		assertEquals(dst.translate("dsd\n\n\n\nsds\n\ndsdsds\n\n"),
				"dsd\n\n\n\n\n\n\nsds\n\n\ndsdsds\n\n\n");
		
		assertEquals(dst.translate("ds   d\n\n\n\nsds\n\nd'\t'sdsds\n\n"),
				"ds   d\n\n\n\n\n\n\nsds\n\n\nd'\t'sdsds\n\n\n");
		
		assertEquals(dst.translate("ds   dsdsdsdsds"),
				"ds   dsdsdsdsds");
		
		assertEquals(dst.translate("dsdsds\ndsdsds"),
				"dsdsds\ndsdsds");
	}
	@Test
	public void testWrapText() {
		WrapTextTranslator wtt=new WrapTextTranslator();
		assertEquals("abcdefghijklmnopqrstuvwxyz 1234567890 1234567890 1234567890 1234567890\n1234567890",
				wtt.translate("abcdefghijklmnopqrstuvwxyz 1234567890 1234567890 1234567890 1234567890 1234567890"));
		
		assertEquals("abcdefghijklmnopqrstuvwxyz 1234567890 1234567890 1234567890 1234567890\n1234567890\nabcdefghijklmnopqrstuvwxyz 1234567890 1234567890 1234567890 1234567890\n1234567890",
				wtt.translate("abcdefghijklmnopqrstuvwxyz 1234567890 1234567890 1234567890 1234567890 1234567890\nabcdefghijklmnopqrstuvwxyz 1234567890 1234567890 1234567890 1234567890 1234567890"));

	}
	@Test
	public void testFlowText() {
		FlowTextTranslator ftt=new FlowTextTranslator();
		assertEquals("abcde fghij klmno pqrst uvwxyz 12345\n\n67890 123456 7890 12345 67890 12345 67890 12345\n6789000000000000000000000000000000000000000000000",
				ftt.translate("abcde\nfghij\nklmno\npqrst\nuvwxyz 12345\n\n67890 123456\n7890 12345\n67890 12345\n67890 12345\n6789000000000000000000000000000000000000000000000"));
		
		assertEquals("klmno pqrst uvwxyz 12345\n\n67890 123456 7890 12345 67890 12345 67890 12345",
				ftt.translate("klmno\npqrst\nuvwxyz 12345\n\n67890 123456\n7890 12345\n67890 12345\n67890 12345"));

	}
	@Test
	public void testFixIndentation() {
		FixIndentationTranslator fit=new FixIndentationTranslator();
		assertEquals("aaa{aaa\n" +
				     "    {aaa\n" +
				     "        {\n" +
				     "            aaa\n" +
				     "        aaa}\n" +
				     "    aaa}\n" +
				     "}",fit.translate("aaa{aaa\n{aaa\n{\naaa\naaa}\naaa}\n}"));
		assertEquals("aaa\n" +
					 "{\n" +
				     "    {aaa\n" +
				     "        {\n" +
				     "            aaa\n" +
				     "        }\n" +
				     "    aaa}\n" +
				     "}",fit.translate("aaa\n{\n{aaa\n{\naaa\n}\naaa}\n}"));
		assertEquals("aaa\n" +
					 "{\n" +
				     "    {aaa}\n" +
				     "    {\n" +
				     "        aaa\n" +
				     "    }\n" +
				     "aaa}\n" +
				     "}",fit.translate("aaa\n{\n{aaa}\n{\naaa\n}\naaa}\n}"));
		assertEquals("aaa\n" +
					 "{\n" +
				     "    {aaa}\n" +
				     "}{\n" +
				     "    aaa\n" +
				     "}\n" +
				     "aaa}\n" +
				     "}",fit.translate("aaa\n{\n{aaa}\n}{\naaa\n}\naaa}\n}"));
	}
	

}
