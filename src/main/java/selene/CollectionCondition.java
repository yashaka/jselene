package selene;

import selene.ElementsCollection;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Function;

public interface CollectionCondition extends Function<ElementsCollection, List<WebElement>> {
}
