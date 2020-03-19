import 'package:library/src/BuiltInTypes__conversion.dart';
import 'package:library/src/_token_cache.dart' as __lib;
import 'dart:ffi';
import 'package:ffi/ffi.dart';
import 'package:meta/meta.dart';
import 'package:library/src/_library_init.dart' as __lib;
typedef StandaloneProducer = String Function();
// StandaloneProducer "private" section, not exported.
final _smoke_StandaloneProducer_copy_handle = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_StandaloneProducer_copy_handle');
final _smoke_StandaloneProducer_release_handle = __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_StandaloneProducer_release_handle');
final _smoke_StandaloneProducer_create_proxy = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Uint64, Pointer, Pointer),
    Pointer<Void> Function(int, Pointer, Pointer)
  >('library_smoke_StandaloneProducer_create_proxy');
final _smoke_StandaloneProducer_get_raw_pointer = __lib.nativeLibrary.lookupFunction<
      Pointer<Void> Function(Pointer<Void>),
      Pointer<Void> Function(Pointer<Void>)
    >('library_smoke_StandaloneProducer_get_raw_pointer');
class StandaloneProducer$Impl {
  Pointer<Void> get _handle => handle;
  final Pointer<Void> handle;
  StandaloneProducer$Impl(this.handle);
  void release() => _smoke_StandaloneProducer_release_handle(handle);
  String call() {
    final _call_ffi = __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Pointer<Void>), Pointer<Void> Function(Pointer<Void>)>('library_smoke_StandaloneProducer_call');
    final _handle = this.handle;
    final __result_handle = _call_ffi(_handle);
    final _result = String_fromFfi(__result_handle);
    String_releaseFfiHandle(__result_handle);
    return _result;
  }
}
int _StandaloneProducer_call_static(int _token, Pointer<Pointer<Void>> _result) {
  final _result_object = (__lib.instanceCache[_token] as StandaloneProducer)();
  _result.value = String_toFfi(_result_object);
  return 0;
}
Pointer<Void> smoke_StandaloneProducer_toFfi(StandaloneProducer value) {
  final result = _smoke_StandaloneProducer_create_proxy(
    __lib.cacheObject(value),
    __lib.uncacheObjectFfi,
    Pointer.fromFunction<Int64 Function(Uint64, Pointer<Pointer<Void>>)>(_StandaloneProducer_call_static, __lib.unknownError)
  );
  __lib.reverseCache[_smoke_StandaloneProducer_get_raw_pointer(result)] = value;
  return result;
}
StandaloneProducer smoke_StandaloneProducer_fromFfi(Pointer<Void> handle) {
  final instance = __lib.reverseCache[_smoke_StandaloneProducer_get_raw_pointer(handle)] as StandaloneProducer;
  if (instance != null) return instance;
  final _impl = StandaloneProducer$Impl(_smoke_StandaloneProducer_copy_handle(handle));
  return () {
    final _result =_impl.call();
    _impl.release();
    return _result;
  };
}
void smoke_StandaloneProducer_releaseFfiHandle(Pointer<Void> handle) =>
  _smoke_StandaloneProducer_release_handle(handle);
// Nullable StandaloneProducer
final _smoke_StandaloneProducer_create_handle_nullable = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_StandaloneProducer_create_handle_nullable');
final _smoke_StandaloneProducer_release_handle_nullable = __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_StandaloneProducer_release_handle_nullable');
final _smoke_StandaloneProducer_get_value_nullable = __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_StandaloneProducer_get_value_nullable');
Pointer<Void> smoke_StandaloneProducer_toFfi_nullable(StandaloneProducer value) {
  if (value == null) return Pointer<Void>.fromAddress(0);
  final _handle = smoke_StandaloneProducer_toFfi(value);
  final result = _smoke_StandaloneProducer_create_handle_nullable(_handle);
  smoke_StandaloneProducer_releaseFfiHandle(_handle);
  return result;
}
StandaloneProducer smoke_StandaloneProducer_fromFfi_nullable(Pointer<Void> handle) {
  if (handle.address == 0) return null;
  final _handle = _smoke_StandaloneProducer_get_value_nullable(handle);
  final result = smoke_StandaloneProducer_fromFfi(_handle);
  smoke_StandaloneProducer_releaseFfiHandle(_handle);
  return result;
}
void smoke_StandaloneProducer_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_StandaloneProducer_release_handle_nullable(handle);
// End of StandaloneProducer "private" section.
