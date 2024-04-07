package zlagoda.zlagoda.locale;

import java.util.ResourceBundle;
import java.util.Locale;

public final class MessageLocale {

	private MessageLocale() {
	}

	public static final String MESSAGES_BUNDLE_NAME = "i18n/messages";

	public static ResourceBundle BUNDLE = ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME, new Locale("en", "US"));

	public static void setResourceBundleLocale(Locale locale) {
		BUNDLE = ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME, locale);
	}
}