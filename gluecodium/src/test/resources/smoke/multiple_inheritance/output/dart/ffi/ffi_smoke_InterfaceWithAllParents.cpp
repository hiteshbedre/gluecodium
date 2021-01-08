#include "ffi_smoke_InterfaceWithAllParents.h"
#include "ConversionBase.h"
#include "ReverseCache.h"
#include "CallbacksQueue.h"
#include "IsolateContext.h"
#include "ProxyCache.h"
#include "gluecodium/TypeRepository.h"
#include "smoke/InterfaceWithAllParents.h"
#include <memory>
#include <string>
#include <memory>
#include <new>
class smoke_InterfaceWithAllParents_Proxy : public ::smoke::InterfaceWithAllParents {
public:
    smoke_InterfaceWithAllParents_Proxy(uint64_t token, int32_t isolate_id, FfiOpaqueHandle deleter, FfiOpaqueHandle f0, FfiOpaqueHandle f1, FfiOpaqueHandle f2, FfiOpaqueHandle p0g, FfiOpaqueHandle p0s, FfiOpaqueHandle p1g, FfiOpaqueHandle p1s, FfiOpaqueHandle p2g, FfiOpaqueHandle p2s)
        : token(token), isolate_id(isolate_id), deleter(deleter), f0(f0), f1(f1), f2(f2), p0g(p0g), p0s(p0s), p1g(p1g), p1s(p1s), p2g(p2g), p2s(p2s) { }
    ~smoke_InterfaceWithAllParents_Proxy() {
        gluecodium::ffi::remove_cached_proxy(token, isolate_id, "smoke_InterfaceWithAllParents");
        gluecodium::ffi::remove_cached_token(this, isolate_id);
        auto token_local = token;
        auto deleter_local = reinterpret_cast<void (*)(uint64_t)>(deleter);
        gluecodium::ffi::cbqm.enqueueCallback(isolate_id, [token_local, deleter_local]() {
            (*deleter_local)(token_local);
        });
    }
    smoke_InterfaceWithAllParents_Proxy(const smoke_InterfaceWithAllParents_Proxy&) = delete;
    smoke_InterfaceWithAllParents_Proxy& operator=(const smoke_InterfaceWithAllParents_Proxy&) = delete;
    void
    parent_function_one() override {
        dispatch([&]() { (*reinterpret_cast<bool (*)(uint64_t)>(f0))(token
        ); });
    }
    void
    parent_function_two() override {
        dispatch([&]() { (*reinterpret_cast<bool (*)(uint64_t)>(f1))(token
        ); });
    }
    void
    child_function() override {
        dispatch([&]() { (*reinterpret_cast<bool (*)(uint64_t)>(f2))(token
        ); });
    }
    std::string
    get_parent_property_one() const override {
        FfiOpaqueHandle _result_handle;
        dispatch([&]() { (*reinterpret_cast<bool (*)(uint64_t, FfiOpaqueHandle*)>(p0g))(token, &_result_handle); });
        auto _result = gluecodium::ffi::Conversion<std::string>::toCpp(_result_handle);
        delete reinterpret_cast<std::string*>(_result_handle);
        return _result;
    }
    void
    set_parent_property_one(const std::string& value) override {
        dispatch([&]() { (*reinterpret_cast<bool (*)(uint64_t, FfiOpaqueHandle)>(p0s))(token,
            gluecodium::ffi::Conversion<std::string>::toFfi(value)
        ); });
    }
    std::string
    get_parent_property_two() const override {
        FfiOpaqueHandle _result_handle;
        dispatch([&]() { (*reinterpret_cast<bool (*)(uint64_t, FfiOpaqueHandle*)>(p1g))(token, &_result_handle); });
        auto _result = gluecodium::ffi::Conversion<std::string>::toCpp(_result_handle);
        delete reinterpret_cast<std::string*>(_result_handle);
        return _result;
    }
    void
    set_parent_property_two(const std::string& value) override {
        dispatch([&]() { (*reinterpret_cast<bool (*)(uint64_t, FfiOpaqueHandle)>(p1s))(token,
            gluecodium::ffi::Conversion<std::string>::toFfi(value)
        ); });
    }
    std::string
    get_child_property() const override {
        FfiOpaqueHandle _result_handle;
        dispatch([&]() { (*reinterpret_cast<bool (*)(uint64_t, FfiOpaqueHandle*)>(p2g))(token, &_result_handle); });
        auto _result = gluecodium::ffi::Conversion<std::string>::toCpp(_result_handle);
        delete reinterpret_cast<std::string*>(_result_handle);
        return _result;
    }
    void
    set_child_property(const std::string& value) override {
        dispatch([&]() { (*reinterpret_cast<bool (*)(uint64_t, FfiOpaqueHandle)>(p2s))(token,
            gluecodium::ffi::Conversion<std::string>::toFfi(value)
        ); });
    }
private:
    const uint64_t token;
    const int32_t isolate_id;
    const FfiOpaqueHandle deleter;
    const FfiOpaqueHandle f0;
    const FfiOpaqueHandle f1;
    const FfiOpaqueHandle f2;
    const FfiOpaqueHandle p0g;
    const FfiOpaqueHandle p0s;
    const FfiOpaqueHandle p1g;
    const FfiOpaqueHandle p1s;
    const FfiOpaqueHandle p2g;
    const FfiOpaqueHandle p2s;
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
void
library_smoke_InterfaceWithAllParents_childFunction(FfiOpaqueHandle _self, int32_t _isolate_id) {
    gluecodium::ffi::IsolateContext _isolate_context(_isolate_id);
            (*gluecodium::ffi::Conversion<std::shared_ptr<::smoke::InterfaceWithAllParents>>::toCpp(_self)).child_function();
}
FfiOpaqueHandle
library_smoke_InterfaceWithAllParents_childProperty_get(FfiOpaqueHandle _self, int32_t _isolate_id) {
    gluecodium::ffi::IsolateContext _isolate_context(_isolate_id);
    return gluecodium::ffi::Conversion<std::string>::toFfi(
        (*gluecodium::ffi::Conversion<std::shared_ptr<::smoke::InterfaceWithAllParents>>::toCpp(_self)).get_child_property()
    );
}
void
library_smoke_InterfaceWithAllParents_childProperty_set__String(FfiOpaqueHandle _self, int32_t _isolate_id, FfiOpaqueHandle value) {
    gluecodium::ffi::IsolateContext _isolate_context(_isolate_id);
            (*gluecodium::ffi::Conversion<std::shared_ptr<::smoke::InterfaceWithAllParents>>::toCpp(_self)).set_child_property(
            gluecodium::ffi::Conversion<std::string>::toCpp(value)
        );
}
FfiOpaqueHandle
library_smoke_InterfaceWithAllParents_copy_handle(FfiOpaqueHandle handle) {
    return reinterpret_cast<FfiOpaqueHandle>(
        new (std::nothrow) std::shared_ptr<::smoke::InterfaceWithAllParents>(
            *reinterpret_cast<std::shared_ptr<::smoke::InterfaceWithAllParents>*>(handle)
        )
    );
}
void
library_smoke_InterfaceWithAllParents_release_handle(FfiOpaqueHandle handle) {
    delete reinterpret_cast<std::shared_ptr<::smoke::InterfaceWithAllParents>*>(handle);
}
FfiOpaqueHandle
library_smoke_InterfaceWithAllParents_create_proxy(uint64_t token, int32_t isolate_id, FfiOpaqueHandle deleter, FfiOpaqueHandle f0, FfiOpaqueHandle f1, FfiOpaqueHandle f2, FfiOpaqueHandle p0g, FfiOpaqueHandle p0s, FfiOpaqueHandle p1g, FfiOpaqueHandle p1s, FfiOpaqueHandle p2g, FfiOpaqueHandle p2s) {
    auto cached_proxy = gluecodium::ffi::get_cached_proxy<smoke_InterfaceWithAllParents_Proxy>(token, isolate_id, "smoke_InterfaceWithAllParents");
    std::shared_ptr<smoke_InterfaceWithAllParents_Proxy>* proxy_ptr;
    if (cached_proxy) {
        proxy_ptr = new (std::nothrow) std::shared_ptr<smoke_InterfaceWithAllParents_Proxy>(cached_proxy);
    } else {
        proxy_ptr = new (std::nothrow) std::shared_ptr<smoke_InterfaceWithAllParents_Proxy>(
            new (std::nothrow) smoke_InterfaceWithAllParents_Proxy(token, isolate_id, deleter, f0, f1, f2, p0g, p0s, p1g, p1s, p2g, p2s)
        );
        gluecodium::ffi::cache_proxy(token, isolate_id, "smoke_InterfaceWithAllParents", *proxy_ptr);
        gluecodium::ffi::cache_token(proxy_ptr->get(), isolate_id, token);
    }
    return reinterpret_cast<FfiOpaqueHandle>(proxy_ptr);
}
FfiOpaqueHandle
library_smoke_InterfaceWithAllParents_get_type_id(FfiOpaqueHandle handle) {
    const auto& type_id = ::gluecodium::get_type_repository().get_id(reinterpret_cast<std::shared_ptr<::smoke::InterfaceWithAllParents>*>(handle)->get());
    return reinterpret_cast<FfiOpaqueHandle>(new (std::nothrow) std::string(type_id));
}
#ifdef __cplusplus
}
#endif
