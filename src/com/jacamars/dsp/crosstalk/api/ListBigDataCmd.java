package com.jacamars.dsp.crosstalk.api;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.hazelcast.core.HazelcastInstance;
import com.jacamars.dsp.crosstalk.budget.Crosstalk;
import com.jacamars.dsp.rtb.bidder.RTBServer;
import com.jacamars.dsp.rtb.blocks.LookingGlass;
import com.jacamars.dsp.rtb.common.Campaign;
import com.jacamars.dsp.rtb.common.Configuration;
import com.jacamars.dsp.rtb.shared.BidCachePool;
import com.jacamars.dsp.rtb.shared.CampaignCache;

/**
 * Web API to list all campaigns known by crosstalk
 * @author Ben M. Faul
 *
 */
public class ListBigDataCmd extends ApiCommand {

	/** The list of campaigns */
	public List<Map<String,String>> catalog;
	public TreeMap<String,Integer> hazelcast;

	/**
	 * Default constructor
	 */
	public ListBigDataCmd() {

	}

	/**
	 * Convert to JSON
	 */
	public String toJson() throws Exception {
		return WebAccess.mapper.writeValueAsString(this);
	}

	/**
	 * Execute the command, marshal the results.
	 */
	@Override
		public void execute() {
			super.execute();
			
			// TBD: Needs rewrite for multi tenant
			
			try {
				catalog = new ArrayList<>(); 
				
				TreeMap<String,Object> map = new TreeMap(LookingGlass.symbols); // sort it
				
				map.forEach((key,value)-> {
					String 	type = value.getClass().getName();
					Map<String,String> m = new HashMap<>();
					m.put("name", key);
					m.put("type",type);
					LookingGlass x = (LookingGlass)value;
					m.put("file", x.fileName);
					m.put("s3",x.s3);
					m.put("size", "" + x.getMembers());
					catalog.add(m);
					hazelcast = new TreeMap<>(BidCachePool.getStats());
				});
				
				return;
			} catch (Exception err) {
				error = true;
				message = err.toString();
			}
			error = true;
		}
}
