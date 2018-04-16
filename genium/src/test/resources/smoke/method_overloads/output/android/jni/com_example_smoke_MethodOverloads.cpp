/*
 *
 * Automatically generated. Do not modify. Your changes will be lost.
 */
#include "smoke/MethodOverloads.h"
#include "com_example_smoke_MethodOverloads.h"
#include "CppProxyBase.h"
#include "FieldAccessMethods.h"
#include "JniBase.h"
#include "JniCppConversionUtils.h"
#include "InstanceConversion.h"
#include "StructConversion.h"
#include "ProxyConversion.h"
#include "EnumConversion.h"
#include "ArrayConversionUtils.h"

extern "C" {

jboolean
Java_com_example_smoke_MethodOverloads_isBoolean__Z(JNIEnv* _jenv, jobject _jinstance, jboolean jinput)
{
    bool input = jinput;
    auto pointerAsLong = genium::jni::get_long_field(_jenv, _jenv->GetObjectClass(_jinstance), _jinstance, "nativeHandle");
    auto pInstanceSharedPointer = reinterpret_cast<std::shared_ptr<::smoke::MethodOverloads>*> (pointerAsLong);
    auto result = (*pInstanceSharedPointer)->is_boolean(input);
    return result;
}

jboolean
Java_com_example_smoke_MethodOverloads_isBoolean__B(JNIEnv* _jenv, jobject _jinstance, jbyte jinput)
{
    int8_t input = jinput;
    auto pointerAsLong = genium::jni::get_long_field(_jenv, _jenv->GetObjectClass(_jinstance), _jinstance, "nativeHandle");
    auto pInstanceSharedPointer = reinterpret_cast<std::shared_ptr<::smoke::MethodOverloads>*> (pointerAsLong);
    auto result = (*pInstanceSharedPointer)->is_boolean(input);
    return result;
}

jboolean
Java_com_example_smoke_MethodOverloads_isBoolean__Ljava_lang_String_2(JNIEnv* _jenv, jobject _jinstance, jstring jinput)
{
    ::std::string input;
    genium::jni::convert_from_jni( _jenv, jinput, input );
    _jenv->DeleteLocalRef(jinput);
    auto pointerAsLong = genium::jni::get_long_field(_jenv, _jenv->GetObjectClass(_jinstance), _jinstance, "nativeHandle");
    auto pInstanceSharedPointer = reinterpret_cast<std::shared_ptr<::smoke::MethodOverloads>*> (pointerAsLong);
    auto result = (*pInstanceSharedPointer)->is_boolean(input);
    return result;
}

jboolean
Java_com_example_smoke_MethodOverloads_isBoolean__Lcom_example_smoke_MethodOverloads_00024Point_2(JNIEnv* _jenv, jobject _jinstance, jobject jinput)
{
    ::smoke::MethodOverloads::Point input;
    genium::jni::convert_from_jni( _jenv, jinput, input );
    _jenv->DeleteLocalRef(jinput);
    auto pointerAsLong = genium::jni::get_long_field(_jenv, _jenv->GetObjectClass(_jinstance), _jinstance, "nativeHandle");
    auto pInstanceSharedPointer = reinterpret_cast<std::shared_ptr<::smoke::MethodOverloads>*> (pointerAsLong);
    auto result = (*pInstanceSharedPointer)->is_boolean(input);
    return result;
}

jboolean
Java_com_example_smoke_MethodOverloads_isBoolean__ZBLjava_lang_String_2Lcom_example_smoke_MethodOverloads_00024Point_2(JNIEnv* _jenv, jobject _jinstance, jboolean jinput1, jbyte jinput2, jstring jinput3, jobject jinput4)
{
    bool input1 = jinput1;
    int8_t input2 = jinput2;
    ::std::string input3;
    genium::jni::convert_from_jni( _jenv, jinput3, input3 );
    _jenv->DeleteLocalRef(jinput3);
    ::smoke::MethodOverloads::Point input4;
    genium::jni::convert_from_jni( _jenv, jinput4, input4 );
    _jenv->DeleteLocalRef(jinput4);
    auto pointerAsLong = genium::jni::get_long_field(_jenv, _jenv->GetObjectClass(_jinstance), _jinstance, "nativeHandle");
    auto pInstanceSharedPointer = reinterpret_cast<std::shared_ptr<::smoke::MethodOverloads>*> (pointerAsLong);
    auto result = (*pInstanceSharedPointer)->is_boolean(input1,input2,input3,input4);
    return result;
}

jboolean
Java_com_example_smoke_MethodOverloads_isBooleanStringArrayOverload__Ljava_util_List_2(JNIEnv* _jenv, jobject _jinstance, jobject jinput)
{
    ::smoke::MethodOverloads::StringArray input;
    genium::jni::convert_from_jni( _jenv, jinput, input );
    _jenv->DeleteLocalRef(jinput);
    auto pointerAsLong = genium::jni::get_long_field(_jenv, _jenv->GetObjectClass(_jinstance), _jinstance, "nativeHandle");
    auto pInstanceSharedPointer = reinterpret_cast<std::shared_ptr<::smoke::MethodOverloads>*> (pointerAsLong);
    auto result = (*pInstanceSharedPointer)->is_boolean(input);
    return result;
}

jboolean
Java_com_example_smoke_MethodOverloads_isBooleanIntArrayOverload__Ljava_util_List_2(JNIEnv* _jenv, jobject _jinstance, jobject jinput)
{
    ::smoke::MethodOverloads::IntArray input;
    genium::jni::convert_from_jni( _jenv, jinput, input );
    _jenv->DeleteLocalRef(jinput);
    auto pointerAsLong = genium::jni::get_long_field(_jenv, _jenv->GetObjectClass(_jinstance), _jinstance, "nativeHandle");
    auto pInstanceSharedPointer = reinterpret_cast<std::shared_ptr<::smoke::MethodOverloads>*> (pointerAsLong);
    auto result = (*pInstanceSharedPointer)->is_boolean(input);
    return result;
}

jboolean
Java_com_example_smoke_MethodOverloads_isBoolean__(JNIEnv* _jenv, jobject _jinstance)
{
    auto pointerAsLong = genium::jni::get_long_field(_jenv, _jenv->GetObjectClass(_jinstance), _jinstance, "nativeHandle");
    auto pInstanceSharedPointer = reinterpret_cast<std::shared_ptr<::smoke::MethodOverloads>*> (pointerAsLong);
    auto result = (*pInstanceSharedPointer)->is_boolean();
    return result;
}

void
Java_com_example_smoke_MethodOverloads_disposeNativeHandle(JNIEnv* _jenv, jobject _jinstance, jlong _jpointerRef)
{
    delete reinterpret_cast<std::shared_ptr<::smoke::MethodOverloads>*> (_jpointerRef);
}
}