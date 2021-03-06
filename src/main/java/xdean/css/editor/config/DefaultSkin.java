package xdean.css.editor.config;

import xdean.jex.util.string.StringUtil;
import xdean.jfx.ex.support.skin.SkinStyle;

public enum DefaultSkin implements SkinStyle {
  CLASSIC("classic"),
  DEFAULT("default"),
  METAL("metal"),
  PINK("pink"),
  GTK_DARK("gtk-dark");

  private static final String CSS_PATH = "/css/skin/";

  private String path;

  private DefaultSkin(String name) {
    try {
      this.path = DefaultSkin.class.getResource(CSS_PATH + name + ".css").toExternalForm();
    } catch (NullPointerException e) {// If the resource not exist
      this.path = DefaultSkin.class.getResource(CSS_PATH + name + ".bss").toExternalForm();
    }
  }

  @Override
  public String getURL() {
    return path;
  }

  @Override
  public String getName() {
    return StringUtil.upperFirst(toString());
  }
}