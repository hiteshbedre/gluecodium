//
//
#include "cbridge/include/smoke/cbridge_InterfaceWithAllParents.h"
#include "cbridge/include/StringHandle.h"
#include "cbridge_internal/include/BaseHandleImpl.h"
#include "cbridge_internal/include/CachedProxyBase.h"
#include "cbridge_internal/include/TypeInitRepository.h"
#include "cbridge_internal/include/WrapperCache.h"
#include "gluecodium/TypeRepository.h"
#include "smoke/InterfaceWithAllParents.h"
#include "smoke/ParentInterfaceOne.h"
#include "smoke/ParentInterfaceTwo.h"
#include <memory>
#include <new>
#include <string>
void smoke_InterfaceWithAllParents_release_handle(_baseRef handle) {
    delete get_pointer<::std::shared_ptr< ::smoke::InterfaceWithAllParents >>(handle);
}
_baseRef smoke_InterfaceWithAllParents_copy_handle(_baseRef handle) {
    return handle
        ? reinterpret_cast<_baseRef>(checked_pointer_copy(*get_pointer<::std::shared_ptr< ::smoke::InterfaceWithAllParents >>(handle)))
        : 0;
}
const void* smoke_InterfaceWithAllParents_get_swift_object_from_wrapper_cache(_baseRef handle) {
    return handle
        ? ::gluecodium::get_wrapper_cache().get_cached_wrapper(get_pointer<::std::shared_ptr< ::smoke::InterfaceWithAllParents >>(handle)->get())
        : nullptr;
}
void smoke_InterfaceWithAllParents_cache_swift_object_wrapper(_baseRef handle, const void* swift_pointer) {
    if (!handle) return;
    ::gluecodium::get_wrapper_cache().cache_wrapper(get_pointer<::std::shared_ptr< ::smoke::InterfaceWithAllParents >>(handle)->get(), swift_pointer);
}
void smoke_InterfaceWithAllParents_remove_swift_object_from_wrapper_cache(_baseRef handle) {
    if (!::gluecodium::WrapperCache::is_alive) return;
    ::gluecodium::get_wrapper_cache().remove_cached_wrapper(get_pointer<::std::shared_ptr< ::smoke::InterfaceWithAllParents >>(handle)->get());
}
extern "C" {
extern void* _CBridgeInitsmoke_InterfaceWithAllParents(_baseRef handle);
}
namespace {
struct smoke_InterfaceWithAllParentsRegisterInit {
    smoke_InterfaceWithAllParentsRegisterInit() {
        get_init_repository().add_init("smoke_InterfaceWithAllParents", &_CBridgeInitsmoke_InterfaceWithAllParents);
    }
} s_smoke_InterfaceWithAllParents_register_init;
}
void* smoke_InterfaceWithAllParents_get_typed(_baseRef handle) {
    const auto& real_type_id = ::gluecodium::get_type_repository().get_id(get_pointer<::std::shared_ptr< ::smoke::InterfaceWithAllParents >>(handle)->get());
    auto init_function = get_init_repository().get_init(real_type_id);
    return init_function ? init_function(handle) : _CBridgeInitsmoke_InterfaceWithAllParents(handle);
}
void smoke_InterfaceWithAllParents_childFunction(_baseRef _instance) {
    return get_pointer<::std::shared_ptr< ::smoke::InterfaceWithAllParents >>(_instance)->get()->child_function();
}
_baseRef smoke_InterfaceWithAllParents_childProperty_get(_baseRef _instance) {
    return Conversion<::std::string>::toBaseRef(get_pointer<::std::shared_ptr< ::smoke::InterfaceWithAllParents >>(_instance)->get()->get_child_property());
}
void smoke_InterfaceWithAllParents_childProperty_set(_baseRef _instance, _baseRef value) {
    return get_pointer<::std::shared_ptr< ::smoke::InterfaceWithAllParents >>(_instance)->get()->set_child_property(Conversion<::std::string>::toCpp(value));
}
class smoke_InterfaceWithAllParentsProxy : public ::smoke::InterfaceWithAllParents, public CachedProxyBase<smoke_InterfaceWithAllParentsProxy> {
public:
    smoke_InterfaceWithAllParentsProxy(smoke_InterfaceWithAllParents_FunctionTable&& functions)
     : mFunctions(::std::move(functions))
    {
    }
    virtual ~smoke_InterfaceWithAllParentsProxy() {
        mFunctions.release(mFunctions.swift_pointer);
    }
    smoke_InterfaceWithAllParentsProxy(const smoke_InterfaceWithAllParentsProxy&) = delete;
    smoke_InterfaceWithAllParentsProxy& operator=(const smoke_InterfaceWithAllParentsProxy&) = delete;
    void parent_function_one() override {
        mFunctions.smoke_ParentInterfaceOne_parentFunctionOne(mFunctions.swift_pointer);
    }
    void parent_function_two() override {
        mFunctions.smoke_ParentInterfaceTwo_parentFunctionTwo(mFunctions.swift_pointer);
    }
    void child_function() override {
        mFunctions.smoke_InterfaceWithAllParents_childFunction(mFunctions.swift_pointer);
    }
    ::std::string get_parent_property_one() const override {
        auto _call_result = mFunctions.smoke_ParentInterfaceOne_parentPropertyOne_get(mFunctions.swift_pointer);
        return Conversion<::std::string>::toCppReturn(_call_result);
    }
    void set_parent_property_one(const ::std::string& value) override {
        mFunctions.smoke_ParentInterfaceOne_parentPropertyOne_set(mFunctions.swift_pointer, Conversion<::std::string>::toBaseRef(value));
    }
    ::std::string get_parent_property_two() const override {
        auto _call_result = mFunctions.smoke_ParentInterfaceTwo_parentPropertyTwo_get(mFunctions.swift_pointer);
        return Conversion<::std::string>::toCppReturn(_call_result);
    }
    void set_parent_property_two(const ::std::string& value) override {
        mFunctions.smoke_ParentInterfaceTwo_parentPropertyTwo_set(mFunctions.swift_pointer, Conversion<::std::string>::toBaseRef(value));
    }
    ::std::string get_child_property() const override {
        auto _call_result = mFunctions.smoke_InterfaceWithAllParents_childProperty_get(mFunctions.swift_pointer);
        return Conversion<::std::string>::toCppReturn(_call_result);
    }
    void set_child_property(const ::std::string& value) override {
        mFunctions.smoke_InterfaceWithAllParents_childProperty_set(mFunctions.swift_pointer, Conversion<::std::string>::toBaseRef(value));
    }
private:
    smoke_InterfaceWithAllParents_FunctionTable mFunctions;
};
_baseRef smoke_InterfaceWithAllParents_create_proxy(smoke_InterfaceWithAllParents_FunctionTable functionTable) {
    auto proxy = smoke_InterfaceWithAllParentsProxy::get_proxy(::std::move(functionTable));
    return proxy ? reinterpret_cast<_baseRef>(new ::std::shared_ptr< ::smoke::InterfaceWithAllParents >(proxy)) : 0;
}
const void* smoke_InterfaceWithAllParents_get_swift_object_from_cache(_baseRef handle) {
    return handle ? smoke_InterfaceWithAllParentsProxy::get_swift_object(get_pointer<::std::shared_ptr< ::smoke::InterfaceWithAllParents >>(handle)->get()) : nullptr;
}
