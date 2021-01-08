#pragma once
#include "Export.h"
#include "OpaqueHandle.h"
#include <stdint.h>
#ifdef __cplusplus
extern "C" {
#endif
_GLUECODIUM_FFI_EXPORT void library_smoke_ClassWithAllParents_childFunction(FfiOpaqueHandle _self, int32_t _isolate_id);
_GLUECODIUM_FFI_EXPORT FfiOpaqueHandle library_smoke_ClassWithAllParents_childProperty_get(FfiOpaqueHandle _self, int32_t _isolate_id);
_GLUECODIUM_FFI_EXPORT void library_smoke_ClassWithAllParents_childProperty_set__String(FfiOpaqueHandle _self, int32_t _isolate_id, FfiOpaqueHandle value);
_GLUECODIUM_FFI_EXPORT FfiOpaqueHandle library_smoke_ClassWithAllParents_copy_handle(FfiOpaqueHandle handle);
_GLUECODIUM_FFI_EXPORT void library_smoke_ClassWithAllParents_release_handle(FfiOpaqueHandle handle);
_GLUECODIUM_FFI_EXPORT FfiOpaqueHandle library_smoke_ClassWithAllParents_get_type_id(FfiOpaqueHandle handle);
#ifdef __cplusplus
}
#endif
