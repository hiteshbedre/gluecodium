import 'package:library/src/_token_cache.dart' as __lib;
import 'package:library/src/_type_repository.dart' as __lib;
import 'package:library/src/builtin_types__conversion.dart';
import 'package:library/src/smoke/parent_interface_one.dart';
import 'package:library/src/smoke/parent_interface_two.dart';
import 'dart:ffi';
import 'package:ffi/ffi.dart';
import 'package:meta/meta.dart';
import 'package:library/src/_library_context.dart' as __lib;
abstract class ClassWithInterfaceParents implements ParentInterfaceOne, ParentInterfaceTwo {
  /// Destroys the underlying native object.
  ///
  /// Call this to free memory when you no longer need this instance.
  /// Note that setting the instance to null will not destroy the underlying native object.
  void release();
  childFunction();
  String get childProperty;
  set childProperty(String value);
}
// ClassWithInterfaceParents "private" section, not exported.
final _smoke_ClassWithInterfaceParents_copy_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_ClassWithInterfaceParents_copy_handle'));
final _smoke_ClassWithInterfaceParents_release_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_ClassWithInterfaceParents_release_handle'));
final _smoke_ClassWithInterfaceParents_get_type_id = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_ClassWithInterfaceParents_get_type_id'));
class ClassWithInterfaceParents$Impl implements ClassWithInterfaceParents {
  @protected
  Pointer<Void> handle;
  ClassWithInterfaceParents$Impl(this.handle);
  @override
  void release() {
    if (handle == null) return;
    __lib.uncacheObject(this);
    __lib.ffi_uncache_token(handle, __lib.LibraryContext.isolateId);
    _smoke_ClassWithInterfaceParents_release_handle(handle);
    handle = null;
  }
  @override
  childFunction() {
    final _childFunction_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Int32), void Function(Pointer<Void>, int)>('library_smoke_ClassWithInterfaceParents_childFunction'));
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
  parentFunctionTwo() {
    final _parentFunctionTwo_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Int32), void Function(Pointer<Void>, int)>('library_smoke_ParentInterfaceTwo_parentFunctionTwo'));
    final _handle = this.handle;
    final __result_handle = _parentFunctionTwo_ffi(_handle, __lib.LibraryContext.isolateId);
    try {
      return (__result_handle);
    } finally {
      (__result_handle);
    }
  }
  @override
  String get childProperty {
    final _get_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Pointer<Void>, Int32), Pointer<Void> Function(Pointer<Void>, int)>('library_smoke_ClassWithInterfaceParents_childProperty_get'));
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
    final _set_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Int32, Pointer<Void>), void Function(Pointer<Void>, int, Pointer<Void>)>('library_smoke_ClassWithInterfaceParents_childProperty_set__String'));
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
  @override
  String get parentPropertyTwo {
    final _get_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Pointer<Void>, Int32), Pointer<Void> Function(Pointer<Void>, int)>('library_smoke_ParentInterfaceTwo_parentPropertyTwo_get'));
    final _handle = this.handle;
    final __result_handle = _get_ffi(_handle, __lib.LibraryContext.isolateId);
    try {
      return String_fromFfi(__result_handle);
    } finally {
      String_releaseFfiHandle(__result_handle);
    }
  }
  @override
  set parentPropertyTwo(String value) {
    final _set_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Int32, Pointer<Void>), void Function(Pointer<Void>, int, Pointer<Void>)>('library_smoke_ParentInterfaceTwo_parentPropertyTwo_set__String'));
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
Pointer<Void> smoke_ClassWithInterfaceParents_toFfi(ClassWithInterfaceParents value) =>
  _smoke_ClassWithInterfaceParents_copy_handle((value as ClassWithInterfaceParents$Impl).handle);
ClassWithInterfaceParents smoke_ClassWithInterfaceParents_fromFfi(Pointer<Void> handle) {
  final isolateId = __lib.LibraryContext.isolateId;
  final token = __lib.ffi_get_cached_token(handle, isolateId);
  final instance = __lib.instanceCache[token] as ClassWithInterfaceParents;
  if (instance != null) return instance;
  final _type_id_handle = _smoke_ClassWithInterfaceParents_get_type_id(handle);
  final factoryConstructor = __lib.typeRepository[String_fromFfi(_type_id_handle)];
  String_releaseFfiHandle(_type_id_handle);
  final _copied_handle = _smoke_ClassWithInterfaceParents_copy_handle(handle);
  final result = factoryConstructor != null
    ? factoryConstructor(_copied_handle)
    : ClassWithInterfaceParents$Impl(_copied_handle);
  __lib.ffi_cache_token(_copied_handle, isolateId, __lib.cacheObject(result));
  return result;
}
void smoke_ClassWithInterfaceParents_releaseFfiHandle(Pointer<Void> handle) =>
  _smoke_ClassWithInterfaceParents_release_handle(handle);
Pointer<Void> smoke_ClassWithInterfaceParents_toFfi_nullable(ClassWithInterfaceParents value) =>
  value != null ? smoke_ClassWithInterfaceParents_toFfi(value) : Pointer<Void>.fromAddress(0);
ClassWithInterfaceParents smoke_ClassWithInterfaceParents_fromFfi_nullable(Pointer<Void> handle) =>
  handle.address != 0 ? smoke_ClassWithInterfaceParents_fromFfi(handle) : null;
void smoke_ClassWithInterfaceParents_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_ClassWithInterfaceParents_release_handle(handle);
// End of ClassWithInterfaceParents "private" section.
