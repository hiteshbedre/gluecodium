//
//
// Automatically generated. Do not modify. Your changes will be lost.

import Foundation



internal func getRef(_ ref: NestedInstantiable?, owning: Bool = false) -> RefHolder {
    return RefHolder(ref?.c_instance ?? 0)
}
public class NestedInstantiable {
    let c_instance : _baseRef

    init?(cNestedInstantiable: _baseRef) {
        guard cNestedInstantiable != 0 else {
            return nil
        }
        c_instance = cNestedInstantiable
    }

    deinit {
        smoke_NestedInstantiable_release(c_instance)
    }
    public func setSameTypeInstances(instanceOne: SimpleInstantiable?, instanceTwo: SimpleInstantiable?) -> Void {
        let instanceOne_handle = getRef(instanceOne)
        let instanceTwo_handle = getRef(instanceTwo)
        return smoke_NestedInstantiable_setSameTypeInstances(c_instance, instanceOne_handle.ref, instanceTwo_handle.ref)
    }

    public func getInstanceOne() -> SimpleInstantiable? {
        let cResult = smoke_NestedInstantiable_getInstanceOne(c_instance)
        return SimpleInstantiable(cSimpleInstantiable: cResult)
    }
    public func instanceNotNullMethod(input: SimpleInstantiable?) -> SimpleInstantiable? {
        let input_handle = getRef(input)
        let cResult = smoke_NestedInstantiable_instanceNotNullMethod(c_instance, input_handle.ref)
        return SimpleInstantiable(cSimpleInstantiable: cResult)
    }
}

extension NestedInstantiable: NativeBase {
    var c_handle: _baseRef { return c_instance }
}