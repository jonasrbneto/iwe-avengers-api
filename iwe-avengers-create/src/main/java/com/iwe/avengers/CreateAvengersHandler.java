package com.iwe.avengers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.iwe.avenger.dynamodb.entity.Avenger;
import com.iwe.avenger.lambda.response.HandlerResponse;
import com.iwe.avengers.dao.AvengerDAO;

public class CreateAvengersHandler implements RequestHandler<Avenger, HandlerResponse> {

	private AvengerDAO dao = new AvengerDAO();

	@Override
	public HandlerResponse handleRequest(final Avenger newAvenger, final Context context) {

		context.getLogger().log("[#] - Creating avenger by id: " + newAvenger.getName());

		Avenger avenger = dao.create(newAvenger);

		context.getLogger().log("[#] - Created avenger by id: " + avenger.getId());

		final HandlerResponse response = HandlerResponse.builder().setObjectBody(avenger).build();

		return response;

	}
}
