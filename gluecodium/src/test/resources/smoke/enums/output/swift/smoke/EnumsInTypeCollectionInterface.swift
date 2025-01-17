//
//
import Foundation
public class EnumsInTypeCollectionInterface {
    let c_instance : _baseRef
    init(cEnumsInTypeCollectionInterface: _baseRef) {
        guard cEnumsInTypeCollectionInterface != 0 else {
            fatalError("Nullptr value is not supported for initializers")
        }
        c_instance = cEnumsInTypeCollectionInterface
    }
    deinit {
        smoke_EnumsInTypeCollectionInterface_remove_swift_object_from_wrapper_cache(c_instance)
        smoke_EnumsInTypeCollectionInterface_release_handle(c_instance)
    }
    public static func flipEnumValue(input: TCEnum) -> TCEnum {
        let c_input = moveToCType(input)
        let c_result_handle = smoke_EnumsInTypeCollectionInterface_flipEnumValue(c_input.ref)
        return moveFromCType(c_result_handle)
    }
}
internal func getRef(_ ref: EnumsInTypeCollectionInterface?, owning: Bool = true) -> RefHolder {
    guard let c_handle = ref?.c_instance else {
        return RefHolder(0)
    }
    let handle_copy = smoke_EnumsInTypeCollectionInterface_copy_handle(c_handle)
    return owning
        ? RefHolder(ref: handle_copy, release: smoke_EnumsInTypeCollectionInterface_release_handle)
        : RefHolder(handle_copy)
}
extension EnumsInTypeCollectionInterface: NativeBase {
    /// :nodoc:
    var c_handle: _baseRef { return c_instance }
}
extension EnumsInTypeCollectionInterface: Hashable {
    /// :nodoc:
    public static func == (lhs: EnumsInTypeCollectionInterface, rhs: EnumsInTypeCollectionInterface) -> Bool {
        return lhs.c_handle == rhs.c_handle
    }
    /// :nodoc:
    public func hash(into hasher: inout Hasher) {
        hasher.combine(c_handle)
    }
}
internal func EnumsInTypeCollectionInterface_copyFromCType(_ handle: _baseRef) -> EnumsInTypeCollectionInterface {
    if let swift_pointer = smoke_EnumsInTypeCollectionInterface_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? EnumsInTypeCollectionInterface {
        return re_constructed
    }
    let result = EnumsInTypeCollectionInterface(cEnumsInTypeCollectionInterface: smoke_EnumsInTypeCollectionInterface_copy_handle(handle))
    smoke_EnumsInTypeCollectionInterface_cache_swift_object_wrapper(handle, Unmanaged<AnyObject>.passUnretained(result).toOpaque())
    return result
}
internal func EnumsInTypeCollectionInterface_moveFromCType(_ handle: _baseRef) -> EnumsInTypeCollectionInterface {
    if let swift_pointer = smoke_EnumsInTypeCollectionInterface_get_swift_object_from_wrapper_cache(handle),
        let re_constructed = Unmanaged<AnyObject>.fromOpaque(swift_pointer).takeUnretainedValue() as? EnumsInTypeCollectionInterface {
        smoke_EnumsInTypeCollectionInterface_release_handle(handle)
        return re_constructed
    }
    let result = EnumsInTypeCollectionInterface(cEnumsInTypeCollectionInterface: handle)
    smoke_EnumsInTypeCollectionInterface_cache_swift_object_wrapper(handle, Unmanaged<AnyObject>.passUnretained(result).toOpaque())
    return result
}
internal func EnumsInTypeCollectionInterface_copyFromCType(_ handle: _baseRef) -> EnumsInTypeCollectionInterface? {
    guard handle != 0 else {
        return nil
    }
    return EnumsInTypeCollectionInterface_moveFromCType(handle) as EnumsInTypeCollectionInterface
}
internal func EnumsInTypeCollectionInterface_moveFromCType(_ handle: _baseRef) -> EnumsInTypeCollectionInterface? {
    guard handle != 0 else {
        return nil
    }
    return EnumsInTypeCollectionInterface_moveFromCType(handle) as EnumsInTypeCollectionInterface
}
internal func copyToCType(_ swiftClass: EnumsInTypeCollectionInterface) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: EnumsInTypeCollectionInterface) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
internal func copyToCType(_ swiftClass: EnumsInTypeCollectionInterface?) -> RefHolder {
    return getRef(swiftClass, owning: false)
}
internal func moveToCType(_ swiftClass: EnumsInTypeCollectionInterface?) -> RefHolder {
    return getRef(swiftClass, owning: true)
}
