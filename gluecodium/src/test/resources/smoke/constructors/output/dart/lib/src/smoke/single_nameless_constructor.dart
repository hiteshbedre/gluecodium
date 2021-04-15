import 'package:library/src/_token_cache.dart' as __lib;
import 'dart:ffi';
import 'package:ffi/ffi.dart';
import 'package:meta/meta.dart';
import 'package:library/src/_library_context.dart' as __lib;
abstract class SingleNamelessConstructor {
  factory SingleNamelessConstructor() => SingleNamelessConstructor$Impl.create();
  /// Destroys the underlying native object.
  ///
  /// Call this to free memory when you no longer need this instance.
  /// Note that setting the instance to null will not destroy the underlying native object.
  void release();
}
// SingleNamelessConstructor "private" section, not exported.
final _smoke_SingleNamelessConstructor_copy_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Pointer<Void> Function(Pointer<Void>),
    Pointer<Void> Function(Pointer<Void>)
  >('library_smoke_SingleNamelessConstructor_copy_handle'));
final _smoke_SingleNamelessConstructor_release_handle = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<
    Void Function(Pointer<Void>),
    void Function(Pointer<Void>)
  >('library_smoke_SingleNamelessConstructor_release_handle'));
class SingleNamelessConstructor$Impl implements SingleNamelessConstructor {
  @protected
  Pointer<Void> handle = Pointer<Void>.fromAddress(0);
  SingleNamelessConstructor$Impl(this.handle);
  @override
  void release() {
    if (handle.address == 0) return;
    __lib.uncacheInstance(handle);
    _smoke_SingleNamelessConstructor_release_handle(handle);
    handle = Pointer<Void>.fromAddress(0);
  }
  SingleNamelessConstructor$Impl.create() : handle = _create() {
    __lib.cacheInstance(handle, this);
  }
  static Pointer<Void> _create() {
    final _create_ffi = __lib.catchArgumentError(() => __lib.nativeLibrary.lookupFunction<Pointer<Void> Function(Int32), Pointer<Void> Function(int)>('library_smoke_SingleNamelessConstructor_create'));
    final __result_handle = _create_ffi(__lib.LibraryContext.isolateId);
    return __result_handle;
  }
}
Pointer<Void> smoke_SingleNamelessConstructor_toFfi(SingleNamelessConstructor value) =>
  _smoke_SingleNamelessConstructor_copy_handle((value as SingleNamelessConstructor$Impl).handle);
SingleNamelessConstructor smoke_SingleNamelessConstructor_fromFfi(Pointer<Void> handle) {
  final instance = __lib.getCachedInstance(handle);
  if (instance != null && instance is SingleNamelessConstructor) return instance as SingleNamelessConstructor;
  final _copied_handle = _smoke_SingleNamelessConstructor_copy_handle(handle);
  final result = SingleNamelessConstructor$Impl(_copied_handle);
  __lib.cacheInstance(_copied_handle, result);
  return result;
}
void smoke_SingleNamelessConstructor_releaseFfiHandle(Pointer<Void> handle) =>
  _smoke_SingleNamelessConstructor_release_handle(handle);
Pointer<Void> smoke_SingleNamelessConstructor_toFfi_nullable(SingleNamelessConstructor? value) =>
  value != null ? smoke_SingleNamelessConstructor_toFfi(value) : Pointer<Void>.fromAddress(0);
SingleNamelessConstructor? smoke_SingleNamelessConstructor_fromFfi_nullable(Pointer<Void> handle) =>
  handle.address != 0 ? smoke_SingleNamelessConstructor_fromFfi(handle) : null;
void smoke_SingleNamelessConstructor_releaseFfiHandle_nullable(Pointer<Void> handle) =>
  _smoke_SingleNamelessConstructor_release_handle(handle);
// End of SingleNamelessConstructor "private" section.
