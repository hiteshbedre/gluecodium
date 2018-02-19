/*
 *
 * Automatically generated. Do not modify. Your changes will be lost.
 */
package com.example.examples;
import com.example.NativeBase;
/**
 * This is some very useful interface.
 */
public class Comments extends NativeBase {
    /**
     * This is some very useful constant.
     */
    static final boolean VERY_USEFUL = true;
    /**
     * This is some very useful enum.
     */
    public enum SomeEnum {
        /**
         * Not quite useful
         */
        USELESS(0),
        /**
         * Somewhat useful
         */
        USEFUL(1);
        public final int value;
        SomeEnum(final int value) {
            this.value = value;
        }
    }
    /**
     * This is some very useful struct.
     */
    public static class SomeStruct {
        /**
         * How useful this struct is
         */
        public boolean someField;
        public SomeStruct() {}
        public SomeStruct(boolean someField) {
            this.someField = someField;
        }
    }
    /** For internal use only */
    protected Comments(final long nativeHandle) {
        super(nativeHandle, new Disposer() {
            @Override
            public void disposeNative(long handle) {
                disposeNativeHandle(handle);
            }
        });
    }
    private static native void disposeNativeHandle(long nativeHandle);
    /**
     * This is some very useful method that measures the usefulness of its input.
     * @param input Very useful input parameter
     * @return Usefulness of the input
     */
    public native boolean someMethod(final String input);
    /**
     * This is some very useful attribute.
     */
    public native boolean getSomeAttribute();
    /**
     * This is some very useful attribute.
     */
    public native void setSomeAttribute(final boolean value);
}
