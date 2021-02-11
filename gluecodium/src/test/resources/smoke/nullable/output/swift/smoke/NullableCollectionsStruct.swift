//
//
import Foundation
public struct NullableCollectionsStruct {
    public var dates: [Date?]
    public var structs: [Int32: Nullable.SomeStruct?]
    public init(dates: [Date?], structs: [Int32: Nullable.SomeStruct?]) {
        self.dates = dates
        self.structs = structs
    }
    internal init(cHandle: _baseRef) {
        dates = foobar_moveFromCType(smoke_NullableCollectionsStruct_dates_get(cHandle))
        structs = foobar_moveFromCType(smoke_NullableCollectionsStruct_structs_get(cHandle))
    }
}
internal func foobar_copyFromCType(_ handle: _baseRef) -> NullableCollectionsStruct {
    return NullableCollectionsStruct(cHandle: handle)
}
internal func foobar_moveFromCType(_ handle: _baseRef) -> NullableCollectionsStruct {
    defer {
        smoke_NullableCollectionsStruct_release_handle(handle)
    }
    return foobar_copyFromCType(handle)
}
internal func foobar_copyToCType(_ swiftType: NullableCollectionsStruct) -> RefHolder {
    let c_dates = foobar_moveToCType(swiftType.dates)
    let c_structs = foobar_moveToCType(swiftType.structs)
    return RefHolder(smoke_NullableCollectionsStruct_create_handle(c_dates.ref, c_structs.ref))
}
internal func foobar_moveToCType(_ swiftType: NullableCollectionsStruct) -> RefHolder {
    return RefHolder(ref: foobar_copyToCType(swiftType).ref, release: smoke_NullableCollectionsStruct_release_handle)
}
internal func foobar_copyFromCType(_ handle: _baseRef) -> NullableCollectionsStruct? {
    guard handle != 0 else {
        return nil
    }
    let unwrappedHandle = smoke_NullableCollectionsStruct_unwrap_optional_handle(handle)
    return NullableCollectionsStruct(cHandle: unwrappedHandle) as NullableCollectionsStruct
}
internal func foobar_moveFromCType(_ handle: _baseRef) -> NullableCollectionsStruct? {
    defer {
        smoke_NullableCollectionsStruct_release_optional_handle(handle)
    }
    return foobar_copyFromCType(handle)
}
internal func foobar_copyToCType(_ swiftType: NullableCollectionsStruct?) -> RefHolder {
    guard let swiftType = swiftType else {
        return RefHolder(0)
    }
    let c_dates = foobar_moveToCType(swiftType.dates)
    let c_structs = foobar_moveToCType(swiftType.structs)
    return RefHolder(smoke_NullableCollectionsStruct_create_optional_handle(c_dates.ref, c_structs.ref))
}
internal func foobar_moveToCType(_ swiftType: NullableCollectionsStruct?) -> RefHolder {
    return RefHolder(ref: foobar_copyToCType(swiftType).ref, release: smoke_NullableCollectionsStruct_release_optional_handle)
}
