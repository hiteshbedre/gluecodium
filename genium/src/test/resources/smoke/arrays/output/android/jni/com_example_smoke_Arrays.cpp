/*
 *
 * Automatically generated. Do not modify. Your changes will be lost.
 */
#include "smoke/Arrays.h"
#include "com_example_smoke_Arrays.h"
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
jobject
Java_com_example_smoke_Arrays_methodWithArray(JNIEnv* _jenv, jobject _jinstance, jobject jinput)
{
    ::smoke::Arrays::StringArray input;
    genium::jni::convert_from_jni( _jenv, jinput, input );
    _jenv->DeleteLocalRef(jinput);
    auto result = ::smoke::Arrays::method_with_array(input);
    return genium::jni::convert_to_jni(_jenv, result);
}
jobject
Java_com_example_smoke_Arrays_methodWithArrayInline(JNIEnv* _jenv, jobject _jinstance, jobject jinput)
{
    ::std::vector< uint8_t > input;
    genium::jni::convert_from_jni( _jenv, jinput, input );
    _jenv->DeleteLocalRef(jinput);
    auto result = ::smoke::Arrays::method_with_array_inline(input);
    return genium::jni::convert_to_jni(_jenv, result);
}
jobject
Java_com_example_smoke_Arrays_methodWithStructArray(JNIEnv* _jenv, jobject _jinstance, jobject jinput)
{
    ::std::vector< ::smoke::Arrays::BasicStruct > input;
    genium::jni::convert_from_jni( _jenv, jinput, input );
    _jenv->DeleteLocalRef(jinput);
    auto result = ::smoke::Arrays::method_with_struct_array(input);
    return genium::jni::convert_to_jni(_jenv, result);
}
jobject
Java_com_example_smoke_Arrays_methodWithArrayOfArrays(JNIEnv* _jenv, jobject _jinstance, jobject jinput)
{
    ::std::vector< ::smoke::Arrays::UIntArray > input;
    genium::jni::convert_from_jni( _jenv, jinput, input );
    _jenv->DeleteLocalRef(jinput);
    auto result = ::smoke::Arrays::method_with_array_of_arrays(input);
    return genium::jni::convert_to_jni(_jenv, result);
}
jobject
Java_com_example_smoke_Arrays_mergeArraysOfStructsWithArrays(JNIEnv* _jenv, jobject _jinstance, jobject jinlineFancyArray, jobject jfancyArray)
{
    ::std::vector< ::smoke::Arrays::FancyStruct > inlineFancyArray;
    genium::jni::convert_from_jni( _jenv, jinlineFancyArray, inlineFancyArray );
    _jenv->DeleteLocalRef(jinlineFancyArray);
    ::smoke::Arrays::FancyArray fancyArray;
    genium::jni::convert_from_jni( _jenv, jfancyArray, fancyArray );
    _jenv->DeleteLocalRef(jfancyArray);
    auto result = ::smoke::Arrays::merge_arrays_of_structs_with_arrays(inlineFancyArray,fancyArray);
    return genium::jni::convert_to_jni(_jenv, result);
}
jobject
Java_com_example_smoke_Arrays_methodWithArrayOfAliases(JNIEnv* _jenv, jobject _jinstance, jobject jinput)
{
    ::smoke::Arrays::ProfileIdList input;
    genium::jni::convert_from_jni( _jenv, jinput, input );
    _jenv->DeleteLocalRef(jinput);
    auto result = ::smoke::Arrays::method_with_array_of_aliases(input);
    return genium::jni::convert_to_jni(_jenv, result);
}
jobject
Java_com_example_smoke_Arrays_methodWithArrayOfMaps(JNIEnv* _jenv, jobject _jinstance, jobject jinput)
{
    ::smoke::Arrays::ArrayOfMaps input;
    genium::jni::convert_from_jni( _jenv, jinput, input );
    _jenv->DeleteLocalRef(jinput);
    auto result = ::smoke::Arrays::method_with_array_of_maps(input);
    return genium::jni::convert_to_jni(_jenv, result);
}
jbyteArray
Java_com_example_smoke_Arrays_methodWithByteBuffer(JNIEnv* _jenv, jobject _jinstance, jbyteArray jinput)
{
    ::std::shared_ptr< ::std::vector< uint8_t > > input;
    genium::jni::convert_from_jni( _jenv, jinput, input );
    _jenv->DeleteLocalRef(jinput);
    auto result = ::smoke::Arrays::method_with_byte_buffer(input);
    return genium::jni::convert_to_jni(_jenv, result);
}
void
Java_com_example_smoke_Arrays_disposeNativeHandle(JNIEnv* _jenv, jobject _jinstance, jlong _jpointerRef)
{
    delete reinterpret_cast<std::shared_ptr<::smoke::Arrays>*> (_jpointerRef);
}
}