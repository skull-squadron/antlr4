package org.antlr.v4.test.runtime.javascript.explorer;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class TestSemPredEvalParser extends BaseTest {

	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testValidateInDFA() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(406);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s : a ';' a;\n");
		grammarBuilder.append("// ';' helps us to resynchronize without consuming\n");
		grammarBuilder.append("// 2nd 'a' reference. We our testing that the DFA also\n");
		grammarBuilder.append("// throws an exception if the validating predicate fails\n");
		grammarBuilder.append("a : {false}? ID  {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | {true}?  INT {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="x ; y";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals("", found);

		assertEquals(
			"line 1:0 no viable alternative at input 'x'\n" +
			"line 1:4 no viable alternative at input 'y'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testOrder() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(371);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s : a {} a; // do 2x: once in ATN, next in DFA;\n");
		grammarBuilder.append("// action blocks lookahead from falling off of 'a'\n");
		grammarBuilder.append("// and looking into 2nd 'a' ref. !ctx dependent pred\n");
		grammarBuilder.append("a : ID {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | {true}?  ID {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="x y";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals(
			"alt 1\n" +
			"alt 1\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testNoTruePredsThrowsNoViableAlt() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(245);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s : a a;\n");
		grammarBuilder.append("a : {false}? ID INT {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | {false}? ID INT {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="y 3 x 4";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals("", found);

		assertEquals("line 1:0 no viable alternative at input 'y'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testDepedentPredsInGlobalFOLLOW() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(390);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@members {\n");
		grammarBuilder.append("this.pred = function(v) {\n");
		grammarBuilder.append("	document.getElementById('output').value += 'eval=' + v.toString() + '\\n';\n");
		grammarBuilder.append("	return v;\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("s : a[99] ;\n");
		grammarBuilder.append("a[int i] : e {this.pred($i===99)}? {document.getElementById('output').value += \"parse\" + '\\n';} '!' ;\n");
		grammarBuilder.append("b[int i] : e {this.pred($i===99)}? ID ;\n");
		grammarBuilder.append("e : ID | ; // non-LL(1) so we use ATN\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="a!";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals(
			"eval=true\n" +
			"parse\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testDependentPredNotInOuterCtxShouldBeIgnored() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(346);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s : b[2] ';' |  b[2] '.' ; // decision in s drills down to ctx-dependent pred in a;\n");
		grammarBuilder.append("b[int i] : a[i] ;\n");
		grammarBuilder.append("a[int i]\n");
		grammarBuilder.append("  : {$i===1}? ID {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("    | {$i===2}? ID {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("    ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;\n");
		String grammar = grammarBuilder.toString();
		String input ="a;";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals("alt 2\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testActionHidesPreds() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(302);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@members {this.i = 0;}\n");
		grammarBuilder.append("s : a+ ;\n");
		grammarBuilder.append("a : {this.i = 1;} ID {this.i === 1}? {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | {this.i = 2;} ID {this.i === 2}? {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="x x y";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals(
			"alt 1\n" +
			"alt 1\n" +
			"alt 1\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testToLeftWithVaryingPredicate() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(363);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@members {this.i = 0;}\n");
		grammarBuilder.append("s : ({this.i += 1;\n");
		grammarBuilder.append("document.getElementById('output').value += \"i=\" + this.i + '\\n';} a)+ ;\n");
		grammarBuilder.append("a : {this.i % 2 === 0}? ID {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | {this.i % 2 != 0}? ID {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="x x y";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals(
			"i=1\n" +
			"alt 2\n" +
			"i=2\n" +
			"alt 1\n" +
			"i=3\n" +
			"alt 2\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testSimpleValidate() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(238);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s : a ;\n");
		grammarBuilder.append("a : {false}? ID  {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | {true}?  INT {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals("", found);

		assertEquals("line 1:0 no viable alternative at input 'x'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testPredicateDependentOnArg() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(277);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@members {this.i = 0;}\n");
		grammarBuilder.append("s : a[2] a[1];\n");
		grammarBuilder.append("a[int i]\n");
		grammarBuilder.append("  : {$i===1}? ID {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | {$i===2}? ID {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="a b";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals(
			"alt 2\n" +
			"alt 1\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testPredFromAltTestedInLoopBack_1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(247);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("file_\n");
		grammarBuilder.append("@after {document.getElementById('output').value += $ctx.toStringTree(null, this) + '\\n';}\n");
		grammarBuilder.append("  : para para EOF ;\n");
		grammarBuilder.append("para: paraContent NL NL ;\n");
		grammarBuilder.append("paraContent : ('s'|'x'|{this._input.LA(2)!=TParser.NL}? NL)+ ;\n");
		grammarBuilder.append("NL : '\\n' ;\n");
		grammarBuilder.append("s : 's' ;\n");
		grammarBuilder.append("X : 'x' ;");
		String grammar = grammarBuilder.toString();
		String input =
			"s\n" +
			"\n" +
			"\n" +
			"x\n";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "file_", input, true);
		assertEquals("(file_ (para (paraContent s) \\n \\n) (para (paraContent \\n x \\n)) <EOF>)\n", found);

		assertEquals(
			"line 5:0 mismatched input '<EOF>' expecting '\n" +
			"'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testPredFromAltTestedInLoopBack_2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(247);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("file_\n");
		grammarBuilder.append("@after {document.getElementById('output').value += $ctx.toStringTree(null, this) + '\\n';}\n");
		grammarBuilder.append("  : para para EOF ;\n");
		grammarBuilder.append("para: paraContent NL NL ;\n");
		grammarBuilder.append("paraContent : ('s'|'x'|{this._input.LA(2)!=TParser.NL}? NL)+ ;\n");
		grammarBuilder.append("NL : '\\n' ;\n");
		grammarBuilder.append("s : 's' ;\n");
		grammarBuilder.append("X : 'x' ;");
		String grammar = grammarBuilder.toString();
		String input =
			"s\n" +
			"\n" +
			"\n" +
			"x\n" +
			"\n";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "file_", input, true);
		assertEquals("(file_ (para (paraContent s) \\n \\n) (para (paraContent \\n x) \\n \\n) <EOF>)\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void test2UnpredicatedAltsAndOneOrthogonalAlt() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(508);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s : {this._interp.predictionMode = antlr4.atn.PredictionMode.LL_EXACT_AMBIG_DETECTION;} a ';' a ';' a;\n");
		grammarBuilder.append("a : INT {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | ID {document.getElementById('output').value += \"alt 2\" + '\\n';} // must pick this one for ID since pred is false\n");
		grammarBuilder.append("  | ID {document.getElementById('output').value += \"alt 3\" + '\\n';}\n");
		grammarBuilder.append("  | {false}? ID {document.getElementById('output').value += \"alt 4\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="34; x; y";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals(
			"alt 1\n" +
			"alt 2\n" +
			"alt 2\n", found);

		assertEquals(
			"line 1:4 reportAttemptingFullContext d=0 (a), input='x'\n" +
			"line 1:4 reportAmbiguity d=0 (a): ambigAlts={2, 3}, input='x'\n" +
			"line 1:7 reportAttemptingFullContext d=0 (a), input='y'\n" +
			"line 1:7 reportAmbiguity d=0 (a): ambigAlts={2, 3}, input='y'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testUnpredicatedPathsInAlt() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(257);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s : a {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | b {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("a : {false}? ID INT\n");
		grammarBuilder.append("  | ID INT\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("b : ID ID\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="x 4";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals("alt 1\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testToLeft() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(238);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("	s : a+ ;\n");
		grammarBuilder.append("a : {false}? ID {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | {true}?  ID {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="x x y";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals(
			"alt 2\n" +
			"alt 2\n" +
			"alt 2\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void test2UnpredicatedAlts() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(419);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s : {this._interp.predictionMode = antlr4.atn.PredictionMode.LL_EXACT_AMBIG_DETECTION;} a ';' a; // do 2x: once in ATN, next in DFA\n");
		grammarBuilder.append("a : ID {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | ID {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("  | {false}? ID {document.getElementById('output').value += \"alt 3\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="x; y";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals(
			"alt 1\n" +
			"alt 1\n", found);

		assertEquals(
			"line 1:0 reportAttemptingFullContext d=0 (a), input='x'\n" +
			"line 1:0 reportAmbiguity d=0 (a): ambigAlts={1, 2}, input='x'\n" +
			"line 1:3 reportAttemptingFullContext d=0 (a), input='y'\n" +
			"line 1:3 reportAmbiguity d=0 (a): ambigAlts={1, 2}, input='y'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testRewindBeforePredEval() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(291);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s : a a;\n");
		grammarBuilder.append("a : {this._input.LT(1).text===\"x\"}? ID INT {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | {this._input.LT(1).text===\"y\"}? ID INT {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="y 3 x 4";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals(
			"alt 2\n" +
			"alt 1\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testDisabledAlternative() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(121);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("cppCompilationUnit : content+ EOF;\n");
		grammarBuilder.append("content: anything | {false}? .;\n");
		grammarBuilder.append("anything: ANY_CHAR;\n");
		grammarBuilder.append("ANY_CHAR: [_a-zA-Z0-9];");
		String grammar = grammarBuilder.toString();
		String input ="hello";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "cppCompilationUnit", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testIndependentPredNotPassedOuterCtxToAvoidCastException() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(257);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s : b ';' |  b '.' ;\n");
		grammarBuilder.append("b : a ;\n");
		grammarBuilder.append("a\n");
		grammarBuilder.append("  : {false}? ID {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | {true}? ID {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append(" ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="a;";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals("alt 2\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testPredsInGlobalFOLLOW() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(359);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@members {\n");
		grammarBuilder.append("this.pred = function(v) {\n");
		grammarBuilder.append("	document.getElementById('output').value += 'eval=' + v.toString() + '\\n';\n");
		grammarBuilder.append("	return v;\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("s : e {this.pred(true)}? {document.getElementById('output').value += \"parse\" + '\\n';} '!' ;\n");
		grammarBuilder.append("t : e {this.pred(false)}? ID ;\n");
		grammarBuilder.append("e : ID | ; // non-LL(1) so we use ATN\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="a!";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals(
			"eval=true\n" +
			"parse\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testSimple() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(367);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s : a a a; // do 3x: once in ATN, next in DFA then INT in ATN\n");
		grammarBuilder.append("a : {false}? ID {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | {true}?  ID {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("  | INT         {document.getElementById('output').value += \"alt 3\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="x y 3";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals(
			"alt 2\n" +
			"alt 2\n" +
			"alt 3\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testSimpleValidate2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(241);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s : a a a;\n");
		grammarBuilder.append("a : {false}? ID  {document.getElementById('output').value += \"alt 1\" + '\\n';}\n");
		grammarBuilder.append("  | {true}?  INT {document.getElementById('output').value += \"alt 2\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="3 4 x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals(
			"alt 2\n" +
			"alt 2\n", found);

		assertEquals("line 1:4 no viable alternative at input 'x'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testPredTestedEvenWhenUnAmbig_1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(275);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@members {this.enumKeyword = true;}\n");
		grammarBuilder.append("primary\n");
		grammarBuilder.append("    :   ID {document.getElementById('output').value += \"ID \"+$ID.text + '\\n';}\n");
		grammarBuilder.append("    |   {!this.enumKeyword}? 'enum' {document.getElementById('output').value += \"enum\" + '\\n';}\n");
		grammarBuilder.append("    ;\n");
		grammarBuilder.append("ID : [a-z]+ ;\n");
		grammarBuilder.append("WS : [ \\t\\n\\r]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="abc";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "primary", input, false);
		assertEquals("ID abc\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testPredTestedEvenWhenUnAmbig_2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(275);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@members {this.enumKeyword = true;}\n");
		grammarBuilder.append("primary\n");
		grammarBuilder.append("    :   ID {document.getElementById('output').value += \"ID \"+$ID.text + '\\n';}\n");
		grammarBuilder.append("    |   {!this.enumKeyword}? 'enum' {document.getElementById('output').value += \"enum\" + '\\n';}\n");
		grammarBuilder.append("    ;\n");
		grammarBuilder.append("ID : [a-z]+ ;\n");
		grammarBuilder.append("WS : [ \\t\\n\\r]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="enum";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "primary", input, false);
		assertEquals("", found);

		assertEquals("line 1:0 no viable alternative at input 'enum'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testActionsHidePredsInGlobalFOLLOW() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(365);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@members {\n");
		grammarBuilder.append("this.pred = function(v) {\n");
		grammarBuilder.append("	document.getElementById('output').value += 'eval=' + v.toString() + '\\n';\n");
		grammarBuilder.append("	return v;\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("s : e {} {this.pred(true)}? {document.getElementById('output').value += \"parse\" + '\\n';} '!' ;\n");
		grammarBuilder.append("t : e {} {this.pred(false)}? ID ;\n");
		grammarBuilder.append("e : ID | ; // non-LL(1) so we use ATN\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="a!";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals(
			"eval=true\n" +
			"parse\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testPredicateDependentOnArg2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(157);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@members {this.i = 0;}\n");
		grammarBuilder.append("s : a[2] a[1];\n");
		grammarBuilder.append("a[int i]\n");
		grammarBuilder.append("  : {$i===1}? ID \n");
		grammarBuilder.append("  | {$i===2}? ID \n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="a b";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testAtomWithClosureInTranslatedLRRule() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(94);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("start : e[0] EOF;\n");
		grammarBuilder.append("e[int _p]\n");
		grammarBuilder.append("    :   ( 'a' | 'b'+ ) ( {3 >= $_p}? '+' e[4] )*\n");
		grammarBuilder.append("    ;\n");
		String grammar = grammarBuilder.toString();
		String input ="a+b+a";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "start", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}

}
