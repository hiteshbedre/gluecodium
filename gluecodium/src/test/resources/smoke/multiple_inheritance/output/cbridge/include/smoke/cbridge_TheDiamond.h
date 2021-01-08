//
//
#pragma once
#ifdef __cplusplus
extern "C" {
#endif
#include "cbridge/include/BaseHandle.h"
#include "cbridge/include/Export.h"
_GLUECODIUM_C_EXPORT void smoke_TheDiamond_release_handle(_baseRef handle);
_GLUECODIUM_C_EXPORT _baseRef smoke_TheDiamond_copy_handle(_baseRef handle);
_GLUECODIUM_C_EXPORT const void* smoke_TheDiamond_get_swift_object_from_wrapper_cache(_baseRef handle);
_GLUECODIUM_C_EXPORT void smoke_TheDiamond_cache_swift_object_wrapper(_baseRef handle, const void* swift_pointer);
_GLUECODIUM_C_EXPORT void smoke_TheDiamond_remove_swift_object_from_wrapper_cache(_baseRef handle);
_GLUECODIUM_C_EXPORT void* smoke_TheDiamond_get_typed(_baseRef handle);
typedef struct {
    void* swift_pointer;
    void(*release)(void* swift_pointer);
    void(*smoke_ParentInterfaceOne_parentFunctionOne)(void* swift_pointer);
    _baseRef(*smoke_ParentInterfaceOne_parentPropertyOne_get)(void* swift_pointer);
    void(*smoke_ParentInterfaceOne_parentPropertyOne_set)(void* swift_pointer, _baseRef value);
} smoke_TheDiamond_FunctionTable;
_GLUECODIUM_C_EXPORT _baseRef smoke_TheDiamond_create_proxy(smoke_TheDiamond_FunctionTable functionTable);
_GLUECODIUM_C_EXPORT const void* smoke_TheDiamond_get_swift_object_from_cache(_baseRef handle);
#ifdef __cplusplus
}
#endif
