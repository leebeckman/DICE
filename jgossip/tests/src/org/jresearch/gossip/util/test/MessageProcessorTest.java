/*
 * $Id: MessageProcessorTest.java,v 1.1 2004/11/23 11:08:47 bel70 Exp $
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
 * Created on 10.07.2004
 *
 */
package org.jresearch.gossip.util.test;

import java.util.ResourceBundle;

import junit.framework.TestCase;

import org.apache.struts.util.MessageResources;
import org.jresearch.gossip.exception.ConfiguratorException;
import org.jresearch.gossip.util.MessageProcessor;

/**
 * @author Dmitry Belov
 *  
 */
public class MessageProcessorTest extends TestCase {

    MessageResources messages;

    MessageProcessor mp;

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        MessageProcessor.setEmoticonsMap(ResourceBundle
                .getBundle("org/jresearch/gossip/resources/emoticon"));
        mp = MessageProcessor.getInstance();
        messages = MessageResources
                .getMessageResources("org.jresearch.gossip.resources.lang.lang");
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Constructor for MessageProcessorTest.
     * 
     * @param name
     */
    public MessageProcessorTest(String name) {
        super(name);
    }

    public void testPrepareMessage() {

        System.out
                .println(mp
                        .prepareMessage(
                                "[b]line1[/b] ;&#41; line2 [NOSMILE];&#41;[/NOSMILE];&#41;[NOSMILE];&#41;[/NOSMILE];&#41;",
                                0, messages));
        System.out
                .println(mp
                        .prepareMessage(
                                "[b]line1[/b] ;&#41; line2 [NOSMILE];&#41;[/NOSMILE];&#41;[NOSMILE];&#41;[/NOSMILE];&#41;",
                                12, messages));
    }

    public void testCleanup() {
        System.out
                .println(mp
                        .cleanup("line1 ;&#41; line2 [NOSMILE];&#41;[/NOSMILE];&#41;[NOSMILE];&#41;[/NOSMILE];&#41;"));
    }

    public void testNl2br() {
        System.out.println(mp.nl2br("\nline1 \n line2\n"));
    }

    public void testProcessEmoticons() throws ConfiguratorException {
        System.out
                .println(mp
                        .processEmoticons(
                                "line1 ;&#41; line2 [NOSMILE];&#41;[/nOsMiLe];&#41;[NOSMILE];&#41;[/NOSMILE];&#41;",
                                messages));
    }

}