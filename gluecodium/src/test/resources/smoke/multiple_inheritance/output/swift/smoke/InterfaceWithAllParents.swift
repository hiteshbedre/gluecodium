//
//
import Foundation
public protocol InterfaceWithAllParents : ParentInterfaceOne, ParentInterfaceTwo {
    var parentPropertyOne: String { get set }
    var parentPropertyTwo: String { get set }
    var childProperty: String { get set }
    func parentFunctionOne() -> Void
    func parentFunctionTwo() -> Void
    func childFunction() -> Void
}
internal class _InterfaceWithAllParents: InterfaceWithAllParents {
    var parentPropertyOne: String {
        get {
            return moveFromCType(smoke_ParentInterfaceOne_parentPropertyOne_get(self.c_instance))
        }
        set {
            let c_value = moveToCType(newValue)
            return moveFromCType(smoke_ParentInterfaceOne_parentPropertyOne_set(self.c_instance, c_value.ref))
        }
    }
    var parentPropertyTwo: String {
        get {
            return moveFromCType(smoke_ParentInterfaceTwo_parentPropertyTwo_get(self.c_instance))
        }
        set {
            let c_value = moveToCType(newValue)
            return moveFromCType(smoke_ParentInterfaceTwo_parentPropertyTwo_set(self.c_instance, c_value.ref))
        }
    }
    var childProperty: String {
        get {
            return moveFromCType(smoke_InterfaceWithAllParents_childProperty_get(self.c_instance))
        }
        set {
            let c_value = moveToCType(newValue)
            return moveFromCType(smoke_InterfaceWithAllParents_childProperty_set(self.c_instance, c_value.ref))
        }
    }
    let c_instance : _baseRef
    init(cInterfaceWithAllParents: _baseRef) {
        guard cInterfaceWithAllParents != 0 else {
            fatalError("Nullptr value is not supported for initializers")
        }
        c_instance = cInterfaceWithAllParents
    }
    deinit {
        smoke_InterfaceWithAllParents_remove_swift_object_from_wrapper_cache(c_instance)
        smoke_InterfaceWithAllParents_release_handle(c_instance)
    }
    public func parentFunctionOne() -> Void {
        return moveFromCType(smoke_ParentInterfaceOne_parentFunctionOne(self.c_instance))
    }
    public func parentFunctionTwo() -> Void {
        return moveFromCType(smoke_ParentInterfaceTwo_parentFunctionTwo(self.c_instance))
    }
    public func childFunction() -> Void {
        return moveFromCType(smoke_InterfaceWithAllParents_childFunction(self.c_instance))
    }
}
@_cdecl("_CBridgeInitsmoke_InterfaceWithAllParents")
internal func _CBridgeInitsmoke_InterfaceWithAllParents(handle: _baseRef) -> UnsafeMutableRawPointer {
    let reference = _InterfaceWithAllParents(cInterfaceWithAllParents: handle)
    return Unmanaged<AnyObject>.passRetained(reference).toOpaque()
}
internal func getRef(_ ref: InterfaceWithAllParents?, owning: Bool = true) -> RefHolder {
    guard let reference = ref else {
        return RefHolder(0)
    }
    if let instanceReference = reference as? NativeBase {
        let handle_copy = smoke_InterfaceWithAllParents_copy_handle(instanceReference.c_handle)
        return owning
            ? RefHolder(ref: handle_copy, release: smoke_InterfaceWithAllParents_release_handle)
            : RefHolder(handle_copy)
    }
    var functions = smoke_InterfaceWithAllParents_FunctionTable()
    functions.swift_pointer = Unmanaged<AnyObject>.passRetained(reference).toOpaque()
    functions.release = {swift_class_pointer in
        if let swift_class = swift_class_pointer {
            Unmanaged<AnyObject>.fromOpaque(swift_class).release()
        }
    }
    functions.smoke_ParentInterfaceOne_parentFunctionOne = {(swift_class_pointer) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! InterfaceWithAllParents
        swift_class.parentFunctionOne()
    }
    functions.smoke_ParentInterfaceTwo_parentFunctionTwo = {(swift_class_pointer) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! InterfaceWithAllParents
        swift_class.parentFunctionTwo()
    }
    functions.smoke_InterfaceWithAllParents_childFunction = {(swift_class_pointer) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! InterfaceWithAllParents
        swift_class.childFunction()
    }
    functions.smoke_ParentInterfaceOne_parentPropertyOne_get = {(swift_class_pointer) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! InterfaceWithAllParents
        return copyToCType(swift_class.parentPropertyOne).ref
    }
    functions.smoke_ParentInterfaceOne_parentPropertyOne_set = {(swift_class_pointer, value) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! InterfaceWithAllParents
        swift_class.parentPropertyOne = moveFromCType(value)
    }
    functions.smoke_ParentInterfaceTwo_parentPropertyTwo_get = {(swift_class_pointer) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! InterfaceWithAllParents
        return copyToCType(swift_class.parentPropertyTwo).ref
    }
    functions.smoke_ParentInterfaceTwo_parentPropertyTwo_set = {(swift_class_pointer, value) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! InterfaceWithAllParents
        swift_class.parentPropertyTwo = moveFromCType(value)
    }
    functions.smoke_InterfaceWithAllParents_childProperty_get = {(swift_class_pointer) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! InterfaceWithAllParents
        return copyToCType(swift_class.childProperty).ref
    }
    functions.smoke_InterfaceWithAllParents_childProperty_set = {(swift_class_pointer, value) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! InterfaceWithAllParents
        swift_class.childProperty = moveFromCType(value)
    }
    let proxy = smoke_InterfaceWithAllParents_create_proxy(functions)
    return owning ? RefHolder(ref: proxy, release: smoke_InterfaceWithAllParents_release_handle) : RefHolder(proxy)
}
extension _InterfaceWithAllParents: NativeBase {
    var c_handle: _baseRef { return c_instance }
}
internal func InterfaceWithAllParents_copyFromCType(_ handle: _baseRef) -> InterfaceWithAllParents {
    if let swift_pointer = smoke_InterfaceWithAllParents_get_swift_object_from_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? InterfaceWithAllParents {
        return re_constructed
    }
    if let swift_pointer = smoke_InterfaceWithAllParents_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? InterfaceWithAllParents {
        return re_constructed
    }
    if let swift_pointer = smoke_InterfaceWithAllParents_get_typed(smoke_InterfaceWithAllParents_copy_handle(handle)),
        let typed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeRetainedValue() as? InterfaceWithAllParents {
        smoke_InterfaceWithAllParents_cache_swift_object_wrapper(handle, swift_pointer)
        return typed
    }
    fatalError("Failed to initialize Swift object")
}
internal func InterfaceWithAllParents_moveFromCType(_ handle: _baseRef) -> InterfaceWithAllParents {
    if let swift_pointer = smoke_InterfaceWithAllParents_get_swift_object_from_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? InterfaceWithAllParents {
        smoke_InterfaceWithAllParents_release_handle(handle)
        return re_constructed
    }
    if let swift_pointer = smoke_InterfaceWithAllParents_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? InterfaceWithAllParents {
        smoke_InterfaceWithAllParents_release_handle(handle)
        return re_constructed
    }
    if let swift_pointer = smoke_InterfaceWithAllParents_get_typed(handle),
        let typed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeRetainedValue() as? InterfaceWithAllParents {
        smoke_InterfaceWithAllParents_cache_swift_object_wrapper(handle, swift_pointer)
        return typed
    }
    fatalError("Failed to initialize Swift object")
}
internal func InterfaceWithAllParents_copyFromCType(_ handle: _baseRef) -> InterfaceWithAllParents? {
    guard handle != 0 else {
        return nil
    }
    return InterfaceWithAllParents_moveFromCType(handle) as InterfaceWithAllParents
}
internal func InterfaceWithAllParents_moveFromCType(_ handle: _baseRef) -> InterfaceWithAllParents? {
    guard handle != 0 else {
        return nil
    }
    return InterfaceWithAllParents_moveFromCType(handle) as InterfaceWithAllParents
}
internal func copyToCType(_ swiftClass: InterfaceWithAllParents) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: InterfaceWithAllParents) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
internal func copyToCType(_ swiftClass: InterfaceWithAllParents?) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: InterfaceWithAllParents?) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
