package github.lightningcreations.lclib.annotation;

/**
 * A Value-based class. Value-based classes are immutable classes that act as values.
 * Constructors are not used to produce new instances, rather factory methods are,
 *  which may be implemented efficiently.
 * Comparison and hashing should be done with o.equals() (And Objects.equal),
 *  and o.hashCode() resp. Use of reference identity, identity hashcode, or synchronization
 *   may yield unexpected results as equal values may have the same identity, or different identity.
 *
 * ValueBased classes typically have Const factory methods and operations.
 */
public @interface ValueBased {
}
