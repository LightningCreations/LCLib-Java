package github.lightningcreations.lclib.jnix.annotations;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Target;

/**
 * Apply to a method if calling it can result in C++ Core Language or Standard Library Undefined Behavior.
 * This annotation is NOT appropriate for use to define Library specific preconditions, only undefined behavior declared by the C++ Standard.
 * @author chorm
 */
@Target(METHOD)
public @interface PotentiallyUndefined {

}
