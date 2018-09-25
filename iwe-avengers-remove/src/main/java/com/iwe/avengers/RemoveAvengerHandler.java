package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDAO;
import com.iwe.avengers.exception.AvengerNotFoundException;

public class RemoveAvengerHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDAO dao = new AvengerDAO();

	@Override
	public HandlerResponse handleRequest(final Avenger avenger, final Context context) {

		context.getLogger().log("[#] - Deleting avenger by id: " + avenger.getId());

		if (dao.search(avenger.getId()) == null) {
			throw new AvengerNotFoundException("[NotFound] Avenger id " + avenger.getId());
		}

		dao.remove(avenger);

		context.getLogger().log("[#] - Deleted avenger by id: " + avenger.getId());

		final HandlerResponse response = HandlerResponse.builder().setObjectBody(avenger).build();

		return response;
	}
}
