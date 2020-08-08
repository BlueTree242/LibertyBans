/* 
 * LibertyBans-api
 * Copyright © 2020 Anand Beh <https://www.arim.space>
 * 
 * LibertyBans-api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * LibertyBans-api is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with LibertyBans-api. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Affero General Public License.
 */
package space.arim.libertybans.core.env;

import space.arim.api.chat.SendableMessage;
import space.arim.api.env.annote.PlatformCommandSender;

import space.arim.libertybans.api.Operator;
import space.arim.libertybans.core.LibertyBansCore;

public abstract class AbstractCmdSender implements CmdSender {
	
	private final LibertyBansCore core;
	private final Object rawSender;
	private final Operator operator;
	
	protected AbstractCmdSender(LibertyBansCore core, Object rawSender, Operator operator) {
		this.core = core;
		this.rawSender = rawSender;
		this.operator = operator;
	}
	
	@Override
	public Operator getOperator() {
		return operator;
	}
	
	@Override
	public void sendMessage(SendableMessage message) {
		core.getEnvironment().getPlatformHandle().sendMessage(rawSender, message);
	}
	
	@Override
	public void parseThenSend(String message) {
		sendMessage(core.getFormatter().parseMessage(message));
	}
	
	@Override
	@PlatformCommandSender
	public Object getRawSender() {
		return rawSender;
	}
	
}
