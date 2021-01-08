/*
 *
 */
#pragma once
#include <jni.h>
#ifdef __cplusplus
extern "C" {
#endif
JNIEXPORT void JNICALL
Java_com_example_smoke_ClassWithAllParents_childFunction(JNIEnv* _jenv, jobject _jinstance);
JNIEXPORT jstring JNICALL
Java_com_example_smoke_ClassWithAllParents_getChildProperty(JNIEnv* _jenv, jobject _jinstance);
JNIEXPORT void JNICALL
Java_com_example_smoke_ClassWithAllParents_setChildProperty(JNIEnv* _jenv, jobject _jinstance, jstring jvalue);
JNIEXPORT void JNICALL
Java_com_example_smoke_ClassWithAllParents_parentFunction(JNIEnv* _jenv, jobject _jinstance);
JNIEXPORT void JNICALL
Java_com_example_smoke_ClassWithAllParents_parentFunctionOne(JNIEnv* _jenv, jobject _jinstance);
JNIEXPORT void JNICALL
Java_com_example_smoke_ClassWithAllParents_parentFunctionTwo(JNIEnv* _jenv, jobject _jinstance);
JNIEXPORT jstring JNICALL
Java_com_example_smoke_ClassWithAllParents_getParentProperty(JNIEnv* _jenv, jobject _jinstance);
JNIEXPORT void JNICALL
Java_com_example_smoke_ClassWithAllParents_setParentProperty(JNIEnv* _jenv, jobject _jinstance, jstring jvalue);
JNIEXPORT jstring JNICALL
Java_com_example_smoke_ClassWithAllParents_getParentPropertyOne(JNIEnv* _jenv, jobject _jinstance);
JNIEXPORT void JNICALL
Java_com_example_smoke_ClassWithAllParents_setParentPropertyOne(JNIEnv* _jenv, jobject _jinstance, jstring jvalue);
JNIEXPORT jstring JNICALL
Java_com_example_smoke_ClassWithAllParents_getParentPropertyTwo(JNIEnv* _jenv, jobject _jinstance);
JNIEXPORT void JNICALL
Java_com_example_smoke_ClassWithAllParents_setParentPropertyTwo(JNIEnv* _jenv, jobject _jinstance, jstring jvalue);
#ifdef __cplusplus
}
#endif
