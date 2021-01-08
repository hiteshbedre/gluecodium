/*
 *
 */
package com.example.smoke;
import android.support.annotation.NonNull;
public final class ClassWithMixedParents extends ParentClass implements ParentInterfaceOne {
    /**
     * For internal use only.
     * @exclude
     */
    protected ClassWithMixedParents(final long nativeHandle, final Object dummy) {
        super(nativeHandle, dummy);
    }
    public native void childFunction();
    @NonNull
    public native String getChildProperty();
    public native void setChildProperty(@NonNull final String value);
    @Override
    public native void parentFunction();
    @Override
    public native void parentFunctionOne();
    @NonNull
    @Override
    public native String getParentProperty();
    @Override
    public native void setParentProperty(@NonNull final String value);
    @NonNull
    @Override
    public native String getParentPropertyOne();
    @Override
    public native void setParentPropertyOne(@NonNull final String value);
}
