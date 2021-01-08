// -------------------------------------------------------------------------------------------------
//
//
// -------------------------------------------------------------------------------------------------
#pragma once
#include "gluecodium/ExportGluecodiumCpp.h"
#include "smoke/ParentClass.h"
#include "smoke/ParentInterfaceOne.h"
#include <string>
namespace smoke {
class _GLUECODIUM_CPP_EXPORT ClassWithMixedParents: public ::smoke::ParentClass, public virtual ::smoke::ParentInterfaceOne {
public:
    ClassWithMixedParents();
    virtual ~ClassWithMixedParents() = 0;
public:
    virtual void child_function(  ) = 0;
    virtual ::std::string get_child_property(  ) const = 0;
    virtual void set_child_property( const ::std::string& value ) = 0;
};
}
