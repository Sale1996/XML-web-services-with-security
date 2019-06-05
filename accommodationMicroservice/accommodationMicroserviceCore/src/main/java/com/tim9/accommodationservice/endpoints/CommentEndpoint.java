package com.tim9.accommodationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.accommodationservice.models.GetCommentsRequest;
import com.tim9.accommodationservice.models.GetCommentsResponse;
import com.tim9.accommodationservice.services.CommentService;

@Endpoint
public class CommentEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com/accommodationService";
	private CommentService commentService;
	
	@Autowired
	public CommentEndpoint(CommentService commentService) {
		this.commentService = commentService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCommentsRequest")
	@ResponsePayload
	public GetCommentsResponse getComments(@RequestPayload GetCommentsRequest request) {
		GetCommentsResponse response = new GetCommentsResponse();
		response.setComments(commentService.findAllCommentsByAgentId(Long.parseLong(request.getId())));
		return response;
	}
}