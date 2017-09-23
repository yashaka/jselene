package selene;


import selene.Browser;

public class HTMLBuilder {

  private final Browser driver;
  public final Given given;
  public final When when;

  public HTMLBuilder(Browser driver) {
    this.driver = driver;
    this.given = new Given();
    this.when = new When();
  }

  private void execute(String jsCommand) {
    this.driver.executeJavaScript(jsCommand);
  }

  public class When {
    public void withBody(String... html) {
      execute(
          String.join(" ",
              "document.getElementsByTagName('body')[0].innerHTML =",
              "\"",
              String.join(" ", html).replace("\n", " "),
              "\";"
          )
      );
    }

    public void withBodyTimedOut(int timeout, String... html) {
      execute(
          String.join(" ",
              "setTimeout(",
              "function(){",
              "document.getElementsByTagName('body')[0].innerHTML = \"",
              String.join(" ", html).replace("\n", " "),
              "\" },",
              timeout + ");"
          )
      );
    }

    public void executeScriptWithTimeout(int timeout, String... js) {
      execute(
          String.join(" ",
              "setTimeout(",
              "function(){",
              String.join(" ", js),
              "},",
              timeout + ");"
          )
      );
    }
  }

  public class Given {
    public void openedEmptyPage() {
      driver.open("file:///Users/ayia/projects/educist/gitbook/the-art-of-test-automation-selenide-java-demo/src/selenide-demo/src/test/resources/empty.html");
    }

    public void openedPageWithBody(String... html) {
      openedEmptyPage();
      when.withBody(html);
    }
  }

}
