import 'package:library/src/_token_cache.dart' as __lib;
import 'dart:ffi';
import 'package:ffi/ffi.dart';
import 'package:meta/meta.dart';
import 'package:library/src/_library_context.dart' as __lib;
abstract class SingleNamedConstructor {
  factory SingleNamedConstructor.fooBar() => SingleNamedConstructor$Impl.fooBar();
  /// Destroys the underlying native object.
  ///
  /// Call this to free memory when you no longer need this instance.
  /// Note that setting the instance to null will not destroy the underlying native object.
  void release();
}
// SingleNamedConstructor "private" section, not exported.
final _smoke_SingleNamedConstructor_copy_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_SingleNamedConstructor_copy_handle'));
final _smoke_SingleNamedConstructor_release_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_SingleNamedConstructor_release_handle'));
class SingleNamedConstructor$Impl implements SingleNamedConstructor {
  @protected
  Pointer<Void> handle = Pointer<Void>.fromAddress(0);
  SingleNamedConstructor$Impl(this.handle);
  @override
  void release() {
    if (handle.address == 0) return;
    __lib.uncacheInstance(handle);
    _smoke_SingleNamedConstructor_release_handle(handle);
    handle = Pointer<Void>.fromAddress(0);
  }
  SingleNamedConstructor$Impl.fooBar() : handle = _fooBar() {
    __lib.cacheInstance(handle, this);
  }
  static Pointer<Void> _fooBar() {
    final _fooBar_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Int32), Pointer<Void> Function(int)>('library_smoke_SingleNamedConstructor_create'));
    final __result_handle = _fooBar_ffi(__lib.LibraryContext.isolateId);
    return __result_handle;
  }
}
Pointer<Void> smoke_SingleNamedConstructor_toFfi(SingleNamedConstructor value) =>
  _smoke_SingleNamedConstructor_copy_handle((value as SingleNamedConstructor$Impl).handle);
SingleNamedConstructor smoke_SingleNamedConstructor_fromFfi(Pointer<Void> handle) {
  final instance = __lib.getCachedInstance(handle);
  if (instance != null && instance is SingleNamedConstructor) return instance as SingleNamedConstructor;
  final _copied_handle = _smoke_SingleNamedConstructor_copy_handle(handle);
  final result = SingleNamedConstructor$Impl(_copied_handle);
  __lib.cacheInstance(_copied_handle, result);
  return result;
}
void smoke_SingleNamedConstructor_releaseFfiHandle(Pointer<Void> handle) =>
  _smoke_SingleNamedConstructor_release_handle(handle);
Pointer<Void> smoke_SingleNamedConstructor_toFfi_nullable(SingleNamedConstructor? value) =>
  value != null ? smoke_SingleNamedConstructor_toFfi(value) : Pointer<Void>.fromAddress(0);
SingleNamedConstructor? smoke_SingleNamedConstructor_fromFfi_nullable(Pointer<Void> handle) =>
  handle.address != 0 ? smoke_SingleNamedConstructor_fromFfi(handle) : null;
void smoke_SingleNamedConstructor_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_SingleNamedConstructor_release_handle(handle);
// End of SingleNamedConstructor "private" section.
