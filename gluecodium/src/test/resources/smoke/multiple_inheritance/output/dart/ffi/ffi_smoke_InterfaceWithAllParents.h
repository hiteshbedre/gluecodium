#pragma once
#include "Export.h"
#include "OpaqueHandle.h"
#include <stdint.h>
#ifdef __cplusplus
extern "C" {
#endif
_GLUECODIUM_FFI_EXPORT void library_smoke_InterfaceWithAllParents_childFunction(FfiOpaqueHandle _self, int32_t _isolate_id);
_GLUECODIUM_FFI_EXPORT FfiOpaqueHandle library_smoke_InterfaceWithAllParents_childProperty_get(FfiOpaqueHandle _self, int32_t _isolate_id);
_GLUECODIUM_FFI_EXPORT void library_smoke_InterfaceWithAllParents_childProperty_set__String(FfiOpaqueHandle _self, int32_t _isolate_id, FfiOpaqueHandle value);
_GLUECODIUM_FFI_EXPORT FfiOpaqueHandle library_smoke_InterfaceWithAllParents_copy_handle(FfiOpaqueHandle handle);
_GLUECODIUM_FFI_EXPORT void library_smoke_InterfaceWithAllParents_release_handle(FfiOpaqueHandle handle);
_GLUECODIUM_FFI_EXPORT FfiOpaqueHandle library_smoke_InterfaceWithAllParents_create_proxy(uint64_t token, int32_t isolate_id, FfiOpaqueHandle deleter, FfiOpaqueHandle f0, FfiOpaqueHandle f1, FfiOpaqueHandle f2, FfiOpaqueHandle p0g, FfiOpaqueHandle p0s, FfiOpaqueHandle p1g, FfiOpaqueHandle p1s, FfiOpaqueHandle p2g, FfiOpaqueHandle p2s);
_GLUECODIUM_FFI_EXPORT FfiOpaqueHandle library_smoke_InterfaceWithAllParents_get_type_id(FfiOpaqueHandle handle);
#ifdef __cplusplus
}
#endif
