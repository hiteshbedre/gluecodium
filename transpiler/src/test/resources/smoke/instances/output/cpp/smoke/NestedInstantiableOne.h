// -------------------------------------------------------------------------------------------------
//
// Copyright (C) 2017 HERE Global B.V. and/or its affiliated companies. All rights reserved.
//
// This software, including documentation, is protected by copyright controlled by
// HERE Global B.V. All rights are reserved. Copying, including reproducing, storing,
// adapting or translating, any or all of this material requires the prior written
// consent of HERE Global B.V. This material also contains confidential information,
// which may not be disclosed to others without prior written consent of HERE Global B.V.
//
// -------------------------------------------------------------------------------------------------
//
// Automatically generated. Do not modify. Your changes will be lost.
//
// -------------------------------------------------------------------------------------------------

#pragma once

#include "cpp/smoke/SimpleInstantiableOne.h"
#include <memory>

namespace smoke {

class NestedInstantiableOne {
public:
    virtual ~NestedInstantiableOne() = 0;

public:
virtual void setSameTypeInstances( const ::std::shared_ptr< ::smoke::SimpleInstantiableOne >& instanceOne, const ::std::shared_ptr< ::smoke::SimpleInstantiableOne >& instanceTwo ) = 0;
/**
 *
 * * @return The result type, containing ::std::shared_ptr< ::smoke::SimpleInstantiableOne > value.
 */
virtual ::std::shared_ptr< ::smoke::SimpleInstantiableOne > getInstanceOne(  ) = 0;
/**
 *
 * * @return The result type, containing ::std::shared_ptr< ::smoke::SimpleInstantiableOne > value.
 */
virtual ::std::shared_ptr< ::smoke::SimpleInstantiableOne > getInstanceTwo(  ) = 0;

};

}
