/*
 *
 */
package com.example.smoke;
import android.support.annotation.NonNull;
import com.example.NativeBase;
class TheDiamondImpl extends NativeBase implements TheDiamond {
    protected TheDiamondImpl(final long nativeHandle, final Object dummy) {
        super(nativeHandle, new Disposer() {
            @Override
            public void disposeNative(long handle) {
                disposeNativeHandle(handle);
            }
        });
    }
    private static native void disposeNativeHandle(long nativeHandle);
    @Override
    public native void parentFunctionOne();
    @NonNull
    @Override
    public native String getParentPropertyOne();
    @Override
    public native void setParentPropertyOne(@NonNull final String value);
}
