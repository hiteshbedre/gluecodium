/*
 *
 */
package com.example.smoke;
import android.support.annotation.NonNull;
public interface InterfaceWithAllParents extends ParentInterfaceOne, ParentInterfaceTwo {
    void childFunction();
    @NonNull
    String getChildProperty();
    void setChildProperty(@NonNull final String value);
}
