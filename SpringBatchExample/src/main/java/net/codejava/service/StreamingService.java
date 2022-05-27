package net.codejava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.Repository.StreamRepository;
import net.codejava.model.Streaming;

@Service
public class StreamingService {

	@Autowired
	private StreamRepository SRepo;
	
	public StreamingService()
	{
		
	}
	public Streaming addStreamingwithViewers(StreamingRequest request)
	{
		Streaming streaming=new Streaming();
		
		streaming.setId(request.getId());
		streaming.setName(request.getName());
		return SRepo.save(streaming);
	}
}
