package com.jacamars.dsp.crosstalk.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.jacamars.dsp.crosstalk.budget.CrosstalkConfig;
import com.jacamars.dsp.rtb.common.Campaign;
import com.jacamars.dsp.rtb.tools.JdbcTools;
import com.jacamars.dsp.crosstalk.budget.Crosstalk;

/**
 * Returns an empty campaign object.
 * @author Ben M. Faul
 *
 */
public class SQLDeleteCreativeCmd extends ApiCommand {
	
	ResultSet rs = null;
	public int id;
	public String key;

	/**
	 * Default constructor
	 */
	public SQLDeleteCreativeCmd() {

	}

	/**
	 * Convert to JSON
	 */
	public String toJson() throws Exception {
		return WebAccess.mapper.writeValueAsString(this);
	}

	/**
	 * Execute the command, masrshall the results.
	 */
	@Override
		public void execute() {
			super.execute();
			try {
				String sql = null;
				switch(key) {
				case "banner":
					sql = "delete from banners where id=?";
					break;
				case "video":
					sql = "delete  from banner_videos where id=?";
					break;
				case "audio":
					sql = "delete from banner_audios where id=?";
					break;
				case "native":
					sql = "delete from banner_natives where id=?";
					break;
				default:
					throw new Exception("Can't delete unknown type: " + key);
				}
				PreparedStatement st = CrosstalkConfig.getInstance().getConnection().
						prepareStatement(sql);
				st.setInt(1, id);
				st.executeUpdate();
				st.close();
				 
				Campaign.removeCreativeFromCampaigns(id, key, tokenData);
				return;
			} catch (Exception err) { 
				err.printStackTrace();
				error = true;
				message = err.toString();
			}
		}
	
}
