package com.jacamars.dsp.crosstalk.api;


/**
 * Web APU that dcuments an unknwown  campaign
 * @author Ben M. Faul
 *
 */
public class UnknownCmd extends ApiCommand {


	/** The command that was attempted, but not known to the API */
	public String command;
	
	/**
	 * Default constructor
	 */
	public UnknownCmd() {

	}
	
	public UnknownCmd(String s) {
		command = s;
	}

	/**
	 * Convert to JSON
	 */
	public String toJson() throws Exception {
		return WebAccess.mapper.writeValueAsString(this);
	}

	/**
	 * Execute the command, marshall the results.
	 */
	@Override
		public void execute() {
			super.execute();
			message = "Unkown command: " + command;
			error = true;
		}
}
