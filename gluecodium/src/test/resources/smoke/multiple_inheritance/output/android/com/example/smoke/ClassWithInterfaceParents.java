/*
 *
 */
package com.example.smoke;
import android.support.annotation.NonNull;
import com.example.NativeBase;
public final class ClassWithInterfaceParents extends NativeBase implements ParentInterfaceOne, ParentInterfaceTwo {
    /**
     * For internal use only.
     * @exclude
     */
    protected ClassWithInterfaceParents(final long nativeHandle, final Object dummy) {
        super(nativeHandle, new Disposer() {
            @Override
            public void disposeNative(long handle) {
                disposeNativeHandle(handle);
            }
        });
    }
    private static native void disposeNativeHandle(long nativeHandle);
    public native void childFunction();
    @NonNull
    public native String getChildProperty();
    public native void setChildProperty(@NonNull final String value);
    @Override
    public native void parentFunctionOne();
    @Override
    public native void parentFunctionTwo();
    @NonNull
    @Override
    public native String getParentPropertyOne();
    @Override
    public native void setParentPropertyOne(@NonNull final String value);
    @NonNull
    @Override
    public native String getParentPropertyTwo();
    @Override
    public native void setParentPropertyTwo(@NonNull final String value);
}
