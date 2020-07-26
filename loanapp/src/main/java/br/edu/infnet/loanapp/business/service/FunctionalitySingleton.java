package br.edu.infnet.loanapp.business.service;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import br.edu.infnet.loanapp.business.model.Client;
import br.edu.infnet.loanapp.business.type.ClientType;
import br.edu.infnet.loanapp.core.constants.URLConsts;
import br.edu.infnet.loanapp.dto.model.FunctionalityDTO;

public final class FunctionalitySingleton {

	private static FunctionalitySingleton instance;

	private final EnumMap<ClientType, List<FunctionalityDTO>> functionalities;

	private FunctionalitySingleton() {
		super();
		this.functionalities = new EnumMap<>(ClientType.class);
		this.populateFunctionalities();
	}

	public static FunctionalitySingleton getInstance() {
		if (instance == null) {
			instance = new FunctionalitySingleton();
		}
		return instance;
	}

	public List<FunctionalityDTO> getFunctionalitiesByClientType(final ClientType clientType) {
		return this.functionalities.get(clientType);
	}

	public boolean isPathValid(final Client client, final String path) {

		final ClientType clientType = ClientType.getFromClientInstance(client);

		if (client == null || clientType == null) {
			throw new RuntimeException("Object is invalid");
		}

		return this.functionalities//
				.get(clientType)//
				.stream()//
				.map(FunctionalityDTO::getRoute) //
				.anyMatch(item -> item.equals(path));

	}

	private void populateFunctionalities() {

		this.functionalities.computeIfAbsent(ClientType.COLLECTOR, key -> {

			return Arrays.asList(//
					FunctionalityDTO.newInstance(URLConsts.getContractPath(), "Adicionar Contrato", key, true), //
					FunctionalityDTO.newInstance(URLConsts.getClientPath(), "Adicionar Cliente", key, false));
		});

		this.functionalities.computeIfAbsent(ClientType.CUSTOMER, key -> {

			return Arrays.asList(//
					FunctionalityDTO.newInstanceHidden(URLConsts.getPaymentPath(), "Pagamento", key, true), //
					FunctionalityDTO.newInstance(URLConsts.getContractListPath(), "Contratos", key, true), //
					FunctionalityDTO.newInstanceHidden(URLConsts.getPaymentListPath(), "Pagamentos", key, true));
		});
	}

}
