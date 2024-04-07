package zlagoda.zlagoda.constants;

public interface Page {

    public static final String PREFIX = "/WEB-INF/views/";
    public static final String ERROR_PREFIX = "errors";
    public static final String SUFFIX = ".jsp";

    public static final String HOME_VIEW = "/index" + SUFFIX;
    public static final String LOGIN_VIEW = PREFIX + "login" + SUFFIX;

    public static final String PAGE_NOT_FOUND = PREFIX + ERROR_PREFIX + "pageNotFound" + SUFFIX;
}
