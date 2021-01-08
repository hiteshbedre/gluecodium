import 'package:library/src/_token_cache.dart' as __lib;
import 'package:library/src/_type_repository.dart' as __lib;
import 'package:library/src/builtin_types__conversion.dart';
import 'package:library/src/smoke/parent_class.dart';
import 'package:library/src/smoke/parent_interface_one.dart';
import 'dart:ffi';
import 'package:ffi/ffi.dart';
import 'package:meta/meta.dart';
import 'package:library/src/_library_context.dart' as __lib;
abstract class ClassWithMixedParents implements ParentClass, ParentInterfaceOne {
  /// Destroys the underlying native object.
  ///
  /// Call this to free memory when you no longer need this instance.
  /// Note that setting the instance to null will not destroy the underlying native object.
  void release();
  childFunction();
  String get childProperty;
  set childProperty(String value);
}
// ClassWithMixedParents "private" section, not exported.
final _smoke_ClassWithMixedParents_copy_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_ClassWithMixedParents_copy_handle'));
final _smoke_ClassWithMixedParents_release_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_ClassWithMixedParents_release_handle'));
final _smoke_ClassWithMixedParents_get_type_id = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_ClassWithMixedParents_get_type_id'));
class ClassWithMixedParents$Impl extends ParentClass$Impl implements ClassWithMixedParents {
  ClassWithMixedParents$Impl(Pointer<Void> handle) : super(handle);
  @override
  void release() {
    if (handle == null) return;
    __lib.uncacheObject(this);
    __lib.ffi_uncache_token(handle, __lib.LibraryContext.isolateId);
    _smoke_ClassWithMixedParents_release_handle(handle);
    handle = null;
  }
  @override
  childFunction() {
    final _childFunction_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Int32), void Function(Pointer<Void>, int)>('library_smoke_ClassWithMixedParents_childFunction'));
    final _handle = this.handle;
    final __result_handle = _childFunction_ffi(_handle, __lib.LibraryContext.isolateId);
    try {
      return (__result_handle);
    } finally {
      (__result_handle);
    }
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
  @override
  String get childProperty {
    final _get_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Pointer<Void>, Int32), Pointer<Void> Function(Pointer<Void>, int)>('library_smoke_ClassWithMixedParents_childProperty_get'));
    final _handle = this.handle;
    final __result_handle = _get_ffi(_handle, __lib.LibraryContext.isolateId);
    try {
      return String_fromFfi(__result_handle);
    } finally {
      String_releaseFfiHandle(__result_handle);
    }
  }
  @override
  set childProperty(String value) {
    final _set_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Int32, Pointer<Void>), void Function(Pointer<Void>, int, Pointer<Void>)>('library_smoke_ClassWithMixedParents_childProperty_set__String'));
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
  @override
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
  @override
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
Pointer<Void> smoke_ClassWithMixedParents_toFfi(ClassWithMixedParents value) =>
  _smoke_ClassWithMixedParents_copy_handle((value as ClassWithMixedParents$Impl).handle);
ClassWithMixedParents smoke_ClassWithMixedParents_fromFfi(Pointer<Void> handle) {
  final isolateId = __lib.LibraryContext.isolateId;
  final token = __lib.ffi_get_cached_token(handle, isolateId);
  final instance = __lib.instanceCache[token] as ClassWithMixedParents;
  if (instance != null) return instance;
  final _type_id_handle = _smoke_ClassWithMixedParents_get_type_id(handle);
  final factoryConstructor = __lib.typeRepository[String_fromFfi(_type_id_handle)];
  String_releaseFfiHandle(_type_id_handle);
  final _copied_handle = _smoke_ClassWithMixedParents_copy_handle(handle);
  final result = factoryConstructor != null
    ? factoryConstructor(_copied_handle)
    : ClassWithMixedParents$Impl(_copied_handle);
  __lib.ffi_cache_token(_copied_handle, isolateId, __lib.cacheObject(result));
  return result;
}
void smoke_ClassWithMixedParents_releaseFfiHandle(Pointer<Void> handle) =>
  _smoke_ClassWithMixedParents_release_handle(handle);
Pointer<Void> smoke_ClassWithMixedParents_toFfi_nullable(ClassWithMixedParents value) =>
  value != null ? smoke_ClassWithMixedParents_toFfi(value) : Pointer<Void>.fromAddress(0);
ClassWithMixedParents smoke_ClassWithMixedParents_fromFfi_nullable(Pointer<Void> handle) =>
  handle.address != 0 ? smoke_ClassWithMixedParents_fromFfi(handle) : null;
void smoke_ClassWithMixedParents_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_ClassWithMixedParents_release_handle(handle);
// End of ClassWithMixedParents "private" section.
