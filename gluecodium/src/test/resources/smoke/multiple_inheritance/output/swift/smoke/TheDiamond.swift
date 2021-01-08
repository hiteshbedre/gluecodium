//
//
import Foundation
public protocol TheDiamond : FacetOne, FacetTwo {
    var parentPropertyOne: String { get set }
    func parentFunctionOne() -> Void
}
internal class _TheDiamond: TheDiamond {
    var parentPropertyOne: String {
        get {
            return moveFromCType(smoke_ParentInterfaceOne_parentPropertyOne_get(self.c_instance))
        }
        set {
            let c_value = moveToCType(newValue)
            return moveFromCType(smoke_ParentInterfaceOne_parentPropertyOne_set(self.c_instance, c_value.ref))
        }
    }
    let c_instance : _baseRef
    init(cTheDiamond: _baseRef) {
        guard cTheDiamond != 0 else {
            fatalError("Nullptr value is not supported for initializers")
        }
        c_instance = cTheDiamond
    }
    deinit {
        smoke_TheDiamond_remove_swift_object_from_wrapper_cache(c_instance)
        smoke_TheDiamond_release_handle(c_instance)
    }
    public func parentFunctionOne() -> Void {
        return moveFromCType(smoke_ParentInterfaceOne_parentFunctionOne(self.c_instance))
    }
}
@_cdecl("_CBridgeInitsmoke_TheDiamond")
internal func _CBridgeInitsmoke_TheDiamond(handle: _baseRef) -> UnsafeMutableRawPointer {
    let reference = _TheDiamond(cTheDiamond: handle)
    return Unmanaged<AnyObject>.passRetained(reference).toOpaque()
}
internal func getRef(_ ref: TheDiamond?, owning: Bool = true) -> RefHolder {
    guard let reference = ref else {
        return RefHolder(0)
    }
    if let instanceReference = reference as? NativeBase {
        let handle_copy = smoke_TheDiamond_copy_handle(instanceReference.c_handle)
        return owning
            ? RefHolder(ref: handle_copy, release: smoke_TheDiamond_release_handle)
            : RefHolder(handle_copy)
    }
    var functions = smoke_TheDiamond_FunctionTable()
    functions.swift_pointer = Unmanaged<AnyObject>.passRetained(reference).toOpaque()
    functions.release = {swift_class_pointer in
        if let swift_class = swift_class_pointer {
            Unmanaged<AnyObject>.fromOpaque(swift_class).release()
        }
    }
    functions.smoke_ParentInterfaceOne_parentFunctionOne = {(swift_class_pointer) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! TheDiamond
        swift_class.parentFunctionOne()
    }
    functions.smoke_ParentInterfaceOne_parentPropertyOne_get = {(swift_class_pointer) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! TheDiamond
        return copyToCType(swift_class.parentPropertyOne).ref
    }
    functions.smoke_ParentInterfaceOne_parentPropertyOne_set = {(swift_class_pointer, value) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! TheDiamond
        swift_class.parentPropertyOne = moveFromCType(value)
    }
    let proxy = smoke_TheDiamond_create_proxy(functions)
    return owning ? RefHolder(ref: proxy, release: smoke_TheDiamond_release_handle) : RefHolder(proxy)
}
extension _TheDiamond: NativeBase {
    var c_handle: _baseRef { return c_instance }
}
internal func TheDiamond_copyFromCType(_ handle: _baseRef) -> TheDiamond {
    if let swift_pointer = smoke_TheDiamond_get_swift_object_from_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? TheDiamond {
        return re_constructed
    }
    if let swift_pointer = smoke_TheDiamond_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? TheDiamond {
        return re_constructed
    }
    if let swift_pointer = smoke_TheDiamond_get_typed(smoke_TheDiamond_copy_handle(handle)),
        let typed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeRetainedValue() as? TheDiamond {
        smoke_TheDiamond_cache_swift_object_wrapper(handle, swift_pointer)
        return typed
    }
    fatalError("Failed to initialize Swift object")
}
internal func TheDiamond_moveFromCType(_ handle: _baseRef) -> TheDiamond {
    if let swift_pointer = smoke_TheDiamond_get_swift_object_from_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? TheDiamond {
        smoke_TheDiamond_release_handle(handle)
        return re_constructed
    }
    if let swift_pointer = smoke_TheDiamond_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? TheDiamond {
        smoke_TheDiamond_release_handle(handle)
        return re_constructed
    }
    if let swift_pointer = smoke_TheDiamond_get_typed(handle),
        let typed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeRetainedValue() as? TheDiamond {
        smoke_TheDiamond_cache_swift_object_wrapper(handle, swift_pointer)
        return typed
    }
    fatalError("Failed to initialize Swift object")
}
internal func TheDiamond_copyFromCType(_ handle: _baseRef) -> TheDiamond? {
    guard handle != 0 else {
        return nil
    }
    return TheDiamond_moveFromCType(handle) as TheDiamond
}
internal func TheDiamond_moveFromCType(_ handle: _baseRef) -> TheDiamond? {
    guard handle != 0 else {
        return nil
    }
    return TheDiamond_moveFromCType(handle) as TheDiamond
}
internal func copyToCType(_ swiftClass: TheDiamond) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: TheDiamond) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
internal func copyToCType(_ swiftClass: TheDiamond?) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: TheDiamond?) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
