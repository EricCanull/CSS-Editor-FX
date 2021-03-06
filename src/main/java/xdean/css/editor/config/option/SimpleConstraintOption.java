package xdean.css.editor.config.option;

public abstract class SimpleConstraintOption<T> extends SimpleOption<T> implements ConstraintOption<T> {

  SimpleConstraintOption(T defaultValue, String describe) {
    super(defaultValue, describe);
  }

  @Override
  public void set(T t) {
    if (isValid(t)) {
      writableProperty().setValue(t);
    }
  }
}
