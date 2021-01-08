/*
 *
 */
package com.example.smoke;
import android.support.annotation.NonNull;
public final class ClassWithAllParents extends ParentClass implements ParentInterfaceOne, ParentInterfaceTwo {
    /**
     * For internal use only.
     * @exclude
     */
    protected ClassWithAllParents(final long nativeHandle, final Object dummy) {
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
    @Override
    public native void parentFunctionTwo();
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
    @NonNull
    @Override
    public native String getParentPropertyTwo();
    @Override
    public native void setParentPropertyTwo(@NonNull final String value);
}
