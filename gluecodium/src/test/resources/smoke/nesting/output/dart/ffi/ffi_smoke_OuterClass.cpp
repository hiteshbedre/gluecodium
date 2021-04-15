#include "ffi_smoke_OuterClass.h"
#include "ConversionBase.h"
#include "InstanceCache.h"
#include "CallbacksQueue.h"
#include "IsolateContext.h"
#include "ProxyCache.h"
#include "gluecodium/TypeRepository.h"
#include "smoke/OuterClass.h"
#include <memory>
#include <string>
#include <memory>
#include <new>
class smoke_OuterClass_InnerInterface_Proxy : public ::smoke::OuterClass::InnerInterface {
public:
    smoke_OuterClass_InnerInterface_Proxy(uint64_t token, int32_t isolate_id, Dart_Handle dart_handle, FfiOpaqueHandle f0)
        : token(token), isolate_id(isolate_id), dart_persistent_handle(Dart_NewPersistentHandle_DL(dart_handle)), f0(f0) {
        library_cache_dart_handle_by_raw_pointer(this, isolate_id, dart_handle);
    }
    ~smoke_OuterClass_InnerInterface_Proxy() {
        gluecodium::ffi::remove_cached_proxy(token, isolate_id, "smoke_OuterClass_InnerInterface");
        auto raw_pointer_local = this;
        auto isolate_id_local = isolate_id;
        auto dart_persistent_handle_local = dart_persistent_handle;
        auto deleter = [raw_pointer_local, isolate_id_local, dart_persistent_handle_local]() {
            library_uncache_dart_handle_by_raw_pointer(raw_pointer_local, isolate_id_local);
            Dart_DeletePersistentHandle_DL(dart_persistent_handle_local);
        };
        if (gluecodium::ffi::IsolateContext::is_current(isolate_id)) {
            deleter();
        } else {
            gluecodium::ffi::cbqm.enqueueCallback(isolate_id, deleter);
        }
    }
    smoke_OuterClass_InnerInterface_Proxy(const smoke_OuterClass_InnerInterface_Proxy&) = delete;
    smoke_OuterClass_InnerInterface_Proxy& operator=(const smoke_OuterClass_InnerInterface_Proxy&) = delete;
    std::string
    foo(const std::string& input) override {
        FfiOpaqueHandle _result_handle;
        dispatch([&]() { (*reinterpret_cast<bool (*)(Dart_Handle, FfiOpaqueHandle, FfiOpaqueHandle*)>(f0))(Dart_HandleFromPersistent_DL(dart_persistent_handle),
            gluecodium::ffi::Conversion<std::string>::toFfi(input),
            &_result_handle
        ); });
        auto _result = gluecodium::ffi::Conversion<std::string>::toCpp(_result_handle);
        delete reinterpret_cast<std::string*>(_result_handle);
        return _result;
    }
private:
    const uint64_t token;
    const int32_t isolate_id;
    const Dart_PersistentHandle dart_persistent_handle;
    const FfiOpaqueHandle f0;
    inline void dispatch(std::function<void()>&& callback) const
    {
        gluecodium::ffi::IsolateContext::is_current(isolate_id)
            ? callback()
            : gluecodium::ffi::cbqm.enqueueCallback(isolate_id, std::move(callback)).wait();
    }
};
#ifdef __cplusplus
extern "C" {
#endif
FfiOpaqueHandle
library_smoke_OuterClass_foo__String(FfiOpaqueHandle _self, int32_t _isolate_id, FfiOpaqueHandle input) {
    gluecodium::ffi::IsolateContext _isolate_context(_isolate_id);
    return gluecodium::ffi::Conversion<std::string>::toFfi(
        (*gluecodium::ffi::Conversion<std::shared_ptr<::smoke::OuterClass>>::toCpp(_self)).foo(
            gluecodium::ffi::Conversion<std::string>::toCpp(input)
        )
    );
}
FfiOpaqueHandle
library_smoke_OuterClass_InnerClass_foo__String(FfiOpaqueHandle _self, int32_t _isolate_id, FfiOpaqueHandle input) {
    gluecodium::ffi::IsolateContext _isolate_context(_isolate_id);
    return gluecodium::ffi::Conversion<std::string>::toFfi(
        (*gluecodium::ffi::Conversion<std::shared_ptr<::smoke::OuterClass::InnerClass>>::toCpp(_self)).foo(
            gluecodium::ffi::Conversion<std::string>::toCpp(input)
        )
    );
}
FfiOpaqueHandle
library_smoke_OuterClass_InnerInterface_foo__String(FfiOpaqueHandle _self, int32_t _isolate_id, FfiOpaqueHandle input) {
    gluecodium::ffi::IsolateContext _isolate_context(_isolate_id);
    return gluecodium::ffi::Conversion<std::string>::toFfi(
        (*gluecodium::ffi::Conversion<std::shared_ptr<::smoke::OuterClass::InnerInterface>>::toCpp(_self)).foo(
            gluecodium::ffi::Conversion<std::string>::toCpp(input)
        )
    );
}
FfiOpaqueHandle
library_smoke_OuterClass_copy_handle(FfiOpaqueHandle handle) {
    return reinterpret_cast<FfiOpaqueHandle>(
        new (std::nothrow) std::shared_ptr<::smoke::OuterClass>(
            *reinterpret_cast<std::shared_ptr<::smoke::OuterClass>*>(handle)
        )
    );
}
void
library_smoke_OuterClass_release_handle(FfiOpaqueHandle handle) {
    delete reinterpret_cast<std::shared_ptr<::smoke::OuterClass>*>(handle);
}
FfiOpaqueHandle
library_smoke_OuterClass_InnerClass_copy_handle(FfiOpaqueHandle handle) {
    return reinterpret_cast<FfiOpaqueHandle>(
        new (std::nothrow) std::shared_ptr<::smoke::OuterClass::InnerClass>(
            *reinterpret_cast<std::shared_ptr<::smoke::OuterClass::InnerClass>*>(handle)
        )
    );
}
void
library_smoke_OuterClass_InnerClass_release_handle(FfiOpaqueHandle handle) {
    delete reinterpret_cast<std::shared_ptr<::smoke::OuterClass::InnerClass>*>(handle);
}
FfiOpaqueHandle
library_smoke_OuterClass_InnerInterface_copy_handle(FfiOpaqueHandle handle) {
    return reinterpret_cast<FfiOpaqueHandle>(
        new (std::nothrow) std::shared_ptr<::smoke::OuterClass::InnerInterface>(
            *reinterpret_cast<std::shared_ptr<::smoke::OuterClass::InnerInterface>*>(handle)
        )
    );
}
void
library_smoke_OuterClass_InnerInterface_release_handle(FfiOpaqueHandle handle) {
    delete reinterpret_cast<std::shared_ptr<::smoke::OuterClass::InnerInterface>*>(handle);
}
FfiOpaqueHandle
library_smoke_OuterClass_InnerInterface_create_proxy(uint64_t token, int32_t isolate_id, Dart_Handle dart_handle, FfiOpaqueHandle f0) {
    auto cached_proxy = gluecodium::ffi::get_cached_proxy<smoke_OuterClass_InnerInterface_Proxy>(token, isolate_id, "smoke_OuterClass_InnerInterface");
    std::shared_ptr<smoke_OuterClass_InnerInterface_Proxy>* proxy_ptr;
    if (cached_proxy) {
        proxy_ptr = new (std::nothrow) std::shared_ptr<smoke_OuterClass_InnerInterface_Proxy>(cached_proxy);
    } else {
        proxy_ptr = new (std::nothrow) std::shared_ptr<smoke_OuterClass_InnerInterface_Proxy>(
            new (std::nothrow) smoke_OuterClass_InnerInterface_Proxy(token, isolate_id, dart_handle, f0)
        );
        gluecodium::ffi::cache_proxy(token, isolate_id, "smoke_OuterClass_InnerInterface", *proxy_ptr);
    }
    return reinterpret_cast<FfiOpaqueHandle>(proxy_ptr);
}
FfiOpaqueHandle
library_smoke_OuterClass_InnerInterface_get_type_id(FfiOpaqueHandle handle) {
    const auto& type_id = ::gluecodium::get_type_repository().get_id(reinterpret_cast<std::shared_ptr<::smoke::OuterClass::InnerInterface>*>(handle)->get());
    return reinterpret_cast<FfiOpaqueHandle>(new (std::nothrow) std::string(type_id));
}
#ifdef __cplusplus
}
#endif
