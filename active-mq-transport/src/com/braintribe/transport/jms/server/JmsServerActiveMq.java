// ============================================================================
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ============================================================================
// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
// 
// This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License along with this library; See http://www.gnu.org/licenses/.
// ============================================================================
package com.braintribe.transport.jms.server;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.braintribe.cfg.Required;
import com.braintribe.logging.Logger;
import com.braintribe.transport.jms.IServer;
import com.braintribe.transport.jms.queuecomm.QueueContext;
import com.braintribe.transport.jms.util.JmsExceptionListener;

public class JmsServerActiveMq implements IServer {

	protected static Logger logger = Logger.getLogger(JmsServerActiveMq.class);

	protected String providerURL = null;

	@Override
	public Connection createConnection() throws Exception {
		ConnectionFactory factory = new ActiveMQConnectionFactory(this.providerURL);

		Connection con = factory.createConnection();
		con.setExceptionListener(new JmsExceptionListener(logger));

		return con;
	}

	@Override
	public QueueContext getQueueContext(String queueName, boolean transactionalSession, int acknowledgeMode) throws Exception {
		QueueContext queueContext = new QueueContext(this, null, queueName, transactionalSession, acknowledgeMode);
		return queueContext;
	}

	public void destroy() throws Exception {
	}

	public String getProviderURL() {
		return providerURL;
	}

	@Required
	public void setProviderURL(String providerURL) {
		this.providerURL = providerURL;
	}

	public static String getBuildVersion() {
		return "$Build_Version$ $Id: JmsServerActiveMq.java 92403 2016-03-14 15:56:02Z roman.kurmanowytsch $";
	}
	
	@Override
	public String toString() {
		return "ActiveMQ:"+this.providerURL;
	}
}
