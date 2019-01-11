//
//
// Automatically generated. Do not modify. Your changes will be lost.
import Foundation
internal func getRef(_ ref: SimpleInterface?, owning: Bool = true) -> RefHolder {
    guard let reference = ref else {
        return RefHolder(0)
    }
    if let instanceReference = reference as? NativeBase {
        let handle_copy = smoke_SimpleInterface_copy_handle(instanceReference.c_handle)
        return owning
            ? RefHolder(ref: handle_copy, release: smoke_SimpleInterface_release_handle)
            : RefHolder(handle_copy)
    }
    var functions = smoke_SimpleInterface_FunctionTable()
    functions.swift_pointer = Unmanaged<AnyObject>.passRetained(reference).toOpaque()
    functions.release = {swift_class_pointer in
        if let swift_class = swift_class_pointer {
            Unmanaged<AnyObject>.fromOpaque(swift_class).release()
        }
    }
    functions.smoke_SimpleInterface_setStringValue = {(swift_class_pointer, stringValue) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! SimpleInterface
        defer {
            std_string_release_handle(stringValue)
        }
        swift_class.setStringValue(stringValue: String(data: Data(bytes: std_string_data_get(stringValue),
                                                count: Int(std_string_size_get(stringValue))), encoding: .utf8)!)
    }
    functions.smoke_SimpleInterface_getStringValue = {(swift_class_pointer) in
        let swift_class = Unmanaged<AnyObject>.fromOpaque(swift_class_pointer!).takeUnretainedValue() as! SimpleInterface
        return swift_class.getStringValue().convertToCType()
    }
    let proxy = smoke_SimpleInterface_create_proxy(functions)
    return owning ? RefHolder(ref: proxy, release: smoke_SimpleInterface_release_handle) : RefHolder(proxy)
}
public protocol SimpleInterface : AnyObject {
    func setStringValue(stringValue: String) -> Void
    func getStringValue() -> String
}
internal class _SimpleInterface: SimpleInterface {
    let c_instance : _baseRef
    init(cSimpleInterface: _baseRef) {
        guard cSimpleInterface != 0 else {
            fatalError("Nullptr value is not supported for initializers")
        }
        c_instance = cSimpleInterface
    }
    deinit {
        smoke_SimpleInterface_release_handle(c_instance)
    }
    public func setStringValue(stringValue: String) -> Void {
        return moveFromCType(smoke_SimpleInterface_setStringValue(c_instance, stringValue))
    }
    public func getStringValue() -> String {
        return moveFromCType(smoke_SimpleInterface_getStringValue(c_instance))
    }
}
extension _SimpleInterface: NativeBase {
    var c_handle: _baseRef { return c_instance }
}