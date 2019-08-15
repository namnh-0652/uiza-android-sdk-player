package io.uiza.player.util;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

import android.content.Context;
import android.content.SharedPreferences;
import io.uiza.player.analytic.UzTrackingHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UzTrackingHelperTest {
    @Mock
    Context context;
    @Mock
    SharedPreferences sharedPreferences;
    @Mock
    SharedPreferences.Editor editor;

    private boolean defaultReturnBooleanValue = true;

    @Before
    public void setup() {
        Mockito.when(context.getSharedPreferences(anyString(), anyInt()))
                .thenReturn(sharedPreferences);
        Mockito.when(sharedPreferences.edit()).thenReturn(editor);
        Mockito.when(sharedPreferences.getBoolean(anyString(), anyBoolean()))
                .thenReturn(defaultReturnBooleanValue);
        Mockito.when(sharedPreferences.getBoolean(anyString(), anyBoolean()))
                .thenReturn(defaultReturnBooleanValue);
    }

    @Test
    public void isTrackedEventTypeDisplay() {
        boolean expected = UzTrackingHelper.isTrackedEventTypeDisplay(context);
        Mockito.verify(sharedPreferences).getBoolean(anyString(), anyBoolean());
        assertTrue(expected);
    }

    @Test
    public void setTrackingDoneWithEventTypeDisplay() {
        UzTrackingHelper.setTrackingDoneWithEventTypeDisplay(context, true);
        Mockito.verify(sharedPreferences).edit();
        Mockito.verify(editor).putBoolean(anyString(), anyBoolean());
        Mockito.verify(editor).apply();
    }

    @Test
    public void isTrackedEventTypePlaysRequested() {
        boolean expected = UzTrackingHelper.isTrackedEventTypePlaysRequested(context);
        Mockito.verify(sharedPreferences).getBoolean(anyString(), anyBoolean());
        assertTrue(expected);
    }

    @Test
    public void setTrackingDoneWithEventTypePlaysRequested() {
        UzTrackingHelper.setTrackingDoneWithEventTypePlaysRequested(context, true);
        Mockito.verify(sharedPreferences).edit();
        Mockito.verify(editor).putBoolean(anyString(), anyBoolean());
        Mockito.verify(editor).apply();
    }

    @Test
    public void isTrackedEventTypeVideoStarts() {
        boolean expected = UzTrackingHelper.isTrackedEventTypeVideoStarts(context);
        Mockito.verify(sharedPreferences).getBoolean(anyString(), anyBoolean());
        assertTrue(expected);
    }

    @Test
    public void setTrackingDoneWithEventTypeVideoStarts() {
        UzTrackingHelper.setTrackingDoneWithEventTypeVideoStarts(context, true);
        Mockito.verify(sharedPreferences).edit();
        Mockito.verify(editor).putBoolean(anyString(), anyBoolean());
        Mockito.verify(editor).apply();
    }

    @Test
    public void isTrackedEventTypeView() {
        boolean expected = UzTrackingHelper.isTrackedEventTypeView(context);
        Mockito.verify(sharedPreferences).getBoolean(anyString(), anyBoolean());
        assertTrue(expected);
    }

    @Test
    public void setTrackingDoneWithEventTypeView() {
        UzTrackingHelper.setTrackingDoneWithEventTypeView(context, true);
        Mockito.verify(sharedPreferences).edit();
        Mockito.verify(editor).putBoolean(anyString(), anyBoolean());
        Mockito.verify(editor).apply();
    }

    @Test
    public void isTrackedEventTypeReplay() {
        boolean expected = UzTrackingHelper.isTrackedEventTypeReplay(context);
        Mockito.verify(sharedPreferences).getBoolean(anyString(), anyBoolean());
        assertTrue(expected);
    }

    @Test
    public void setTrackingDoneWithEventTypeReplay() {
        UzTrackingHelper.setTrackingDoneWithEventTypeReplay(context, true);
        Mockito.verify(sharedPreferences).edit();
        Mockito.verify(editor).putBoolean(anyString(), anyBoolean());
        Mockito.verify(editor).apply();
    }

    @Test
    public void isTrackedEventTypePlayThrought25() {
        boolean expected = UzTrackingHelper.isTrackedEventTypePlayThrought25(context);
        Mockito.verify(sharedPreferences).getBoolean(anyString(), anyBoolean());
        assertTrue(expected);
    }

    @Test
    public void setTrackingDoneWithEventTypePlayThrought25() {
        UzTrackingHelper.setTrackingDoneWithEventTypePlayThrought25(context, true);
        Mockito.verify(sharedPreferences).edit();
        Mockito.verify(editor).putBoolean(anyString(), anyBoolean());
        Mockito.verify(editor).apply();
    }

    @Test
    public void isTrackedEventTypePlayThrought50() {
        boolean expected = UzTrackingHelper.isTrackedEventTypePlayThrought50(context);
        Mockito.verify(sharedPreferences).getBoolean(anyString(), anyBoolean());
        assertTrue(expected);
    }

    @Test
    public void setTrackingDoneWithEventTypePlayThrought50() {
        UzTrackingHelper.setTrackingDoneWithEventTypePlayThrought50(context, true);
        Mockito.verify(sharedPreferences).edit();
        Mockito.verify(editor).putBoolean(anyString(), anyBoolean());
        Mockito.verify(editor).apply();
    }

    @Test
    public void isTrackedEventTypePlayThrought75() {
        boolean expected = UzTrackingHelper.isTrackedEventTypePlayThrought75(context);
        Mockito.verify(sharedPreferences).getBoolean(anyString(), anyBoolean());
        assertTrue(expected);
    }

    @Test
    public void setTrackingDoneWithEventTypePlayThrought75() {
        UzTrackingHelper.setTrackingDoneWithEventTypePlayThrought75(context, true);
        Mockito.verify(sharedPreferences).edit();
        Mockito.verify(editor).putBoolean(anyString(), anyBoolean());
        Mockito.verify(editor).apply();
    }

    @Test
    public void isTrackedEventTypePlayThrought100() {
        boolean expected = UzTrackingHelper.isTrackedEventTypePlayThrough100(context);
        Mockito.verify(sharedPreferences).getBoolean(anyString(), anyBoolean());
        assertTrue(expected);
    }

    @Test
    public void setTrackingDoneWithEventTypePlayThrought100() {
        UzTrackingHelper.setTrackingDoneWithEventTypePlayThrough100(context, true);
        Mockito.verify(sharedPreferences).edit();
        Mockito.verify(editor).putBoolean(anyString(), anyBoolean());
        Mockito.verify(editor).apply();
    }

    @Test
    public void clearAllValues() {
        UzTrackingHelper.resetTracking(context);
        Mockito.verify(sharedPreferences, times(9)).edit();
        Mockito.verify(editor, times(9)).putBoolean(anyString(), anyBoolean());
        Mockito.verify(editor, times(9)).apply();
    }
}
