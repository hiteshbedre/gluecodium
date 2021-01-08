/*
 *
 */
#pragma once
#include "smoke/TheDiamond.h"
#include "CppProxyBase.h"
#include "JniReference.h"
namespace gluecodium
{
namespace jni
{
class com_example_smoke_TheDiamondImpl_CppProxy : public CppProxyBase, public ::smoke::TheDiamond {
public:
    com_example_smoke_TheDiamondImpl_CppProxy( JNIEnv* _jenv, JniReference<jobject> globalRef, jint _jHashCode );
    com_example_smoke_TheDiamondImpl_CppProxy( const com_example_smoke_TheDiamondImpl_CppProxy& ) = delete;
    com_example_smoke_TheDiamondImpl_CppProxy& operator=( const com_example_smoke_TheDiamondImpl_CppProxy& ) = delete;
    void parent_function_one(  ) override;
    ::std::string get_parent_property_one(  ) const override;
    void set_parent_property_one( const ::std::string& nvalue ) override;
};
}
}
