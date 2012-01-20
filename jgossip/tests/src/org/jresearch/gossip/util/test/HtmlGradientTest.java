/*
 * $Id: HtmlGradientTest.java,v 1.1 2004/11/23 11:08:47 bel70 Exp $
 *
 * ***** BEGIN LICENSE BLOCK *****
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in 
 * compliance with the License. You may obtain a copy of the License 
 * at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and 
 * limitations under the License.
 *
 * The Original Code is JGossip forum code.
 *
 * The Initial Developer of the Original Code is the JResearch, Org. 
 * Portions created by the Initial Developer are Copyright (C) 2004 
 * the Initial Developer. All Rights Reserved. 
 * 
 * Contributor(s): 
 *              Dmitriy Belov <bel@jresearch.org>
 *               .
 * * ***** END LICENSE BLOCK ***** */
/*
 * Created on 11.07.2004
 *
 */
package org.jresearch.gossip.util.test;

import org.jresearch.gossip.util.HtmlGradient;

import junit.framework.TestCase;

/**
 * @author Dmitry Belov
 *  
 */
public class HtmlGradientTest extends TestCase {

    public void testMakeHGradientHtml() {
        System.out.println(HtmlGradient.makeHGradientHtml("FFFFFF", "000000",
               128, "10"));
    }
    public void testMakeVGradientHtml() {
        System.out.println(HtmlGradient.makeVGradientHtml("FFFFFF", "000000",
                128, "10"));
    }
}