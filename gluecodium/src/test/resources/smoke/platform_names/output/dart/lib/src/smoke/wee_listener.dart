import 'package:library/src/_token_cache.dart' as __lib;
import 'package:library/src/_type_repository.dart' as __lib;
import 'package:library/src/builtin_types__conversion.dart';
import 'dart:ffi';
import 'package:ffi/ffi.dart';
import 'package:meta/meta.dart';
import 'package:library/src/_library_context.dart' as __lib;
abstract class weeListener {
  weeListener() {}
  factory weeListener.fromLambdas({
    @required void Function(String) lambda_WeeMethod,
  }) => weeListener$Lambdas(
    lambda_WeeMethod,
  );
  /// Destroys the underlying native object.
  ///
  /// Call this to free memory when you no longer need this instance.
  /// Note that setting the instance to null will not destroy the underlying native object.
  void release() {}
  WeeMethod(String WeeParameter);
}
// weeListener "private" section, not exported.
final _smoke_PlatformNamesListener_copy_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_PlatformNamesListener_copy_handle'));
final _smoke_PlatformNamesListener_release_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_PlatformNamesListener_release_handle'));
final _smoke_PlatformNamesListener_create_proxy = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Uint64, Int32, Pointer, Pointer),
    Pointer<Void> Function(int, int, Pointer, Pointer)
  >('library_smoke_PlatformNamesListener_create_proxy'));
final _smoke_PlatformNamesListener_get_type_id = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_PlatformNamesListener_get_type_id'));
class weeListener$Lambdas implements weeListener {
  void Function(String) lambda_WeeMethod;
  weeListener$Lambdas(
    this.lambda_WeeMethod,
  );
  @override
  void release() {}
  @override
  WeeMethod(String WeeParameter) =>
    lambda_WeeMethod(WeeParameter);
}
class weeListener$Impl implements weeListener {
  Pointer<Void> handle;
  weeListener$Impl(this.handle);
  @override
  void release() {
    if (handle == null) return;
    __lib.uncacheObject(this);
    __lib.ffi_uncache_token(handle, __lib.LibraryContext.isolateId);
    _smoke_PlatformNamesListener_release_handle(handle);
    handle = null;
  }
  @override
  WeeMethod(String WeeParameter) {
    final _WeeMethod_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Int32, Pointer<Void>), void Function(Pointer<Void>, int, Pointer<Void>)>('library_smoke_PlatformNamesListener_basicMethod__String'));
    final _WeeParameter_handle = String_toFfi(WeeParameter);
    final _handle = this.handle;
    final __result_handle = _WeeMethod_ffi(_handle, __lib.LibraryContext.isolateId, _WeeParameter_handle);
    String_releaseFfiHandle(_WeeParameter_handle);
    try {
      return (__result_handle);
    } finally {
      (__result_handle);
    }
  }
}
int _weeListener_WeeMethod_static(int _token, Pointer<Void> WeeParameter) {
  try {
    (__lib.instanceCache[_token] as weeListener).WeeMethod(String_fromFfi(WeeParameter));
  } finally {
    String_releaseFfiHandle(WeeParameter);
  }
  return 0;
}
Pointer<Void> smoke_PlatformNamesListener_toFfi(weeListener value) {
  if (value is weeListener$Impl) return _smoke_PlatformNamesListener_copy_handle(value.handle);
  final result = _smoke_PlatformNamesListener_create_proxy(
    __lib.cacheObject(value),
    __lib.LibraryContext.isolateId,
    __lib.uncacheObjectFfi,
    Pointer.fromFunction<Uint8 Function(Uint64, Pointer<Void>)>(_weeListener_WeeMethod_static, __lib.unknownError)
  );
  return result;
}
weeListener smoke_PlatformNamesListener_fromFfi(Pointer<Void> handle) {
  final isolateId = __lib.LibraryContext.isolateId;
  final token = __lib.ffi_get_cached_token(handle, isolateId);
  final instance = __lib.instanceCache[token] as weeListener;
  if (instance != null) return instance;
  final _type_id_handle = _smoke_PlatformNamesListener_get_type_id(handle);
  final factoryConstructor = __lib.typeRepository[String_fromFfi(_type_id_handle)];
  String_releaseFfiHandle(_type_id_handle);
  final _copied_handle = _smoke_PlatformNamesListener_copy_handle(handle);
  final result = factoryConstructor != null
    ? factoryConstructor(_copied_handle)
    : weeListener$Impl(_copied_handle);
  __lib.ffi_cache_token(_copied_handle, isolateId, __lib.cacheObject(result));
  return result;
}
void smoke_PlatformNamesListener_releaseFfiHandle(Pointer<Void> handle) =>
  _smoke_PlatformNamesListener_release_handle(handle);
Pointer<Void> smoke_PlatformNamesListener_toFfi_nullable(weeListener value) =>
  value != null ? smoke_PlatformNamesListener_toFfi(value) : Pointer<Void>.fromAddress(0);
weeListener smoke_PlatformNamesListener_fromFfi_nullable(Pointer<Void> handle) =>
  handle.address != 0 ? smoke_PlatformNamesListener_fromFfi(handle) : null;
void smoke_PlatformNamesListener_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_PlatformNamesListener_release_handle(handle);
// End of weeListener "private" section.
