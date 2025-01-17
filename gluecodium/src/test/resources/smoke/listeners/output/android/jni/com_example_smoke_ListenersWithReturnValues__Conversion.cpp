/*
 *
 */
#include "com_example_smoke_ListenersWithReturnValues__Conversion.h"
#include "com_example_smoke_ListenersWithReturnValuesImplCppProxy.h"
#include "CppProxyBase.h"
#include "FieldAccessMethods.h"
#include "JniClassCache.h"
#include "JniWrapperCache.h"
#include <new>
namespace gluecodium
{
namespace jni
{
REGISTER_JNI_CLASS_CACHE_INHERITANCE("com/example/smoke/ListenersWithReturnValuesImpl", com_example_smoke_ListenersWithReturnValues, "smoke_ListenersWithReturnValues", ::smoke::ListenersWithReturnValues)
template<>
void createCppProxy(JNIEnv* env, const JniReference<jobject>& obj, ::std::shared_ptr<::smoke::ListenersWithReturnValues>& result)
{
    CppProxyBase::createProxy<::smoke::ListenersWithReturnValues, com_example_smoke_ListenersWithReturnValues_CppProxy>(env, obj, "com_example_smoke_ListenersWithReturnValues", result);
}
std::shared_ptr<::smoke::ListenersWithReturnValues> convert_from_jni(JNIEnv* _env, const JniReference<jobject>& _jobj, std::shared_ptr<::smoke::ListenersWithReturnValues>*)
{
    std::shared_ptr<::smoke::ListenersWithReturnValues> _nresult{};
    auto& nativeBaseClass = get_cached_native_base_class();
    if (_env->IsInstanceOf(_jobj.get(), nativeBaseClass.get()))
    {
        if (_jobj != nullptr)
        {
            auto long_ptr = get_field_value(
                _env,
                _jobj,
                "nativeHandle",
                (int64_t*)nullptr);
            _nresult = *reinterpret_cast<std::shared_ptr<::smoke::ListenersWithReturnValues>*>(long_ptr);
        }
    }
    else
    {
        createCppProxy(_env, _jobj, _nresult);
    }
    return _nresult;
}
JniReference<jobject>
convert_to_jni(JNIEnv* _jenv, const std::shared_ptr<::smoke::ListenersWithReturnValues>& _ninput)
{
    if ( !_ninput )
    {
        return {};
    }
    auto jResult = ::gluecodium::jni::CppProxyBase::getJavaObject( _ninput.get( ) );
    if (jResult) return jResult;
    jResult = ::gluecodium::jni::JniWrapperCache::get_cached_wrapper(_jenv, _ninput);
    if (jResult) return jResult;
    const auto& id = ::gluecodium::get_type_repository().get_id(_ninput.get());
    const auto& javaClass = CachedJavaClass<::smoke::ListenersWithReturnValues>::get_java_class(id);
    auto pInstanceSharedPointer = new (::std::nothrow) std::shared_ptr<::smoke::ListenersWithReturnValues>(_ninput);
    if ( pInstanceSharedPointer == nullptr )
    {
        auto exceptionClass = find_class(_jenv, "java/lang/OutOfMemoryError" );
        _jenv->ThrowNew( exceptionClass.get(), "Cannot allocate native memory." );
    }
    jResult = ::gluecodium::jni::create_instance_object(
        _jenv, javaClass, reinterpret_cast<jlong>( pInstanceSharedPointer ) );
    ::gluecodium::jni::JniWrapperCache::cache_wrapper(_jenv, _ninput, jResult);
    return jResult;
}
}
}
