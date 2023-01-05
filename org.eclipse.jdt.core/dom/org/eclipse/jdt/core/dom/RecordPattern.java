/*******************************************************************************
 * Copyright (c) 2022, 2023 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * This is an implementation of an early-draft specification developed under the Java
 * Community Process (JCP) and is made available for testing and evaluation purposes
 * only. The code is not compatible with any specification of the JCP.
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.core.dom;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.core.dom.util.DOMASTUtil;

/**
 * TypePattern pattern AST node type.
 *
 * <pre>
 * RecordPattern:
 *      Pattern<Pattern<Patterns....>> Type SimpleName
 * </pre>
 *
 * @since 3.31
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noreference This class is not intended to be referenced by clients.
 */
@SuppressWarnings("rawtypes")
public class RecordPattern extends Pattern {

	/**
	 * The "patterns" structural property of this node type (child type: {@link Pattern}).
	 */
	public static final ChildListPropertyDescriptor PATTERNS_PROPERTY =
			new ChildListPropertyDescriptor(RecordPattern.class, "patterns", Pattern.class, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * The "patternType" structural property of this node type (child type: {@link Type}).
	 */
	public static final ChildPropertyDescriptor PATTERN_TYPE_PROPERTY =
		new ChildPropertyDescriptor(RecordPattern.class, "patternType", Type.class, MANDATORY, CYCLE_RISK); //$NON-NLS-1$

	/**
	 * The "patternName" structural property of this node type (child type: {@link SimpleName}).
	 */
	public static final ChildPropertyDescriptor PATTERN_NAME_PROPERTY =
			new ChildPropertyDescriptor(RecordPattern.class, "patternName", SimpleName.class, OPTIONAL, CYCLE_RISK); //$NON-NLS-1$

		/**
	 * A list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor}),
	 * or null if uninitialized.
	 */
	private static final List PROPERTY_DESCRIPTORS;

	static {
		List properyList = new ArrayList(5);
		createPropertyList(RecordPattern.class, properyList);
		addProperty(PATTERN_TYPE_PROPERTY, properyList);
		addProperty(PATTERNS_PROPERTY, properyList);
		addProperty(PATTERN_NAME_PROPERTY, properyList);
		PROPERTY_DESCRIPTORS = reapPropertyList(properyList);
	}

	@Override
	int getNodeType0() {
		return ASTNode.RECORD_PATTERN;
	}

	RecordPattern(AST ast) {
		super(ast);
		supportedOnlyIn20();
		unsupportedWithoutPreviewError();
	}


	/**
	 * The pattern name;
	 */
	private SimpleName optionalPatternName = null;

	/**
	 * The pattern type;
	 */
	private Type patternType = null;

	/**
	 * The patterns
	 * (element type: {@link Pattern}).
	 * Defaults to an empty list.
	 */
	private ASTNode.NodeList patterns =
		new ASTNode.NodeList(PATTERNS_PROPERTY);

	/**
	 * Returns a list of structural property descriptors for this node type.
	 * Clients must not modify the result.
	 *
	 * @param apiLevel the API level; one of the
	 * <code>AST.JLS*</code> constants
	 * @return a list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor})
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public static List propertyDescriptors(int apiLevel) {
		return null;
	}

	/**
	 * Returns a list of structural property descriptors for this node type.
	 * Clients must not modify the result.
	 *
	 * @param apiLevel the API level; one of the
	 * <code>AST.JLS*</code> constants
	 * @param previewEnabled the previewEnabled flag
	 * @return a list of property descriptors (element type:
	 * {@link StructuralPropertyDescriptor})
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public static List propertyDescriptors(int apiLevel, boolean previewEnabled) {
		if (DOMASTUtil.isPatternSupported(apiLevel, previewEnabled)) {
			return PROPERTY_DESCRIPTORS;
		}
		return null;
	}

	@Override
	final List internalStructuralPropertiesForType(int apiLevel) {
		return propertyDescriptors(apiLevel);
	}

	@Override
	final List internalStructuralPropertiesForType(int apiLevel, boolean previewEnabled) {
		return propertyDescriptors(apiLevel, previewEnabled);
	}

	@Override
	final ASTNode internalGetSetChildProperty(ChildPropertyDescriptor property, boolean get, ASTNode child) {
		if (property == PATTERN_TYPE_PROPERTY ) {
			if (get) {
				return getPatternType();
			} else {
				setPatternType((Type) child);
				return null;
			}
		} else if (property == PATTERN_NAME_PROPERTY ) {
			if (get) {
				return getPatternName();
			} else {
				setPatternName((SimpleName) child);
				return null;
			}
		}
		// allow default implementation to flag the error
		return super.internalGetSetChildProperty(property, get, child);
	}

	@Override
	final List internalGetChildListProperty(ChildListPropertyDescriptor property) {
		if (property == PATTERNS_PROPERTY) {
			return patterns();
		}
		// allow default implementation to flag the error
		return super.internalGetChildListProperty(property);
	}

	/**
	 * Sets the pattern name.
	 *
	 * @param patternName the right operand node
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 * @exception UnsupportedOperationException if this operation is used other than JLS19
	 * @exception UnsupportedOperationException if this expression is used with previewEnabled flag as false
	 * @noreference This method is not intended to be referenced by clients as it is a part of Java preview feature.
	 */
	public void setPatternName(SimpleName patternName) {
		supportedOnlyIn20();
		unsupportedWithoutPreviewError();
		if (patternName == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.optionalPatternName;
		preReplaceChild(oldChild, patternName, PATTERN_NAME_PROPERTY);
		this.optionalPatternName = patternName;
		postReplaceChild(oldChild, patternName, PATTERN_NAME_PROPERTY);
	}

	/**
	 * Sets the pattern type.
	 *
	 * @param patternType the right operand node
	 * @exception IllegalArgumentException if:
	 * <ul>
	 * <li>the node belongs to a different AST</li>
	 * <li>the node already has a parent</li>
	 * <li>a cycle in would be created</li>
	 * </ul>
	 * @exception UnsupportedOperationException if this operation is used other than JLS19
	 * @exception UnsupportedOperationException if this expression is used with previewEnabled flag as false
	 * @noreference This method is not intended to be referenced by clients as it is a part of Java preview feature.
	 */
	public void setPatternType(Type patternType) {
		supportedOnlyIn20();
		unsupportedWithoutPreviewError();
		if (patternType == null) {
			throw new IllegalArgumentException();
		}
		ASTNode oldChild = this.patternType;
		preReplaceChild(oldChild, patternType, PATTERN_TYPE_PROPERTY);
		this.patternType = patternType;
		postReplaceChild(oldChild, patternType, PATTERN_TYPE_PROPERTY);
	}

	/**
	 * Returns the pattern Name of Types Pattern.
	 *
	 * @return the pattern Name
	 * @exception UnsupportedOperationException if this operation is used other than JLS19
	 * @exception UnsupportedOperationException if this expression is used with previewEnabled flag as false
	 * @noreference This method is not intended to be referenced by clients as it is a part of Java preview feature.
	 */
	public SimpleName getPatternName() {
		supportedOnlyIn20();
		unsupportedWithoutPreviewError();
		return this.optionalPatternName;
	}

	/**
	 * Returns the pattern type of Types Pattern.
	 *
	 * @return the pattern type
	 * @exception UnsupportedOperationException if this operation is used other than JLS19
	 * @exception UnsupportedOperationException if this expression is used with previewEnabled flag as false
	 * @noreference This method is not intended to be referenced by clients as it is a part of Java preview feature.
	 */
	public Type getPatternType() {
		supportedOnlyIn20();
		unsupportedWithoutPreviewError();
		if (this.patternType  == null) {
			// lazy init must be thread-safe for readers
			synchronized (this) {
				if (this.patternType == null) {
					preLazyInit();
					this.patternType= this.ast.newPrimitiveType(PrimitiveType.INT);
					postLazyInit(this.patternType, PATTERN_TYPE_PROPERTY);
				}
			}
		}
		return this.patternType;
	}

	/**
	 * Returns the nested Pattern list.
	 *
	 * @return the live list of pattern nodes
	 *    (element type: {@link Pattern})
	 * @exception UnsupportedOperationException if this operation is used other than JLS19
	 * @exception UnsupportedOperationException if this expression is used with previewEnabled flag as false
	 * @noreference This method is not intended to be referenced by clients as it is a part of Java preview feature.
	 */
	@SuppressWarnings("unchecked")
	public List<Pattern> patterns() {
		supportedOnlyIn20();
		unsupportedWithoutPreviewError();
		return this.patterns;
	}


	@Override
	boolean subtreeMatch0(ASTMatcher matcher, Object other) {
		return matcher.match(this, other);
	}

	@Override
	ASTNode clone0(AST target) {
		RecordPattern result = new RecordPattern(target);
		result.setSourceRange(getStartPosition(), getLength());
		result.patterns().addAll(ASTNode.copySubtrees(target, patterns()));
		result.setPatternType((Type) getPatternType().clone(target));
		result.setPatternName((SimpleName) getPatternName().clone(target));
		return result;
	}

	@Override
	void accept0(ASTVisitor visitor) {
		boolean visitChildren = visitor.visit(this);
		if (visitChildren) {
			// visit children in normal left to right reading order
			acceptChildren(visitor, this.patterns);
			acceptChild(visitor, getPatternType());
			acceptChild(visitor, getPatternName());
		}
		visitor.endVisit(this);

	}

	@Override
	int memSize() {
		return BASE_NODE_SIZE + 2 * 4 ;
	}

	@Override
	int treeSize() {
		return
				memSize()
				+ (this.patternType == null ? 0 : getPatternType().treeSize())
				+ (this.optionalPatternName == null ? 0 : getPatternName().treeSize())
				+ this.patterns.listSize();
	}


}
