/*
 *
 * Automatically generated. Do not modify. Your changes will be lost.
 */
package com.example.smoke;
import com.example.NativeBase;
public class StructsInheritance extends NativeBase {
    public static class ColoredLineInherited extends Structs.Line {
        public Color color = new Color();
        public ColoredLineInherited() {}
        public ColoredLineInherited(Color color) {
            this.color = color;
        }
    }
    /** For internal use only */
    protected StructsInheritance(final long nativeHandle) {
        super(nativeHandle, new Disposer() {
            @Override
            public void disposeNative(long handle) {
                disposeNativeHandle(handle);
            }
        });
    }
    private static native void disposeNativeHandle(long nativeHandle);
    public static native StructsInheritance.ColoredLineInherited methodWithInheritedType(final StructsInheritance.ColoredLineInherited input);
}