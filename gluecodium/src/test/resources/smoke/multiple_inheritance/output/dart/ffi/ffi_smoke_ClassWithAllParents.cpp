#include "ffi_smoke_ClassWithAllParents.h"
#include "ConversionBase.h"
#include "ReverseCache.h"
#include "IsolateContext.h"
#include "gluecodium/TypeRepository.h"
#include "smoke/ClassWithAllParents.h"
#include <memory>
#include <string>
#include <memory>
#include <new>
#ifdef __cplusplus
extern "C" {
#endif
void
library_smoke_ClassWithAllParents_childFunction(FfiOpaqueHandle _self, int32_t _isolate_id) {
    gluecodium::ffi::IsolateContext _isolate_context(_isolate_id);
            (*gluecodium::ffi::Conversion<std::shared_ptr<::smoke::ClassWithAllParents>>::toCpp(_self)).child_function();
}
FfiOpaqueHandle
library_smoke_ClassWithAllParents_childProperty_get(FfiOpaqueHandle _self, int32_t _isolate_id) {
    gluecodium::ffi::IsolateContext _isolate_context(_isolate_id);
    return gluecodium::ffi::Conversion<std::string>::toFfi(
        (*gluecodium::ffi::Conversion<std::shared_ptr<::smoke::ClassWithAllParents>>::toCpp(_self)).get_child_property()
    );
}
void
library_smoke_ClassWithAllParents_childProperty_set__String(FfiOpaqueHandle _self, int32_t _isolate_id, FfiOpaqueHandle value) {
    gluecodium::ffi::IsolateContext _isolate_context(_isolate_id);
            (*gluecodium::ffi::Conversion<std::shared_ptr<::smoke::ClassWithAllParents>>::toCpp(_self)).set_child_property(
            gluecodium::ffi::Conversion<std::string>::toCpp(value)
        );
}
FfiOpaqueHandle
library_smoke_ClassWithAllParents_copy_handle(FfiOpaqueHandle handle) {
    return reinterpret_cast<FfiOpaqueHandle>(
        new (std::nothrow) std::shared_ptr<::smoke::ClassWithAllParents>(
            *reinterpret_cast<std::shared_ptr<::smoke::ClassWithAllParents>*>(handle)
        )
    );
}
void
library_smoke_ClassWithAllParents_release_handle(FfiOpaqueHandle handle) {
    delete reinterpret_cast<std::shared_ptr<::smoke::ClassWithAllParents>*>(handle);
}
FfiOpaqueHandle
library_smoke_ClassWithAllParents_get_type_id(FfiOpaqueHandle handle) {
    const auto& type_id = ::gluecodium::get_type_repository().get_id(reinterpret_cast<std::shared_ptr<::smoke::ClassWithAllParents>*>(handle)->get());
    return reinterpret_cast<FfiOpaqueHandle>(new (std::nothrow) std::string(type_id));
}
#ifdef __cplusplus
}
#endif
