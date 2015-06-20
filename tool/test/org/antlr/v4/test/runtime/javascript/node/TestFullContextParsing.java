package org.antlr.v4.test.runtime.javascript.node;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class TestFullContextParsing extends BaseTest {

	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testExprAmbiguity_1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(304);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s\n");
		grammarBuilder.append("@init {this._interp.predictionMode = antlr4.atn.PredictionMode.LL_EXACT_AMBIG_DETECTION;}\n");
		grammarBuilder.append(":   expr[0] {console.log($expr.ctx.toStringTree(null, this));};\n");
		grammarBuilder.append("	expr[int _p]\n");
		grammarBuilder.append("		: ID \n");
		grammarBuilder.append("		( \n");
		grammarBuilder.append("			{5 >= $_p}? '*' expr[6]\n");
		grammarBuilder.append("			| {4 >= $_p}? '+' expr[5]\n");
		grammarBuilder.append("		)*\n");
		grammarBuilder.append("		;\n");
		grammarBuilder.append("ID  : [a-zA-Z]+ ;\n");
		grammarBuilder.append("WS  : [ \\r\\n\\t]+ -> skip ;\n");
		String grammar = grammarBuilder.toString();
		String input ="a+b";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals("(expr a + (expr b))\n", found);

		assertEquals(
			"line 1:1 reportAttemptingFullContext d=1 (expr), input='+'\n" +
			"line 1:2 reportContextSensitivity d=1 (expr), input='+b'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testExprAmbiguity_2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(304);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s\n");
		grammarBuilder.append("@init {this._interp.predictionMode = antlr4.atn.PredictionMode.LL_EXACT_AMBIG_DETECTION;}\n");
		grammarBuilder.append(":   expr[0] {console.log($expr.ctx.toStringTree(null, this));};\n");
		grammarBuilder.append("	expr[int _p]\n");
		grammarBuilder.append("		: ID \n");
		grammarBuilder.append("		( \n");
		grammarBuilder.append("			{5 >= $_p}? '*' expr[6]\n");
		grammarBuilder.append("			| {4 >= $_p}? '+' expr[5]\n");
		grammarBuilder.append("		)*\n");
		grammarBuilder.append("		;\n");
		grammarBuilder.append("ID  : [a-zA-Z]+ ;\n");
		grammarBuilder.append("WS  : [ \\r\\n\\t]+ -> skip ;\n");
		String grammar = grammarBuilder.toString();
		String input ="a+b*c";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals("(expr a + (expr b * (expr c)))\n", found);

		assertEquals(
			"line 1:1 reportAttemptingFullContext d=1 (expr), input='+'\n" +
			"line 1:2 reportContextSensitivity d=1 (expr), input='+b'\n" +
			"line 1:3 reportAttemptingFullContext d=1 (expr), input='*'\n" +
			"line 1:5 reportAmbiguity d=1 (expr): ambigAlts={1, 2}, input='*c'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testAmbiguityNoLoop() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(224);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("prog\n");
		grammarBuilder.append("@init {this._interp.predictionMode = antlr4.atn.PredictionMode.LL_EXACT_AMBIG_DETECTION;}\n");
		grammarBuilder.append("	: expr expr {console.log(\"alt 1\");}\n");
		grammarBuilder.append("	| expr\n");
		grammarBuilder.append("	;\n");
		grammarBuilder.append("expr: '@'\n");
		grammarBuilder.append("	| ID '@'\n");
		grammarBuilder.append("	| ID\n");
		grammarBuilder.append("	;\n");
		grammarBuilder.append("ID  : [a-z]+ ;\n");
		grammarBuilder.append("WS  : [ \\r\\n\\t]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="a@";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "prog", input, true);
		assertEquals("alt 1\n", found);

		assertEquals(
			"line 1:2 reportAttemptingFullContext d=0 (prog), input='a@'\n" +
			"line 1:2 reportAmbiguity d=0 (prog): ambigAlts={1, 2}, input='a@'\n" +
			"line 1:2 reportAttemptingFullContext d=1 (expr), input='a@'\n" +
			"line 1:2 reportContextSensitivity d=1 (expr), input='a@'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testFullContextIF_THEN_ELSEParse_1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(252);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s \n");
		grammarBuilder.append("@init {this._interp.predictionMode = antlr4.atn.PredictionMode.LL_EXACT_AMBIG_DETECTION;}\n");
		grammarBuilder.append("@after {this.dumpDFA();}\n");
		grammarBuilder.append("	: '{' stat* '}' ;\n");
		grammarBuilder.append("stat: 'if' ID 'then' stat ('else' ID)?\n");
		grammarBuilder.append("		| 'return'\n");
		grammarBuilder.append("		;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\t'|'\\n')+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="{ if x then return }";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals(
			"Decision 1:\n" +
			"s0-'}'->:s1=>2\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testFullContextIF_THEN_ELSEParse_2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(252);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s \n");
		grammarBuilder.append("@init {this._interp.predictionMode = antlr4.atn.PredictionMode.LL_EXACT_AMBIG_DETECTION;}\n");
		grammarBuilder.append("@after {this.dumpDFA();}\n");
		grammarBuilder.append("	: '{' stat* '}' ;\n");
		grammarBuilder.append("stat: 'if' ID 'then' stat ('else' ID)?\n");
		grammarBuilder.append("		| 'return'\n");
		grammarBuilder.append("		;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\t'|'\\n')+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="{ if x then return else foo }";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals(
			"Decision 1:\n" +
			"s0-'else'->:s1^=>1\n", found);

		assertEquals(
			"line 1:19 reportAttemptingFullContext d=1 (stat), input='else'\n" +
			"line 1:19 reportContextSensitivity d=1 (stat), input='else'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testFullContextIF_THEN_ELSEParse_3() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(252);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s \n");
		grammarBuilder.append("@init {this._interp.predictionMode = antlr4.atn.PredictionMode.LL_EXACT_AMBIG_DETECTION;}\n");
		grammarBuilder.append("@after {this.dumpDFA();}\n");
		grammarBuilder.append("	: '{' stat* '}' ;\n");
		grammarBuilder.append("stat: 'if' ID 'then' stat ('else' ID)?\n");
		grammarBuilder.append("		| 'return'\n");
		grammarBuilder.append("		;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\t'|'\\n')+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="{ if x then if y then return else foo }";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals(
			"Decision 1:\n" +
			"s0-'}'->:s2=>2\n" +
			"s0-'else'->:s1^=>1\n", found);

		assertEquals(
			"line 1:29 reportAttemptingFullContext d=1 (stat), input='else'\n" +
			"line 1:38 reportAmbiguity d=1 (stat): ambigAlts={1, 2}, input='elsefoo}'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testFullContextIF_THEN_ELSEParse_4() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(252);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s \n");
		grammarBuilder.append("@init {this._interp.predictionMode = antlr4.atn.PredictionMode.LL_EXACT_AMBIG_DETECTION;}\n");
		grammarBuilder.append("@after {this.dumpDFA();}\n");
		grammarBuilder.append("	: '{' stat* '}' ;\n");
		grammarBuilder.append("stat: 'if' ID 'then' stat ('else' ID)?\n");
		grammarBuilder.append("		| 'return'\n");
		grammarBuilder.append("		;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\t'|'\\n')+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="{ if x then if y then return else foo else bar }";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals(
			"Decision 1:\n" +
			"s0-'else'->:s1^=>1\n", found);

		assertEquals(
			"line 1:29 reportAttemptingFullContext d=1 (stat), input='else'\n" +
			"line 1:38 reportContextSensitivity d=1 (stat), input='elsefooelse'\n" +
			"line 1:38 reportAttemptingFullContext d=1 (stat), input='else'\n" +
			"line 1:38 reportContextSensitivity d=1 (stat), input='else'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testSLLSeesEOFInLLGrammar() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(149);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s @after {this.dumpDFA();}\n");
		grammarBuilder.append("  : a;\n");
		grammarBuilder.append("a : e ID ;\n");
		grammarBuilder.append("b : e INT ID ;\n");
		grammarBuilder.append("e : INT | ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\t'|'\\n')+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="34 abc";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals(
			"Decision 0:\n" +
			"s0-INT->s1\n" +
			"s1-ID->:s2^=>1\n", found);

		assertEquals(
			"line 1:3 reportAttemptingFullContext d=0 (e), input='34abc'\n" +
			"line 1:0 reportContextSensitivity d=0 (e), input='34'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testAmbigYieldsCtxSensitiveDFA() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(101);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s @after {this.dumpDFA();}\n");
		grammarBuilder.append("	: ID | ID {} ;\n");
		grammarBuilder.append("ID : 'a'..'z'+;\n");
		grammarBuilder.append("WS : (' '|'\\t'|'\\n')+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="abc";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals(
			"Decision 0:\n" +
			"s0-ID->:s1^=>1\n", found);

		assertEquals("line 1:0 reportAttemptingFullContext d=0 (s), input='abc'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testCtxSensitiveDFA_1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(162);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s @after {this.dumpDFA();}\n");
		grammarBuilder.append("  : '$' a | '@' b ;\n");
		grammarBuilder.append("a : e ID ;\n");
		grammarBuilder.append("b : e INT ID ;\n");
		grammarBuilder.append("e : INT | ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\t'|'\\n')+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="$ 34 abc";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals(
			"Decision 1:\n" +
			"s0-INT->s1\n" +
			"s1-ID->:s2^=>1\n", found);

		assertEquals(
			"line 1:5 reportAttemptingFullContext d=1 (e), input='34abc'\n" +
			"line 1:2 reportContextSensitivity d=1 (e), input='34'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testCtxSensitiveDFATwoDiffInput() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(165);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s @after {this.dumpDFA();}\n");
		grammarBuilder.append("  : ('$' a | '@' b)+ ;\n");
		grammarBuilder.append("a : e ID ;\n");
		grammarBuilder.append("b : e INT ID ;\n");
		grammarBuilder.append("e : INT | ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\t'|'\\n')+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="$ 34 abc @ 34 abc";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals(
			"Decision 2:\n" +
			"s0-INT->s1\n" +
			"s1-ID->:s2^=>1\n", found);

		assertEquals(
			"line 1:5 reportAttemptingFullContext d=2 (e), input='34abc'\n" +
			"line 1:2 reportContextSensitivity d=2 (e), input='34'\n" +
			"line 1:14 reportAttemptingFullContext d=2 (e), input='34abc'\n" +
			"line 1:14 reportContextSensitivity d=2 (e), input='34abc'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testCtxSensitiveDFA_2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(162);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s @after {this.dumpDFA();}\n");
		grammarBuilder.append("  : '$' a | '@' b ;\n");
		grammarBuilder.append("a : e ID ;\n");
		grammarBuilder.append("b : e INT ID ;\n");
		grammarBuilder.append("e : INT | ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\t'|'\\n')+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="@ 34 abc";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals(
			"Decision 1:\n" +
			"s0-INT->s1\n" +
			"s1-ID->:s2^=>1\n", found);

		assertEquals(
			"line 1:5 reportAttemptingFullContext d=1 (e), input='34abc'\n" +
			"line 1:5 reportContextSensitivity d=1 (e), input='34abc'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testLoopsSimulateTailRecursion() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(324);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("prog\n");
		grammarBuilder.append("@init {this._interp.predictionMode = antlr4.atn.PredictionMode.LL_EXACT_AMBIG_DETECTION;}\n");
		grammarBuilder.append("	: expr_or_assign*;\n");
		grammarBuilder.append("expr_or_assign\n");
		grammarBuilder.append("	: expr '++' {console.log(\"fail.\");}\n");
		grammarBuilder.append("	|  expr {console.log(\"pass: \"+$expr.text);}\n");
		grammarBuilder.append("	;\n");
		grammarBuilder.append("expr: expr_primary ('<-' ID)?;\n");
		grammarBuilder.append("expr_primary\n");
		grammarBuilder.append("	: '(' ID ')'\n");
		grammarBuilder.append("	| ID '(' ID ')'\n");
		grammarBuilder.append("	| ID\n");
		grammarBuilder.append("	;\n");
		grammarBuilder.append("ID  : [a-z]+ ;");
		String grammar = grammarBuilder.toString();
		String input ="a(i)<-x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "prog", input, true);
		assertEquals("pass: a(i)<-x\n", found);

		assertEquals(
			"line 1:3 reportAttemptingFullContext d=3 (expr_primary), input='a(i)'\n" +
			"line 1:7 reportAmbiguity d=3 (expr_primary): ambigAlts={2, 3}, input='a(i)<-x'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testFullContextIF_THEN_ELSEParse_6() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(252);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s \n");
		grammarBuilder.append("@init {this._interp.predictionMode = antlr4.atn.PredictionMode.LL_EXACT_AMBIG_DETECTION;}\n");
		grammarBuilder.append("@after {this.dumpDFA();}\n");
		grammarBuilder.append("	: '{' stat* '}' ;\n");
		grammarBuilder.append("stat: 'if' ID 'then' stat ('else' ID)?\n");
		grammarBuilder.append("		| 'return'\n");
		grammarBuilder.append("		;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\t'|'\\n')+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input =
			"{ if x then return else foo\n" +
			"if x then if y then return else foo }";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals(
			"Decision 1:\n" +
			"s0-'}'->:s2=>2\n" +
			"s0-'else'->:s1^=>1\n", found);

		assertEquals(
			"line 1:19 reportAttemptingFullContext d=1 (stat), input='else'\n" +
			"line 1:19 reportContextSensitivity d=1 (stat), input='else'\n" +
			"line 2:27 reportAttemptingFullContext d=1 (stat), input='else'\n" +
			"line 2:36 reportAmbiguity d=1 (stat): ambigAlts={1, 2}, input='elsefoo}'\n", this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testFullContextIF_THEN_ELSEParse_5() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(252);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s \n");
		grammarBuilder.append("@init {this._interp.predictionMode = antlr4.atn.PredictionMode.LL_EXACT_AMBIG_DETECTION;}\n");
		grammarBuilder.append("@after {this.dumpDFA();}\n");
		grammarBuilder.append("	: '{' stat* '}' ;\n");
		grammarBuilder.append("stat: 'if' ID 'then' stat ('else' ID)?\n");
		grammarBuilder.append("		| 'return'\n");
		grammarBuilder.append("		;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\t'|'\\n')+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input =
			"{ if x then return else foo\n" +
			"if x then if y then return else foo }";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals(
			"Decision 1:\n" +
			"s0-'}'->:s2=>2\n" +
			"s0-'else'->:s1^=>1\n", found);

		assertEquals(
			"line 1:19 reportAttemptingFullContext d=1 (stat), input='else'\n" +
			"line 1:19 reportContextSensitivity d=1 (stat), input='else'\n" +
			"line 2:27 reportAttemptingFullContext d=1 (stat), input='else'\n" +
			"line 2:36 reportAmbiguity d=1 (stat): ambigAlts={1, 2}, input='elsefoo}'\n", this.stderrDuringParse);

	}

}
