/*
 * Created on 23.07.2004
 *
 */
package org.jresearch.gossip.util.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jresearch.gossip.IConst;
import org.jresearch.gossip.util.DesEncrypter;

import junit.framework.TestCase;


/**
 * @author dbelov
 *
 */
public class DesEncrypterTest extends TestCase {
    private DesEncrypter des;
    private static final String TEST_VAL="test";
    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        des=new DesEncrypter(IConst.VALUES.ENCRYPTER_KEY);
    }

    public void testEncrypt() throws UnsupportedEncodingException {
        String str=des.encrypt(TEST_VAL);
        System.out.println(str);
    }

    public void testDecrypt() throws IOException {
        String str=des.encrypt(TEST_VAL);
        System.out.println(des.decrypt(str));
    }

}
