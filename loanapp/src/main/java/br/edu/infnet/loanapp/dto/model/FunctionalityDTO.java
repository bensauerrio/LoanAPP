package br.edu.infnet.loanapp.dto.model;

import java.io.Serializable;

import br.edu.infnet.loanapp.business.type.ClientType;

public class FunctionalityDTO implements Serializable {

	private static final long serialVersionUID = 1992218710934135798L;

	private String route;

	private String name;

	private ClientType clientType;

	private boolean enabled;

	private boolean hidden;

	private FunctionalityDTO() {
		super();
	}

	private FunctionalityDTO(final String route, final String name, final ClientType clientType,
			final boolean enabled) {
		super();
		this.route = route;
		this.name = name;
		this.clientType = clientType;
		this.enabled = enabled;
	}

	public static FunctionalityDTO newInstance(//
			final String route, //
			final String name, //
			final ClientType clientType, //
			final boolean enabled) {
		return new FunctionalityDTO(route, name, clientType, enabled);
	}

	public static FunctionalityDTO newInstanceHidden(//
			final String route, //
			final String name, //
			final ClientType clientType, //
			final boolean enabled) {
		final FunctionalityDTO functionalityDTO = new FunctionalityDTO(route, name, clientType, enabled);
		functionalityDTO.setHidden(true);
		return functionalityDTO;
	}

	public String getRoute() {
		return this.route;
	}

	public void setRoute(final String route) {
		this.route = route;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public ClientType getClientType() {
		return this.clientType;
	}

	public void setClientType(final ClientType clientType) {
		this.clientType = clientType;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return String.format("FunctionalityDTO [route=%s, name=%s, clientType=%s, enabled=%s]", this.route, this.name,
				this.clientType, this.enabled);
	}

	public boolean isHidden() {
		return this.hidden;
	}

	public void setHidden(final boolean hidden) {
		this.hidden = hidden;
	}

}
