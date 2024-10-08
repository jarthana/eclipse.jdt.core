/*******************************************************************************
 * Copyright (c) 2000, 2015 IBM Corporation and others.
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
 *******************************************************************************/
package org.eclipse.jdt.core.tests.model;

import java.util.List;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.eclipse.jdt.core.compiler.CharOperation;


/**
 * Test for search of generic types using R_EQUIVALENT_MATCH rule.
 */
@SuppressWarnings("rawtypes")
public class JavaSearchGenericTypeEquivalentTests extends JavaSearchGenericTypeTests {

//	static char[] RESULT_ERASURE_MATCH = "*] ERASURE_*".toCharArray();

	public JavaSearchGenericTypeEquivalentTests(String name) {
		super(name, EQUIVALENT_RULE);
	}
	public static Test suite() {
		TestSuite suite = new Suite(JavaSearchGenericTypeEquivalentTests.class.getName());
		List tests = buildTestsList(JavaSearchGenericTypeEquivalentTests.class, 1, 0/* do not sort*/);
		for (int index=0, size=tests.size(); index<size; index++) {
			suite.addTest((Test)tests.get(index));
		}
		return suite;
	}

	/*
	 * Add line to result only if it is not an erasure match rule.
	 */
	@Override
	void addResultLine(StringBuilder buffer, char[] line) {
		if (!CharOperation.match(RESULT_ERASURE_MATCH, line, true)) {
			super.addResultLine(buffer, line);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.tests.model.JavaSearchGenericTypeTests#removeLastTypeArgument(char[])
	 */
	@Override
	int[] removeLastTypeArgument(char[] line) {
		return null;
	}
}
