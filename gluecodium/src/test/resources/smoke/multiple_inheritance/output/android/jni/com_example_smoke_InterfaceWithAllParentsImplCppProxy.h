/*
 *
 */
#pragma once
#include "smoke\InterfaceWithAllParents.h"
#include "CppProxyBase.h"
#include "JniReference.h"
namespace gluecodium
{
namespace jni
{
class com_example_smoke_InterfaceWithAllParentsImpl_CppProxy : public CppProxyBase, public ::smoke::InterfaceWithAllParents {
public:
    com_example_smoke_InterfaceWithAllParentsImpl_CppProxy( JNIEnv* _jenv, JniReference<jobject> globalRef, jint _jHashCode );
    com_example_smoke_InterfaceWithAllParentsImpl_CppProxy( const com_example_smoke_InterfaceWithAllParentsImpl_CppProxy& ) = delete;
    com_example_smoke_InterfaceWithAllParentsImpl_CppProxy& operator=( const com_example_smoke_InterfaceWithAllParentsImpl_CppProxy& ) = delete;
    void parent_function_one(  ) override;
    void parent_function_two(  ) override;
    ::std::string get_parent_property_one(  ) const override;
    void set_parent_property_one( const ::std::string& nvalue ) override;
    ::std::string get_parent_property_two(  ) const override;
    void set_parent_property_two( const ::std::string& nvalue ) override;
    void child_function(  ) override;
    ::std::string get_child_property(  ) const override;
    void set_child_property( const ::std::string& nvalue ) override;
};
}
}
