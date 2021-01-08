//
//
import Foundation
public class ClassWithAllParents: ParentClass, ParentInterfaceOne, ParentInterfaceTwo {
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
            return moveFromCType(smoke_ClassWithAllParents_childProperty_get(self.c_instance))
        }
        set {
            let c_value = moveToCType(newValue)
            return moveFromCType(smoke_ClassWithAllParents_childProperty_set(self.c_instance, c_value.ref))
        }
    }
    init(cClassWithAllParents: _baseRef) {
        super.init(cParentClass: cClassWithAllParents)
    }
    public func parentFunctionOne() -> Void {
        return moveFromCType(smoke_ParentInterfaceOne_parentFunctionOne(self.c_instance))
    }
    public func parentFunctionTwo() -> Void {
        return moveFromCType(smoke_ParentInterfaceTwo_parentFunctionTwo(self.c_instance))
    }
    public func childFunction() -> Void {
        return moveFromCType(smoke_ClassWithAllParents_childFunction(self.c_instance))
    }
}
@_cdecl("_CBridgeInitsmoke_ClassWithAllParents")
internal func _CBridgeInitsmoke_ClassWithAllParents(handle: _baseRef) -> UnsafeMutableRawPointer {
    let reference = ClassWithAllParents(cClassWithAllParents: handle)
    return Unmanaged<AnyObject>.passRetained(reference).toOpaque()
}
internal func getRef(_ ref: ClassWithAllParents?, owning: Bool = true) -> RefHolder {
    guard let c_handle = ref?.c_instance else {
        return RefHolder(0)
    }
    let handle_copy = smoke_ClassWithAllParents_copy_handle(c_handle)
    return owning
        ? RefHolder(ref: handle_copy, release: smoke_ClassWithAllParents_release_handle)
        : RefHolder(handle_copy)
}
internal func ClassWithAllParents_copyFromCType(_ handle: _baseRef) -> ClassWithAllParents {
    if let swift_pointer = smoke_ClassWithAllParents_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? ClassWithAllParents {
        return re_constructed
    }
    if let swift_pointer = smoke_ClassWithAllParents_get_typed(smoke_ClassWithAllParents_copy_handle(handle)),
        let typed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeRetainedValue() as? ClassWithAllParents {
        smoke_ClassWithAllParents_cache_swift_object_wrapper(handle, swift_pointer)
        return typed
    }
    fatalError("Failed to initialize Swift object")
}
internal func ClassWithAllParents_moveFromCType(_ handle: _baseRef) -> ClassWithAllParents {
    if let swift_pointer = smoke_ClassWithAllParents_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? ClassWithAllParents {
        smoke_ClassWithAllParents_release_handle(handle)
        return re_constructed
    }
    if let swift_pointer = smoke_ClassWithAllParents_get_typed(handle),
        let typed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeRetainedValue() as? ClassWithAllParents {
        smoke_ClassWithAllParents_cache_swift_object_wrapper(handle, swift_pointer)
        return typed
    }
    fatalError("Failed to initialize Swift object")
}
internal func ClassWithAllParents_copyFromCType(_ handle: _baseRef) -> ClassWithAllParents? {
    guard handle != 0 else {
        return nil
    }
    return ClassWithAllParents_moveFromCType(handle) as ClassWithAllParents
}
internal func ClassWithAllParents_moveFromCType(_ handle: _baseRef) -> ClassWithAllParents? {
    guard handle != 0 else {
        return nil
    }
    return ClassWithAllParents_moveFromCType(handle) as ClassWithAllParents
}
internal func copyToCType(_ swiftClass: ClassWithAllParents) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: ClassWithAllParents) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
internal func copyToCType(_ swiftClass: ClassWithAllParents?) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: ClassWithAllParents?) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
