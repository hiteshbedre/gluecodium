//
//
#pragma once
#ifdef __cplusplus
extern "C" {
#endif
#include "cbridge/include/BaseHandle.h"
#include "cbridge/include/Export.h"
_GLUECODIUM_C_EXPORT void smoke_InterfaceWithAllParents_release_handle(_baseRef handle);
_GLUECODIUM_C_EXPORT _baseRef smoke_InterfaceWithAllParents_copy_handle(_baseRef handle);
_GLUECODIUM_C_EXPORT const void* smoke_InterfaceWithAllParents_get_swift_object_from_wrapper_cache(_baseRef handle);
_GLUECODIUM_C_EXPORT void smoke_InterfaceWithAllParents_cache_swift_object_wrapper(_baseRef handle, const void* swift_pointer);
_GLUECODIUM_C_EXPORT void smoke_InterfaceWithAllParents_remove_swift_object_from_wrapper_cache(_baseRef handle);
_GLUECODIUM_C_EXPORT void* smoke_InterfaceWithAllParents_get_typed(_baseRef handle);
typedef struct {
    void* swift_pointer;
    void(*release)(void* swift_pointer);
    void(*smoke_ParentInterfaceOne_parentFunctionOne)(void* swift_pointer);
    void(*smoke_ParentInterfaceTwo_parentFunctionTwo)(void* swift_pointer);
    void(*smoke_InterfaceWithAllParents_childFunction)(void* swift_pointer);
    _baseRef(*smoke_ParentInterfaceOne_parentPropertyOne_get)(void* swift_pointer);
    void(*smoke_ParentInterfaceOne_parentPropertyOne_set)(void* swift_pointer, _baseRef value);
    _baseRef(*smoke_ParentInterfaceTwo_parentPropertyTwo_get)(void* swift_pointer);
    void(*smoke_ParentInterfaceTwo_parentPropertyTwo_set)(void* swift_pointer, _baseRef value);
    _baseRef(*smoke_InterfaceWithAllParents_childProperty_get)(void* swift_pointer);
    void(*smoke_InterfaceWithAllParents_childProperty_set)(void* swift_pointer, _baseRef value);
} smoke_InterfaceWithAllParents_FunctionTable;
_GLUECODIUM_C_EXPORT _baseRef smoke_InterfaceWithAllParents_create_proxy(smoke_InterfaceWithAllParents_FunctionTable functionTable);
_GLUECODIUM_C_EXPORT const void* smoke_InterfaceWithAllParents_get_swift_object_from_cache(_baseRef handle);
_GLUECODIUM_C_EXPORT void smoke_InterfaceWithAllParents_childFunction(_baseRef _instance);
_GLUECODIUM_C_EXPORT _baseRef smoke_InterfaceWithAllParents_childProperty_get(_baseRef _instance);
_GLUECODIUM_C_EXPORT void smoke_InterfaceWithAllParents_childProperty_set(_baseRef _instance, _baseRef value);
#ifdef __cplusplus
}
#endif
