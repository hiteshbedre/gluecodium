import 'dart:typed_data';
import 'package:library/src/BuiltInTypes__conversion.dart';
import 'package:library/src/GenericTypes__conversion.dart';
import 'package:library/src/_token_cache.dart' as __lib;
import 'package:library/src/_type_repository.dart' as __lib;
import 'package:library/src/smoke/CalculationResult.dart';
import 'dart:ffi';
import 'package:ffi/ffi.dart';
import 'package:meta/meta.dart';
import 'package:library/src/_library_init.dart' as __lib;
abstract class ListenerWithProperties {
  void release();
  String get message;
  set message(String value);
  CalculationResult get packedMessage;
  set packedMessage(CalculationResult value);
  ListenerWithProperties_ResultStruct get structuredMessage;
  set structuredMessage(ListenerWithProperties_ResultStruct value);
  ListenerWithProperties_ResultEnum get enumeratedMessage;
  set enumeratedMessage(ListenerWithProperties_ResultEnum value);
  List<String> get arrayedMessage;
  set arrayedMessage(List<String> value);
  Map<String, double> get mappedMessage;
  set mappedMessage(Map<String, double> value);
  Uint8List get bufferedMessage;
  set bufferedMessage(Uint8List value);
}
enum ListenerWithProperties_ResultEnum {
    none,
    result
}
// ListenerWithProperties_ResultEnum "private" section, not exported.
int smoke_ListenerWithProperties_ResultEnum_toFfi(ListenerWithProperties_ResultEnum value) {
  switch (value) {
  case ListenerWithProperties_ResultEnum.none:
    return 0;
  break;
  case ListenerWithProperties_ResultEnum.result:
    return 1;
  break;
  default:
    throw StateError("Invalid enum value $value for ListenerWithProperties_ResultEnum enum.");
  }
}
ListenerWithProperties_ResultEnum smoke_ListenerWithProperties_ResultEnum_fromFfi(int handle) {
  switch (handle) {
  case 0:
    return ListenerWithProperties_ResultEnum.none;
  break;
  case 1:
    return ListenerWithProperties_ResultEnum.result;
  break;
  default:
    throw StateError("Invalid numeric value $handle for ListenerWithProperties_ResultEnum enum.");
  }
}
void smoke_ListenerWithProperties_ResultEnum_releaseFfiHandle(int handle) {}
final _smoke_ListenerWithProperties_ResultEnum_create_handle_nullable = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Uint32),
    Pointer<Void> Function(int)
  >('library_smoke_ListenerWithProperties_ResultEnum_create_handle_nullable');
final _smoke_ListenerWithProperties_ResultEnum_release_handle_nullable = __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_ListenerWithProperties_ResultEnum_release_handle_nullable');
final _smoke_ListenerWithProperties_ResultEnum_get_value_nullable = __lib.nativeLibrary.lookupFunction<
    Uint32 Function(Pointer<Void>),
    int Function(Pointer<Void>)
  >('library_smoke_ListenerWithProperties_ResultEnum_get_value_nullable');
Pointer<Void> smoke_ListenerWithProperties_ResultEnum_toFfi_nullable(ListenerWithProperties_ResultEnum value) {
  if (value == null) return Pointer<Void>.fromAddress(0);
  final _handle = smoke_ListenerWithProperties_ResultEnum_toFfi(value);
  final result = _smoke_ListenerWithProperties_ResultEnum_create_handle_nullable(_handle);
  smoke_ListenerWithProperties_ResultEnum_releaseFfiHandle(_handle);
  return result;
}
ListenerWithProperties_ResultEnum smoke_ListenerWithProperties_ResultEnum_fromFfi_nullable(Pointer<Void> handle) {
  if (handle.address == 0) return null;
  final _handle = _smoke_ListenerWithProperties_ResultEnum_get_value_nullable(handle);
  final result = smoke_ListenerWithProperties_ResultEnum_fromFfi(_handle);
  smoke_ListenerWithProperties_ResultEnum_releaseFfiHandle(_handle);
  return result;
}
void smoke_ListenerWithProperties_ResultEnum_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_ListenerWithProperties_ResultEnum_release_handle_nullable(handle);
// End of ListenerWithProperties_ResultEnum "private" section.
class ListenerWithProperties_ResultStruct {
  double result;
  ListenerWithProperties_ResultStruct(this.result);
}
// ListenerWithProperties_ResultStruct "private" section, not exported.
final _smoke_ListenerWithProperties_ResultStruct_create_handle = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Double),
    Pointer<Void> Function(double)
  >('library_smoke_ListenerWithProperties_ResultStruct_create_handle');
final _smoke_ListenerWithProperties_ResultStruct_release_handle = __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_ListenerWithProperties_ResultStruct_release_handle');
final _smoke_ListenerWithProperties_ResultStruct_get_field_result = __lib.nativeLibrary.lookupFunction<
    Double Function(Pointer<Void>),
    double Function(Pointer<Void>)
  >('library_smoke_ListenerWithProperties_ResultStruct_get_field_result');
Pointer<Void> smoke_ListenerWithProperties_ResultStruct_toFfi(ListenerWithProperties_ResultStruct value) {
  final _result_handle = (value.result);
  final _result = _smoke_ListenerWithProperties_ResultStruct_create_handle(_result_handle);
  (_result_handle);
  return _result;
}
ListenerWithProperties_ResultStruct smoke_ListenerWithProperties_ResultStruct_fromFfi(Pointer<Void> handle) {
  final _result_handle = _smoke_ListenerWithProperties_ResultStruct_get_field_result(handle);
  final _result = ListenerWithProperties_ResultStruct(
    (_result_handle)
  );
  (_result_handle);
  return _result;
}
void smoke_ListenerWithProperties_ResultStruct_releaseFfiHandle(Pointer<Void> handle) => _smoke_ListenerWithProperties_ResultStruct_release_handle(handle);
// Nullable ListenerWithProperties_ResultStruct
final _smoke_ListenerWithProperties_ResultStruct_create_handle_nullable = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_ListenerWithProperties_ResultStruct_create_handle_nullable');
final _smoke_ListenerWithProperties_ResultStruct_release_handle_nullable = __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_ListenerWithProperties_ResultStruct_release_handle_nullable');
final _smoke_ListenerWithProperties_ResultStruct_get_value_nullable = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_ListenerWithProperties_ResultStruct_get_value_nullable');
Pointer<Void> smoke_ListenerWithProperties_ResultStruct_toFfi_nullable(ListenerWithProperties_ResultStruct value) {
  if (value == null) return Pointer<Void>.fromAddress(0);
  final _handle = smoke_ListenerWithProperties_ResultStruct_toFfi(value);
  final result = _smoke_ListenerWithProperties_ResultStruct_create_handle_nullable(_handle);
  smoke_ListenerWithProperties_ResultStruct_releaseFfiHandle(_handle);
  return result;
}
ListenerWithProperties_ResultStruct smoke_ListenerWithProperties_ResultStruct_fromFfi_nullable(Pointer<Void> handle) {
  if (handle.address == 0) return null;
  final _handle = _smoke_ListenerWithProperties_ResultStruct_get_value_nullable(handle);
  final result = smoke_ListenerWithProperties_ResultStruct_fromFfi(_handle);
  smoke_ListenerWithProperties_ResultStruct_releaseFfiHandle(_handle);
  return result;
}
void smoke_ListenerWithProperties_ResultStruct_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_ListenerWithProperties_ResultStruct_release_handle_nullable(handle);
// End of ListenerWithProperties_ResultStruct "private" section.
// ListenerWithProperties "private" section, not exported.
final _smoke_ListenerWithProperties_copy_handle = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_ListenerWithProperties_copy_handle');
final _smoke_ListenerWithProperties_release_handle = __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_ListenerWithProperties_release_handle');
final _smoke_ListenerWithProperties_create_proxy = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Uint64, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer),
    Pointer<Void> Function(int, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer, Pointer)
  >('library_smoke_ListenerWithProperties_create_proxy');
final _smoke_ListenerWithProperties_get_raw_pointer = __lib.nativeLibrary.lookupFunction<
      Pointer<Void> Function(Pointer<Void>),
      Pointer<Void> Function(Pointer<Void>)
    >('library_smoke_ListenerWithProperties_get_raw_pointer');
final _smoke_ListenerWithProperties_get_type_id = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_ListenerWithProperties_get_type_id');
class ListenerWithProperties$Impl implements ListenerWithProperties {
  final Pointer<Void> handle;
  ListenerWithProperties$Impl(this.handle);
  @override
  void release() => _smoke_ListenerWithProperties_release_handle(handle);
  String get message {
    final _get_ffi = __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Pointer<Void>), Pointer<Void> Function(Pointer<Void>)>('library_smoke_ListenerWithProperties_message_get');
    final _handle = this.handle;
    final __result_handle = _get_ffi(_handle);
    final _result = String_fromFfi(__result_handle);
    String_releaseFfiHandle(__result_handle);
    return _result;
  }
  set message(String value) {
    final _set_ffi = __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Pointer<Void>), void Function(Pointer<Void>, Pointer<Void>)>('library_smoke_ListenerWithProperties_message_set__String');
    final _value_handle = String_toFfi(value);
    final _handle = this.handle;
    final __result_handle = _set_ffi(_handle, _value_handle);
    String_releaseFfiHandle(_value_handle);
    final _result = (__result_handle);
    (__result_handle);
    return _result;
  }
  CalculationResult get packedMessage {
    final _get_ffi = __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Pointer<Void>), Pointer<Void> Function(Pointer<Void>)>('library_smoke_ListenerWithProperties_packedMessage_get');
    final _handle = this.handle;
    final __result_handle = _get_ffi(_handle);
    final _result = smoke_CalculationResult_fromFfi(__result_handle);
    smoke_CalculationResult_releaseFfiHandle(__result_handle);
    return _result;
  }
  set packedMessage(CalculationResult value) {
    final _set_ffi = __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Pointer<Void>), void Function(Pointer<Void>, Pointer<Void>)>('library_smoke_ListenerWithProperties_packedMessage_set__CalculationResult');
    final _value_handle = smoke_CalculationResult_toFfi(value);
    final _handle = this.handle;
    final __result_handle = _set_ffi(_handle, _value_handle);
    smoke_CalculationResult_releaseFfiHandle(_value_handle);
    final _result = (__result_handle);
    (__result_handle);
    return _result;
  }
  ListenerWithProperties_ResultStruct get structuredMessage {
    final _get_ffi = __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Pointer<Void>), Pointer<Void> Function(Pointer<Void>)>('library_smoke_ListenerWithProperties_structuredMessage_get');
    final _handle = this.handle;
    final __result_handle = _get_ffi(_handle);
    final _result = smoke_ListenerWithProperties_ResultStruct_fromFfi(__result_handle);
    smoke_ListenerWithProperties_ResultStruct_releaseFfiHandle(__result_handle);
    return _result;
  }
  set structuredMessage(ListenerWithProperties_ResultStruct value) {
    final _set_ffi = __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Pointer<Void>), void Function(Pointer<Void>, Pointer<Void>)>('library_smoke_ListenerWithProperties_structuredMessage_set__ResultStruct');
    final _value_handle = smoke_ListenerWithProperties_ResultStruct_toFfi(value);
    final _handle = this.handle;
    final __result_handle = _set_ffi(_handle, _value_handle);
    smoke_ListenerWithProperties_ResultStruct_releaseFfiHandle(_value_handle);
    final _result = (__result_handle);
    (__result_handle);
    return _result;
  }
  ListenerWithProperties_ResultEnum get enumeratedMessage {
    final _get_ffi = __lib.nativeLibrary.lookupFunction<Uint32 Function(Pointer<Void>), int Function(Pointer<Void>)>('library_smoke_ListenerWithProperties_enumeratedMessage_get');
    final _handle = this.handle;
    final __result_handle = _get_ffi(_handle);
    final _result = smoke_ListenerWithProperties_ResultEnum_fromFfi(__result_handle);
    smoke_ListenerWithProperties_ResultEnum_releaseFfiHandle(__result_handle);
    return _result;
  }
  set enumeratedMessage(ListenerWithProperties_ResultEnum value) {
    final _set_ffi = __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Uint32), void Function(Pointer<Void>, int)>('library_smoke_ListenerWithProperties_enumeratedMessage_set__ResultEnum');
    final _value_handle = smoke_ListenerWithProperties_ResultEnum_toFfi(value);
    final _handle = this.handle;
    final __result_handle = _set_ffi(_handle, _value_handle);
    smoke_ListenerWithProperties_ResultEnum_releaseFfiHandle(_value_handle);
    final _result = (__result_handle);
    (__result_handle);
    return _result;
  }
  List<String> get arrayedMessage {
    final _get_ffi = __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Pointer<Void>), Pointer<Void> Function(Pointer<Void>)>('library_smoke_ListenerWithProperties_arrayedMessage_get');
    final _handle = this.handle;
    final __result_handle = _get_ffi(_handle);
    final _result = ListOf_String_fromFfi(__result_handle);
    ListOf_String_releaseFfiHandle(__result_handle);
    return _result;
  }
  set arrayedMessage(List<String> value) {
    final _set_ffi = __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Pointer<Void>), void Function(Pointer<Void>, Pointer<Void>)>('library_smoke_ListenerWithProperties_arrayedMessage_set__ListOf_1String');
    final _value_handle = ListOf_String_toFfi(value);
    final _handle = this.handle;
    final __result_handle = _set_ffi(_handle, _value_handle);
    ListOf_String_releaseFfiHandle(_value_handle);
    final _result = (__result_handle);
    (__result_handle);
    return _result;
  }
  Map<String, double> get mappedMessage {
    final _get_ffi = __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Pointer<Void>), Pointer<Void> Function(Pointer<Void>)>('library_smoke_ListenerWithProperties_mappedMessage_get');
    final _handle = this.handle;
    final __result_handle = _get_ffi(_handle);
    final _result = MapOf_String_to_Double_fromFfi(__result_handle);
    MapOf_String_to_Double_releaseFfiHandle(__result_handle);
    return _result;
  }
  set mappedMessage(Map<String, double> value) {
    final _set_ffi = __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Pointer<Void>), void Function(Pointer<Void>, Pointer<Void>)>('library_smoke_ListenerWithProperties_mappedMessage_set__MapOf_1String_1to_1Double');
    final _value_handle = MapOf_String_to_Double_toFfi(value);
    final _handle = this.handle;
    final __result_handle = _set_ffi(_handle, _value_handle);
    MapOf_String_to_Double_releaseFfiHandle(_value_handle);
    final _result = (__result_handle);
    (__result_handle);
    return _result;
  }
  Uint8List get bufferedMessage {
    final _get_ffi = __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Pointer<Void>), Pointer<Void> Function(Pointer<Void>)>('library_smoke_ListenerWithProperties_bufferedMessage_get');
    final _handle = this.handle;
    final __result_handle = _get_ffi(_handle);
    final _result = Blob_fromFfi(__result_handle);
    Blob_releaseFfiHandle(__result_handle);
    return _result;
  }
  set bufferedMessage(Uint8List value) {
    final _set_ffi = __lib.nativeLibrary.lookupFunction<Void Function(Pointer<Void>, Pointer<Void>), void Function(Pointer<Void>, Pointer<Void>)>('library_smoke_ListenerWithProperties_bufferedMessage_set__Blob');
    final _value_handle = Blob_toFfi(value);
    final _handle = this.handle;
    final __result_handle = _set_ffi(_handle, _value_handle);
    Blob_releaseFfiHandle(_value_handle);
    final _result = (__result_handle);
    (__result_handle);
    return _result;
  }
}
int _ListenerWithProperties_message_get_static(int _token, Pointer<Pointer<Void>> _result) {
  _result.value = String_toFfi((__lib.instanceCache[_token] as ListenerWithProperties).message);
  return 0;
}
int _ListenerWithProperties_message_set_static(int _token, Pointer<Void> _value) {
  (__lib.instanceCache[_token] as ListenerWithProperties).message = String_fromFfi(_value);
  String_releaseFfiHandle(_value);
  return 0;
}
int _ListenerWithProperties_packedMessage_get_static(int _token, Pointer<Pointer<Void>> _result) {
  _result.value = smoke_CalculationResult_toFfi((__lib.instanceCache[_token] as ListenerWithProperties).packedMessage);
  return 0;
}
int _ListenerWithProperties_packedMessage_set_static(int _token, Pointer<Void> _value) {
  (__lib.instanceCache[_token] as ListenerWithProperties).packedMessage = smoke_CalculationResult_fromFfi(_value);
  smoke_CalculationResult_releaseFfiHandle(_value);
  return 0;
}
int _ListenerWithProperties_structuredMessage_get_static(int _token, Pointer<Pointer<Void>> _result) {
  _result.value = smoke_ListenerWithProperties_ResultStruct_toFfi((__lib.instanceCache[_token] as ListenerWithProperties).structuredMessage);
  return 0;
}
int _ListenerWithProperties_structuredMessage_set_static(int _token, Pointer<Void> _value) {
  (__lib.instanceCache[_token] as ListenerWithProperties).structuredMessage = smoke_ListenerWithProperties_ResultStruct_fromFfi(_value);
  smoke_ListenerWithProperties_ResultStruct_releaseFfiHandle(_value);
  return 0;
}
int _ListenerWithProperties_enumeratedMessage_get_static(int _token, Pointer<Uint32> _result) {
  _result.value = smoke_ListenerWithProperties_ResultEnum_toFfi((__lib.instanceCache[_token] as ListenerWithProperties).enumeratedMessage);
  return 0;
}
int _ListenerWithProperties_enumeratedMessage_set_static(int _token, int _value) {
  (__lib.instanceCache[_token] as ListenerWithProperties).enumeratedMessage = smoke_ListenerWithProperties_ResultEnum_fromFfi(_value);
  smoke_ListenerWithProperties_ResultEnum_releaseFfiHandle(_value);
  return 0;
}
int _ListenerWithProperties_arrayedMessage_get_static(int _token, Pointer<Pointer<Void>> _result) {
  _result.value = ListOf_String_toFfi((__lib.instanceCache[_token] as ListenerWithProperties).arrayedMessage);
  return 0;
}
int _ListenerWithProperties_arrayedMessage_set_static(int _token, Pointer<Void> _value) {
  (__lib.instanceCache[_token] as ListenerWithProperties).arrayedMessage = ListOf_String_fromFfi(_value);
  ListOf_String_releaseFfiHandle(_value);
  return 0;
}
int _ListenerWithProperties_mappedMessage_get_static(int _token, Pointer<Pointer<Void>> _result) {
  _result.value = MapOf_String_to_Double_toFfi((__lib.instanceCache[_token] as ListenerWithProperties).mappedMessage);
  return 0;
}
int _ListenerWithProperties_mappedMessage_set_static(int _token, Pointer<Void> _value) {
  (__lib.instanceCache[_token] as ListenerWithProperties).mappedMessage = MapOf_String_to_Double_fromFfi(_value);
  MapOf_String_to_Double_releaseFfiHandle(_value);
  return 0;
}
int _ListenerWithProperties_bufferedMessage_get_static(int _token, Pointer<Pointer<Void>> _result) {
  _result.value = Blob_toFfi((__lib.instanceCache[_token] as ListenerWithProperties).bufferedMessage);
  return 0;
}
int _ListenerWithProperties_bufferedMessage_set_static(int _token, Pointer<Void> _value) {
  (__lib.instanceCache[_token] as ListenerWithProperties).bufferedMessage = Blob_fromFfi(_value);
  Blob_releaseFfiHandle(_value);
  return 0;
}
Pointer<Void> smoke_ListenerWithProperties_toFfi(ListenerWithProperties value) {
  if (value is ListenerWithProperties$Impl) return _smoke_ListenerWithProperties_copy_handle(value.handle);
  final result = _smoke_ListenerWithProperties_create_proxy(
    __lib.cacheObject(value),
    __lib.uncacheObjectFfi,
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Pointer<Void>>)>(_ListenerWithProperties_message_get_static, __lib.unknownError),
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Void>)>(_ListenerWithProperties_message_set_static, __lib.unknownError),
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Pointer<Void>>)>(_ListenerWithProperties_packedMessage_get_static, __lib.unknownError),
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Void>)>(_ListenerWithProperties_packedMessage_set_static, __lib.unknownError),
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Pointer<Void>>)>(_ListenerWithProperties_structuredMessage_get_static, __lib.unknownError),
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Void>)>(_ListenerWithProperties_structuredMessage_set_static, __lib.unknownError),
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Uint32>)>(_ListenerWithProperties_enumeratedMessage_get_static, __lib.unknownError),
    Pointer.fromFunction<Int64 Function(Uint64, Uint32)>(_ListenerWithProperties_enumeratedMessage_set_static, __lib.unknownError),
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Pointer<Void>>)>(_ListenerWithProperties_arrayedMessage_get_static, __lib.unknownError),
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Void>)>(_ListenerWithProperties_arrayedMessage_set_static, __lib.unknownError),
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Pointer<Void>>)>(_ListenerWithProperties_mappedMessage_get_static, __lib.unknownError),
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Void>)>(_ListenerWithProperties_mappedMessage_set_static, __lib.unknownError),
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Pointer<Void>>)>(_ListenerWithProperties_bufferedMessage_get_static, __lib.unknownError),
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Void>)>(_ListenerWithProperties_bufferedMessage_set_static, __lib.unknownError)
  );
  __lib.reverseCache[_smoke_ListenerWithProperties_get_raw_pointer(result)] = value;
  return result;
}
ListenerWithProperties smoke_ListenerWithProperties_fromFfi(Pointer<Void> handle) {
  final instance = __lib.reverseCache[_smoke_ListenerWithProperties_get_raw_pointer(handle)] as ListenerWithProperties;
  if (instance != null) return instance;
  final _copied_handle = _smoke_ListenerWithProperties_copy_handle(handle);
  final _type_id_handle = _smoke_ListenerWithProperties_get_type_id(handle);
  final _type_id = String_fromFfi(_type_id_handle);
  final result = _type_id.isEmpty
    ? ListenerWithProperties$Impl(_copied_handle)
    : __lib.typeRepository[_type_id](_copied_handle);
  String_releaseFfiHandle(_type_id_handle);
  return result;
}
void smoke_ListenerWithProperties_releaseFfiHandle(Pointer<Void> handle) =>
  _smoke_ListenerWithProperties_release_handle(handle);
Pointer<Void> smoke_ListenerWithProperties_toFfi_nullable(ListenerWithProperties value) =>
  value != null ? smoke_ListenerWithProperties_toFfi(value) : Pointer<Void>.fromAddress(0);
ListenerWithProperties smoke_ListenerWithProperties_fromFfi_nullable(Pointer<Void> handle) =>
  handle.address != 0 ? smoke_ListenerWithProperties_fromFfi(handle) : null;
void smoke_ListenerWithProperties_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_ListenerWithProperties_release_handle(handle);
// End of ListenerWithProperties "private" section.
