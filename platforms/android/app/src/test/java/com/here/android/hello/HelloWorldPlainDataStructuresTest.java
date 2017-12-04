package com.here.android.hello;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;

import com.here.android.RobolectricApplication;

import com.here.android.hello.HelloWorldPlainDataStructures.NumericSyncResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.M, application = RobolectricApplication.class, constants = BuildConfig.class)
public final class HelloWorldPlainDataStructuresTest {
    @Test
    public void methodWithNonNestedType_nonNestedPlainDataStructure() {
        HelloWorldPlainDataStructures.SyncResult input = createSyncResult();

        HelloWorldPlainDataStructures.SyncResult result =
                HelloWorldPlainDataStructures.methodWithNonNestedType(input);

        assertEquals(input.lastUpdatedTimeStamp, result.lastUpdatedTimeStamp);
        assertEquals(input.numberOfChanges + 1, result.numberOfChanges);
    }

    @Test
    public void methodWithNonNestedType_nestedPlainDataStructure() {
        HelloWorldPlainDataStructures.IdentifiableSyncResult input =
                new HelloWorldPlainDataStructures.IdentifiableSyncResult();
        input.syncResult = createSyncResult();
        input.id = 20;

        HelloWorldPlainDataStructures.IdentifiableSyncResult result =
                HelloWorldPlainDataStructures.methodWithNestedType(input);

        assertEquals(input.syncResult.lastUpdatedTimeStamp, result.syncResult.lastUpdatedTimeStamp);
        assertEquals(input.syncResult.numberOfChanges + 1, result.syncResult.numberOfChanges);
        assertEquals(input.id, result.id);
    }

    @Test
    public void methodWithInheritedStruct() {
        NumericSyncResult input = new NumericSyncResult();
        input.lastUpdatedTimeStamp = 10;
        input.numberOfChanges = 100;
        input.result = 1000;

        NumericSyncResult result = HelloWorldPlainDataStructures.methodWithInheritedStruct(input);

        assertEquals(10, result.lastUpdatedTimeStamp);
        assertEquals(101, result.numberOfChanges);
        assertEquals(1000, result.result);
    }

    @NonNull
    private static HelloWorldPlainDataStructures.SyncResult createSyncResult() {
        HelloWorldPlainDataStructures.SyncResult input =
                new HelloWorldPlainDataStructures.SyncResult();
        input.lastUpdatedTimeStamp = 100L;
        input.numberOfChanges = 5;
        return input;
    }
}
