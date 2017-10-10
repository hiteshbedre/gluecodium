/*
 * Copyright (C) 2017 HERE Global B.V. and/or its affiliated companies. All rights reserved.
 *
 * This software, including documentation, is protected by copyright controlled by
 * HERE Global B.V. All rights are reserved. Copying, including reproducing, storing,
 * adapting or translating, any or all of this material requires the prior written
 * consent of HERE Global B.V. This material also contains confidential information,
 * which may not be disclosed to others without prior written consent of HERE Global B.V.
 *
 * Automatically generated. Do not modify. Your changes will be lost.
 */

package com.here.android.smoke;

import com.here.android.NativeBase;
import com.here.android.smoke.NestedInstantiableOne;
import com.here.android.smoke.NestedInstantiableTwo;
import com.here.android.smoke.SimpleInstantiableOne;
import com.here.android.smoke.SimpleInstantiableTwo;

class NestedInstantiableTwoImpl extends NativeBase implements NestedInstantiableTwo {
    protected NestedInstantiableTwoImpl(final long nativeHandle) {
        super(nativeHandle, new Disposer() {
            @Override
            public void disposeNative(long handle) {
                disposeNativeHandle(handle);
            }
        });
    }

    private static native void disposeNativeHandle(long nativeHandle);
    public native void setMultipleTypeInstances(final SimpleInstantiableOne instanceOne, final SimpleInstantiableTwo instanceTwo, final NestedInstantiableOne nestedInstantiable);
    public native SimpleInstantiableOne getInstantiableOne();
    public native SimpleInstantiableTwo getInstantiableTwo();
    public native NestedInstantiableOne getNestedInstantiable();
    public native void setSelfInstantiable(final NestedInstantiableTwo selfInstance);
    public native NestedInstantiableTwo getSelfInstantiable();
}