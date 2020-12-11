package github.lightningcreations.lclib.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.CONSTRUCTOR;

import java.lang.annotation.Target;

/**
 * A method or constructor which has no side-effects or observable behaviour,
 *  and for which the output is repeatable from the input.
 */
@Target({METHOD, CONSTRUCTOR})
public @interface Const {

}
