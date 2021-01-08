//
//
import Foundation
public class ClassWithMixedParents: ParentClass, ParentInterfaceOne {
    public var parentPropertyOne: String {
        get {
            return moveFromCType(smoke_ParentInterfaceOne_parentPropertyOne_get(self.c_instance))
        }
        set {
            let c_value = moveToCType(newValue)
            return moveFromCType(smoke_ParentInterfaceOne_parentPropertyOne_set(self.c_instance, c_value.ref))
        }
    }
    public var childProperty: String {
        get {
            return moveFromCType(smoke_ClassWithMixedParents_childProperty_get(self.c_instance))
        }
        set {
            let c_value = moveToCType(newValue)
            return moveFromCType(smoke_ClassWithMixedParents_childProperty_set(self.c_instance, c_value.ref))
        }
    }
    init(cClassWithMixedParents: _baseRef) {
        super.init(cParentClass: cClassWithMixedParents)
    }
    public func parentFunctionOne() -> Void {
        return moveFromCType(smoke_ParentInterfaceOne_parentFunctionOne(self.c_instance))
    }
    public func childFunction() -> Void {
        return moveFromCType(smoke_ClassWithMixedParents_childFunction(self.c_instance))
    }
}
@_cdecl("_CBridgeInitsmoke_ClassWithMixedParents")
internal func _CBridgeInitsmoke_ClassWithMixedParents(handle: _baseRef) -> UnsafeMutableRawPointer {
    let reference = ClassWithMixedParents(cClassWithMixedParents: handle)
    return Unmanaged<AnyObject>.passRetained(reference).toOpaque()
}
internal func getRef(_ ref: ClassWithMixedParents?, owning: Bool = true) -> RefHolder {
    guard let c_handle = ref?.c_instance else {
        return RefHolder(0)
    }
    let handle_copy = smoke_ClassWithMixedParents_copy_handle(c_handle)
    return owning
        ? RefHolder(ref: handle_copy, release: smoke_ClassWithMixedParents_release_handle)
        : RefHolder(handle_copy)
}
internal func ClassWithMixedParents_copyFromCType(_ handle: _baseRef) -> ClassWithMixedParents {
    if let swift_pointer = smoke_ClassWithMixedParents_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? ClassWithMixedParents {
        return re_constructed
    }
    if let swift_pointer = smoke_ClassWithMixedParents_get_typed(smoke_ClassWithMixedParents_copy_handle(handle)),
        let typed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeRetainedValue() as? ClassWithMixedParents {
        smoke_ClassWithMixedParents_cache_swift_object_wrapper(handle, swift_pointer)
        return typed
    }
    fatalError("Failed to initialize Swift object")
}
internal func ClassWithMixedParents_moveFromCType(_ handle: _baseRef) -> ClassWithMixedParents {
    if let swift_pointer = smoke_ClassWithMixedParents_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? ClassWithMixedParents {
        smoke_ClassWithMixedParents_release_handle(handle)
        return re_constructed
    }
    if let swift_pointer = smoke_ClassWithMixedParents_get_typed(handle),
        let typed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeRetainedValue() as? ClassWithMixedParents {
        smoke_ClassWithMixedParents_cache_swift_object_wrapper(handle, swift_pointer)
        return typed
    }
    fatalError("Failed to initialize Swift object")
}
internal func ClassWithMixedParents_copyFromCType(_ handle: _baseRef) -> ClassWithMixedParents? {
    guard handle != 0 else {
        return nil
    }
    return ClassWithMixedParents_moveFromCType(handle) as ClassWithMixedParents
}
internal func ClassWithMixedParents_moveFromCType(_ handle: _baseRef) -> ClassWithMixedParents? {
    guard handle != 0 else {
        return nil
    }
    return ClassWithMixedParents_moveFromCType(handle) as ClassWithMixedParents
}
internal func copyToCType(_ swiftClass: ClassWithMixedParents) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: ClassWithMixedParents) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
internal func copyToCType(_ swiftClass: ClassWithMixedParents?) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: ClassWithMixedParents?) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
