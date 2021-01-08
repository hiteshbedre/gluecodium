import 'package:library/src/_token_cache.dart' as __lib;
import 'package:library/src/_type_repository.dart' as __lib;
import 'package:library/src/builtin_types__conversion.dart';
import 'package:library/src/smoke/facet_one.dart';
import 'package:library/src/smoke/facet_two.dart';
import 'dart:ffi';
import 'package:ffi/ffi.dart';
import 'package:meta/meta.dart';
import 'package:library/src/_library_context.dart' as __lib;
abstract class TheDiamond implements FacetOne, FacetTwo {
  TheDiamond() {}
  factory TheDiamond.fromLambdas({
    @required void Function() lambda_parentFunctionOne,
    @required String Function() lambda_parentPropertyOne_get,
    @required void Function(String) lambda_parentPropertyOne_set
  }) => TheDiamond$Lambdas(
    lambda_parentFunctionOne,
    lambda_parentPropertyOne_get,
    lambda_parentPropertyOne_set
  );
  /// Destroys the underlying native object.
  ///
  /// Call this to free memory when you no longer need this instance.
  /// Note that setting the instance to null will not destroy the underlying native object.
  void release() {}
}
// TheDiamond "private" section, not exported.
final _smoke_TheDiamond_copy_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_TheDiamond_copy_handle'));
final _smoke_TheDiamond_release_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_TheDiamond_release_handle'));
final _smoke_TheDiamond_create_proxy = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Uint64, Int32, Pointer, Pointer, Pointer, Pointer),
    Pointer<Void> Function(int, int, Pointer, Pointer, Pointer, Pointer)
  >('library_smoke_TheDiamond_create_proxy'));
final _smoke_TheDiamond_get_type_id = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_TheDiamond_get_type_id'));
class TheDiamond$Lambdas implements TheDiamond {
  void Function() lambda_parentFunctionOne;
  String Function() lambda_parentPropertyOne_get;
  void Function(String) lambda_parentPropertyOne_set;
  TheDiamond$Lambdas(
    this.lambda_parentFunctionOne,
    this.lambda_parentPropertyOne_get,
    this.lambda_parentPropertyOne_set
  );
  @override
  void release() {}
  @override
  parentFunctionOne() =>
    lambda_parentFunctionOne();
  @override
  String get parentPropertyOne => lambda_parentPropertyOne_get();
  @override
  set parentPropertyOne(String value) => lambda_parentPropertyOne_set(value);
}
class TheDiamond$Impl implements TheDiamond {
  Pointer<Void> handle;
  TheDiamond$Impl(this.handle);
  @override
  void release() {
    if (handle == null) return;
    __lib.uncacheObject(this);
    __lib.ffi_uncache_token(handle, __lib.LibraryContext.isolateId);
    _smoke_TheDiamond_release_handle(handle);
    handle = null;
  }
  @override
  parentFunctionOne() {
    final _parentFunctionOne_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Int32), void Function(Pointer<Void>, int)>('library_smoke_ParentInterfaceOne_parentFunctionOne'));
    final _handle = this.handle;
    final __result_handle = _parentFunctionOne_ffi(_handle, __lib.LibraryContext.isolateId);
    try {
      return (__result_handle);
    } finally {
      (__result_handle);
    }
  }
  String get parentPropertyOne {
    final _get_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Pointer<Void>, Int32), Pointer<Void> Function(Pointer<Void>, int)>('library_smoke_ParentInterfaceOne_parentPropertyOne_get'));
    final _handle = this.handle;
    final __result_handle = _get_ffi(_handle, __lib.LibraryContext.isolateId);
    try {
      return String_fromFfi(__result_handle);
    } finally {
      String_releaseFfiHandle(__result_handle);
    }
  }
  set parentPropertyOne(String value) {
    final _set_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Int32, Pointer<Void>), void Function(Pointer<Void>, int, Pointer<Void>)>('library_smoke_ParentInterfaceOne_parentPropertyOne_set__String'));
    final _value_handle = String_toFfi(value);
    final _handle = this.handle;
    final __result_handle = _set_ffi(_handle, __lib.LibraryContext.isolateId, _value_handle);
    String_releaseFfiHandle(_value_handle);
    try {
      return (__result_handle);
    } finally {
      (__result_handle);
    }
  }
}
int _TheDiamond_parentFunctionOne_static(int _token) {
  try {
    (__lib.instanceCache[_token] as TheDiamond).parentFunctionOne();
  } finally {
  }
  return 0;
}
int _TheDiamond_parentPropertyOne_get_static(int _token, Pointer<Pointer<Void>> _result) {
  _result.value = String_toFfi((__lib.instanceCache[_token] as TheDiamond).parentPropertyOne);
  return 0;
}
int _TheDiamond_parentPropertyOne_set_static(int _token, Pointer<Void> _value) {
  try {
    (__lib.instanceCache[_token] as TheDiamond).parentPropertyOne =
      String_fromFfi(_value);
  } finally {
    String_releaseFfiHandle(_value);
  }
  return 0;
}
Pointer<Void> smoke_TheDiamond_toFfi(TheDiamond value) {
  if (value is TheDiamond$Impl) return _smoke_TheDiamond_copy_handle(value.handle);
  final result = _smoke_TheDiamond_create_proxy(
    __lib.cacheObject(value),
    __lib.LibraryContext.isolateId,
    __lib.uncacheObjectFfi,
    Pointer.fromFunction<Uint8 Function(Uint64)>(_TheDiamond_parentFunctionOne_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64, Pointer<Pointer<Void>>)>(_TheDiamond_parentPropertyOne_get_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64, Pointer<Void>)>(_TheDiamond_parentPropertyOne_set_static, __lib.unknownError)
  );
  return result;
}
TheDiamond smoke_TheDiamond_fromFfi(Pointer<Void> handle) {
  final isolateId = __lib.LibraryContext.isolateId;
  final token = __lib.ffi_get_cached_token(handle, isolateId);
  final instance = __lib.instanceCache[token] as TheDiamond;
  if (instance != null) return instance;
  final _type_id_handle = _smoke_TheDiamond_get_type_id(handle);
  final factoryConstructor = __lib.typeRepository[String_fromFfi(_type_id_handle)];
  String_releaseFfiHandle(_type_id_handle);
  final _copied_handle = _smoke_TheDiamond_copy_handle(handle);
  final result = factoryConstructor != null
    ? factoryConstructor(_copied_handle)
    : TheDiamond$Impl(_copied_handle);
  __lib.ffi_cache_token(_copied_handle, isolateId, __lib.cacheObject(result));
  return result;
}
void smoke_TheDiamond_releaseFfiHandle(Pointer<Void> handle) =>
  _smoke_TheDiamond_release_handle(handle);
Pointer<Void> smoke_TheDiamond_toFfi_nullable(TheDiamond value) =>
  value != null ? smoke_TheDiamond_toFfi(value) : Pointer<Void>.fromAddress(0);
TheDiamond smoke_TheDiamond_fromFfi_nullable(Pointer<Void> handle) =>
  handle.address != 0 ? smoke_TheDiamond_fromFfi(handle) : null;
void smoke_TheDiamond_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_TheDiamond_release_handle(handle);
// End of TheDiamond "private" section.
