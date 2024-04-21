package zlagoda.zlagoda.controller.command.i18n;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import zlagoda.zlagoda.constants.Attribute;
import zlagoda.zlagoda.constants.Page;
import zlagoda.zlagoda.controller.command.Command;
import zlagoda.zlagoda.locale.MessageLocale;

import java.util.Locale;

public class ChangeLocaleCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		setLocale(request);
		return Page.HOME_VIEW;
	}

	private void setLocale(HttpServletRequest request) {
		String selectedLanguage = request.getParameter(Attribute.LANG);
		Locale chosenLocale = AppLocale.forValue(selectedLanguage);
		
		request.getSession().setAttribute(Attribute.LOCALE, chosenLocale);
		MessageLocale.setResourceBundleLocale(chosenLocale);
	}
}