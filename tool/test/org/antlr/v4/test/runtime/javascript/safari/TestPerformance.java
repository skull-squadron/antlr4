package org.antlr.v4.test.runtime.javascript.safari;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class TestPerformance extends BaseTest {

	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testExpressionGrammar_1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(164);
		grammarBuilder.append("grammar Expr;\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("program: expr EOF;\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("expr\n");
		grammarBuilder.append("	: ID\n");
		grammarBuilder.append("	| 'not' expr\n");
		grammarBuilder.append("	| expr 'and' expr\n");
		grammarBuilder.append("	| expr 'or' expr\n");
		grammarBuilder.append("	;\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("ID: [a-zA-Z_][a-zA-Z_0-9]*;\n");
		grammarBuilder.append("WS: [ \\t\\n\\r\\f]+ -> skip;\n");
		grammarBuilder.append("ERROR: .;");
		String grammar = grammarBuilder.toString();
		String input =
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"    X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and     X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and     X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and     X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and     X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and     X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and     X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and     X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and     X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and     X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and     X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and     X12";
		String found = execParser("Expr.g4", grammar, "ExprParser", "ExprLexer",
		                          "ExprListener", "ExprVisitor",
		                          "program", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testExpressionGrammar_2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(164);
		grammarBuilder.append("grammar Expr;\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("program: expr EOF;\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("expr\n");
		grammarBuilder.append("	: ID\n");
		grammarBuilder.append("	| 'not' expr\n");
		grammarBuilder.append("	| expr 'and' expr\n");
		grammarBuilder.append("	| expr 'or' expr\n");
		grammarBuilder.append("	;\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("ID: [a-zA-Z_][a-zA-Z_0-9]*;\n");
		grammarBuilder.append("WS: [ \\t\\n\\r\\f]+ -> skip;\n");
		grammarBuilder.append("ERROR: .;");
		String grammar = grammarBuilder.toString();
		String input =
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"    X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and     X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and     X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and     X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and     X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and     X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and     X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and     X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and     X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and     X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and     X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and     X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"    X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and     X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and     X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and     X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and     X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and     X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and     X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and     X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and     X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and     X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and     X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and     X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"    X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and     X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and     X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and     X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and     X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and     X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and     X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and     X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and     X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and     X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and     X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and     X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"    X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and     X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and     X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and     X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and     X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and     X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and     X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and     X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and     X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and     X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and     X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and     X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"    X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and     X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and     X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and     X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and     X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and     X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and     X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and     X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and     X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and     X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and     X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and     X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"    X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and     X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and     X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and     X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and     X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and     X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and     X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and     X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and     X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and     X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and     X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and     X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"    X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and     X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and     X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and     X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and     X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and     X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and     X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and     X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and     X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and     X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and     X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and     X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"    X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and     X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and     X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and     X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and     X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and     X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and     X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and     X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and     X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and     X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and     X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and     X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"    X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and     X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and     X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and     X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and     X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and     X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and     X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and     X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and     X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and     X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and     X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and     X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"    X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and     X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and     X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and     X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and     X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and     X6 and not X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and     X7 and not X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and     X8 and not X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and     X9 and not X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and     X10 and not X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and     X11 and not X12 or\n" +
			"not X1 and not X2 and not X3 and not X4 and not X5 and not X6 and not X7 and not X8 and not X9 and not X10 and not X11 and     X12";
		String found = execParser("Expr.g4", grammar, "ExprParser", "ExprLexer",
		                          "ExprListener", "ExprVisitor",
		                          "program", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}

}
