package br.edu.infnet.loanapp.core.constants;

public class URLConsts {

	private static final String MAIN_MENU_PATH = "/mainmenu";

	private static final String LOGIN_PATH = "/login";

	private static final String CONTRACT_PATH = "/contract";

	private static final String PAYMENT_PATH = "/payment";

	private static final String CLIENT_PATH = "/client";

	private static final String PAYMENT_LIST_PATH = "/payment_list";

	private static final String CONTRACT_LIST_PATH = "/contract_list";

	private URLConsts() {
		super();
	}

	public static String getMainMenuPath() {
		return MAIN_MENU_PATH;
	}

	public static String getLoginPath() {
		return LOGIN_PATH;
	}

	public static String getPaymentPath() {
		return PAYMENT_PATH;
	}

	public static String getContractPath() {
		return CONTRACT_PATH;
	}

	public static String getClientPath() {
		return CLIENT_PATH;
	}

	public static String getPaymentListPath() {
		return PAYMENT_LIST_PATH;
	}

	public static String getContractListPath() {
		return CONTRACT_LIST_PATH;
	}

}
