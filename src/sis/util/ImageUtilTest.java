package sis.util;

import junit.framework.TestCase;

public class ImageUtilTest extends TestCase {
    public void testLoadImage() {
        assertNull(ImageUtil.create("/images/bogusFilename.gif"));
        assertNotNull(ImageUtil.create("/images/course.gif"));
    }
}
