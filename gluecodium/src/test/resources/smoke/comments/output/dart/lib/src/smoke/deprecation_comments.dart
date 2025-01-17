import 'package:library/src/_token_cache.dart' as __lib;
import 'package:library/src/_type_repository.dart' as __lib;
import 'package:library/src/builtin_types__conversion.dart';
import 'dart:ffi';
import 'package:ffi/ffi.dart';
import 'package:meta/meta.dart';
import 'package:library/src/_library_context.dart' as __lib;
/// This is some very useful interface.
@Deprecated("Unfortunately, this interface is deprecated. Use [Comments] instead.")
abstract class DeprecationComments {
  DeprecationComments() {}
  factory DeprecationComments.fromLambdas({
    @required bool Function(String) lambda_someMethodWithAllComments,
    @required bool Function() lambda_isSomeProperty_get,
    @required void Function(bool) lambda_isSomeProperty_set,
    @required String Function() lambda_propertyButNotAccessors_get,
    @required void Function(String) lambda_propertyButNotAccessors_set
  }) => DeprecationComments$Lambdas(
    lambda_someMethodWithAllComments,
    lambda_isSomeProperty_get,
    lambda_isSomeProperty_set,
    lambda_propertyButNotAccessors_get,
    lambda_propertyButNotAccessors_set
  );
  /// Destroys the underlying native object.
  ///
  /// Call this to free memory when you no longer need this instance.
  /// Note that setting the instance to null will not destroy the underlying native object.
  void release() {}
  /// This is some very useful constant.
  @Deprecated("Unfortunately, this constant is deprecated. Use [Comments.veryUseful] instead.")
  static final bool veryUseful = true;
  /// This is some very useful method that measures the usefulness of its input.
  ///
  /// [input] Very useful input parameter
  ///
  /// Returns [bool]. Usefulness of the input
  ///
  @Deprecated("Unfortunately, this method is deprecated.\nUse [Comments.someMethodWithAllComments] instead.")
  bool someMethodWithAllComments(String input);
  /// Gets some very useful property.
  @Deprecated("Unfortunately, this property's getter is deprecated.\nUse [Comments.isSomeProperty] instead.")
  bool get isSomeProperty;
  /// Sets some very useful property.
  @Deprecated("Unfortunately, this property's setter is deprecated.\nUse [Comments.isSomeProperty] instead.")
  set isSomeProperty(bool value);
  /// Gets the property but not accessors.
  @Deprecated("Will be removed in v3.2.1.")
  String get propertyButNotAccessors;
  @Deprecated("Will be removed in v3.2.1.")
  set propertyButNotAccessors(String value);
}
/// This is some very useful enum.
@Deprecated("Unfortunately, this enum is deprecated. Use [Comments_SomeEnum] instead.")
enum DeprecationComments_SomeEnum {
    /// Not quite useful
    @Deprecated("Unfortunately, this item is deprecated.\nUse [Comments_SomeEnum.useless] instead.")
    useless
}
// DeprecationComments_SomeEnum "private" section, not exported.
int smoke_DeprecationComments_SomeEnum_toFfi(DeprecationComments_SomeEnum value) {
  switch (value) {
  case DeprecationComments_SomeEnum.useless:
    return 0;
  break;
  default:
    throw StateError("Invalid enum value $value for DeprecationComments_SomeEnum enum.");
  }
}
DeprecationComments_SomeEnum smoke_DeprecationComments_SomeEnum_fromFfi(int handle) {
  switch (handle) {
  case 0:
    return DeprecationComments_SomeEnum.useless;
  break;
  default:
    throw StateError("Invalid numeric value $handle for DeprecationComments_SomeEnum enum.");
  }
}
void smoke_DeprecationComments_SomeEnum_releaseFfiHandle(int handle) {}
final _smoke_DeprecationComments_SomeEnum_create_handle_nullable = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Uint32),
    Pointer<Void> Function(int)
  >('library_smoke_DeprecationComments_SomeEnum_create_handle_nullable'));
final _smoke_DeprecationComments_SomeEnum_release_handle_nullable = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_DeprecationComments_SomeEnum_release_handle_nullable'));
final _smoke_DeprecationComments_SomeEnum_get_value_nullable = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Uint32 Function(Pointer<Void>),
    int Function(Pointer<Void>)
  >('library_smoke_DeprecationComments_SomeEnum_get_value_nullable'));
Pointer<Void> smoke_DeprecationComments_SomeEnum_toFfi_nullable(DeprecationComments_SomeEnum value) {
  if (value == null) return Pointer<Void>.fromAddress(0);
  final _handle = smoke_DeprecationComments_SomeEnum_toFfi(value);
  final result = _smoke_DeprecationComments_SomeEnum_create_handle_nullable(_handle);
  smoke_DeprecationComments_SomeEnum_releaseFfiHandle(_handle);
  return result;
}
DeprecationComments_SomeEnum smoke_DeprecationComments_SomeEnum_fromFfi_nullable(Pointer<Void> handle) {
  if (handle.address == 0) return null;
  final _handle = _smoke_DeprecationComments_SomeEnum_get_value_nullable(handle);
  final result = smoke_DeprecationComments_SomeEnum_fromFfi(_handle);
  smoke_DeprecationComments_SomeEnum_releaseFfiHandle(_handle);
  return result;
}
void smoke_DeprecationComments_SomeEnum_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_DeprecationComments_SomeEnum_release_handle_nullable(handle);
// End of DeprecationComments_SomeEnum "private" section.
@Deprecated("Unfortunately, this exception is deprecated, please use [Comments_SomethingWrongException] instead.")
class DeprecationComments_SomethingWrongException implements Exception {
  final DeprecationComments_SomeEnum error;
  DeprecationComments_SomethingWrongException(this.error);
}
/// This is some very useful struct.
@Deprecated("Unfortunately, this struct is deprecated. Use [Comments_SomeStruct] instead.")
class DeprecationComments_SomeStruct {
  /// How useful this struct is.
  @Deprecated("Unfortunately, this field is deprecated.\nUse [Comments_SomeStruct.someField] instead.")
  bool someField;
  DeprecationComments_SomeStruct(this.someField);
}
// DeprecationComments_SomeStruct "private" section, not exported.
final _smoke_DeprecationComments_SomeStruct_create_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Uint8),
    Pointer<Void> Function(int)
  >('library_smoke_DeprecationComments_SomeStruct_create_handle'));
final _smoke_DeprecationComments_SomeStruct_release_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_DeprecationComments_SomeStruct_release_handle'));
final _smoke_DeprecationComments_SomeStruct_get_field_someField = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Uint8 Function(Pointer<Void>),
    int Function(Pointer<Void>)
  >('library_smoke_DeprecationComments_SomeStruct_get_field_someField'));
Pointer<Void> smoke_DeprecationComments_SomeStruct_toFfi(DeprecationComments_SomeStruct value) {
  final _someField_handle = Boolean_toFfi(value.someField);
  final _result = _smoke_DeprecationComments_SomeStruct_create_handle(_someField_handle);
  Boolean_releaseFfiHandle(_someField_handle);
  return _result;
}
DeprecationComments_SomeStruct smoke_DeprecationComments_SomeStruct_fromFfi(Pointer<Void> handle) {
  final _someField_handle = _smoke_DeprecationComments_SomeStruct_get_field_someField(handle);
  try {
    return DeprecationComments_SomeStruct(
      Boolean_fromFfi(_someField_handle)
    );
  } finally {
    Boolean_releaseFfiHandle(_someField_handle);
  }
}
void smoke_DeprecationComments_SomeStruct_releaseFfiHandle(Pointer<Void> handle) => _smoke_DeprecationComments_SomeStruct_release_handle(handle);
// Nullable DeprecationComments_SomeStruct
final _smoke_DeprecationComments_SomeStruct_create_handle_nullable = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_DeprecationComments_SomeStruct_create_handle_nullable'));
final _smoke_DeprecationComments_SomeStruct_release_handle_nullable = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_DeprecationComments_SomeStruct_release_handle_nullable'));
final _smoke_DeprecationComments_SomeStruct_get_value_nullable = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_DeprecationComments_SomeStruct_get_value_nullable'));
Pointer<Void> smoke_DeprecationComments_SomeStruct_toFfi_nullable(DeprecationComments_SomeStruct value) {
  if (value == null) return Pointer<Void>.fromAddress(0);
  final _handle = smoke_DeprecationComments_SomeStruct_toFfi(value);
  final result = _smoke_DeprecationComments_SomeStruct_create_handle_nullable(_handle);
  smoke_DeprecationComments_SomeStruct_releaseFfiHandle(_handle);
  return result;
}
DeprecationComments_SomeStruct smoke_DeprecationComments_SomeStruct_fromFfi_nullable(Pointer<Void> handle) {
  if (handle.address == 0) return null;
  final _handle = _smoke_DeprecationComments_SomeStruct_get_value_nullable(handle);
  final result = smoke_DeprecationComments_SomeStruct_fromFfi(_handle);
  smoke_DeprecationComments_SomeStruct_releaseFfiHandle(_handle);
  return result;
}
void smoke_DeprecationComments_SomeStruct_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_DeprecationComments_SomeStruct_release_handle_nullable(handle);
// End of DeprecationComments_SomeStruct "private" section.
// DeprecationComments "private" section, not exported.
final _smoke_DeprecationComments_copy_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_DeprecationComments_copy_handle'));
final _smoke_DeprecationComments_release_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_DeprecationComments_release_handle'));
final _smoke_DeprecationComments_create_proxy = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Uint64, Int32, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer),
    Pointer<Void> Function(int, int, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer)
  >('library_smoke_DeprecationComments_create_proxy'));
final _smoke_DeprecationComments_get_type_id = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_DeprecationComments_get_type_id'));
class DeprecationComments$Lambdas implements DeprecationComments {
  bool Function(String) lambda_someMethodWithAllComments;
  bool Function() lambda_isSomeProperty_get;
  void Function(bool) lambda_isSomeProperty_set;
  String Function() lambda_propertyButNotAccessors_get;
  void Function(String) lambda_propertyButNotAccessors_set;
  DeprecationComments$Lambdas(
    this.lambda_someMethodWithAllComments,
    this.lambda_isSomeProperty_get,
    this.lambda_isSomeProperty_set,
    this.lambda_propertyButNotAccessors_get,
    this.lambda_propertyButNotAccessors_set
  );
  @override
  void release() {}
  @override
  bool someMethodWithAllComments(String input) =>
    lambda_someMethodWithAllComments(input);
  @override
  bool get isSomeProperty => lambda_isSomeProperty_get();
  @override
  set isSomeProperty(bool value) => lambda_isSomeProperty_set(value);
  @override
  String get propertyButNotAccessors => lambda_propertyButNotAccessors_get();
  @override
  set propertyButNotAccessors(String value) => lambda_propertyButNotAccessors_set(value);
}
class DeprecationComments$Impl implements DeprecationComments {
  Pointer<Void> handle;
  DeprecationComments$Impl(this.handle);
  @override
  void release() {
    if (handle == null) return;
    __lib.uncacheObject(this);
    __lib.ffi_uncache_token(handle, __lib.LibraryContext.isolateId);
    _smoke_DeprecationComments_release_handle(handle);
    handle = null;
  }
  @override
  bool someMethodWithAllComments(String input) {
    final _someMethodWithAllComments_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Uint8 Function(Pointer<Void>, Int32, Pointer<Void>), int Function(Pointer<Void>, int, Pointer<Void>)>('library_smoke_DeprecationComments_someMethodWithAllComments__String'));
    final _input_handle = String_toFfi(input);
    final _handle = this.handle;
    final __result_handle = _someMethodWithAllComments_ffi(_handle, __lib.LibraryContext.isolateId, _input_handle);
    String_releaseFfiHandle(_input_handle);
    try {
      return Boolean_fromFfi(__result_handle);
    } finally {
      Boolean_releaseFfiHandle(__result_handle);
    }
  }
  /// Gets some very useful property.
  @Deprecated("Unfortunately, this property's getter is deprecated.\nUse [Comments.isSomeProperty] instead.")
  bool get isSomeProperty {
    final _get_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Uint8 Function(Pointer<Void>, Int32), int Function(Pointer<Void>, int)>('library_smoke_DeprecationComments_isSomeProperty_get'));
    final _handle = this.handle;
    final __result_handle = _get_ffi(_handle, __lib.LibraryContext.isolateId);
    try {
      return Boolean_fromFfi(__result_handle);
    } finally {
      Boolean_releaseFfiHandle(__result_handle);
    }
  }
  /// Sets some very useful property.
  @Deprecated("Unfortunately, this property's setter is deprecated.\nUse [Comments.isSomeProperty] instead.")
  set isSomeProperty(bool value) {
    final _set_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Int32, Uint8), void Function(Pointer<Void>, int, int)>('library_smoke_DeprecationComments_isSomeProperty_set__Boolean'));
    final _value_handle = Boolean_toFfi(value);
    final _handle = this.handle;
    final __result_handle = _set_ffi(_handle, __lib.LibraryContext.isolateId, _value_handle);
    Boolean_releaseFfiHandle(_value_handle);
    try {
      return (__result_handle);
    } finally {
      (__result_handle);
    }
  }
  /// Gets the property but not accessors.
  @Deprecated("Will be removed in v3.2.1.")
  String get propertyButNotAccessors {
    final _get_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Pointer<Void>, Int32), Pointer<Void> Function(Pointer<Void>, int)>('library_smoke_DeprecationComments_propertyButNotAccessors_get'));
    final _handle = this.handle;
    final __result_handle = _get_ffi(_handle, __lib.LibraryContext.isolateId);
    try {
      return String_fromFfi(__result_handle);
    } finally {
      String_releaseFfiHandle(__result_handle);
    }
  }
  @Deprecated("Will be removed in v3.2.1.")
  set propertyButNotAccessors(String value) {
    final _set_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Int32, Pointer<Void>), void Function(Pointer<Void>, int, Pointer<Void>)>('library_smoke_DeprecationComments_propertyButNotAccessors_set__String'));
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
int _DeprecationComments_someMethodWithAllComments_static(int _token, Pointer<Void> input, Pointer<Uint8> _result) {
  bool _result_object = null;
  try {
    _result_object = (__lib.instanceCache[_token] as DeprecationComments).someMethodWithAllComments(String_fromFfi(input));
    _result.value = Boolean_toFfi(_result_object);
  } finally {
    String_releaseFfiHandle(input);
  }
  return 0;
}
int _DeprecationComments_isSomeProperty_get_static(int _token, Pointer<Uint8> _result) {
  _result.value = Boolean_toFfi((__lib.instanceCache[_token] as DeprecationComments).isSomeProperty);
  return 0;
}
int _DeprecationComments_isSomeProperty_set_static(int _token, int _value) {
  try {
    (__lib.instanceCache[_token] as DeprecationComments).isSomeProperty =
      Boolean_fromFfi(_value);
  } finally {
    Boolean_releaseFfiHandle(_value);
  }
  return 0;
}
int _DeprecationComments_propertyButNotAccessors_get_static(int _token, Pointer<Pointer<Void>> _result) {
  _result.value = String_toFfi((__lib.instanceCache[_token] as DeprecationComments).propertyButNotAccessors);
  return 0;
}
int _DeprecationComments_propertyButNotAccessors_set_static(int _token, Pointer<Void> _value) {
  try {
    (__lib.instanceCache[_token] as DeprecationComments).propertyButNotAccessors =
      String_fromFfi(_value);
  } finally {
    String_releaseFfiHandle(_value);
  }
  return 0;
}
Pointer<Void> smoke_DeprecationComments_toFfi(DeprecationComments value) {
  if (value is DeprecationComments$Impl) return _smoke_DeprecationComments_copy_handle(value.handle);
  final result = _smoke_DeprecationComments_create_proxy(
    __lib.cacheObject(value),
    __lib.LibraryContext.isolateId,
    __lib.uncacheObjectFfi,
    Pointer.fromFunction<Uint8 Function(Uint64, Pointer<Void>, Pointer<Uint8>)>(_DeprecationComments_someMethodWithAllComments_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64, Pointer<Uint8>)>(_DeprecationComments_isSomeProperty_get_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64, Uint8)>(_DeprecationComments_isSomeProperty_set_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64, Pointer<Pointer<Void>>)>(_DeprecationComments_propertyButNotAccessors_get_static, __lib.unknownError),
    Pointer.fromFunction<Uint8 Function(Uint64, Pointer<Void>)>(_DeprecationComments_propertyButNotAccessors_set_static, __lib.unknownError)
  );
  return result;
}
DeprecationComments smoke_DeprecationComments_fromFfi(Pointer<Void> handle) {
  final isolateId = __lib.LibraryContext.isolateId;
  final token = __lib.ffi_get_cached_token(handle, isolateId);
  final instance = __lib.instanceCache[token] as DeprecationComments;
  if (instance != null) return instance;
  final _type_id_handle = _smoke_DeprecationComments_get_type_id(handle);
  final factoryConstructor = __lib.typeRepository[String_fromFfi(_type_id_handle)];
  String_releaseFfiHandle(_type_id_handle);
  final _copied_handle = _smoke_DeprecationComments_copy_handle(handle);
  final result = factoryConstructor != null
    ? factoryConstructor(_copied_handle)
    : DeprecationComments$Impl(_copied_handle);
  __lib.ffi_cache_token(_copied_handle, isolateId, __lib.cacheObject(result));
  return result;
}
void smoke_DeprecationComments_releaseFfiHandle(Pointer<Void> handle) =>
  _smoke_DeprecationComments_release_handle(handle);
Pointer<Void> smoke_DeprecationComments_toFfi_nullable(DeprecationComments value) =>
  value != null ? smoke_DeprecationComments_toFfi(value) : Pointer<Void>.fromAddress(0);
DeprecationComments smoke_DeprecationComments_fromFfi_nullable(Pointer<Void> handle) =>
  handle.address != 0 ? smoke_DeprecationComments_fromFfi(handle) : null;
void smoke_DeprecationComments_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_DeprecationComments_release_handle(handle);
// End of DeprecationComments "private" section.
