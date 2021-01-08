//
//
#include "cbridge/include/smoke/cbridge_TheDiamond.h"
#include "cbridge/include/StringHandle.h"
#include "cbridge_internal/include/BaseHandleImpl.h"
#include "cbridge_internal/include/CachedProxyBase.h"
#include "cbridge_internal/include/TypeInitRepository.h"
#include "cbridge_internal/include/WrapperCache.h"
#include "gluecodium/TypeRepository.h"
#include "smoke/FacetOne.h"
#include "smoke/FacetTwo.h"
#include "smoke/ParentInterfaceOne.h"
#include "smoke/TheDiamond.h"
#include <memory>
#include <new>
#include <string>
void smoke_TheDiamond_release_handle(_baseRef handle) {
    delete get_pointer<::std::shared_ptr< ::smoke::TheDiamond >>(handle);
}
_baseRef smoke_TheDiamond_copy_handle(_baseRef handle) {
    return handle
        ? reinterpret_cast<_baseRef>(checked_pointer_copy(*get_pointer<::std::shared_ptr< ::smoke::TheDiamond >>(handle)))
        : 0;
}
const void* smoke_TheDiamond_get_swift_object_from_wrapper_cache(_baseRef handle) {
    return handle
        ? ::gluecodium::get_wrapper_cache().get_cached_wrapper(get_pointer<::std::shared_ptr< ::smoke::TheDiamond >>(handle)->get())
        : nullptr;
}
void smoke_TheDiamond_cache_swift_object_wrapper(_baseRef handle, const void* swift_pointer) {
    if (!handle) return;
    ::gluecodium::get_wrapper_cache().cache_wrapper(get_pointer<::std::shared_ptr< ::smoke::TheDiamond >>(handle)->get(), swift_pointer);
}
void smoke_TheDiamond_remove_swift_object_from_wrapper_cache(_baseRef handle) {
    if (!::gluecodium::WrapperCache::is_alive) return;
    ::gluecodium::get_wrapper_cache().remove_cached_wrapper(get_pointer<::std::shared_ptr< ::smoke::TheDiamond >>(handle)->get());
}
extern "C" {
extern void* _CBridgeInitsmoke_TheDiamond(_baseRef handle);
}
namespace {
struct smoke_TheDiamondRegisterInit {
    smoke_TheDiamondRegisterInit() {
        get_init_repository().add_init("smoke_TheDiamond", &_CBridgeInitsmoke_TheDiamond);
    }
} s_smoke_TheDiamond_register_init;
}
void* smoke_TheDiamond_get_typed(_baseRef handle) {
    const auto& real_type_id = ::gluecodium::get_type_repository().get_id(get_pointer<::std::shared_ptr< ::smoke::TheDiamond >>(handle)->get());
    auto init_function = get_init_repository().get_init(real_type_id);
    return init_function ? init_function(handle) : _CBridgeInitsmoke_TheDiamond(handle);
}
class smoke_TheDiamondProxy : public ::smoke::TheDiamond, public CachedProxyBase<smoke_TheDiamondProxy> {
public:
    smoke_TheDiamondProxy(smoke_TheDiamond_FunctionTable&& functions)
     : mFunctions(::std::move(functions))
    {
    }
    virtual ~smoke_TheDiamondProxy() {
        mFunctions.release(mFunctions.swift_pointer);
    }
    smoke_TheDiamondProxy(const smoke_TheDiamondProxy&) = delete;
    smoke_TheDiamondProxy& operator=(const smoke_TheDiamondProxy&) = delete;
    void parent_function_one() override {
        mFunctions.smoke_ParentInterfaceOne_parentFunctionOne(mFunctions.swift_pointer);
    }
    ::std::string get_parent_property_one() const override {
        auto _call_result = mFunctions.smoke_ParentInterfaceOne_parentPropertyOne_get(mFunctions.swift_pointer);
        return Conversion<::std::string>::toCppReturn(_call_result);
    }
    void set_parent_property_one(const ::std::string& value) override {
        mFunctions.smoke_ParentInterfaceOne_parentPropertyOne_set(mFunctions.swift_pointer, Conversion<::std::string>::toBaseRef(value));
    }
private:
    smoke_TheDiamond_FunctionTable mFunctions;
};
_baseRef smoke_TheDiamond_create_proxy(smoke_TheDiamond_FunctionTable functionTable) {
    auto proxy = smoke_TheDiamondProxy::get_proxy(::std::move(functionTable));
    return proxy ? reinterpret_cast<_baseRef>(new ::std::shared_ptr< ::smoke::TheDiamond >(proxy)) : 0;
}
const void* smoke_TheDiamond_get_swift_object_from_cache(_baseRef handle) {
    return handle ? smoke_TheDiamondProxy::get_swift_object(get_pointer<::std::shared_ptr< ::smoke::TheDiamond >>(handle)->get()) : nullptr;
}
