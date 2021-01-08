import 'package:library/src/_token_cache.dart' as __lib;
import 'package:library/src/_type_repository.dart' as __lib;
import 'package:library/src/builtin_types__conversion.dart';
import 'package:library/src/smoke/parent_interface_one.dart';
import 'package:library/src/smoke/parent_interface_two.dart';
import 'dart:ffi';
import 'package:ffi/ffi.dart';
import 'package:meta/meta.dart';
import 'package:library/src/_library_context.dart' as __lib;
abstract class InterfaceWithAllParents implements ParentInterfaceOne, ParentInterfaceTwo {
  InterfaceWithAllParents() {}
  factory InterfaceWithAllParents.fromLambdas({
    @required void Function() lambda_parentFunctionOne,
    @required void Function() lambda_parentFunctionTwo,
    @required void Function() lambda_childFunction,
    @required String Function() lambda_parentPropertyOne_get,
    @required void Function(String) lambda_parentPropertyOne_set,
    @required String Function() lambda_parentPropertyTwo_get,
    @required void Function(String) lambda_parentPropertyTwo_set,
    @required String Function() lambda_childProperty_get,
    @required void Function(String) lambda_childProperty_set
  }) => InterfaceWithAllParents$Lambdas(
    lambda_parentFunctionOne,
    lambda_parentFunctionTwo,
    lambda_childFunction,
    lambda_parentPropertyOne_get,
    lambda_parentPropertyOne_set,
    lambda_parentPropertyTwo_get,
    lambda_parentPropertyTwo_set,
    lambda_childProperty_get,
    lambda_childProperty_set
  );
  /// Destroys the underlying native object.
  ///
  /// Call this to free memory when you no longer need this instance.
  /// Note that setting the instance to null will not destroy the underlying native object.
  void release() {}
  childFunction();
  String get childProperty;
  set childProperty(String value);
}
// InterfaceWithAllParents "private" section, not exported.
final _smoke_InterfaceWithAllParents_copy_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_InterfaceWithAllParents_copy_handle'));
final _smoke_InterfaceWithAllParents_release_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_InterfaceWithAllParents_release_handle'));
final _smoke_InterfaceWithAllParents_create_proxy = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Uint64, Int32, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer),
    Pointer<Void> Function(int, int, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer)
  >('library_smoke_InterfaceWithAllParents_create_proxy'));
final _smoke_InterfaceWithAllParents_get_type_id = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_InterfaceWithAllParents_get_type_id'));
class InterfaceWithAllParents$Lambdas implements InterfaceWithAllParents {
  void Function() lambda_parentFunctionOne;
  void Function() lambda_parentFunctionTwo;
  void Function() lambda_childFunction;
  String Function() lambda_parentPropertyOne_get;
  void Function(String) lambda_parentPropertyOne_set;
  String Function() lambda_parentPropertyTwo_get;
  void Function(String) lambda_parentPropertyTwo_set;
  String Function() lambda_childProperty_get;
  void Function(String) lambda_childProperty_set;
  InterfaceWithAllParents$Lambdas(
    this.lambda_parentFunctionOne,
    this.lambda_parentFunctionTwo,
    this.lambda_childFunction,
    this.lambda_parentPropertyOne_get,
    this.lambda_parentPropertyOne_set,
    this.lambda_parentPropertyTwo_get,
    this.lambda_parentPropertyTwo_set,
    this.lambda_childProperty_get,
    this.lambda_childProperty_set
  );
  @override
  void release() {}
  @override
  parentFunctionOne() =>
    lambda_parentFunctionOne();
  @override
  parentFunctionTwo() =>
    lambda_parentFunctionTwo();
  @override
  childFunction() =>
    lambda_childFunction();
  @override
  String get parentPropertyOne => lambda_parentPropertyOne_get();
  @override
  set parentPropertyOne(String value) => lambda_parentPropertyOne_set(value);
  @override
  String get parentPropertyTwo => lambda_parentPropertyTwo_get();
  @override
  set parentPropertyTwo(String value) => lambda_parentPropertyTwo_set(value);
  @override
  String get childProperty => lambda_childProperty_get();
  @override
  set childProperty(String value) => lambda_childProperty_set(value);
}
class InterfaceWithAllParents$Impl implements InterfaceWithAllParents {
  Pointer<Void> handle;
  InterfaceWithAllParents$Impl(this.handle);
  @override
  void release() {
    if (handle == null) return;
    __lib.uncacheObject(this);
    __lib.ffi_uncache_token(handle, __lib.LibraryContext.isolateId);
    _smoke_InterfaceWithAllParents_release_handle(handle);
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
  childFunction() {
    final _childFunction_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Int32), void Function(Pointer<Void>, int)>('library_smoke_InterfaceWithAllParents_childFunction'));
    final _handle = this.handle;
    final __result_handle = _childFunction_ffi(_handle, __lib.LibraryContext.isolateId);
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
  String get childProperty {
    final _get_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Pointer<Void>, Int32), Pointer<Void> Function(Pointer<Void>, int)>('library_smoke_InterfaceWithAllParents_childProperty_get'));
    final _handle = this.handle;
    final __result_handle = _get_ffi(_handle, __lib.LibraryContext.isolateId);
    try {
      return String_fromFfi(__result_handle);
    } finally {
      String_releaseFfiHandle(__result_handle);
    }
  }
  set childProperty(String value) {
    final _set_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Int32, Pointer<Void>), void Function(Pointer<Void>, int, Pointer<Void>)>('library_smoke_InterfaceWithAllParents_childProperty_set__String'));
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
int _InterfaceWithAllParents_parentFunctionOne_static(int _token) {
  try {
    (__lib.instanceCache[_token] as InterfaceWithAllParents).parentFunctionOne();
  } finally {
  }
  return 0;
}
int _InterfaceWithAllParents_parentFunctionTwo_static(int _token) {
  try {
    (__lib.instanceCache[_token] as InterfaceWithAllParents).parentFunctionTwo();
  } finally {
  }
  return 0;
}
int _InterfaceWithAllParents_childFunction_static(int _token) {
  try {
    (__lib.instanceCache[_token] as InterfaceWithAllParents).childFunction();
  } finally {
  }
  return 0;
}
int _InterfaceWithAllParents_parentPropertyOne_get_static(int _token, Pointer<Pointer<Void>> _result) {
  _result.value = String_toFfi((__lib.instanceCache[_token] as InterfaceWithAllParents).parentPropertyOne);
  return 0;
}
int _InterfaceWithAllParents_parentPropertyOne_set_static(int _token, Pointer<Void> _value) {
  try {
    (__lib.instanceCache[_token] as InterfaceWithAllParents).parentPropertyOne =
      String_fromFfi(_value);
  } finally {
    String_releaseFfiHandle(_value);
  }
  return 0;
}
int _InterfaceWithAllParents_parentPropertyTwo_get_static(int _token, Pointer<Pointer<Void>> _result) {
  _result.value = String_toFfi((__lib.instanceCache[_token] as InterfaceWithAllParents).parentPropertyTwo);
  return 0;
}
int _InterfaceWithAllParents_parentPropertyTwo_set_static(int _token, Pointer<Void> _value) {
  try {
    (__lib.instanceCache[_token] as InterfaceWithAllParents).parentPropertyTwo =
      String_fromFfi(_value);
  } finally {
    String_releaseFfiHandle(_value);
  }
  return 0;
}
int _InterfaceWithAllParents_childProperty_get_static(int _token, Pointer<Pointer<Void>> _result) {
  _result.value = String_toFfi((__lib.instanceCache[_token] as InterfaceWithAllParents).childProperty);
  return 0;
}
int _InterfaceWithAllParents_childProperty_set_static(int _token, Pointer<Void> _value) {
  try {
    (__lib.instanceCache[_token] as InterfaceWithAllParents).childProperty =
      String_fromFfi(_value);
  } finally {
    String_releaseFfiHandle(_value);
  }
  return 0;
}
Pointer<Void> smoke_InterfaceWithAllParents_toFfi(InterfaceWithAllParents value) {
  if (value is InterfaceWithAllParents$Impl) return _smoke_InterfaceWithAllParents_copy_handle(value.handle);
  final result = _smoke_InterfaceWithAllParents_create_proxy(
    __lib.cacheObject(value),
    __lib.LibraryContext.isolateId,
    __lib.uncacheObjectFfi,
    Pointer.fromFunction<Uint8 Function(Uint64)>(_InterfaceWithAllParents_parentFunctionOne_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64)>(_InterfaceWithAllParents_parentFunctionTwo_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64)>(_InterfaceWithAllParents_childFunction_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64, Pointer<Pointer<Void>>)>(_InterfaceWithAllParents_parentPropertyOne_get_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64, Pointer<Void>)>(_InterfaceWithAllParents_parentPropertyOne_set_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64, Pointer<Pointer<Void>>)>(_InterfaceWithAllParents_parentPropertyTwo_get_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64, Pointer<Void>)>(_InterfaceWithAllParents_parentPropertyTwo_set_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64, Pointer<Pointer<Void>>)>(_InterfaceWithAllParents_childProperty_get_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64, Pointer<Void>)>(_InterfaceWithAllParents_childProperty_set_static, __lib.unknownError)
  );
  return result;
}
InterfaceWithAllParents smoke_InterfaceWithAllParents_fromFfi(Pointer<Void> handle) {
  final isolateId = __lib.LibraryContext.isolateId;
  final token = __lib.ffi_get_cached_token(handle, isolateId);
  final instance = __lib.instanceCache[token] as InterfaceWithAllParents;
  if (instance != null) return instance;
  final _type_id_handle = _smoke_InterfaceWithAllParents_get_type_id(handle);
  final factoryConstructor = __lib.typeRepository[String_fromFfi(_type_id_handle)];
  String_releaseFfiHandle(_type_id_handle);
  final _copied_handle = _smoke_InterfaceWithAllParents_copy_handle(handle);
  final result = factoryConstructor != null
    ? factoryConstructor(_copied_handle)
    : InterfaceWithAllParents$Impl(_copied_handle);
  __lib.ffi_cache_token(_copied_handle, isolateId, __lib.cacheObject(result));
  return result;
}
void smoke_InterfaceWithAllParents_releaseFfiHandle(Pointer<Void> handle) =>
  _smoke_InterfaceWithAllParents_release_handle(handle);
Pointer<Void> smoke_InterfaceWithAllParents_toFfi_nullable(InterfaceWithAllParents value) =>
  value != null ? smoke_InterfaceWithAllParents_toFfi(value) : Pointer<Void>.fromAddress(0);
InterfaceWithAllParents smoke_InterfaceWithAllParents_fromFfi_nullable(Pointer<Void> handle) =>
  handle.address != 0 ? smoke_InterfaceWithAllParents_fromFfi(handle) : null;
void smoke_InterfaceWithAllParents_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_InterfaceWithAllParents_release_handle(handle);
// End of InterfaceWithAllParents "private" section.
