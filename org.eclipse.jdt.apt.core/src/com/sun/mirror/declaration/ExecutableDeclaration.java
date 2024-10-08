/*
 * @(#)ExecutableDeclaration.java	1.2 04/03/08
 *
 * Copyright (c) 2004, Sun Microsystems, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Sun Microsystems, Inc. nor the names of
 *       its contributors may be used to endorse or promote products derived from
 *       this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.sun.mirror.declaration;


import com.sun.mirror.type.ReferenceType;
import java.util.Collection;


/**
 * Represents a method or constructor of a class or interface.
 *
 * @author Joseph D. Darcy
 * @author Scott Seligman
 * @version 1.2 04/03/08
 * @since 1.5
 */

public interface ExecutableDeclaration extends MemberDeclaration {

    /**
     * Returns <code>true</code> if this method or constructor accepts a variable
     * number of arguments.
     *
     * @return <code>true</code> if this method or constructor accepts a variable
     * number of arguments
     */
    boolean isVarArgs();

    /**
     * Returns the formal type parameters of this method or constructor.
     * They are returned in declaration order.
     *
     * @return the formal type parameters of this method or constructor,
     * or an empty collection if there are none
     */
    Collection<TypeParameterDeclaration> getFormalTypeParameters();

    /**
     * Returns the formal parameters of this method or constructor.
     * They are returned in declaration order.
     *
     * @return the formal parameters of this method or constructor,
     * or an empty collection if there are none
     */
    Collection<ParameterDeclaration> getParameters();

    /**
     * Returns the exceptions and other throwables listed in this
     * method or constructor's <code>throws</code> clause.
     *
     * @return the exceptions and other throwables listed in the
     * <code>throws</code> clause, or an empty collection if there are none
     */
    Collection<ReferenceType> getThrownTypes();
}
