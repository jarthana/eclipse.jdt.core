/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Stephan Herrmann - Contribution for bug 349326 - [1.7] new warning for missing try-with-resources
 *******************************************************************************/
package org.eclipse.jdt.internal.compiler.ast;

import org.eclipse.jdt.internal.compiler.ASTVisitor;
import org.eclipse.jdt.internal.compiler.codegen.CodeStream;
import org.eclipse.jdt.internal.compiler.flow.FlowContext;
import org.eclipse.jdt.internal.compiler.flow.FlowInfo;
import org.eclipse.jdt.internal.compiler.lookup.BlockScope;

public class EmptyStatement extends Statement {

	public EmptyStatement(int startPosition, int endPosition) {
		this.sourceStart = startPosition;
		this.sourceEnd = endPosition;
	}

	@Override
	public FlowInfo analyseCode(BlockScope currentScope, FlowContext flowContext, FlowInfo flowInfo) {
		return flowInfo;
	}

	@Override
	public void generateCode(BlockScope currentScope, CodeStream codeStream){
		// no bytecode, no need to check for reachability or recording source positions
	}

	@Override
	public StringBuilder printStatement(int tab, StringBuilder output) {
		return printIndent(tab, output).append(';');
	}

	@Override
	public void resolve(BlockScope scope) {
		if ((this.bits & IsUsefulEmptyStatement) == 0) {
			scope.problemReporter().superfluousSemicolon(this.sourceStart, this.sourceEnd);
		} else {
			scope.problemReporter().emptyControlFlowStatement(this.sourceStart, this.sourceEnd);
		}
	}

	@Override
	public void traverse(ASTVisitor visitor, BlockScope scope) {
		visitor.visit(this, scope);
		visitor.endVisit(this, scope);
	}


}

