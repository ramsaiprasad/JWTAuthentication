package net.codejava;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbWrite  implements ItemWriter<UserBatch>{
	@Autowired
	private UserBatchRepository userbatch;

	@Override
	public void write(List<? extends UserBatch> items) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("data save to Data base "+items);
		
		userbatch.saveAll(items);
		
	}

}
