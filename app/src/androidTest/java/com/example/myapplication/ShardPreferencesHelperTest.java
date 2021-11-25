package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Calendar;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class ShardPreferencesHelperTest {
    private static final String TEST_NAME = "Test Name";

    private static final String TEST_EMAIL = "test@email.com";

    private static final Calendar TEST_DATE_OF_BIRTH = Calendar.getInstance();

    private SharedPreferenceEntry mShardPreferenceEntry;

    private SharedPreferencesHelper mSharedPreferencesHelper;

    private SharedPreferences mSharePreferences;

    /** 上下文 */
    private Context mContext;
    /** 如果需要扩展类的行为，可以通过mock来实现 */
    private SharedPreferencesHelper mMockSharedPreferencesHelper;

    /** mock操作，用于模拟失败的操作 */
    @Mock
    SharedPreferences mMockSharePreferences;

    @Mock
    SharedPreferences.Editor mMockBrokenEditor;
    @Before
    public void setUp() throws Exception {
        //获取application的context
        mContext = getInstrumentation().getTargetContext();
        //实例化SharedPreferences
        mSharePreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        mShardPreferenceEntry = new SharedPreferenceEntry(TEST_NAME, TEST_DATE_OF_BIRTH, TEST_EMAIL);
        //实例化SharedPreferencesHelper，依赖注入SharePreferences
        mSharedPreferencesHelper = new SharedPreferencesHelper(mSharePreferences);

        //以下是在mock的相关操作，模拟commit失败
        mMockSharePreferences = Mockito.mock(SharedPreferences.class);
        mMockBrokenEditor = Mockito.mock(SharedPreferences.Editor.class);
        when(mMockSharePreferences.edit()).thenReturn(mMockBrokenEditor);
        when(mMockBrokenEditor.commit()).thenReturn(false);
        mMockSharedPreferencesHelper = new SharedPreferencesHelper(mMockSharePreferences);

    }

    /**
     * 判等
     * @param sharedPreferenceEntry
     * @param target
     * @return
     */
    private boolean isEquals(SharedPreferenceEntry sharedPreferenceEntry, SharedPreferenceEntry target){
        return sharedPreferenceEntry.getName().equals(target.getName())&&sharedPreferenceEntry.getEmail().equals(target.getEmail())&&sharedPreferenceEntry.getDateOfBirth().equals(target.getDateOfBirth());
    }

    /**
     * 测试保存数据是否成功
     */
    @Test
    public void sharedPreferencesHelper_SavePersonalInformation() throws Exception {
        assertThat(mSharedPreferencesHelper.savePersonalInfo(mShardPreferenceEntry), is(true));
    }

    /**
     * 测试保存数据，然后获取数据是否成功
     */
    @Test
    public void sharedPreferencesHelper_SaveAndReadPersonalInformation() throws Exception {
        Log.i("=====","=====");
        System.out.println();
        mSharedPreferencesHelper.savePersonalInfo(mShardPreferenceEntry);
        SharedPreferenceEntry sharedPreferenceEntry = mSharedPreferencesHelper.getPersonalInfo();
        System.out.println("====="+sharedPreferenceEntry.getName());
        assertThat(isEquals(mShardPreferenceEntry, sharedPreferenceEntry), is(true));
    }

    /**
     * 该方法的测试需要修改SharedPreference的部分行为，所以需要用到mock
     */
    @Test
    public void sharedPreferencesHelper_SavePersonalInformationFailed_ReturnsFalse() {
        // Read personal information from a broken SharedPreferencesHelper
        boolean success =
                mMockSharedPreferencesHelper.savePersonalInfo(mShardPreferenceEntry);
        assertThat("Makes sure writing to a broken SharedPreferencesHelper returns false", success,
                is(false));

    }
}
