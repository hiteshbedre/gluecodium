//
//
// Automatically generated. Do not modify. Your changes will be lost.
import Foundation
public enum ShoeSizes : UInt32 {
    case small = 39
    case biggerThanSmall
    case normal = 43
    case big = 46
}
internal func copyFromCType(_ cValue: UInt32) -> ShoeSizes {
    return ShoeSizes(rawValue: cValue)!
}
internal func moveFromCType(_ cValue: UInt32) -> ShoeSizes {
    return copyFromCType(cValue)
}