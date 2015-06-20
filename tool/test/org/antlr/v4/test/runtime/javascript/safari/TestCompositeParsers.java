package org.antlr.v4.test.runtime.javascript.safari;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import org.antlr.v4.test.tool.ErrorQueue;
import org.antlr.v4.tool.Grammar;

@SuppressWarnings("unused")
public class TestCompositeParsers extends BaseTest {

	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testDelegatorInvokesDelegateRuleWithArgs() throws Exception {
		mkdir(tmpdir);
		String slave_S =
			"parser grammar S;\n" +
			"a[int x] returns [int y] : B {document.getElementById('output').value += \"S.a\";$y=1000;} ;";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(175);
		grammarBuilder.append("grammar M;\n");
		grammarBuilder.append("import S;\n");
		grammarBuilder.append("s : label=a[3] {document.getElementById('output').value += $label.y + '\\n';} ;\n");
		grammarBuilder.append("B : 'b' ; // defines B from inherited token space\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="b";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "s", input, false);
		assertEquals("S.a1000\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testKeywordVSIDOrder() throws Exception {
		mkdir(tmpdir);
		String slave_S =
			"lexer grammar S;\n" +
			"ID : 'a'..'z'+;";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(189);
		grammarBuilder.append("grammar M;\n");
		grammarBuilder.append("import S;\n");
		grammarBuilder.append("a : A {document.getElementById('output').value += \"M.a: \" + $A + '\\n';};\n");
		grammarBuilder.append("A : 'abc' {document.getElementById('output').value += \"M.A\" + '\\n';};\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="abc";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "a", input, false);
		assertEquals(
			"M.A\n" +
			"M.a: [@0,0:2='abc',<1>,1:0]\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testDelegatorRuleOverridesLookaheadInDelegate() throws Exception {
		mkdir(tmpdir);
		String slave_S =
			"parser grammar S;\n" +
			"type_ : 'int' ;\n" +
			"decl : type_ ID ';'\n" +
			"	| type_ ID init ';' {document.getElementById('output').value += \"JavaDecl: \" + $text;};\n" +
			"init : '=' INT;";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(121);
		grammarBuilder.append("grammar M;\n");
		grammarBuilder.append("import S;\n");
		grammarBuilder.append("prog : decl ;\n");
		grammarBuilder.append("type_ : 'int' | 'float' ;\n");
		grammarBuilder.append("ID  : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip;");
		String grammar = grammarBuilder.toString();
		String input ="float x = 3;";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "prog", input, false);
		assertEquals("JavaDecl: floatx=3;\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testImportedGrammarWithEmptyOptions() throws Exception {
		mkdir(tmpdir);
		String slave_S =
			"parser grammar S;\n" +
			"options {}\n" +
			"a : B ;";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(64);
		grammarBuilder.append("grammar M;\n");
		grammarBuilder.append("import S;\n");
		grammarBuilder.append("s : a ;\n");
		grammarBuilder.append("B : 'b' ;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="b";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "s", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testCombinedImportsCombined() throws Exception {
		mkdir(tmpdir);
		String slave_S =
			"parser grammar S;\n" +
			"tokens { A, B, C }\n" +
			"x : 'x' INT {document.getElementById('output').value += \"S.x\" + '\\n';};\n" +
			"INT : '0'..'9'+ ;\n" +
			"WS : (' '|'\\n') -> skip ;";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(31);
		grammarBuilder.append("grammar M;\n");
		grammarBuilder.append("import S;\n");
		grammarBuilder.append("s : x INT;");
		String grammar = grammarBuilder.toString();
		String input ="x 34 9";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "s", input, false);
		assertEquals("S.x\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testBringInLiteralsFromDelegate() throws Exception {
		mkdir(tmpdir);
		String slave_S =
			"parser grammar S;\n" +
			"a : '=' 'a' {document.getElementById('output').value += \"S.a\";};";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(54);
		grammarBuilder.append("grammar M;\n");
		grammarBuilder.append("import S;\n");
		grammarBuilder.append("s : a ;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="=a";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "s", input, false);
		assertEquals("S.a\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testDelegatorInvokesDelegateRule() throws Exception {
		mkdir(tmpdir);
		String slave_S =
			"parser grammar S;\n" +
			"a : B {document.getElementById('output').value += \"S.a\" + '\\n';};";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(104);
		grammarBuilder.append("grammar M;\n");
		grammarBuilder.append("import S;\n");
		grammarBuilder.append("s : a ;\n");
		grammarBuilder.append("B : 'b' ; // defines B from inherited token space\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="b";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "s", input, false);
		assertEquals("S.a\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testDelegatorInvokesDelegateRuleWithReturnStruct() throws Exception {
		mkdir(tmpdir);
		String slave_S =
			"parser grammar S;\n" +
			"a : B {document.getElementById('output').value += \"S.a\";} ;";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(158);
		grammarBuilder.append("grammar M;\n");
		grammarBuilder.append("import S;\n");
		grammarBuilder.append("s : a {document.getElementById('output').value += $a.text;} ;\n");
		grammarBuilder.append("B : 'b' ; // defines B from inherited token space\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="b";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "s", input, false);
		assertEquals("S.ab\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testDelegatorInvokesFirstVersionOfDelegateRule() throws Exception {
		mkdir(tmpdir);
		String slave_T =
			"parser grammar T;\n" +
			"a : B {document.getElementById('output').value += \"T.a\" + '\\n';};";
		writeFile(tmpdir, "T.g4", slave_T);

		String slave_S =
			"parser grammar S;\n" +
			"a : b {document.getElementById('output').value += \"S.a\" + '\\n';};\n" +
			"b : B;";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(106);
		grammarBuilder.append("grammar M;\n");
		grammarBuilder.append("import S,T;\n");
		grammarBuilder.append("s : a ;\n");
		grammarBuilder.append("B : 'b' ; // defines B from inherited token space\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="b";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "s", input, false);
		assertEquals("S.a\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testDelegatorRuleOverridesDelegates() throws Exception {
		mkdir(tmpdir);
		String slave_T =
			"parser grammar T;\n" +
			"tokens { A }\n" +
			"b : 'b' {document.getElementById('output').value += \"T.b\" + '\\n';};";
		writeFile(tmpdir, "T.g4", slave_T);

		String slave_S =
			"parser grammar S;\n" +
			"a : b {document.getElementById('output').value += \"S.a\" + '\\n';};\n" +
			"b : 'b' ;";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(125);
		grammarBuilder.append("grammar M;\n");
		grammarBuilder.append("import S, T;\n");
		grammarBuilder.append("b : 'b'|'c' {document.getElementById('output').value += \"M.b\" + '\\n';}|B|A;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="c";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "a", input, false);
		assertEquals(
			"M.b\n" +
			"S.a\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testDelegatorAccessesDelegateMembers() throws Exception {
		mkdir(tmpdir);
		String slave_S =
			"parser grammar S;\n" +
			"@parser::members {\n" +
			"this.foo = function() {document.getElementById('output').value += 'foo' + '\\n';};\n" +
			"}\n" +
			"a : B;";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(127);
		grammarBuilder.append("grammar M; // uses no rules from the import\n");
		grammarBuilder.append("import S;\n");
		grammarBuilder.append("s : 'b' {this.foo();} ; // gS is import pointer\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="b";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "s", input, false);
		assertEquals("foo\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testDelegatesSeeSameTokenType() throws Exception {
		mkdir(tmpdir);
		String slave_T =
			"parser grammar T;\n" +
			"tokens { C, B, A } // reverse order\n" +
			"y : A {document.getElementById('output').value += \"T.y\" + '\\n';};";
		writeFile(tmpdir, "T.g4", slave_T);

		String slave_S =
			"parser grammar S;\n" +
			"tokens { A, B, C }\n" +
			"x : A {document.getElementById('output').value += \"S.x\" + '\\n';};";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(598);
		grammarBuilder.append("// The lexer will create rules to match letters a, b, c.\n");
		grammarBuilder.append("// The associated token types A, B, C must have the same value\n");
		grammarBuilder.append("// and all import'd parsers.  Since ANTLR regenerates all imports\n");
		grammarBuilder.append("// for use with the delegator M, it can generate the same token type\n");
		grammarBuilder.append("// mapping in each parser:\n");
		grammarBuilder.append("// public static final int C=6;\n");
		grammarBuilder.append("// public static final int EOF=-1;\n");
		grammarBuilder.append("// public static final int B=5;\n");
		grammarBuilder.append("// public static final int WS=7;\n");
		grammarBuilder.append("// public static final int A=4;\n");
		grammarBuilder.append("grammar M;\n");
		grammarBuilder.append("import S,T;\n");
		grammarBuilder.append("s : x y ; // matches AA, which should be 'aa'\n");
		grammarBuilder.append("B : 'b' ; // another order: B, A, C\n");
		grammarBuilder.append("A : 'a' ; \n");
		grammarBuilder.append("C : 'c' ; \n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="aa";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "s", input, false);
		assertEquals(
			"S.x\n" +
			"T.y\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testImportedRuleWithAction() throws Exception {
		mkdir(tmpdir);
		String slave_S =
			"parser grammar S;\n" +
			"a @after {this.x = 0;} : B;";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(62);
		grammarBuilder.append("grammar M;\n");
		grammarBuilder.append("import S;\n");
		grammarBuilder.append("s : a;\n");
		grammarBuilder.append("B : 'b';\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="b";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "s", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testImportLexerWithOnlyFragmentRules() throws Exception {
		mkdir(tmpdir);
		String slave_Unicode =
			"lexer grammar Unicode;\n" +
			"\n" +
			"fragment\n" +
			"UNICODE_CLASS_Zs    : '\\u0020' | '\\u00A0' | '\\u1680' | '\\u180E'\n" +
			"                    | '\\u2000'..'\\u200A'\n" +
			"                    | '\\u202F' | '\\u205F' | '\\u3000'\n" +
			"                    ;\n";
		writeFile(tmpdir, "Unicode.g4", slave_Unicode);

		StringBuilder grammarBuilder = new StringBuilder(91);
		grammarBuilder.append("grammar Test;\n");
		grammarBuilder.append("import Unicode;\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("program : 'test' 'test';\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("WS : (UNICODE_CLASS_Zs)+ -> skip;\n");
		String grammar = grammarBuilder.toString();
		String input ="test test";
		String found = execParser("Test.g4", grammar, "TestParser", "TestLexer",
		                          "TestListener", "TestVisitor",
		                          "program", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testDelegatorRuleOverridesDelegate() throws Exception {
		mkdir(tmpdir);
		String slave_S =
			"parser grammar S;\n" +
			"a : b {document.getElementById('output').value += \"S.a\";};\n" +
			"b : B ;";
		writeFile(tmpdir, "S.g4", slave_S);

		StringBuilder grammarBuilder = new StringBuilder(59);
		grammarBuilder.append("grammar M;\n");
		grammarBuilder.append("import S;\n");
		grammarBuilder.append("b : 'b'|'c';\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="c";
		String found = execParser("M.g4", grammar, "MParser", "MLexer",
		                          "MListener", "MVisitor",
		                          "a", input, false);
		assertEquals("S.a\n", found);
		assertNull(this.stderrDuringParse);

	}

}
