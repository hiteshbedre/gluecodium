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

#include "cbridge/include/StringHandle.h"
#include "cbridge_internal/include/BaseHandleImpl.h"
#include <new>
#include <string>

_baseRef
std_string_create( const char* c_str )
{
    return reinterpret_cast<_baseRef>( new ( std::nothrow ) std::string( c_str ) );
}

void
std_string_release( _baseRef handle )
{
    delete get_pointer< std::string >( handle );
}

const char*
std_string_data_get( _baseRef handle )
{
    return get_pointer< std::string >( handle )->data( );
}

int64_t
std_string_size_get( _baseRef handle )
{
    return get_pointer< std::string >( handle )->size( );
}
