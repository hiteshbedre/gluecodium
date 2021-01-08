/*
 *
 */
#include "com_example_smoke_TheDiamondImplCppProxy.h"
#include "com_example_smoke_TheDiamond__Conversion.h"
#include "ArrayConversionUtils.h"
#include "FieldAccessMethods.h"
namespace gluecodium
{
namespace jni
{
com_example_smoke_TheDiamondImpl_CppProxy::com_example_smoke_TheDiamondImpl_CppProxy( JNIEnv* _jenv, JniReference<jobject> globalRef, jint _jHashCode )
    : CppProxyBase( _jenv, std::move( globalRef ), _jHashCode, "com_example_smoke_TheDiamondImpl" ) {
}
void
com_example_smoke_TheDiamondImpl_CppProxy::parent_function_one(  ) {
    JNIEnv* jniEnv = getJniEnvironment( );
    callJavaMethod<void>( "parentFunctionOne", "()V", jniEnv  );
    if ( jniEnv->ExceptionCheck( ) )
    {
        jniEnv->ExceptionDescribe( );
        jniEnv->ExceptionClear( );
        jniEnv->FatalError( "Exception was thrown in Java and it was not handled.\n"
            "See the log for more information about the exception (including Java stack trace)." );
    }
}
::std::string
com_example_smoke_TheDiamondImpl_CppProxy::get_parent_property_one(  ) const {
    JNIEnv* jniEnv = getJniEnvironment( );
    auto result = callJavaMethod<jstring>( "getParentPropertyOne", "()Ljava/lang/String;", jniEnv  );
    if ( jniEnv->ExceptionCheck( ) )
    {
        jniEnv->ExceptionDescribe( );
        jniEnv->ExceptionClear( );
        jniEnv->FatalError( "Exception was thrown in Java and it was not handled.\n"
            "See the log for more information about the exception (including Java stack trace)." );
    }
    return convert_from_jni( jniEnv, result, (::std::string*)nullptr );
}
void
com_example_smoke_TheDiamondImpl_CppProxy::set_parent_property_one( const ::std::string& nvalue ) {
    JNIEnv* jniEnv = getJniEnvironment( );
    auto jvalue = convert_to_jni( jniEnv, nvalue );
    callJavaMethod<void>( "setParentPropertyOne", "(Ljava/lang/String;)V", jniEnv , jvalue);
    if ( jniEnv->ExceptionCheck( ) )
    {
        jniEnv->ExceptionDescribe( );
        jniEnv->ExceptionClear( );
        jniEnv->FatalError( "Exception was thrown in Java and it was not handled.\n"
            "See the log for more information about the exception (including Java stack trace)." );
    }
}
}
}
