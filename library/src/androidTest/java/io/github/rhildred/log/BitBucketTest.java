package io.github.rhildred.log;

import android.app.Activity;
import android.app.Application;
import android.test.ApplicationTestCase;

public class BitBucketTest extends ApplicationTestCase<Application> {
    public BitBucketTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
    }


    public void testBitBucketLogCat() throws Exception {
        assertEquals(true,
                BitBucketLogCat.eSynchronous(getContext(),"rhildred", "faculty", "test logcat", "rhildred"));
    }

}
