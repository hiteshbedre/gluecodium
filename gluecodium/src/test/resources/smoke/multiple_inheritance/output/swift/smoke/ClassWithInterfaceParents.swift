//
//
import Foundation
public class ClassWithInterfaceParents: ParentInterfaceOne, ParentInterfaceTwo {
    public var parentPropertyOne: String {
        get {
            return moveFromCType(smoke_ParentInterfaceOne_parentPropertyOne_get(self.c_instance))
        }
        set {
            let c_value = moveToCType(newValue)
            return moveFromCType(smoke_ParentInterfaceOne_parentPropertyOne_set(self.c_instance, c_value.ref))
        }
    }
    public var parentPropertyTwo: String {
        get {
            return moveFromCType(smoke_ParentInterfaceTwo_parentPropertyTwo_get(self.c_instance))
        }
        set {
            let c_value = moveToCType(newValue)
            return moveFromCType(smoke_ParentInterfaceTwo_parentPropertyTwo_set(self.c_instance, c_value.ref))
        }
    }
    public var childProperty: String {
        get {
            return moveFromCType(smoke_ClassWithInterfaceParents_childProperty_get(self.c_instance))
        }
        set {
            let c_value = moveToCType(newValue)
            return moveFromCType(smoke_ClassWithInterfaceParents_childProperty_set(self.c_instance, c_value.ref))
        }
    }
    let c_instance : _baseRef
    init(cClassWithInterfaceParents: _baseRef) {
        guard cClassWithInterfaceParents != 0 else {
            fatalError("Nullptr value is not supported for initializers")
        }
        c_instance = cClassWithInterfaceParents
    }
    deinit {
        smoke_ClassWithInterfaceParents_remove_swift_object_from_wrapper_cache(c_instance)
        smoke_ClassWithInterfaceParents_release_handle(c_instance)
    }
    public func parentFunctionOne() -> Void {
        return moveFromCType(smoke_ParentInterfaceOne_parentFunctionOne(self.c_instance))
    }
    public func parentFunctionTwo() -> Void {
        return moveFromCType(smoke_ParentInterfaceTwo_parentFunctionTwo(self.c_instance))
    }
    public func childFunction() -> Void {
        return moveFromCType(smoke_ClassWithInterfaceParents_childFunction(self.c_instance))
    }
}
@_cdecl("_CBridgeInitsmoke_ClassWithInterfaceParents")
internal func _CBridgeInitsmoke_ClassWithInterfaceParents(handle: _baseRef) -> UnsafeMutableRawPointer {
    let reference = ClassWithInterfaceParents(cClassWithInterfaceParents: handle)
    return Unmanaged<AnyObject>.passRetained(reference).toOpaque()
}
internal func getRef(_ ref: ClassWithInterfaceParents?, owning: Bool = true) -> RefHolder {
    guard let c_handle = ref?.c_instance else {
        return RefHolder(0)
    }
    let handle_copy = smoke_ClassWithInterfaceParents_copy_handle(c_handle)
    return owning
        ? RefHolder(ref: handle_copy, release: smoke_ClassWithInterfaceParents_release_handle)
        : RefHolder(handle_copy)
}
extension ClassWithInterfaceParents: NativeBase {
    var c_handle: _baseRef { return c_instance }
}
extension ClassWithInterfaceParents: Hashable {
    public static func == (lhs: ClassWithInterfaceParents, rhs: ClassWithInterfaceParents) -> Bool {
        return lhs.c_handle == rhs.c_handle
    }
    public func hash(into hasher: inout Hasher) {
        hasher.combine(c_handle)
    }
}
internal func ClassWithInterfaceParents_copyFromCType(_ handle: _baseRef) -> ClassWithInterfaceParents {
    if let swift_pointer = smoke_ClassWithInterfaceParents_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? ClassWithInterfaceParents {
        return re_constructed
    }
    if let swift_pointer = smoke_ClassWithInterfaceParents_get_typed(smoke_ClassWithInterfaceParents_copy_handle(handle)),
        let typed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeRetainedValue() as? ClassWithInterfaceParents {
        smoke_ClassWithInterfaceParents_cache_swift_object_wrapper(handle, swift_pointer)
        return typed
    }
    fatalError("Failed to initialize Swift object")
}
internal func ClassWithInterfaceParents_moveFromCType(_ handle: _baseRef) -> ClassWithInterfaceParents {
    if let swift_pointer = smoke_ClassWithInterfaceParents_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? ClassWithInterfaceParents {
        smoke_ClassWithInterfaceParents_release_handle(handle)
        return re_constructed
    }
    if let swift_pointer = smoke_ClassWithInterfaceParents_get_typed(handle),
        let typed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeRetainedValue() as? ClassWithInterfaceParents {
        smoke_ClassWithInterfaceParents_cache_swift_object_wrapper(handle, swift_pointer)
        return typed
    }
    fatalError("Failed to initialize Swift object")
}
internal func ClassWithInterfaceParents_copyFromCType(_ handle: _baseRef) -> ClassWithInterfaceParents? {
    guard handle != 0 else {
        return nil
    }
    return ClassWithInterfaceParents_moveFromCType(handle) as ClassWithInterfaceParents
}
internal func ClassWithInterfaceParents_moveFromCType(_ handle: _baseRef) -> ClassWithInterfaceParents? {
    guard handle != 0 else {
        return nil
    }
    return ClassWithInterfaceParents_moveFromCType(handle) as ClassWithInterfaceParents
}
internal func copyToCType(_ swiftClass: ClassWithInterfaceParents) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: ClassWithInterfaceParents) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
internal func copyToCType(_ swiftClass: ClassWithInterfaceParents?) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: ClassWithInterfaceParents?) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
