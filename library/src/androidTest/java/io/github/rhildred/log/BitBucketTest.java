package io.github.rhildred.log;

import android.app.Activity;
import android.app.Application;
import android.test.ApplicationTestCase;

public class BitBucketTest extends ApplicationTestCase<Application> {
    public BitBucketTest() {
        super(Application.class);
    }
    public void testBitBucketLogCat() throws Exception {
        BitBucketLogCat.eSynchronous("rhildred", "faculty", "test logcat", "rhildred");
        assertEquals(true, true);
    }

}
