// -------------------------------------------------------------------------------------------------
//
// Copyright (C) 2018 HERE Global B.V. and/or its affiliated companies. All rights reserved.
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

#include <string>

namespace smoke {

class InheritanceRoot {
public:
    virtual ~InheritanceRoot() = 0;
public:
virtual void root_method(  ) = 0;
virtual ::std::string get_root_attribute(  ) = 0;
virtual void set_root_attribute( const ::std::string& value ) = 0;
};

}
