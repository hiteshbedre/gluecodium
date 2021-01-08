//
//
#include "cbridge/include/smoke/cbridge_ClassWithAllParents.h"
#include "cbridge/include/StringHandle.h"
#include "cbridge_internal/include/BaseHandleImpl.h"
#include "cbridge_internal/include/TypeInitRepository.h"
#include "cbridge_internal/include/WrapperCache.h"
#include "gluecodium/TypeRepository.h"
#include "smoke/ClassWithAllParents.h"
#include <memory>
#include <new>
#include <string>
void smoke_ClassWithAllParents_release_handle(_baseRef handle) {
    delete get_pointer<::std::shared_ptr< ::smoke::ClassWithAllParents >>(handle);
}
_baseRef smoke_ClassWithAllParents_copy_handle(_baseRef handle) {
    return handle
        ? reinterpret_cast<_baseRef>(checked_pointer_copy(*get_pointer<::std::shared_ptr< ::smoke::ClassWithAllParents >>(handle)))
        : 0;
}
const void* smoke_ClassWithAllParents_get_swift_object_from_wrapper_cache(_baseRef handle) {
    return handle
        ? ::gluecodium::get_wrapper_cache().get_cached_wrapper(get_pointer<::std::shared_ptr< ::smoke::ClassWithAllParents >>(handle)->get())
        : nullptr;
}
void smoke_ClassWithAllParents_cache_swift_object_wrapper(_baseRef handle, const void* swift_pointer) {
    if (!handle) return;
    ::gluecodium::get_wrapper_cache().cache_wrapper(get_pointer<::std::shared_ptr< ::smoke::ClassWithAllParents >>(handle)->get(), swift_pointer);
}
void smoke_ClassWithAllParents_remove_swift_object_from_wrapper_cache(_baseRef handle) {
    if (!::gluecodium::WrapperCache::is_alive) return;
    ::gluecodium::get_wrapper_cache().remove_cached_wrapper(get_pointer<::std::shared_ptr< ::smoke::ClassWithAllParents >>(handle)->get());
}
extern "C" {
extern void* _CBridgeInitsmoke_ClassWithAllParents(_baseRef handle);
}
namespace {
struct smoke_ClassWithAllParentsRegisterInit {
    smoke_ClassWithAllParentsRegisterInit() {
        get_init_repository().add_init("smoke_ClassWithAllParents", &_CBridgeInitsmoke_ClassWithAllParents);
    }
} s_smoke_ClassWithAllParents_register_init;
}
void* smoke_ClassWithAllParents_get_typed(_baseRef handle) {
    const auto& real_type_id = ::gluecodium::get_type_repository().get_id(get_pointer<::std::shared_ptr< ::smoke::ClassWithAllParents >>(handle)->get());
    auto init_function = get_init_repository().get_init(real_type_id);
    return init_function ? init_function(handle) : _CBridgeInitsmoke_ClassWithAllParents(handle);
}
void smoke_ClassWithAllParents_childFunction(_baseRef _instance) {
    return get_pointer<::std::shared_ptr< ::smoke::ClassWithAllParents >>(_instance)->get()->child_function();
}
_baseRef smoke_ClassWithAllParents_childProperty_get(_baseRef _instance) {
    return Conversion<::std::string>::toBaseRef(get_pointer<::std::shared_ptr< ::smoke::ClassWithAllParents >>(_instance)->get()->get_child_property());
}
void smoke_ClassWithAllParents_childProperty_set(_baseRef _instance, _baseRef value) {
    return get_pointer<::std::shared_ptr< ::smoke::ClassWithAllParents >>(_instance)->get()->set_child_property(Conversion<::std::string>::toCpp(value));
}
