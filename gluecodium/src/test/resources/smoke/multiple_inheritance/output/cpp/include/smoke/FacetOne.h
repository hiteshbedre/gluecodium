// -------------------------------------------------------------------------------------------------
//
//
// -------------------------------------------------------------------------------------------------
#pragma once
#include "gluecodium/ExportGluecodiumCpp.h"
#include "smoke/ParentInterfaceOne.h"
namespace smoke {
class _GLUECODIUM_CPP_EXPORT FacetOne: public virtual ::smoke::ParentInterfaceOne {
public:
    FacetOne();
    virtual ~FacetOne() = 0;
};
}
