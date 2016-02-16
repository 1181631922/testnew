package com.fanyafeng.testnew;

import android.support.v7.widget.Toolbar;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.fanyafeng.testnew.toolar.TestToolbarActivity;

/**
 * Created by fanyafeng on 2015/12/22,0022.
 */
public class ToolbarActivityTest extends ActivityInstrumentationTestCase2<TestToolbarActivity> {
    private TestToolbarActivity testToolbarActivity;
    private Toolbar toolbar;

    public ToolbarActivityTest() {
        super(TestToolbarActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        testToolbarActivity = getActivity();
        toolbar = (Toolbar) testToolbarActivity.findViewById(R.id.toolbar);
    }

    public void testPrecondition() {
        assertNotNull("Toolbaractivity is null", testToolbarActivity);
    }

    public void testToolbarActivity_label() {
        final String expected = "测试Html";
        final String actual = toolbar.getTitle().toString();
        assertEquals(expected, actual);
    }
}
