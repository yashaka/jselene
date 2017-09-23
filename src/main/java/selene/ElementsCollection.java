package selene;

import selene.locators.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;
import java.util.List;

/**
 * Created by yashaka on 3/30/17.
 */
public class ElementsCollection implements java.util.Collection<Element> {  // todo: rename to Collection?
    private final Locator<List<WebElement>> locator;
    private final DriverContext context;

    ElementsCollection(Locator<List<WebElement>> locator, DriverContext context) {
        this.locator = locator;
        this.context = context;
    }

    public ElementsCollection(By by, DriverContext context) {
        this(new ByWebElementsListLocator(by, context.driver()), context);
    }

    public List<WebElement> getActualWebElements() {
        return this.locator.find();
    }

    public ElementsCollection should(CollectionCondition condition) {
        new Wait<>(this, this.context.getCapability("selene.collection.wait.timeout", 4000L)).until(condition);
        return this;
    }

    @Override
    public String toString() {
        return this.locator.description();
    }

    public Element elementBy(ElementCondition condition) {
        return new Element(new FoundByConditionCollectionWebElementLocator(this, condition), context);
    }

    public ElementsCollection filteredBy(ElementCondition condition) {
        return new ElementsCollection(new FilteredByConditionWebElementsListLocator(this, condition), context);
    }

    public ElementsCollection excludedBy(ElementCondition condition) {
        // todo: refactor to use "negated condition" not non-DRY ExcludedBlaBlaLocator ...
        return new ElementsCollection(new ExcludedByConditionWebElementsListLocator(this, condition), context);
    }

    @Override
    public int size() {
        return this.getActualWebElements().size();
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Iterator<Element> iterator() {
        List<WebElement> webElements = this.getActualWebElements();
        ElementsCollection originalCollection = this;
        return new Iterator<Element>() {
            protected int index = 0;

            @Override
            public boolean hasNext() {
                return webElements.size() > this.index;
            }

            @Override
            public Element next() {
                Element indexedElement = new Element(new CachedWebElementLocator(webElements.get(index),
                        String.format("(%s)[%s]", originalCollection, index)), context);
                this.index += 1;
                return indexedElement;
            }
        };
    }

    /*
     * Private
     */

    // ...

    /*
     * Some redundant bullshit below... :)
     */

    @Override
    public boolean contains(Object o) {
        throw new NotImplementedException();
//        return false;
    }

    @Override
    public Object[] toArray() {
        throw new NotImplementedException();
//        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new NotImplementedException();
//        return null;
    }

    @Override
    public boolean add(Element element) {
        throw new NotImplementedException();
//        return false;
    }

    @Override
    public boolean remove(Object o) {
        throw new NotImplementedException();
//        return false;
    }

    @Override
    public boolean containsAll(java.util.Collection<?> c) {
        throw new NotImplementedException();
//        return false;
    }

    @Override
    public boolean addAll(java.util.Collection<? extends Element> c) {
        throw new NotImplementedException();
//        return false;
    }

    @Override
    public boolean removeAll(java.util.Collection<?> c) {
        throw new NotImplementedException();
//        return false;
    }

    @Override
    public boolean retainAll(java.util.Collection<?> c) {
        throw new NotImplementedException();
//        return false;
    }

    @Override
    public void clear() {
        throw new NotImplementedException();
    }
}
