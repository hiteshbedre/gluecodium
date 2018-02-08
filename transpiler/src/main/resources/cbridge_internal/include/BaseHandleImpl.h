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

#pragma once

#include <memory>
#include "cbridge/include/BaseHandle.h"

template < typename T >
inline static T*
get_pointer( _baseRef handle )
{
    return reinterpret_cast< T* >( handle );
}

template < typename T >
inline static std::shared_ptr< T >*
checked_pointer_copy( std::shared_ptr< T >&& pointer )
{
    return !pointer ? nullptr : new std::shared_ptr< T >( std::move( pointer ) );
}

template < typename T >
inline static std::shared_ptr< T >*
checked_pointer_copy( const std::shared_ptr< T >& pointer )
{
    return !pointer ? nullptr : new std::shared_ptr< T >( pointer );
}
