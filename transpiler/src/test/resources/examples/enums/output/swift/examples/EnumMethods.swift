//
// Copyright (C) 2018 HERE Global B.V. and/or its affiliated companies. All rights reserved.
//
// This software, including documentation, is protected by copyright controlled by
// HERE Global B.V. All rights are reserved. Copying, including reproducing, storing,
// adapting or translating, any or all of this material requires the prior written
// consent of HERE Global B.V. This material also contains confidential information,
// which may not be disclosed to others without prior written consent of HERE Global B.V.
//
// Automatically generated. Do not modify. Your changes will be lost.

import Foundation



internal func getRef(_ ref: EnumMethods) -> RefHolder {
    return RefHolder(ref.c_instance)
}
public class EnumMethods {
    let c_instance : _baseRef

    public init?(cEnumMethods: _baseRef) {
        guard cEnumMethods != 0 else {
            return nil
        }
        c_instance = cEnumMethods
    }

    deinit {
        examples_EnumMethods_release(c_instance)
    }
    public static func methodWithEnumeration(input: ShoeSizes) -> ShoeSizes {
        let cResult = examples_EnumMethods_methodWithEnumeration(input.rawValue)
        return ShoeSizes(rawValue: cResult)!
    }

}
