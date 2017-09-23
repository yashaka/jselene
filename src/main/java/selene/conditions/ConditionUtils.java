package selene.conditions;

import selene.exceptions.SeleneError;

import java.util.function.Function;

/**
 * Created by yashaka on 3/30/17.
 */
public class ConditionUtils {

    public static <P, R> boolean checkCondition(P entity, Function<P, R> condition) {
        try {
            condition.apply(entity);
            return true;
        } catch (SeleneError e) {
            return false;
        }
    }

/*    public static <P, R> Function<P, R> negate(Function<P, R> condition) {
        return new Function<P, R>() {
            @Override
            public R apply(P p) {
                R result;
                try {
                    result = condition.apply(p);
                } catch (exception)
            }

            @Override
            public String toString() {
                return String.format("not(%s)", condition.toString());
            }
        };
    }*/
}
